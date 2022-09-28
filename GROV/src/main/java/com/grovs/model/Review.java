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
	private String id;
	@Column(name="users_id",nullable=false)
	private String userId;
	@Column(name="products_id",nullable=false)
	private String productId;
	@Column(name="users_name",nullable=false,length=50)
	private String userName;
	@Column(name="review")
	private String review;
	@Column(name="rating",nullable=false,columnDefinition="decimal(2,1)")
	private double rating;
	@Column(name="updated_time",insertable=false,updatable=false,columnDefinition="datetime default current_timestamp on update current_timestamp")
	private LocalDateTime updatedTime;
	
	
	@Override
	public String toString() {
		return "Review [id=" + id + ", userId=" + userId + ", productId=" + productId + ", userName=" + userName
				+ ", review=" + review + ", rating=" + rating + ", updatedTime=" + updatedTime + "]";
	}
	public LocalDateTime getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(LocalDateTime updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
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
