function sendAjaxPostForRegister(url, params, callback){
	const xhr = new XMLHttpRequest();
	xhr.open("POST", url);

	let paramStr = "";

	for (let [p, v] of Object.entries(params)) {
		paramStr += p + "=" + v + "&";
	}
	
	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status===200){
			callback(this);
		} else if (this.readyState===4){
//			window.location.href="http://localhost:8080/login";
			console.error("Server error");
		}
	}

	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");	
	xhr.send(paramStr);
}


function register(e) {
	
	sendAjaxPostForRegister("http://localhost:8080/ERS/api/employee", {
			firstname: $("#firstname").val(),
			lastname: $("#lastname").val(),
			title: $("#title").val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()					
			}, ()=>{
				window.location = "http://localhost:8080/ERS/login";
		});	
}


document.getElementById("register-btn").addEventListener("click", register);






