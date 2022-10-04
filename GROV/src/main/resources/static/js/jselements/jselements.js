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
		console.log("addToCart"+productId)
		let quantity=document.getElementById('quantity').value;
		let quantityNumber=getNumber(quantity);
		if(typeof quantityNumber==='number' && quantityNumber>0){
			addProductToCart(productId,quantityNumber)
		}else{
			alert("Enter valid data in input");
		}
	}
	
