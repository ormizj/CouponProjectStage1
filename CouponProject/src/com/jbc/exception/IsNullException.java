package com.jbc.exception;

/**
 * Exception {@code abstract} {@code class} used to {@code throw} exceptions related to
 * a {@code null} bean object, with the <code>toString</code> method.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#CustomException
 */
public abstract class IsNullException extends CustomException {

	/* attributes */
	private static final long serialVersionUID = 6918078131467279137L;
	protected String className;

	/* toString */
	@Override
	public String toString() {
		return "The " + className + " value is \"null\", you need to initalize the " + className + ".";
	}
}
