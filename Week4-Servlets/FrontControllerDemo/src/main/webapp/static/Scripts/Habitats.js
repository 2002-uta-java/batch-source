/**
 * 
 */

const requestUrl = "http://localhost:8080/FrontControllerDemo/api/habitats";

function sendAjaxGet(url, callback){
	let xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4 && xhr.status==200){
			callback(xhr.response);
		}
	}
	xhr.send();
}

sendAjaxGet(requestUrl, displayHabitatOptions);

function displayHabitatOptions(habitatJson){
	let habitatSelect = document.getElementById("habitat-select");
	let habitats = JSON.parse(habitatJson);
	for(let habitat of habitats){
		let newOption = document.createElement("option");
		newOption.value = habitat.id;
		newOption.innerText = habitat.name;
		habitatSelect.appendChild(newOption);
	}
}