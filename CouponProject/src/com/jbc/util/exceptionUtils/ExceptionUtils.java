package com.jbc.util.exceptionUtils;

/**
 * {@code enum} which contains information used by the exceptions that extend
 * the <code>CustomException</code> {@code class}.
 * <p>
 * The method <code>toString</code> should be used after specifying which
 * information is wanted, when using a "CLASS_" type, in order to get the
 * correct information.
 * <p>
 * The method <code>name</code> should be used when specifying a type that does
 * not contain the "CLASS_", to get the type name.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#CustomException
 * @see #toString()
 */
public enum ExceptionUtils {

	/* attributes */
	CLASS_COMPANY, CLASS_COUPON, CLASS_CUSTOMER,

	CATEGORY, TITLE, DESCRIPTION, START_DATE, END_DATE, IMAGE, NAME, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, MAX_PRICE,
	PURCHASE, CREATE, ID;

	/* toString */
	@Override
	public String toString() {
		switch (this) {
		case CLASS_COMPANY:
			return "Company";
		case CLASS_COUPON:
			return "Coupon";
		case CLASS_CUSTOMER:
			return "Customer";
		default:
			return super.toString();
		}
	}

}
