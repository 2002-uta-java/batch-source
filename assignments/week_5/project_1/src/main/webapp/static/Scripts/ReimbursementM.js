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
//sendAjaxGet(statusUrl, populateReimStatusOptions);

// adding event listener to update reimbursement form button
// document.getElementById("update-reim-button").addEventListener("click", updateReim);

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
                    <button id="all-pendding-reim-${reim.id}" class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapse${reim.id}" aria-expanded="false" aria-controls="collapse${reim.id}">
                        <table class="table table-borderless" id="no-space">
                            <tbody>
                                <tr>
                                    <td id="no-space"><strong>${reim.dateCreated}</strong></td>
                                    <td id="no-space">${reim.employeeName}</td>
                                    <td id="no-space">$${reim.amount}.00</td>
                                    <td id="no-space"><strong>for:</strong> ${reim.category}</td>
                                    <td id="no-space"><strong>status:</strong> ${reim.status}</td>
                                </tr>
                            </tbody>
                        </table>
                    </button>
                </h2>
            </div>
            <div id="collapse${reim.id}" class="collapse" aria-labelledby="heading${reim.id}" data-parent="#accordionAllPendingReimbursements">
                <div class="card-body">
                    <div id="all-pendding-reim-${reim.id}-desc">Description Here</div>
                    <div id="update-all-pendding-reim-${reim.id}-form" class="float-right"></div>
                    <br>
                </div>
            </div>`;
        
            accordionAllPending.appendChild(newDiv);

            document.getElementById(`all-pendding-reim-${reim.id}`).addEventListener("click", () => {addUpdateAllPenddingCollapse(reim)});

        } else {
            newDiv.innerHTML = `
            <div class="card-header" id="heading${reim.id}">
                <h2 class="mb-0">
                    <button id="all-processed-reim-${reim.id}" class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapse${reim.id}" aria-expanded="false" aria-controls="collapse${reim.id}">
                        <table class="table table-borderless" id="no-space">
                            <tbody>
                                <tr>
                                    <td id="no-space"><strong>${reim.dateCreated}</strong></td>
                                    <td id="no-space">${reim.employeeName}</td>
                                    <td id="no-space">$${reim.amount}.00</td>
                                    <td id="no-space"><strong>for:</strong> ${reim.category}</td>
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
                    <div id="all-processed-reim-${reim.id}-desc">Description Here</div>
                    <div id="update-all-processed-reim-${reim.id}-form" class="float-right"></div>
                    <br>
                </div>
            </div>`;

            accordionAllProcessed.appendChild(newDiv);

            document.getElementById(`all-processed-reim-${reim.id}`).addEventListener("click", () => {addUpdateAllProcessedCollapse(reim)});

        }
    }
}


function addUpdateAllPenddingCollapse(reim) {

    console.log(reim);

    let descDiv = document.getElementById(`all-pendding-reim-${reim.id}-desc`);
    let formDiv = document.getElementById(`update-all-pendding-reim-${reim.id}-form`);

    descDiv.innerText = reim.discussion;

    if (reim.statusId < 4) {

        // <button id="more-info-embedded-btn-${reim.id}" type="button" class="btn btn-secondary">More Info Needed</button>

        formDiv.innerHTML = `
            <button id="deny-pendding-btn-${reim.id}" type="button" class="btn btn-danger">Deny</button>
            <button id="approve-pendding-btn-${reim.id}" type="button" class="btn btn-success">Approve</button>`;

            //document.getElementById(`more-info-embedded-btn-${reim.id}`).addEventListener("click", () => {processReimUpdate(reim.id, 3)});
            document.getElementById(`approve-pendding-btn-${reim.id}`).addEventListener("click", () => {processReimUpdate(reim.id, 4)});
            document.getElementById(`deny-pendding-btn-${reim.id}`).addEventListener("click", () => {processReimUpdate(reim.id, 5)});
    }
}


function addUpdateAllProcessedCollapse(reim) {

    console.log(reim);

    let descDiv = document.getElementById(`all-processed-reim-${reim.id}-desc`);
    let formDiv = document.getElementById(`update-all-processed-reim-${reim.id}-form`);

    descDiv.innerText = reim.discussion;

    if (reim.statusId < 4) {

        // <button id="more-info-embedded-btn-${reim.id}" type="button" class="btn btn-secondary">More Info Needed</button>

        formDiv.innerHTML = `
            <button id="deny-processed-btn-${reim.id}" type="button" class="btn btn-danger">Deny</button>
            <button id="approve-processed-btn-${reim.id}" type="button" class="btn btn-success">Approve</button>`;

            //document.getElementById(`more-info-embedded-btn-${reim.id}`).addEventListener("click", () => {processReimUpdate(reim.id, 3)});
            document.getElementById(`approve-processed-btn-${reim.id}`).addEventListener("click", () => {processReimUpdate(reim.id, 4)});
            document.getElementById(`deny-processed-btn-${reim.id}`).addEventListener("click", () => {processReimUpdate(reim.id, 5)});
    }
}


// // retrieve and populate the options for the select input of the reimbursement category selector
// function populateReimStatusOptions(staJson) {
//     let statuses = JSON.parse(staJson);
//     console.table(statuses);
//     console.log("here!")

//     let selectList = document.getElementById("select-status");

//     for (let sta of statuses) {
//         let newOption = document.createElement("option");

//         newOption.value = `${sta.id}`;
//         newOption.innerText = `${sta.name}`;

//         selectList.appendChild(newOption);
//     }
// }


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