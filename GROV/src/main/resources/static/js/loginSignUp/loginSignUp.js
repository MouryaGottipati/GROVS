$(async function(){
	const myJson=await fetch("http://localhost:9090/existingUserCheck");
	if(myJson){
		const userJson=await fetch("http://localhost:9090/userDetails");
		const userData=await userJson.json();
		const formJson = JSON.stringify({
			"mail": userData["mail"],
			"password": userData["password"],
		})


		const myJson = await fetch("http://localhost:9090/login", {
			method: "POST",
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			},
			body: formJson
		})


	console.log(myJson)
		let user = await myJson.json();
		if (myJson.status == 200) {
			console.log(user["firstName"])
			document.getElementById("user-name").innerHTML = user["firstName"];
			document.getElementById("user-name").setAttribute("onclick", "")
			document.getElementById("account-dropdown").setAttribute("onmouseover", "displayDropDown()")
		}
	}
})

const displayLoginPage = () => {

	const signUpButtonBlock = document.getElementById("signup-button-block");
	const loginButtonBlock = document.getElementById("login-button-block");
	document.getElementById("signuppage").style.display = "none";
	document.getElementById("loginpage").style.display = "block";
	loginButtonBlock.style.borderBottom = "3px solid rgba(255, 0, 0, .5)";
	signUpButtonBlock.style.border = "none"
}

const displaySignUpPage = () => {
	document.getElementById("signup-status").innerHtml = "";
	const signUpButtonBlock = document.getElementById("signup-button-block");
	const loginButtonBlock = document.getElementById("login-button-block");
	document.getElementById("loginpage").style.display = "none";
	document.getElementById("signuppage").style.display = "block";
	signUpButtonBlock.style.borderBottom = "3px solid rgba(255, 0, 0, .5)";
	loginButtonBlock.style.border = "none"

}
const goBack = () => {
	document.getElementById("signup-status").innerHtml = "";
	displayLoginPage();
	document.getElementById("login-signup-page").style.display = "none";
	document.getElementById("grovs-header-block").style.pointerEvents = "auto"

}
const openLoginPage = () => {
	document.getElementById("login-signup-page").style.display = "flex";
	document.getElementById("grovs-header-block").style.pointerEvents = "none"
	document.getElementById("login-signup-page").style.pointerEvents = "auto"

}

//const signUpSubmit = () => {
document.getElementById("signUpForm").addEventListener("submit", async function(event) {
	event.preventDefault();
	// const form=document.getElementById("signUpForm");
	//console.log(new FormData(form))
	//const prePayload=new FormData(form);
	//const payload=new URLSearchParams(prePayload);
	// const data=new FormData(event.target);
	//const value=Object.fromEntries(data.entries())
	//console.log(value)

	const firstName = document.getElementById('first-name').value;
	const middleNameElement = document.getElementById('middle-name');
	let middleName;
	if (middleNameElement != null) {
		middleName = middleNameElement.value;
	}
	else {
		middleName = null;
	}

	const lastName = document.getElementById('last-name').value;
	const mail = document.getElementById('mail').value;
	const phone = document.getElementById('phone').value;
	const password = document.getElementById('password').value;
	const dateOfBirth = document.getElementById('date-of-birth').value;

	const formJson = JSON.stringify({
		"firstName": firstName,
		"middleName": middleName,
		"lastName": lastName,
		"mail": mail,
		"mobile": phone,
		"password": password,
		"dateOfBirth": dateOfBirth
	})


	console.log(formJson)
	const myJson = await fetch("http://localhost:9090/signUp", {
		method: "POST",
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		},
		body: formJson
	})/*.then(async (response)=>{
			
			if(response.ok){
			return response.json();
		}
		if(response.status==409){
			
			console.log("In body"+response.body);
			console.log("In text"+response.text);
			console.log(response);
			console.log(response.json())
			
			console.log(response.json().errorCode)
			console.log(response.json()["errorMessage"])
		throw new Error(response.json());
			//documnet.getElementById
		}
		})
		.catch((reponse)=>{
			console.log(reponse.errorCode)
			console.log(reponse["errorCode"])
		})
*/	const boxes = Array.from(
		document.getElementsByClassName('error')
	);

	boxes.forEach(box => {
		box.innerHTML = '';
	});
	let user = await myJson.json();
	//console.log(user)
	//console.log(user["errorCode"])
	//console.log(user["errorMessage"])
	if (myJson.status == 200) {
		document.getElementById("signup-status").innerHtml = "SignUp successfull";
		displayLoginPage();
	}
	if (myJson.status == 406) {
		let fields = [];
		for (let field in user) { fields.push(field); }
		fields.forEach(field => {
			document.getElementById(field + "-validate-text").innerHTML = user[field]
		})
	}
	if (user["errorCode"] == 600) {
		document.getElementById("mail-validate-text").innerHTML = user["errorMessage"]
	}
	else if (user["errorCode"] == 601) {
		document.getElementById("mobile-validate-text").innerHTML = user["errorMessage"]
	}
	else if (user["errorCode"] == 602) {
		document.getElementById("dateOfBirth-validate-text").innerHTML = user["errorMessage"]
	}
});


document.getElementById("loginForm").addEventListener("submit", async function(event) {
	event.preventDefault();

	const mail = document.getElementById('login-mail').value;
	const password = document.getElementById('login-password').value;

	const formJson = JSON.stringify({
		"mail": mail,
		"password": password,
	})


	const myJson = await fetch("http://localhost:9090/login", {
		method: "POST",
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		},
		body: formJson
	})
	const errors = Array.from(
		document.getElementsByClassName('error')
	);

	errors.forEach(error => {
		error.innerHTML = '';
	});
	let user = await myJson.json();
	console.log(user)
	if (myJson.status == 200) {
		console.log(user["firstName"])
		document.getElementById("user-name").innerHTML = user["firstName"];
		document.getElementById("user-name").setAttribute("onclick", "")
		document.getElementById("account-dropdown").setAttribute("onmouseover", "displayDropDown()")
		goBack();
	}
	console.log(user)
	if (myJson.status == 406) {
		let fields = [];
		for (let field in user) { fields.push(field); }
		fields.forEach(field => {
			console.log("login-" + field + "-validate-text")
			document.getElementById("login-" + field + "-validate-text").innerHTML = user[field]
		})
	}
	if (myJson.status == 422) {
		if (user["errorCode"] == 603) {
			document.getElementById("login-mail-validate-text").innerHTML = user["errorMessage"]
		}
		else if (user["errorCode"] == 604) {
			document.getElementById("login-password-validate-text").innerHTML = user["errorMessage"]
		}
	}

});
//}