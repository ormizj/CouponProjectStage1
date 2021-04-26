package scrap.syncThread;

import com.jbc.util.facadeUtils.StringClass;

public class FirstThread implements Runnable {

	public static void main(String[] args) throws InterruptedException {
		new Thread(new FirstThread()).start();
		Thread.sleep(100);
		new Thread(new SecondThread()).start();
	}

	@Override
	public void run() {
		synchronized (StringClass.COUPON_ID_SYNC + "2") {
			try {
				System.out.println("First Thread is sleeping");
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("First Thread is awake");
		}
	}

}
