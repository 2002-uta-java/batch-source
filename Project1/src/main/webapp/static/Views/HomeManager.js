// Check token to remain on page and load.
function checkTokenAndLoadPage() {
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
function sendAjaxGet(url, callback, token){
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
function loadPage(xhr){
    let user = JSON.parse(xhr.response);

    // Clear reimbursement lists.
    clearList("all-reim");
    clearList("pending-reim");
    clearList("resolved-reim");

    // Load profile name (top right).
    document.getElementById("profile-name").innerHTML = ` ${user.firstName} ${user.lastName} `;

    // Load profile information (view profile button).
    document.getElementById("modal-name").innerHTML = `${user.firstName} ${user.lastName}`;
    document.getElementById("modal-email").innerHTML = `${user.email}`;
    document.getElementById("modal-position").innerHTML = `${user.position}`;
    document.getElementById("modal-gender").innerHTML = `${user.gender}`;
    document.getElementById("modal-id").innerHTML = `${user.id}`;
    // TODO: picture depending on gender (id=)

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
        sendAjaxPostUpdateProfile(baseUrl+tokenArr[0], checkTokenAndLoadPage, myJSON);
    }
}

// updateProfile AJAX helper. 
function sendAjaxPostUpdateProfile(url, callback, data){
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

function requestReimbursements(){
    let baseUrl1 = "http://localhost:8080/Project1/api/reimb";   // ALL reimbursements.
    let baseUrl2 = "http://localhost:8080/Project1/api/reimb/p"; // PENDING
    let baseUrl3 = "http://localhost:8080/Project1/api/reimb/r"; // RESOLVED
    sendAjaxGetReimbursements(baseUrl1, loadReimbursements);
    sendAjaxGetReimbursements(baseUrl2, loadReimbursements);
    sendAjaxGetReimbursements(baseUrl3, loadReimbursements);
}

// requestReimbursements AJAX helper.
function sendAjaxGetReimbursements(url, callback) {
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
function loadReimbursements(baseUrl, xhr) {
    let baseUrl1 = "http://localhost:8080/Project1/api/reimb";   // ALL reimbs.
    let baseUrl2 = "http://localhost:8080/Project1/api/reimb/p"; // PENDING reimbs.
    let baseUrl3 = "http://localhost:8080/Project1/api/reimb/r"; // RESOLVED reimbs
    let reimbs = JSON.parse(xhr.response);

    for (let r of reimbs) {
        let id = r.id;
        let purpose = r.purpose;
        let amount = r.amount;
        let idEmployee = r.idEmployee;
        let idManager = r.idManager;
        let status = r.status;
        // TODO: PLUS ADD REQUEST FOR THE NAME based on idEmployee

        let reimElement = document.createElement("a");

        // record data for future potential purposes.
        reimElement.setAttribute("id", "r" + r.id);
        reimElement.setAttribute("data-id", r.id);
        reimElement.setAttribute("data-purpose", r.purpose);
        reimElement.setAttribute("data-amount", r.amount);
        reimElement.setAttribute("data-idEmployee", r.idEmployee);
        reimElement.setAttribute("data-idManager", r.idManager);
        reimElement.setAttribute("data-status", r.status);
        reimElement.setAttribute("data-eFirstName", "TODO");
        reimElement.setAttribute("data-eLastName", "TODO");
        reimElement.setAttribute("href", "#");
        reimElement.setAttribute("class", "list-group-item list-group-item-action");

        if (status == "pending") { // Pending reimbursements can be resolved in modals.
            reimElement.setAttribute("data-target", "#view-reimbursement");
            reimElement.setAttribute("data-toggle", "modal");
            reimElement.setAttribute("onclick", "loadSingleReimbursement('r" + r.id + "')");
        }
        else { // Resolved reimbursements need the manager who resolved it.
            // TODO: REQUEST FOR THE NAME OF MANAGER and RECORD IT
        }

        reimElement.innerHTML = `${amount}  ${purpose}  ${idEmployee}  ${status}`;

        if (baseUrl == baseUrl1) {document.getElementById("all-reim").appendChild(reimElement);}
        if (baseUrl == baseUrl2) {document.getElementById("pending-reim").appendChild(reimElement);}
        if (baseUrl == baseUrl3) {document.getElementById("resolved-reim").appendChild(reimElement);}
    }
}

function loadSingleReimbursement(reimbHtmlId){
    // let baseUrl = "http://localhost:8080/Project1/api/reimb/"; // Certain reimbursements with reimbId.

    let r = document.getElementById(reimbHtmlId);
    let purpose = r.getAttribute("data-purpose");
    let firstName = r.getAttribute("data-eFirstName");
    let lastName = r.getAttribute("data-eLastName");
    let amount = r.getAttribute("data-amount");
    let id = r.getAttribute("data-id");

    let htmlAmount = document.getElementById("single-reimb-amount");
    let htmlName = document.getElementById("single-reimb-name");
    let htmlPurpose = document.getElementById("single-reimb-purpose");
    let htmlId = document.getElementById("single-reimb-id");

    htmlAmount.innerHTML = amount;
    htmlName.innerHTML = firstName + " " + lastName;
    htmlPurpose.innerHTML = purpose;
    htmlId.innerHTML = id; // DO NOT CHANGE. can make this hidden if needed.
}

function approveReimbursement() {
    resolveReimbursement("approved");
}

function rejectReimbursement() {
    resolveReimbursement("rejected");
}


function resolveReimbursement(newStatus) {
    let baseUrl = "http://localhost:8080/Project1/resolvereimbursement/";
    let reimbId = document.getElementById("single-reimb-id").innerHTML;
    let token = sessionStorage.getItem("token");
    let tokenArr = token.split(":");
    let dataPack = {id: null, purpose: null, amount: null, idEmployee: null, idManager: tokenArr[0], status: newStatus};

    let myJSON = JSON.stringify(dataPack);

    sendAjaxPostResolveReimbursement(baseUrl+reimbId, checkTokenAndLoadPage, myJSON);
}

// resolveReimbursement AJAX helper. 
function sendAjaxPostResolveReimbursement(url, callback, data){
	let xhr = new XMLHttpRequest();
    xhr.open("POST", url);

	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status===200){
            callback(this);
		} else if (this.readyState===4){
            console.log("Ajax failure.");
		}
	} // probably should authenticate to post; for later.
	xhr.send(data);
}


// TODO: Redirect to view employees tab.


// Commands to execute on load.
// ALL EVENT LISTENERS
document.getElementById("logout-btn").addEventListener("click", logout);
document.getElementById("update-profile-btn").addEventListener("click", updateProfile);
document.getElementById("approve-btn").addEventListener("click", approveReimbursement);
document.getElementById("reject-btn").addEventListener("click", rejectReimbursement);
document.getElementById("view-employees-btn").addEventListener("click", viewEmployees);

// LOAD PAGE + Authentication
let token = checkTokenAndLoadPage();