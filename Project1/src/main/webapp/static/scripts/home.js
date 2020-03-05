let token;
let user;
let requests;
let users;

window.onload = function() {
	token = sessionStorage.getItem("token");
	
	if(!token){
		window.location.href="http://localhost:8080/login";
	} else {
		let baseUrl = "http://localhost:8080/api/user";
		sendAjaxGet(baseUrl, loadPageBase);
		document.getElementById("submit-new-request").addEventListener("click", createRequest);
		
	}
}

function sendAjaxGet(url, callback){
	const xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	
	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status===200){
			callback(this);
		} else if (this.readyState===4){
//			window.location.href="http://localhost:8080/login";
			console.error("Server error");
		}
	}
	
	xhr.setRequestHeader("Authorization",token);
	xhr.send();
}

function sendAjaxPost(url, params, callback){
	const xhr = new XMLHttpRequest();
	xhr.open("POST", url);

	let paramStr = "";

	for (let [p, v] of Object.entries(params)) {
		paramStr += p + "=" + v + "&";
	}
	
	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status===200){
			callback(this);
		} else if (this.readyState===4){
//			window.location.href="http://localhost:8080/login";
			console.error("Server error");
		}
	}

	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.setRequestHeader("Authorization",token);
	xhr.send(paramStr);
}

function sendAjaxPut(url, params, callback){
	const xhr = new XMLHttpRequest();
	xhr.open("PUT", url);

	let paramStr = "";

	for (let [p, v] of Object.entries(params)) {
		paramStr += p + "=" + v + "&";
	}
	
	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status===200){
			callback(this);
		} else if (this.readyState===4){
//			window.location.href="http://localhost:8080/login";
			console.error("Server error");
		}
	}

	console.log(paramStr);

	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.setRequestHeader("Authorization",token);
	xhr.send(paramStr);
}

function loadPageBase(xhr) {
	user = JSON.parse(xhr.response);

	loadProfile();
	
	if (user.acctType == "EMPLOYEE") {
		document.getElementById("create-request-btn").hidden = false;
		sendAjaxGet("http://localhost:8080/api/users/" + user.id + "/requests", loadRequests)
	} else if (user.acctType == "MANAGER") {
		document.getElementById("user-section").hidden = false;
		document.getElementById("name-filter-section").hidden = false;
		sendAjaxGet("http://localhost:8080/api/users/", loadUsers);
	}
	
}

function createRequest(e) {

	const formData = new FormData(document.getElementById("create-request-form"));

	const amount = formData.get("amount");

	if (amount <= 0 || amount > 1000000) {
		document.getElementById("invalid-notice").hidden = false;
	} else {
		sendAjaxPost("http://localhost:8080/api/requests", {
			amount: formData.get("amount"),
			date: formData.get("date"),
			description: formData.get("description"),
			category: formData.get("category")
		}, ()=>{
			$('#create-request').modal('hide');
			sendAjaxGet("http://localhost:8080/api/users/" + user.id + "/requests", loadRequests);
		});
	}
	

}

function loadRequests(xhr) {
	
	const res = JSON.parse(xhr.response);
	console.table(res);
	const tableBody = document.getElementById("request-table").children[1];
	tableBody.innerHTML = '';

	const statusFilter = $("#status-filter").val();
	const nameFilter = $("#name-filter").val();
	
	for(let req of res) {

		if ((statusFilter == "ALL" || req.status == statusFilter) && (!nameFilter || req.emplAccount.name == nameFilter)) {
		
		const dateStr = req.submitted.monthValue + "/" + req.submitted.dayOfMonth + "/" + req.submitted.year;
		const dateFull = req.reimburseDate.month + " " + req.reimburseDate.dayOfMonth + ", " + req.reimburseDate.year;
		
		const tr = document.createElement("tr");
		tr.dataset.toggle="collapse";
		tr.dataset.target="#request-"+req.id;
		tr.className = "accordion-toggle clickable";
		tr.innerHTML = `<td>${req.id}</td><td>${dateStr}</td><td>$${req.amount.toFixed(2)}</td><td>${req.status}</td>`;
		tableBody.appendChild(tr);

		let resolvedBy;

		if (user.acctType == "MANAGER") {
			resolvedBy = users.filter((e)=>e.id == req.resolvedBy)[0];
		}

		const reviewSection = user.acctType == "MANAGER" ? `
			<div class="review-section">
				${req.status == "PENDING" ? `
				<button class="btn btn-success approve-btn" data-reqid="${req.id}" data-type="approve-btn">Approve</button>
				<button class="btn btn-danger deny-btn" data-reqid="${req.id}" data-type="deny-btn">Deny</button>
				` : `
				<h5>${req.status} By ${resolvedBy ? resolvedBy.name : "error"}</h5>
				`}
			</div>
		` : `
			<div class="review-section">
				<h5>${req.status == "PENDING" ? "Awaiting Review" : req.status}</h5>
			</div>
		`;
		
		const trHidden = document.createElement("tr");
		trHidden.innerHTML = `<td colspan="4" style="padding: 0;"><div id="request-${req.id}" class="collapse">
			<div class="hidden-row">
				<div class="req-header">
					<h4>${dateFull}</h4>
					<h5>Category: ${req.category}</h5>
				</div>
				${user.acctType == "MANAGER" ? `<p>Requested by: ${req.emplAccount.name}</p>` : ""}
				<p>Description: ${req.description}</p>

				${reviewSection}
			</div>
		</div></td>`;
		tableBody.appendChild(trHidden);
		
		}
	}
	
	$('.accordion-toggle').on('click', (e)=>{
		$('.accordion-toggle').removeClass("active-req");
		if ($(e.currentTarget.dataset.target).hasClass("show")) {
			$(e.currentTarget).removeClass("active-req");
		} else {
			$(e.currentTarget).toggleClass("active-req");
		}
		$('.collapse').collapse('hide');
		$(e.currentTarget.dataset.target).collapse('toggle')
	});

	$('.approve-btn').each((ind, elm)=>{elm.onclick = reviewRequest});
	$('.deny-btn').each((ind, elm)=>{elm.onclick = reviewRequest});
	
}

function reviewRequest(e) {

	const reviewType = e.target.dataset.type;

	console.log(e.target);

	const xhr = new XMLHttpRequest();
	xhr.open("PUT", "http://localhost:8080/api/requests/" + e.target.dataset.reqid + "/");
	
	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status===200){
			sendAjaxGet("http://localhost:8080/api/users/" + user.id + "/requests", loadRequests);
		} else if (this.readyState===4){
//			window.location.href="http://localhost:8080/login";
			console.error("Server error");
		}
	}
	xhr.setRequestHeader("Authorization", token);
	xhr.setRequestHeader("Review", reviewType == "approve-btn" ? "APPROVED" : "DENIED");
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	
	xhr.send();

}

function loadUsers(xhr) {
	users = JSON.parse(xhr.response);

	console.table(users);
	
	const tableBody = document.getElementById("user-table").children[1];
	tableBody.innerHTML = '';

	for (let u of users) {

		const manager = users.filter((e)=>e.id == u.managerId)[0];

		const tr = document.createElement("tr");
		tr.innerHTML = `<td>${u.id}</td><td>${u.name}</td><td>${u.email}</td><td>${manager ? manager.name : "-"}</td>`;
		tableBody.appendChild(tr);
	}

	sendAjaxGet("http://localhost:8080/api/requests/", loadRequests);

}

function loadProfile() {
	const profile = document.getElementById("profile-body");
	document.getElementById("nav-name").innerText = user.name;

	profile.innerHTML = `
		<h3>Profile <i class="fa fa-pencil"></i></h3>
		<h5>Name: ${user.name}</h5>
		<h5>Email: ${user.email}</h5>
		<h5>Password: ********</h5>
	`;

	$(".fa-pencil").click(()=>{

		profile.innerHTML = `
			<h3>Profile <i class="fa fa-check"></i></h3>
			<h5>Name: <input id="edit-name" type="text" value="${user.name}"></h5>
			<h5>Email: <input id="edit-email" type="text" value="${user.email}"></h5>
			<h5>Password: <input id="edit-pass" type="password" value="********"></h5>
		`;

		$(".fa-check").click(()=>{
			const newName = $("#edit-name").val();
			const newEmail = $("#edit-email").val();
			const newPass = $("#edit-pass").val();

			if (newName && newEmail && newPass) {

				
				sendAjaxPut("http://localhost:8080/api/user/", {
					name: newName,
					email: newEmail,
					password: newPass
				}, () => {
					sendAjaxGet("http://localhost:8080/api/user", (xhr)=>{
						user = JSON.parse(xhr.response);
						loadProfile();
					});
				});
			}
		});
	});
}

$("#logout-button").on("click", (e)=>{
	sessionStorage.removeItem('token');
	window.location.href="http://localhost:8080/login";
});

$("#status-filter").on("change", (e)=>{
	if (user.acctType == "EMPLOYEE") {
		sendAjaxGet("http://localhost:8080/api/users/" + user.id + "/requests", loadRequests)
	} else if (user.acctType == "MANAGER") {
		sendAjaxGet("http://localhost:8080/api/requests/", loadRequests);
	}
})

$("#name-filter").on("blur", (e)=>{
	if (user.acctType == "EMPLOYEE") {
		sendAjaxGet("http://localhost:8080/api/users/" + user.id + "/requests", loadRequests)
	} else if (user.acctType == "MANAGER") {
		sendAjaxGet("http://localhost:8080/api/requests/", loadRequests);
	}
})