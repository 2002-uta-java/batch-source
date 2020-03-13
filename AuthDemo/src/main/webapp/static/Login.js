/**
 * 
 */

window.onload = function() {
	document.getElementById("login-btn").addEventListener("click", requestLogin);
}

function requestLogin() {
	const username = document.getElementById("username").value;
	const password = document.getElementById("password").value;
	
	const xhr = new XMLHttpRequest();
	xhr.open("POST", "/AuthDemo/login");
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	const requestBody = `username=${username}&password=${password}`;
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4) {
			handleLogin(xhr);
		}
	}
	
	xhr.send(requestBody);
}

function handleLogin(xhr) {
	if(xhr.status == 200)
		login(xhr);
	else
		failLogin(xhr);
}

function login(xhr) {
	console.log("success!!!");
	const auth = xhr.getResponseHeader("Authorization");
	console.log(auth);
	// set client side session item
	sessionStorage.setItem("token", auth);
	window.location.href = "/AuthDemo/home";
}

function failLogin(xhr) {
	console.log("failed login, :(");
}