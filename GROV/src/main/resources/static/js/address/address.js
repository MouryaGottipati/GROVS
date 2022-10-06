
const goBack = () => {
	//displayLoginPage();

	document.getElementById("address-page-block").style.display = "none";
	//document.getElementById("grovs-header-block").style.pointerEvents = "auto"

}
const openAddressPage = () => {
	document.getElementById("address-page").style.display = "flex";
	//document.getElementById("grovs-header-block").style.pointerEvents = "none"
	document.getElementById("address-page").style.pointerEvents = "auto"

}
console.log(document.getElementById("address-form").innerHTML)
document.getElementById("address-form").addEventListener("submit", async function(event) {
	event.preventDefault();
	const firstName = document.getElementById('first-name').value;
	//const mail = document.getElementById('mail').value;
	const phone = document.getElementById('phone').value;
	const houseNo = document.getElementById('house-no').value;
	const street = document.getElementById('street').value;
	const city = document.getElementById('city').value;
	const pincode = document.getElementById('pincode').value;
	const state = document.getElementById('state').value;
	const type = document.getElementById('type').value;

	const formJson = JSON.stringify({
		"firstName": firstName,
		"houseNo": houseNo,
		"street": street,
		//"mail": mail,
		"phone": phone,
		"city": city,
		"pincode": pincode,
		"state": state,
		"type": type
	})


	console.log(formJson)
	const myJson = await fetch("http://localhost:9090/addAddress", {
		method: "POST",
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		},
		body: formJson
	})
	console.log(myJson)
	const elements = Array.from(
		document.getElementsByClassName('error')
	);

	elements.forEach(element => {
		element.innerHTML = '';
	});
	let address = await myJson.json();
	//console.log(user)
	//console.log(user["errorCode"])
	//console.log(user["errorMessage"])
	if (myJson.status == 200) {
		const elements = Array.from(
			document.getElementsByClassName('error')
		);

		elements.forEach(element => {
			element.innerHTML = '';
		});

		const inputElements = Array.from(
			document.getElementsByClassName('input')
		);

		inputElements.forEach(inputElement => {
			inputElement.setAttribute("value", "");
		});
		goBack()
	}
	if (myJson.status == 406) {
		let fields = [];
		for (let field in address) { fields.push(field); }
		fields.forEach(field => {
			document.getElementById(field + "-validate-text").innerHTML = address[field]
		})
	}

});


