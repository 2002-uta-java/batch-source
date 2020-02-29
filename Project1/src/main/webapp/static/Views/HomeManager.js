function sendAjaxGet(url, callback){
	let xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status===200){
			callback(this);
		} else if (this.readyState===4){
			window.location.href="http://localhost:8080/Project1/login";
		}
	}
	xhr.setRequestHeader("Authorization",token);
	xhr.send();
}

function displayName(xhr){
	let user = JSON.parse(xhr.response); // should recieve the current employee logged in.
	console.log(user); 
	document.getElementById("user").innerHTML = `Welcome ${user.firstName} ${user.lastName}`;
	
}

/*
 * (After successful login... on page load...)
 * if we are not redirected when checking for the token, a request will be made to 
 * the url for that particular user 
 */
let token = sessionStorage.getItem("token");

if(!token){
	window.location.href="http://localhost:8080/Project1/login";
} else {
	let tokenArr = token.split(":");
	console.log(tokenArr);
	if(tokenArr.length===2){
		let baseUrl = "http://localhost:8080/Project1/api/users/";
		sendAjaxGet(baseUrl+tokenArr[0], displayName);
	} else {
		window.location.href="http://localhost:8080/Project1/login";
	}
}