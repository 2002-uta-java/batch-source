/**
 * 
 */

window.onload = function() {
	setupNav();

	const xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				displayReimbs(xhr);
			} else {
				displayError();
			}
		}
	};
	xhr.open("GET", ALL_EMP_EXCEPT_ME);
	xhr.send();
}

function displayReimbs(xhr) {
	console.log(xhr.responseText);
	const employees = JSON.parse(xhr.responseText);
	const tbody = document.getElementById("employee-table-body");
	
	for(const employee of employees){
		const tr = getTr(employee);
		tbody.appendChild(tr);
	}
}

function getTr(employee){
	const tr = document.createElement("tr");
	
	// create and add the elements in order
	let td = document.createElement("td");
	td.innerText = employee[EMPL_FIRSTNAME] + " " + employee[EMPL_LASTNAME];
	tr.appendChild(td);

	td = document.createElement("td");
	td.innerText = employee[EMPL_EMAIL];
	tr.appendChild(td);

	td = document.createElement("td");
	td.innerText = employee[EMPL_ISMANAGER] ? "yes" : "no";
	tr.appendChild(td);
	
	return tr;
}