// Check token and load appropriate user homepage. Redirected if bad token.
function checkToken() {
	let token = sessionStorage.getItem("token");
	console.log(token);

	if(!token){
		window.location.href="http://localhost:8080/Project1/login";
	} else {
		let tokenArr = token.split(":");
		console.log(tokenArr);
		if(tokenArr.length===2){
			let baseUrl = "http://localhost:8080/Project1/api/users/";
			sendAjaxGet(baseUrl+tokenArr[0], displayHomepage, token); // user request here.
		} else {
			window.location.href="http://localhost:8080/Project1/login";
		}
	}
}

// checkToken AJAX helper. Will displayHomepage if successful authentication to specific user.
function sendAjaxGet(url, callback, token){
	let xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status===200){
			callback(this);
		} else if (this.readyState===4){
			window.location.href="http://localhost:8080/Project1/login";
		}
	}
	xhr.setRequestHeader("Authorization", token);
	xhr.send();
}

// Decides whether to display the Manager or Employee homepage.
function displayHomepage(xhr){
	let user = JSON.parse(xhr.response);
    console.log(user); 
    
    document.getElementById("user").innerHTML = `Welcome ${user.firstName} ${user.lastName}`;

    if (user.position == "manager") {
        // Display MANAGER homepage.
    }
    else if (user.position == "employee") {
        // Display EMPLOYEE homepage.
    }
	
}

// TODO: function that handles click events for LOGGING OUT.
function logout () {

}

// TODO: function that handles viewing/editing your profile.

// MANAGER-EXCLUSIVE FUNCTIONS
// TODO: viewALLReimbursements (active by default)

// TODO: viewPENDINGReimbursements (click event)

// TODO: viewRESOLVEDReimbursements (click event)

// TODO: resolveReimbursementTool (click event)

// TODO: viewONLYEmployees.html + viewAllEmployees function + others (new page, active by default)
// TODO: viewONLYManagers (click event)

// EMPLOYEE-EXCLUSIVE FUNCTIONS
// TODO: viewALLMYReimbursements (active by default) + managerlink function

// TODO: viewALLMYPendingReimbursements (click event)

// TODO: viewALLMYResolvedReimbursements (click event)


// Commands to execute on load.
checkToken();