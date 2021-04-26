package com.jbc.exception.coupon;

import com.jbc.exception.NotFoundException;
import com.jbc.util.beanUtils.CategoryUtils;
import com.jbc.util.exceptionUtils.ExceptionUtils;

/**
 * Exception {@code class} used to {@code throw} exceptions related to
 * <code>Coupon</code> <code>id</code> not found with the <code>toString</code>
 * method.
 * <p>
 * Also thrown when <code>Coupon</code> in a category was not found or coupon
 * below a specific price was not found.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#CustomException
 * @see beans#Coupon
 * @see util#CategoryUtils
 */
public final class CouponNotFoundException extends NotFoundException {

	/* attributes */
	private static final long serialVersionUID = -2097523446869921554L;
	private CategoryUtils category;
	private double maxPrice;
	private ExceptionUtils toString;

	/* constructors */
	public CouponNotFoundException(int id) {
		this.id = id;
		className = ExceptionUtils.CLASS_COUPON.toString();
		toString = ExceptionUtils.ID;
	}

	public CouponNotFoundException(CategoryUtils category) {
		this.category = category;
		className = ExceptionUtils.CLASS_COUPON.toString();
		toString = ExceptionUtils.CATEGORY;
	}

	public CouponNotFoundException(double maxPrice) {
		this.maxPrice = maxPrice;
		className = ExceptionUtils.CLASS_COUPON.toString();
		toString = ExceptionUtils.MAX_PRICE;
	}

	/* toString */
	@Override
	public String toString() {
		if (toString.equals(ExceptionUtils.CATEGORY)) {
			return className + " category: " + category + " was not found, make sure that the " + className
					+ " category is correct.";
		}
		if (toString.equals(ExceptionUtils.MAX_PRICE)) {
			return className + " max price: " + maxPrice + " was not found, make sure that the " + className
					+ " max price is correct.";
		}
		return super.toString();
	}

}
