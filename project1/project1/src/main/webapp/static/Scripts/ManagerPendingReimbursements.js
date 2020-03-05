let token = sessionStorage.getItem("token");

if(!token) {
    window.location.href="http://localhost:8080/project1/login";
} else {
    let tokenArr = token.split(":");
	if(tokenArr.length === 2) {
        let baseUrl = "http://localhost:8080/project1/api/reimbursements/pending/manager";
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
    while(table.rows.length > 1) {
        table.deleteRow(1);
    }
    if(reimbursements) {
        table.hidden = false;
    }
    let count = 1;
	for(let r of reimbursements){
        let newRow = document.createElement("tr");
        newRow.setAttribute("id", "row" + count);
        if(!r.resolver) {
            r.resolver = "N/A";
        }
		newRow.innerHTML = `<td>${r.rid}</td><td>${r.status.toUpperCase()}</td><td>${r.expense}</td><td>${r.resolver.toUpperCase()}</td><td>${r.rtype.toUpperCase()}</td><div class="resolve-container"><button id="approve${count}" class="btn btn-sm resolve-btn" data-value="approved">Approve</button><button id="deny${count}" class="btn btn-sm resolve-btn" data-value="denied">Deny</button></div></td>`;
        table.appendChild(newRow);
        let btn1 = document.getElementById("approve"+count)
        let value1 = btn1.getAttribute('data-value');
        btn1.addEventListener("click", function(){resolution(r.rid, value1);});
        let btn2 = document.getElementById("deny"+count)
        let value2 = btn2.getAttribute('data-value');
        btn2.addEventListener("click", function(){resolution(r.rid, value2);});
        count += 1;
	}
    if(table.rows.length <= 1) {
        table.hidden = true;
    }
}

function resolution(rid, value) {
    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8080/project1/api/reimbursements/update";
	xhr.open("POST", url);
	xhr.onreadystatechange = function(){
        if(this.readyState === 4 && this.status === 200) {
            let baseUrl = "http://localhost:8080/project1/api/reimbursements/pending/manager";
            sendAjaxGet(baseUrl, displayReimbursements);
		} else if (this.readyState === 4) {
            window.location.href="http://localhost:8080/project1/login";
		}
	}
	xhr.setRequestHeader("Authorization", token);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    let requestBody = `rid=${rid}&resolution=${value}`;
    xhr.send(requestBody);
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