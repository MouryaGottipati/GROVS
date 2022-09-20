package com.grovs.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="reviews")
public class Review {
	@Id
	@Column(name="id")
	private long id;
	@Column(name="users_id",nullable=false)
	private long userId;
	@Column(name="products_id",nullable=false)
	private long productId;
	@Column(name="users_name",nullable=false,length=50)
	private String userName;
	@Column(name="review")
	private String review;
	@Column(name="rating",nullable=false,columnDefinition="decimal(2,1)")
	private double rating;
	@Column(name="updated_time",insertable=false,updatable=false,columnDefinition="datetime default current_timestamp on update current_timestamp")
	private LocalDateTime updatedTime;
	public LocalDateTime getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(LocalDateTime updatedTime) {
		this.updatedTime = updatedTime;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	
	
	
}
