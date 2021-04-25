package com.jbc.facade;

import java.util.Iterator;
import java.util.List;
import com.jbc.beans.Coupon;
import com.jbc.beans.Customer;
import com.jbc.dao.dbdao.CouponsDBDAO;
import com.jbc.dao.dbdao.CustomersDBDAO;
import com.jbc.exception.coupon.CouponExpiredPurchaseException;
import com.jbc.exception.coupon.CouponNoStockException;
import com.jbc.exception.coupon.CouponNotFoundException;
import com.jbc.exception.coupon.CouponOwnedException;
import com.jbc.util.beanUtils.CategoryUtils;
import com.jbc.util.facadeUtils.StringClass;

/**
 * {@code class} that {@code extends} the <code>ClientFacade</code> and ensures
 * the business logic of the application related to the <code>Customer</code>
 * before executing it in the DAO classes.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see facade#ClientFacade
 * @see dao#CustomersDAO
 * @see dao#CouponsDAO
 */
public final class CustomerFacade extends ClientFacade {

	/* attributes */
	private Customer thisCustomer;

	/**
	 * Constructor that initializes the connections with the
	 * <code>CustomersDBDAO</code> and the <code>CouponsDBDAO</code>.
	 * 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 * @see #con
	 * @see #customersDAO
	 * @see #couponsDAO
	 */
	public CustomerFacade() {
		con = conPool.getConnection();
		customersDAO = new CustomersDBDAO(con);
		couponsDAO = new CouponsDBDAO(con);
	}

	/**
	 * Checks if there is an existing <code>Customer</code> with the specified email
	 * and password in the SQL DB Server, closing the <code>Connection</code> if
	 * does not exist.
	 * <p>
	 * If it does exist, creates an instance of <code>Customer</code> with the
	 * corresponding email and password.
	 * 
	 * @param email
	 * @param password
	 * @return {@code true} if the email and password are correct. {@code false} if
	 *         they are incorrect.
	 * @see #customersDAO
	 * @see #conPool
	 */
	@Override
	public boolean login(String email, String password) {
		if (customersDAO.isCustomerExists(email, password)) {
			thisCustomer = customersDAO.getThisCustomer(email);
			return true;
		}
		conPool.releaseConnection(con);
		return false;
	}

	/**
	 * Checks if the <code>Coupon</code> based on the entered couponId has any
	 * irregularities, if it does, it throws an exception, if not, adds the
	 * <code>Coupon</code> to the <code>Customer</code> in the SQL DB Server.
	 * 
	 * @param couponId
	 * @throws CouponExpiredPurchaseException if the <code>Coupon</code> is already
	 *                                        expired.
	 * @throws CouponNoStockException         if the <code>Coupon</code> amount is
	 *                                        below 1.
	 * @throws CouponOwnedException           if the <code>Customer</code> already
	 *                                        owns this <code>Coupon</code>.
	 * @throws CouponNotFoundException        if the <code>Coupon</code> id was not
	 *                                        found.
	 * @see util#SyncUtils
	 * @see #couponsDAO
	 */
	public void purchaseCoupon(int couponId) throws CouponExpiredPurchaseException, CouponNoStockException,
			CouponOwnedException, CouponNotFoundException {
		synchronized (StringClass.COUPON_ID_SYNC + couponId) {
			Coupon coupon = couponsDAO.getOneCoupon(couponId);
			if (coupon == null)
				throw new CouponNotFoundException(couponId);
			if (coupon.getAmount() <= 0)
				throw new CouponNoStockException(couponId);
			if (coupon.getEndDate().getTime() < System.currentTimeMillis())
				throw new CouponExpiredPurchaseException(coupon.getId(), coupon.getEndDate());
			if (couponsDAO.getCustomerCoupons(thisCustomer.getId()).contains(coupon))
				throw new CouponOwnedException(couponId);
			couponsDAO.addCouponPurchase(thisCustomer.getId(), coupon.getId());
		}
	}

	/**
	 * 
	 * @return the <code>Coupon</code> <code>List</code> of
	 *         <code>thisCustomer</code>.
	 * @see #couponsDAO
	 */
	public List<Coupon> CustomerCoupons() {
		return couponsDAO.getCustomerCoupons(thisCustomer.getId());
	}

	/**
	 * 
	 * @param category
	 * @return the <code>Coupon</code> <code>List</code> of
	 *         <code>thisCustomer</code>, with the same category that was specified.
	 * @throws CouponNotFoundException if no coupons with the specified category
	 *                                 were found.
	 * @see #couponsDAO
	 * @see #thisCustomer
	 */
	public List<Coupon> getCustomerCoupons(CategoryUtils category) throws CouponNotFoundException {
		List<Coupon> customerCoupons = couponsDAO.getCustomerCoupons(thisCustomer.getId());
		for (Iterator<Coupon> iterator = customerCoupons.iterator(); iterator.hasNext();) {
			Coupon coupon = (Coupon) iterator.next();
			if (!coupon.getCategory().equals(category))
				iterator.remove();
		}
		if (customerCoupons.size() != 0) {
			return customerCoupons;
		}
		throw new CouponNotFoundException(category);
	}

	/**
	 * 
	 * @param maxPrice
	 * @return the <code>Coupon</code> <code>List</code> of
	 *         <code>thisCustomer</code>, with price below of which was specified.
	 * @throws CouponNotFoundException if no coupons below the price were found.
	 * @see #couponsDAO
	 * @see #thisCustomer
	 */
	public List<Coupon> getCustomerCoupons(double maxPrice) throws CouponNotFoundException {
		List<Coupon> customerCoupons = couponsDAO.getCustomerCoupons(thisCustomer.getId());
		for (Iterator<Coupon> iterator = customerCoupons.iterator(); iterator.hasNext();) {
			Coupon coupon = (Coupon) iterator.next();
			if (coupon.getPrice() > maxPrice)
				iterator.remove();
		}
		if (customerCoupons.size() != 0) {
			return customerCoupons;
		}
		throw new CouponNotFoundException(maxPrice);
	}

	/**
	 * 
	 * @return the information of the <Code>Customer</code> who called this method.
	 * @see #thisCustomer
	 */
	public Customer getCustomerDetails() {
		return thisCustomer;
	}
}
