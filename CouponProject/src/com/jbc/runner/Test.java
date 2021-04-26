package com.jbc.runner;

import java.util.Date;
import java.util.List;
import com.jbc.beans.Company;
import com.jbc.beans.Coupon;
import com.jbc.beans.Customer;
import com.jbc.exception.CustomException;
import com.jbc.facade.AdminFacade;
import com.jbc.facade.CompanyFacade;
import com.jbc.facade.CustomerFacade;
import com.jbc.facade.LoginManager;
import com.jbc.util.beanUtils.CategoryUtils;
import com.jbc.util.daoUtils.ConnectionPool;
import com.jbc.util.facadeUtils.ClientTypeUtils;

/**
 * {@code class} which contains testing of the facades, to ensure that they work
 * correctly.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see facade#LoginManager
 * @see facade#AdminFacade
 * @see facade#CompanyFacade
 * @see facade#CustomerFacade
 */
public final class Test {

	/**
	 * Calls all the methods in the <code>AdminFacade</code>,
	 * <code>CompanyFacade</code>, <code>CustomerFacade</code> {@code class}, this
	 * method will not affect the SQL DB Server, all the created objects are deleted
	 * at the end of the method.
	 * <p>
	 * Starts the <code>CouponExpirationDailyJob</code> at the start of the method
	 * and closes it at the end.
	 * <p>
	 * Prints an exception, if there was a problem with the
	 * <code>ConnectionPool</code>, <code>CouponExpirationDailyJob</code>, or one of
	 * the facades.
	 * 
	 * @see CouponExpirationDailyJob
	 * @see beans#ConnectionPool
	 * @see facade#LoginManager
	 * @see facade#AdminFacade
	 * @see facade#CompanyFacade
	 * @see facade#CustomerFacade
	 */
	public static void testAll() {
		ConnectionPool conPool = null;
		CouponExpirationDailyJob couponJob = null;
		try {
			conPool = ConnectionPool.getInstance();
			couponJob = CouponExpirationDailyJob.getInstance();
			LoginManager login = LoginManager.getInstance();
			AdminFacade admin = (AdminFacade) login.login("admin@admin.com", "admin", ClientTypeUtils.ADMINISTRATOR);

			/* using all AdminFacade methods */
			admin.createCompany(new Company("Mamma Off", "Mamma@gmail.com", "Olamnizu1234"));
			List<Company> companies = admin.getAllCompanies();
			for (Company company : companies) {
				System.out.println(company.toString());
			}
			admin.updateCompany(new Company(companies.get(companies.size() - 1).getId(), "Bakara Inc",
					"Bakara@gmail.com", "a2801maIolz"));
			System.out.println(admin.getOneCompany(companies.get(companies.size() - 1).getId()));
			admin.addCustomer(new Customer("Yonatan", "Caspi", "Caspi@gmail.com", "0128Moalizko"));
			List<Customer> customers = admin.getAllCustomers();
			for (Customer customer : customers) {
				System.out.println(customer.toString());
			}
			admin.updateCustomer(new Customer(customers.get(customers.size() - 1).getId(), "Shay", "Dayan",
					"Dayan@gmail.com", "mopoa8zpL"));
			System.out.println(admin.getOneCustomer(customers.get(customers.size() - 1).getId()));

			/* using all CompanyFacade methods */
			CompanyFacade company = (CompanyFacade) login.login("Bakara@gmail.com", "a2801maIolz",
					ClientTypeUtils.COMPANY);
			company.addCoupon(new Coupon(CategoryUtils.GAMING, "COD", "Free Game Copy",
					new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24 * 7)),
					new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 7)), 19, 19.99, "GAMING.png"));
			List<Coupon> allCoupons = company.getCompanyCoupons();
			for (Coupon coupon : allCoupons) {
				System.out.println(coupon.toString());
			}
			company.updateCoupon(new Coupon(allCoupons.get(allCoupons.size() - 1).getId(), CategoryUtils.ELECTRICITY,
					"Microwave", "20% Discount on any microwave",
					new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24 * 14)),
					new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24 * 14)), 20, 7.99, "ELECTRICITY.jpeg"));
			List<Coupon> coupons = company.getCompanyCoupons(CategoryUtils.ELECTRICITY);
			for (Coupon coupon : coupons) {
				System.out.println(coupon.toString());
			}
			coupons = company.getCompanyCoupons(7.99);
			for (Coupon coupon : coupons) {
				System.out.println(coupon.toString());
			}
			System.out.println(company.getCompanyDetails());

			/* using all CustomerFacade methods */
			CustomerFacade customer = (CustomerFacade) login.login("Dayan@gmail.com", "mopoa8zpL",
					ClientTypeUtils.CUSTOMER);
			customer.purchaseCoupon(allCoupons.get(allCoupons.size() - 1).getId());
			coupons = customer.CustomerCoupons();
			for (Coupon coupon : coupons) {
				System.out.println(coupon.toString());
			}
			coupons = customer.getCustomerCoupons(CategoryUtils.ELECTRICITY);
			for (Coupon coupon : coupons) {
				System.out.println(coupon.toString());
			}
			coupons = customer.getCustomerCoupons(7.99);
			for (Coupon coupon : coupons) {
				System.out.println(coupon.toString());
			}
			System.out.println(customer.getCustomerDetails());

			/* deleting coupon,customer and company */
			company.deleteCoupon(allCoupons.get(allCoupons.size() - 1).getId());
			allCoupons = company.getCompanyCoupons();
			for (Coupon deletedCoupon : allCoupons) {
				System.out.println(deletedCoupon.toString());
			}
			admin.deleteCustomer(customer.getCustomerDetails().getId());
			customers = admin.getAllCustomers();
			for (Customer deletedCustomer : customers) {
				System.out.println(deletedCustomer.toString());
			}
			admin.deleteCompany(company.getCompanyDetails().getId());
			companies = admin.getAllCompanies();
			for (Company deletedCompany : companies) {
				System.out.println(deletedCompany.toString());
			}
			System.out.println("\nTesting completed successfully");
		} catch (CustomException e) {
			System.err.println(e);
		} finally {
			couponJob.stop();
			conPool.closeAllConnections();
		}
	}

}
