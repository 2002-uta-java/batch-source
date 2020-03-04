// Check token to remain on page and load.
async function checkTokenAndLoadPage() {
	let token = sessionStorage.getItem("token");

	if(!token){
		window.location.href="http://localhost:8080/Project1/login";
	} else {
		let tokenArr = token.split(":");
		console.log(tokenArr);
		if(tokenArr.length===2){
            // Authentication success! Page will load.
            let baseUrl = "http://localhost:8080/Project1/api/users/";
            sendAjaxGet(baseUrl+tokenArr[0], loadPage, token);
		} else {
			window.location.href="http://localhost:8080/Project1/login";
		}
    }
    return token;
}

// checkToken AJAX helper. 
async function sendAjaxGet(url, callback, token){
	let xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status===200){
			callback(this);
		} else if (this.readyState===4){
            console.log("Ajax failure.")
		}
	}
	xhr.setRequestHeader("Authorization", token);
	xhr.send();
}

// Load all page information.
async function loadPage(xhr){
    let user = JSON.parse(xhr.response);

    // Clear reimbursement lists.
    clearList("all-reim-table");
    clearList("pending-reim-table");
    clearList("resolved-reim-table");

    // Load profile name (top right).
    document.getElementById("profile-name").innerHTML = ` ${user.firstName} ${user.lastName} `;

    // Load profile information (view profile button).
    document.getElementById("modal-name").innerHTML = `${user.firstName} ${user.lastName}`;
    document.getElementById("modal-email").innerHTML = `${user.email}`;
    document.getElementById("modal-position").innerHTML = `${user.position}`;
    document.getElementById("modal-gender").innerHTML = `${user.gender}`;
    document.getElementById("modal-id").innerHTML = `${user.id}`;
    // TODO: picture depending on gender (id=) (is also in viewemployees.js)

    // Load reimbursement information
    requestReimbursements();
}

// Helper function to clear the reimbursements lists.
function clearList(id) {
    let parent = document.getElementById(id);
    while (parent.firstChild) {
        parent.firstChild.remove();
    }
}

// Remove user token and return to the login menu.
function logout() {
    sessionStorage.clear();
    window.location.href="http://localhost:8080/Project1/login";
}

// Load viewEmployees page.
function viewEmployees() {
    window.location.href="http://localhost:8080/Project1/viewemployees";
}

// Update profile information based on form input AND re-build page.
async function updateProfile() {
    let formInfo = document.getElementById("edit-profile-form");
    let new_fName = formInfo.elements[0].value;
    let new_lName = formInfo.elements[1].value;
    let new_email = formInfo.elements[2].value;
    let new_gender = formInfo.elements[3].value.toLowerCase();

    // TODO: Gather all data (null arguments ignored in backend).
    let updateInfo = {id: null, email: new_email, position: null, firstName: new_fName, lastName: new_lName, gender: new_gender, password: null};
    let myJSON = JSON.stringify(updateInfo);

    // Send a POST request to update the database, immediately re-build page (checkToken).
    let baseUrl = "http://localhost:8080/Project1/updateuser/";
    let token = sessionStorage.getItem("token");
    let tokenArr = token.split(":");
	if(tokenArr.length===2) {
        sendAjaxPostUpdateProfile(baseUrl+tokenArr[0], checkTokenAndLoadPage, myJSON);
    }
}

// updateProfile AJAX helper. 
async function sendAjaxPostUpdateProfile(url, callback, data){
	let xhr = new XMLHttpRequest();
	xhr.open("POST", url);
	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status===200){
            callback(this);
            let status = document.getElementById("edit-profile-form-status");
            status.style.visibility = "visible"; // Display success message for short time.
            setTimeout(function(){status.style.visibility = "hidden"; }, 4000);
		} else if (this.readyState===4){
            console.log("Ajax failure.");
		}
    } // probably should authenticate to post; for later.
    xhr.setRequestHeader("Authorization", token);
	xhr.send(data);
}

async function requestReimbursements(){
    let baseUrl1 = "http://localhost:8080/Project1/api/reimb";   // ALL reimbursements.
    let baseUrl2 = "http://localhost:8080/Project1/api/reimb/p"; // PENDING
    let baseUrl3 = "http://localhost:8080/Project1/api/reimb/r"; // RESOLVED
    sendAjaxGetReimbursements(baseUrl1, loadReimbursements);
    sendAjaxGetReimbursements(baseUrl2, loadReimbursements);
    sendAjaxGetReimbursements(baseUrl3, loadReimbursements);
}

// requestReimbursements AJAX helper.
async function sendAjaxGetReimbursements(url, callback) {
    let token = sessionStorage.getItem("token");
	let xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status===200){
            callback(url, this);
		} else if (this.readyState===4){
            console.log("Ajax failure.");
		}
    }
    xhr.setRequestHeader("Authorization", token);
	xhr.send();
}

// Recieve data and load ALL reimbursements.
async function loadReimbursements(baseUrl, xhr) {
    let reimbs = JSON.parse(xhr.response);
    let emplUrl = "http://localhost:8080/Project1/api/users";
    sendAjaxGetEmployeeNames(emplUrl, continueLoadReimbursements, baseUrl, reimbs); // this is stupid.
}

// ajax helper for finding employeeNames
async function sendAjaxGetEmployeeNames(url, callback, baseUrl, reimbs) {
    let xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status===200){
            callback(this, baseUrl, reimbs);
		} else if (this.readyState===4){
            console.log("Ajax failure.")
		}
	}
	xhr.setRequestHeader("Authorization", token);
    xhr.send();
}

async function continueLoadReimbursements(xhr, baseUrl, reimbs) {
    let employees = JSON.parse(xhr.response);
    let employeeNames = createEmployeeNameList(employees);

    let baseUrl1 = "http://localhost:8080/Project1/api/reimb";   // ALL reimbs.
    let baseUrl2 = "http://localhost:8080/Project1/api/reimb/p"; // PENDING reimbs.
    let baseUrl3 = "http://localhost:8080/Project1/api/reimb/r"; // RESOLVED reimbs.
    
    for (let r of reimbs) {
        let id = r.id;
        let purpose = r.purpose;
        let amount = r.amount;
        let idEmployee = r.idEmployee;
        let idManager = r.idManager;
        let status = r.status;
        let eFullName = findEmployeeName(idEmployee, employeeNames);

        let reimElement = document.createElement("tr");
        let d1 = document.createElement("td");
        let d2 = document.createElement("td");
        let d3 = document.createElement("td");

        d1.style.verticalAlign = "middle";
        d2.style.verticalAlign = "middle";
        d3.style.verticalAlign = "middle";

        // record data for future potential purposes.
        reimElement.setAttribute("id", "r" + id);
        reimElement.setAttribute("data-id", id);
        reimElement.setAttribute("data-purpose", purpose);
        reimElement.setAttribute("data-amount", amount);
        reimElement.setAttribute("data-idEmployee", idEmployee);
        reimElement.setAttribute("data-idManager", idManager);
        reimElement.setAttribute("data-status", status);
        reimElement.setAttribute("data-eFullName", eFullName);

        if (status == "pending") { // Pending reimbursements can be resolved in modals.
            reimElement.setAttribute("data-target", "#view-reimbursement");
            reimElement.setAttribute("data-toggle", "modal");
            reimElement.setAttribute("onclick", "loadSingleReimbursement('r" + id + "')");
            d1.innerHTML = `<div id='amount-text'>$${amount}</div>`;
            d2.innerHTML = `<b>${eFullName}</b>` + "<br>Reimbursement Id: " + `${id}` + "<br>Purpose: " + `${purpose}`;
            d3.innerHTML = addStatusImage(status);
            reimElement.appendChild(d1);
            reimElement.appendChild(d2);
            reimElement.appendChild(d3);
        }
        else { // Resolved reimbursements need the manager who resolved it.
            let mFullName = findEmployeeName(idManager, employeeNames);
            reimElement.setAttribute("data-mFullName", mFullName);
            d1.innerHTML = `<div id='amount-text'>$${amount}</div>`;
            d2.innerHTML = `<b>${eFullName}</b>` + "<br>Reimbursement Id: " + `${id}` + "<br>Purpose: " + `${purpose}` + "<br>Approved by: " + `${mFullName}`;
            d3.innerHTML = `${status}`;
            reimElement.appendChild(d1);
            reimElement.appendChild(d2);
            reimElement.appendChild(d3);
        }

        if (baseUrl == baseUrl1) {document.getElementById("all-reim-table").appendChild(reimElement);}
        if (baseUrl == baseUrl2) {document.getElementById("pending-reim-table").appendChild(reimElement);}
        if (baseUrl == baseUrl3) {document.getElementById("resolved-reim-table").appendChild(reimElement);}
    }
}

function addStatusImage(status) {
    if (status == "pending") {
        return "<img src='static/Images/pending.jpg' alt='pending'></img>";
    }
    else if (status == "approved") {
        return "<img src='static/Images/approved.jpg' alt='approved'></img>";
    }
    else if (status == "rejected") {
        return "<img src='static/Images/rejected.jpg' alt='rejected'></img>";
    }
}



function createEmployeeNameList(employees) {
    let employeeNames = []; // format: [ [id, fullName], [id, fullName], ...]

    for (let e of employees) {
        let id = e.id;
        let fullName = e.firstName + " " + e.lastName;
        employeeNames.push([id, fullName]);
    }

    return employeeNames;
}

function findEmployeeName(id, employeeNames) {
    let arrayLength = employeeNames.length;

    for (let i = 0; i < arrayLength; i++) {
        eId = employeeNames[i][0];
        eName = employeeNames[i][1];
        if (id == eId) {
            return eName;
        }
    }
    return "Name missing (id error)."
}

async function loadSingleReimbursement(reimbHtmlId){
    let r = document.getElementById(reimbHtmlId);
    let purpose = r.getAttribute("data-purpose");
    let fullName = r.getAttribute("data-eFullName");
    let amount = r.getAttribute("data-amount");
    let id = r.getAttribute("data-id");

    let htmlAmount = document.getElementById("single-reimb-amount");
    let htmlName = document.getElementById("single-reimb-name");
    let htmlPurpose = document.getElementById("single-reimb-purpose");
    let htmlId = document.getElementById("single-reimb-id");

    htmlAmount.innerHTML = amount;
    htmlName.innerHTML = fullName;
    htmlPurpose.innerHTML = purpose;
    htmlId.innerHTML = id; // TODO: need to update [DO NOT CHANGE. can make this hidden if needed.
} // also in viewemployee.js

async function approveReimbursement() {
    resolveReimbursement("approved");
}

async function rejectReimbursement() {
    resolveReimbursement("rejected");
}

async function resolveReimbursement(newStatus) {
    let baseUrl = "http://localhost:8080/Project1/resolvereimbursement/";
    let reimbId = document.getElementById("single-reimb-id").innerHTML;
    let token = sessionStorage.getItem("token");
    let tokenArr = token.split(":");
    let dataPack = {id: null, purpose: null, amount: null, idEmployee: null, idManager: tokenArr[0], status: newStatus};

    let myJSON = JSON.stringify(dataPack);

    sendAjaxPostResolveReimbursement(baseUrl+reimbId, checkTokenAndLoadPage, myJSON);
}

// resolveReimbursement AJAX helper. 
async function sendAjaxPostResolveReimbursement(url, callback, data){ // also in viewemployees.js
	let xhr = new XMLHttpRequest();
    xhr.open("POST", url);

	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status===200){
            callback(this);
		} else if (this.readyState===4){
            console.log("Ajax failure.");
		}
    } // probably should authenticate to post; for later.
    xhr.setRequestHeader("Authorization", token);
	xhr.send(data);
}



// Commands to execute on load.
// ALL EVENT LISTENERS
document.getElementById("logout-btn").addEventListener("click", logout);
document.getElementById("update-profile-btn").addEventListener("click", updateProfile);
document.getElementById("approve-btn").addEventListener("click", approveReimbursement);
document.getElementById("reject-btn").addEventListener("click", rejectReimbursement);
document.getElementById("view-employees-btn").addEventListener("click", viewEmployees);

// LOAD PAGE + Authentication
let token = checkTokenAndLoadPage();