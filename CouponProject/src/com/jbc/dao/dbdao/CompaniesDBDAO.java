package com.jbc.dao.dbdao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.jbc.beans.Company;
import com.jbc.dao.CompaniesDAO;
import com.jbc.util.tableUtils.CompaniesTableUtils;

/**
 * DBDAO {@code class} that {@code implements} the <code>CompaniesDAO</code>
 * {@code interface}, this {@code class} queries the SQL DB Server, which are
 * related to the companies.
 * <p>
 * Also {@code extends} the <code>ConnectorDBDAO</code> for a
 * <code>Connection</code>, <code>Statement</code> and <code>ResultSet</code>.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see dao#CompaniesDAO
 * @see dao#dbdao#ConnectorDBDAO
 */
public final class CompaniesDBDAO extends ConnectorDBDAO implements CompaniesDAO {

	/**
	 * Constructor that takes a <code>Connection</code> that will be used to connect
	 * to the SQL DB Server using the methods in the {@code class}.
	 * 
	 * @param con
	 */
	public CompaniesDBDAO(Connection con) {
		this.con = con;
	}

	@Override
	public boolean isCompanyExist(String email, String password) {
		boolean exists = false;
		try {
			openState();
			result = state.executeQuery(
					"SELECT coupon.companies.id\r\n" + "FROM coupon.companies\r\n" + "WHERE coupon.companies.email = '"
							+ email + "'\r\n" + "AND coupon.companies.password = '" + password + "';");
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
	public Company getThisCompany(String email) {
		Company company = null;
		try {
			openState();
			result = state.executeQuery(
					"SELECT *\r\n" + "FROM coupon.companies\r\n" + "WHERE companies.email = '" + email + "';");
			company = createCompany();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeState();
			closeResult();
		}
		return company;
	}

	@Override
	public void addCompany(Company company) {
		try {
			openState();
			state.executeUpdate("INSERT INTO coupon.companies (name,email,password) VALUES ('" + company.getName()
					+ "','" + company.getEmail() + "','" + company.getPassword() + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeState();
		}
	}

	@Override
	public void updateCompany(Company company) {
		try {
			openState();
			state.executeUpdate("UPDATE coupon.companies\r\n" + "SET coupon.companies.name ='" + company.getName()
					+ "',\r\n" + "coupon.companies.email = '" + company.getEmail() + "',\r\n"
					+ "coupon.companies.password = '" + company.getPassword() + "'\r\n" + "WHERE id = "
					+ company.getId() + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeState();
		}
	}

	@Override
	public void deleteCompany(int companyId) {
		try {
			openState();
			state.executeUpdate("DELETE coupon.customers_vs_coupons\r\n" + "FROM coupon.customers_vs_coupons\r\n"
					+ "LEFT JOIN coupon.coupons ON coupon.coupons.id = coupon.customers_vs_coupons.coupon_id\r\n"
					+ "LEFT JOIN coupon.companies ON coupon.coupons.company_id = coupon.companies.id\r\n"
					+ "WHERE coupon.companies.id = " + companyId + ";");
			state.executeUpdate(
					"DELETE \r\n" + "FROM coupon.coupons\r\n" + "WHERE coupon.coupons.company_id = " + companyId + ";");
			state.executeUpdate(
					"DELETE\r\n" + "FROM coupon.companies\r\n" + "WHERE coupon.companies.id=" + companyId + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeState();
		}
	}

	@Override
	public List<Company> getAllCompanies() {
		List<Company> companies = null;
		try {
			openState();
			result = state.executeQuery("SELECT *\r\n" + "FROM coupon.companies;");
			companies = createCompanies();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeState();
			closeResult();
		}
		return companies;
	}

	@Override
	public Company getOneCompany(int companyId) {
		Company company = null;
		try {
			openState();
			result = state.executeQuery(
					"SELECT *\r\n" + "FROM coupon.companies\r\n" + "WHERE companies.id =" + companyId + ";");
			company = createCompany();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeState();
			closeResult();
		}
		return company;
	}

	/**
	 * Helper method, that creates a <code>Company</code> <code>List</code> based on
	 * the <code>Statement</code> opened on previous methods.
	 * 
	 * @return The <code>Company</code> <code>List</code> that is currently inside
	 *         the <code>ResultSet</code>.
	 * @see util#tableUtils#CompaniesTableUtils
	 * @see #getCompanyCoupons(int)
	 * @see #result
	 */
	private List<Company> createCompanies() {
		List<Company> companies = null;
		try {
			companies = new ArrayList<>();
			while (result.next()) {
				companies.add(new Company(result.getInt(CompaniesTableUtils.ID.name()),
						result.getString(CompaniesTableUtils.NAME.name()),
						result.getString(CompaniesTableUtils.EMAIL.name()),
						result.getString(CompaniesTableUtils.PASSWORD.name())));
			}
			for (Company company : companies) {
				company.setCoupons(getCompanyCoupons(company.getId()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return companies;
	}

	/**
	 * Helper method, to get only one <code>Company</code> instead of a
	 * <code>List</code>, should be used with a <code>ResultSet</code> that can only
	 * result in a single <code>Company</code> (by its id).
	 * 
	 * @return The first <code>Company</code> from a <code>ResultSet</code> or
	 *         {@code null} if no companies were found.
	 * @see #createCompanies()
	 */
	private Company createCompany() {
		List<Company> companies = createCompanies();
		if (companies.size() > 0) {
			return companies.get(0);
		}
		return null;
	}

}
