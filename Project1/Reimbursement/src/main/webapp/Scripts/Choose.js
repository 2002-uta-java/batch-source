function sendAjaxGet(url, func){
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if (this.readyState == 4 && this.status == 200){
			func(this);
		}
	}
	xhr.open("GET", url)
	xhr.send();
}
//Get the id from url
var str = ""+window.location.href; // retrieve current url
var index = str.search("id="); // find the id
console.log(str.slice(index+3));

var manButton = document.getElementById("manButton"); 
manButton.disabled = true;

// send get request to go to specific manager, if validation is correct
sendAjaxGet("http://localhost:8080/Reimbursement/manager?" + str.slice(index), enableButton); //get request with manager to callback function

function enableButton(xhr){ // boolean function to determine if the manager id is valid
	manager = JSON.parse(xhr.responseText);
	
	if(manager.id){
		manButton.disabled = false;
	}
}