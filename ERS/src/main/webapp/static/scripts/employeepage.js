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

	// const statusFilter = $("#status-filter").val();
	// const nameFilter = $("#name-filter").val();
	
	for(let req of res) {

		// const dateStr = req.date.monthValue + "/" + req.date.dayOfMonth + "/"
		// + req.date.year;
				
		const tr = document.createElement("tr");
		tr.dataset.target="#request-"+req.reimbursementId;
		tr.innerHTML = `<td><input type="checkbox" name="reimbursement" value = ${req.reimbursementId}></td>
						<td>${req.reimbursementId}</td>
		                <td>${req.date}</td>
		                <td>${req.description}</td>
		                <td>${req.category}</td>
		                <td>$${req.cost}</td>
		                <td>${req.status}</td>
		                <td>${req.comments}</td>`;
		
		tableBody.appendChild(tr);
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

$(document).ready(function(){	
	  $("rTable").simpleCheckboxTable();

});

$("#logout").on("click", (e)=>{
	window.location.href="http://localhost:8080/ERS/login";
});


//$(document).ready(function(){
//	  $("rTable").simpleCheckboxTable({
//	    onCheckedStateChanged:function($checkbox) {
//	      // Do something
//	    	
//	    	//printChecked();
//	    }
//
//	  });
//});

//var printChecked = function() {
//	  var n = $( "input:checked" ).length;
//	  $( "div" ).text( n + (n === 1 ? " is" : " are") + " checked!" );
//};
//$("input[type=checkbox]" ).on( "click", printChecked );

$(document).ready(function() {
    $("#btn-approve-reimbursement").click(function(){
        var reimbursementsList=[];
        $.each($("input[name='reimbursement']:checked"), function(){
        	reimbursementsList.push($(this).val());
        	//reimbursementsList= $(this).val()+$(this).val();
        	
        	//reimbursementsList.concat(($(this).val()),','); 
        });
        alert("Selected reimbursements are : " + reimbursementsList);
            
        sendAjaxPut("http://localhost:8080/ERS/api/reimbursement/bulk", {
        	reimbursementsList:{reimbursementsList},
			status:"APPROVE"		
			}, ()=>{
				window.location = "http://localhost:8080/ERS/employeehome";
		});       
        
    });
    
    
 $("#btn-deny-reimbursement").click(function(){
        var reimbursementsList = [];
        $.each($("input[name='reimbursement']:checked"), function(){
        	reimbursementsList.push($(this).val());
        });
        alert("Selected reimbursements are : " + reimbursementsList.join(", "));
            
        sendAjaxPut("http://localhost:8080/ERS/api/reimbursement/bulk", {
        	reimbursementsList:{reimbursementsList},
			status:"DENY"		
			}, ()=>{
				window.location = "http://localhost:8080/ERS/employeehome";
		});       
        
    });
    
    
});

//function updateReimbursements(url,reimbist,status){
//	const xhr = new XMLHttpRequest();
//	xhr.open("PUT", url);
//	
//	xhr.onreadystatechange = function(){
//		if(this.readyState===4 && this.status===200){
//			callback(this);
//		} else if (this.readyState===4){
//			// window.location.href="http://localhost:8080/login";
//			console.error("Server error");
//		}
//	}
//	
//	xhr.setRequestHeader("Authorization",token);
//	if (userRole !== "MANAGER") {
//		xhr.setRequestHeader("employee_id",emplid);		
//	} 
//	
//	xhr.send(reimbist);
//	
//};
//
//function createRequest(e) {
//	
//	const amount = $("#cost").val();
//	
//
//	if (amount <= 0 || amount > 1000000) {
//		document.getElementById("invalid-notice").hidden = false;
//	} else {
//		sendAjaxPost("http://localhost:8080/ERS/api/reimbursement", {
//			description: $("#description").val(),
//			category: $("#category").val(),
//			cost: $("#cost").val(),			
//			comments: $("#comments").val()		
//			}, ()=>{
//				window.location = "http://localhost:8080/ERS/employeehome";
//		});
//	}
//
//}
//
//
//$.ajax({
//	   type: "POST",
//	   data: {info:info},
//	   url: "index.php",
//	   success: function(msg){
//	     $('.answer').html(msg);
//	   }
//	});



  


