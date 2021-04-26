package com.jbc.dao;

import com.jbc.beans.Customer;

import java.util.List;

/**
 * DAO {@code interface} that {@code implements} the <code>CustomersDBDAO</code>
 * {@code class}, this {@code interface} queries the SQL DB Server, which are
 * related to the customers.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see dao#dbdao#CustomersDBDAO
 */
public interface CustomersDAO {

	/**
	 * 
	 * @return {@code true} if there is an existing <code>Customer</code> with the
	 *         specified email and password, and {@code false} if there isn't.
	 * @param email
	 * @param password
	 * @see #openState()
	 * @see #closeState()
	 * @see #closeResult()
	 * @see #result
	 * @see #state
	 */
	public boolean isCustomerExists(String email, String password);

	/**
	 * Adds a <code>Customer</code> to the SQL DB Server.
	 * 
	 * @param customer
	 * @throws SQLException
	 * @see #openState()
	 * @see #closeState()
	 * @see #state
	 */
	public void addCustomer(Customer customer);

	/**
	 * Updates a <code>Customer</code> from the SQL DB Server.
	 * 
	 * @param customer
	 * @throws SQLException
	 * @see #openState()
	 * @see #closeState()
	 * @see #state
	 */
	public void updateCustomer(Customer customer);

	/**
	 * Deletes a <code>Customer</code> from the SQL DB Server.
	 * 
	 * @param customerId
	 * @throws SQLException
	 * @see #openState()
	 * @see #closeState()
	 * @see #state
	 */
	public void deleteCustomer(int CustomerId);

	/**
	 * 
	 * @return The <code>Customer</code> <code>List</code> of all customers that are
	 *         currently in the SQL DB Server.
	 * @throws SQLException
	 * @see #createCustomers()
	 * @see #openState()
	 * @see #closeState()
	 * @see #closeResult()
	 * @see #result
	 * @see #state
	 */
	public List<Customer> getAllCustomers();

	/**
	 * 
	 * @return The <code>Customer</code> of the specified <code>Customer</code> id
	 *         that is currently in the SQL DB Server, if the <code>Customer</code>
	 *         does not exists it will return {@code null}.
	 * @param customerId
	 * @see #createCustomer()
	 * @see #openState()
	 * @see #closeState()
	 * @see #closeResult()
	 * @see #result
	 * @see #state
	 */
	public Customer getOneCustomer(int customerId);

	/**
	 * 
	 * @param email
	 * @return The <code>Customer</code> with the specified email or {@code null} if
	 *         it doesn't exist.
	 * @throws SQLException
	 * @see #openState()
	 * @see #closeState()
	 * @see #closeResult()
	 * @see #result
	 * @see #state
	 */
	public Customer getThisCustomer(String email);

}
