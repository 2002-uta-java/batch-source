/**
 *  Login
 */
//validation delegate url
//let url = "http://localhost:8080/ERS/api/validate";

//redirect to home page if validation was successful
let callback = (userJson) =>{
	//set the token in session storage
	sessionStorage.setItem("token", userJson);
	//redirect to home page
	window.location = "http://localhost:8080/ERS/employeehome";
}

//attempt to log in if button is clicked
let login = () =>{
	console.log("login");
	let user = document.getElementById("username").value;
	let pass = document.getElementById("password").value;
	let xhr = new XMLHttpRequest();
	
	//callback function to check if validation was successful
	xhr.onreadystatechange = () =>{
		if(xhr.readyState == 4 && xhr.status == 200){
			//gets Authorization header from response (from the Authorization delegate)
			let auth = xhr.getResponseHeader("Authorization");
			sessionStorage.setItem("token", auth);			
			//checks if Authorization header is not empty (i.e. success)
			if(auth.length !== 0){
				//Login was successful, use callback
				callback(auth);
			}
		}
	}
	let url = `http://localhost:8080/ERS/api/validate?username=${user}&password=${pass}`;
	//add (username) and (password) parameters to url
//	url += `?username=${user}&password=${pass}`;
	//use GET request with validation delegate url
	xhr.open("POST", url);
	//send request
	xhr.send();
	return false;
}



//attempt to log in if button is clicked
let register = () =>{
	window.location = "http://localhost:8080/ERS/register";
	return false;
}


//add click event listener if sign in button is clicked
document.getElementById("login-btn").addEventListener("click", login);
document.getElementById("register-btn").addEventListener("click", register);





/**
 * 
 */

