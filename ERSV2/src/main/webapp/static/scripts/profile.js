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
		baseUrl= "http://localhost:8080/ERS/api/employee";	
		sendAjaxGet(baseUrl, loadUserProfile);
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
	xhr.setRequestHeader("employee_id",emplid);		
	xhr.send();
}
  

function loadUserProfile(xhr) {
	
	const res = JSON.parse(xhr.response);
	console.table(res);
	
	$('#id').val(emplid);
	$('#firstname').val(res.firstName);
	$('#lastname').val(res.lastName);
	$('#title').val(res.title);
	$('#email').val(res.email);
	$('#username').val(res.username);
	$('#password').val(res.password);
}

$("#logout").on("click", (e)=>{
	sessionStorage.removeItem('token');
	window.location.href="http://localhost:8080/ERS/login";
});


$(document).ready(function(){
	$("#btn-update-profile").click(function(){
	
		var id  = $('#id').val();
		var firstName = $('#firstname').val();
		var lastname = $('#lastname').val(); 
		var title = $('#title').val();
		var email = $('#email').val();
		var username = $('#username').val();
		var password = $('#password').val();
		
		
		$.ajax({
			url:'http://localhost:8080/ERS/api/employee',
			type:'PUT',
			data: JSON.stringify({
				employeeId:id,
				firstName:firstName,
				LastName:lastname,
				title:title,
				username:username,
				password:password,
				email:email			
				})
			 //dataType: 'json'
			}).done(function(response){
				console.log('Profile updated successfullly', response);
				//console.log(response);
				window.location = "http://localhost:8080/ERS/profile";
			}); 	
	});  
});  





    





