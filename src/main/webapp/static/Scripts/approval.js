
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
		else if (tokenArr[4] == "false") {
			"http://localhost:8081/project_1/signin";
		}
		function getTokenId() {
			return tokenArr[0];
		}
	}
}


document.getElementById("approve-button").addEventListener("click", approve);
document.getElementById("deny-button").addEventListener("click", deny);

function approve(tokenArr) {
	let id = document.getElementById("reimbursementId").value;
	let app = true;
	
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8081/project_1/approval";
	xhr.open("POST", url);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log("post successful");
			window.location.href = "http://localhost:8081/project_1/expense";
		} else if (xhr.readyState == 4) {
			console.log("something went wrong");
		}
	}
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let requestBody = `reimbursementId=${id}&approval=${app}`;
	xhr.send(requestBody);
}

function deny(tokenArr) {
	let id = document.getElementById("reimbursementId").value;
	let app = false;
	
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8081/project_1/approval";
	xhr.open("POST", url);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log("post successful");
			window.location.href = "http://localhost:8081/project_1/expense";
		} else if (xhr.readyState == 4) {
			console.log("something went wrong");
		}
	}
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let requestBody = `reimbursementId=${id}&approval=${app}`;
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