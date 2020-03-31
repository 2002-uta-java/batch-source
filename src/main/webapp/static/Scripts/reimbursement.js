
let token = sessionStorage.getItem("token");

let signOutButton = document.getElementById("sign-out-button").addEventListener("click", signOut);

if (!token) {
	window.location.href = "http://localhost:8081/project_1/signin";
} else {
	let tokenArr = token.split(":");
	console.log(tokenArr);
	if (tokenArr.length === 5) {
		if (tokenArr[4] == "true") {
			let dirLink = document.getElementById("directory-link");
			let expLink = document.getElementById("expense-link");
			let appLink = document.getElementById("approval-link");
			dirLink.hidden = false;
			expLink.hidden = false;
			appLink.hidden = false;
		}
		function getTokenId() {
			return tokenArr[0];
		}
	}
}

document.getElementById("reimbursement-button").addEventListener("click", reimbursementRequest);

function reimbursementRequest(tokenArr) {
	let id = getTokenId();
	console.log("id: "+id);
	let et = document.getElementById("expenseType").value;
	let am = document.getElementById("amountInput").value;
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8081/project_1/reimbursement";
	xhr.open("POST", url);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log("post successful");
			window.location.href = "http://localhost:8081/project_1/status";
			
		} else if (xhr.readyState == 4) {
			console.log("something went wrong");
		}
	}
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let requestBody = `employeeId=${id}&expenseType=${et}&amount=${am}`;
	xhr.send(requestBody);
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

function signOut() {
	sessionStorage.clear()
}