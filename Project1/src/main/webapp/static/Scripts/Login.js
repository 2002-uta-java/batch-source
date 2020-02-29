/**
 * 
 */
function sendAjaxGet(url, callback){
    let xhr = new XMLHttpRequest();
    xhr.open("GET", url);
    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){
            callback(xhr.response);
        }
    }
    xhr.send();
}

function requestLogin(){
	
	let user = document.getElementById("username").value;
	let pass = document.getElementById("password").value;
	
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8080/AuthDemo2/login";
	xhr.open("POST", url);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			console.log("success!!!");
			let auth = xhr.getResponseHeader("Authorization");
			sessionStorage.setItem("token", auth);
			console.log(auth);
			window.location.href="http://localhost:8080/AuthDemo2/home"
		} 
		else if (xhr.readyState == 4){
			console.log("incorrect credentials");
		}
	}
	
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let requestBody = `username=${user}&password=${pass}`;
	xhr.send(requestBody);
}

// If submit button is pressed. send an ajax get request for password authentication i guess.
document.getElementById("login-btn").addEventListener("click", requestLogin);


// If new user button is pressed, send a GET to load a new "Create User" page.