
package com.grovs.service;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grovs.dao.ICartDao;
import com.grovs.dao.ICartItemsDao;
import com.grovs.dao.IProductDao;
import com.grovs.model.Cart;
import com.grovs.model.CartItems;
import com.grovs.model.Product;

@Service
public class CartService implements ICartService {

	@Autowired
	private ICartDao cartDaoObject;

	@Autowired
	private IProductDao productDaoObject;

	@Autowired
	private ICartItemsDao cartItemDaoObject;

	public Cart addProductToCart(String productId, int quantity, HttpServletRequest request,HttpServletResponse response) { // TODO Auto-generated
		HttpSession session=request.getSession(false);																	// HttpSession
		session = request.getSession();
		
		  if(session==null) { new SessionService().cartCookie(request,response);
		  }
		 
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart!=null) {
		CartItems cartItem = new CartItems();
		cartItem.setCartId(cart.getId());
		cartItem.setUpdateTime(LocalDateTime.now());
		System.out.println(productId);
		Product product = productDaoObject.findById(productId).orElse(new Product());
		cartItem.setProduct(product);
		System.out.println(product);
		Set<CartItems> cartItemsSet = new LinkedHashSet<>();
		if (cart.getCartItems()!=null && cart.getCartItems().size() > 0) {
			Set<CartItems> tempCart = cart.getCartItems();
			if (tempCart.stream().anyMatch(
					p -> p.getCartId().equals(cart.getId()) && p.getProduct().getId().equals(productId))) {
				cart.getCartItems().stream().filter(p -> p.getProduct().getId().equals(productId)).forEach(p -> {
					p.setQuantity(quantity);
					cartItem.setId(p.getId());
					cartItem.setQuantity(quantity);
				});
				cartItemDaoObject.save(cartItem);
				
			} else {
				cartItem.setQuantity(quantity);
				CartItems newCartItem = cartItemDaoObject.save(cartItem);
				cartItemsSet.addAll(cart.getCartItems());
				cartItemsSet.add(newCartItem);
				cart.setCartItems(cartItemsSet);
			}
		} else {
			cartItem.setQuantity(quantity);
			CartItems newCartItem = cartItemDaoObject.save(cartItem);
			cartItemsSet.add(newCartItem);
			cart.setCartItems(cartItemsSet);
		}
		cart.setUpdatedTime(LocalDateTime.now());
		cartDaoObject.save(cart);
		session.setAttribute("cart", cart);
		
	}
	else {
		
		SessionService sessionService=new SessionService();
		sessionService.cartCookie(request, response);
		addProductToCart(productId,quantity,request,response);
	}
		return cart;
	}

	public Cart getCartProducts(HttpServletRequest request, HttpSession session) { 
	System.out.println("getCartProduct " + session.getAttribute("cart"));
		return (Cart) session.getAttribute("cart");
	}

	public  void removeProductQuantity(String cartItemId,HttpServletRequest request) {
		HttpSession session=request.getSession();
		Cart cart=(Cart) session.getAttribute("cart");
		cart.getCartItems().stream().filter(p->p.getId().equals(cartItemId))
		.forEach(p->{if(p.getQuantity()==1){
		cartItemDaoObject.deleteById(p.getId());
		cart.getCartItems().remove(p);
		}else { p.setQuantity(p.getQuantity()-1); 
		p.setUpdateTime(LocalDateTime.now());
		cartItemDaoObject.save(p);
		}});
	
		Cart sessionCart=cartDaoObject.findById(cart.getId()).orElse(new Cart());
		System.out.println(sessionCart);
		session.setAttribute("cart", sessionCart);
	}

	public void addProductQuantity(String cartItemId, HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(false);
		Cart cart=(Cart) session.getAttribute("cart");
		cart.getCartItems().parallelStream().filter(p->p.getId().equals(cartItemId))
		.forEach(p->{p.setQuantity(p.getQuantity()+1); 
		p.setUpdateTime(LocalDateTime.now());
		cartItemDaoObject.save(p);
		});
		session.setAttribute("cart", cart);
	}

	public void removeProduct(String cartItemId, HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession(false);
		Cart cart=(Cart) session.getAttribute("cart");
		
			
		cart.getCartItems().parallelStream().filter(p->p.getId().equals(cartItemId))
		.forEach(p->{cart.getCartItems().remove(p);
		cartItemDaoObject.deleteById(p.getId());
		});
		
		Cart sessionCart=cartDaoObject.findById(cart.getId()).orElse(new Cart());
		System.out.println(sessionCart);
		session.setAttribute("cart", sessionCart);
	}
}
