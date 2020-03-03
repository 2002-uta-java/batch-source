/**
 * 
 */

document.getElementById("login-button").addEventListener("click", attemptLogin);



function attemptLogin() {
	
	let usernameInput = document.getElementById("username_input").value;
	let passwordInput = document.getElementById("password_input").value;
	
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8080/project_1/login";
	
	xhr.open("POST", url);
	
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
			console.log("we got inside the 4 && 200 !!!");
			let responseHeaderContent = xhr.getResponseHeader("AuthToken");
			sessionStorage.setItem("token", responseHeaderContent);
			window.location.href="http://localhost:8080/project_1/home";
		}
		
		else if (xhr.readyState == 4) {
			console.log("incorrect credentials");

			// need to update the webpage (like set an erros not hidden?)
			
		}
	}
	
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let requestBody = `username=${usernameInput}&password=${passwordInput}`;
	xhr.send(requestBody);
	
}