
window.addEventListener("load",profileLoad);

function profileLoad(){

	
	let xhr = new XMLHttpRequest();
	let token = sessionStorage.getItem("token");
	let url = "http://localhost:8080/project_one/api/userProfile?token="+token;
	xhr.open("GET",url);
	
	xhr.onreadystatechange=function(){
		if(xhr.readyState === 4 && xhr.status === 200){
			console.log("Profile Found \n");
			
			var User = JSON.parse(xhr.response);
			
			if (User == null){
				console.log("logged out");
			} else{
				document.getElementById("table01").innerHTML=
					`<table
					 id="table"
					 data-toggle="table"
					 data-flat="true"
					 data-search="true"
					 data-url="json/data3.json">
					<thead>
						<tr>
							<th data-field="${User.firstname}" data-sortable="true">${User.firstname}</th>
							<th data-field="${User.lastname}" data-sortable="true">${User.lastname}</th>
							<th data-field="${User.username}" data-sortable="true">${User.username}</th>
							<th data-field="${User.email}" data-sortable="true">${User.email}</th>
							<th data-field="${User.role}" data-sortable="true">${User.role}</th>
						</tr>
					</thead>
					</table>`;
			}
			

		} else if (xhr.readyState===4){
			console.log("status "+xhr.status);
			console.log("User not found");
		}
	}
	 token = sessionStorage.getItem("token");
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	let requestBody = `token=${token}}`;
	xhr.send(requestBody);
	
	
}