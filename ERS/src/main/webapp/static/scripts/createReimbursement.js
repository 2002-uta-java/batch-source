/**
 *  createReimbursement
 */
document.getElementById("add-btn").addEventListener("click", createReimbursement);

function createReimbursement() {
	let description = document.getElementById("description").value;
	let category = document.getElementById("category").value;
	let description = document.getElementById("description").value;
	let cost = document.getElementById("cost").value;
	let status = document.getElementById("status").value;
	let comments = document.getElementById("comments").value;
    let eid    = document.getElementById("employeeId").value;

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
    let requestBody = `cost=${cost}&eid=${eid}`;
    xhr.send(requestBody);
}