package com.jbc.exception.coupon;

import com.jbc.exception.IsNullException;
import com.jbc.util.exceptionUtils.ExceptionUtils;

/**
 * Exception {@code class} used to {@code throw} exceptions related to a
 * {@code null} <code>Coupon</code> object, with the <code>toString</code>
 * method.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#IsNullException
 * @see beans#Coupon
 */
public final class CouponIsNullException extends IsNullException {

	/* attributes */
	private static final long serialVersionUID = 2343329825146715443L;

	/* Constructor */
	public CouponIsNullException() {
		className = ExceptionUtils.CLASS_COUPON.toString();
	}

}
