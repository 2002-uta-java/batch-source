/*
 * 
 */
	let ta = token.split(":");
	document.getElementById("user").innerHTML = `Welcome ${ta[0]}`;
	document.getElementById("logout-btn").addEventListener("click", requestLogout);
	document.getElementById("profile-btn").addEventListener("click", showProfile);
	document.getElementById("empls-btn").addEventListener("click", showEmployees);
	document.getElementById("addReims").addEventListener("click", postReimbursement);
	document.getElementById("modalUsernameR").value = ta[0];
	document.getElementById("updateProfile").addEventListener("click", postUpdateUser);
	showReims();
	console.log(ta);
	function showReims(){
		let baseUrl = "http://localhost:8080/RepayPal/api/";
		if(ta[1] === "true"){
			document.getElementById("manager").innerHTML = "Manager Home";
			document.getElementById("empls-btn").style.display = "block";
			document.getElementById("reims-btn").style.display = "none";
			sendAjaxGet("http://localhost:8080/RepayPal/api/reimbursements", displayReimbursementsAsManager);
		}else{
			document.getElementById("manager").innerHTML = "Employee Home";
			document.getElementById("empls-btn").style.display = "none";
			document.getElementById("reims-btn").style.display = "block";
			let newUrl = baseUrl+"reimbursements/"+ta[0];
			sendAjaxGet(newUrl, displayReimbursements);
		}
	}


	function sendAjaxGet(url, callback){
		let xhr = new XMLHttpRequest();
		xhr.open("GET", url);
		xhr.onreadystatechange = function(){
			if(this.readyState===4 && this.status===200){
				callback(this);
			} else if (this.readyState===4){
				window.location.href="http://localhost:8080/RepayPal/home";
			}
		}
		xhr.setRequestHeader("Authorization",token);
		xhr.send();
	}
	
	function displayReimbursements(xhr){
		let reimbursements = JSON.parse(xhr.response);
		let table = document.getElementById("reims");
		console.log(reimbursements);
		for(let reimbursement of reimbursements){
			let id = reimbursement.id;
			let username = reimbursement.username;
			let status = reimbursement.status;
			let amount = reimbursement.amount;
			let description = reimbursement.description;
			let resolution = reimbursement.resolved;
			let newRow = document.createElement("tr");
			newRow.innerHTML = `<tr><td>${id}</td><td>${username}</td><td>${status}</td><td>${amount}</td><td>${description}</td><td>${resolution}</td></tr>`;
			table.appendChild(newRow);
		}
	}
	
	
	function displayReimbursementsAsManager(xhr){
		let reimbursements = JSON.parse(xhr.response);
		let table = document.getElementById("reims");
		let headerRow = document.getElementById("table-header");
		let hrw = document.createElement("th");
		hrw.innerText = "Submit";
		headerRow.appendChild(hrw);
		console.log(reimbursements);
		//Populate the list
		for(let reimbursement of reimbursements){
			let id = reimbursement.id;
			let username = reimbursement.username;
			let status = reimbursement.status;
			let amount = reimbursement.amount;
			let description = reimbursement.description;
			let resolution = reimbursement.resolved;
			let newRow = document.createElement("tr");
			let resolveBtn = `<div class="btn-group"><button class="btn btn-info" id="accept-btn-${id}" onclick="acceptReims(${id})">Accept</button><button class="btn btn-warning" id="deny-btn-${id}" onclick="denyReims(${id})">Deny</button></div>`;
			newRow.innerHTML = `<tr><td>${id}</td><td>${username}</td><td>${status}</td><td>${amount}</td><td>${description}</td><td>${resolution}</td><td>${resolveBtn}</td></tr>`;
			table.appendChild(newRow);
		}
		//Check for Approved or Denied Reimbursements
		for(let reimbursement of reimbursements){
			let statusCheck = reimbursement.status;
			if(statusCheck === "Approved" || statusCheck === "Denied"){
				document.getElementById(`accept-btn-${reimbursement.id}`).disabled = "true";
				document.getElementById(`deny-btn-${reimbursement.id}`).disabled = "true";
			}
		}
	}
	
	function requestLogout(){
		window.location.href="http://localhost:8080/RepayPal/logout";
	}
	
	function showProfile(){
		let baseUrl = "http://localhost:8080/RepayPal/api/users/"+ta[0];
		sendAjaxGet(baseUrl, displayProfile);
	}
	
	function showEmployees(){
		let baseUrl = "http://localhost:8080/RepayPal/api/users";
		sendAjaxGet(baseUrl, displayUsers);
	}
	
	function displayProfile(xhr){
		let profile = JSON.parse(xhr.response);
		console.log(profile);
		document.getElementById("modalUsername").value = profile.username;
		document.getElementById("modalFirstname").value = profile.firstName;
		document.getElementById("modalLastname").value = profile.lastName;
		document.getElementById("modalPassword").value = profile.password;
		document.getElementById("modalIsManager").value = profile.manager;
	}
	
	
	function displayUsers(xhr){
		let users = JSON.parse(xhr.response);
		let tableDiv = document.getElementById("users");
		if (tableDiv.hasChildNodes()) {
		    tableDiv.removeChild(tableDiv.childNodes[0]);
		  }
		let table = document.createElement("table");
		table.className = "table table-stripped";
		tableDiv.appendChild(table);
		console.log(users);
		let headerRow = document.createElement("tr");
		headerRow.innerHTML = `<tr><th>Username:</th><th>First Name:</th><th>Last Name:</th><th>Manager:</th></tr>`;
		table.appendChild(headerRow);
		for(let user of users){
			let newRow = document.createElement("tr");
			newRow.innerHTML = `<tr><td>${user.username}</td><td>${user.firstName}</td><td>${user.lastName}</td><td>${user.manager}</td></tr>`;
			table.appendChild(newRow);
		}
	}
	
	function postReimbursement(){
		console.log("Request Started");
		let user = document.getElementById("modalUsernameR").value;
		let amount = document.getElementById("modalAmount").value;
		let desc = document.getElementById("modalDescription").value;
		console.log(user);
		console.log(amount);
		console.log(desc);
		let xhr = new XMLHttpRequest();
		let url = "http://localhost:8080/RepayPal/add-reimbursement";
		xhr.open("POST", url);
		
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				console.log("success!!!");
				window.location.href="http://localhost:8080/RepayPal/home"
			} 
			else if (xhr.readyState == 4){
				console.log("Invalid Data");
			}
		}
		
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		let requestBody = `username=${user}&amount=${amount}&description=${desc}`;
		xhr.send(requestBody);
	}
	
	function postUpdateUser(){
		console.log("Request Started");
		let username = document.getElementById("modalUsername").value;
		let firstName = document.getElementById("modalFirstname").value;
		let lastName = document.getElementById("modalLastname").value;
		let password = document.getElementById("modalPassword").value;
		console.log(username);
		console.log(firstName);
		console.log(lastName);
		console.log(password);
		let xhr = new XMLHttpRequest();
		let url = "http://localhost:8080/RepayPal/update-user";
		xhr.open("POST", url);
		
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				console.log("success!!!");
				window.location.href="http://localhost:8080/RepayPal/home"
			} 
			else if (xhr.readyState == 4){
				console.log("Invalid Data");
			}
		}
		
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		let requestBody = `username=${username}&firstName=${firstName}&lastName=${lastName}&password=${password}`;
		xhr.send(requestBody);
	}
	
	function acceptReims(id){
		console.log(id);
		postUpdateReims(id,"Approved");
	}
	
	function denyReims(id){
		console.log(id);
		postUpdateReims(id,"Denied");
	}
	
	function postUpdateReims(id, status){
		console.log("Request Started");
		let xhr = new XMLHttpRequest();
		let url = "http://localhost:8080/RepayPal/update-reimbursement";
		xhr.open("POST", url);
		
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				console.log("success!!!");
				window.location.href="http://localhost:8080/RepayPal/home"
			} 
			else if (xhr.readyState == 4){
				console.log("Invalid Data");
			}
		}
		
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		let requestBody = `id=${id}&status=${status}&username=${ta[0]}`;
		xhr.send(requestBody);
	}
	
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */