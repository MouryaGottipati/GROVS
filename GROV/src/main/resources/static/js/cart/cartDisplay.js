
$(async function(){
	$(".header-file").load("http://localhost:9090/header/header.html")
	$(".offer-deals").load("http://localhost:9090/offersdeals/deals.html")
	$(".category-dropdown").load("http://localhost:9090/category/category_dropdown.html")
	$(".offers-button").load("http://localhost:9090/offersdeals/offer_button.html")
	await cartDisplay();
})

const cartDisplay=async ()=>{
	let cartJson ;
	try{
	const responseEntity = await fetch("http://localhost:9090/getCartProducts");
	
	cartJson= await responseEntity.json();
	}catch{
		console.log(Error)
		console.log(cartJson)
	}
		document.getElementById("numberOfItems").innerHTML = cartJson["cartItems"].length + " items";
	 await printBasket(cartJson);
}

const printBasket=async (cartJson)=>{
	

	console.log(cartJson)
	if (cartJson == null|| cartJson["cartItems"]==null || cartJson["cartItems"].length == 0) {
		document.getElementById("basket-empty-block").style.display="block"
		document.getElementById("basket-block").style.display="none";
		document.getElementById("checkout-block").style.display = "none";
	} else {
		// removeExisting();
		
		const tBodyTag=document.getElementById("basket-body");
	document.getElementById("basket-title").innerHTML = "Your Basket ( "+cartJson["cartItems"].length+ " items )";
	while((tBodyTag!=undefined || tBodyTag!=null)&& tBodyTag.hasChildNodes()){
		tBodyTag.removeChild(tBodyTag.firstChild);
	}
		let subTotal=0;
		document.getElementById("basket-empty-block").style.display="none"
		document.getElementById("basket-block").style.display="block";
		document.getElementById("checkout-block").style.display = "block";
		const tbody=document.getElementById("basket-table").getElementsByTagName('tbody')[0];
		

		for(let i=0;i<cartJson["cartItems"].length;i++){
			let row=tbody.insertRow();
			row.setAttribute("class","data-row")
			
			let productName=createNameEle("span","basket-product-name","basket-product-name",cartJson["cartItems"][i]["product"]["name"]);
			productName.setAttribute("onClick",'fetchSingleProductDetails("'+cartJson["cartItems"][i]["product"]["id"]+'")');
			let cell1=row.insertCell(0);
			cell1.appendChild(productName);
			cell1.setAttribute("class","cell1")
			
			
			let finalPriceValue=cartJson["cartItems"][i]["product"]["basePrice"]*(1-0.01*cartJson["cartItems"][i]["product"]["discount"]+0.01*cartJson["cartItems"][i]["product"]["gst"]);
			
			let finalPrice=createNameEle("span","basket-product-final-price","basket-product-final-price","Rs."+(finalPriceValue).toFixed(2));
			
			let del=createEle("del","strikeof-base-price","strikeof-base-price");
			
			let basePriceValue=cartJson["cartItems"][i]["product"]["basePrice"];
			let basePrice=createNameEle("span","basket-product-base-price","basket-product-base-price","Rs."+(basePriceValue).toFixed(2));
			
			del.appendChild(basePrice)
			
			let cell2=row.insertCell(1);
			
			cell2.appendChild(finalPrice);
			del.appendChild(basePrice);
			cell2.appendChild(del);
			cell2.setAttribute("class","cell2")
			
			let subtractButton=createNameEle("button","basket-product-subtract-button","basket-product-subtract-button","-");
			subtractButton.setAttribute("onClick","subtractQuantityByOne('"+cartJson["cartItems"][i]["id"]+"')");
			
			
			let quantity=createEle("input","basket-product-quantity","basket-product-quantity")
			quantity.setAttribute("value",cartJson["cartItems"][i]["quantity"]);
			quantity.setAttribute("type","text")
			quantity.setAttribute("inputMode","numeric");
			quantity.disabled=true;
			
			let addButton=createNameEle("button","basket-product-add-button","basket-product-add-button","+")
			addButton.setAttribute("onClick","addQuantityByOne('"+cartJson["cartItems"][i]["id"]+"')");
			
			
			let cell3=row.insertCell(2);
			cell3.appendChild(subtractButton)
			cell3.appendChild(quantity)
			cell3.appendChild(addButton)
			cell3.setAttribute("class","cell3")
			
			let quantityValue=cartJson["cartItems"][i]["quantity"];
			
			let totalPriceOfProduct=createNameEle("span","total-price-product","total-price-product","Rs."+(finalPriceValue*quantityValue).toFixed(2))
			let cell4=row.insertCell(3);
			cell4.appendChild(totalPriceOfProduct)
			cell4.setAttribute("class","cell4")
			
			let removeProduct=createNameEle("button","basket-product-remove-button","basket-product-remove-button","X")
			removeProduct.setAttribute("onClick","removeProductFromBasket('"+cartJson["cartItems"][i]["id"]+"')");
			let cell5=row.insertCell(4);
			cell5.appendChild(removeProduct);
			cell5.setAttribute("class","cell5");
			
			let totalSaved=createNameEle("span","basket-product-saved-amount","basket-product-saved-amount","Rs."+((basePriceValue-finalPriceValue)*quantityValue).toFixed(2));
			let cell6=row.insertCell(5);
			cell6.appendChild(totalSaved);
			cell6.setAttribute("class","cell6");
			subTotal+=finalPriceValue*quantityValue;
		}
		document.getElementById("checkout-subtotal-value").innerHTML="Rs."+(subTotal).toFixed(2);
		document.getElementById("total-amount-value").innerHTML="Rs."+(subTotal+50).toFixed(2);
	}
	
}

const removeExisting=async ()=>{
	
	const tbody=document.getElementById("basket-body");
	document.getElementById("numberOfItems").innerHTML = 0 + " items";
	while((tbody!=undefined || tbody!=null)&& tbody.hasChildNodes()){
		tbody.removeChild(tbody.firstChild);
	}
}

const subtractQuantityByOne=async (cartItemId)=>{
	console.log("In subtract")
	await fetch("http://localhost:9090/removeProductQuantity/"+cartItemId,{method:"POST"});
	await cartDisplay();
}

const addQuantityByOne=async (cartItemId)=>{
	await fetch("http://localhost:9090/addProductQuantity/"+cartItemId,{method:"POST"});
	 await cartDisplay();
}
const removeProductFromBasket =async (cartItemId)=>{
	console.log("In remove")
	await fetch("http://localhost:9090/removeProduct/"+cartItemId,{method:"POST"});
	await  cartDisplay();
}
const goToCheckOutPage=()=>{
	window.location.href="http://localhost:9090/checkout/checkout.html"
}