/**
 * 
 */
document.getElementById("logout-btn").addEventListener("click", requestLogout);
document.getElementById("employees-btn").addEventListener("click", requestEmployees);
document.getElementById("profile-btn").addEventListener("click", requestProfile);
document.getElementById("edit-btn").addEventListener("click", editProfile);
document.getElementById("update-btn").addEventListener("click", updateProfile )
document.getElementById("Pending-btn").addEventListener("click", requestPending);
document.getElementById("Resolve-btn").addEventListener("click", requestResolve);
document.getElementById("edit-reimb-btn").addEventListener("click", requestEditReimb);
document.getElementById("reimb-emp-id-btn").addEventListener("click", requestReimbByEmpId);
document.getElementById("reimb-emp-btn").addEventListener("click", requestReimbTableByEmpId);

// array hold reimbursement
let pendingArray;
let resolveArray;
let empArray;

let tokenArray = sessionStorage.token.split(':',2);

const requestUrlAll = "http://localhost:8080/project1/api/all-employees";
const requestUrlProfile = `http://localhost:8080/project1/api/profile?id=${tokenArray[0]}`;
const requestUrlReimbPending = `http://localhost:8080/project1/api/manager-reimbursement-pending?id=${tokenArray[0]}`;
const requestUrlReimbResolve = `http://localhost:8080/project1/api/manager-reimbursement-resolve?id=${tokenArray[0]}`;


function sendAjaxGet(url, callback){
	let xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4 && xhr.status==200){
			callback(xhr.response);
		}
	}
	xhr.send();
}

// Send Put for update profile
function sendAjaxPut(url){
	let xhr = new XMLHttpRequest();
	xhr.open("PUT", url);
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4 && xhr.status==200){
			window.location.href="http://localhost:8080/project1/manager-homepage";
		}
	}
	xhr.send();
}

// ajax get
window.onload = sendAjaxGet(requestUrlProfile, displayProfile);
window.onload = sendAjaxGet(requestUrlAll, displayEmployees);
window.onload = sendAjaxGet(requestUrlReimbPending, displayPending);
window.onload = sendAjaxGet(requestUrlReimbResolve, displayResolve);

// function to send put for update reimbursement
function updateProfile(){
	let name = document.getElementById("emp-name").value;
	let age = document.getElementById("emp-age").value;
	let email = document.getElementById("emp-email").value;

	const requestUrlEditProfile = `http://localhost:8080/project1/api/edit-profile?id=${tokenArray[0]}&emp-name=${name}&emp-age=${age}&emp-email=${email}`;
	if (age > 0){
		if(emailIsValid(email)){
			//ajax put
			sendAjaxPut(requestUrlEditProfile);
		}else{
			document.getElementById("emp-email-invalid").innerText = "Invalid Email!";
		}
	}else if (age == 0){
		document.getElementById("emp-age-invalid").innerText = "Can't have 0 age!";
	} else{
		document.getElementById("emp-age-invalid").innerText = "No negative age!";
	}
}

// Send Put for update reimbursement
function updateReimbursement(event){
	let targetBtn = event.target.id;
	let num = targetBtn.replace("reimb-btn-", "");
	console.log(num);
	let id = document.getElementById(`reimb-field-reimbId-${num}`).innerText;
	let approveBy = tokenArray[0];
	let status = document.getElementById(`reimb-select-input-${num}`).value;
	console.log(status);

	const requestUrlUpdateReimb = `http://localhost:8080/project1/api/edit-reimbursement?id=${id}&approve-by=${approveBy}&status=${status}`;
	sendAjaxPut(requestUrlUpdateReimb);
}

//function logout button===-------------=========-------=======
function requestLogout(){
	let ss = sessionStorage.clear();
	tokenArray = null;
	console.log(ss);

	window.location.href="http://localhost:8080/project1/login";
}

//-----------------------------------HIDE & DISPLAY-----------------------------------------
// submit reimbursement
function requestEditReimb(){
	let divEditReimb = document.getElementById("reimb-edit");
	divEditReimb.hidden=false;

	// hide 
	let reimbSearchDiv = document.getElementById("reimb-emp-search-div");
	reimbSearchDiv.hidden = true;
	let divEditProfile = document.getElementById("edit-profile-form");
	divEditProfile.hidden = true;
	let divProfile = document.getElementById("profile-div");
	divProfile.hidden = true;
	// hide
	let reimbPending = document.getElementById("reimb-pending");
	reimbPending.hidden = true;
	let reimbResolve = document.getElementById("reimb-resolve");
	reimbResolve.hidden = true;
	let table = document.getElementById("employee-table");
	table.hidden = true;
	let reimbSearchTableDiv = document.getElementById("reimb-emp-table-div");
	reimbSearchTableDiv.hidden = true;

}
// view table of reimbursement after click search
function requestReimbTableByEmpId(){
	displayReimbSearchResult();
	// show search table after search result
	let reimbSearchTableDiv = document.getElementById("reimb-emp-table-div");
	reimbSearchTableDiv.hidden = false;

	// exception not to hide
	// let reimbSearchDiv = document.getElementById("reimb-emp-search-div");
	// reimbSearchDiv.hidden = true;
	//hide
	let divEditReimb = document.getElementById("reimb-edit");
	divEditReimb.hidden=true;
	let divEditProfile = document.getElementById("edit-profile-form");
	divEditProfile.hidden = true;
	let divProfile = document.getElementById("profile-div");
	divProfile.hidden = true;
	// hide
	let reimbPending = document.getElementById("reimb-pending");
	reimbPending.hidden = true;
	let reimbResolve = document.getElementById("reimb-resolve");
	reimbResolve.hidden = true;
	let table = document.getElementById("employee-table");
	table.hidden = true;
}
// view search button reimbursement by employee id
function requestReimbByEmpId(){
	// show search button for reimbursement by employee id
	let reimbSearchDiv = document.getElementById("reimb-emp-search-div");
	reimbSearchDiv.hidden = false;

	// hide 
	let reimbSearchTableDiv = document.getElementById("reimb-emp-table-div");
	reimbSearchTableDiv.hidden = true;
	let divEditReimb = document.getElementById("reimb-edit");
	divEditReimb.hidden=true;
	let divEditProfile = document.getElementById("edit-profile-form");
	divEditProfile.hidden = true;
	let divProfile = document.getElementById("profile-div");
	divProfile.hidden = true;
	// hide
	let reimbPending = document.getElementById("reimb-pending");
	reimbPending.hidden = true;
	let reimbResolve = document.getElementById("reimb-resolve");
	reimbResolve.hidden = true;
	let table = document.getElementById("employee-table");
	table.hidden = true;
}

// employees
function requestEmployees(){
	// show employees table
	let table = document.getElementById("employee-table");
	table.hidden = false;

	// hide others info
	let reimbSearchTableDiv = document.getElementById("reimb-emp-table-div");
	reimbSearchTableDiv.hidden = true;
	let reimbSearchDiv = document.getElementById("reimb-emp-search-div");
	reimbSearchDiv.hidden = true;
	let divEditProfile = document.getElementById("edit-profile-form");
	divEditProfile.hidden = true;
	let divProfile = document.getElementById("profile-div");
	divProfile.hidden = true;
	// hide
	let reimbPending = document.getElementById("reimb-pending");
	reimbPending.hidden = true;
	let reimbResolve = document.getElementById("reimb-resolve");
	reimbResolve.hidden = true;
	let divEditReimb = document.getElementById("reimb-edit");
	divEditReimb.hidden=true;

}
// function editProfile
function editProfile(){
	document.getElementById("emp-email").value = `${empArray.email}`;
	document.getElementById("emp-age").value = `${empArray.age}`;
	document.getElementById("emp-age-invalid").innerText = "";
	document.getElementById("emp-email-invalid").innerText = "";

	let divEditProfile = document.getElementById("edit-profile-form");
	divEditProfile.hidden = false;

	// hide
	let reimbSearchTableDiv = document.getElementById("reimb-emp-table-div");
	reimbSearchTableDiv.hidden = true;
	let reimbSearchDiv = document.getElementById("reimb-emp-search-div");
	reimbSearchDiv.hidden = true;
	let table = document.getElementById("employee-table");
	table.hidden = true;
	let divProfile = document.getElementById("profile-div");
	divProfile.hidden = true;
	// hide
	let reimbPending = document.getElementById("reimb-pending");
	reimbPending.hidden = true;
	let reimbResolve = document.getElementById("reimb-resolve");
	reimbResolve.hidden = true;
	let divEditReimb = document.getElementById("reimb-edit");
	divEditReimb.hidden=true;
}

function requestProfile(){
	let divProfile = document.getElementById("profile-div");
	divProfile.hidden = false;
	
	// hide 
	let reimbSearchTableDiv = document.getElementById("reimb-emp-table-div");
	reimbSearchTableDiv.hidden = true;
	let reimbSearchDiv = document.getElementById("reimb-emp-search-div");
	reimbSearchDiv.hidden = true;
	let table = document.getElementById("employee-table");
	table.hidden = true;
	let divEditProfile = document.getElementById("edit-profile-form");
	divEditProfile.hidden = true;
	// hide
	let reimbPending = document.getElementById("reimb-pending");
	reimbPending.hidden = true;
	let reimbResolve = document.getElementById("reimb-resolve");
	reimbResolve.hidden = true;
	let divEditReimb = document.getElementById("reimb-edit");
	divEditReimb.hidden=true;
}

function requestPending(){
	// show
	let reimbPending = document.getElementById("reimb-pending");
	reimbPending.hidden = false;

	//hide
	let reimbSearchTableDiv = document.getElementById("reimb-emp-table-div");
	reimbSearchTableDiv.hidden = true;
	let reimbSearchDiv = document.getElementById("reimb-emp-search-div");
	reimbSearchDiv.hidden = true;
	let reimbResolve = document.getElementById("reimb-resolve");
	reimbResolve.hidden = true;
	let table = document.getElementById("employee-table");
	table.hidden = true;
	let divProfile = document.getElementById("profile-div");
	divProfile.hidden = true;
	let divEditProfile = document.getElementById("edit-profile-form");
	divEditProfile.hidden = true;
	let divEditReimb = document.getElementById("reimb-edit");
	divEditReimb.hidden=true;
}
function requestResolve(){
	//show
	let reimbResolve = document.getElementById("reimb-resolve");
	reimbResolve.hidden = false;
	

	// hide
	let reimbSearchTableDiv = document.getElementById("reimb-emp-table-div");
	reimbSearchTableDiv.hidden = true;
	let reimbSearchDiv = document.getElementById("reimb-emp-search-div");
	reimbSearchDiv.hidden = true;
	let reimbPending = document.getElementById("reimb-pending");
	reimbPending.hidden = true;
	let table = document.getElementById("employee-table");
	table.hidden = true;
	let divProfile = document.getElementById("profile-div");
	divProfile.hidden = true;
	let divEditProfile = document.getElementById("edit-profile-form");
	divEditProfile.hidden = true;
	let divEditReimb = document.getElementById("reimb-edit");
	divEditReimb.hidden=true;
	
}
//--------------------------------------------------------------------------------------------
function displayEmployees(employeeJson){
	let emps = JSON.parse(employeeJson);
	console.log(emps);
	
	let table = document.getElementById("employee-table");

	for(let emp of emps){
		let newRow = document.createElement("tr");
		newRow.innerHTML = `<td>${emp.empId}</td><td>${emp.position}</td><td>${emp.reportTo}</td><td>${emp.name}</td><td>${emp.age}</td><td>${emp.email}</td>`;
		table.appendChild(newRow);
	}

}

function displayProfile(empJson){
	let emp = JSON.parse(empJson);
	console.log(emp);
	empArray = JSON.parse(empJson);
	// welcome message
	document.getElementById("welcome-emp-name").innerText = `Welcome ${emp.name}`;

	let div = document.getElementById("profile-div-content");
	
	div.innerHTML = `<p>${emp.name}</p><p>${emp.age}</p><p>${emp.email}</p>` ;

	// display in update as well
	document.getElementById("emp-name").value=emp.name;
	document.getElementById("emp-age").value=emp.age;
	document.getElementById("emp-email").value=emp.email;

}

// display in html-----------------------------------------
function displayPending(reimbJson){
	let reimbs = JSON.parse(reimbJson);
	console.log(reimbs);
	pendingArray = reimbs.slice();
	console.log(pendingArray);

	let table = document.getElementById("pending-table");


	for(let reimb of reimbs){
		let newRow = document.createElement("tr");
		newRow.innerHTML = `<td>${reimb.reimbId}</td><td>${reimb.empId}</td><td>${reimb.amount}</td><td>${reimb.purpose}</td><td>${reimb.approveBy}</td><td>${reimb.reimbStatus}</td>`;
		table.appendChild(newRow);
	}

	// create edit reimbursement
	displayReimbursement();
}

// create search table result============
function displayReimbSearchResult(){

	deleteReimbSearchResult();

	let table = document.getElementById("reimb-emp-table");
	let employeeId = document.getElementById("reimb-emp-id-input").value;
	for(let reimb in pendingArray){
		if(pendingArray[reimb].empId == employeeId){
			let newRow = document.createElement("tr");
			newRow.className = "reimb-search-tr";
			newRow.innerHTML = `<td>${pendingArray[reimb].reimbId}</td><td>${pendingArray[reimb].empId}</td><td>${pendingArray[reimb].amount}</td><td>${pendingArray[reimb].purpose}</td><td>${pendingArray[reimb].approveBy}</td><td>${pendingArray[reimb].reimbStatus}</td>`;
			table.appendChild(newRow);
		}
	}
	// for resolve
	for(let reimb in resolveArray){
		if(resolveArray[reimb].empId == employeeId){
			let newRow = document.createElement("tr");
			newRow.className = "reimb-search-tr";
			newRow.innerHTML = `<td>${resolveArray[reimb].reimbId}</td><td>${resolveArray[reimb].empId}</td><td>${resolveArray[reimb].amount}</td><td>${resolveArray[reimb].purpose}</td><td>${resolveArray[reimb].approveBy}</td><td>${resolveArray[reimb].reimbStatus}</td>`;
			table.appendChild(newRow);
		}
	}
}

// drop search table result
function deleteReimbSearchResult(){
	let table = document.getElementById("reimb-emp-table");
	while (table.lastElementChild) {
		table.removeChild(table.lastElementChild);
	}
}

function displayResolve(reimbJson){
	let reimbs = JSON.parse(reimbJson);
	console.log(reimbs);
	resolveArray = reimbs.slice();
	
	let table = document.getElementById("resolve-table");


	for(let reimb of reimbs){
		let newRow = document.createElement("tr");
		newRow.innerHTML = `<td>${reimb.reimbId}</td><td>${reimb.empId}</td><td>${reimb.amount}</td><td>${reimb.purpose}</td><td>${reimb.approveBy}</td><td>${reimb.reimbStatus}</td>`;
		table.appendChild(newRow);
	}
}

function displayReimbursement(){
	let table = document.getElementById("reimb-edit-table");
	console.log(pendingArray);

	let counter = 0;
	for(let reimb in pendingArray){

		newRow = document.createElement("tr");
		newRow.innerHTML = 
		`<td id="reimb-field-reimbId-${counter}">${pendingArray[reimb].reimbId}</td>
		<td id="reimb-field-empId-${counter}">${pendingArray[reimb].empId}</td>
		<td id="reimb-field-amount-${counter}">${pendingArray[reimb].amount}</td>
		<td id="reimb-field-purpose-${counter}">${pendingArray[reimb].purpose}</td>`;
		table.appendChild(newRow);

		// let reimbSelect = document.getElementById("reimb-select-status");
		// let cloneSelect = reimbSelect.cloneNode(true);
		// cloneSelect.id = `reimb-select-status-${counter}`;
		// cloneSelect.addEventListener("click", updateReimbursement);
		// newRow.appendChild(cloneSelect);
		let reimbSelect = document.getElementById("reimb-select-status");
		let cloneSelect = reimbSelect.cloneNode(true);
		cloneSelect.id = `reimb-select-status-${counter}`;

		let selectInput = document.getElementById("reimb-select-input");
		let cloneSelectInput = selectInput.cloneNode(true);
		cloneSelectInput.id = `reimb-select-input-${counter}`;
		cloneSelect.appendChild(cloneSelectInput);
		newRow.appendChild(cloneSelect);


		let reimbBtn = document.getElementById("reimb-btn");
		let cloneBtn = reimbBtn.cloneNode(true);
		cloneBtn.id = `reimb-btn-${counter}`;
		cloneBtn.addEventListener("click", updateReimbursement);
		newRow.appendChild(cloneBtn);

		counter++;
	}

	counter = 0;

}

// email validation
function emailIsValid(email) {
	return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
}
