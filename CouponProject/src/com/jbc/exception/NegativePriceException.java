package com.jbc.exception;

import com.jbc.exception.coupon.CouponException;

/**
 * Exception {@code class} used to {@code throw} exceptions related to a price that has
 * a negative price with the <code>toString</code> method.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#CustomException
 */
public final class NegativePriceException extends CouponException {

	/* attributes */
	private static final long serialVersionUID = 7509044611898086927L;
	private double price;

	/* constructor */
	public NegativePriceException(double price) {
		this.price = price;
	}

	/* toString */
	@Override
	public String toString() {
		return "The coupon price cannot be: " + price + ", the minimum value is: 0.";
	}

}
