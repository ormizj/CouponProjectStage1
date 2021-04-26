package scrap.oldCode.dao;

import java.sql.SQLException;
import java.util.List;

import com.jbc.beans.Coupon;

public interface ConnectorDAO {

	/**
	 * Used to get all the <code>Company</code> coupons from a specified id, will
	 * return the <code>Coupon</code> <code>List</code> based on the existing
	 * <code>Company</code> coupons, if it has any, and if it exists.
	 * 
	 * @param companyId
	 * @return All the <code>Company</code> coupons as a <code>List</code>.
	 * @throws SQLException
	 * @see #createCoupons()
	 * @see #openState()
	 * @see #closeState()
	 * @see #closeResult()
	 * @see #result
	 * @see #state
	 */
	public List<Coupon> getCompanyCoupons(int companyId) throws SQLException;

	/**
	 * Used to get all the <code>Customer</code> coupons from a specified id, will
	 * return the <code>Coupon</code> <code>List</code> based on the existing
	 * <code>Customer</code> coupons, if it has any, and if it exists.
	 * 
	 * @param customerId
	 * @return All the <code>Customer</code> coupons as a <code>List</code>.
	 * @throws SQLException
	 * @see #createCoupons()
	 * @see #openState()
	 * @see #closeState()
	 * @see #closeResult()
	 * @see #result
	 * @see #state
	 */
	public List<Coupon> getCustomerCoupons(int customerId) throws SQLException;

}
