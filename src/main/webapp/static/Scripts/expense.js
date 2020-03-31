let token = sessionStorage.getItem("token");
let signOutButton = document.getElementById("sign-out-button").addEventListener("click", signOut);

if (!token) {
	window.location.href = "http://localhost:8081/project_1/signin";
} else {
	let tokenArr = token.split(":");
	if (tokenArr.length === 5) {
		if (tokenArr[4] == "false") {
			window.location.href = "http://localhost:8081/project_1/home";
		} else if (tokenArr[4] == "true") {
			let dirLink = document.getElementById("directory-link");
			let expLink = document.getElementById("expense-link");
			let appLink = document.getElementById("approval-link");
			dirLink.hidden = false;
			expLink.hidden = false;
			appLink.hidden = false;
			sendAjaxPost("http://localhost:8081/project_1/expense", getReimbursementsTable);
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

function getReimbursementsTable(reimbursementJson) {
	let reimbursementContainer = document.getElementById("reimbursements-container");
	let reimbursements = JSON.parse(reimbursementJson.response);
	
	let table = document.getElementById("reimbursement-table");
	let index = 0;
	for(let r of reimbursements){
		index++;
		let indexStr = "approved"+index;
		let row = document.createElement("tr");
		row.innerHTML = `<td>${r.id}</td><td>${r.employeeId}</td><td>${r.expenseType}</td><td>${r.amount}</td><td>${r.approved}</td>`;
		table.appendChild(row);
		
	}
	reimbursementContainer.hidden = false;
}
function signOut() {
	sessionStorage.clear()
}