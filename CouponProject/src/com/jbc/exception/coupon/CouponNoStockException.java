package com.jbc.exception.coupon;

/**
 * Exception {@code class} used to {@code throw} exceptions related to
 * <code>Coupon</code> which is out of stock, with the <code>toString</code>
 * method.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#CouponException
 * @see beans#Coupon
 */
public final class CouponNoStockException extends CouponException {

	/* attributes */
	private static final long serialVersionUID = -8169406035430103383L;

	/* constructor */
	public CouponNoStockException(int id) {
		this.id = id;
	}

	/* toString */
	@Override
	public String toString() {
		return super.toString() + " is out of stock, you can't purchase coupons that are out of stock.";
	}

}
