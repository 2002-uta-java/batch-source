
document.getElementById("login_submit").addEventListener("click",userLogin);

function userLogin(){
	let user = document.getElementById("un01").value;
	let pw = document.getElementById("pw01").value;
	
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8080/project_one/ulogin";
	
	xhr.open("POST",url);
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	xhr.onreadystatechange=function(){
		if(this.readyState == 4 && this.status == 200){
			console.log("Login Successful \n"+user+" : "+pw+"  "+url);
			let auth = this.getResponseHeader("Authorization");
			sessionStorage.setItem("token",auth);
			console.log(auth);
			window.location.href="http://localhost:8080/project_one/profile";
		} else {
			console.log();
			console.log("Credentials invalid: "+user+" : "+pw);

			document.getElementById("errorbox").innerHTML=
			`    
			<div class="card text-white bg-danger">
			  <div class="card-body">
			    <p class="card-text"> Account does not exist</p>
			  </div>
			</div>
				`;
		}
	}
	
	
	let requestBody = `username=${user}&password=${pw}`;
	xhr.send(requestBody);
	
}