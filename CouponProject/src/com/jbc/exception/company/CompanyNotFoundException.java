package com.jbc.exception.company;

import com.jbc.exception.NotFoundException;
import com.jbc.util.exceptionUtils.ExceptionUtils;

/**
 * Exception {@code class} used to {@code throw} exceptions related to
 * <code>Company</code> <code>id</code> not found with the <code>toString</code>
 * method.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#CustomException
 * @see beans#Company
 */
public final class CompanyNotFoundException extends NotFoundException {

	/* attributes */
	private static final long serialVersionUID = 4924233908046554183L;

	/* constructor */
	public CompanyNotFoundException(int id) {
		this.id = id;
		className = ExceptionUtils.CLASS_COMPANY.toString();
	}

}
