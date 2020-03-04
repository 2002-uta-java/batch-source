/**
 * 
 */
const reimbursementRequestUrl = "http://localhost:8080/ers/api/reimbursements";
const employeeRequestUrl = "http://localhost:8080/ers/api/employees";
let reimbursements;
let token = sessionStorage.getItem("token");
let tokenArr = token.split(":");

document.getElementById("search-btn").addEventListener("click", searchFunction);

if (!token) {
	window.location.href = "http://localhost:8080/ers/";
	console.log("!token");
} else {
	if (tokenArr[1] != null) {
		sendAjaxGet(reimbursementRequestUrl, displayReimbursements);
		if (tokenArr[2] == "true") {
			let searchDiv = document.getElementById("searchDiv");
			searchDiv.hidden = false;
			employeeAjaxGet(employeeRequestUrl, employeeTable);
		}
	}
	else
		window.location.href = "http://localhost:8080/ers/";
}

function sendAjaxGet(url, callback) {
	let xhr = new XMLHttpRequest;
	xhr.open("Get", url);
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200)
			callback(this);
	}
	xhr.setRequestHeader("Authorization", token);
	xhr.send();
}

function employeeAjaxGet(url, callback) {
	let xhr = new XMLHttpRequest;
	xhr.open("Get", url);
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200)
			callback(this);
	}
	xhr.setRequestHeader("Authorization", token);
	xhr.send();
}

function processReimbursement() {
	
}


function displayReimbursements (xhr) {
	reimbursements = JSON.parse(xhr.response);
	    
	let table = document.getElementById("rTable");

	for (let r of reimbursements) {
		let newRow = document.createElement("tr");
		
		let time = new Date(r.time).toString();

	    newRow.innerHTML = `<td>${r.id}</td><td>${r.stage}</td><td>${r.amount}</td><td>${time}</td><td>${r.employeeId}</td>`;

	    table.appendChild(newRow);
	    if (tokenArr[2] == "true") {
	    	let newBtn = document.createElement("button");
	    	newBtn.addEventListener("click", processReimbursment());
	    	newBtn.class = "btn btn-secondary";
	    	table.appendChild(newBtn);
	    }
	}
}

function employeeTable(xhr) {
	let emTableDiv = document.getElementById("emTableDiv");
	emTableDiv.hidden = false;
	
	let employees = JSON.parse(xhr.response);
		
	let emTable = document.getElementById("emTable");
		
	for (let e of employees) {
		let newRow = document.createElement("tr");

	    newRow.innerHTML = `<td>${e.id}</td><td>${e.fName}</td><td>${e.lName}</td><td>${e.email}</td><td>${e.phone}`;

	    emTable.appendChild(newRow);
	}
}

function searchAjaxGet(url, callback) {
	let xhr = new XMLHttpRequest;
	xhr.open("Get", url);
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200)
			callback(this);
	}
	xhr.setRequestHeader("Authorization", token);
	xhr.send();
}

function searchForEmployee(xhr) {	
	let employee = JSON.parse(xhr.response);
	
	let semTable = document.getElementById("semTable")
	semTable.hidden = false;
	let newRow = document.createElement("tr");
    newRow.innerHTML = `<td>${employee.id}</td><td>${employee.fName}</td><td>${employee.lName}</td><td>${employee.email}</td><td>${employee.phone}`;
    semTable.appendChild(newRow);

    let erTable = document.getElementById("erTable");
    erTable.hidden = false;
	
	for (let r of reimbursements) {
		if (r.employeeId == employee.id) {
			let newRow = document.createElement("tr");
			let time = new Date(r.time).toString();
		    newRow.innerHTML = `<td>${r.id}</td><td>${r.stage}</td><td>${r.amount}</td><td>${time}</td><td>${r.employeeId}</td>`;
		    erTable.appendChild(newRow);
		}
	}
}

function searchFunction() {
	let email = document.getElementById("employeeSearchEmail").value;
	let url = "http://localhost:8080/ers/api/employees/" + email;
	
	searchAjaxGet(url, searchForEmployee);
}