package com.jbc.util.runnerUtils;

/**
 * {@code enum} which contains the current timezone information used the
 * <code>CouponExpirationDailyJob</code> {@code class}
 * <p>
 * The method <code>toString</code> should be used after specifying which
 * information is wanted, in order to get the corresponding information.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see runner#CouponExpirationDailyJob
 * @see #toString()
 */
public enum TimeZoneUtils {

	/* variables to choose a time zone in the CouponExpirationDailyJob */

	/* attributes */
	ISRAEL;

	/* toString */
	@Override
	public String toString() {
		switch (this) {
		case ISRAEL:
			return "Israel";
		default:
			return super.toString();
		}
	}

}
