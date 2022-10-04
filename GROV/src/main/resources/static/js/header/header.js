
const userCheck=async ()=>{
	let cart;

		cart = await fetch("http://localhost:9090/cartCookieCheck");
		let cartJson=await cart.json();
		console.log(cartJson);
		if(cartJson["cartItems"]==null){
			document.getElementById("numberOfItems").innerHTML=0+" items";
		}else{
		document.getElementById("numberOfItems").innerHTML=cartJson["cartItems"].length+" items";
		}
	
}
const fetchSingleProductDetails=(productId)=>{
		window.location.href = "http://localhost:9090/product/product_details.html?productId="+productId;
	}

$(function(){
 userCheck();	
 	
 
});