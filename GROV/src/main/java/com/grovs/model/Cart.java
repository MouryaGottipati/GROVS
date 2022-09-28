package com.grovs.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.grovs.sequenceIdGenerator.SequenceIdGenerator;

@Entity
@Table(name = "cart")
public class Cart {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="cart_seq")
	@GenericGenerator(name="cart_seq",
		strategy="com.grovs.sequenceIdGenerator.SequenceIdGenerator",
		parameters= {
				 @Parameter(name=SequenceIdGenerator.INCREMENT_PARAM,value="1"),
                 @Parameter(name=SequenceIdGenerator.VALUE_PREFIX_PARAMETER,value="C"),
                 @Parameter(name=SequenceIdGenerator.NUMBER_FORMAT_PARAMETER,value="%05d")
		}
	)
	private String id;

	@Column(name = "users_id")
	private String userId;

	@ManyToMany(fetch=FetchType.EAGER)
	@Column(name="products_id",nullable=false)
	private Set<Product> productId;

	@Column(name = "count")
	private int count;

	public String getId() {
		return id;
	}

	public String getUser() {
		return userId;
	}

	public Set<Product> getProductId() {
		return productId;
	}

	public int getCount() {
		return count;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUser(String userId) {
		this.userId = userId;
	}

	public void setProductId(Set<Product> productId) {
		this.productId = productId;
	}

	public void setCount(int count) {
		this.count = count;
	}

	
}
