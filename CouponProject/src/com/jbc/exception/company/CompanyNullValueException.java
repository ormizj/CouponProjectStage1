package com.jbc.exception.company;

import com.jbc.exception.NullValueException;
import com.jbc.util.exceptionUtils.ExceptionUtils;

/**
 * Exception {@code class} used to {@code throw} exceptions related to
 * <code>Company</code> which contains {@code null} values, with the
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
 * @see beans#Company
 * @see #addNull(String)
 */
public final class CompanyNullValueException extends NullValueException {

	/* attributes */
	private static final long serialVersionUID = -6461212727416722187L;

	/* constructor */
	public CompanyNullValueException() {
		className = ExceptionUtils.CLASS_COMPANY.toString();
	}

}
