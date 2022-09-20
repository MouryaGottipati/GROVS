package com.grovs.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Product {
	@Id
	@Column(name="id")
	private long id;
	@Column(name="name",nullable=false)
	private String name;
	@Column(name="description",nullable=false)
	private String description;
	@Column(name="image",nullable=false,length=64)
	private String image;
	@Column(name="base_price",nullable=false)
	private double basePrice;
	@Column(name="discount",columnDefinition="int  default 0")
	private double discount;
	@Column(name="gst",columnDefinition="decimal(4,2)  default 0")
	private double gst;
	@Column(name="expiry_date",nullable=false)
	private LocalDate expiryDate;
	@Column(name="return_period",columnDefinition="int not null default 0")
	private int returnPeriod;
	@Column(name="stock",columnDefinition="bigint not null default 0")
	private long stock;
	@Column(name="category_id",nullable=false)
	private long categoryId;
	@Column(name="updated_time",insertable=false,updatable=false,columnDefinition="datetime default current_timestamp on update current_timestamp")
	private LocalDateTime updatedTime;
	@OneToMany
	@JoinColumn(name="products_id")
	private Set<Review> review;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String img) {
		this.image = image;
	}
	public double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getGst() {
		return gst;
	}
	public void setGst(double gst) {
		this.gst = gst;
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	public int getReturnPeriod() {
		return returnPeriod;
	}
	public void setReturnPeriod(int returnPeriod) {
		this.returnPeriod = returnPeriod;
	}
	public long getStock() {
		return stock;
	}
	public void setStock(long stock) {
		this.stock = stock;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public Set<Review> getReview() {
		return review;
	}
	public void setReview(Set<Review> review) {
		this.review = review;
	}
	
	
	
	
}
