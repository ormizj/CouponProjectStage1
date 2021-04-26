package com.jbc.dao;

import java.util.List;
import com.jbc.beans.Coupon;

/**
 * DAO {@code interface} that gets implemented by the <code>CouponsDBDAO</code>
 * {@code class}, this {@code interface} queries the SQL DB Server, which are
 * related to the coupons.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see dao#dbdao#CouponsDBDAO
 */
public interface CouponsDAO {

	/**
	 * Adds a <code>Coupon</code> to the <code>Company</code> in the SQL DB Server.
	 * 
	 * @param coupon
	 * @see util#tableUtils#CategoriesTableUtils
	 * @see #openState()
	 * @see #closeState()
	 * @see #closeResult()
	 * @see #result
	 * @see #state
	 */
	public void addCoupon(Coupon coupon);

	/**
	 * Updates a <code>Coupon</code> of a <code>Company</code> in the SQL DB Server.
	 * 
	 * @param coupon
	 * @see util#tableUtils#CategoriesTableUtils
	 * @see #openState()
	 * @see #closeState()
	 * @see #closeResult()
	 * @see #result
	 * @see #state
	 */
	public void updateCoupon(Coupon coupon);

	/**
	 * Deletes a <code>Coupon</code> of a <code>Company</code> in the SQL DB Server.
	 * 
	 * @param couponId
	 * @see #openState()
	 * @see #closeState()
	 * @see #state
	 */
	public void deleteCoupon(int couponId);

	/**
	 * 
	 * @return The <code>Coupon</code> <code>List</code> of all companies that are
	 *         currently in the SQL DB Server.
	 * @see #createCoupons
	 * @see #openState()
	 * @see #closeState()
	 * @see #closeResult()
	 * @see #result
	 * @see #state
	 */
	public List<Coupon> getAllCoupons();

	/**
	 * Used to get all the <code>Customer</code> coupons from a specified id, will
	 * return the <code>Coupon</code> <code>List</code> based on the existing
	 * <code>Customer</code> coupons, if it has any, and if it exists.
	 * 
	 * @param customerId
	 * @return All the <code>Customer</code> coupons as a <code>List</code>.
	 * @see #createCoupons()
	 * @see #openState()
	 * @see #closeState()
	 * @see #closeResult()
	 * @see #result
	 * @see #state
	 */
	public List<Coupon> getCustomerCoupons(int CustomerId);

	/**
	 * Used to get all the <code>Company</code> coupons from a specified id, will
	 * return the <code>Coupon</code> <code>List</code> based on the existing
	 * <code>Company</code> coupons, if it has any, and if it exists.
	 * 
	 * @param companyId
	 * @return All the <code>Company</code> coupons as a <code>List</code>.
	 * @see #createCoupons()
	 * @see #openState()
	 * @see #closeState()
	 * @see #closeResult()
	 * @see #result
	 * @see #state
	 */
	public List<Coupon> getCompanyCoupons(int CompanyId);

	/**
	 * 
	 * @return The <code>Coupon</code> of the specified <code>Coupon</code> id that
	 *         is currently in the SQL DB Server, if the <code>Coupon</code> does
	 *         not exists it will return {@code null}.
	 * @param couponId
	 * @see #createCoupon()
	 * @see #openState()
	 * @see #closeState()
	 * @see #closeResult()
	 * @see #result
	 * @see #state
	 */
	public Coupon getOneCoupon(int couponId);

	/**
	 * Adds a <code>Coupon</code> to the <code>Customer</code> in the SQL DB Server.
	 * 
	 * @param customerId, couponId
	 * @see #openState()
	 * @see #closeState()
	 * @see #state
	 */
	public void addCouponPurchase(int customerId, int couponId);

}
