package scrap;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SynchronizedLoop {

	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		List<Integer> list = getList();
		new Thread(RUN).start();
		
		System.out.println(list);
		sync(list, list.size());

		scan.close();
	}

	public static void sync(List<Integer> list, int size) {
		synchronized (list.get(size - 1)) {
			System.out.println(list.get(size - 1));
			if (--size != 0) {
				sync(list, size);
				return;
			}
			System.out.println("Enter anything to open the synchronized block for the thread");
			scan.next();
			return;
		}
	}

	private static final Runnable RUN = new Runnable() {
		@Override
		public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			List<Integer> list = getList();
			syncThread(list, list.size());
		}
	};

	public static void syncThread(List<Integer> list, int size) {
		synchronized (list.get(size - 1)) {
			System.out.println(list.get(size - 1));
			if (--size != 0) {
				syncThread(list, size);
				return;
			}
			System.out.println("Thread ended");
			return;
		}
	}
	
	public static List<Integer> getList() {
		List<Integer> list = new ArrayList<>();
		int i = 0;
		while (i++ < 10) {
			list.add(i);
		}
		return list;
	}

}
