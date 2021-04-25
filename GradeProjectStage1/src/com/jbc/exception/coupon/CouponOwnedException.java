package com.jbc.exception.coupon;

/**
 * Exception {@code class} used to {@code throw} exceptions related to
 * <code>Coupon</code> which is already owned by a <code>Customer</code>, with
 * the <code>toString</code> method.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#CouponException
 * @see beans#Coupon
 */
public final class CouponOwnedException extends CouponException {

	/* attributes */
	private static final long serialVersionUID = 9010697755872441982L;

	/* constructor */
	public CouponOwnedException(int id) {
		this.id = id;
	}

	/* toString */
	@Override
	public String toString() {
		return super.toString() + " is already owned, you can't purchase already owned coupons.";
	}

}
