/**
 * 
 */

window.onload = function() {
	document.getElementById("submit-btn").addEventListener("click", login);
}

function login() {
	const email = document.getElementById("email").value;
	const password = document.getElementById("password").value;

	const xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				redirectToHomePage(xhr);
			} else {
				invalidLogin();
			}
		}
	};
	xhr.open("GET", LOGIN_API);
	// set up headers to attempt to login
	console.log("Setting email header: (" + EMAIL_HEADER + ", " + email + ")");
	console.log("Setting password header: (" + PASSWORD_HEADER + ", "
			+ password + ")");
	xhr.setRequestHeader(EMAIL_HEADER, email);
	xhr.setRequestHeader(PASSWORD_HEADER, password);

	console.log("sending xhr request...");
	xhr.send();
}

function redirectToHomePage(xhr) {
	console.log("redirecting");
	window.location = "/ERS/";
}

function invalidLogin() {
	// TODO need to display that login failed
	console.log("invalid login");
}