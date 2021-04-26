package com.jbc.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * <code>Company</code> bean {@code class} that uses the <code>Coupon</code>
 * bean {@code class}.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see beans#Coupon
 */
public final class Company {

	/* attributes */
	private int id;
	private String name;
	private String email;
	private String password;
	private List<Coupon> coupons;

	/* constructors */
	/**
	 * <code>Company</code> constructor for the <code>AdminFacade</code> <code>updateCompany</code> method.
	 * 
	 * @see facade#AdminFacade
	 */
	public Company(int id, String name, String email, String password) {
		coupons = new ArrayList<Coupon>();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	/**
	 * <code>Company</code> constructor for the <code>AdminFacade</code> <code>createCompany</code> method.
	 * 
	 * @see facade#AdminFacade
	 */
	public Company(String name, String email, String password) {
		coupons = new ArrayList<Coupon>();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	/* getters & setters */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<Coupon> coupons) {
		this.coupons = coupons;
	}

	/* toString */
	@Override
	public String toString() {
		String string = "Company [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password;
		if (coupons.size() != 0)
			return string + ", coupons=" + coupons + "]";
		return string + ", coupons=None]";
	}

	/* equals & hashCode */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Company)
			return this.id == ((Company) obj).id;
		return false;
	}

	@Override
	public int hashCode() {
		return id;
	}

}
