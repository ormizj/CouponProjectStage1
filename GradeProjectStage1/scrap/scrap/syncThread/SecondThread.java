package scrap.syncThread;

import com.jbc.util.facadeUtils.StringClass;

public class SecondThread implements Runnable {

	@Override
	public void run() {
		synchronized (StringClass.COUPON_ID_SYNC + "1") {
			System.out.println("This is the " + StringClass.COUPON_ID_SYNC + "1 synchronized block");
		}
		synchronized (StringClass.COUPON_ID_SYNC + "2") {
			System.out.println("This is the " + StringClass.COUPON_ID_SYNC + "2 synchronized block");
		}
	}

}
