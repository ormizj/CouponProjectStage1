package com.jbc.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * <code>Customer</code> bean {@code class} that uses the <code>Coupon</code>
 * bean {@code class}.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see beans#Coupon
 */
public final class Customer {

	/* attributes */
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private List<Coupon> coupons;

	/* constructors */
	/**
	 * <code>Customer</code> constructor for the <code>AdminFacade</code> <code>updateCustomer</code> method.
	 * 
	 * @see facade#AdminFacade
	 */
	public Customer(int id, String firstName, String lastName, String email, String password) {
		coupons = new ArrayList<Coupon>();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	/**
	 * <code>Customer</code> constructor for the <code>AdminFacade</code> <code>addCustomer</code> method.
	 * 
	 * @see facade#AdminFacade
	 */
	public Customer(String firstName, String lastName, String email, String password) {
		coupons = new ArrayList<Coupon>();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	/* getters & setters */
	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupon) {
		this.coupons = coupon;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/* toString */
	@Override
	public String toString() {
		String string = "Customer [id=" + id + ", first name=" + firstName + ", last name=" + lastName + ", email="
				+ email + ", password=" + password;
		if (coupons.size() != 0)
			return string + ", coupons=" + coupons + "]";
		return string + ", coupons=None]";
	}

	/* equals & hashCode */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Customer)
			return this.id == ((Customer) obj).id;
		return false;
	}

	@Override
	public int hashCode() {
		return id;
	}
}