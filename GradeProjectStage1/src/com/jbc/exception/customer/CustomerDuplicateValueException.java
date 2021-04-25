package com.jbc.exception.customer;

import com.jbc.exception.DuplicateValueException;
import com.jbc.util.exceptionUtils.ExceptionUtils;

/**
 * Exception {@code abstract} {@code class} used to {@code throw} exceptions related to
 * duplication of a value in a <code>Customer</code> with the
 * <code>toString</code> method.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#CustomValueException
 * @see beans#Coupon
 */
public final class CustomerDuplicateValueException extends DuplicateValueException {

	/* attributes */
	private static final long serialVersionUID = -3439177959321226748L;

	/* constructor */
	public CustomerDuplicateValueException() {
		className = ExceptionUtils.CLASS_CUSTOMER.toString();
		stringOfDuplicates = ExceptionUtils.EMAIL.name().toLowerCase();
	}
}
