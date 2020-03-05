// ADDITIONAL EMPLOYEE INFORMATION IF MANAGER -----------------------------------------------------

// all employee info URL
const allEmpInfoRequestUrl = `http://localhost:8080/project_1/emp-info`;

// check if is_manager was set
if (tokenArray[1] == 1) {
    sendAjaxGet(allEmpInfoRequestUrl, displayAllEmpInfo);
}

// display all employees' information
function displayAllEmpInfo(empJason) {
    let employee = JSON.parse(empJason);
    console.table(employee);

    let accordionEmployee = document.getElementById("accordionAllEmployees");

    for (let emp of employee) {

        let newDiv = document.createElement("div");

        newDiv.className = "card";

        newDiv.innerHTML = `
            <div class="card-header" id="heading${emp.id}">
                <h2 class="mb-0">
                    <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapse${emp.id}" aria-expanded="false" aria-controls="collapse${emp.id}">
                        <table class="table table-borderless" id="no-space">
                            <tbody>
                                <tr>
                                    <td id="no-space"><strong>${emp.firstName} ${emp.lastName}</strong></td>
                                    <td id="no-space"><strong>${emp.department}</strong></td>
                                    <td id="no-space"><strong>email:</strong> ${emp.email}</td>
                                    <td id="no-space"><strong>address:</strong> ${emp.address}</td>
                                </tr>
                            </tbody>
                        </table>
                    </button>
                </h2>
            </div>
            <div id="collapse${emp.id}" class="collapse" aria-labelledby="heading${emp.id}" data-parent="#accordionAllEmployees">
                <div class="card-body">
                    <div class="accordion" id="accordionEmbededEmployeeReims${emp.id}">
                    </div>
                </div>
            </div>`;

        accordionEmployee.appendChild(newDiv);

        addEmbededReims(emp.id);
    }
}

function addEmbededReims(empId) {

    let newSingleEmpReimRequestUrl = `http://localhost:8080/project_1/reimburs?reimFor=${empId}&manager=duncan`;

    sendAjaxGet(newSingleEmpReimRequestUrl, displayEmbededReimHist);
}


function displayEmbededReimHist(reimJason) {
    let reimbursements = JSON.parse(reimJason);
    console.table(reimbursements);
    console.log("in embbeded area")

    for (let reim of reimbursements) {

        let accordionEmbeded = document.getElementById(`accordionEmbededEmployeeReims${reim.employee}`);

        let newDiv = document.createElement("div");

        newDiv.className = "card";

        newDiv.innerHTML = `
        <div class="card-header" id="headingEmbedded${reim.id}">
            <h2 class="mb-0">
                <button id="embedded-reim-${reim.id}" class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseEmbedded${reim.id}" aria-expanded="false" aria-controls="collapseEmbedded${reim.id}">
                    <table class="table table-borderless" id="no-space">
                        <tbody>
                            <tr>
                                <td id="no-space"><strong>${reim.dateCreated}</strong></td>
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
        <div id="collapseEmbedded${reim.id}" class="collapse" aria-labelledby="headingEmbedded${reim.id}" data-parent="#accordionEmbededEmployeeReims${reim.employee}">
            <div class="card-body">
                <div id="embedded-reim-${reim.id}-desc">Description Here</div>
                <div id="update-embedded-reim-${reim.id}-form" class="float-right"></div>
                <br>
            </div>
        </div>`;
    
        accordionEmbeded.appendChild(newDiv);

        document.getElementById(`embedded-reim-${reim.id}`).addEventListener("click", () => {addUpdateCollapseEmbedded(reim)});

    }
}

function addUpdateCollapseEmbedded(reim) {

    console.log(reim);

    let descDiv = document.getElementById(`embedded-reim-${reim.id}-desc`);
    let formDiv = document.getElementById(`update-embedded-reim-${reim.id}-form`);

    descDiv.innerText = reim.discussion;

    if (reim.statusId < 4) {

        // <button id="more-info-embedded-btn-${reim.id}" type="button" class="btn btn-secondary">More Info Needed</button>

        formDiv.innerHTML = `
            <button id="deny-embedded-btn-${reim.id}" type="button" class="btn btn-danger">Deny</button>
            <button id="approve-embedded-btn-${reim.id}" type="button" class="btn btn-success">Approve</button>`;

            //document.getElementById(`more-info-embedded-btn-${reim.id}`).addEventListener("click", () => {processReimUpdate(reim.id, 3)});
            document.getElementById(`approve-embedded-btn-${reim.id}`).addEventListener("click", () => {processReimUpdate(reim.id, 4)});
            document.getElementById(`deny-embedded-btn-${reim.id}`).addEventListener("click", () => {processReimUpdate(reim.id, 5)});
    }
}


//<button type="button" class="btn btn-link">Link</button>




//         // display all employees' information
// function displayAllEmpInfo(empJason) {
//     let employee = JSON.parse(empJason);
//     console.table(employee);

//     let table = document.getElementById("all-info-table");
//     let accordionPending = document.getElementById("accordionPendingReimbursements");

//     for (let emp of employee) {
//         let newRow = document.createElement("tr");

//         newRow.innerHTML = `<td>${emp.department}</td><td>${emp.firstName}</td><td>${emp.lastName}</td><td>${emp.email}</td>`;

//         table.appendChild(newRow);

//     }
// }


// retrieve and populate the options for the select input of the reimbursement category selector
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