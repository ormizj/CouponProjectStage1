package com.jbc.exception.coupon;

import java.util.Date;

import com.jbc.util.exceptionUtils.ExceptionUtils;

/**
 * Exception {@code class} used to {@code throw} exceptions related to
 * <code>Coupon</code> expiration <code>date</code> when purchasing a
 * <code>Coupon</code>, with the <code>toString</code> method.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see exception#CouponException
 * @see beans@Coupon
 */
public final class CouponExpiredPurchaseException extends CouponExpiredException {

	/* attributes */
	private static final long serialVersionUID = 218981284770480943L;

	/* constructor */
	public CouponExpiredPurchaseException(int id, Date date) {
		this.id = id;
		this.date = date;
		this.action = ExceptionUtils.PURCHASE.name().toLowerCase();
	}

}
