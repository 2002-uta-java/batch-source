
document.getElementById("submit_btn").addEventListener("click",newUser);

function newUser(){
	console.log("NEW USER CALLED");
	let firstname = document.getElementById("firstname_input").value;
	let lastname = document.getElementById("lastname_input").value;
	let username = document.getElementById("username_input").value;
	let email = document.getElementById("email_input").value;
	let password = document.getElementById("password_input").value;
	let role = document.getElementById("role_input").value;
	console.log("fn: "+firstname);
	
	
	let reqBody = `firstname=${firstname}&lastname=${lastname}&username=${username}&email=${email}&password=${password}&role=${role}`;
	
	
	let xhr  = new XMLHttpRequest(); 
	let url  = "http://localhost:8080/project_one/create_user";

	xhr.onreadystatechange = function(){
		if(this.readyState == 4 && this.status==200){
				console.log("user registration submitted");
				window.location.href="http://localhost:8080/project_one/login";
			
		} else if (this.readyState==4){
			console.log("account info invalid: ");
			document.getElementById("errorbox").innerHTML=
			`    
			<div class="card text-white bg-danger">
			  <div class="card-body">
			    <p class="card-text"> Account Info not sufficient</p>
			  </div>
			</div>
				`;
		
		}
	}
	


	xhr.open("POST",url);
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");

	xhr.send(reqBody);
	
}
