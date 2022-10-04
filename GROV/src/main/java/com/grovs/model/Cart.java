package com.grovs.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.grovs.enums.CartUserType;
import com.grovs.sequenceIdGenerator.SequenceIdGenerator;

@Entity
@Table(name = "cart")
public class Cart {
	@Id

	@Column(name = "id", nullable = false)

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq")

	@GenericGenerator(name = "cart_seq", strategy = "com.grovs.sequenceIdGenerator.SequenceIdGenerator", parameters = {

			@Parameter(name = SequenceIdGenerator.INCREMENT_PARAM, value = "1"),

			@Parameter(name = SequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "C"),

			@Parameter(name = SequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%01d") })

	private String id;

	@Column(name = "creation_time", columnDefinition = "DateTime default current_timestamp")
	private LocalDateTime creationTime;
	@Column(name = "updated_time", columnDefinition = "DateTime default current_timestamp on update current_timestamp")
	private LocalDateTime updatedTime;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_type")
	private CartUserType userType;

	@OneToMany(mappedBy="cartId",fetch=FetchType.EAGER)
	private Set<CartItems> cartItems;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	public LocalDateTime getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(LocalDateTime updatedTime) {
		this.updatedTime = updatedTime;
	}

	public CartUserType getUserType() {
		return userType;
	}

	public void setUserType(CartUserType userType) {
		this.userType = userType;
	}

	public Set<CartItems> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItems> cartItems) {
		this.cartItems = cartItems;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", creationTime=" + creationTime + ", updatedTime=" + updatedTime + ", userType="
				+ userType + ", cartItems=" + cartItems + ", getId()=" + getId() + ", getCreationTime()="
				+ getCreationTime() + ", getUpdatedTime()=" + getUpdatedTime() + ", getUserType()=" + getUserType()
				+ ", getCartItems()=" + getCartItems() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	
	/*
	 * @Override public String toString() { return "Cart [id=" + id +
	 * ", creationTime=" + creationTime + ", updatedTime=" + updatedTime +
	 * ", userType=" + userType + ", cartItems=" + cartItems + "]"; }
	 */
	
	

	

}
/*
 * import java.time.LocalDateTime; import java.util.Map; import java.util.Set;
 * 
 * import javax.persistence.Column; import javax.persistence.Entity; import
 * javax.persistence.FetchType; import javax.persistence.ForeignKey; import
 * javax.persistence.GeneratedValue; import javax.persistence.GenerationType;
 * import javax.persistence.Id; import javax.persistence.IdClass; import
 * javax.persistence.JoinColumn; import javax.persistence.ManyToMany; import
 * javax.persistence.ManyToOne; import javax.persistence.OneToOne; import
 * javax.persistence.Table;
 * 
 * import org.hibernate.annotations.GenericGenerator; import
 * org.hibernate.annotations.Parameter;
 * 
 * import com.grovs.sequenceIdGenerator.SequenceIdGenerator;
 * 
 * @Entity
 * 
 * @Table(name = "cart")
 * 
 * @IdClass(CartId.class) public class Cart {
 * 
 * @Id
 * 
 * @Column(name = "id", nullable = false)
 * 
 * @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="cart_seq")
 * 
 * @GenericGenerator(name="cart_seq",
 * strategy="com.grovs.sequenceIdGenerator.SequenceIdGenerator", parameters= {
 * 
 * @Parameter(name=SequenceIdGenerator.INCREMENT_PARAM,value="1"),
 * 
 * @Parameter(name=SequenceIdGenerator.VALUE_PREFIX_PARAMETER,value="C"),
 * 
 * @Parameter(name=SequenceIdGenerator.NUMBER_FORMAT_PARAMETER,value="%01d") } )
 * 
 * private String id;
 * 
 * @Id
 * 
 * @Column(name = "users_id") private String userId;
 * 
 * @Id
 * 
 * @ManyToOne(fetch=FetchType.EAGER)
 * 
 * @JoinColumn(name="products_id",referencedColumnName="id") private Product
 * product;
 * 
 * @Column(name="quantity",nullable=false) private int quantity;
 * 
 * @Column(name="updated_time",
 * columnDefinition="DateTime default current_timestamp on update current_timestamp"
 * ) private LocalDateTime updatedTime;
 * 
 * public String getId() { return id; }
 * 
 * public void setId(String id) { this.id = id; }
 * 
 * public String getUserId() { return userId; }
 * 
 * public void setUserId(String userId) { this.userId = userId; }
 * 
 * public Product getProduct() { return product; }
 * 
 * public void setProduct(Product product) { this.product = product; }
 * 
 * public int getQuantity() { return quantity; }
 * 
 * public void setQuantity(int quantity) { this.quantity = quantity; }
 * 
 * 
 * 
 * 
 * 
 * 
 * }
 */