package com.jbc.exception;

/**
 * Exception {@code abstract} {@code class} used to {@code throw} exceptions related to
 * <code>id</code> not found with the <code>toString</code> method.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#CustomException
 */
public abstract class NotFoundException extends CustomException {

	/* attributes */
	private static final long serialVersionUID = -4992870382681308172L;
	protected int id;
	protected String className;

	/* toString */
	@Override
	public String toString() {
		return className + " id: " + id + " was not found, make sure that the " + className + " id is correct.";
	}

}
