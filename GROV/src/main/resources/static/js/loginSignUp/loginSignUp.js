const displayLoginPage = () => {
	const signUpButtonBlock = document.getElementById("signup-button-block");
	const loginButtonBlock = document.getElementById("login-button-block");
	document.getElementById("signuppage").style.display = "none";
	document.getElementById("loginpage").style.display = "block";
	loginButtonBlock.style.borderBottom = "3px solid rgba(255, 0, 0, .5)";
	signUpButtonBlock.style.border = "none"
}

const displaySignUpPage = () => {
	const signUpButtonBlock = document.getElementById("signup-button-block");
	const loginButtonBlock = document.getElementById("login-button-block");
	document.getElementById("loginpage").style.display = "none";
	document.getElementById("signuppage").style.display = "block";
	signUpButtonBlock.style.borderBottom = "3px solid rgba(255, 0, 0, .5)";
	loginButtonBlock.style.border = "none"

}
const goBack = () => {
	displayLoginPage();
	document.getElementById("login-signup-page").style.display = "none";
	document.getElementById("grovs-header-block").style.pointerEvents = "auto"

}
const openLoginPage = () => {
	document.getElementById("login-signup-page").style.display = "flex";
	document.getElementById("grovs-header-block").style.pointerEvents = "none"
	document.getElementById("login-signup-page").style.pointerEvents = "auto"

}

const signUpSubmit=()=>{
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
	const middleNameElement= document.getElementById('middle-name');
	let middleName;
	if(middleNameElement!=null){
		 middleName=middleNameElement.value;
	}
	else{
		middleName =null;
	}
	
	const lastName = document.getElementById('last-name').value;
	const mail = document.getElementById('mail').value;
	const phone = document.getElementById('phone').value;
	const password = document.getElementById('password').value;
	const dateOfBirth = document.getElementById('date-of-birth').value;
	const myJson=await fetch("http://localhost:9090/signUp", {
		method: "POST",
		body: JSON.stringify({
			firstName:firstName,
			middleName:middleName,
			lastName:lastName,
			mail:mail,
			mobile:phone,
			password:password,
			dateOfBirth:dateOfBirth
			
		})

	})		
	console.log(myJson)
	console.log(await myJson.json())
});
}