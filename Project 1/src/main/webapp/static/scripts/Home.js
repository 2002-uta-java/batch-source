//check if validation was successful by checking token
if(sessionStorage.getItem("token") !== null){
	//gets the title section from the homepage
	let title = document.getElementById("title");
	//create an h3 element to add to title section
	let welcome = document.createElement("h3");
	//parse json data from session storage
	let data = JSON.parse(sessionStorage.getItem("token"));
	//message to output
	let msg = `Welcome, ${data.name}.`;
	//if the user is a manager then append to message
	if(data.role === "manager"){
		msg = "[manager] " + msg;
	}
	//set the content of the h3 element to the user's name
	welcome.innerHTML = msg;
	//add element to title section
	title.appendChild(welcome);
} else {
	window.location = "http://localhost:8080/project1/login";
}