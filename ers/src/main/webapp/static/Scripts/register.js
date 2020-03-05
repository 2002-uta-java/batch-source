let addUserUrl = "http://localhost:8080/ers/addEmployee";

document.getElementById("add-btn").addEventListener("click", addEmployee);

function sendAjaxPost(url, requestBody) {
	let xhr = new XMLHttpRequest;
	xhr.open("Post", url);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200)
			alert("Updated");
	}
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send(requestBody);
}

function addEmployee() {
	
	let firstname = document.getElementById("firstname").value;
	let lastname  = document.getElementById("lastname").value;
	let email     = document.getElementById("email").value;
	let phone     = document.getElementById("phone").value;
	let password  = document.getElementById("password").value;
	let managerT = document.getElementById("isManager").checked;
		
	let requestBody = `firstname=${firstname}&lastname=${lastname}&email=${email}&email=${email}&phone=${phone}&password=${password}&isManager=${managerT}`;
	
	sendAjaxPost(addUserUrl, requestBody);
}