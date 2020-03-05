document.getElementById("login-btn").addEventListener("click", requestLogin);

function requestLogin(){
	let user = document.getElementById("username").value;
	let pass = document.getElementById("password").value;
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8080/project1/login";
	xhr.open("POST", url);
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			let auth = xhr.getResponseHeader("Authorization");
			sessionStorage.setItem("token", auth);
			let tokenArr = auth.split(":");
            window.location.href=`http://localhost:8080/project1/${tokenArr[1]}-homepage`;
		} 
		else if (xhr.readyState == 4){
			console.log("incorrect credentials");
		}
	}
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let requestBody = `username=${user}&password=${pass}`;
	xhr.send(requestBody);
}