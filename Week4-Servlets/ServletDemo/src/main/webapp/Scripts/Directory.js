/**
 * 
 */

const requestUrl = "http://localhost:8080/ServletDemo/birds";

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

sendAjaxGet(requestUrl, displayBirds);


function displayBirds(birdJson){
	let birds = JSON.parse(birdJson);
	console.table(birds);
	
	let table = document.getElementById("bird-table");
	table.hidden = false;
	
	for(let bird of birds){
		let newRow = document.createElement("tr");
		
		newRow.innerHTML = `<td>${bird.name}</td><td>${bird.breed}</td><td>${bird.habitat.name}</td>`;
		table.appendChild(newRow);
		
	}
}
