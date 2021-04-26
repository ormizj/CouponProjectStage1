package com.jbc.exception.company;

import com.jbc.exception.IsNullException;
import com.jbc.util.exceptionUtils.ExceptionUtils;

/**
 * Exception {@code class} used to {@code throw} exceptions related to a
 * {@code null} <code>Company</code> object, with the <code>toString</code>
 * method.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#IsNullException
 * @see beans#Company
 */
public final class CompanyIsNullException extends IsNullException {

	/* attributes */
	private static final long serialVersionUID = 4496682272249313607L;

	/* Constructor */
	public CompanyIsNullException() {
		className = ExceptionUtils.CLASS_COMPANY.toString();
	}

}
