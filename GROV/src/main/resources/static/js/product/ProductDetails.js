$(function(){
	$(".header-file").load("http://localhost:9090/header/header.html")
	$(".offer-deals").load("http://localhost:9090/offersdeals/deals.html")
	$(".category-dropdown").load("http://localhost:9090/category/category_dropdown.html")
	$(".offers-button").load("http://localhost:9090/offersdeals/offer_button.html")
	$(".products_listing").load("http://localhost:9090/product/products_listing.html");
});
window.onload = function() {
	const params = new URLSearchParams(location.search);
	var productId=params.get("productId");
	PrintSingleProductDetails(productId)
	
};
const fetchProductDetails = async (productId) => {
	const response = await fetch("http://localhost:9090/fetchProductDetails/" + productId + "");
	const myJson = await response.json();
	return myJson;

}

const printProduct = (tableTag, productId, imagePath, name, basePrice, discount, returnPeriod) => {
	
	console.log(discount)
	
	let image = createImage('img', 'product_print_image', 'product_print_image', 500, 300, imagePath);


	let product_data_div = createEle('div', 'product-data-div', 'product-data-div');
	let product_name_div = createEle('div', 'product-name-div', 'product-name-div');

	let product_name = createNameEle('h3', 'product-name', 'product-name', name);

	product_name_div.append(product_name);


	let base_price_details_div = createEle('div', 'base_price-details-div', 'base_price-details-div');
	let mrp = createEle('h6', 'mrp', 'mrp');
	mrp.appendChild(document.createTextNode('MRP:'))

	let base_price_div = createEle('div', 'base-price-div', 'base-price-div');
	let base_price_del = createEle('del', 'base-price-del', 'base-price-del');
	let base_price_data = createEle('span', 'base-price-data', 'base-price-data');
	base_price_data.appendChild(document.createTextNode('Rs '));
	let base_price = createPriceEle('span', 'base-price', 'base-price', basePrice);

	base_price_data.appendChild(base_price);
	base_price_del.appendChild(base_price_data);
	base_price_div.appendChild(base_price_del);

	base_price_details_div.appendChild(mrp);
	base_price_details_div.appendChild(base_price_div);


	let sale_price_div = createEle('div', 'sale_price_div', 'sale_price_div');
	let sale_price_data = createEle('h5', 'sale-price-data', 'sale-price-data');

	let sale_price_rs = createEle('span', 'sale-price-rs', 'sale-price-rs');
	sale_price_rs.appendChild(document.createTextNode('Price: Rs '))

	let discount_amount_value = basePrice * (0.01 * discount);
	let sale_price_value = basePrice - discount_amount_value;

	let sale_price = createPriceEle('span', 'sale-price', 'sale-price', sale_price_value);

	sale_price_rs.appendChild(sale_price);
	sale_price_data.appendChild(sale_price_rs);
	sale_price_div.appendChild(sale_price_data);

	let discount_amount_div = createEle('div', 'discount_amount_div', 'discount_amount_div');
	let discount_amount = createEle('span', 'discount_amount', 'discount_amount');
	discount_amount.appendChild(document.createTextNode('You Save: '));
	let discount_amount_input = createPriceEle('span', 'base-price', 'base-price', discount_amount_value);

	discount_amount.appendChild(discount_amount_input);

	discount_amount_div.appendChild(discount_amount);

	let delivery_expected_div = createEle('div', 'delivery_expected_div', 'delivery_expected_div')
	let delivery_expected = createNameEle('span', 'delivery_expected', 'delivery_expected', "delivered with in 7days");
	delivery_expected_div.appendChild(delivery_expected);

	let return_period_div = createEle('div', 'return_period_div', 'return_period_div');
	let return_period;
	if (returnPeriod > 0) {
		return_period = createNameEle('span', 'return_period', 'return_period', "returnable before " + returnPeriod + " days of delivery");
	}
	else {
		return_period = createNameEle('span', 'return_period', 'return_period', "Not returnable once delivered");
	}
	return_period_div.append(return_period);

	//Cart
	let cart_block = createEle('div', 'cart', 'cart');
	let subtract_button = createButtonEle('button', 'subtract-button', 'subtract-button', '-');
	subtract_button.setAttribute('onClick', 'subtractQuantity()')

	let quantity = inputElement('input', 'quantity', 'quantity', 'text', 3, 1);

	let addition_button = createButtonEle('button', 'addition-button', 'addition-button', '+');
	addition_button.setAttribute('onClick', 'addQuantity()')

	let add_to_cart_button = createButtonEle('button', 'add-to-cart-button', 'add-to-cart-button', 'ADD');

	//Add to cart
	add_to_cart_button.setAttribute('onClick', 'addToCart(' + productId + ')')

	subtract_button.classList.add('cart-element');
	quantity.classList.add('cart-element');
	addition_button.classList.add('cart-element')
	add_to_cart_button.classList.add('cart-element');


	cart_block.appendChild(subtract_button);
	cart_block.appendChild(quantity);
	cart_block.appendChild(addition_button);
	cart_block.appendChild(add_to_cart_button);

	product_data_div.appendChild(product_name_div);
	product_data_div.appendChild(base_price_details_div);
	product_data_div.appendChild(sale_price_div);
	product_data_div.appendChild(discount_amount_div);
	product_data_div.appendChild(delivery_expected_div);
	product_data_div.appendChild(return_period_div)
	product_data_div.appendChild(cart_block);


	let row = tableTag.insertRow(0);
	row.setAttribute('class', 'product_details_row')
	let cell1 = row.insertCell(0);
	cell1.setAttribute('class', 'product_image_cell');
	cell1.appendChild(image);
	let cell2 = row.insertCell(1);
	cell2.setAttribute('class', 'product_details_cell');
	cell2.appendChild(product_data_div);




}

const printDescription = (tableTag, name, descriptionData) => {

	let description_div = createEle('div', 'description_div', 'description_div');

	let description_title_div = createEle('div', 'description_title_div', 'description_title_div');
	let description_title = createNameEle('h2', 'description_title', 'description_title', name);

	let headerLine = createEle('hr', 'header_line', 'header_line');

	description_title_div.appendChild(description_title);
	description_title_div.appendChild(headerLine);

	let description_block = createEle('div', 'description_block', 'description_block');
	let description = createNameEle('span', 'description', 'description', descriptionData);
	description_block.appendChild(description);

	description_div.appendChild(description_title_div);
	description_div.appendChild(description_block);
	let row = tableTag.insertRow(1);
	let cell1 = row.insertCell(0);
	cell1.setAttribute('colspan', 2)
	row.setAttribute('class', 'description_row')
	cell1.setAttribute('class', 'description_cell')


	cell1.appendChild(description_div);

}

const userRatingReviewBlock=(row1,row2,rating,review,userName,year,month,date)=>{
	
	
	row1.setAttribute('class','rating-review-row','rating-review-row')
	row2.setAttribute('class','username-date-row','username-date-row')
	let userRatingBlock=createEle('div','user-rating-block','user-rating-block');
	let userReviewRatingIcon=createImage('img','user-review-rating-icon','user-review-rating-icon',15,15,'http://localhost:9090/images/RatingIcon/rating_icon.png');
	let userRating=createNameEle('h5','user-rating-value','user-rating-value',rating);
	
	userRatingBlock.appendChild(userRating);
	userRatingBlock.appendChild(userReviewRatingIcon);
	
	let reviewData=createNameEle('h4','review-data','review-data',review);
	
	
	let cell=row1.insertCell(0)
	cell.setAttribute('colspan',3)
	cell.setAttribute('class','userRatingReviewCell')
	
	cell.append(userRatingBlock);
	cell.append(reviewData);
	
	let cell2=row2.insertCell(0)
	cell2.setAttribute('colspan',3)
	cell2.setAttribute('class','userReviewNameDateCell')
	
	let usersName=createNameEle('h4','userName','userName',userName)
	
	let dateOfReview=createNameEle('h5','dateOfReview','dateOfReview','Date :- '+date+'-'+month+'-'+year)
	
	cell2.appendChild(usersName);
	cell2.appendChild(dateOfReview);
	
	
	
	
	
}

const ratingNamingBlock=(numberOfRating,averageRatings)=>{
	let ratingTable=createEle('table','ratingTable','ratingTable')
	let namingRow=ratingTable.insertRow(0);
	namingRow.setAttribute('class','rating_naming_row','rating_naming_row');
	let ratingName=createNameEle('h3','rating-name','rating-name','Reviews & Ratings');
	
	let averageRatingBlock=createEle('div','average-rating-block','average-rating-block');
	let averageRating=createNameEle('h3','average-rating','average-rating',averageRatings);
	let ratingIconNameBlock=createImage('img','rating-icon-name-block','rating-icon-name-block',20,20,'http://localhost:9090/images/RatingIcon/rating_icon.png');
	
	averageRatingBlock.append(averageRating);
	averageRatingBlock.append(ratingIconNameBlock);
	
	let numberOfRatings=createNameEle('span','number-of-Ratings','number-of-Ratings','total '+numberOfRating+' ratings');
	
	let rateButton=createNameEle('button','rateButton','rateButton',"Rate")
	//document.getElementById('rateButton').appendChild(document.createTextNode("RATE"));
	
	let cell1=namingRow.insertCell(0);
	cell1.setAttribute('class','rating-naming-cell');
	let cell2=namingRow.insertCell(1);
	cell2.setAttribute('class','rating-cell')
	let cell3=namingRow.insertCell(2);
	cell3.setAttribute('class','rate-button-cell')
	
	cell1.appendChild(ratingName);
	cell2.appendChild(averageRatingBlock);
	cell2.appendChild(numberOfRatings);
	cell3.appendChild(rateButton);
	
	return ratingTable;
}

const printRatingBlock =  (tableTag, review) => {
	let totalRating = 0;
	for (let i = 0; i < review.length; i++) {
			totalRating+=review[i]['rating'];
	}
	let averageRating=(totalRating/(review.length)).toFixed(1);
	let ratingTable= ratingNamingBlock(review.length,averageRating);
	
	if (review.length > 0) {
		
		for (let i = 0; i < review.length; i++) {
			totalRating+=review['rating'];
			let rows=1;
			let userRatingBlockTotal=createEle('div','user-rating-block-total','user-rating-block-total')
			ratingTable.appendChild(userRatingBlockTotal);
			
			let row1=ratingTable.insertRow(rows);
			let row2=ratingTable.insertRow(rows+1)
			rows+=2;
			console.log(review[i]['updatedTime'])
			userRatingBlock=userRatingReviewBlock(row1,row2,review[i]['rating'],review[i]['review'],review[i]['userName'],review[i]['updatedTime'][0],review[i]['updatedTime'][1],review[i]['updatedTime'][2]);
		}
		let row=tableTag.insertRow(2);
		row.setAttribute('class','rating-row','rating-row')
		
		let cell=row.insertCell(0);
		cell.setAttribute('colspan',2);
		cell.setAttribute('class','rating-cells','rating-cells');
		
		cell.append(ratingTable);
	}
	else{
		document.getElementById('numberOfRatings').appendChild(document.createTextNode(0+" ratings"));
		document.getElementById('averageRatingBlock').style.display=none;
	}

}

const PrintSingleProductDetails =  async (productId) => {
	
	const myJson = await fetchProductDetails(productId);
	var products_table = document.getElementById('products-table');
	while (products_table.hasChildNodes()) {
		products_table.removeChild(products_table.firstChild);
	}
	console.log(myJson)
	console.log(myJson['discount'])						
	printProduct(products_table, myJson['id'], myJson['image'], myJson['name'], myJson['basePrice'], myJson['discount'], myJson['returnPeriod']);
	printDescription(products_table, myJson['name'], myJson['description'])
	printRatingBlock(products_table, myJson['review'])


	/*
	for(let i=0;i<myJson.length;i++){
		
		let row=products_table.insertRow(numberOfRow);
		let cell=row.insertCell(numberOfCell);
		let li= createProductItem(myJson[i][0],myJson[i][2],myJson[i][3],myJson[i][4],myJson[i][5],myJson[i][6],myJson[i][7]);
		cell.append(li);
		if(numberOfCell==5){
			numberOfRow+=1;
			numberOfCell=0;
		}
	}	*/

}