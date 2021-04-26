package com.jbc.exception;

import com.jbc.util.exceptionUtils.ExceptionUtils;

/**
 * Exception {@code abstract} {@code class} used to {@code throw} exceptions related to
 * duplication of a value with the <code>toString</code> method.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#CustomException
 */
public abstract class DuplicateValueException extends CustomException {

	/* attributes */
	private static final long serialVersionUID = 2023156129898996103L;
	protected String stringOfDuplicates;
	protected ExceptionUtils nameOfDuplicate;
	protected String className;

	/* toString */
	@Override
	public String toString() {
		return "You cannot have any duplicate values for the: " + stringOfDuplicates + " in the " + className + " list.";
	}

}
