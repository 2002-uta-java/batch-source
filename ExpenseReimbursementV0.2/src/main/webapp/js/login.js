
window.onload = function() {
	document.getElementById("submit-btn").addEventListener("click", login);
}

function login() {
	// take the values from and attempt to login in
	let username = document.getElementById("input-email").value;
	let password = document.getElementById("input-password").value;
	
	console.log("login() called");
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = tryToLogin;
// = function(){
// if(this.readyState == 4){
// console.log("got response: " + this.responseText);
// }
// };

		xhr.open("POST", "login");
	let body = 
`login
${username}
${password}
`;
	console.log("sending body with: " + body);
	xhr.send(body);
}

function tryToLogin() {
	if(this.readyState == 4){
		if(this.status < 200 || this.status > 299){
			console.log("setting invalid-login");
			document.getElementById("invalid-login").innerText = "Invalid Email/Password";
		}else{
			// TODO need to redirect to employee page
		}
		console.log("got response: " + this.responseText);
	}
}