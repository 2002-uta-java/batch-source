/**
 * 
 */

document.getElementById("add-btn").addEventListener("click", createReimbursement);

function createReimbursement() {
	let amount = document.getElementById("amount").value;
    let eid    = document.getElementById("employeeid").value;

    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8080/ers/addReimbursement";
    xhr.open("POST", url);

    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            let auth = xhr.getResponseHeader("Authorization");
            sessionStorage.setItem("token", auth);
            window.location.href = "http://localhost:8080/ers/employeehome";
        } else if (xhr.readyState == 4) {
            console.log("Please enter all criteria");
        }
    }

    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    let requestBody = `amount=${amount}&eid=${eid}`;
    xhr.send(requestBody);
}