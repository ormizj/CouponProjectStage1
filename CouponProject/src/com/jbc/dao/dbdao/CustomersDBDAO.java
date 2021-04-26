package com.jbc.dao.dbdao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.jbc.beans.Customer;
import com.jbc.dao.CustomersDAO;
import com.jbc.util.tableUtils.CustomersTableUtils;

/**
 * DBDAO {@code class} that {@code implements} the <code>CustomersDAO</code>
 * {@code interface}, this {@code class} queries the SQL DB Server, which are
 * related to the customers.
 * <p>
 * Also {@code extends} the <code>ConnectorDBDAO</code> for a
 * <code>Connection</code>, <code>Statement</code> and <code>ResultSet</code>.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see dao#CustomersDAO
 * @see dao#dbdao#ConnectorDBDAO
 */
public final class CustomersDBDAO extends ConnectorDBDAO implements CustomersDAO {

	/**
	 * Constructor that takes a <code>Connection</code> that will be used to connect
	 * to the SQL DB Server using the methods in the {@code class}.
	 * 
	 * @param con
	 */
	public CustomersDBDAO(Connection con) {
		this.con = con;
	}

	@Override
	public boolean isCustomerExists(String email, String password) {
		boolean exists = false;
		try {
			openState();
			result = state.executeQuery(
					"SELECT coupon.customers.id\r\n" + "FROM coupon.customers\r\n" + "WHERE coupon.customers.email = '"
							+ email + "'\r\n" + "AND coupon.customers.password = '" + password + "';");
			if (result.next()) {
				exists = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeState();
			closeResult();
		}
		return exists;
	}

	@Override
	public Customer getThisCustomer(String email) {
		Customer customer = null;
		try {
			openState();
			result = state.executeQuery(
					"SELECT *\r\n" + "FROM coupon.customers\r\n" + "WHERE customers.email = '" + email + "';");
			customer = createCustomer();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeState();
			closeResult();
		}
		return customer;
	}

	@Override
	public void addCustomer(Customer customer) {
		try {
			openState();
			state.executeUpdate("INSERT INTO coupon.customers (first_name,last_name,email,password) VALUES ('"
					+ customer.getFirstName() + "','" + customer.getLastName() + "','" + customer.getEmail() + "','"
					+ customer.getPassword() + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeState();
		}
	}

	@Override
	public void updateCustomer(Customer customer) {
		try {
			openState();
			state.executeUpdate("UPDATE coupon.customers  \r\n" + "SET coupon.customers.first_name ='"
					+ customer.getFirstName() + "',  \r\n" + "coupon.customers.last_name = '" + customer.getLastName()
					+ "',\r\n" + "coupon.customers.email = '" + customer.getEmail() + "',\r\n"
					+ "coupon.customers.password = '" + customer.getPassword() + "' \r\n" + "WHERE id =  "
					+ customer.getId() + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeState();
		}
	}

	@Override
	public void deleteCustomer(int customerId) {
		try {
			openState();
			state.executeUpdate("DELETE coupon.customers_vs_coupons\r\n" + "FROM coupon.customers_vs_coupons\r\n"
					+ "LEFT JOIN coupon.customers ON coupon.customers.id = coupon.customers_vs_coupons.customer_id\r\n"
					+ "WHERE coupon.customers.id = " + customerId + ";");
			state.executeUpdate(
					"DELETE\r\n" + "FROM coupon.customers\r\n" + "WHERE coupon.customers.id=" + customerId + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeState();
		}
	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = null;
		try {
			openState();
			result = state.executeQuery("SELECT *\r\n" + "FROM coupon.customers;");
			customers = createCustomers();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeState();
			closeResult();
		}
		return customers;
	}

	@Override
	public Customer getOneCustomer(int customerId) {
		Customer customer = null;
		try {
			openState();
			result = state.executeQuery(
					"SELECT *\r\n" + "FROM coupon.customers\r\n" + "WHERE coupon.customers.id =" + customerId + ";");
			customer = createCustomer();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeState();
			closeResult();
		}
		return customer;
	}

	/**
	 * Helper method, that creates a <code>Customer</code> <code>List</code> based
	 * on the <code>Statement</code> opened on previous methods.
	 * 
	 * @return The <code>Customer</code> <code>List</code> that is currently inside
	 *         the <code>ResultSet</code>.
	 * @see util#tableUtils#CustomersTableUtils
	 * @see #getCustomerCoupons(int)
	 * @see #result
	 */
	private List<Customer> createCustomers() {
		List<Customer> customers = null;
		try {
			customers = new ArrayList<Customer>();
			while (result.next()) {
				customers.add(new Customer(result.getInt(CustomersTableUtils.ID.name()),
						result.getString(CustomersTableUtils.FIRST_NAME.name()),
						result.getString(CustomersTableUtils.LAST_NAME.name()),
						result.getString(CustomersTableUtils.EMAIL.name()),
						result.getString(CustomersTableUtils.PASSWORD.name())));
			}
			for (Customer customer : customers) {
				customer.setCoupons(getCustomerCoupons(customer.getId()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}

	/**
	 * Helper method, to get only one <code>Customer</code> instead of a
	 * <code>List</code>, should be used with a <code>ResultSet</code> that can only
	 * result in a single <code>Customer</code> (by its id).
	 * 
	 * @return The first <code>Customer</code> from a <code>ResultSet</code> or
	 *         {@code null} if no customers were found.
	 * @see #createCustomers()
	 */
	private Customer createCustomer() {
		List<Customer> customers = createCustomers();
		if (customers.size() > 0) {
			Customer customer = customers.get(0);
			return customer;
		}
		return null;
	}

}
