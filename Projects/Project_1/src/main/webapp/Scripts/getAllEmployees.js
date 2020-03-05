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


function display(xhr) {
	let employees = JSON.parse(xhr.responseText);
	console.log(employees);
	employeeArr = employees.employees;
	console.log(employeeArr);

	let table = document.getElementById("table");

	for (i in employeeArr) {
		let newRow = document.createElement("tr");

		newRow.innerHTML = `<td>${employeeArr[i].id}</td>
	 	<td>${employeeArr[i].name}</td>
	 	<td>${employeeArr[i].username}</td>
	 	<td>${employeeArr[i].position}</td>
	 	
	 	`;

		table.appendChild(newRow);
	}
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
	}
}


sendAjaxGet("http://localhost:8080/Project1/employees", display);
sendAjaxGet("http://localhost:8080/Project1/session", populateUser)

