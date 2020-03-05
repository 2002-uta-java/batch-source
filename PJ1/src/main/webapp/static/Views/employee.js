//stores our token for the time that the tab is open. once open it is flushed out 
//then we check if there is a token available
let token = sessionStorage.getItem("token");

if (!token) {
    window.location.href = "http://localhost:8080/PJ1/login";
} else {
    const tokenArr = token.split(":");
    if (tokenArr.length === 2) {
        //getting our employees and reimbursements from the server
        const employeeUrl = "http://localhost:8080/PJ1/api/employees?username=";
        const reimbursementUrl = "http://localhost:8080/PJ1/api/reimbursements";
        // getting our employees by name
        sendAjaxGetEmployees(employeeUrl + tokenArr[0], displayEmployee);
        // gets reimbursements by status = true and name
        sendAjaxGetReimbursements(reimbursementUrl + "?status=true" + `&username=${tokenArr[0]}`, displayResReimbursements);
        // gets reimbursements by status = false and name
        sendAjaxGetReimbursements(reimbursementUrl + "?status=false" + `&username=${tokenArr[0]}`, displayPenReimbursements);

    } else { // redirects back to login page if sessionStorage is empty.
        window.location.href = "http://localhost:8080/PJ1/login";
    }
}
// sending an ajax request to get employees
function sendAjaxGetEmployees(url, callback) {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", url);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            callback(xhr.response);
        } else if (xhr.readyState === 4) {
            window.location.href = "http://localhost:8080/PJ1/login";
        }
    }
    xhr.setRequestHeader("Authorization", token);
    xhr.send();
}

//adding our employees info to our page in the card section
function displayManager(xhrResponse) {
    let employee = JSON.parse(xhrResponse);
    document.getElementById("emp_header").innerHTML = `${employee.name}`;
    document.getElementById("emp_card_email").innerHTML = `${employee.email}`;
    document.getElementById("emp_card_id").innerHTML = `${employee.emp_id}`;
}


//sending ajax requests for reimburstments
function sendAjaxGetReimbursements(url, callback) {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", url);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            callback(xhr.response);
        }
    }
    xhr.send();
}
//now we display our pending reimbustmenst

function displayPenReimbursements(xhrResponse) {
    let reimbursements = JSON.parse(xhrResponse);
    const reimTable = document.getElementById("reim_tableP");
    //looping through our reimburstments to add them to the table
    for (reim of reimbursements) {
        const newTr = document.createElement("tr");
        newTr.innerHTML = `<td>${reim.status}</td><td>$${reim.aproved_by}</td><td>${reim.request_id}</td><td>${reim.name}</td>`;
        reimTable.appendChild(newTr);
    }
}
// don't forget about the aproved ones
function displayResReimbursements(xhrResponse) {
    let reimbursements = JSON.parse(xhrResponse);
    const reimTable = document.getElementById("reim_tableA");
    reimTable.hidden = false;


    for (reim of reimbursements) {
        const newTr = document.createElement("tr");
        newTr.innerHTML = `<td>${reim.status}</td><td>$${reim.aproved_by}</td><td>${reim.request_id}</td><td>${reim.name}</td>`;
        reimTable.appendChild(newTr);
    }
}

//now we log the user out
document.getElementById("logout").addEventListener("click", function () {
    sessionStorage.clear();
    window.location.href = "http://localhost:8080/PJ1/login";
});

//now we get a request
document.getElementById("req_submit").addEventListener("click", makeReq);

// after the submit button is clicked this function runs where we request a post method to create a new reimbursement
function makeReq() {
    const url = "http://localhost:8080/PJ1/create";
    let xhr = new XMLHttpRequest();
    xhr.open("POST", url);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            window.location.href = "http://localhost:8080/PJ1/employee";
        }
    }
    const tokenArr = token.split(":"); // to get the username.
    // assign the input field values to send to the server.
    const name = document.getElementById("make_req_id").value;
    const description = document.getElementById("make_req_rname").value;

    // checks if the name and description fields are filled.
    if (!name || !description) {
        document.getElementById("fill-inputs-text").hidden = false; // shows error if not
        return; // also returns.
    }

    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")

    let requestBody = `employee_name=${name}&name=${description}`;

    xhr.send(requestBody);
}
// resolve.
function resolveReim(e) {
    const url = "http://localhost:8080/PJ1/updateReim";
    let xhr = new XMLHttpRequest();
    xhr.open("POST", url);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            window.location.href = "http://localhost:8080/PJ1/manager"; // refreshes the page to see new changes.
        }
    }
    const tokenArr = token.split(":");
    const reim_id = e.path[2].children[0].outerText; // this is acquired through the event object.
    const managerName = tokenArr[0];
    const status = "true"; // Since we clicked the resolve button, the status is now resolved

    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    let requestBody = `reim_id=${request_id}&managerName=${aproved_by}&status=${status}`;

    xhr.send(requestBody);
}
//logging our teacher int
document.getElementById("logout").addEventListener("click", function () {
    sessionStorage.clear();
    window.location.href = "http://localhost:8080/PJ1/login";
});
