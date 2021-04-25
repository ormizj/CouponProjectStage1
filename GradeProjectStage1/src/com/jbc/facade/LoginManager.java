package com.jbc.facade;

import java.sql.SQLException;

import com.jbc.util.facadeUtils.ClientTypeUtils;

/**
 * {@code class} that manages the login into the system, and retrieving
 * corresponding instances based on the <code>email</code>,
 * <code>password</code> and <code>clientType</code> with the help of the
 * facades.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see facade#ClientFacade
 * @see facade#CompanyFacade
 * @see facade#AdminFacade
 * @see util#ClientTypeUtils
 */
public final class LoginManager {

	/* attributes */
	private static LoginManager instance;

	/* constructor, singleton */
	private LoginManager() {
	}

	/**
	 * Creates a <code>LoginManager</code> instance, and limiting the instances of
	 * the <code>LoginManager</code> to 1, returning the same instance, if this
	 * method is called upon multiple times , allowing multiple objects use the same
	 * instance.
	 * 
	 * @return <code>LoginManager</code> instance, if this method is called upon
	 *         multiple times, returns the same instance that was first created.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @see #LoginManager()
	 * @see #instance
	 */
	public synchronized static LoginManager getInstance() {
		if (instance == null)
			instance = new LoginManager();
		return instance;
	}

	/**
	 * Attempts to login as a client based on the <code>ClientTypeUtils</code> type,
	 * email and password, if the login is successful returns an instance, if its
	 * not returns {@code null}.
	 * 
	 * @param email
	 * @param password
	 * @param clientType
	 * @return <code>CustomerFacade</code>, <code>CompanyFacade</code>,
	 *         <code>AdminFacade</code> based on the <code>ClientTypeUtils</code>
	 *         type, or {@code null} if the login was unsuccessful.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InterruptedException
	 */
	public synchronized ClientFacade login(String email, String password, ClientTypeUtils clientType) {
		if (email == null || password == null || clientType == null)
			return null;
		switch (clientType) {
		case ADMINISTRATOR:
			AdminFacade admin = AdminFacade.getInstance();
			if (admin.login(email, password))
				return admin;
		case COMPANY:
			CompanyFacade company = new CompanyFacade();
			if (company.login(email, password))
				return company;
		case CUSTOMER:
			CustomerFacade customer = new CustomerFacade();
			if (customer.login(email, password))
				return customer;
		default:
			return null;
		}
	}

}
