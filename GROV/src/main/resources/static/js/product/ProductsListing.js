$(function() {
	const params = new URLSearchParams(location.search);
	if(params.has('catId')){
	var categoryId = params.get('catId');
	printsubCategoryProductItems(categoryId);
	}
	if(params.has('rootCatId')){
	var rootCategoryId=params.get("rootCatId");
	printProductItems(rootCategoryId)
	}
});
	
	const getRootCategoryProducts= async (id)=>{
	const response = await fetch('http://localhost:9090/getRootCategoryProducts/'+id);
	const myJson =await response.json();
	return myJson;
}
const getSubCategoryProducts= async (id)=>{
	const response = await fetch('http://localhost:9090/getSubCategoryProducts/'+id);
	const myJson =await response.json();
	return myJson;
}
	/*const createEle=(element, id, classname)=>{
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
	
*/	//const fetchSingleProductDetails=(productId)=>{
		//window.location.href = "http://localhost:9090/product/product_details.html?productId="+productId;
	//}

	const createProductItem= (productId,discountInput,image,name,base_priceInput,sale_priceInput,rating)=>{
		let li=createEle('li','product-list','product-list');
		let product_discount_div=createEle('div','product-discount-div','product-discount-div');
		let discount=createDiscount('button','discount','discount',discountInput);
		let select_product_block=createEle('div','select-product-block','select-product-block');
		
		//To fetch single product
		select_product_block.setAttribute('onClick','fetchSingleProductDetails("'+productId+'")');
		
		let product_image_div=createEle('div','product-image-div','product-image-div');
		let product_image=createImage('img','product-image','product-image',200,150,image);
		let product_name_div=createEle('div','product-name-div','product-name-div');
		
		let product_name=createNameEle('h5','product-name','product-name',name);
		
		let price_cart_block=createEle('div','price-cart-block','price-cart-block');
		let price_details_div=createEle('div','price-details-div','price-details-div');
		let mrp=createEle('h6','mrp','mrp');
		mrp.appendChild(document.createTextNode('MRP:'))

		let base_price_div=createEle('div','base-price-div','base-price-div');
		let base_price_del=createEle('del','base-price-del','base-price-del');
		let base_price_data=createEle('span','base-price-data','base-price-data');
		base_price_data.appendChild(document.createTextNode('Rs '));
		let base_price=createPriceEle('span','base-price','base-price',base_priceInput);
		
		let sale_price_data=createEle('h5','sale-price-data','sale-price-data');
		
		let sale_price_rs=createEle('span','sale-price-rs','sale-price-rs');
		sale_price_rs.appendChild(document.createTextNode(' Rs '))
		
		let sale_price=createPriceEle('span','sale-price','sale-price',sale_priceInput);
		
		
		 
		let cart=createEle('div','cart','cart');
		let subtract_button=createButtonEle('button','subtract-button','subtract-button','-');
		subtract_button.setAttribute('onClick','subtractQuantity()')
		
		let quantity=inputElement('input','quantity','quantity','text',3,1);
		
		let addition_button=createButtonEle('button','addition-button','addition-button','+');
		addition_button.setAttribute('onClick','addQuantity()')
		
		let add_to_cart_button=createButtonEle('button','add-to-cart-button','add-to-cart-button','ADD');
		
		//Add to cart
		add_to_cart_button.setAttribute('onClick',"addToCart('"+productId+"')")
		
		subtract_button.classList.add('cart-element');
		quantity.classList.add('cart-element');
		addition_button.classList.add('cart-element')
		add_to_cart_button.classList.add('cart-element');
		
		product_discount_div.appendChild(discount);
		
		product_image_div.appendChild(product_image);
		product_name_div.appendChild(product_name);
		select_product_block.appendChild(product_image_div);
		select_product_block.appendChild(product_name_div);
		
		base_price_data.appendChild(base_price);
		base_price_del.appendChild(base_price_data);
		base_price_div.appendChild(base_price_del);
		
		sale_price_rs.appendChild(sale_price);
		sale_price_data.appendChild(sale_price_rs);
		
		
		price_details_div.appendChild(mrp);
		price_details_div.appendChild(base_price_div);
		price_details_div.appendChild(sale_price_data);
		if(rating>0.0){
		let rating_block=createEle('div','rating-block','rating-block');
		let rating_icon=createImage('img','rating-icon','rating-icon',15,15,'http://localhost:9090/images/RatingIcon/rating_icon.png');
		let ratings=createEle('h5','rating','rating');
		ratings.innerHTML=rating.toFixed(1);
		rating_block.appendChild(rating_icon);
		rating_block.appendChild(ratings);
		price_details_div.appendChild(rating_block);
		}
		
		
		cart.appendChild(subtract_button);
		cart.appendChild(quantity);
		cart.appendChild(addition_button);
		cart.appendChild(add_to_cart_button);
		
		
		
		price_cart_block.appendChild(price_details_div);
		price_cart_block.appendChild(cart);
		
		li.appendChild(product_discount_div);
		li.appendChild(select_product_block);
		li.appendChild(price_cart_block);	
		
		return li;
	};
	
	 const printProductItems=async (id)=>{
		//clean already existed data
				
		let myJson= await getRootCategoryProducts(id);
		var bodyRef = document.getElementById('products-table'); 
	  while(bodyRef.hasChildNodes()){
		  bodyRef.removeChild(bodyRef.firstChild);
	  }
		
		let products_table=document.getElementById('products-table');
		let numberOfRow=0;
		let numberOfCell=0;
		document.getElementById('category-name').innerHTML=myJson[0][1];
		console.log(myJson);
		let row=products_table.insertRow(numberOfRow);
		let cell;
		for(let i=0;i<myJson.length;i++){
			
			
			cell=row.insertCell(numberOfCell++)
			let li= createProductItem(myJson[i][0],myJson[i][2],myJson[i][3],myJson[i][4],myJson[i][5],myJson[i][6],myJson[i][7]);
			cell.append(li);
			if(numberOfCell==5){
				row.insertRow(++numberOfRow);
				numberOfCell=0;
			}
		}
		
	}
	 const printsubCategoryProductItems=async (id)=>{
		
		let myJson= await getSubCategoryProducts(id);
		var bodyRef = document.getElementById('products-table'); 
		  while(bodyRef.hasChildNodes()){
			  bodyRef.removeChild(bodyRef.firstChild);
		  }
		
		let products_table=document.getElementById('products-table');
		let numberOfRow=0;
		let numberOfCell=0;
		document.getElementById('category-name').innerHTML=myJson[0][1];
		let row=products_table.insertRow(numberOfRow);
		let cell;
		for(let i=0;i<myJson.length;i++){
			
			cell=row.insertCell(numberOfCell);
			let li= createProductItem(myJson[i][0],myJson[i][2],myJson[i][3],myJson[i][4],myJson[i][5],myJson[i][6],myJson[i][7]);
			cell.append(li);
			if(numberOfCell==5){
				row.insert(++numberOfRow);
				numberOfCell=0;
			}
		}	
	}
