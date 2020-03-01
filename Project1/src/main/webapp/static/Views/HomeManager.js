// Check token to remain on page.
function checkToken() {
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

    // Load profile name (top right).
    document.getElementById("profile-name").innerHTML = ` ${user.firstName} ${user.lastName} `;

    // Load profile information (view profile button).
    document.getElementById("modal-name").innerHTML = `${user.firstName} ${user.lastName}`;
    document.getElementById("modal-email").innerHTML = `${user.email}`;
    document.getElementById("modal-position").innerHTML = `${user.position}`;
    document.getElementById("modal-gender").innerHTML = `${user.gender}`;
    document.getElementById("modal-id").innerHTML = `${user.id}`;
    // TODO: picture depending on gender (id=)
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
        sendAjaxPost(baseUrl+tokenArr[0], checkToken, myJSON);
    }
}

// updateProfile AJAX helper. 
function sendAjaxPost(url, callback, data){
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
	}
	xhr.send(data);
}

// TODO: function that handles viewing/editing your profile.

// MANAGER-EXCLUSIVE FUNCTIONS
// TODO: viewALLReimbursements (active by default)

// TODO: viewPENDINGReimbursements (click event)

// TODO: viewRESOLVEDReimbursements (click event)

// TODO: resolveReimbursementTool (click event)

// TODO: viewONLYEmployees.html + viewAllEmployees function + others (new page, active by default)
// TODO: viewONLYManagers (click event)




// Commands to execute on load.

// ALL EVENT LISTENERS
document.getElementById("logout-btn").addEventListener("click", logout);
document.getElementById("update-profile-btn").addEventListener("click", updateProfile);

// TODO: get all reimbursements and display them (with proper features)
let token = checkToken();