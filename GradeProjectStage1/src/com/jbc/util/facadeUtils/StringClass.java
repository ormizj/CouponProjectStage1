package com.jbc.util.facadeUtils;

/**
 * {@code class} which is used as an {@code enum} in order to synchronize the
 * application.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 */
public class StringClass {

	/**
	 * <code>String</code> that is used in a <code>synchronized</code> block, with
	 * an id of a <code>Coupon</code> to synchronize coupons based on their id.
	 * <p>
	 * Example: synchronized (StringClass.COUPON_ID_SYNC + couponId) {
	 */
	public static final String COUPON_ID_SYNC = "COUPON_ID_SYNC";

}
