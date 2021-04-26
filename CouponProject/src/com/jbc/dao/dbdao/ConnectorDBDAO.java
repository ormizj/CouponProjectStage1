package com.jbc.dao.dbdao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.jbc.beans.Coupon;
import com.jbc.util.beanUtils.CategoryUtils;
import com.jbc.util.tableUtils.CategoriesTableUtils;
import com.jbc.util.tableUtils.CouponsTableUtils;

/**
 * DBDAO {@code class} that, this {@code class} queries the SQL DB Server, which
 * are related to the beans classes.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see dao#dbdao#CustomersDBDAO
 * @see dao#dbdao#CompaniesDBDAO
 * @see dao#dbdao#CouponsDBDAO
 */
public abstract class ConnectorDBDAO {

	/* attributes */
	protected Statement state;
	protected Connection con;
	protected ResultSet result;

	/**
	 * Opens the <code>Statement</code> to connect to the SQL DB Server.
	 * 
	 * @see #state
	 */
	protected void openState() {
		try {
			state = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Closes the <code>Statement</close> disabling the connection to the SQL DB
	 * Server.
	 * 
	 * @see #state
	 */
	protected void closeState() {
		if (state != null)
			try {
				state.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	/**
	 * Closes the <code>ResultSet</code> to prevent errors and to clean up after
	 * using it.
	 * 
	 * @see #result
	 */
	protected void closeResult() {
		if (result != null)
			try {
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

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
	public List<Coupon> getCompanyCoupons(int companyId) {
		List<Coupon> companyCoupons = null;
		try {
			openState();
			result = state.executeQuery(
					"SELECT coupon.coupons.id, coupon.coupons.company_id,coupon.categories.name, coupon.coupons.title, coupon.coupons.description, coupon.coupons.start_date, coupon.coupons.end_date,coupon.coupons.amount,coupon.coupons.price,coupon.coupons.image \r\n"
							+ "FROM coupon.coupons\r\n"
							+ "LEFT JOIN coupon.companies ON coupon.coupons.company_id = coupon.companies.id\r\n"
							+ "LEFT JOIN coupon.categories ON coupon.categories.id = coupon.coupons.category_id\r\n"
							+ "WHERE coupon.companies.id=" + companyId + ";");
			companyCoupons = createCoupons();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeState();
			closeResult();
		}
		return companyCoupons;

	}

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
	public List<Coupon> getCustomerCoupons(int customerId) {
		List<Coupon> customerCoupons = null;
		try {
			openState();
			result = state.executeQuery(
					"SELECT coupon.coupons.id, coupon.coupons.company_id,coupon.categories.name, coupon.coupons.title, coupon.coupons.description, coupon.coupons.start_date, coupon.coupons.end_date,coupon.coupons.amount,coupon.coupons.price,coupon.coupons.image \r\n"
							+ "FROM coupon.coupons\r\n"
							+ "LEFT JOIN coupon.customers_vs_coupons ON coupon.coupons.id = coupon.customers_vs_coupons.coupon_id\r\n"
							+ "LEFT JOIN coupon.customers ON coupon.customers_vs_coupons.customer_id = coupon.customers.id\r\n"
							+ "LEFT JOIN coupon.categories ON coupon.coupons.category_id = coupon.categories.id\r\n"
							+ "WHERE coupon.customers.id=" + customerId + ";");
			customerCoupons = createCoupons();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeState();
			closeResult();
		}
		return customerCoupons;
	}

	/**
	 * Helper method, that creates a <code>Coupon</code> <code>List</code> based on
	 * the <code>Statement</code> opened on previous methods.
	 * 
	 * @return The <code>Coupon</code> <code>List</code> that is currently inside
	 *         the <code>ResultSet</code>.
	 * @see util#tableUtils#CouponsTableUtils
	 * @see util#tableUtils#CategoriesTableUtils
	 * @see #result
	 */
	protected List<Coupon> createCoupons() {
		List<Coupon> coupons = new ArrayList<Coupon>();
		try {
			while (result.next()) {
				coupons.add(new Coupon(result.getInt(CouponsTableUtils.ID.name()),
						result.getInt(CouponsTableUtils.COMPANY_ID.name()),
						CategoryUtils.valueOf(result.getString(CategoriesTableUtils.NAME.name())),
						result.getString(CouponsTableUtils.TITLE.name()),
						result.getString(CouponsTableUtils.DESCRIPTION.name()),
						new Date(result.getDate(CouponsTableUtils.START_DATE.name()).getTime()),
						new Date(result.getDate(CouponsTableUtils.END_DATE.name()).getTime()),
						result.getInt(CouponsTableUtils.AMOUNT.name()),
						result.getDouble(CouponsTableUtils.PRICE.name()),
						result.getString(CouponsTableUtils.IMAGE.name())));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return coupons;
	}

}
