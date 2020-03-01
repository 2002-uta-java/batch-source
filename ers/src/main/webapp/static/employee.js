
const requestUrl = "http://localhost:8080/ers/api/reimbursments";

function sendAjaxGet(url, callback) {
	let xhr = new XMLHttpRequest;
	xhr.open("Get", url);
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200)
			callback(xhr.response);
	}
}

sendAjaxGet(requestUrl, displayReimbursments);

function addTableRow(r) {
    let table = document.getElementById("rTable");

    let newRow = document.createElement("tr");

    newRow.innerHTML = "<td>${r.id}</td><td>type</td><td>200</td><td>$50</td><td>Time created</td><td>Time submitted</td>";

    table.appendChild(newRow);
}

function displayReimbursments (reimbursments) {
	let reimbursment = JSON.parse(reimbursments);
	console.table(reimbursment);
	for (let r of reimbursment) {
		addTableRow(r);
	}
}