// update status when change is detected
let handleChange = (event, id) =>{
	// url for reimbursement update
	let url = "http://localhost:8080/project1/api/reimbursement";
	// home page url
	let home = "http://localhost:8080/project1/home";
	// send ajax request to update reimbursement based on id
	let xhr = new XMLHttpRequest();
	// refresh page if successful
	xhr.onreadystatechange = () =>{
		if(xhr.readyState === 4 && xhr.status === 200){
			window.location = home;
		}
	}
	// set id parameter
	url += `?id=${id}&status=${event.target.value}`;
	// set verb and url
	xhr.open("PUT", url);
	// send request
	xhr.send();
}



// onclick function for editing reimbursement
let editReimbursement = (event, id) =>{
	// reimbursement div id
	let rmbId = event.target.parentElement.parentElement.parentElement.id;
	// get select element
	let options = document.querySelector(`#${rmbId} select`);
	// enable updates
	options.removeAttribute("disabled");
	// listen for changes in select element
	options.addEventListener("change", (event)=>{handleChange(event, id)});
}

let createReimbursement = (email) =>{
	// API endpoint for creating reimbursement
	let url = "http://localhost:8080/project1/api/reimbursement"
	// home url
	let home = "http://localhost:8080/project1/home";
	// get new reimbursment amount
	let amount = document.querySelector("#new-amount").value;
	// get new reimbursment details
	let details = document.querySelector("#new-details").value;
	//validate input
	if(!isNaN(Math.floor(amount))){
		// ajax query to create reimbursement
		let xhr = new XMLHttpRequest();
		// refresh home page if successful
		xhr.onreadystatechange = () =>{
			if(xhr.readyState === 4 && xhr.status === 200){
				window.location = home;
			}
		}
		// set data in parameters
		url += `?amount=${amount}&details=${details}&email=${email}`;
		// set post request and url
		xhr.open("POST", url);
		// send request
		xhr.send();
	} else{
		alert("Please enter a valid amount.");
	}
}

let changeReimbursementLayout = () =>{
	//get user from user storage
	let user = JSON.parse(sessionStorage.getItem("token"));
	// get new reimbursement div
	let newRmb = document.querySelector("#new-rmb");
	// set new layout for input
	newRmb.innerHTML=
	`
		<h4>New Reimbursement</h4>
		<hr>
		<em>Amount:</em>
		<input type="text" id="new-amount" maxlength="17">
		<em>Details:</em>
		<input type="text" id="new-details" maxlength="60">
	`;
	// create update button
	let updateBtn = document.createElement("div");
	updateBtn.className = "center-flex";
	// add button text
	updateBtn.innerHTML = 
	`
		<div id="submit-rmb" class="button fancy-button small">
			<div class="slide"></div>
			<a style="font-size:14px;">Submit</a>
		</div>
	`;
	// add click event listener
	updateBtn.addEventListener("click", ()=>{createReimbursement(user.employee_email)});
	// add button to div
	newRmb.appendChild(updateBtn);
	
}