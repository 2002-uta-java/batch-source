
const requestUrl = "http://localhost:8080/ers/api/reimbursments";
let token = sessionStorage.getItem("token");

if (!token) {
	window.location.href = "http://localhost:8080/ers/";
	console.log("!token");
} else {
	let tokenArr = token.split(":");
	console.log("token before tokenArr.length === 2");
	if (tokenArr.length === 2)
		sendAjaxGet(requestUrl, displayReimbursments);
	else
		window.location.href = "http://localhost:8080/ers/";
}

function sendAjaxGet(url, callback) {
	let xhr = new XMLHttpRequest;
	xhr.open("Get", url);
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200)
			callback(xhr.response);
	}
	xhr.send();
}

function displayReimbursments (reimbursmentsJson) {
	let reimbursments = JSON.parse(reimbursmentsJson);
	console.table(reimbursment);
    
	let table = document.getElementById("rTable");

	for (let r of reimbursments) {
		let newRow = document.createElement("tr");

	    newRow.innerHTML = "<td>${r.id}</td><td>type</td><td>200</td><td>$50</td><td>Time created</td><td>Time submitted</td>";

	    table.appendChild(newRow);
	}
}