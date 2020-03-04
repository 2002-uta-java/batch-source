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

    let accordionEmployee = document.getElementById("accordionAllEmployees");

    for (let emp of employee) {

        let newDiv = document.createElement("div");

        newDiv.className = "card";

        newDiv.innerHTML = `
            <div class="card-header" id="heading${emp.id}">
                <h2 class="mb-0">
                    <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapse${emp.id}" aria-expanded="false" aria-controls="collapse${emp.id}">
                        <table class="table table-borderless" id="no-space">
                            <tbody>
                                <tr>
                                    <td id="no-space"><strong>${emp.firstName} ${emp.lastName}</strong></td>
                                    <td id="no-space"><strong>${emp.department}</strong></td>
                                    <td id="no-space"><strong>email:</strong> ${emp.email}</td>
                                    <td id="no-space"><strong>address:</strong> ${emp.address}</td>
                                </tr>
                            </tbody>
                        </table>
                    </button>
                </h2>
            </div>
            <div id="collapse${emp.id}" class="collapse" aria-labelledby="heading${emp.id}" data-parent="#accordionAllEmployees">
                <div class="card-body">
                    Reims Here
                </div>
            </div>`;

        accordionEmployee.appendChild(newDiv);
    }
}


//         // display all employees' information
// function displayAllEmpInfo(empJason) {
//     let employee = JSON.parse(empJason);
//     console.table(employee);

//     let table = document.getElementById("all-info-table");
//     let accordionPending = document.getElementById("accordionPendingReimbursements");

//     for (let emp of employee) {
//         let newRow = document.createElement("tr");

//         newRow.innerHTML = `<td>${emp.department}</td><td>${emp.firstName}</td><td>${emp.lastName}</td><td>${emp.email}</td>`;

//         table.appendChild(newRow);

//     }
// }