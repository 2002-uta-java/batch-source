// Additional REIMBURSEMENT INFORMATION IF MANAGER ------------------------------------------------

// reimbursements for all employees URL
const allEmpReimRequestUrl = `http://localhost:8080/project_1/reimburs`;

// status api URL
const statusUrl = 'http://localhost:8080/project_1/status';

// check if is_manager was set
if (tokenArray[1] == 1) {
    sendAjaxGet(allEmpReimRequestUrl, displayAllReimHist);
}

// function call
sendAjaxGet(statusUrl, populateReimStatusOptions);

// adding event listener to update reimbursement form button
document.getElementById("update-reim-button").addEventListener("click", updateReim);

// populate retrieved employees' reimbursements
function displayAllReimHist(reimJason) {
    let reimbursements = JSON.parse(reimJason);
    console.table(reimbursements);

    let table = document.getElementById("all-reim-hist-table");

    for (let reim of reimbursements) {
        let newRow = document.createElement("tr");

        newRow.innerHTML = `<td>${reim.dateCreated}</td><td>${reim.employeeName}</td><td>${reim.category}</td><td>${reim.amount}</td><td>${reim.status}</td><td>${reim.approvedByName}</td>`;

        table.appendChild(newRow);

    }
}

// retrieve and populate the options for the select input of the reimbursement category selector
function populateReimStatusOptions(staJson) {
    let statuses = JSON.parse(staJson);
    console.table(statuses);
    console.log("here!")

    let selectList = document.getElementById("select-status");

    for (let sta of statuses) {
        let newOption = document.createElement("option");

        newOption.value = `${sta.id}`;
        newOption.innerText = `${sta.name}`;

        selectList.appendChild(newOption);
    }
}

// if the update button is clicked in the update reimbursement form, update the reimbursement
function updateReim() {

    let tokenArray = sessionStorage.token.split(':',2);

    let currentUser = tokenArray[0];

    let statusId = document.getElementById("select-status").value;
    let emailInput = document.getElementById("comment-field").value;

    let url = `http://localhost:8080/project_1/reimburs?status=${statusId}&comment=${comment}`;

    let xhr = new XMLHttpRequest();
    xhr.open("PUT", url);

    xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
            console.log("we got inside the 4 && 200 !!!");
            // probably should reload page here
		}
		
		else if (xhr.readyState == 4) {
			console.log("Mistakes were made...");
		}
	}
	xhr.send();
}