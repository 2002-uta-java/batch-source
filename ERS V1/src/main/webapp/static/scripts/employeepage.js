let token;
let user;
let requests;
let users;
let emplid;
let userRole;

window.onload = function() {
	
	token = sessionStorage.getItem("token");
	// The Auth Token is "EmplID%ROLE"
	var array = token.split("%"); 
	emplid = array[0];
	userRole = array[1]; 
	let baseUrl;
	if(!token){
		window.location.href="http://localhost:8080/ERS/login";
	} else {		
		baseUrl= "http://localhost:8080/ERS/api/reimbursement";	
		sendAjaxGet(baseUrl, loadRequests);
		// document.getElementById("submit-new-request").addEventListener("click",
		// createRequest);
		
	}
	//Don't need to show approve or deny buttons for non managers
	if (userRole !== "MANAGER") {
		 $("#btn-approve-reimbursement").hide();
		 $("#btn-deny-reimbursement").hide();
	
	} 
		
	
}

function sendAjaxGet(url, callback){
	const xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	
	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status===200){
			callback(this);
		} else if (this.readyState===4){
			// window.location.href="http://localhost:8080/login";
			console.error("Server error");
		}
	}
	
	xhr.setRequestHeader("Authorization",token);
	if (userRole !== "MANAGER") {
		xhr.setRequestHeader("employee_id",emplid);		
	} 
	
	xhr.send();
}
  

function loadRequests(xhr) {
	
	const res = JSON.parse(xhr.response);
	console.table(res);
	
	const tableBody = document.getElementById("rTable").children[0];
	tableBody.innerHTML = '';

	const statusFilter = $("#status-filter").val();
	const nameFilter = $("#name-filter").val();
	
	for(let req of res) {
		
		if ((statusFilter == "ALL" || req.status == statusFilter) && (!nameFilter || req.emplAccount.name == nameFilter)) {

		// const dateStr = req.date.monthValue + "/" + req.date.dayOfMonth + "/"
		// + req.date.year;
		var date=new Date(req.date).toLocaleDateString();		
		const tr = document.createElement("tr");
		tr.dataset.target="#request-"+req.reimbursementId;
		tr.innerHTML = `<td><input type="checkbox" name="reimbursement" value = ${req.reimbursementId}></td>
						<td>${req.reimbursementId}</td>
		                <td>${date}</td>
		                <td>${req.description}</td>
		                <td>${req.category}</td>
		                <td>$${req.cost}</td>
		                <td>${req.status}</td>
		                <td>${req.comments}</td>`;
		
		tableBody.appendChild(tr);
		}
	}
	
	//Don't need to show approve or deny buttons for non managers
	if (userRole !== "MANAGER") {
		 $("#btn-approve-reimbursement").hide();
		 $("#btn-deny-reimbursement").hide();
	} 
		
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

	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");	
	xhr.send(paramStr);
}




$("#btn-create-reimbursement").on("click", (e)=>{
	window.location.href="http://localhost:8080/ERS/reimbursement";
});


$("#logout").on("click", (e)=>{
	sessionStorage.removeItem('token');
	window.location.href="http://localhost:8080/ERS/login";
});

$("#profile").on("click", (e)=>{
	window.location.href="http://localhost:8080/ERS/profile";
});
$(document).ready(function(){	
	  $("rTable").simpleCheckboxTable();

});



$(document).ready(function(){
$("#btn-approve-reimbursement").click(function(){
	var reimbursementsList=[];
	$.each($("input[name='reimbursement']:checked"), function(){
		reimbursementsList.push($(this).val());
	});
	$.ajax({
		url:'http://localhost:8080/ERS/api/reimbursement/bulk',
		type:'PUT',
		data: JSON.stringify({reimbursementsList:reimbursementsList,status:'APPROVED'})
		}).done(function(response){
		console.log('Profile updated successfullly', response);
		//console.log(response);
		window.location = "http://localhost:8080/ERS/employeehome";
	  });
   });  
});  

$(document).ready(function(){
	$("#btn-deny-reimbursement").click(function(){
		var reimbursementsList=[];
		$.each($("input[name='reimbursement']:checked"), function(){
			reimbursementsList.push($(this).val());
		});
		$.ajax({
			url:'http://localhost:8080/ERS/api/reimbursement/bulk',
			type:'PUT',
			data: JSON.stringify({reimbursementsList:reimbursementsList,status:'DENIED'})
			//dataType: 'json',			
			}).done(function(response){
				console.log('Profile updated successfullly', response);
			//console.log(response);
				window.location = "http://localhost:8080/ERS/employeehome";
			}); 	
		
		 
	   });  
});  



$("#status-filter").on("change", (e)=>{
	if (userRole === "EMPLOYEE") {
		sendAjaxGet("http://localhost:8080/ERS/api/users/" + emplid + "/reimbursement", loadRequests)
	} else if (userRole === "MANAGER") {
		sendAjaxGet("http://localhost:8080/ERS/api/reimbursement", loadRequests);
	}
})

$("#name-filter").on("blur", (e)=>{
	if (userRole === "EMPLOYEE") {
		sendAjaxGet("http://localhost:8080/ERS/api/users/" + emplid + "/reimbursement", loadRequests)
	} else if (userRole === "MANAGER") {
		sendAjaxGet("http://localhost:8080/ERS/api/reimbursement", loadRequests);
	}
})
     
        





