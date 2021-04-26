package com.jbc.exception.customer;

import com.jbc.exception.NullValueException;
import com.jbc.util.exceptionUtils.ExceptionUtils;

/**
 * Exception {@code class} used to {@code throw} exceptions related to
 * <code>Customer</code> which contains {@code null} values, with the
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
 * @see beans#Customer
 * @see #addNull(String)
 */
public final class CustomerNullValueException extends NullValueException {

	/* attributes */
	private static final long serialVersionUID = -633491210916568262L;

	/* constructor */
	public CustomerNullValueException() {
		className = ExceptionUtils.CLASS_CUSTOMER.toString();
	}

}
