
document.getElementById("register-button").addEventListener("click", register);

function register() {
	
	let un = document.getElementById("usernameInput").value;
	let pw = document.getElementById("passwordInput").value;
	let pwc = document.getElementById("passwordConfirmation").value;
	let pos = document.getElementById("positionInput").value;
	let sal = document.getElementById("salaryInput").value;
	let man = document.getElementById("managementInput").checked;
	
	console.log(man);
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8081/project_1/register";
	let message = document.getElementById("username-taken");
	xhr.open("POST", url);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log("sign in successful");
			let auth = xhr.getResponseHeader("Authorization");
			sessionStorage.setItem("token", auth);
			console.log("auth: " + auth);
			let authArr = auth.split(":");
			if (authArr.length === 5) {
				window.location.href = "http://localhost:8081/project_1/home";
			} 
		} else if (xhr.readyState == 4) {
			console.log("username not available");
			message.hidden = false;
		}
	}
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let requestBody = `username=${un}&password=${pw}&position=${pos}&salary=${sal}&management=${man}`;
	xhr.send(requestBody);
}

/*
function validateRegisterForm() {
	const lettersAndNumbers = "[a-zA-Z0-9]*";
	
	let un = document.forms[0].usernameInput.value;
	let pw = document.forms[0].passwordInput.value;
	let pwc = document.forms[0].passwordConfirmation.value;
	
	if (un.length < 3) {
		console.log("Username must be 3-50 letters or numbers.");
		alert("Username must be 3-50 letters or numbers.");
	
	}
	if (pw.length < 8) {
		console.log("Password must be 8-50 characters.");
		alert("Password must be 8-50 characters.");
	}
	if (pwc != pw) {
		console.log("Passwords must match.");
		alert("Passwords must match.");
	}
	else if ((un.length >= 3) && (pw.length >= 8)  && (pwc === pw)){
		let xhr = new XMLHttpRequest();
		let url = "http://localhost:8081/project_1/register";
		xhr.open("POST", url);
		xhr.onreadystatechange = function() {
			if(xhr.readyState == 4 && xhr.status == 200){
			console.log("success!!!");
			let auth = xhr.getResponseHeader("Authorization");
			sessionStorage.setItem("token", auth);
			console.log(auth);
			window.location.href="http://localhost:8080/project_1/home"
			} else if (xhr.readyState == 4) {
			console.log("incorrect credentials");
			}
		}
	}
}
*/
