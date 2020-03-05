// REIMBURSEMENT INFORMATION ----------------------------------------------------------------------

// reimbursements for one employee (currently logged in) URL
const singleEmpReimRequestUrl = `http://localhost:8080/project_1/reimburs?reimFor=${tokenArray[0]}`;

// categories api URL
const categoryUrl = 'http://localhost:8080/project_1/cats';

// function calls
sendAjaxGet(singleEmpReimRequestUrl, displayReimHist);
sendAjaxGet(categoryUrl, populateReimCatOptions);

// adding event listener to create new reimbursement form button
document.getElementById("submit-reim-button").addEventListener("click", createNewReim);

function displayReimHist(reimJason) {
    let reimbursements = JSON.parse(reimJason);
    console.table(reimbursements);

    let accordionPending = document.getElementById("accordionPendingReimbursements");
    let accordionProcessed = document.getElementById("accordionProcessedReimbursements");

    let count = 0;

    for (let reim of reimbursements) {

        let newDiv = document.createElement("div");

        newDiv.className = "card";

        if (reim.statusId < 4) {
            newDiv.innerHTML = `
            <div class="card-header" id="heading${count}">
                <h2 class="mb-0">
                    <button id="my-pendding-reim-${reim.id}" class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapse${count}" aria-expanded="false" aria-controls="collapse${count}">
                        <table class="table table-borderless" id="no-space">
                            <tbody>
                                <tr>
                                    <td id="no-space"><strong>${reim.dateCreated}</strong></td>
                                    <td id="no-space"><strong>amount:</strong>$${reim.amount}.00</td>
                                    <td id="no-space"><strong>for:</strong> ${reim.category}</td>
                                    <td id="no-space"><strong>status:</strong> ${reim.status}</td>
                                </tr>
                            </tbody>
                        </table>
                    </button>
                </h2>
            </div>
            <div id="collapse${count}" class="collapse" aria-labelledby="heading${count}" data-parent="#accordionPendingReimbursements">
                <div class="card-body">
                    <div id="my-pendding-reim-${reim.id}-desc">Description Here</div>
                </div>
            </div>`;
        
            accordionPending.appendChild(newDiv);

            document.getElementById(`my-pendding-reim-${reim.id}`).addEventListener("click", () => {addUpdateMyPenddingCollapse(reim)});

        } else {
            newDiv.innerHTML = `
            <div class="card-header" id="heading${count}">
                <h2 class="mb-0">
                    <button id="my-processed-reim-${reim.id}" class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapse${count}" aria-expanded="false" aria-controls="collapse${count}">
                        <table class="table table-borderless" id="no-space">
                            <tbody>
                                <tr>
                                    <td id="no-space"><strong>${reim.dateCreated}</strong></td>
                                    <td id="no-space"><strong>amount:</strong> $${reim.amount}.00</td>
                                    <td id="no-space"><strong>for:</strong> ${reim.category}</td>
                                    <td id="no-space"><strong>status:</strong> ${reim.status}</td>
                                </tr>
                            </tbody>
                        </table>
                    </button>
                </h2>
            </div>
            <div id="collapse${count}" class="collapse" aria-labelledby="heading${count}" data-parent="#accordionProcessedReimbursements">
                <div class="card-body">
                    <div id="my-processed-reim-${reim.id}-desc">Description Here</div>
                </div>
            </div>`;

            accordionProcessed.appendChild(newDiv);

            document.getElementById(`my-processed-reim-${reim.id}`).addEventListener("click", () => {addUpdateMyProcessedCollapse(reim)});

        }

        count ++;
    }
}

function addUpdateMyPenddingCollapse(reim) {

    console.log(reim);

    let descDiv = document.getElementById(`my-pendding-reim-${reim.id}-desc`);

    descDiv.innerText = reim.discussion;
}


function addUpdateMyProcessedCollapse(reim) {

    console.log(reim);

    let descDiv = document.getElementById(`my-processed-reim-${reim.id}-desc`);

    descDiv.innerText = reim.discussion;
}


// retrieve and populate the options for the select input of the reimbursement category selector
function populateReimCatOptions(catJason) {
    let categories = JSON.parse(catJason);
    console.table(categories);

    let selectList = document.getElementById("select-cat");

    for (let cat of categories) {
        let newOption = document.createElement("option");

        newOption.value = `${cat.id}`;
        newOption.innerText = `${cat.name}`;

        selectList.appendChild(newOption);
    }
}

// when the submit button in the create new reimbursement form is clicked, create the reimbursement
function createNewReim() {

    let currentUser = tokenArray[0];

    let catInput = document.getElementById("select-cat").value;
    let amountInput = document.getElementById("amount-field").value;
    let descInput = document.getElementById("desc-field").value;

    console.log(catInput);
    console.log(descInput);

    let url = `http://localhost:8080/project_1/reimburs`;

    let xhr = new XMLHttpRequest();
    xhr.open("POST", url);

    xhr.onreadystatechange = function() {
        if(xhr.readyState == 4 && xhr.status == 200) {
            console.log("we got inside the 4 && 200 !!!");
            window.location.href="http://localhost:8080/project_1/home";
		}
		
		else if (xhr.readyState == 4) {
			console.log("Mistakes were made...");
        }
    }
	
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let requestBody = `employee=${currentUser}&category=${catInput}&amount=${amountInput}&desc=${descInput}`;
	xhr.send(requestBody);
}


// retrieve and populate the reimbursement history for currently logged in user
// function displayReimHist(reimJason) {
//     let reimbursements = JSON.parse(reimJason);
//     console.table(reimbursements);

//     let tablePending = document.getElementById("my-reim-hist-table");
//     let tableProcessed = document.getElementById("processed-reim-hist-table");

//     for (let reim of reimbursements) {

//         let newRow = document.createElement("tr");

//         if (reim.statusId < 4) {
//             newRow.innerHTML = `<td>${reim.dateCreated}</td><td>${reim.category}</td><td>${reim.amount}</td><td>${reim.status}</td>`;
            
//             tablePending.appendChild(newRow);
//         } else {
//             newRow.innerHTML = `<td>${reim.dateCreated}</td><td>${reim.category}</td><td>${reim.amount}</td><td>${reim.status}</td>`;
            
//             tableProcessed.appendChild(newRow);
//         }
//     }
// }

// function processReimUpdate(reimId, newStatId){

//     console.log(tokenArray[0]);
//     console.log(newStatId);
//     console.log(reimId);
// }


// if the update button is clicked in the update reimbursement form, update the reimbursement
function processReimUpdate(reimId, newStatId) {

    console.log(reimId);
    console.log(newStatId);

    let tokenArray = sessionStorage.token.split(':',2);

    let currentUser = tokenArray[0];

    let url = `http://localhost:8080/project_1/reimburs?status=${newStatId}&reim=${reimId}&manager=${currentUser}`;

    let xhr = new XMLHttpRequest();
    xhr.open("PUT", url);

    xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
            console.log("we got inside the 4 && 200 !!!");
            window.location.href="http://localhost:8080/project_1/home";
		}
		
		else if (xhr.readyState == 4) {
			console.log("Mistakes were made...");
		}
	}
	xhr.send();
}