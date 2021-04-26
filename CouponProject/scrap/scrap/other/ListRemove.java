package scrap.other;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListRemove {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();

		int i = 0;

		while (i++ < 10) {
			list.add(i);
		}

		for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext();) {
			Integer integer = (Integer) iterator.next();
			if (integer % 2 == 0)
				iterator.remove();
		}

		for (Integer integer : list) {
			System.out.println(integer);
		}
	}

}
