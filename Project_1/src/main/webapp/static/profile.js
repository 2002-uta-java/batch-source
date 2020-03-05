
document.getElementById("login_submit").addEventListener("load",profileLoad);

function profileLoad(){

	
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8080/project_one/userProfile";
	xhr.open("GET",url);
	
	xhr.onreadystatechange=function(){
		if(this.readyState == 4 && this.status == 200){
			console.log("Profile Found \n");
			let token = sessionStorage.getItem("token");
			console.log(token);
			//window.location.href="http://localhost:8080/project_one/index"/*TODO: change to user page*/
		} else if (this.readyState==4){
			console.log();
			console.log("User not found");
		}
	}
	
	xhr.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	let requestBody = `token=${token}}`;
	xhr.send(requestBody);
	
}