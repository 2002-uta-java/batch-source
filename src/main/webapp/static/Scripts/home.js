let token = sessionStorage.getItem("token");

let signOutButton = document.getElementById("sign-out-button").addEventListener("click", signOut);

if (!token) {
	window.location.href = "http://localhost:8081/project_1/signin";
} else {
	let tokenArr = token.split(":");
	console.log(tokenArr);
	if (tokenArr.length === 5) {
		employeeTable(tokenArr);
		if (tokenArr[4] == "true") {
			let dirLink = document.getElementById("directory-link");
			let expLink = document.getElementById("expense-link");
			let appLink = document.getElementById("approval-link");
			dirLink.hidden = false;
			expLink.hidden = false;
			appLink.hidden = false;
		}
		/*
		if (tokenArr[4] == "true") {
			sendAjaxPost("http://localhost:8081/project_1/home", managerTable);
		}
		else {
			employeeTable(tokenArr);
		}*/
		
	}
}

function employeeTable(tokenArr) {
	let table = document.getElementById("employee-table");
	let row = table.insertRow(1);
	let nameCell = row.insertCell(0);
	let name = document.createTextNode(tokenArr[1]);
	nameCell.appendChild(name);
	let posCell = row.insertCell(1);
	let pos = document.createTextNode(tokenArr[2]);
	posCell.appendChild(pos);
	let salaryCell = row.insertCell(2);
	let numericSalary = Number(tokenArr[3]);
	if (numericSalary > 0) {
		numericSalary = numericSalary / 100;
	}
	let salary = document.createTextNode(numericSalary);
	salaryCell.appendChild(salary);
	let profileContainer = document.getElementById("employee-container");
	profileContainer.hidden = false;
}
/*
function managerTable(employeeJson) {
	let employees = JSON.parse(employeeJson);
	let table = document.getElementById("employee-table");
	for (employee of employees) {
		console.log("e: "+employee);
		let row = document.createElement("tr");
		row.innerHTML = `<td>${employee.name}</td><td>${employee.position}</td><td>${employee.salary}</td>`;
		table.appendChild(row);
	}
}*/

function signOut() {
	sessionStorage.clear()
}
/*
function sendAjaxPost(url, callback){
	let xhr = new XMLHttpRequest();
	xhr.open("POST", url);
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4 && xhr.status==200){
			console.log("post success");
		}
	}
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	//let reqBody = `id=${id}`;
	xhr.send();

}*/

/*
function 
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8081/project_1/signin";
	let message = document.getElementById("incorrect-attempt");
	console.log("un: " + un + " pw: " + pw);
	xhr.open("POST", url);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log("sign in successful");
			let auth = xhr.getResponseHeader("Authorization");
			sessionStorage.setItem("token", auth);
			console.log(auth);
			window.location.href="http://localhost:8081/project_1/home"
		} else if (xhr.readyState == 4) {
			console.log("incorrect credentials");
			message.hidden = false;
		}
	}
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let requestBody = `username=${un}&password=${pw}`;
	xhr.send(requestBody);
	
*/
/*
function getEmployeeTable(xhr){
	let employee = JSON.parse(employeeJson);
	console.table(birds);
	
	let table = document.getElementById("employee-table");
	for(let bird of birds){
		let newRow = document.createElement("tr");
		
		newRow.innerHTML = `<td>${bird.name}</td><td>${bird.breed}</td><td>${bird.habitat.name}</td>`;
		table.appendChild(newRow);
		
	}
}*/