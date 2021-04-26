package com.jbc.exception.customer;

import com.jbc.exception.IsNullException;
import com.jbc.util.exceptionUtils.ExceptionUtils;

/**
 * Exception {@code class} used to {@code throw} exceptions related to a
 * {@code null} <code>Customer</code> object, with the <code>toString</code>
 * method.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#IsNullException
 * @see beans#Customer
 */
public final class CustomerIsNullException extends IsNullException {

	/* attributes */
	private static final long serialVersionUID = 6062395087108093928L;

	/* constructor */
	public CustomerIsNullException() {
		className = ExceptionUtils.CLASS_CUSTOMER.toString();
	}

}
