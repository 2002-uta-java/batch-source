/**
 * 
 */

function sendAjaxGet(url, callback) {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200) {
			callback(this);
		}
	}
	xhr.open("GET", url);
	xhr.send();
}


function populateUser(xhr) {
	let response = JSON.parse(xhr.responseText);
	console.log(response);

	if (response == null) {
		window.location = "http://localhost:8080/Project1/login";
	} else if (response.username == null) {
		window.location = "http://localhost:8080/Project1/login";
	} else {
		let username = document.getElementById("username");
		username.innerHTML = response.username;
		
		console.log(response.id);
		
		document.getElementById('empName').placeholder = `${response.name}`;
		document.getElementById('empPosition').placeholder = `${response.position}`;
		document.getElementById('empUsername').placeholder = `${response.username}`;
	}
}

sendAjaxGet("http://localhost:8080/Project1/session", populateUser)