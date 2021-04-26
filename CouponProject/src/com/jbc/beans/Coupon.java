package com.jbc.beans;

import java.util.Date;

import com.jbc.util.beanUtils.CategoryUtils;

/**
 * <code>Coupon</code> bean {@code class} that is used by the
 * <code>Company</code> bean {@code class}, and the <code>Customer</code> bean
 * {@code class}.
 * 
 * @author Or Mizrahi
 * @author Shay Yadin
 * @author Jonathan Kaspi
 * @see beans#Company
 * @see beans#Customer
 */
public final class Coupon {

	/* attributes */
	private int id;
	private int companyId;
	private CategoryUtils category;
	private String title;
	private String description;
	private Date startDate;
	private Date endDate;
	private int amount;
	private double price;
	private String image;

	/* constructors */
	/**
	 * <code>Coupon</code> constructor for the <code>Connector</code> <code>createCoupons</code> method.
	 * 
	 * @see dao#dbdao#Connector
	 */
	public Coupon(int id, int companyId, CategoryUtils category, String title, String description, Date startDate,
			Date endDate, int amount, double price, String image) {
		this.id = id;
		this.companyId = companyId;
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	/**
	 * <code>Coupon</code> constructor for the <code>CompanyFacade</code> <code>updateCoupon</code> method.
	 * 
	 * @see facade#CompanyFacade
	 */
	public Coupon(int id, CategoryUtils category, String title, String description, Date startDate, Date endDate,
			int amount, double price, String image) {
		this.id = id;
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	/**
	 * <code>Coupon</code> constructor for the <code>CompanyFacade</code> <code>addCoupon</code> method.
	 * 
	 * @see facade#CompanyFacade
	 */
	public Coupon(CategoryUtils category, String title, String description, Date startDate, Date endDate, int amount,
			double price, String image) {
		this.category = category;
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.price = price;
		this.image = image;
	}

	/* getters & setters */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public CategoryUtils getCategory() {
		return category;
	}

	public void setCategory(CategoryUtils category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	/* toString */
	@Override
	public String toString() {
		return "Coupon [id=" + id + ", companyID=" + companyId + ", category=" + category + ", title=" + title
				+ ", description=" + description + ", startDate=" + startDate + ", endDate=" + endDate + ", amount="
				+ amount + ", price=" + price + ", image=" + image + "]";
	}

	/* equals & hashCode */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Coupon)
			return this.id == ((Coupon) obj).id;
		return false;
	}

	@Override
	public int hashCode() {
		return id;
	}
	
}