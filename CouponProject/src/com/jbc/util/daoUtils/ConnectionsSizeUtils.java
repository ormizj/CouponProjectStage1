package com.jbc.util.daoUtils;

/**
 * {@code enum} which contains the maximum number of connections to the SQL DB
 * Server.
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
public enum ConnectionsSizeUtils {

	/* the ConnectionPool Connection set size */

	/* attributes */
	MAX(10);

	private final int MAX_SIZE;

	/* constructor */
	ConnectionsSizeUtils(int max) {
		MAX_SIZE = max;
	}

	/* method */
	public int toInt() {
		return MAX_SIZE;
	}

}
