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

// Get id from url
var str = ""+window.location.href;
var index = str.search("id=");
console.log(str.slice(index+3));

// static functions to get a callback function based off of endpoint
function viewProfile(){ 
	sendAjaxGet("http://localhost:8080/Reimbursement/profile?" + str.slice(index), displayProfile);
}

function viewPending(){
	sendAjaxGet("http://localhost:8080/Reimbursement/reimbursement?" + str.slice(index), displayPending);
}

function viewResolved(){
	sendAjaxGet("http://localhost:8080/Reimbursement/reimbursement?" + str.slice(index), displayResolved);
}

// Display single profile
function displayProfile(xhr){
	profile = JSON.parse(xhr.responseText);
	let table = document.getElementById("profTable");
	let newRow = document.createElement("tr");
	
	while(table.childElementCount > 1){
		table.removeChild(table.lastChild);
	}
	
	name = `${profile.firstname} ${profile.lastname}`;
	
	loc = "Dallas, TX";
	
	phone = `${profile.phone}`;
	
	email = `${profile.email}`;
	
	newRow.innerHTML = `<td>${name}</td>
	<td>${loc}</td>
	<td>${phone}</td>
	<td>${email}</td>`;
	
	table.appendChild(newRow);

}

// Display all profiles
function displayProfiles(xhr){
	profiles = JSON.parse(xhr.responseText);	
	let table = document.getElementById("profTable");
	profileArr = profiles.profiles;
	
	while(table.childElementCount > 1){
		table.removeChild(table.lastChild);
	}
	
	for(i in profileArr){
		let newRow = document.createElement("tr");
		
		name = `${profileArr[i].firstname} ${profileArr[i].lastname}`;
		
		loc = `$Dallas, TX`;
		
		phone = `${profileArr[i].phone}`;
		
		email = `${profileArr[i].email}`;
		
		newRow.innerHTML = `<td>${name}</td>
		<td>${loc}</td>
		<td>${phone}</td>
		<td>${email}</td>`;
		
		table.appendChild(newRow);
	}
}

//View pending requests
function displayPending(xhr){
	reimbs = JSON.parse(xhr.responseText);	
	let table = document.getElementById("reimbTable");
	reimbArr = reimbs.reimbursement;
	
	while(table.childElementCount > 1){
		table.removeChild(table.lastChild);
	}
	
	for(i in reimbArr){
		let newRow = document.createElement("tr");
		
		id = `${reimbArr[i].rid}`;
		
		status = `${reimbArr[i].status}`;
		
		descript = `${reimbArr[i].description}`;
		
		if(reimbArr[i].action){
			action = `${reimbArr[i].action}`;
		} else {
			action = "n/a";
		}
		
		newRow.innerHTML = `<td>${id}</td>
		<td>${status}</td>
		<td>${descript}</td>
		<td>${action}</td>`;
		
		// Append if pending
		if(status == "PENDING"){
			table.appendChild(newRow);
		}
	}
}

//View resolved requests
function displayResolved(xhr){
	reimbs = JSON.parse(xhr.responseText);	
	let table = document.getElementById("reimbTable");
	reimbArr = reimbs.reimbursement;
	
	while(table.childElementCount > 1){
		table.removeChild(table.lastChild);
	}
	
	for(i in reimbArr){
		let newRow = document.createElement("tr");
		
		id = `${reimbArr[i].rid}`;
		
		status = `${reimbArr[i].status}`;
		
		descript = `${reimbArr[i].description}`;
		
		if(reimbArr[i].action){
			action = `${reimbArr[i].action}`;
		} else {
			action = "n/a";
		}
		
		newRow.innerHTML = `<td>${id}</td>
		<td>${status}</td>
		<td>${descript}</td>
		<td>${action}</td>`;
		
		// Append if pending
		if(status == "RESOLVED"){
			table.appendChild(newRow);
		}
	}
}