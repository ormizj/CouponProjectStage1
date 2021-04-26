package com.jbc.facade;

import java.util.Iterator;
import java.util.List;
import com.jbc.beans.Company;
import com.jbc.beans.Coupon;
import com.jbc.dao.dbdao.CompaniesDBDAO;
import com.jbc.dao.dbdao.CouponsDBDAO;
import com.jbc.exception.NegativePriceException;
import com.jbc.exception.coupon.CouponExpiredCreateException;
import com.jbc.exception.coupon.CouponIsNullException;
import com.jbc.exception.coupon.CouponNoStockException;
import com.jbc.exception.coupon.CouponNotFoundException;
import com.jbc.exception.coupon.CouponNullValueException;
import com.jbc.util.beanUtils.CategoryUtils;
import com.jbc.util.facadeUtils.StringClass;
import com.jbc.exception.coupon.CouponDuplicateValueException;

/**
 * {@code class} that {@code extends} the <code>ClientFacade</code> and ensures
 * the business logic of the application related to the <code>Company</code>
 * before executing it in the DAO classes.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see facade#ClientFacade
 * @see dao#CompaniesDAO
 * @see dao#CouponsDAO
 */
public final class CompanyFacade extends ClientFacade {

	/* attributes */
	private Company thisCompany;

	/**
	 * Constructor that initializes the connections with the
	 * <code>CompaniesDBDAO</code> and the <code>CouponsDBDAO</code>.
	 * 
	 * @see #con
	 * @see #companiesDAO
	 * @see #couponsDAO
	 */
	public CompanyFacade() {
		con = conPool.getConnection();
		companiesDAO = new CompaniesDBDAO(con);
		couponsDAO = new CouponsDBDAO(con);
	}

	/**
	 * Checks if there is an existing <code>Company</code> with the specified email
	 * and password in the SQL DB Server, closing the <code>Connection</code> if
	 * does not exist.
	 * <p>
	 * If it does exist, creates an instance of <code>Company</code> with the
	 * corresponding email and password.
	 * 
	 * @param email
	 * @param password
	 * @return {@code true} if the email and password are correct. {@code false} if
	 *         they are incorrect.
	 * @see #companiesDAO
	 * @see #conPool
	 */
	@Override
	public boolean login(String email, String password) {
		if (companiesDAO.isCompanyExist(email, password)) {
			thisCompany = companiesDAO.getThisCompany(email);
			return true;
		}
		conPool.releaseConnection(con);
		return false;
	}

	/**
	 * checks if the <code>Coupon</code> has any irregularities, if it does, it
	 * throws an exception, if not, adds the <code>Coupon</code> to the SQL DB
	 * Server with the <code>Company</code> id of the <code>Company</code> who
	 * called the method.
	 * 
	 * @param coupon
	 * @throws CouponNullValueException      if the <code>Coupon</code> has any
	 *                                       {@code null} values in it.
	 * @throws NegativePriceException        if the <code>Coupon</code> price is
	 *                                       below 0.
	 * @throws CouponDuplicateValueException if <code>thisCompany</code> already has
	 *                                       a <code>Coupon</code> with the same
	 *                                       title
	 * @throws CouponNoStockException        if the <code>Coupon</code> amount is
	 *                                       below 1.
	 * @throws CouponExpiredCreateException  if the <code>Coupon</code> is already
	 *                                       expired.
	 * @throws CouponIsNullException         if the <code>Coupon</code> value is
	 *                                       {@code null}
	 * @see #checkCoupon(Coupon)
	 * @see #checkTitle(List, Coupon)
	 * @see #thisCompany
	 * @see #couponsDAO
	 */
	public void addCoupon(Coupon coupon) throws CouponNullValueException, NegativePriceException,
			CouponDuplicateValueException, CouponNoStockException, CouponExpiredCreateException, CouponIsNullException {
		checkCoupon(coupon);
		coupon.setCompanyId(thisCompany.getId());
		checkTitle(couponsDAO.getCompanyCoupons(thisCompany.getId()), coupon);
		if (coupon.getEndDate().getTime() < System.currentTimeMillis()) {
			throw new CouponExpiredCreateException(coupon.getId(), coupon.getEndDate());
		}
		couponsDAO.addCoupon(coupon);
	}

	/**
	 * checks if the specified <code>Coupon</code> has any irregularities, if it
	 * does, it throws an exception, if not, updates the <code>Coupon</code> in the
	 * SQL DB Server with the new variables specified in this method.
	 * 
	 * @param coupon
	 * @throws CouponNullValueException      if the <code>Coupon</code> has any
	 *                                       {@code null} values in it.
	 * @throws NegativePriceException        if the <code>Coupon</code> price is
	 *                                       below 0.
	 * @throws CouponNotFoundException       if the <code>Coupon</code> id was not
	 *                                       found.
	 * @throws CouponDuplicateValueException if <code>thisCompany</code> already has
	 *                                       a <code>Coupon</code> with the same
	 *                                       title.
	 * @throws CouponNoStockException        if the <code>Coupon</code> amount is
	 *                                       below 1.
	 * @throws CouponExpiredCreateException
	 * @throws CouponIsNullException         if the <code>Coupon</code> value is
	 *                                       {@code null}
	 * @see util#SyncUtils
	 * @see #checkCoupon(Coupon)
	 * @see #checkTitle(List, Coupon)
	 * @see #couponsDAO
	 */
	public void updateCoupon(Coupon coupon)
			throws CouponNullValueException, NegativePriceException, CouponNotFoundException,
			CouponDuplicateValueException, CouponNoStockException, CouponExpiredCreateException, CouponIsNullException {
		checkCoupon(coupon);
		if (coupon.getEndDate().getTime() < System.currentTimeMillis()) {
			throw new CouponExpiredCreateException(coupon.getId(), coupon.getEndDate());
		}
		synchronized (StringClass.COUPON_ID_SYNC + coupon.getId()) {
			List<Coupon> coupons = couponsDAO.getCompanyCoupons(thisCompany.getId());
			checkTitle(coupons, coupon);
			if (coupons.contains(coupon)) {
				couponsDAO.updateCoupon(coupon);
				return;
			}
		}
		throw new CouponNotFoundException(coupon.getId());
	}

	/**
	 * Helper method, checks if a <code>Coupon</code> with the same title already
	 * exists in the inserted <code>List</code> if it does, throws
	 * <code>CouponDuplicateValueException</code>.
	 * 
	 * @param coupons
	 * @param coupon
	 * @throws CouponDuplicateValueException if <code>thisCompany</code> already has
	 *                                       a <code>Coupon</code> with the same
	 *                                       title.
	 */
	private void checkTitle(List<Coupon> coupons, Coupon coupon) throws CouponDuplicateValueException {
		for (Coupon tempCoupon : coupons) {
			if (!tempCoupon.equals(coupon) && tempCoupon.getTitle().equalsIgnoreCase(coupon.getTitle())) {
				throw new CouponDuplicateValueException();
			}
		}
	}

	/**
	 * Deletes <code>thisCompany</code> <code>Coupon</code> with the specified id,
	 * if the <code>Coupon</code> does not exist, throws
	 * <code>CouponNotFoundException</code>.
	 * 
	 * @param CouponId
	 * @throws CouponNotFoundException if the <code>Coupon</code> id was not found.
	 * @see util#SyncUtils
	 * @see #couponsDAO
	 */
	public void deleteCoupon(int CouponId) throws CouponNotFoundException {
		synchronized (StringClass.COUPON_ID_SYNC + CouponId) {
			for (Coupon coupon : couponsDAO.getCompanyCoupons(thisCompany.getId())) {
				if (coupon.getId() == CouponId) {
					couponsDAO.deleteCoupon(CouponId);
					return;
				}
			}
		}
		throw new CouponNotFoundException(CouponId);
	}

	/**
	 * 
	 * @return <code>Coupon</code> <code>List</code> of <code>thisCompany</code>.
	 * @see #couponsDAO
	 */
	public List<Coupon> getCompanyCoupons() {
		return couponsDAO.getCompanyCoupons(thisCompany.getId());
	}

	/**
	 * 
	 * @param category
	 * @return the <code>Coupon</code> <code>List</code> of
	 *         <code>thisCompany</code>, with the same category that was specified.
	 * @throws CouponNotFoundException if no coupons with the specified category
	 *                                 were found.
	 * @see #couponsDAO
	 * @see #thisCompany
	 */
	public List<Coupon> getCompanyCoupons(CategoryUtils category) throws CouponNotFoundException {
		List<Coupon> coupons = couponsDAO.getCompanyCoupons(thisCompany.getId());
		for (Iterator<Coupon> iterator = coupons.iterator(); iterator.hasNext();) {
			Coupon coupon = (Coupon) iterator.next();
			if (!coupon.getCategory().equals(category))
				iterator.remove();
		}
		if (coupons.size() != 0) {
			return coupons;
		}
		throw new CouponNotFoundException(category);
	}

	/**
	 * 
	 * @param maxPrice
	 * @return the <code>Coupon</code> <code>List</code> of
	 *         <code>thisCompany</code>, with price below of which was specified.
	 * @throws CouponNotFoundException if no coupons below the price were found.
	 * @see #couponsDAO
	 * @see #thisCompany
	 */
	public List<Coupon> getCompanyCoupons(double maxPrice) throws CouponNotFoundException {
		List<Coupon> coupons = couponsDAO.getCompanyCoupons(thisCompany.getId());
		for (Iterator<Coupon> iterator = coupons.iterator(); iterator.hasNext();) {
			Coupon coupon = (Coupon) iterator.next();
			if (coupon.getPrice() > maxPrice)
				iterator.remove();
		}
		if (coupons.size() != 0) {
			return coupons;
		}
		throw new CouponNotFoundException(maxPrice);
	}

	/**
	 * 
	 * @return the information of the <Code>Company</code> who called this method.
	 * @see #thisCompany
	 */
	public Company getCompanyDetails() {
		return thisCompany;
	}

}
