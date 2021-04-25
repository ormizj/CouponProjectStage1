package com.jbc.facade;

import java.util.List;
import com.jbc.beans.Company;
import com.jbc.beans.Coupon;
import com.jbc.beans.Customer;
import com.jbc.dao.dbdao.CompaniesDBDAO;
import com.jbc.dao.dbdao.CouponsDBDAO;
import com.jbc.dao.dbdao.CustomersDBDAO;
import com.jbc.exception.company.CompanyNotFoundException;
import com.jbc.exception.company.CompanyNullValueException;
import com.jbc.exception.company.CompanyDuplicateValueException;
import com.jbc.exception.company.CompanyIsNullException;
import com.jbc.exception.customer.CustomerNotFoundException;
import com.jbc.exception.customer.CustomerNullValueException;
import com.jbc.util.exceptionUtils.ExceptionUtils;
import com.jbc.util.facadeUtils.AdminUtils;
import com.jbc.util.facadeUtils.StringClass;
import com.jbc.exception.customer.CustomerDuplicateValueException;
import com.jbc.exception.customer.CustomerIsNullException;

/**
 * {@code class} that {@code extends} the <code>ClientFacade</code> and ensures
 * the business logic of the application related to the admin before executing
 * it in the DAO classes.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see facade#ClientFacade
 * @see dao#CompaniesDAO
 * @see dao#CustomersDAO
 */
public final class AdminFacade extends ClientFacade {

	/* attributes */
	private static AdminFacade instance;

	/**
	 * Singleton constructor that initializes the connections with the
	 * <code>CompaniesDBDAO</code> and the <code>CustomersDBDAO</code>.
	 * 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 * @see #con
	 * @see #companiesDAO
	 * @see #customersDAO
	 */
	private AdminFacade() {
		con = conPool.getConnection();
		companiesDAO = new CompaniesDBDAO(con);
		customersDAO = new CustomersDBDAO(con);
		couponsDAO = new CouponsDBDAO(con);
	}

	/**
	 * /** Creates an <code>AdminFacade</code> instance, and limiting the instances
	 * of the <code>AdminFacade</code> to 1, returning the same instance, if this
	 * method is called upon multiple times , allowing multiple objects use the same
	 * instance.
	 * 
	 * @return <code>AdminFacade</code> instance, if this method is called upon
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 * @see #AdminFacade()
	 * @see #instance
	 */
	public synchronized static AdminFacade getInstance() {
		if (instance == null)
			instance = new AdminFacade();
		return instance;
	}

	/**
	 * Checks if the email and password are correct, closing the
	 * <code>Connection</code> if it is not.
	 * 
	 * @param email
	 * @param password
	 * @return {@code true} if the email and password are correct. {@code false} if
	 *         they are incorrect.
	 * @see util#AdminUtils
	 * @see #conPool
	 */
	@Override
	public synchronized boolean login(String email, String password) {
		if (email.equalsIgnoreCase(AdminUtils.ADMIN_USER.toString())
				&& password.equals(AdminUtils.ADMIN_PASS.toString()))
			return true;
		conPool.releaseConnection(con);
		return false;
	}

	/**
	 * Checks if the specified <code>Company</code> has any irregularities, if it
	 * does, it throws an exception, if not, adds the <code>Company</code> to the
	 * SQL DB Server.
	 * 
	 * @param company
	 * @throws CompanyNullValueException      if the <code>Company</code> has any
	 *                                        {@code null} values in it.
	 * @throws CompanyDuplicateValueException if a <code>Company</code> with the
	 *                                        same name or email already exists.
	 * @throws CompanyIsNullException
	 * @see #checkNameEmail(Company)
	 * @see #companiesDAO
	 */
	public synchronized void createCompany(Company company) throws CompanyNullValueException, CompanyDuplicateValueException, CompanyIsNullException {
		checkNameEmail(companiesDAO.getAllCompanies(), company);
		companiesDAO.addCompany(company);
	}

	/**
	 * Checks if the specified <code>Company</code> has any irregularities, if it
	 * does, it throws an exception, if not, updates the <code>Company</code> in the
	 * SQL DB Server with the new variables specified in this method.
	 * 
	 * @param company
	 * @throws CompanyNullValueException      if the <code>Company</code> has any
	 *                                        {@code null} values in it.
	 * @throws CompanyNotFoundException       if the <code>Company</code> id was not
	 *                                        found.
	 * @throws CompanyDuplicateValueException if a <code>Company</code> with the
	 *                                        same name or email already exists.
	 * @throws CompanyIsNullException
	 * @see #checkNameEmail(Company)
	 * @see #companiesDAO
	 */
	public synchronized void updateCompany(Company company) throws CompanyNullValueException, CompanyNotFoundException,
			CompanyDuplicateValueException, CompanyIsNullException {
		List<Company> companies = companiesDAO.getAllCompanies();
		checkNameEmail(companies, company);
		if (companies.contains(company)) {
			companiesDAO.updateCompany(company);
			return;
		}
		throw new CompanyNotFoundException(company.getId());
	}

	/**
	 * Helper method, checks if a <code>Company</code> with the same name or email
	 * already exists in the inserted <code>List</code> if it does, throws
	 * <code>CompanyDuplicateValueException</code>.
	 * <p>
	 * Also uses the <code>checkCompany</code> method to make sure it doesn't have
	 * any irregularities.
	 * 
	 * @param companies
	 * @param company
	 * @throws CompanyNullValueException      if the <code>Company</code> has any
	 *                                        {@code null} values in it.
	 * @throws CompanyDuplicateValueException if a <code>Company</code> with the
	 *                                        same name or email already exists.
	 * @throws CompanyIsNullException
	 * @see util#ExceptionUtils
	 * @see #checkCompany(Company)
	 */
	private void checkNameEmail(List<Company> companies, Company company)
			throws CompanyNullValueException, CompanyDuplicateValueException, CompanyIsNullException {
		checkCompany(company);
		for (Company tempCompany : companies) {
			if (!tempCompany.equals(company) && (tempCompany.getName().equalsIgnoreCase(company.getName())
					|| tempCompany.getEmail().equalsIgnoreCase(company.getEmail()))) {
				CompanyDuplicateValueException exception = new CompanyDuplicateValueException();
				if (tempCompany.getName().equalsIgnoreCase(company.getName())) {
					exception.addNameOfDuplicate(ExceptionUtils.NAME);
				}
				if (tempCompany.getEmail().equalsIgnoreCase(company.getEmail())) {
					exception.addNameOfDuplicate(ExceptionUtils.EMAIL);
				}
				throw exception;
			}
		}
	}

	/**
	 * Deletes the <code>Company</code> with the specified id, if the
	 * <code>Company</code> does not exist, throws
	 * <code>CompanyNotFoundException</code>.
	 * 
	 * @param companyId
	 * @throws CompanyNotFoundException if the <code>Company</code> id was not
	 *                                  found.
	 * @see #companiesDAO
	 */
	public synchronized void deleteCompany(int companyId) throws CompanyNotFoundException {
		for (Company company : companiesDAO.getAllCompanies()) {
			if (company.getId() == companyId) {
				deleteCompanyHelper(companyId);
				return;
			}
		}
		throw new CompanyNotFoundException(companyId);
	}

	/**
	 * Helper method, for deleting a <code>Company</code>, calls the
	 * <code>deleteCompanySynchronizer</code> to ensures synchronization with the
	 * <code>Company</code> coupons, to avoid errors.
	 * 
	 * @param companyId
	 * @see #deleteCompany(int)
	 * @see #deleteCompanySynchronizer(List, int, int)
	 * @see #couponsDAO
	 */
	private void deleteCompanyHelper(int companyId) {
		List<Coupon> coupons = couponsDAO.getCompanyCoupons(companyId);
		if (coupons.size() != 0)
			deleteCompanySynchronizer(coupons, coupons.size(), companyId);
		else
			companiesDAO.deleteCompany(companyId);
	}

	/**
	 * Helper method, that ensures the synchronization of the coupons in the
	 * specified <code>Company</code>.
	 * <p>
	 * should not be called directly, but through the
	 * <code>deleteCompanyHelper</code> method to avoid exceptions.
	 * 
	 * @param coupons
	 * @param size
	 * @param companyId
	 * @see util#StringClass
	 * @see #companiesDAO
	 */
	private void deleteCompanySynchronizer(List<Coupon> coupons, int size, int companyId) {
		synchronized (StringClass.COUPON_ID_SYNC + coupons.get(size - 1).getId()) {
			if (--size != 0) {
				deleteCompanySynchronizer(coupons, size, companyId);
				return;
			}
			companiesDAO.deleteCompany(companyId);
			return;
		}
	}

	/**
	 * 
	 * @return <code>Company</code> <code>List</code> of all companies in the SQL DB
	 *         Server.
	 * 
	 * @see #companiesDAO
	 */
	public synchronized List<Company> getAllCompanies() {
		return companiesDAO.getAllCompanies();
	}

	/**
	 * 
	 * @param companyId
	 * @return the <code>Company</code> with the specified id from the SQL DB
	 *         Server.
	 * @throws CompanyNotFoundException if the <code>Company</code> id was not
	 *                                  found.
	 * @see #companiesDAO
	 */
	public synchronized Company getOneCompany(int companyId) throws CompanyNotFoundException {
		Company company = companiesDAO.getOneCompany(companyId);
		if (company != null) {
			return company;
		}
		throw new CompanyNotFoundException(companyId);
	}

	/**
	 * checks if the specified <code>Customer</code> has any irregularities, if it
	 * does, it throws an exception, if not, updates the <code>Customer</code> in
	 * the SQL DB Server with the new variables specified in this method.
	 * 
	 * @param customer
	 * @throws CustomerNullValueException      if the <code>Company</code> has any
	 *                                         {@code null} values in it.
	 * @throws CustomerNotFoundException       if the <code>Customer</code> id was
	 *                                         not found.
	 * @throws CustomerDuplicateValueException if a <code>Customer</code> with the
	 *                                         same email already exists.
	 * @throws CustomerIsNullException
	 * @see #checkCustomer(Customer)
	 * @see #checkEmail(Customer)
	 * @see #customersDAO
	 */
	public synchronized void updateCustomer(Customer customer) throws CustomerNullValueException,
			CustomerNotFoundException, CustomerDuplicateValueException, CustomerIsNullException {
		List<Customer> customers = customersDAO.getAllCustomers();
		checkEmail(customers, customer);
		if (customers.contains(customer)) {
			customersDAO.updateCustomer(customer);
			return;
		}
		throw new CustomerNotFoundException(customer.getId());
	}

	/**
	 * Checks if the specified <code>Customer</code> has any irregularities, if it
	 * does, it throws an exception, if not, adds the <code>Customer</code> to the
	 * SQL DB Server.
	 * 
	 * @param customer
	 * @throws CustomerNullValueException      if the <code>Company</code> has any
	 *                                         {@code null} values in it.
	 * @throws CustomerDuplicateValueException if a <code>Customer</code> with the
	 *                                         same email already exists.
	 * @throws CustomerIsNullException
	 * @see #checkEmail(Customer)
	 * @see #customersDAO
	 */
	public synchronized void addCustomer(Customer customer)
			throws CustomerNullValueException, CustomerDuplicateValueException, CustomerIsNullException {
		checkEmail(customersDAO.getAllCustomers(), customer);
		customersDAO.addCustomer(customer);
	}

	/**
	 * Helper method, checks if a <code>Customer</code> with the same name already
	 * exists in the inserted <code>List</code> if it does, throws
	 * <code>CustomerDuplicateValueException</code>.
	 * 
	 * @param customers
	 * @param customer
	 * @throws CustomerDuplicateValueException if a <code>Customer</code> with the
	 *                                         same email already exists.
	 * @throws CustomerNullValueException      if the <code>Company</code> has any
	 *                                         {@code null} values in it.
	 * @throws CustomerIsNullException
	 * @see #checkCustomer(Customer)
	 */
	private void checkEmail(List<Customer> customers, Customer customer)
			throws CustomerDuplicateValueException, CustomerNullValueException, CustomerIsNullException {
		checkCustomer(customer);
		for (Customer tempCustomer : customers) {
			if (!tempCustomer.equals(customer) && tempCustomer.getEmail().equalsIgnoreCase(customer.getEmail())) {
				throw new CustomerDuplicateValueException();
			}
		}
	}

	/**
	 * Deletes the <code>Customer</code> with the specified id, if the
	 * <code>Customer</code> does not exist, throws
	 * <code>CustomerNotFoundException</code>.
	 * 
	 * @param customerId
	 * @throws CustomerNotFoundException if the <code>Customer</code> id was not
	 *                                   found.
	 * @see #customersDAO
	 */
	public synchronized void deleteCustomer(int customerId) throws CustomerNotFoundException {
		for (Customer customer : customersDAO.getAllCustomers()) {
			if (customer.getId() == customerId) {
				customersDAO.deleteCustomer(customerId);
				return;
			}
		}
		throw new CustomerNotFoundException(customerId);
	}

	/**
	 * 
	 * @return <code>Customer</code> <code>List</code> of all customers in the SQL
	 *         DB Server.
	 * 
	 * @return
	 * @see #customersDAO
	 */
	public synchronized List<Customer> getAllCustomers() {
		return customersDAO.getAllCustomers();
	}

	/**
	 * 
	 * @param customerId
	 * @return the <code>Customer</code> with the specified id from the SQL DB
	 *         Server.
	 * @throws CustomerNotFoundException if the <code>Customer</code> id was not
	 *                                   found.
	 * @see #customersDAO
	 */
	public synchronized Customer getOneCustomer(int customerId) throws CustomerNotFoundException {
		Customer customer = customersDAO.getOneCustomer(customerId);
		if (customer != null) {
			return customer;
		}
		throw new CustomerNotFoundException(customerId);
	}

}
