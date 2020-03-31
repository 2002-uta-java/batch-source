
document.getElementById("sign-in-button").addEventListener("click", signIn);


function signIn() {
	let un = document.getElementById("usernameInput").value;
	let pw = document.getElementById("passwordInput").value;
	let xhr = new XMLHttpRequest();
	let url = "http://localhost:8081/project_1/signin";
	let message = document.getElementById("incorrect-attempt");
	console.log("un: " + un + " pw: " + pw);
	xhr.open("POST", url);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log("sign in successful");
			let auth = xhr.getResponseHeader("Authorization");
			sessionStorage.setItem("token", auth);
			console.log("auth: " + auth);
			let authArr = auth.split(":");
			if (authArr.length === 5) {
				window.location.href = "http://localhost:8081/project_1/home";
			} 
		} else if (xhr.readyState == 4) {
			console.log("incorrect credentials");
			message.hidden = false;
		}
	}
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let requestBody = `username=${un}&password=${pw}`;
	xhr.send(requestBody);
}