/**
 * 
 */
document.getElementById("logout-btn").addEventListener("click", requestLogout);
document.getElementById("profile-btn").addEventListener("click", requestProfile);
document.getElementById("Pending-btn").addEventListener("click", requestPending);
document.getElementById("Resolve-btn").addEventListener("click", requestResolve);
document.getElementById("edit-btn").addEventListener("click", editProfile);
document.getElementById("update-btn").addEventListener("click", updateProfile );
document.getElementById("reimb-submit-btn").addEventListener("click", requestSubmitReimbursement);
document.getElementById("reimb-submit-form-btn").addEventListener("click", createReimbursement);

let tokenArray = sessionStorage.token.split(':',2);
let empArray;
const requestUrlReimbPending = `http://localhost:8080/project1/api/reimbursement-pending?id=${tokenArray[0]}`;
const requestUrlReimbResolve = `http://localhost:8080/project1/api/reimbursement-resolve?id=${tokenArray[0]}`;
const requestUrlProfile = `http://localhost:8080/project1/api/profile?id=${tokenArray[0]}`;

// send Get
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

// Send Put
function sendAjaxPut(url){
	let xhr = new XMLHttpRequest();
	xhr.open("PUT", url);
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4 && xhr.status==200){
			window.location.href="http://localhost:8080/project1/employee-homepage";
		}
	}
	xhr.send();
}

// send ajax post, create reimbursement
function sendAjaxPost(url){
	let xhr = new XMLHttpRequest();
	xhr.open("POST", url);
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4 && xhr.status==200){
			window.location.href="http://localhost:8080/project1/employee-homepage";
		}
	}
	xhr.send();
}

// ajax get
window.onload = sendAjaxGet(requestUrlProfile, displayProfile);
window.onload = sendAjaxGet(requestUrlReimbPending, displayPending);
window.onload = sendAjaxGet(requestUrlReimbResolve, displayResolve);

// function create reimbursement
function createReimbursement(){

	let amount = document.getElementById("reimb-amount-input").value;
	let purpose = document.getElementById("reimb-purpose-select-input").value;
	const requestUrlSubmitReimbForm = `http://localhost:8080/project1/api/submit-reimbursement?empId=${tokenArray[0]}&amount=${amount}&purpose=${purpose}`;

	if (amount > 0){
		// ajax put
		sendAjaxPost(requestUrlSubmitReimbForm);
	} 
	else if(amount == 0){
		document.getElementById("invalid-amount").innerText = "Can't have 0 amount!";
	} else{
		document.getElementById("invalid-amount").innerText = "No negative amount!";
	}


}

// function update
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

//function logout button===-------------=========-------=======
function requestLogout(){
	let ss = sessionStorage.clear();
	tokenArray = null;
	console.log(ss);

	window.location.href="http://localhost:8080/project1/login";
}
//===============================HIDE & DISPLAY=================================
// function show submit reimbursement form
function requestSubmitReimbursement(){
	// delete old data
	document.getElementById("invalid-amount").innerText = "";
	document.getElementById("reimb-amount-input").value = 0;
	// show
	let reimbSubmitDiv = document.getElementById("reimb-submit-form");
	reimbSubmitDiv.hidden = false;

	// hide
	let divEditProfile = document.getElementById("edit-profile-form");
	divEditProfile.hidden = true;
	let reimbPending = document.getElementById("reimb-pending");
	reimbPending.hidden = true;
	let divProfile = document.getElementById("profile-div");
	divProfile.hidden = true;
	let reimbResolve = document.getElementById("reimb-resolve");
	reimbResolve.hidden = true;
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
	let reimbSubmitDiv = document.getElementById("reimb-submit-form");
	reimbSubmitDiv.hidden = true;
	let reimbPending = document.getElementById("reimb-pending");
	reimbPending.hidden = true;
	let divProfile = document.getElementById("profile-div");
	divProfile.hidden = true;
	let reimbResolve = document.getElementById("reimb-resolve");
	reimbResolve.hidden = true;
}

function requestProfile(){
	let divProfile = document.getElementById("profile-div");
	divProfile.hidden = false;
	
	// hide
	let reimbSubmitDiv = document.getElementById("reimb-submit-form");
	reimbSubmitDiv.hidden = true;
	let reimbPending = document.getElementById("reimb-pending");
	reimbPending.hidden = true;
	let reimbResolve = document.getElementById("reimb-resolve");
	reimbResolve.hidden = true;
	let divEditProfile = document.getElementById("edit-profile-form");
	divEditProfile.hidden = true;
}

function requestPending(){
	let reimbPending = document.getElementById("reimb-pending");
	reimbPending.hidden = false;
	
	// hide
	let reimbSubmitDiv = document.getElementById("reimb-submit-form");
	reimbSubmitDiv.hidden = true;
	let divProfile = document.getElementById("profile-div");
	divProfile.hidden = true;
	let divEditProfile = document.getElementById("edit-profile-form");
	divEditProfile.hidden = true;
	let reimbResolve = document.getElementById("reimb-resolve");
	reimbResolve.hidden = true;
}

function requestResolve(){
	let reimbResolve = document.getElementById("reimb-resolve");
	reimbResolve.hidden = false;
	
	// hide
	let reimbSubmitDiv = document.getElementById("reimb-submit-form");
	reimbSubmitDiv.hidden = true;
	let reimbPending = document.getElementById("reimb-pending");
	reimbPending.hidden = true;
	let divProfile = document.getElementById("profile-div");
	divProfile.hidden = true;
	let divEditProfile = document.getElementById("edit-profile-form");
	divEditProfile.hidden = true;
}


// display in html-----------------------------------------
function displayPending(reimbJson){
	let reimbs = JSON.parse(reimbJson);
	console.log(reimbs);
	
	let table = document.getElementById("pending-table");


	for(let reimb of reimbs){
		let newRow = document.createElement("tr");
		newRow.innerHTML = `<td>${reimb.reimbId}</td><td>${reimb.amount}</td><td>${reimb.purpose}</td><td>${reimb.reimbStatus}</td>`;
		table.appendChild(newRow);
	}
}

function displayResolve(reimbJson){
	let reimbs = JSON.parse(reimbJson);
	console.log(reimbs);
	
	let table = document.getElementById("resolve-table");


	for(let reimb of reimbs){
		let newRow = document.createElement("tr");
		newRow.innerHTML = `<td>${reimb.reimbId}</td><td>${reimb.amount}</td><td>${reimb.purpose}</td><td>${reimb.reimbStatus}</td>`;
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


// email validation
function emailIsValid(email) {
	return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
}


