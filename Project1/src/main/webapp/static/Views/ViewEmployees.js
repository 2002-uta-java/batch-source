// Refresh elements in the page.
function loadPage() {
    // Quick authentication check.
    let token = sessionStorage.getItem("token");
    let tokenArr = token.split(":");

    if(!token){
        window.location.href="http://localhost:8080/Project1/login";
        return;
    }

    let baseUrl1 = "http://localhost:8080/Project1/api/users/" + tokenArr[0]; // current user
    let baseUrl2 = "http://localhost:8080/Project1/api/users"; // all users
    sendAjaxGet(baseUrl1, loadUser, token);
    sendAjaxGet(baseUrl2, loadEmployees, token);

    // TODO: show reimb (shouldnt have to clear, can handle that in separate thing)
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

    // Load profile information (view profile button).
    document.getElementById("modal-name").innerHTML = `${user.firstName} ${user.lastName}`;
    document.getElementById("modal-email").innerHTML = `${user.email}`;
    document.getElementById("modal-position").innerHTML = `${user.position}`;
    document.getElementById("modal-gender").innerHTML = `${user.gender}`;
    document.getElementById("modal-id").innerHTML = `${user.id}`;
    // TODO: picture depending on gender (id=) (is also in homemanager.js)
}

function loadEmployees(xhr) {
    let employees = JSON.parse(xhr.response);
    console.log(employees);
    // Load employees list (both employees and managers).
    // TODO: Display list of users. Need logic to separate into empls/manags.

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



// TODO: viewONLYEmployees.html + viewAllEmployees function + others (new page, active by default)
// TODO: viewONLYManagers (click event)


// TODO: Redirect to manager homepage tab.






// Commands to execute on load.
// ALL EVENT LISTENERS
document.getElementById("logout-btn").addEventListener("click", logout);
document.getElementById("update-profile-btn").addEventListener("click", updateProfile);
document.getElementById("manager-home-btn").addEventListener("click", viewManagerHome);

// LOAD PAGE
loadPage();