let token = sessionStorage.getItem("token");

if(!token) {
    window.location.href="http://localhost:8080/project1/login";
} else {
    let tokenArr = token.split(":");
	if(tokenArr.length === 2) {
        let baseUrl = "http://localhost:8080/project1/api/reimbursements/resolved/manager";
		sendAjaxGet(baseUrl, displayReimbursements);
	} else {
        window.location.href="http://localhost:8080/project1/login";
	}
}

function sendAjaxGet(url, callback) {
    let xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	xhr.onreadystatechange = function(){
        if(this.readyState === 4 && this.status === 200) {
            callback(this);
		} else if (this.readyState === 4) {
            window.location.href="http://localhost:8080/project1/login";
		}
	}
	xhr.setRequestHeader("Authorization", token);
    xhr.send();
}

function displayReimbursements(xhr){
	let reimbursements = JSON.parse(xhr.response);
    let table = document.getElementById("reimbursement-table");
    if(reimbursements) {
        table.hidden = false;
    }
	for(let r of reimbursements){
        let newRow = document.createElement("tr");
        if(!r.resolver) {
            r.resolver = "N/A";
        }
		newRow.innerHTML = `<td>${r.rid}</td><td>${r.status.toUpperCase()}</td><td>${r.expense}</td><td>${r.resolver.toUpperCase()}</td><td>${r.rtype.toUpperCase()}</td>`;
		table.appendChild(newRow);
	}
}

document.getElementById("home").addEventListener("click", goHome);
document.getElementById("pending-reimbursements").addEventListener("click", goPendingReimbursements);
document.getElementById("resolved-reimbursements").addEventListener("click", goResolvedReimbursements);
document.getElementById("view-employees").addEventListener("click", goViewEmployees);
document.getElementById("logout").addEventListener("click", goLogout);

function goHome(){
    window.location.href="http://localhost:8080/project1/manager-homepage";
}

function goPendingReimbursements() {
    window.location.href="http://localhost:8080/project1/manager-pending-reimbursements";
}

function goResolvedReimbursements() {
    window.location.href = "http://localhost:8080/project1/manager-resolved-reimbursements";
}

function goViewEmployees() {
    window.location.href = "http://localhost:8080/project1/manager-view-employees";
}

function goLogout() {
    sessionStorage.clear();
    window.location.href = "http://localhost:8080/project1/login";
}