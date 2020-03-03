//sign user out
let signOut = () =>{
	//login page url
	let url = "http://localhost:8080/project1/login";
	//check if token exists
	if(sessionStorage.getItem("token").length !== 0){
		//remove token
		sessionStorage.removeItem("token");
		//redirect to login page
		window.location = url;
	}
}

//render nav
let renderNav = (name) =>{
	//get nav div
	let nav = document.querySelector("#nav");
	nav.innerHTML =
	`
		<div class="icon">
			<!-- app icon -->
		</div>
		<div class="title">
			<h1>NAVI System Home</h1>
		</div>
		<div class="welcome">
			<h2>Welcome, ${name}.</h2>
		</div>
		<div class="logout">
			<button>Sign out</button>
		</div>
	`;
	//get sign out button
	let signoutBtn = document.querySelector("#nav button");
	//add click event listener
	signoutBtn.addEventListener("click", signOut);
}

// render reimbursement options
let renderSeparator = () =>{
	// get separator div
	let separator = document.querySelector("#separator");
	// render separator
	separator.innerHTML =
	`
		<hr>
		<div>
			<pre>REIMBURSEMENTS</pre>
		</div>
		<hr>
	`;
}

// render reimbursement options
let renderReimbursement = (reimbursement, index, role) =>{
	// get reimbursement div
	let reimbursements = document.querySelector("#reimbursements");
	//create reimbursement div
	let rmb = document.createElement("div");
	//set class name for styling
	rmb.className = "reimbursement";
	//set unique id to handle edit functions
	rmb.id = "rmb" + index;
	//set inner html for reimbursement and set current status (edits are disabled by default)
	let html =
	`
		<h4>${reimbursement.employee_email}</h4>
		<hr>
		<em>Date Submitted:</em>
		<pre>${reimbursement.date}</pre>
		<em>Amount:</em>
		<pre>${reimbursement.amount}</pre>
		<em>Details:</em>
		<pre>${reimbursement.description}</pre>
		<em>Status:</em>
	`;
	// set html for reimbursement
	rmb.innerHTML= html;
	//create select element with options
	let select = document.createElement("select");
	//disabled by default
	select.setAttribute("disabled", true);
	//add options
	select.innerHTML = 
	`
		<option value="pending">Pending</option>
		<option value="approved">Approved</option>
		<option value="denied">Denied</option>
	`;
	//set current status
	select.value = reimbursement.status;
	//add to div
	rmb.appendChild(select);
	//add edit button if the user is a manager
	if(role === 'manager'){
		//create edit button
		let edit = document.createElement("button");
		//set inner text
		edit.innerHTML = "Edit";
		//callback function when clicked (Reimbursement.js)
		edit.addEventListener("click", (event)=>{editReimbursement(event, reimbursement.id)});
		//add edit button to reimbursement div
		rmb.appendChild(edit);
	}
	//render reimbursement
	reimbursements.appendChild(rmb);
}

let renderCreateRmb = (user) =>{
	// get reimbursement div
	let reimbursements = document.querySelector("#reimbursements");
	//add create reimbursement div
	let createRmb = document.createElement("div");
	//set class for styling
	createRmb.className = "reimbursement";
	//add unique id for layout change
	createRmb.id = "new-rmb";
	//add create button with plus sign
	createRmb.innerHTML = 
	`
		<input type="button" value="+" id="create-rmb"/>
	`;
	//add to reimbursement section
	reimbursements.appendChild(createRmb);
	//get button to add click listener
	let createBtn = document.querySelector("#create-rmb");
	//add click listener
	createBtn.addEventListener("click", ()=>{changeReimbursementLayout(user.employee_email)});
}


//send ajax query to get employee's reimbursements
let getReimbursements = (user) =>{
	//url to get reimbursements
	let url = "http://localhost:8080/project1/api/reimbursement";
	//ajax request
	let xhr = new XMLHttpRequest();
	//if the request is successful, render reimbursement collection
	xhr.onreadystatechange = () =>{
		if(xhr.readyState === 4 && xhr.status === 200){
			//check if response isn't empty
			if(xhr.response.length !== 0){
				//parse reimbursements and render
				let reimbursements = JSON.parse(xhr.response);
				reimbursements.map((item, index)=>{
					renderReimbursement(item, index, user.role);
				});
				if(user.role == "employee"){
					renderCreateRmb(user);
				}
			}
		}
	}
	//if user is manager get all of their employee's reimbursements
	if(user.role === "manager"){
		url += `?manager=${user.employee_email}`;
	} 
	//if use is employee get all of their reimbursements
	else{
		url += `?employee=${user.employee_email}`;
	}
	//set verb and url
	xhr.open("GET", url);
	//send request
	xhr.send();
}

// check if validation was successful by checking token
if(sessionStorage.getItem("token") !== null){
	// parse json data from session storage
	let user = JSON.parse(sessionStorage.getItem("token"));
	// render nav
	renderNav(user.name);
	// render appropriate profile for user
	renderProfile(user);
	// render separator for sections
	renderSeparator();
	// render appropriate reimbursement options for user
	getReimbursements(user);
} else {
	//if token doesn't exist send user to login page
	window.location = "http://localhost:8080/project1/login";
}