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
}

//render user profile
let renderProfile = (user) =>{
	// get profile div
	let profile = document.querySelector("#profile");
	// render user info
	profile.innerHTML = 
	`
		<div class="image">
			<img rel="Identifies the user." src="https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_960_720.png"/>
		</div>
		<div class="content">
			<div>
				<h3><strong>${user.employee_email}</strong></h3><button>Edit Profile</button>
			</div>
			<div>
				<h4><strong>Position: </strong></h4><pre>${user.role}</pre>
				<h4><strong>Birthday: </strong></h4><pre>${user.birthday}</pre>
			</div>
			<div>
				<h4><strong>${user.name}'s Bio:</strong></h4><pre>${user.bio}</pre>
			</div>
		</div>
	`;
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
		<em>Details:</em>
		<pre>${reimbursement.description}</pre>
		<em>Status:</em>
		<select disabled value=${reimbursement.status}>
			<option value="pending">Pending</option>
			<option value="approved">Approved</option>
			<option value="denied">Denied</option>
		</select>
	`;
	// set html for reimbursement
	rmb.innerHTML= html;
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