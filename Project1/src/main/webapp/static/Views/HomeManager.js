// Check token to remain on page.
function checkToken() {
	let token = sessionStorage.getItem("token");

	if(!token){
		window.location.href="http://localhost:8080/Project1/login";
	} else {
		let tokenArr = token.split(":");
		console.log(tokenArr);
		if(tokenArr.length===2){
            // Authentication success! Page will load (name, reimbursements)
            let baseUrl = "http://localhost:8080/Project1/api/users/";
            sendAjaxGet(baseUrl+tokenArr[0], loadName, token);
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
			// window.location.href="http://localhost:8080/Project1/login";
		}
	}
	xhr.setRequestHeader("Authorization", token);
	xhr.send();
}

// Load the profile name into the navbar (top right).
function loadName(xhr){
    let user = JSON.parse(xhr.response);
    document.getElementById("profile-name").innerHTML = ` ${user.firstName} ${user.lastName} `;
}

// Remove user token and return to the login menu.
function logout() {
    sessionStorage.clear();
    window.location.href="http://localhost:8080/Project1/login";
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
// TODO: get all reimbursements and display them (with proper features)
let token = checkToken();
loadName();

document.getElementById("logout-btn").addEventListener("click", logout);