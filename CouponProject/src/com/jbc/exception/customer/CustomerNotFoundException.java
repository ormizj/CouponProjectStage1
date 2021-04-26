package com.jbc.exception.customer;

import com.jbc.exception.NotFoundException;
import com.jbc.util.exceptionUtils.ExceptionUtils;

/**
 * Exception {@code class} used to {@code throw} exceptions related to
 * <code>Customer</code> <code>id</code> not found with the
 * <code>toString</code> method.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#CustomException
 * @see beans#Customer
 */
public final class CustomerNotFoundException extends NotFoundException {

	/* attributes */
	private static final long serialVersionUID = -5686637033251638610L;

	/* constructor */
	public CustomerNotFoundException(int id) {
		this.id = id;
		className = ExceptionUtils.CLASS_CUSTOMER.toString();
	}

}
