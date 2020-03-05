/**
 * 
 */

function sendAjaxGet(url, callback) {
	console.log(url);
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
		if (this.readyState == 4 && this.status == 200) {
			callback(this);
		} else if(this.readyState == 4){
			console.log(xhr.status);
		}
	}

	xhr.open("GET", url);
	console.log("before send");
	xhr.send();
	console.log("after send");
}


function display(xhr) {
	console.log("in display");
	let reimbursements = JSON.parse(xhr.responseText);
	let reimbursementsArr = reimbursements.erbemployee;
	console.log(reimbursementsArr);

	let table = document.getElementById("table");

	for (let reim of reimbursementsArr) {
		let newRow = document.createElement("tr");

		newRow.innerHTML = `<td>${reim.reimbursementId}</td><td>${reim.employeeId}</td><td>${reim.reimbursementDescription}</td><td>${reim.reimbursementAmount}</td><td>${reim.reimbursementStatus}</td>`;
		console.log(newRow);

		table.appendChild(newRow);
	}
}


function populateUser(xhr) {
	let response = JSON.parse(xhr.response);

	if (response == null) {
		window.location = "http://localhost:8080/Project1/login";
	} else {
		let username = document.getElementById("username");
		username.innerHTML = `${response.username}`;
	}
}

let erbsUrl = "http://localhost:8080/Project1/erbemployee";
var reimbursementsUrl = "http://localhost:8080/Project1/erbemployee";
var sessionUrl = "http://localhost:8080/Project1/session";

sendAjaxGet(erbsUrl, display);
sendAjaxGet(sessionUrl, populateUser);