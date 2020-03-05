let token = sessionStorage.getItem("token");

if(!token) {
    window.location.href="http://localhost:8080/project1/login";
} else {
    let tokenArr = token.split(":");
	if(tokenArr.length === 2) {
        let baseUrl = "http://localhost:8080/project1/api/employees/id=";
		sendAjaxGet(baseUrl+tokenArr[0], myInfo);
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

function myInfo(xhr){
    let employee = JSON.parse(xhr.response);
    let eid = document.getElementById("eid");
    eid.innerHTML = employee.eid;
    let username = document.getElementById("username");
    username.innerHTML = employee.login;
    let password = document.getElementById("password");
    password.innerHTML = employee.password;
    let title = document.getElementById("title");
    title.innerHTML = employee.title.toUpperCase();
    let firstName = document.getElementById("first-name");
    firstName.innerHTML = employee.firstName.toUpperCase();
    let lastName = document.getElementById("last-name");
    lastName.innerHTML = employee.lastName.toUpperCase();
    let email = document.getElementById("email");
    email.innerHTML = employee.email;
}

document.getElementById("update-btn").addEventListener("click", updateInfo);

function updateInfo(){
    let eidStr = token.split(":")[0];
    let username = document.getElementById("new-username").value;
    let password = document.getElementById("new-password").value;
    let firstName = document.getElementById("new-first-name").value;
    let lastName = document.getElementById("new-last-name").value;
    let email = document.getElementById("new-email").value;

    if(username || password || firstName || lastName || email) {
        if(!username) {
            username = document.getElementById("username").innerText;
        }
        if(!password) {
            password = document.getElementById("password").innerText;
        }
        if(!firstName) {
            firstName = document.getElementById("first-name").innerText;
        }
        if(!lastName) {
            lastName = document.getElementById("last-name").innerText;
        }
        if(!email) {
            email = document.getElementById("email").innerText;
        }
        let xhr = new XMLHttpRequest();
        let url = "http://localhost:8080/project1/api/employees";
        xhr.open("POST", url);
        xhr.onreadystatechange = function(){
        	if(xhr.readyState == 4 && xhr.status == 200){
                window.location.href=`http://localhost:8080/project1/employee-profile`;
        	} 
        	else if (xhr.readyState == 4){
        		console.log("unable to update employee");
        	}
        }
        xhr.setRequestHeader("Authorization", token);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        let requestBody = `eidStr=${eidStr}&username=${username}&password=${password}&firstName=${firstName}&lastName=${lastName}&email=${email}`;
        xhr.send(requestBody);
    } else {
        console.log("invalid");
    }
}

document.getElementById("home").addEventListener("click", goHome);
document.getElementById("submit-reimbursement").addEventListener("click", goSubmitReimbursement);
document.getElementById("pending-reimbursements").addEventListener("click", goPendingReimbursements);
document.getElementById("resolved-reimbursements").addEventListener("click", goResolvedReimbursements);
document.getElementById("profile").addEventListener("click", goProfile);
document.getElementById("logout").addEventListener("click", goLogout);

function goHome(){
    window.location.href="http://localhost:8080/project1/employee-homepage";
}

function goSubmitReimbursement() {
    window.location.href="http://localhost:8080/project1/employee-submit-reimbursement";
}

function goPendingReimbursements() {
    window.location.href="http://localhost:8080/project1/employee-pending-reimbursements";
}

function goResolvedReimbursements() {
    window.location.href = "http://localhost:8080/project1/employee-resolved-reimbursements";
}

function goProfile() {
    window.location.href = "http://localhost:8080/project1/employee-profile";
}

function goLogout() {
    sessionStorage.clear();
    window.location.href = "http://localhost:8080/project1/login";
}