let token = sessionStorage.getItem("token");
let signOutButton = document.getElementById("sign-out-button").addEventListener("click", signOut);

if (!token) {
	window.location.href = "http://localhost:8081/project_1/signin";
} else {
	let tokenArr = token.split(":");
	if (tokenArr.length === 5) {
		if (tokenArr[4] == "false") {
			window.location.href = "http://localhost:8081/project_1/home";
		}
		if (tokenArr[4] == "true") {
			let dirLink = document.getElementById("directory-link");
			let expLink = document.getElementById("expense-link");
			let appLink = document.getElementById("approval-link");
			dirLink.hidden = false;
			expLink.hidden = false;
			appLink.hidden = false;
			sendAjaxPost("http://localhost:8081/project_1/directory", getEmployeeTable);
		}
	}
}

function sendAjaxPost(url, callback){
	let xhr = new XMLHttpRequest();
	xhr.open("POST", url);
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4 && xhr.status==200){
			console.log("post success");
			callback(this);
		}
	}
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send();
}

function getEmployeeTable(employeeJson) {
	let employeeContainer = document.getElementById("employee-container");
	let employees = JSON.parse(employeeJson.response);
	console.log("employees: " +employeeJson);
	
	let table = document.getElementById("employee-table");
	for(let employee of employees){
		let newRow = document.createElement("tr");
		console.log(employee);
		newRow.innerHTML = `<td>${employee.name}</td><td>${employee.position}</td><td>${employee.salary}</td>`;
		table.appendChild(newRow);
		
	}
	employeeContainer.hidden = false;
}
function signOut() {
	sessionStorage.clear()
}