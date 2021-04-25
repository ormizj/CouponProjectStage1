package com.jbc.exception.coupon;

import com.jbc.exception.CustomException;

/**
 * Exception {@code abstract} {@code class} which is the superclass of all the
 * custom exceptions that are customly created, and related to the
 * <code>Coupon</code> bean {@code class} all exceptions work by using the
 * <code>toString</code> method.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#CustomException
 * @see beans@Coupon
 */
public abstract class CouponException extends CustomException {

	/* attributes */
	private static final long serialVersionUID = -148510015680968765L;
	protected int id;

	/* toString */
	@Override
	public String toString() {
		return "Coupon id: " + id;
	}

}
