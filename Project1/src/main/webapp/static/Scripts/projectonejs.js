// this effects password n indexpage
function showPassword() {
    var x = document.getElementById("password-input");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
}

// This post the email and password to server to verify with database

let el = document.getElementById("submit-btn1");
if (el) {
    el.addEventListener('click', logonAjax, false);
}
 

    // Post request with AJAX
 function logonAjax() {
    let emailInput = document.getElementById("email-input").value;
    let passwordInput = document.getElementById("password-input").value;
    
	let xhr = new XMLHttpRequest();
    let url = "http://localhost:8080/Project1/logon";
	xhr.open("POST", url);

	xhr.onreadystatechange = function () {
	    if (xhr.readyState == 4 && xhr.status == 200) {
	        console.log("success!!!");
	        let auth = xhr.getResponseHeader("Authorization");
	        sessionStorage.setItem("token", auth);
            console.log(auth);
            var headers = sessionStorage.getItem("token");
            var arr = headers.split(":");
            console.log(arr);
            let permission = arr[1]; 
            console.log(permission);
            if(permission === 0) {
                window.location.href = "http://localhost:8080/Project1/employeepage"
            } else {
                window.location.href = "http://localhost:8080/Project1/managerpage"
            }
	    } else if (xhr.readyState == 4) {
            console.log("incorrect credentials");
            window.location.href = "http://localhost:8080/Project1/logon"
	    }
	}

	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let requestBody = `email=${emailInput}&password=${passwordInput}`;
	xhr.send(requestBody);
}
