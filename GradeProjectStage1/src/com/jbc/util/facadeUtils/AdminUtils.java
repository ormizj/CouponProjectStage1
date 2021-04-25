package com.jbc.util.facadeUtils;

/**
 * {@code enum} which contains the username and password of the admin.
 * <p>
 * The method <code>toString</code> should be used after specifying which
 * information is wanted, in order to get the corresponding information.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see facade#AdminFacade
 * @see #toString()
 */
public enum AdminUtils {

	/* the information to login as an admin. */

	/* attributes */
	ADMIN_USER, ADMIN_PASS;

	/* toString */
	@Override
	public String toString() {
		switch (this) {
		case ADMIN_USER:
			return "admin@admin.com";
		case ADMIN_PASS:
			return "admin";
		default:
			return super.toString();
		}
	}

}
