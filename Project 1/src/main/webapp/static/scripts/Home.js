//sign user out
let signOut = () =>{
	// login page url
	let url = "http://localhost:8080/project1/login";
	// check if token exists
	if(sessionStorage.getItem("token").length !== 0){
		// remove token
		sessionStorage.removeItem("token");
		// redirect to login page
		window.location = url;
	}
}

// render nav
let renderNav = (name) =>{
	// get nav div
	let nav = document.querySelector("#nav");
	nav.innerHTML =
	`
		<!-- app icon -->
		<div class="left">
			<label>NAVI System Home</label>
		</div>
		<div class="right">
			<label>Welcome, ${name}.</label>
			<div class="button fancy-button small">
				<div class="slide"></div>
				<a>Sign out</a>
			</div>
		</div>
	`;
	// get sign out button
	let signoutBtn = document.querySelector("#nav .fancy-button");
	// add click event listener
	signoutBtn.addEventListener("click", signOut);
}

// render reimbursement options
let renderReimbursement = (reimbursement, index, role) =>{
	// get reimbursement div
	let reimbursements = document.querySelector("#reimbursements");
	// create reimbursement div
	let rmb = document.createElement("div");
	// set class name for styling
	rmb.className = "reimbursement";
	// set unique id to handle edit functions
	rmb.id = "rmb" + index;
	// set inner html for reimbursement and set current status (edits are
	// disabled by default)
	let html =
	`
		<h4>${reimbursement.employee_email}</h4>
		<hr>
		<em>Date Submitted:</em>
		<p>${reimbursement.date}</p>
			<em>Amount:</em>
		<p>${reimbursement.amount}</p>
		<em>Details:</em>
		<p>${reimbursement.description}</p>
		<em>Status:</em>
	`;
	// set html for reimbursement
	rmb.innerHTML= html;
	// create select element with options
	let select = document.createElement("select");
	// disabled by default
	select.setAttribute("disabled", true);
	// add options
	select.innerHTML = 
	`
		<option value="pending">Pending</option>
		<option value="approved">Approved</option>
		<option value="denied">Denied</option>
	`;
	// set current status
	select.value = reimbursement.status;
	// add to div
	rmb.appendChild(select);
	// add edit button if the user is a manager
	if(role === 'manager'){
		// create edit button
		let edit = document.createElement("div");
		edit.className = "right-flex";
		// set inner text
		edit.innerHTML = 
		`
			<div class="button fancy-button small">
				<div class="slide"></div>
				<a>Edit</a>
			</div>
		`;
		// callback function when clicked (Reimbursement.js)
		edit.addEventListener("click", (event)=>{editReimbursement(event, reimbursement.id)});
		// add edit button to reimbursement div
		rmb.appendChild(edit);
	}
	// render reimbursement
	reimbursements.appendChild(rmb);
}

let renderCreateRmb = (user) =>{
	// get reimbursement div
	let reimbursements = document.querySelector("#reimbursements");
	// add create reimbursement div
	let createRmb = document.createElement("div");
	// set class for styling
	createRmb.className = "reimbursement";
	// add unique id for layout change
	createRmb.id = "new-rmb";
	// add create button with plus sign
	createRmb.innerHTML = 
	`
		<div class="center-flex">
			<div id="create-rmb" class="button fancy-button small">
				<div class="slide"></div>
				<a>+</a>
			</div>
		</div>
	`;
	// add to reimbursement section
	reimbursements.appendChild(createRmb);
	// get button to add click listener
	let createBtn = document.querySelector("#create-rmb");
	// add click listener
	createBtn.addEventListener("click", changeReimbursementLayout);
}


// send ajax query to get employee's reimbursements
let getReimbursements = (user) =>{
	// url to get reimbursements
	let url = "http://localhost:8080/project1/api/reimbursement";
	// ajax request
	let xhr = new XMLHttpRequest();
	// if the request is successful, render reimbursement collection
	xhr.onreadystatechange = () =>{
		if(xhr.readyState === 4 && xhr.status === 200){
			// check if response isn't empty
			if(xhr.response.length !== 0){
				// parse reimbursements and render
				let reimbursements = JSON.parse(xhr.response);
				// store reimbursements in session
				sessionStorage.setItem("reimbursements", JSON.stringify(reimbursements));
				// sort reimbursements by date
				let sorted = reimbursements.sort((a, b)=> { return new Date(b.date) - new Date(a.date);});
				sorted.map((item, index)=>{
					renderReimbursement(item, index, user.role);
				});
				if(user.role == "employee"){
					renderCreateRmb(user);
				}
			}
		}
	}
	// if user is manager get all of their employee's reimbursements
	if(user.role === "manager"){
		url += `?manager=${user.employee_email}`;
	} 
	// if use is employee get all of their reimbursements
	else{
		url += `?employee=${user.employee_email}`;
	}
	// set verb and url
	xhr.open("GET", url);
	// send request
	xhr.send();
}

let renderEmployee = (employee, index, user) =>{
	// get reimbursement div
	let reimbursements = document.querySelector("#reimbursements");
	// create employee div
	let emp = document.createElement("div");
	// set class name for styling
	emp.className = "employee";
	// set unique id to handle filter function
	emp.id = "emp" + index;
	// set inner html employee
	let html =
	`
		<h4>${employee}</h4>
		<hr>
		<div id="denied-rmb" class="button fancy-button fit">
			<div class="slide"></div>
			<a>View ${employee.substring(0, employee.indexOf("@"))}'s Reimbursements</a>
		</div>
	`;
	// set html for reimbursement
	emp.innerHTML= html;
	reimbursements.appendChild(emp);
	let filterBtn = document.querySelector(`#emp${index} .button`);
	filterBtn.addEventListener("click", ()=>{toggleTab("filter", user, employee)});
}

let toggleTab = (type, user, employee) =>{
	let rmbDiv = document.querySelector("#reimbursements");
	let reimbursements = JSON.parse(sessionStorage.getItem("reimbursements"));
	let sorted = [];
	switch(type){
	case "pending":{
		sorted = reimbursements.filter((item)=>{return item.status==="pending"});
		break;
	}
	case "approved":{
		sorted = reimbursements.filter((item)=>{return item.status==="approved"});
		break;
	}
	case "denied":{
		sorted = reimbursements.filter((item)=>{return item.status==="denied"});
		break;
	}
	case "all":{
		sorted = [...reimbursements];
		break;
	}
	case "filter":{
		sorted = reimbursements.filter((item)=>{return item.employee_email == employee});
		break;
	}
	case "employees":{
		let employees = reimbursements.map((item)=>{return item.employee_email});
		sorted = employees.filter((item, index, self)=>{return self.indexOf(item) === index});
		break;
	}
	default:{
		break;
	}
	}
	rmbDiv.innerHTML="";
	if(type !== "employees"){
		sorted.map((item, index)=>{
			renderReimbursement(item, index, user.role);
		});
	} else{
		sorted.map((item, index)=>{
			renderEmployee(item, index, user);
		});
	}
	
	if(user.role === "employee"){
		renderCreateRmb(user);
	}
}

let renderSeparator = (user) =>{
	// get div
	let separator = document.querySelector("#separator");
	separator.innerHTML = 
	`
		<hr>
			<div id="menu">
				<div id="rmb-tab" class="button fancy-button medium">
					<div class="slide"></div>
					<a>Reimbursements</a>
				</div>
			</div>
			<ul class="tabs">
				<li>
					<div id="pending-rmb" class="button fancy-button xs">
						<div class="slide"></div>
						<a>Pending</a>
					</div>
				</li>
				<li>
					<div id="approved-rmb" class="button fancy-button xs">
						<div class="slide"></div>
						<a>Approved</a>
					</div>
				</li>
				<li>
					<div id="denied-rmb" class="button fancy-button xs">
						<div class="slide"></div>
						<a>Denied</a>
					</div>
				</li>
			</ul>
		<hr>
	`;
	let pending = document.querySelector("#pending-rmb");
	let approved = document.querySelector("#approved-rmb");
	let denied = document.querySelector("#denied-rmb");
	let rmbTab = document.querySelector("#rmb-tab");
	rmbTab.addEventListener("click", ()=>{toggleTab("all", user)});
	pending.addEventListener("click", ()=>{toggleTab("pending", user)});
	approved.addEventListener("click", ()=>{toggleTab("approved", user)});
	denied.addEventListener("click", ()=>{toggleTab("denied", user)});
	if(user.role === "manager"){
		let menu = document.querySelector("#menu");
		let employees = document.createElement("div");
		employees.className = "button fancy-button medium";
		employees.id="rmb-tab";
		employees.innerHTML = 
		`
			<div class="slide"></div>
			<a>Employees</a>
		`;
		employees.addEventListener("click", ()=>{toggleTab("employees", user)});
		menu.appendChild(employees);
	}
}

// check if validation was successful by checking token
if(sessionStorage.getItem("token") !== null){
	// parse json data from session storage
	let user = JSON.parse(sessionStorage.getItem("token"));
	// render nav
	renderNav(user.name);
	// render separator
	renderSeparator(user);
	// render appropriate profile for user
	renderProfile(user);
	// render appropriate reimbursement options for user
	getReimbursements(user);
} else {
	// if token doesn't exist send user to login page
	window.location = "http://localhost:8080/project1/login";
}