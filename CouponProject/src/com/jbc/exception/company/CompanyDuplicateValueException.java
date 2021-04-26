package com.jbc.exception.company;

import com.jbc.exception.DuplicateValueException;
import com.jbc.util.exceptionUtils.ExceptionUtils;

/**
 * Exception {@code class} used to {@code throw} exceptions related to
 * duplication of a value in a <code>Company</code> with the
 * <code>toString</code> method.
 * <p>
 * This exception should be instantiated and the method
 * <code>addnameOfDuplicate</code> should be called upon for every value which
 * is a duplication, with the help of the <code>ExceptionUtils</code> to specify
 * the values.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#DuplicateValueException
 * @see beans#Company
 * @see #addNameOfDuplicate(String)
 */
public final class CompanyDuplicateValueException extends DuplicateValueException {

	/* attributes */
	private static final long serialVersionUID = -8022985052160567479L;

	/* constructor */
	public CompanyDuplicateValueException() {
		className = ExceptionUtils.CLASS_COMPANY.toString();
	}

	/**
	 * Creates a <code>String</code> made from other strings which are added from
	 * using this method again.
	 * 
	 * @param nameOfDuplicate will add the <code>enum</code> <code>getString</code>
	 *                        to the previous ones called by this method previously.
	 * @see util#ExceptionUtils
	 */
	public void addNameOfDuplicate(ExceptionUtils nameOfDuplicate) {
		if (this.stringOfDuplicates == null) {
			stringOfDuplicates = nameOfDuplicate.name().toLowerCase();
		} else {
			stringOfDuplicates = stringOfDuplicates + ", " + nameOfDuplicate.name().toLowerCase();
		}
	}
}
