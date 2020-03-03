/**
 * 
 */
const requestUrl = "http://localhost:8080/ers/api/reimbursements";
let token = sessionStorage.getItem("token");
let tokenArr = token.split(":");

document.getElementById("create").addEventListener("click", createReimbursement);
document.getElementById("search").addEventListener("click", searchForEmployee);

if (!token) {
	window.location.href = "http://localhost:8080/ers/";
	console.log("!token");
} else {
	if (tokenArr[1] != null) {
		sendAjaxGet(requestUrl, displayReimbursements);
		searchForEmployee();
		employeeTable();
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

function displayReimbursements (xhr) {
	let reimbursements = JSON.parse(xhr.response);
    
	let table = document.getElementById("rTable");

	for (let r of reimbursements) {
		let newRow = document.createElement("tr");

	    newRow.innerHTML = `<td>${r.id}</td><td>${r.stage}</td><td>${r.amount}</td><td>${r.time}</td>`;

	    table.appendChild(newRow);
	}
}

function createReimbursement() {
	
}

function employeeTable() {
	let emTableDiv = document.getElementById("emTableDiv");
	
	if (tokenArr[2] == "true") {
		let url = "http://localhost:8080/ers/api/employees";
		sendAjaxGet(url, addEmployeesToTable);
	}
}

function addEmployeesToTable(xhr) {
	let employees = JSON.parse(xhr.response);
	
	let emTable    = document.getElementById("emTable");
	emTableDiv.hidden = false;
	
	console.table(employees);
	
	for (let r of employees) {
		let newRow = document.createElement("tr");

	    newRow.innerHTML = `<td>${r.id}</td><td>${r.fName}</td><td>${r.lName}</td><td>${r.email}</td><td>${r.phone}`;

	    table.appendChild(newRow);
	}
}

function searchForEmployee() {
	let searchDiv = document.getElementById("searchDiv");

	if (tokenArr[2] == "true") {
		searchDiv.hidden = false;
	}
}