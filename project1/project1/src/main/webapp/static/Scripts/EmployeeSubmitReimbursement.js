let token = sessionStorage.getItem("token");

if(!token) {
    window.location.href="http://localhost:8080/project1/login";
}

document.getElementById("submit-btn").addEventListener("click", requestReimbursement);

function requestReimbursement(xhr){
    let expense = document.getElementById("expense").value;
    let rtype = document.getElementById("rtype").value;

    if(expense && rtype) {
        let xhr = new XMLHttpRequest();
        let url = "http://localhost:8080/project1/api/reimbursements/create";
        xhr.open("POST", url);
        
        xhr.onreadystatechange = function(){
        	if(xhr.readyState == 4 && xhr.status == 200){
                window.location.href=`http://localhost:8080/project1/employee-submit-reimbursement`;
        	} 
        	else if (xhr.readyState == 4){
        		console.log("unable to submit reimbursement");
        	}
        }
        xhr.setRequestHeader("Authorization", token);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        let requestBody = `expense=${expense}&rtype=${rtype}`;
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