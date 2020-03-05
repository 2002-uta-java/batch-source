/**
 * 
 */

let token = sessionStorage.getItem("token");
let tokenArr = token.split(":");
let id = tokenArr[0];
let isManager = tokenArr[2];
let employeeRequestUrl = "http://localhost:8080/ers/api/employees/" + id;
let updateRequestUrl = "http://localhost:8080/ers/updateEmployee";

document.getElementById("update-btn").addEventListener("click", updateEmployee);

function sendAjaxGet(url, callback) {
	let xhr = new XMLHttpRequest;
	xhr.open("Get", url);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200)
			callback(this);
	}
	xhr.setRequestHeader("Authorization", token);
	xhr.send();
}

if (isManager == "true") {
	document.getElementById("managerCheck").hidden = false;
	document.getElementById("id").hidden = false;
	document.getElementById("createaccountnav").hidden = false;
	employeeRequestUrl = "http://localhost:8080/ers/api/employees";
	sendAjaxGet(employeeRequestUrl, otherEmployeeTable);
} else
	sendAjaxGet(employeeRequestUrl, employeeTable);

function otherEmployeeTable(xhr) {
	let employees = JSON.parse(xhr.response);

	let emTable = document.getElementById("emTable");
	
	for (let e of employees) {
		let newRow = document.createElement("tr");
		newRow.innerHTML = `<td>${e.id}</td><td>${e.fName}</td><td>${e.lName}</td><td>${e.email}</td><td>${e.phone}`;
		emTable.appendChild(newRow);
	}
}

function employeeTable(xhr) {
	let e = JSON.parse(xhr.response);

	let emTable = document.getElementById("emTable");
	let newRow = document.createElement("tr");
	newRow.innerHTML = `<td>${e.id}</td><td>${e.fName}</td><td>${e.lName}</td><td>${e.email}</td><td>${e.phone}`;
	emTable.appendChild(newRow);
}

function sendAjaxPost(url, requestBody) {
	let xhr = new XMLHttpRequest;
	xhr.open("Post", url);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200)
			alert("Updated");
	}
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send(requestBody);
}

function updateEmployee() {
	
	if (isManager == "true") {
		var id           = document.getElementById("id").value;
		var managerT = document.getElementById("isManager").checked;
	} else
		id = tokenArr[0];
	let firstname = document.getElementById("firstname").value;
	let lastname  = document.getElementById("lastname").value;
	let email     = document.getElementById("email").value;
	let phone     = document.getElementById("phone").value;
	let password  = document.getElementById("password").value;
		
	let requestBody = `id=${id}&firstname=${firstname}&lastname=${lastname}&email=${email}&email=${email}&phone=${phone}&password=${password}`;
	
	if (isManager == "true");
		if (managerT == true)
			requestBody += `&isManager=${managerT}`;
	
	sendAjaxPost(updateRequestUrl, requestBody);
}