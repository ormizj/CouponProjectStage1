package com.jbc.facade;

import java.sql.Connection;
import com.jbc.beans.Company;
import com.jbc.beans.Coupon;
import com.jbc.beans.Customer;
import com.jbc.dao.CompaniesDAO;
import com.jbc.dao.CouponsDAO;
import com.jbc.dao.CustomersDAO;
import com.jbc.exception.NegativePriceException;
import com.jbc.exception.company.CompanyIsNullException;
import com.jbc.exception.company.CompanyNullValueException;
import com.jbc.exception.coupon.CouponIsNullException;
import com.jbc.exception.coupon.CouponNoStockException;
import com.jbc.exception.coupon.CouponNullValueException;
import com.jbc.exception.customer.CustomerIsNullException;
import com.jbc.exception.customer.CustomerNullValueException;
import com.jbc.util.daoUtils.ConnectionPool;
import com.jbc.util.exceptionUtils.ExceptionUtils;

/**
 * {@code abstract} {@code class} that gets extended by the
 * <code>AdminFacade</code>, <code>CompanyFacade</code> and
 * <code>CustomerFacade</code> {@code class}, this {@code class} ensures the
 * business logic of the application before executing it in the DAO classes.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see facade#AdminFacade
 * @see facade#CompanyFacade
 * @see facade#CustomerFacade
 * @see dao#CouponsDAO
 * @see dao#CompaniesDAO
 * @see dao#CustomersDAO
 */
public abstract class ClientFacade {

	/* attributes */
	protected CouponsDAO couponsDAO;
	protected CompaniesDAO companiesDAO;
	protected CustomersDAO customersDAO;
	protected Connection con;
	protected static ConnectionPool conPool;

	/* static block to initialize the conPool */
	static {
		conPool = ConnectionPool.getInstance();
	}

	/* method */
	public abstract boolean login(String email, String password);

	/**
	 * Helper method, checks if an entered <code>Coupon</code> has any
	 * irregularities, if it does, it throws an exception.
	 * 
	 * @param coupon
	 * @throws CouponNullValueException if the <code>Coupon</code> has any
	 *                                  {@code null} values in it.
	 * @throws NegativePriceException   if the <code>Coupon</code> price is below 0.
	 * @throws CouponNoStockException   if the <code>Coupon</code> amount is below
	 *                                  1.
	 * @throws CouponIsNullException    if the <code>Coupon</code> value is
	 *                                  {@code null}
	 * @see util#ExceptionUtils
	 */
	protected void checkCoupon(Coupon coupon)
			throws CouponNullValueException, NegativePriceException, CouponNoStockException, CouponIsNullException {
		if (coupon == null) {
			throw new CouponIsNullException();
		}
		if (coupon.getCategory() == null || coupon.getTitle() == null || coupon.getDescription() == null
				|| coupon.getStartDate() == null || coupon.getEndDate() == null || coupon.getImage() == null) {
			CouponNullValueException exception = new CouponNullValueException();
			if (coupon.getCategory() == null) {
				exception.addNull(ExceptionUtils.CATEGORY);
			}
			if (coupon.getTitle() == null) {
				exception.addNull(ExceptionUtils.TITLE);
			}
			if (coupon.getDescription() == null) {
				exception.addNull(ExceptionUtils.DESCRIPTION);
			}
			if (coupon.getStartDate() == null) {
				exception.addNull(ExceptionUtils.START_DATE);
			}
			if (coupon.getEndDate() == null) {
				exception.addNull(ExceptionUtils.END_DATE);
			}
			if (coupon.getImage() == null) {
				exception.addNull(ExceptionUtils.IMAGE);
			}
			throw exception;
		}
		if (coupon.getAmount() <= 0) {
			throw new CouponNoStockException(coupon.getId());
		}
		if (coupon.getPrice() < 0) {
			throw new NegativePriceException(coupon.getPrice());
		}
	}

	/**
	 * Helper method, checks if an entered <code>Customer</code> has any
	 * irregularities, if it does, it throws an exception.
	 * 
	 * @param customer
	 * @throws CustomerNullValueException if the <code>Customer</code> has any
	 *                                    {@code null} values in it.
	 * @throws CustomerIsNullException    if the <code>Customer</code> value is
	 *                                    {@code null}
	 * @see util#ExceptionUtils
	 */
	protected void checkCustomer(Customer customer) throws CustomerNullValueException, CustomerIsNullException {
		if (customer == null) {
			throw new CustomerIsNullException();
		}
		if (customer.getFirstName() == null || customer.getEmail() == null || customer.getPassword() == null) {
			CustomerNullValueException exception = new CustomerNullValueException();
			if (customer.getFirstName() == null) {
				exception.addNull(ExceptionUtils.FIRST_NAME);
			}
			if (customer.getLastName() == null) {
				exception.addNull(ExceptionUtils.LAST_NAME);
			}
			if (customer.getEmail() == null) {
				exception.addNull(ExceptionUtils.EMAIL);
			}
			if (customer.getPassword() == null) {
				exception.addNull(ExceptionUtils.PASSWORD);
			}
			throw exception;
		}
	}

	/**
	 * Helper method, checks if an entered <code>Company</code> has any
	 * irregularities, if it does, it throws an exception.
	 * 
	 * @param customer
	 * @throws CompanyNullValueException if the <code>Company</code> has any
	 *                                   {@code null} values in it.
	 * @throws CompanyIsNullException    if the <code>Company</code> value is
	 *                                   {@code null}
	 * @see util#ExceptionUtils
	 */
	protected void checkCompany(Company company) throws CompanyNullValueException, CompanyIsNullException {
		if (company == null) {
			throw new CompanyIsNullException();
		}
		if (company.getName() == null || company.getEmail() == null || company.getPassword() == null) {
			CompanyNullValueException exception = new CompanyNullValueException();
			if (company.getName() == null) {
				exception.addNull(ExceptionUtils.NAME);
			}
			if (company.getEmail() == null) {
				exception.addNull(ExceptionUtils.EMAIL);
			}
			if (company.getPassword() == null) {
				exception.addNull(ExceptionUtils.PASSWORD);
			}
			throw exception;
		}
	}

}
