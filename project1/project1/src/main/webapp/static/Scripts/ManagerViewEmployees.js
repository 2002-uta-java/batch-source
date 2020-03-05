let token = sessionStorage.getItem("token");

if(!token) {
    window.location.href="http://localhost:8080/project1/login";
} else {
    let tokenArr = token.split(":");
	if(tokenArr.length === 2) {
        let url = "http://localhost:8080/project1/api/employees";
		sendAjaxGet(url, displayEmployees);
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

function displayEmployees(xhr){
	let employees = JSON.parse(xhr.response);
    let table = document.getElementById("employee-table");
    if(employees) {
        table.hidden = false;
    }
    let count = 1;
	for(let e of employees){
        let newRow = document.createElement("tr");
        newRow.setAttribute("id", "e" + count);
        newRow.innerHTML = `<td>${e.eid}</td><td>${e.firstName.toUpperCase()}</td><td>${e.lastName.toUpperCase()}</td>`;
        newRow.addEventListener("click", function(){displayReimbursements(e.eid);});
        table.appendChild(newRow);
        count += 1;
	}
}

function displayReimbursements(eid) {
    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8080/project1/api/reimbursements/id="+eid;
	xhr.open("GET", url);
	xhr.onreadystatechange = function(){
        if(this.readyState === 4 && this.status === 200) {
            let reimbursements = JSON.parse(xhr.response);
            let table = document.getElementById("employee-reimbursement-table");
            while(table.rows.length > 1) {
                table.deleteRow(1);
            }
            if(reimbursements) {
                document.getElementById("hidden-reimbursements").hidden = false;
                table.hidden = false;
            } else {
                table.hidden = true;
                document.getElementById("hidden-reimbursements").hidden = true;
            }
            for(let r of reimbursements){
                let newRow = document.createElement("tr");
                if(!r.resolver) {
                    r.resolver = "N/A";
                }
                newRow.innerHTML = `<td>${r.rid}</td><td>${r.e.eid}</td><td>${r.status.toUpperCase()}</td><td>${r.expense}</td><td>${r.resolver.toUpperCase()}</td><td>${r.rtype.toUpperCase()}</td>`;
                table.appendChild(newRow);
            }
		} else if (this.readyState === 4) {
            window.location.href="http://localhost:8080/project1/login";
		}
	}
    xhr.setRequestHeader("Authorization", token);
    xhr.send();
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