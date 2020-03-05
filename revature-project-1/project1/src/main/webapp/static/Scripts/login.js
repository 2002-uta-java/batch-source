/**
 * 
 */

document.getElementById("login-btn").addEventListener("click", requestLogin);

function requestLogin(){
	
	let user = document.getElementById("username").value;
	let pass = document.getElementById("password").value;
	
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8080/project1/api/login";
	xhr.open("POST", url);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log("success!!!");
			let auth = xhr.getResponseHeader("Authorization");
            sessionStorage.setItem("token", auth);
            let empToken = sessionStorage.getItem("token");
            
            // maybe?
            if(empToken.includes("Manager")){
                window.location.href="http://localhost:8080/project1/manager-homepage"
            }
            else if(empToken.includes("Employee")){
                window.location.href="http://localhost:8080/project1/employee-homepage"
            }
		} 
		else if (xhr.readyState == 4){
			console.log("incorrect credentials");
			document.getElementById("login-error").innerText = "Username or Password is incorrect!";
		}
	}
	
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let requestBody = `username=${user}&password=${pass}`;
	xhr.send(requestBody);
}