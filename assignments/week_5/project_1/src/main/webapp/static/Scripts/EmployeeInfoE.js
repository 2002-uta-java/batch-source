// EMPLOYEE INFORMATION ---------------------------------------------------------------------------

// single employee info URL
const singleEmpInfoRequestUrl = `http://localhost:8080/project_1/emp-info?empId=${tokenArray[0]}`;

// function call
sendAjaxGet(singleEmpInfoRequestUrl, populateEmpInfo);

// adding event listner to update employee form button
document.getElementById("update-emp-button").addEventListener("click", updateEmpInfo);

// populate information for single employee
function populateEmpInfo(empJason) {
    let employee = JSON.parse(empJason);

    let deptLabel = document.getElementById("dept-label");
    let fNameLabel = document.getElementById("f-name-label");
    let emailField = document.getElementById("email-field");
    let phoneField = document.getElementById("phone-field");

    let loggedInAs = document.getElementById("logged-user");

    for (let emp of employee) {
        loggedInAs.innerText = `logged in as: ${emp.firstName} ${emp.lastName}`
        deptLabel.innerText = `Department: ${emp.department}`;
        fNameLabel.innerText = `Name: ${emp.firstName} ${emp.lastName}`;
        emailField.value = emp.email;
        phoneField.value = emp.phone;
    }
}

// if the submit button is clicked in the employee info form, update the form information
function updateEmpInfo() {

    let tokenArray = sessionStorage.token.split(':',2);

    let currentUser = tokenArray[0];

    let phoneInput = document.getElementById("phone-field").value;
    let emailInput = document.getElementById("email-field").value;

    let url = `http://localhost:8080/project_1/emp-info?employee=${currentUser}&phone=${phoneInput}&email=${emailInput}`;

    let xhr = new XMLHttpRequest();
    xhr.open("PUT", url);

    xhr.onreadystatechange = function() {
		if(xhr.readyState == 4 && xhr.status == 200) {
            console.log("we got inside the 4 && 200 !!!");
            window.location.href="http://localhost:8080/project_1/home";
		}
		
		else if (xhr.readyState == 4) {
			console.log("Mistakes were made...");
		}
	}
	xhr.send();
}


// if the submit button is clicked in the employee info form, update the form information
//function updateEmpInfo() {

//    let tokenArray = sessionStorage.token.split(':',2);

//    let currentUser = tokenArray[0];

//    let phoneInput = document.getElementById("phone-field").value;
//    let emailInput = document.getElementById("email-field").value;

//    let url = `http://localhost:8080/project_1/emp-info`;

//    let xhr = new XMLHttpRequest();
//    xhr.open("POST", url);

//    xhr.onreadystatechange = function() {
//		if(xhr.readyState == 4 && xhr.status == 200) {
//			console.log("we got inside the 4 && 200 !!!");
//		}
		
//		else if (xhr.readyState == 4) {
//			console.log("Mistakes were made...");
//		}
//	}
	
//	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
//	let requestBody = `employee=${currentUser}&phone=${phoneInput}&email=${emailInput}`;
//	xhr.send(requestBody);
//}

// sendAjaxGet(singleEmpInfoRequestUrl, displayEmpInfo);

//function displayEmpInfo(empJason) {
//    let employee = JSON.parse(empJason);
//    console.table(employee);
//
//    let table = document.getElementById("my-info-table");
//
//    for (let emp of employee) {
//        let newRow = document.createElement("tr");
//
//        newRow.innerHTML = `<td>${emp.department}</td><td>${emp.firstName}</td><td>${emp.lastName}</td><td>${emp.email}</td>`;
//
//        table.appendChild(newRow);
//
//    }
//}