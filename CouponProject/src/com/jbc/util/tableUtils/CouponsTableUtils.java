package com.jbc.util.tableUtils;

/**
 * {@code enum} which contains the name of the columns that are in the coupons
 * table in the SQL DB Server.
 * <p>
 * The <code>name</code> method should be used, to ensure the correct
 * {@code String} will be returned;
 *
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 */
public enum CouponsTableUtils {

	/* attributes */
	ID, COMPANY_ID, CATEGORY_ID, TITLE, DESCRIPTION, START_DATE, END_DATE, AMOUNT, PRICE, IMAGE;

}
