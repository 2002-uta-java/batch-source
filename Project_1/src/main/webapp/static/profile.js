
window.addEventListener("load",profileLoad);

function profileLoad(){

	
	let xhr = new XMLHttpRequest();
	let token = sessionStorage.getItem("token");
	let url = "http://localhost:8080/project_one/api/userProfile?token="+token;
	xhr.open("GET",url);
	
	xhr.onreadystatechange=function(){
		if(this.readyState === 4 && this.status === 200){
			console.log("Profile Found \n");
		
			document.getElementById("table01").innerHTML=`	
			<table id="table" 
			data-toggle="table"
			data-url="http://localhost:8080/project_one/userProfile?token=${token}"
			<thead>
				<tr>
					<th data-field="firstname">First name</th>
					<th data-field="lastname">Last Name</th>
					<th data-field="username">username</th>
					<th data-field="email">email</th>
					<th data-field="role">role</th>
				</tr>
			</thead>
		</table>`
		} else if (this.readyState===4){
			console.log("status "+this.status);
			console.log("User not found");
		}
	}
	 token = sessionStorage.getItem("token");
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	let requestBody = `token=${token}}`;
	xhr.send(requestBody);
	
	
}