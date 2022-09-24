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
	const createImage=(element, id, classname, width, height, src,image)=>{
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
		console.log(quantityNumber)
		if(typeof quantityNumber==='number'){
			document.getElementById('quantity').value=quantityNumber-1;
		}
	}
	
	const addQuantity=()=>{
		let quantity=document.getElementById('quantity').value;
		let quantityNumber=getNumber(quantity);
		console.log(quantityNumber)
		if(typeof quantityNumber==='number'){
			document.getElementById('quantity').value=quantityNumber+1;
		}
	}
	
	const addToCart=()=>{
		let quantity=document.getElementById('quantity').value;
		let quantityNumber=getNumber(quantity);
		if(typeof quantityNumber==='number' && quantityNumber>0){
		}else{
			alert("Enter valid data in input");
		}
	}

	const createProductItem= (discountInput,image,name,base_priceInput,sale_priceInput,rating)=>{
		let li=createEle('li','product-list','product-list');
		let product_discount_div=createEle('div','product-discount-div','product-discount-div');
		let discount=createDiscount('button','discount','discount',discountInput);
		let select_product_block=createEle('div','select-product','select-product-block');
		let product_image_div=createEle('div','product-image-div','product-image-div');
		let product_image=createImage('img','product-image','product-image',200,150,image);
		let product_name_div=createEle('div','product-name-div','product-name-div');
		
		let product_name=createNameEle('h3','product-name','product-name',name);
		
		let price_cart_block=createEle('div','price-cart-block','price-cart-block');
		let price_details_div=createEle('div','price-details-div','price-details-div');
		let mrp=createEle('h5','mrp','mrp');
		mrp.appendChild(document.createTextNode('MRP:'))

		let base_price_div=createEle('div','base-price-div','base-price-div');
		let base_price_del=createEle('del','base-price-del','base-price-del');
		let base_price_data=createEle('span','base-price-data','base-price-data');
		base_price_data.appendChild(document.createTextNode('Rs '));
		let base_price=createNameEle('span','base-price','base-price',base_priceInput);
		
		let sale_price_data=createEle('h4','sale-price-data','sale-price-data');
		
		let sale_price_rs=createEle('span','sale-price-rs','sale-price-rs');
		sale_price_rs.appendChild(document.createTextNode(' Rs '))
		
		let sale_price=createNameEle('span','sale-price','sale-price',sale_priceInput);
		
		
		 
		let cart=createEle('div','cart','cart');
		let subtract_button=createButtonEle('button','subtract-button','subtract-button','-');
		subtract_button.setAttribute('onClick','subtractQuantity()')
		
		let quantity=inputElement('input','quantity','quantity','text',3,1);
		
		let addition_button=createButtonEle('button','addition-button','addition-button','+');
		addition_button.setAttribute('onClick','addQuantity()')
		
		let add_to_cart_button=createButtonEle('button','add-to-cart-button','add-to-cart-button','ADD');
		add_to_cart_button.setAttribute('onClick','addToCart()')
		
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
		console.log(rating)
		if(rating>0.0){
		let rating_block=createEle('div','rating-block','rating-block');
		let rating_icon=createImage('img','rating-icon','rating-icon',15,15,'http://localhost:9090/images/RatingIcon/rating_icon.png');
		let ratings=createEle('h5','rating','rating');
		console.log(rating)
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
	
	const printProductItems=  ()=>{
		let li=  createProductItem(20,'http://localhost:9090/images/Fruits&Vegetables/Fruits/pomegranate_4pcs.jpg','pomegranate_4pcs.jpg',50,40,4.0);
		let products_table=document.getElementById('products-table');
		let row=products_table.insertRow(0);
		let cell=row.insertCell(0);
		cell.appendChild(li);
	}