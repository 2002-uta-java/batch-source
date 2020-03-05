let register = () =>{
	let url = "http://localhost:8080/project1/register";
	window.location = url;
}

//redirect to home page if validation was successful
let callback = (userJson) =>{
	//set the token in session storage
	sessionStorage.setItem("token", userJson);
	//redirect to home page
	window.location = "http://localhost:8080/project1/home";
}

//attempt to log in if button is clicked
let login = () =>{
	let user = document.getElementById("user").value;
	let pass = document.getElementById("pass").value;
	let xhr = new XMLHttpRequest();
	//callback function to check if validation was successful
	xhr.onreadystatechange = () =>{
		if(xhr.readyState == 4){
			//Login was successful, use callback
			if(xhr.status == 200){
				//gets Authorization header from response (from the Authorization delegate)
				let auth = xhr.getResponseHeader("Authorization");
				callback(auth);
			} else{
				//login was not successful
				alert("User details are incorrect.");
			}
		}
	}
	//validation delegate url
	let url = "http://localhost:8080/project1/api/validate";
	//add (username) and (password) parameters to url
	url += `?user=${user}&pass=${pass}`;
	//use GET request with validation delegate url
	xhr.open("GET", url);
	//send request
	xhr.send();
}

//add click event listener if sign in button is clicked
document.getElementById("btn-login").addEventListener("click", login);



