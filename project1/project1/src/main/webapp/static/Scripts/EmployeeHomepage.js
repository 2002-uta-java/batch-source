let token = sessionStorage.getItem("token");

if(!token) {
    window.location.href="http://localhost:8080/project1/login";
} else {
    let tokenArr = token.split(":");
	if(tokenArr.length === 2) {
        let baseUrl = "http://localhost:8080/project1/api/employees/";
		sendAjaxGet(baseUrl+"id="+tokenArr[0], displayName);
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

function displayName(xhr){
	let employee = JSON.parse(xhr.response);
	document.getElementById("welcome").innerHTML = `Welcome ${employee.firstName.toUpperCase()} ${employee.lastName.toUpperCase()}`;
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