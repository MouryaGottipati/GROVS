
package com.grovs.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grovs.model.Cart;
import com.grovs.model.CartItems;
import com.grovs.service.CartService;

@RestController
public class CartController {

	@Autowired
	private CartService cartServiceObject;

	@PostMapping("addProductToCart/{productId}/{quantity}")
	public ResponseEntity<Cart> addProductToCart(@PathVariable("productId") String productId, @PathVariable("quantity") int quantity,
			HttpServletRequest request,HttpServletResponse response) {
		
		return ResponseEntity.status(200).body(cartServiceObject.addProductToCart(productId, quantity, request,response));
	}
	// return new ResponseEntity<>(HttpStatus.ACCEPTED); }

	@GetMapping("getCartProducts")
	public ResponseEntity<Cart> getCartProducts(HttpServletRequest request, HttpSession session) {
		return ResponseEntity.status(HttpStatus.OK).body(cartServiceObject.getCartProducts(request, session));
	}
	
	@PostMapping("removeProductQuantity/{cartItemId}")
	public ResponseEntity<String> removeProductQuantity(@PathVariable("cartItemId") String cartItemId,HttpServletRequest request) {
		cartServiceObject.removeProductQuantity(cartItemId,request);
		return   ResponseEntity.ok("SuccessFull");
	}
	
	@PostMapping("addProductQuantity/{cartItemId}")
	public ResponseEntity<String> addProductQuantity(@PathVariable("cartItemId") String cartItemId,HttpServletRequest request) {
		cartServiceObject.addProductQuantity(cartItemId,request);
		return   ResponseEntity.ok("SuccessFull");
	}
	
	@PostMapping("removeProduct/{cartItemId}")
	public ResponseEntity<String> removeProduct(@PathVariable("cartItemId") String cartItemId,HttpServletRequest request) {
		cartServiceObject.removeProduct(cartItemId,request);
		return   ResponseEntity.ok("SuccessFull");
	}
}
