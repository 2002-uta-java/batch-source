/**
most of the same functionality as the employee.js
just modified for manger 
**/
let token = sessionStorage.getItem("token");

if (!token) {
    window.location.href = "http://localhost:8080/PJ1/login";
} else {
    const tokenArr = token.split(":");
    if (tokenArr.length === 2) {
        const employeeUrl = "http://localhost:8080/PJ1/api/employees";
        const managerUrl = "http://localhost:8080/PJ1/api/managers?username=";
        const reimbursementUrl = "http://localhost:8080/PJ1/api/reimbursements";
        // gets manager by username
        sendAjaxGetManager(managerUrl + tokenArr[0], displayManager);
        // gets all employees
        sendAjaxGetEmployees(employeeUrl, displayEmployees);
        // gets all resolved reimbursements
        sendAjaxGetReimbursements(`${reimbursementUrl}?status=treu`, displayResReimbursements);
        // gets all pending reimbursements
        sendAjaxGetReimbursements(`${reimbursementUrl}?status=false`, displayPenReimbursements);

    } else {
        window.location.href = "http://localhost:8080/PJ1/login";
    }
}
//gettin our ajax
function sendAjaxGetManager(url, callback) {
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
//adding our managers info to our page in the card section
function displayManager(xhrResponse) {
    let manager = JSON.parse(xhrResponse);
    document.getElementById("man_card_header").innerHTML = `${manager.name}`;
    document.getElementById("man_email").innerHTML = `${manager.email}`;
    document.getElementById("man_id").innerHTML = `${manager.emp_id}`;
}
// sending ajax to get reimburstments
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
///look at our pending reimburstments
function displayPenReimbursements(xhrResponse) {
    let reimbursements = JSON.parse(xhrResponse);
    const reimTable = document.getElementById("pending_table");
    //looping through our reimburstments to add them to the table
    for (reim of reimbursements) {
        const newTr = document.createElement("tr");
        newTr.innerHTML = `<td>${reim.employee_name}</td<td>${reim.status}</td><td>$${reim.aproved_by}</td><td>${reim.request_id}</td><td>${reim.name}</td>`;
        reimTable.appendChild(newTr);
    }
}

// displays all resolved reimbursements
function displayResReimbursements(xhrResponse) {
    let reimbursements = JSON.parse(xhrResponse);
    const reimTable = document.getElementById("reim_tableA");

    for (reim of reimbursements) {
        const newTr = document.createElement("tr");
        newTr.innerHTML = `<td>${reim.employee_name}</td<td>${reim.status}</td><td>$${reim.aproved_by}</td><td>${reim.request_id}</td><td>${reim.name}</td>`;
        reimTable.appendChild(newTr);
    }
}
//And now we get and display all the employees
// sends a get request to retrieve all employees.
function sendAjaxGetEmployees(url, callback) {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", url);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            callback(xhr.response);
        }
    }
    xhr.send();
}

// displays all employees.
function displayEmployees(xhrResponse) {
    let employees = JSON.parse(xhrResponse);
    const empTable = document.getElementById("emp_table");

    for (emp of employees) {
        const newTr = document.createElement("tr");
        newTr.innerHTML = `<td>${emp.employee_Name}</td><td>${emp.email}</td><td>${emp.emp_id}</td>`;
        empTable.appendChild(newTr);
    }
}
