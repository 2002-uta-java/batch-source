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

// retrieve and populate the reimbursement history for currently logged in user
function displayReimHist(reimJason) {
    let reimbursements = JSON.parse(reimJason);
    console.table(reimbursements);

    let tablePending = document.getElementById("my-reim-hist-table");
    let tableProcessed = document.getElementById("processed-reim-hist-table");

    for (let reim of reimbursements) {

        let newRow = document.createElement("tr");

        if (reim.statusId < 4) {
            newRow.innerHTML = `<td>${reim.dateCreated}</td><td>${reim.category}</td><td>${reim.amount}</td><td>${reim.status}</td>`;
            
            tablePending.appendChild(newRow);
        } else {
            newRow.innerHTML = `<td>${reim.dateCreated}</td><td>${reim.category}</td><td>${reim.amount}</td><td>${reim.status}</td>`;
            
            tableProcessed.appendChild(newRow);
        }
    }
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

    let url = `http://localhost:8080/project_1/reimburs`;

    let xhr = new XMLHttpRequest();
    xhr.open("POST", url);

    xhr.onreadystatechange = function() {
        if(xhr.readyState == 4 && xhr.status == 200) {
            console.log("we got inside the 4 && 200 !!!");
		}
		
		else if (xhr.readyState == 4) {
			console.log("Mistakes were made...");
        }
    }
	
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let requestBody = `employee=${currentUser}&category=${catInput}&amount=${amountInput}`;
	xhr.send(requestBody);
}