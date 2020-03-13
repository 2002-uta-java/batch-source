/**
 * 
 */

const requestURL = "/fcdemo/api/birds";

sendAjaxGet(requestURL, displayBirds);

function sendAjaxGet(url, callback){
	let xhr = new XMLHttpRequest();
	xhr.open("GET", url);
	xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200){
			callback(xhr.response);
		}
	}
	xhr.send();
}

function displayBirds(birdJson){
	// parse the JSON into an array of bird objects
	let birds = JSON.parse(birdJson);
	console.table(birds);
	
	let table = document.getElementById("bird-table");
	table.hidden = false;
	
	// iterate through array of birds
	for(let bird of birds){
		let newRow = document.createElement("tr");
		
		// maybe should use innerText but hopefully database doesn't hold bad
		// text
		newRow.innerHTML = `<td>${bird.name}</td><td>${bird.breed}</td><td>${bird.habitat.name}</td>`;
		table.appendChild(newRow);
	}
}