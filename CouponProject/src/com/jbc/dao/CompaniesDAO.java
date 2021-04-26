package com.jbc.dao;

import java.util.List;
import com.jbc.beans.Company;

/**
 * DAO {@code interface} that gets implemented by the <code>CompaniesDBDAO</code>
 * {@code class}, this {@code interface} queries the SQL DB Server, which are
 * related to the companies.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see dao#dbdao#CompaniesDBDAO
 */
public interface CompaniesDAO {

	/**
	 * 
	 * @return {@code true} if there is an existing <code>Company</code> with the
	 *         specified email and password, and {@code false} if there isn't.
	 * @param email
	 * @param password
	 * @see #openState()
	 * @see #closeState()
	 * @see #closeResult()
	 * @see #result
	 * @see #state
	 */
	public boolean isCompanyExist(String email, String password);

	/**
	 * Adds a <code>Company</code> to the SQL DB Server.
	 * 
	 * @param company
	 * @see #openState()
	 * @see #closeState()
	 * @see #state
	 */
	public void addCompany(Company company);

	/**
	 * Updates a <code>Company</code> from the SQL DB Server.
	 * 
	 * @param company
	 * @see #openState()
	 * @see #closeState()
	 * @see #state
	 */
	public void updateCompany(Company company);

	/**
	 * Deletes a <code>Company</code> from the SQL DB Server.
	 * 
	 * @param companyId
	 * @see #openState()
	 * @see #closeState()
	 * @see #state
	 */
	public void deleteCompany(int companyId);

	/**
	 * 
	 * @return The <code>Company</code> <code>List</code> of all companies that are
	 *         currently in the SQL DB Server.
	 * @see #createCompanies()
	 * @see #openState()
	 * @see #closeState()
	 * @see #closeResult()
	 * @see #result
	 * @see #state
	 */
	public List<Company> getAllCompanies();

	/**
	 * 
	 * @return The <code>Company</code> of the specified <code>Company</code> id
	 *         that is currently in the SQL DB Server, if the <code>Company</code>
	 *         does not exists it will return {@code null}.
	 * @param companyId
	 * @see #createCompany()
	 * @see #openState()
	 * @see #closeState()
	 * @see #closeResult()
	 * @see #result
	 * @see #state
	 */
	public Company getOneCompany(int companyId);

	/**
	 * 
	 * @param email
	 * @return The <code>Company</code> with the specified email or {@code null} if
	 *         it doesn't exist.
	 * @see #openState()
	 * @see #closeState()
	 * @see #closeResult()
	 * @see #result
	 * @see #state
	 */
	public Company getThisCompany(String email);

}
