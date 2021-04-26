package com.jbc.util.runnerUtils;

/**
 * {@code enum} which contains the number of minutes the
 * <code>CouponExpirationDailyJob</code> thread will attempt to shut down.
 * <p>
 * The method <code>toInt</code> should be used after specifying which
 * information is wanted, in order to get the corresponding information.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see beans#ConnectionPool
 * @see #toInt()
 */
public enum TerminationWaitTime {

	/*
	 * the CouponExpirationDailyJob minutes to wait before stopping to attempt
	 * termination to the thread
	 */

	/* attributes */
	TIME(15);

	private final int MINUTES;

	/* constructor */
	TerminationWaitTime(int minutes) {
		MINUTES = minutes;
	}

	/* method */
	public int toInt() {
		return MINUTES;
	}

}
