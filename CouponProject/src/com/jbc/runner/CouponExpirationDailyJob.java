package com.jbc.runner;

import java.sql.Connection;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.jbc.beans.Coupon;
import com.jbc.dao.dbdao.CouponsDBDAO;
import com.jbc.util.daoUtils.ConnectionPool;
import com.jbc.util.facadeUtils.StringClass;
import com.jbc.util.runnerUtils.TerminationWaitTime;
import com.jbc.util.runnerUtils.TimeZoneUtils;

/**
 * {@code class} that manages the daily job of the system, deleting expired
 * coupons based on the <code>endDate</code> at 12AM from the SQL DB Server.
 * <p>
 * Timezone is based of the <code>TimeZoneUtils</code> {@code enum}.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see util#TimeZoneUtils
 * @see beans#Coupon
 */
public final class CouponExpirationDailyJob implements Runnable {

	/* attributes */
	private static CouponExpirationDailyJob instance;
	private CouponsDBDAO couponsDBDAO;
	private ConnectionPool connectionPool;
	private Connection con;
	private boolean running;
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	/* constructor, singleton */
	private CouponExpirationDailyJob() {
		connectionPool = ConnectionPool.getInstance();
		start();
	}

	/**
	 * Creates a <code>CouponExpirationDailyJob</code> instance, and limiting the
	 * instances of the <code>CouponExpirationDailyJob</code> to 1, returning the
	 * same instance, if this method is called upon multiple times , allowing
	 * multiple objects use the same instance.
	 * <p>
	 * If this method creates the <code>instance</code> and not just returns it, the
	 * <code>start</code> method will be called upon automatically.
	 * 
	 * @return <code>CouponExpirationDailyJob</code> instance, if this method is
	 *         called upon multiple times, returns the same instance that was first
	 *         created.
	 * @see #CouponExpirationDailyJob()
	 * @see #start()
	 * @see #instance
	 */
	public synchronized static CouponExpirationDailyJob getInstance() {
		if (instance == null)
			instance = new CouponExpirationDailyJob();
		return instance;
	}

	/**
	 * Starts the <code>CouponExpirationDailyJob</code>, if its not already
	 * <code>running</code>, will start the operation at 12AM based on the
	 * <code>TimeZoneUtils</code> area and will repeat the operation every 24 hours.
	 * 
	 * @return {@code true} if the <code>CouponExpirationDailyJob</code> isn't
	 *         already running and starts successfully. {@code false} if the
	 *         <code>CouponExpirationDailyJob</code> is already
	 *         <code>running</code>.
	 * @see #run()
	 * @see #con
	 * @see #running
	 * @see #scheduler
	 */
	public synchronized boolean start() {
		if (!running) {
			running = true;
			con = connectionPool.getConnection();
			couponsDBDAO = new CouponsDBDAO(con);
			ZonedDateTime now = ZonedDateTime.now(ZoneId.of(TimeZoneUtils.ISRAEL.toString()));
			ZonedDateTime nextRun = now.withHour(0).withMinute(0).withSecond(0);
			/* if "nextRun" is before "now" add 1 day to "nextRun" */
			if (now.compareTo(nextRun) > 0)
				nextRun = nextRun.plusDays(1);
			Duration duration = Duration.between(now, nextRun);
			scheduler.scheduleAtFixedRate(this, duration.getSeconds(), TimeUnit.DAYS.toSeconds(1), TimeUnit.SECONDS);
			return running;
		}
		return !running;
	}

	/**
	 * Shuts down the <code>scheduler</code>, and closing the connections, waits up
	 * to the <code>TerminationWaitTime</code> <code>MINUTES</code> value to
	 * terminate the <code>scheduler</code>, if the <code>TerminationWaitTime</code>
	 * number of <code>MINUTES</code> passes and the <code>scheduler</code> is still
	 * running, will return false, if the termination is successful, will return
	 * true.
	 * 
	 * @return {@code true} if the <code>CouponExpirationDailyJob</code> was shut
	 *         down successfully. {@code false} if the
	 *         <code>CouponExpirationDailyJob</code> shut down was unsuccessful and
	 *         keeps running.
	 * @see util#TerminationWaitTime
	 * @see #scheduler
	 * @see #connectionPool
	 * @see #running
	 */
	public synchronized boolean stop() {
		try {
			scheduler.shutdown();
			if (!scheduler.awaitTermination(TerminationWaitTime.TIME.toInt(), TimeUnit.MINUTES)) {
				return false;
			}
			connectionPool.releaseConnection(con);
			running = false;
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Deletes every <code>Coupon</code> from the SQL DB Server that is expired.
	 * 
	 * @see util#SyncUtils
	 * @see #couponsDBDAO
	 */
	@Override
	public void run() {
		List<Coupon> coupons = couponsDBDAO.getAllCoupons();
		for (Coupon coupon : coupons) {
			synchronized (StringClass.COUPON_ID_SYNC + coupon.getId()) {
				Coupon tempCoupon = couponsDBDAO.getOneCoupon(coupon.getId());
				if (tempCoupon != null && tempCoupon.getEndDate().getTime() < System.currentTimeMillis()) {
					couponsDBDAO.deleteCoupon(coupon.getId());
				}
			}
		}
	}
	
}
