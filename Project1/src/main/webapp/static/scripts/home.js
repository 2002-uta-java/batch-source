/**
 * 
 */

let token = sessionStorage.getItem("token");
let user;

if(!token){
	window.location.href="http://localhost:8080/login";
} else {
	let baseUrl = "http://localhost:8080/api/user";
	sendAjaxGet(baseUrl, loadPage);
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

function loadPage(xhr) {
	user = JSON.parse(xhr.response);
	
	if (user.acctType == "EMPLOYEE") {
		document.getElementById("create-request-btn").hidden = false;
	}
	
	sendAjaxGet("http://localhost:8080/api/users/" + user.id + "/requests", loadRequests);
}

document.getElementById("submit-new-request").addEventListener("click", createRequest);

function createRequest(e) {

	const formData = new FormData(document.getElementById("create-request-form"));
	
	let params = "";
	
	for (let entry of formData.entries()) {
		params += entry[0] + "=" + entry[1] + "&";
	}
	
	const xhr = new XMLHttpRequest();
	xhr.open("POST", "http://localhost:8080/api/requests");
	
	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status===200){
			$('#create-request').modal('hide');
			sendAjaxGet("http://localhost:8080/api/users/" + user.id + "/requests", loadRequests);
		} else if (this.readyState===4){
//			window.location.href="http://localhost:8080/login";
			console.error("Server error");
		}
	}
	xhr.setRequestHeader("Authorization",token);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	
	xhr.send(params);
}

function loadRequests(xhr) {
	
	const res = JSON.parse(xhr.response);
	console.table(res);
	const tableBody = document.getElementById("request-table").children[1];
	tableBody.innerHTML = '';

	const statusFilter = $("#status-filter").val();
	
	for(let req of res) {

		if (statusFilter == "ALL" || req.status == statusFilter) {
		
		const dateStr = req.submitted.monthValue + "/" + req.submitted.dayOfMonth + "/" + req.submitted.year;
		const dateFull = req.reimburseDate.month + " " + req.reimburseDate.dayOfMonth + ", " + req.reimburseDate.year;
		
		const tr = document.createElement("tr");
		tr.dataset.toggle="collapse";
		tr.dataset.target="#request-"+req.id;
		tr.className = "accordion-toggle clickable";
		tr.innerHTML = `<td>${req.id}</td><td>${dateStr}</td><td>${req.amount}</td><td>${req.status}</td>`;
		tableBody.appendChild(tr);

		const reviewSection = `
			<div class="review-section">
				<button id="approve-btn" class="btn btn-success" data-reqid="${req.id}">Approve</button>
				<button id="deny-btn" class="btn btn-danger" data-reqid="${req.id}">Deny</button>
			</div>
		`;
		
		const trHidden = document.createElement("tr");
		trHidden.innerHTML = `<td colspan="4" style="padding: 0;"><div id="request-${req.id}" class="collapse">
			<div class="hidden-row">
				<div class="req-header">
					<h4>${dateFull}</h4>
					<h5>Category: ${req.category}</h5>
				</div>
				<h5>Description: ${req.description}</h5>
				${user.acctType == "MANAGER" ? reviewSection : ""}
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

	$('#approve-btn').on("click", reviewRequest);
	$('#deny-btn').on("click", reviewRequest);
	
}

function reviewRequest(e) {

	const reviewType = e.target.id;

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

$("#logout-button").on("click", (e)=>{
	sessionStorage.removeItem('token');
	window.location.href="http://localhost:8080/login";
});

$("#status-filter").on("change", (e)=>{
	sendAjaxGet("http://localhost:8080/api/users/" + user.id + "/requests", loadRequests);
})