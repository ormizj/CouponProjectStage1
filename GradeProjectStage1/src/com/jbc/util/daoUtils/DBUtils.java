package com.jbc.util.daoUtils;

/**
 * {@code enum} which contains the information in order to connect to the SQL DB
 * Server.
 * <p>
 * The method <code>toString</code> should be used after specifying which
 * information is wanted, in order to get the corresponding information.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see beans#ConnectionPool
 * @see #toInt()
 */
public enum DBUtils {

	/* variables to connect to the SQL DB Server. */

	/* attributes */
	DB_DRIVER, DB_URL, DB_USER, DB_PASS;

	/* toString */
	@Override
	public String toString() {
		switch (this) {
		case DB_DRIVER:
			return "com.mysql.cj.jdbc.Driver";
		case DB_URL:
			return "jdbc:mysql://127.0.0.1:3306/coupon?useSSL=false&serverTimezone=UTC";
		case DB_USER:
			return "root";
		case DB_PASS:
			return "1234";
		default:
			return super.toString();
		}
	}

}
