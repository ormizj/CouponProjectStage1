package com.jbc.exception.coupon;

import java.util.Date;

/**
 * Exception {@code abstract} {@code class} which is the superclass of all the
 * custom exceptions that are customly created, and related to the
 * <code>Coupon</code> bean {@code class} for the expiration category, by using
 * the <code>toString</code> method.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#CouponException
 * @see beans@Coupon
 */
public abstract class CouponExpiredException extends CouponException {

	/* attributes */
	private static final long serialVersionUID = 659136974186842471L;
	protected Date date;
	protected String action;

	/* toString */
	@Override
	public String toString() {
		return super.toString() + " has expired at: " + date + ", you can't " + action + " expired coupons.";
	}

}
