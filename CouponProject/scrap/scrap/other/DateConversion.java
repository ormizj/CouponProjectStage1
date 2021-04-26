package scrap.other;

import java.sql.Date;

public class DateConversion {

	static java.util.Date java = new java.util.Date();
	static Date sql = new Date(System.currentTimeMillis());

	public static void main(String[] args) {
		System.out.println(java);
		System.out.println(sql);
		
		System.out.println("\nSQL date from Java date");
		Date sqlConverted = new Date(java.getTime());
		System.out.println(sqlConverted);
	}

}
