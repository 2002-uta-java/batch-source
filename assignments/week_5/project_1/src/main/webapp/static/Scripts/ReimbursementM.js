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

    let accordionAllPending = document.getElementById("accordionAllPendingReimbursements");
    let accordionAllProcessed = document.getElementById("accordionAllProcessedReimbursements");

    //let table = document.getElementById("all-reim-hist-table");

    for (let reim of reimbursements) {

        let newDiv = document.createElement("div");

        newDiv.className = "card";

        if (reim.statusId < 4) {
            newDiv.innerHTML = `
            <div class="card-header" id="heading${reim.id}">
                <h2 class="mb-0">
                    <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapse${reim.id}" aria-expanded="false" aria-controls="collapse${reim.id}">
                        <table class="table table-borderless" id="no-space">
                            <tbody>
                                <tr>
                                    <td id="no-space"><strong>${reim.dateCreated}</strong></td>
                                    <td id="no-space">${reim.employeeName}</td>
                                    <td id="no-space"><strong>category:</strong> ${reim.category}</td>
                                    <td id="no-space"><strong>amount:</strong> $${reim.amount}.00</td>
                                    <td id="no-space"><strong>status:</strong> ${reim.status}</td>
                                </tr>
                            </tbody>
                        </table>
                    </button>
                </h2>
            </div>
            <div id="collapse${reim.id}" class="collapse" aria-labelledby="heading${reim.id}" data-parent="#accordionAllPendingReimbursements">
                <div class="card-body">
                    Description Here
                </div>
            </div>`;
        
            accordionAllPending.appendChild(newDiv);
        } else {
            newDiv.innerHTML = `
            <div class="card-header" id="heading${reim.id}">
                <h2 class="mb-0">
                    <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapse${reim.id}" aria-expanded="false" aria-controls="collapse${reim.id}">
                        <table class="table table-borderless" id="no-space">
                            <tbody>
                                <tr>
                                    <td id="no-space"><strong>${reim.dateCreated}</strong></td>
                                    <td id="no-space">${reim.employeeName}</td>
                                    <td id="no-space"><strong>category:</strong> ${reim.category}</td>
                                    <td id="no-space"><strong>amount:</strong> $${reim.amount}.00</td>
                                    <td id="no-space"><strong>status:</strong> ${reim.status}</td>
                                    <td id="no-space"><strong>by:</strong> ${reim.approvedByName}</td>
                                </tr>
                            </tbody>
                        </table>
                    </button>
                </h2>
            </div>
            <div id="collapse${reim.id}" class="collapse" aria-labelledby="heading${reim.id}" data-parent="#accordionAllProcessedReimbursements">
                <div class="card-body">
                    Description Here
                </div>
            </div>`;

            accordionAllProcessed.appendChild(newDiv);
        }
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

// // populate retrieved employees' reimbursements
// function displayAllReimHist(reimJason) {
//     let reimbursements = JSON.parse(reimJason);
//     console.table(reimbursements);

//     let accordionAllPending = document.getElementById("accordionPendingReimbursements");
//     let accordionAllProcessed = document.getElementById("accordionProcessedReimbursements");

//     //let table = document.getElementById("all-reim-hist-table");

//     for (let reim of reimbursements) {
//         let newRow = document.createElement("tr");

//         newRow.innerHTML = `<td>${reim.dateCreated}</td><td>${reim.employeeName}</td><td>${reim.category}</td><td>${reim.amount}</td><td>${reim.status}</td><td>${reim.approvedByName}</td>`;

//         table.appendChild(newRow);

//     }
// }