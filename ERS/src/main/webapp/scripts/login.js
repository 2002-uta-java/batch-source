/**
 * 
 */

window.onload = function() {
	document.getElementById("submit-btn").addEventListener("click", login);
}

function login() {
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
	xhr.send();
}

function redirectToHomePage(xhr) {
	// read the headers returned and save them into local storage
	const firstName = xhr.getResponseHeader(FIRST_NAME_HEADER);
	const lastName = xhr.getResponseHeader(LAST_NAME_HEADER);
	const token = xhr.getResponseHeader(AUTH_TOKEN_HEADER);
	
	
}