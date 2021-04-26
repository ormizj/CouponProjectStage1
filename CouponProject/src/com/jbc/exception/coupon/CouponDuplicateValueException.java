package com.jbc.exception.coupon;

import com.jbc.exception.DuplicateValueException;
import com.jbc.util.exceptionUtils.ExceptionUtils;

/**
 * Exception {@code abstract} {@code class} used to {@code throw} exceptions related to
 * duplication of a value in a <code>Coupon</code> with the
 * <code>toString</code> method.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#CustomValueException
 * @see beans#Coupon
 */
public final class CouponDuplicateValueException extends DuplicateValueException {

	/* attributes */
	private static final long serialVersionUID = -6334847561117102189L;

	/* constructor */
	public CouponDuplicateValueException() {
		className = ExceptionUtils.CLASS_COUPON.toString();
		stringOfDuplicates = ExceptionUtils.TITLE.name().toLowerCase();
	}
}
