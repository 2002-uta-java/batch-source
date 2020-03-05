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
	xhr.open("GET", VIEW_PROCESSED_BY_EMP);
	xhr.send();
}

function displayReimbs(xhr) {
	console.log(xhr.responseText);
	const reimbs = JSON.parse(xhr.responseText);
	const tbody = document.getElementById("reimbursement-table-body");
	
	for(const reimb of reimbs){
		const tr = getTr(reimb);
		tbody.appendChild(tr);
	}
}

function getTr(reimb){
	const tr = document.createElement("tr");
	
	// create and add the elements in order
	let td = document.createElement("td");
	td.innerText = reimb[REIMB_STATUSSTRING];
	tr.appendChild(td);

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

	// the reply date may not be set (if not, don't)
	const replyDate = reimb[REIMB_REPLYDATE];
	console.log("replyDate: " + reimb[REIMB_REPLYDATE]);
	if(replyDate != null){
		td = document.createElement("td");
		td.innerText = new Date(replyDate).toLocaleString("en-US");
		tr.appendChild(td);
	} else{
		td = document.createElement("td");
		td.innerText = "";
		tr.appendChild(td);
		
	}
	
	return tr;
}