// ADDITIONAL EMPLOYEE INFORMATION IF MANAGER -----------------------------------------------------

// all employee info URL
const allEmpInfoRequestUrl = `http://localhost:8080/project_1/emp-info`;

// check if is_manager was set
if (tokenArray[1] == 1) {
    sendAjaxGet(allEmpInfoRequestUrl, displayAllEmpInfo);
}

// display all employees' information
function displayAllEmpInfo(empJason) {
    let employee = JSON.parse(empJason);
    console.table(employee);

    let table = document.getElementById("all-info-table");

    for (let emp of employee) {
        let newRow = document.createElement("tr");

        newRow.innerHTML = `<td>${emp.department}</td><td>${emp.firstName}</td><td>${emp.lastName}</td><td>${emp.email}</td>`;

        table.appendChild(newRow);

    }
}