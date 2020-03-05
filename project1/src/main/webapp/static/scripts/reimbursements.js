const requestUrl = "http://localhost:8080/ERS/R";

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

sendAjaxGet(requestUrl, displayReimbursements);


function displayReimbursements(reimbursementJSON){
	let username = sessionStorage.getItem("username");
	let reimbursements = JSON.parse(reimbursementJSON);
	console.table(reimbursements);
	
	let table = document.getElementById("reimbursements");
	table.hidden = false;
	
	let urlParams = new URLSearchParams(window.location.search);
	let limit = urlParams.get('r');
	
	for(let r of reimbursements){
		if(username == r.user.username) {
			if((limit == 'r' && r.status != "pending") ||
					(limit == 'p' && r.status == "pending")) {
			let newRow = document.createElement("tr");
		
			newRow.innerHTML = `<td>${r.description}</td><td>${r.amount}</td><td>${r.status}</td>`;
			table.appendChild(newRow);
			}
		}
		
	}
}
