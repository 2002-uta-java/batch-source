/**
 * 
 */

const habitatURL = "/fcdemo/api/habitats";

window.onload = function() {
	const xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200)
			displayHabitatOptions(xhr.responseText);
	}
	xhr.open("GET", habitatURL);
	xhr.send();
}

function displayHabitatOptions(habitatJson){
	const habitatSelect = document.getElementById("habitat-select");
	const habitats = JSON.parse(habitatJson);
	
	for(const habitat of habitats){
		const newOption = document.createElement("option");
		newOption.setAttribute("option", "" + habitat.id);
		newOption.innerText = habitat.name;
		habitatSelect.appendChild(newOption);
	}
}