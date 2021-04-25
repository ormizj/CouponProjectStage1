package com.jbc.exception;

import com.jbc.util.exceptionUtils.ExceptionUtils;

/**
 * Exception {@code abstract} {@code class} used to {@code throw} exceptions
 * related to beans which contains {@code null} values, with the <code>toString</code>
 * method.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#CustomException
 */
public abstract class NullValueException extends CustomException {

	/* attributes */
	private static final long serialVersionUID = -4344357858102499390L;
	protected byte numOfNull;
	protected ExceptionUtils nameOfNull;
	protected String className;
	protected String stringOfNulls;

	/**
	 * Creates a <code>String</code> made from other strings which are added from
	 * using this method again.
	 * 
	 * @param nameOfNull will add the {@code enum} <code>String</code> to the
	 *                   ones called by this method previously.
	 * @see util#ExceptionUtils
	 */
	public void addNull(ExceptionUtils nameOfNull) {
		numOfNull++;
		if (stringOfNulls == null) {
			stringOfNulls = nameOfNull.toString().toLowerCase();
		} else {
			stringOfNulls = stringOfNulls + ", " + nameOfNull.toString().toLowerCase();
		}
	}

	/* toString */
	@Override
	public String toString() {
		return "You cannot have any \"null\" values in the " + className + ", you didn't enter a value for: "
				+ numOfNull + " values which are: " + stringOfNulls + ".";
	}

}
