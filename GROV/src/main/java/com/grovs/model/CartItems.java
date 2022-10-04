package com.grovs.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.grovs.sequenceIdGenerator.SequenceIdGenerator;

@Entity
@Table(name="cart_items")
public class CartItems {

	@Id
	
	@Column(name = "id", nullable = false)
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="cart_seq")
	
	@GenericGenerator(name="cart_seq",
	strategy="com.grovs.sequenceIdGenerator.SequenceIdGenerator", parameters= {
	
	@Parameter(name=SequenceIdGenerator.INCREMENT_PARAM,value="1"),
	
	@Parameter(name=SequenceIdGenerator.VALUE_PREFIX_PARAMETER,value="CI"),
	
	@Parameter(name=SequenceIdGenerator.NUMBER_FORMAT_PARAMETER,value="%01d") } )
	
	private String id;
	
	
	@Column(name="cart_id")
	private String cartId;
	
	@ManyToOne
	@JoinColumn(name="product_id",referencedColumnName="id")
	private Product product;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="updated_time",columnDefinition ="datetime default current_timestamp on update current_timestamp")
	private LocalDateTime updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCartId() {
		return cartId;
	}

	public LocalDateTime getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartItems [id=" + id + ", cartId=" + cartId + ", product=" + product + ", quantity=" + quantity + "]";
	}

	
	
	
	
}
