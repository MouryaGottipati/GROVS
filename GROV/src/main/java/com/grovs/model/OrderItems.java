package com.grovs.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.grovs.sequenceIdGenerator.SequenceIdGenerator;

@Entity
@Table(name="order_items")
public class OrderItems {
	
	@Id
	@Column(name = "id",nullable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_sequence")
	@GenericGenerator(name = "order_item_sequence", strategy = "com.grovs.sequenceIdGenerator.SequenceIdGenerator", parameters = {
			@Parameter(name = SequenceIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = SequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "OI_"),
			@Parameter(name = SequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%d") })
	private String id;
	
	@ManyToOne(targetEntity = Order.class,cascade=CascadeType.ALL)
	@JoinColumn(name="orders_id",referencedColumnName="id")
	private String orderId;
	
	@Column(name="quantity",nullable=false)
	private long quantity;
	
	@Column(name="products_id",nullable=false)
	private String productId;
	
	@Column(name="base_price",nullable=false)
	private double basePrice;
	
	@Column(name="gst",nullable=false)
	private double gst;
	
	@Column(name="discount",nullable=false)
	private double discount;
	
	@Column(name="products_name",nullable=false)
	private String productName;
	
	@Column(name="category_name",nullable=false)
	private String categoryName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public double getGst() {
		return gst;
	}

	public void setGst(double gst) {
		this.gst = gst;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	
	
	
	
	

}
