// Refresh elements in the page.
function loadPage() {
    // Quick authentication check.
    let token = sessionStorage.getItem("token");
    let tokenArr = token.split(":");
    let id = tokenArr[0];

    if(!token){
        window.location.href="http://localhost:8080/Project1/login";
        return;
    }

    // Clear everything.
    clearList("all-reim");
    clearList("pending-reim");
    clearList("resolved-reim");

    // Reload.
    let baseUrl1 = "http://localhost:8080/Project1/api/users/" + id; // current user
    let baseUrl2 = "http://localhost:8080/Project1/api/reimb/e/" + id; // current users reimbursements
    sendAjaxGet(baseUrl1, loadUser, token);
    sendAjaxGetEmployeeReimbursements(baseUrl2, loadEmployeeReimbursements);
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

async function loadUser(xhr) {
    let user = JSON.parse(xhr.response);
    console.log(user);

    // Load profile name (top right).
    document.getElementById("profile-name").innerHTML = ` ${user.firstName} ${user.lastName} `;

    // Load profile information (view profile button).
    document.getElementById("modal-name").innerHTML = `${user.firstName} ${user.lastName}`;
    document.getElementById("modal-email").innerHTML = `${user.email}`;
    document.getElementById("modal-position").innerHTML = `${user.position}`;
    document.getElementById("modal-gender").innerHTML = `${user.gender}`;
    document.getElementById("modal-id").innerHTML = `${user.id}`;
    // TODO: picture depending on gender (id=) (is also in homemanager.js) // also in all other htmls
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

        // Bug fix: Apparently need two separate elements to attach to ALL and PENDING/RESOLVED tabs.
        for (let i = 0; i < 2; i++) {
            let reimElement = document.createElement("a");

            // record data for future potential purposes.
            reimElement.setAttribute("id", "r" + id);
            reimElement.setAttribute("data-id", id);
            reimElement.setAttribute("data-purpose", purpose);
            reimElement.setAttribute("data-amount", amount);
            reimElement.setAttribute("data-idEmployee", idEmployee);
            reimElement.setAttribute("data-idManager", idManager);
            reimElement.setAttribute("data-status", status);
            reimElement.setAttribute("data-eFullName", eFullName);
            reimElement.setAttribute("href", "#");
            reimElement.setAttribute("class", "list-group-item list-group-item-action");

            if (status == "pending") { // Pending reimbursements must be added to ALL and PENDING
                reimElement.innerHTML = `$${amount}  ${purpose}  ${status}`;
                if (i == 0) {document.getElementById("all-reim").appendChild(reimElement);}
                if (i == 1) {document.getElementById("pending-reim").appendChild(reimElement);}
            }
            else { // Resolved reimbursements need the manager who resolved it + added to ALL and RESOLVED
                let mFullName = findEmployeeName(idManager, employeeNames);
                reimElement.setAttribute("data-mFullName", mFullName);
                reimElement.innerHTML = `$${amount}  ${purpose}  ${status}  managerResolve: ${mFullName}`;
                if (i == 0) {document.getElementById("all-reim").appendChild(reimElement);}
                if (i == 1) {document.getElementById("resolved-reim").appendChild(reimElement);}
            }
        }

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

function newReimbursement() {
    let token = sessionStore.getItem("token");
    let tokenArr = token.split(":");
    let formInfo = document.getElementById("new-reim-form");
    let new_amount = formInfo.elements[0].value;
    let new_purpose = formInfo.elements[1].value;

    console.log(new_amount);
    console.log(new_purpose);

    if (!new_amount) { // Invalid amount entry. Other validation handled in backend (negatives, etc.)
        badInput();
        return;
    }

    // Convert new_amount string to float.
    new_amount = parseFloat(new_amount);

    // Nulls handled in backend.
    let newInfo = {id: null, purpose: new_purpose, amount: new_amount, idEmployee: tokenArr[0], idManager: 1, status: "pending"};
    let myJSON = JSON.stringify(newInfo);

    // Send a POST request to update the database, immediately re-build page.
    let baseUrl = "http://localhost:8080/Project1/newreimb";
    sendAjaxPostNewReimbursement(baseUrl, loadPage, myJSON);
}

// newReimbursement AJAX helper. 
async function sendAjaxPostNewReimbursement(url, callback, data){
	let xhr = new XMLHttpRequest();
	xhr.open("POST", url);
	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status===200){
            callback(this);
		} else if (this.readyState===4){
            console.log("Ajax failure.");
            badInput();
		}
	} // probably should authenticate to post; for later.
	xhr.send(data);
}

function badInput() {
    // get id and display bad input
    // hide after 5 seconds.
    let status = document.getElementById("new-reim-form-status");
    status.style.visibility = "visible"; // Display error message for short time.
    setTimeout(function(){status.style.visibility = "hidden"; }, 4000);
}


// Commands to execute on load.
// ALL EVENT LISTENERS
document.getElementById("logout-btn").addEventListener("click", logout);
document.getElementById("update-profile-btn").addEventListener("click", updateProfile);
document.getElementById("create-reim-btn").addEventListener("click", newReimbursement);

// LOAD PAGE + Authentication
let token = loadPage();