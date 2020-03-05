// Refresh elements in the page.
function loadPage() {
    // Quick authentication check.
    let token = sessionStorage.getItem("token");
    let tokenArr = token.split(":");

    if(!token){
        window.location.href="http://localhost:8080/Project1/login";
        return;
    }

    // Clear everything.
    clearText("reim-heading");
    clearList("employees-table");
    clearList("managers-table");
    clearList("all-reim");

    // Reload.
    let baseUrl1 = "http://localhost:8080/Project1/api/users/" + tokenArr[0]; // current user
    let baseUrl2 = "http://localhost:8080/Project1/api/users"; // all users
    sendAjaxGet(baseUrl1, loadUser, token);
    sendAjaxGet(baseUrl2, loadEmployees, token);
}

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

function loadUser(xhr) {
    let user = JSON.parse(xhr.response);
    console.log(user);

    // Load profile name (top right).
    document.getElementById("profile-name").innerHTML = ` ${user.firstName} ${user.lastName} `;

    // Load profile picture.
    renderProfilePicture(user.gender);

    // Load profile information (view profile button).
    document.getElementById("modal-name").innerHTML = `${user.firstName} ${user.lastName}`;
    document.getElementById("modal-email").innerHTML = `${user.email}`;
    document.getElementById("modal-position").innerHTML = `${user.position}`;
    document.getElementById("modal-gender").innerHTML = `${user.gender}`;
    document.getElementById("modal-id").innerHTML = `${user.id}`;
}

function renderProfilePicture(gender) {
    let pp = document.getElementById("profile-picture");
    let width = 150;

    if (gender == "male") {
        pp.innerHTML = `<img src='static/Images/male.png' alt='Picture not found' width='${width}'>`;
    }
    else if (gender == "female") {
        pp.innerHTML = `<img src='static/Images/female.png' alt='Picture not found' width='${width}'>`;
    }
}

function loadEmployees(xhr) {
    let employees = JSON.parse(xhr.response);
    // console.log(employees);

    // Load and display each employee appropriately.
    for (let e of employees) {
        let id = e.id;
        let email = e.email;
        let position = e.position;
        let fullName = e.firstName + " " + e.lastName;
        let gender = e.gender;

        let emplElement = document.createElement("tr");
        let d1 = document.createElement("td");
        let d2 = document.createElement("td");

        // record data for future potential purposes.
        emplElement.setAttribute("id", "e" + id);
        emplElement.setAttribute("data-id", id)
        emplElement.setAttribute("data-email", email);
        emplElement.setAttribute("data-position", position);
        emplElement.setAttribute("data-fullName", fullName);
        emplElement.setAttribute("data-gender", gender);

        d1.innerHTML = renderProfilePicture(gender);
        d2.innerHTML = `<b>${fullName}</b><br>Email: ${email}<br>Employee Id: ${id}`;
        emplElement.appendChild(d1);
        emplElement.appendChild(d2);

        if (position == "employee") { // Employees can have their reimbursements shown.
            emplElement.setAttribute("onclick", "requestEmployeeReimbursements('e" + id + "')");
            document.getElementById("employees-table").appendChild(emplElement);
        }
        else if (position == "manager" && id != 1) { // Ignores dummy manager.
            document.getElementById("managers-table").appendChild(emplElement);
        }

    }    
}

function renderProfilePicture(gender) {
    let width = 40;

    if (gender == "male") {
        return `<img src='static/Images/male.png' alt='Picture not found' width='${width}'>`;
    }
    else if (gender == "female") {
        return `<img src='static/Images/female.png' alt='Picture not found' width='${width}'>`;
    }

    return "invalid gender"
}

function requestEmployeeReimbursements(eId) {
    // Clear out current reimbursement list.
    clearList("all-reim");

    // Load the specific employees reimbursements on the side box.
    let e = document.getElementById(eId);
    let id = e.getAttribute("data-id");
    let baseUrl = "http://localhost:8080/Project1/api/reimb/e/" + id;
    sendAjaxGetEmployeeReimbursements(baseUrl, loadEmployeeReimbursements);
}

// requestEmployeeReimbursements AJAX helper.
async function sendAjaxGetEmployeeReimbursements(url, callback) {
    let token = sessionStorage.getItem("token");
	let xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status===200){
            callback(this);
		} else if (this.readyState===4){
            console.log("Ajax failure.");
		}
    }
    xhr.setRequestHeader("Authorization", token);
	xhr.send();
}

function loadEmployeeReimbursements(xhr) {
    let reimbs = JSON.parse(xhr.response);
    let emplUrl = "http://localhost:8080/Project1/api/users";
    sendAjaxGetEmployeeNames(emplUrl, continueLoadReimbursements, reimbs); // this is stupid.
}

// ajax helper for finding employeeNames
async function sendAjaxGetEmployeeNames(url, callback, reimbs) {
    let token = sessionStorage.getItem("token");
    let xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status===200){
            callback(this, reimbs);
		} else if (this.readyState===4){
            console.log("Ajax failure.")
		}
	}
	xhr.setRequestHeader("Authorization", token);
    xhr.send();
}

function continueLoadReimbursements(xhr, reimbs) {
    let employees = JSON.parse(xhr.response);
    let employeeNames = createEmployeeNameList(employees);
    
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
            reimElement.setAttribute("data-target", "#empl-reimbursement");
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
            d2.innerHTML = `<b>${eFullName}</b>` + "<br>Reimbursement Id: " + `${id}` + "<br>Purpose: " + `${purpose}` + "<br>Resolved by: " + `${mFullName}`;
            d3.innerHTML = addStatusImage(status);
            reimElement.appendChild(d1);
            reimElement.appendChild(d2);
            reimElement.appendChild(d3);
        }

        document.getElementById("reim-heading").innerHTML = `${eFullName}'s Reimbursements`; // Redundant but whatever.
        document.getElementById("all-reim").appendChild(reimElement);
    }
}

function addStatusImage(status) {
    let width = 80;
    if (status == "pending") {
        return `<img src='static/Images/pending.png' alt='Pending' width='${width}'></img>`;
    }
    else if (status == "approved") {
        return `<img src='static/Images/approved.png' alt='Approved' width='${width}'></img>`;
    }
    else if (status == "rejected") {
        return `<img src='static/Images/rejected.png' alt='Rejected' width='${width}'></img>`;
    }
    return "invalid status";
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

function loadSingleReimbursement(reimbHtmlId){
    let r = document.getElementById(reimbHtmlId);
    let purpose = r.getAttribute("data-purpose");
    let fullName = r.getAttribute("data-eFullName");
    let amount = r.getAttribute("data-amount");
    let id = r.getAttribute("data-id");
    let idEmployee = r.getAttribute("data-idEmployee");

    let htmlAmount = document.getElementById("single-reimb-amount");
    let htmlName = document.getElementById("single-reimb-name");
    let htmlPurpose = document.getElementById("single-reimb-purpose");
    let htmlId = document.getElementById("single-reimb-id");
    document.getElementById("resolve-reimbursement-info").setAttribute("data-id", id);
    document.getElementById("resolve-reimbursement-info").setAttribute("data-idEmployee", idEmployee);

    htmlAmount.innerHTML = `$${amount}`;
    htmlName.innerHTML = fullName;
    htmlPurpose.innerHTML = `Purpose: ${purpose}`;
    htmlId.innerHTML = `Reimbursement Id: ${id}`;
}

// Helper function to clear the reimbursements lists.
function clearList(id) {
    let parent = document.getElementById(id);
    while (parent.firstChild) {
        parent.firstChild.remove();
    }
}

// Helper function to clear innerHTML text.
function clearText(id) {
    let parent = document.getElementById(id);
    parent.innerHTML = "";
}

// Remove user token and return to the login menu.
function logout() {
    sessionStorage.clear();
    window.location.href="http://localhost:8080/Project1/login";
}

// Load viewManagerHome page.
function viewManagerHome() {
    window.location.href="http://localhost:8080/Project1/homemanager";
}

// Update profile information based on form input AND re-build page.
function updateProfile() {
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
        sendAjaxPostUpdateProfile(baseUrl+tokenArr[0], loadPage, myJSON);
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
	xhr.send(data);
}

function approveReimbursement() {
    resolveReimbursement("approved");
}

function rejectReimbursement() {
    resolveReimbursement("rejected");
}

function resolveReimbursement(newStatus) {
    let baseUrl = "http://localhost:8080/Project1/resolvereimbursement/";
    let reimbId = document.getElementById("resolve-reimbursement-info").getAttribute("data-id");
    let emplId = "e" + document.getElementById("resolve-reimbursement-info").getAttribute("data-idEmployee");
    let token = sessionStorage.getItem("token");
    let tokenArr = token.split(":");
    let dataPack = {id: null, purpose: null, amount: null, idEmployee: null, idManager: tokenArr[0], status: newStatus};

    let myJSON = JSON.stringify(dataPack);

    sendAjaxPostResolveReimbursement(baseUrl+reimbId, requestEmployeeReimbursements, myJSON, emplId);
}

// resolveReimbursement AJAX helper. 
async function sendAjaxPostResolveReimbursement(url, callback, data, emplId){ // also in homemanager.js
    let token = sessionStorage.getItem("token");
	let xhr = new XMLHttpRequest();
    xhr.open("POST", url);

	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status===200){
            callback(emplId);
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
document.getElementById("manager-home-btn").addEventListener("click", viewManagerHome);
document.getElementById("approve-btn").addEventListener("click", approveReimbursement);
document.getElementById("reject-btn").addEventListener("click", rejectReimbursement);

// LOAD PAGE
loadPage();