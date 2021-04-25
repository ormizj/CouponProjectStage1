package com.jbc.exception.coupon;

import com.jbc.exception.NullValueException;
import com.jbc.util.exceptionUtils.ExceptionUtils;

/**
 * Exception {@code class} used to {@code throw} exceptions related to
 * <code>Coupon</code> which contains null values, with the
 * <code>toString</code> method.
 * <p>
 * This exception should be instantiated and the method <code>addNull</code>
 * should be called upon for every value which is {@code null}, with the help of
 * the <code>ExceptionUtils</code> to specify the values.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#NullValueException
 * @see util#ExceptionUtils
 * @see beans#Coupon
 * @see #addNull(String)
 */
public final class CouponNullValueException extends NullValueException {

	/* attributes */
	private static final long serialVersionUID = 3947556183985859582L;

	/* constructor */
	public CouponNullValueException() {
		className = ExceptionUtils.CLASS_COUPON.toString();
	}

}
