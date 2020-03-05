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
	let reimbursements = JSON.parse(xhr.responseText);
	console.log(reimbursements);
	reimbursementsArr = reimbursements.reimbursements;
	console.log(reimbursementsArr);

	let table = document.getElementById("table");

	for (let reim of reimbursementsArr) {
		let newRow = document.createElement("tr");

		newRow.innerHTML = `<td>${reim.reimbursementId}</td>
		<td>${reim.managerId}</td>
	 	<td>${reim.employeeId}</td>
	 	<td>${reim.reimbursementDescription}</td>
		<td>$${reim.reimbursementAmount}</td>
	 	<td>${reim.reimbursementStatus}</td>
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

sendAjaxGet("http://localhost:8080/Project1/reimbursements", display);

sendAjaxGet("http://localhost:8080/Project1/session", populateUser)