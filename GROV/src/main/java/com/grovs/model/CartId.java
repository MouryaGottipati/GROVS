package com.grovs.model;

import java.io.Serializable;
import java.util.Objects;

public class CartId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String userId;
	private Product product;
	public CartId(String cartId, String userId, Product product) {
		super();
		this.id = cartId;
		this.userId = userId;
		this.product = product;
	}
	public CartId() {
		super();
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, product, userId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartId other = (CartId) obj;
		return Objects.equals(id, other.id) && Objects.equals(product, other.product)
				&& Objects.equals(userId, other.userId);
	}
	
	
}
