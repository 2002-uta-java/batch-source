/**
 * 
 */

window.onload = function() {
	setupNav();

	const xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				displayReimbs(xhr);
			} else {
				displayError();
			}
		}
	};
	xhr.open("GET", GET_ALL_PENDING_EXCEPT_ME);
	xhr.send();
}

function displayReimbs(xhr) {
	console.log(xhr.responseText);
	const reimbs = JSON.parse(xhr.responseText);
	const tbody = document.getElementById("approval-table-body");
	
	for(const reimb of reimbs){
		const tr = getTr(reimb);
		tbody.appendChild(tr);
	}
}

function getTr(reimb){
	const tr = document.createElement("tr");

	td = document.createElement("td");
	td.innerText = new Date(reimb[REIMB_SUBMITDATE]).toLocaleDateString("en-US");
	tr.appendChild(td);

	td = document.createElement("td");
	td.innerText = "$" + reimb[REIMB_AMOUNT];
	tr.appendChild(td);

	td = document.createElement("td");
	td.innerText = reimb[REIMB_DESCRIPTION];
	tr.appendChild(td);

	td = document.createElement("td");
	td.innerText = new Date(reimb[REIMB_REIMBDATE]).toLocaleDateString("en-US");
	tr.appendChild(td);
	
	// add buttons to approve or deny
	const approveButton = createButton("approve");
	const rejectButton = createButton("reject");
	
	// give the id's for each button to be the reimbursement id
// approveButton.setAttribute("id", reimb[REIMB_REIMBID]);
// rejectButton.setAttribute("id", reimb[REIMB_REIMBID]);
	
	// add click events
	approveButton.setAttribute("onClick", "approveReimb(" + String(reimb[REIMB_REIMBID]) + ")");
	rejectButton.setAttribute("onClick", "rejectReimb(" + String(reimb[REIMB_REIMBID]) + ")");
	
	// don't forget to add the buttons
	td = document.createElement("td");
	td.appendChild(approveButton);
	td.appendChild(rejectButton);
	tr.appendChild(td);
	
	
	return tr;
}

function createButton(name){
	const button = document.createElement("button");
	button.innerText = name;
	button.setAttribute("type", "button");
	button.setAttribute("class", "btn btn-primary btn-sm");
	
	return button;
}

function approveReimb(reimbId){
	console.log(reimbId);

	const xhr = new XMLHttpRequest();
	xhr.open("PUT", APPROVE_REQUEST + "/" + String(reimbId));
	xhr.send();
}

function rejectReimb(reimbId){
	console.log(reimbId);
	
	const xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4){
			if(xhr.status == 202){
				location.reload();
			}else{
				// oops!
			}
		}
	}
	xhr.open("PUT", REJECT_REQUEST + "/" + String(reimbId));
	xhr.send();
}