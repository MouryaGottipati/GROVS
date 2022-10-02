const createEle=(element, id, classname)=>{
		let ele = document.createElement(element);
		ele.className = classname;
		ele.setAttribute('id', id);
		return ele;

	}
	const createButtonEle=(element, id, classname,value)=>{
		let ele = document.createElement(element);
		ele.className = classname;
		ele.setAttribute('id', id);
		var text=document.createTextNode(value)
		ele.appendChild(text)
		return ele;

	}
	const createImage=(element, id, classname, width, height, src)=>{
		let ele = document.createElement(element);
		ele.className = classname;
		ele.setAttribute('src', src)
		ele.setAttribute('id', id);
		ele.setAttribute('width', width);
		ele.setAttribute('height', height);
		return ele;

	}
	
	const inputElement=(element, id, classname,type,size,value)=>{
		let ele = document.createElement(element);
		ele.className = classname;
		ele.setAttribute('id', id);
		ele.setAttribute('type', type);
		ele.setAttribute('size', size);
		ele.setAttribute('value', value);
		return ele;
	}
	
	const createDiscount=(element, id, classname,discount)=>{
		let ele = document.createElement(element);
		ele.className = classname;
		ele.setAttribute('id', id);
		let discountText=createEle('h5','dicount-text','discount-text');
		discountText.innerHTML='GetOff - '+discount+'%'
		ele.appendChild(discountText)
		return ele;
	}
	
	const  createNameEle=(element, id, classname,name)=>{
		let ele = document.createElement(element);
		ele.className = classname;
		ele.setAttribute('id', id);
		let text=document.createTextNode(name);
		ele.appendChild(text)
		return ele;

	}
	const createPriceEle=(element, id, classname,value)=>{
		let ele = document.createElement(element);
		ele.className = classname;
		ele.setAttribute('id', id);
		ele.innerHTML=value.toFixed(2);
		return ele;
	}
	
	function getNumber(string) {
		string = string.toLowerCase();
		if (string.charAt(0) === '0') return false; // starts with '0'
		string = Number(string);
		if (string !== string) return false; // it failed to convert
		if (string % 1 !== 0) return false; // is a decimal
		return string;
	}
	
	const subtractQuantity=()=>{
		let quantity=document.getElementById('quantity').value;
		let quantityNumber=getNumber(quantity);
		if(typeof quantityNumber==='number'){
			document.getElementById('quantity').value=quantityNumber-1;
		}
	}
	
	const addQuantity=()=>{
		let quantity=document.getElementById('quantity').value;
		let quantityNumber;
		if(Number(quantity)===0){
			quantityNumber=0;
		}
		else{
		quantityNumber=getNumber(quantity);
		}
		if(typeof quantityNumber==='number'){
			document.getElementById('quantity').value=quantityNumber+1;
		}
	}
	const addCartProduct=async (productId,quantityNumber)=>{
		const status=await fetch("http://localhost:9090/addCartProduct/"+productId+"/"+quantityNumber);
		const myJson=await status.json();
	}
	const addToCart=(productId)=>{
		let quantity=document.getElementById('quantity').value;
		let quantityNumber=getNumber(quantity);
		if(typeof quantityNumber==='number' && quantityNumber>0){
			addProductToCart(productId,quantityNumber)
		}else{
			alert("Enter valid data in input");
		}
	}
	
	
const addProductToCart = async (productId, quantity) => {
	console.log(quantity)
	const responseEntity = await fetch(URI = "http://localhost:9090/addProductToCart/" + productId + "/" + quantity, { method: "POST" });

	const cartJson = await responseEntity.json();

	document.getElementById("numberOfItems").innerHTML = cartJson["cartItems"].length + " items";
}
const displayCart = async () => {
	document.getElementById("cart-display").style.display = "block";
	const responseEntity = await fetch("http://localhost:9090/getCartProducts");
	const cartJson = await responseEntity.json();
	//console.log("cartJson"+cartJson)
	printCart(cartJson);
}
const removeProductQuantity=async (cartItemId)=>{
	await fetch("http://localhost:9090/removeProductQuantity/"+cartItemId,{method:"POST"});
	await displayCart();
}

const addProductQuantity=async (cartItemId)=>{
	await fetch("http://localhost:9090/addProductQuantity/"+cartItemId,{method:"POST"});
	await displayCart();
}
const removeProduct=async (cartItemId)=>{
	await fetch("http://localhost:9090/removeProduct/"+cartItemId,{method:"POST"});
	await displayCart();
}
const printCart = (cartJson) => {
	//console.log(cartJson)
	if (cartJson == null|| cartJson["cartItems"]==null || cartJson["cartItems"].length == 0) {
		document.getElementById("numberOfItems").innerHTML = 0 + " items";
		document.getElementById("cart-items").style.display="none";
		document.getElementById("no-items").style.display = "flex";
	} else {
		document.getElementById("numberOfItems").innerHTML = cartJson["cartItems"].length + " items";
		document.getElementById("no-items").style.display = "none";
		let cartItems=document.getElementById("cart-items");
		while(cartItems.hasChildNodes()){
			cartItems.removeChild(cartItems.firstChild);
		}
		document.getElementById("cart-block").setAttribute("onMouseOver","none()");
		document.getElementById("cart-items").style.display = "block";
		document.getElementById("check-out").style.display = "inline-block";
		for (let i = 0; i < cartJson["cartItems"].length; i++) {
			const cart_ul = createEle("ul", "cart-product", "cart-product");
			const cart_image_li=createEle("li","cart-product-image-li","cart-product-image-li");
			const cart_name_quantity_li=createEle("li","cart-product-name-li","cart-product-name-li");
			const cart_product_modify_li=createEle("li","cart-product-modify-li","cart-product-modify-li");
			const cart_product_price_li=createEle("li","cart-product-price-li","cart-product-price-li");
			const cart_product_delete_li=createEle("li","cart-product_delete-li","cart-product_delete-li");
			const cart_product_image=createImage("img","cart-product-image","cart-product-image",50,50,cartJson["cartItems"][i]["product"]["image"]);
			const cart_product_name=createNameEle("span","cart-product-name","cart-product-name",cartJson["cartItems"][i]["product"]["name"]);
			const cart_product_quantity_price=createNameEle("span","cart-product-quantity-price","cart-product-quantity-price",cartJson["cartItems"][i]["quantity"]+"x"+(cartJson["cartItems"][i]["product"]["basePrice"]*(1-0.01*cartJson["cartItems"][i]["product"]["discount"])).toFixed(2));
			
			const cart_subtract_button=createNameEle("button","cart-subtract-button","cart-subtract-button","-")
			cart_subtract_button.setAttribute("onClick","removeProductQuantity('"+cartJson["cartItems"][i]["id"]+"')");
			
			const cart_product_quantity=createNameEle("span","cart-product-quantity","cart-product-quantty",cartJson["cartItems"][i]["quantity"])
			const cart_addition_button=createNameEle("button","cart-addition-button","cart-addition-button","+");
			cart_addition_button.setAttribute("onClick","addProductQuantity('"+cartJson["cartItems"][i]["id"]+"')");
			
			const cart_product_price=createNameEle("span","cart-product-price","cart-product-price","Rs "+(cartJson["cartItems"][i]["product"]["basePrice"]*(1-0.01*cartJson["cartItems"][i]["product"]["discount"])).toFixed(2));
			const cart_saved_amount=createNameEle("span","cart-saved-amount","cart-saved-amount","Saved Rs."+(cartJson["cartItems"][i]["product"]["basePrice"]*(0.01*cartJson["cartItems"][i]["product"]["discount"])).toFixed(2));
			
			const cart_delete_product=createNameEle("button","cart-delete-product","cart-delete-product","x");
			cart_delete_product.setAttribute("onClick","removeProduct('"+cartJson["cartItems"][i]["id"]+"')");
			
			cart_image_li.appendChild(cart_product_image);
			
			cart_name_quantity_li.appendChild(cart_product_name);
			cart_name_quantity_li.appendChild(cart_product_quantity_price);
			
			cart_product_modify_li.appendChild(cart_subtract_button);
			cart_product_modify_li.appendChild( cart_product_quantity);
			cart_product_modify_li.appendChild(cart_addition_button);
			
			cart_product_price_li.appendChild(cart_product_price)
			cart_product_price_li.appendChild(cart_saved_amount)
			
			cart_product_delete_li.appendChild(cart_delete_product )
			
			
			cart_ul.appendChild(cart_image_li);
			cart_ul.appendChild(cart_name_quantity_li);
			cart_ul.appendChild(cart_product_modify_li);
			cart_ul.appendChild(cart_product_price_li);
			cart_ul.appendChild(cart_product_delete_li);
			document.getElementById("cart-items").appendChild(cart_ul);
			
		}
		
	}


}
const hideCart = () => {
	document.getElementById("cart-block").setAttribute("onMouseOver","displayCart()");
	document.getElementById("cart-display").style.display = "none";
}
const none=()=>{
	
}