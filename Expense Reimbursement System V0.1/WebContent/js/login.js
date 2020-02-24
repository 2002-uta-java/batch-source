window.onload = function() {
	console.log("window loaded, adding click listener");
	// setup listeners
	document.getElementById("submit-btn").addEventListener("click", login);
	console.log(document.cookie);
}

function login(event) {
	// set up cookie with email and password
	let email = document.getElementById("inputEmail").value;
	let password = document.getElementById("inputPassword").value;

	document.cookie = "";

	addCookie("email", email);
	addCookie("password", password);
	
	// redirect to servlet
	window.open("/project1/login", "_self");
}

function addCookie(name, value) {
	document.cookie = name + "=" + value + ";";
	console.log("added to cookie: " + document.cookie);
}

function deleteCookie(name){
	document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;'
}

function deleteCookies() {
	deleteCookie("email");
	deleteCookie("password");
}