package com.jbc.dao.dbdao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import com.jbc.beans.Coupon;
import com.jbc.dao.CouponsDAO;
import com.jbc.util.tableUtils.CategoriesTableUtils;

/**
 * DBDAO {@code class} that {@code implements} the <code>CouponsDAO</code>
 * {@code interface}, this {@code class} queries the SQL DB Server, which are
 * related to the coupons.
 * <p>
 * Also {@code extends} the <code>ConnectorDBDAO</code> for a
 * <code>Connection</code>, <code>Statement</code> and <code>ResultSet</code>.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see dao#CouponsDAO
 * @see dao#dbdao#ConnectorDBDAO
 */
public final class CouponsDBDAO extends ConnectorDBDAO implements CouponsDAO {

	/**
	 * Constructor that takes a <code>Connection</code> that will be used to connect
	 * to the SQL DB Server using the methods in the {@code class}.
	 * 
	 * @param con
	 */
	public CouponsDBDAO(Connection con) {
		this.con = con;
	}

	@Override
	public void addCoupon(Coupon coupon) {
		try {
			openState();
			result = state.executeQuery("SELECT id\r\n" + "FROM coupon.categories\r\n"
					+ "WHERE coupon.categories.name = '" + coupon.getCategory().name() + "';");
			result.next();
			state.executeUpdate(
					"INSERT INTO coupon.coupons (company_id,category_id,title,description,start_date,end_date,amount,price,image) \r\n"
							+ "VALUES (" + coupon.getCompanyId() + "," + result.getInt(CategoriesTableUtils.ID.name())
							+ ",'" + coupon.getTitle() + "','" + coupon.getDescription() + "','"
							+ new Date(coupon.getStartDate().getTime()) + "','"
							+ new Date(coupon.getEndDate().getTime()) + "'," + coupon.getAmount() + ","
							+ coupon.getPrice() + ",'" + coupon.getImage() + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeState();
			closeResult();
		}
	}

	@Override
	public void updateCoupon(Coupon coupon) {
		try {
			openState();
			result = state.executeQuery("SELECT id\r\n" + "FROM coupon.categories\r\n"
					+ "WHERE coupon.categories.name = '" + coupon.getCategory().name() + "';");
			result.next();
			state.executeUpdate("UPDATE coupon.coupons\r\n" + "SET coupon.coupons.category_id = "
					+ result.getInt(CategoriesTableUtils.ID.name()) + ",\r\n" + "coupon.coupons.title = '"
					+ coupon.getTitle() + "',\r\n" + "coupon.coupons.description = '" + coupon.getDescription()
					+ "',\r\n" + "coupon.coupons.start_date = '" + new Date(coupon.getStartDate().getTime()) + "',\r\n"
					+ "coupon.coupons.end_date = '" + new Date(coupon.getEndDate().getTime()) + "',\r\n"
					+ "coupon.coupons.amount = '" + coupon.getAmount() + "',\r\n" + "coupon.coupons.price = '"
					+ coupon.getPrice() + "',\r\n" + "coupon.coupons.image = '" + coupon.getImage() + "'\r\n"
					+ "WHERE id = " + coupon.getId() + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeState();
			closeResult();
		}
	}

	@Override
	public void deleteCoupon(int couponId) {
		try {
			openState();
			state.executeUpdate("DELETE coupon.customers_vs_coupons\r\n" + "FROM coupon.customers_vs_coupons\r\n"
					+ "LEFT JOIN coupon.coupons ON customers_vs_coupons.coupon_id = coupons.id\r\n"
					+ "WHERE coupon.coupons.id = " + couponId + ";");
			state.executeUpdate("DELETE coupon.coupons\r\n" + "FROM coupon.coupons\r\n" + "WHERE coupon.coupons.id = "
					+ couponId + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeState();
		}
	}

	@Override
	public List<Coupon> getAllCoupons() {
		List<Coupon> coupons = null;
		try {
			openState();
			result = state.executeQuery(
					"SELECT coupon.coupons.id, coupon.coupons.company_id,coupon.categories.name, coupon.coupons.title, coupon.coupons.description, coupon.coupons.start_date, coupon.coupons.end_date,coupon.coupons.amount,coupon.coupons.price,coupon.coupons.image \r\n"
							+ "FROM coupon.coupons\r\n"
							+ "LEFT JOIN coupon.categories ON coupon.categories.id = coupon.coupons.category_id;");
			coupons = createCoupons();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeState();
			closeResult();
		}
		return coupons;
	}

	@Override
	public Coupon getOneCoupon(int couponId) {
		Coupon coupon = null;
		try {
			openState();
			result = state.executeQuery(
					"SELECT coupon.coupons.id, coupon.coupons.company_id,coupon.categories.name, coupon.coupons.title, coupon.coupons.description, coupon.coupons.start_date, coupon.coupons.end_date,coupon.coupons.amount,coupon.coupons.price,coupon.coupons.image \r\n"
							+ "FROM coupon.coupons\r\n"
							+ "LEFT JOIN coupon.categories ON coupon.categories.id = coupon.coupons.category_id\r\n"
							+ "WHERE coupon.coupons.id=" + couponId + ";");
			coupon = createCoupon();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeState();
			closeResult();
		}
		return coupon;
	}

	@Override
	public void addCouponPurchase(int customerId, int couponId) {
		try {
			openState();
			state.executeUpdate("UPDATE coupon.coupons\r\n" + "SET coupon.coupons.amount = coupon.coupons.amount -1\r\n"
					+ "WHERE coupon.coupons.id=" + couponId + ";\r\n");
			state.executeUpdate("INSERT INTO coupon.customers_vs_coupons (customer_id,coupon_id) VALUES (" + customerId
					+ "," + couponId + ");");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeState();
		}
	}

	/**
	 * Helper method, to get only one <code>Coupon</code> instead of a
	 * <code>List</code>, should be used with a <code>ResultSet</code> that can only
	 * result in a single <code>Coupon</code> (by its id).
	 * 
	 * @return The first <code>Coupon</code> from a <code>ResultSet</code> or
	 *         {@code null} if no coupons were found.
	 * @see #createCoupons()
	 */
	private Coupon createCoupon() {
		List<Coupon> coupons = createCoupons();
		if (coupons.size() > 0) {
			return coupons.get(0);
		}
		return null;
	}

}
