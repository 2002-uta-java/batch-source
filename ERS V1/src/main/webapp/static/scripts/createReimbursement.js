let token;
let user;
let requests;
let users;

window.onload = function() {
	token = sessionStorage.getItem("token");	
	if(!token){
		window.location.href="http://localhost:8080/ERS/login";
	}
	document.getElementById("add-btn").addEventListener("click", createRequest);
}

function sendAjaxPost(url, params, callback){
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
	xhr.setRequestHeader("Authorization",token);
	xhr.send(paramStr);
}



function createRequest(e) {
	
	const amount = $("#cost").val();
	

	if (amount <= 0 || amount > 1000000) {
		document.getElementById("invalid-notice").hidden = false;
	} else {
		sendAjaxPost("http://localhost:8080/ERS/api/reimbursement", {
			description: $("#description").val(),
			category: $("#category").val(),
			cost: $("#cost").val(),			
			comments: $("#comments").val()		
			}, ()=>{
				window.location = "http://localhost:8080/ERS/employeehome";
		});
	}

}