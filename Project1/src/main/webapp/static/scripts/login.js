const loginForm = document.getElementById("login-form");

loginForm.addEventListener("submit", submitForm);

function submitForm(e) {
	e.preventDefault();
	
	const formData = new FormData(loginForm);
	
	const xhr = new XMLHttpRequest();
	const url = "http://localhost:8080/login";
	
	xhr.open("POST", url);
	xhr.onreadystatechange = ()=>{
		if (xhr.readyState == 4 && xhr.status == 200) {			
			const auth = xhr.getResponseHeader("Authorization");
			sessionStorage.setItem("token", auth);
			window.location.href="http://localhost:8080/home";
		}
	}
	
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send("email=" + formData.get('email') + "&password=" + formData.get('password'));
	
	return false;
}
