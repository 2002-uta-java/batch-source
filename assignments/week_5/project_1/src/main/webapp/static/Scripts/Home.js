// Token ------------------------------------------------------------------------------------------

// get token from session storage and split in two [id, isManager flag]
let tokenArray = sessionStorage.token.split(':',2);
console.log(tokenArray);


// AJAX GET FUNCTION ------------------------------------------------------------------------------
function sendAjaxGet(url, callback){
    let xhr = new XMLHttpRequest();
    xhr.open("GET", url);
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4 && xhr.status == 200) {
            callback(xhr.response);
        }
    }

    xhr.send();
}

window.onload = callMe();

function callMe() {

let navBar = document.getElementById("v-pills-tab");
let contentDiv = document.getElementById("v-pill-tabContent");


    if (tokenArray[1] == 1) {
        
        console.log("did you call?");

        let allReimA = document.createElement("a");
        let allEmpsA = document.createElement("a");



        allReimA.className = "nav-link";
        allReimA.id = "v-pills-all-reimbursements-tab";
        allReimA.href = "#v-pills-all-reimbursements";
        allReimA.role = "tab";
        allReimA.setAttribute("data-toggle", "pill");
        allReimA.setAttribute("aria-controls", "v-pills-all-reimbursements");
        allReimA.setAttribute("aria-selected", "false");

        allReimA.innerText = "All Reimbursements";

        allEmpsA.className = "nav-link";
        allEmpsA.id = "v-pills-all-employees-tab";
        allEmpsA.href = "#v-pills-all-employees";
        allEmpsA.role = "tab";
        allEmpsA.setAttribute("data-toggle", "pill");
        allEmpsA.setAttribute("aria-controls", "v-pills-all-employees");
        allEmpsA.setAttribute("aria-selected", "false");

        allEmpsA.innerText = "All Employees";

        navBar.appendChild(allReimA);
        navBar.appendChild(allEmpsA);

        let allReimDiv = document.createElement("div");
        let allEmpsDiv = document.createElement("div");

        allReimDiv.className = "row tab-pane fade";
        allReimDiv.id = "v-pills-all-reimbursements";
        allReimDiv.role = "tabpanel";
        allReimDiv.setAttribute("aria-labelledby", "w-pills-all-reimbursements-tab");

        allEmpsDiv.className = "row tab-pane fade";
        allEmpsDiv.id = "v-pills-all-employees";
        allEmpsDiv.role = "tabpanel";
        allEmpsDiv.setAttribute("aria-labelledby", "w-pills-all-employees-tab");

        allReimDiv.innerHTML = `
            <div class="col-10">
                <div class="card">
                    <div class="card-header">
                        <ul class="nav nav-tabs card-header-tabs" id="myTab" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" id="all-pending-tab" data-toggle="tab" href="#all-pending" role="tab" aria-controls="all-pending" aria-selected="true">Pending</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="all-processed-tab" data-toggle="tab" href="#all-processed" role="tab" aria-controls="all-processed" aria-selected="false">Processed</a>
                            </li>
                        </ul>
                    </div>
                    <div class="tab-content" id="myTabContent">
                        <div class="tab-pane fade show active" id="all-pending" role="tabpanel" aria-labelledby="all-reimburesements-tab">
                            <div class="container">
                                <br>
                                <h5 class="card-title">All Reimbursements Card</h5>
                                <h6 class="card-subtitle mb-2 text-muted">please fill out the following information:</h6>
                                <div class="accordion" id="accordionAllPendingReimbursements">
                                </div>
                                <br>
                            </div>
                        </div>
                        <div class="tab-pane fade" id="all-processed" role="tabpanel" aria-labelledby="all-processed-tab">
                            <div class="container">
                                <br>
                                <h5 class="card-title">Employees' processed reimbursements:</h5>
                                <h6 class="card-subtitle mb-2 text-muted">click on one to see more details...</h6>
                                <div class="accordion" id="accordionAllProcessedReimbursements">
                                </div>
                                <br>
                            </div>
                        </div>
                    </div>
                </div>    
            </div>`

            allEmpsDiv.innerHTML = `
            <div class="col-10">
                <div class="card">
                    <div class="card-header">
                        <ul class="nav nav-tabs card-header-tabs" id="myTab" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" id="all-emp-tab" data-toggle="tab" href="#all-employee" role="tab" aria-controls="all-employee" aria-selected="true">Employees</a>
                            </li>
                        </ul>
                    </div>
                    <div class="tab-content" id="myTabContent">
                        <div class="tab-pane fade show active" id="all-employee" role="tabpanel" aria-labelledby="all-employee-tab">
                            <div class="container">
                                <br>
                                <h5 class="card-title">All Employees Card</h5>
                                <h6 class="card-subtitle mb-2 text-muted">click an employee to see their reimburesements:</h6>
                                <div class="accordion" id="accordionAllEmployees">
                                </div>
                                <br>
                            </div>
                        </div>
                    </div>
                </div>    
            </div>`

        contentDiv.appendChild(allReimDiv);
        contentDiv.appendChild(allEmpsDiv);
    }    
}