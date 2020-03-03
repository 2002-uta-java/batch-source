/*
 * 
 */
	let ta = token.split(":");
	document.getElementById("user").innerHTML = `Welcome ${ta[0]}`;
	document.getElementById("logout-btn").addEventListener("click", requestLogout);
	document.getElementById("profile-btn").addEventListener("click", showProfile);
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
	
	function requestLogout(){
		window.location.href="http://localhost:8080/RepayPal/logout";
	}
	
	function showProfile(){
		let baseUrl = "http://localhost:8080/RepayPal/api/users/"+ta[0];
		sendAjaxGet(baseUrl, displayProfile);
	}
	
	function displayProfile(xhr){
		let profile = JSON.parse(xhr.response);
		console.log(profile);
		document.getElementById("modalUsername").innerText = profile.username;
		document.getElementById("modalFirstname").value = profile.firstName;
		document.getElementById("modalLastname").value = profile.lastName;
		document.getElementById("modalPassword").value = profile.password;
		document.getElementById("modalIsManager").innerText = profile.manager;
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