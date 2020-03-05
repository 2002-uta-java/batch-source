let token = sessionStorage.getItem("token");
let headerInformation = sessionStorage.getItem("token");
var arr = headerInformation.split(":");
console.log(arr);
const clientId = arr[0];
const permission = arr[1];
const emplIdx = arr[2];
const managerIdx = arr[3];

let el7 = document.getElementById("log-out")
if (el7) {
    el7.addEventListener('click', logout, false);
}

function logout() {
    sessionStorage.clear();
    window.location.href = "http://localhost:8080/Project1/index";
}

let baseurl = "http://localhost:8080/Project1/api/messageupdate";
sendAjaxGet(baseurl, displayStatus);

function sendAjaxGet(url, callback) {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", url);
    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            callback(this);
        } else if (this.readyState === 4) {
            window.location.href = "http://localhost:8080/Project1/index";
        }
    }
    xhr.setRequestHeader("Authorization", token);
    xhr.send();
}

function displayStatus(xhr) {
    let messages = JSON.parse(xhr.response);
    console.log(messages);
    let table = document.getElementById("dtBasicExample");
    for(let message of messages) {
        let newRow = document.createElement("tr");
        newRow.innerHTML = `<td>${message.remId}</td><td text-center>${message.remStatus}<td>${message.remNotes}</td><td>${message.remRequestDate}</td>`;
        table.appendChild(newRow); }   
}
let el = document.getElementById("create-btn")
if (el) {
    el.addEventListener('click', create, false);
}

function create() {
    let url = "http://localhost:8080/Project1/reimbursementnew";

    createAjax(url, displayMessage);
}
function createAjax(url, callback) {
        let type = document.getElementById("type-input4").value;
        let ramount = document.getElementById("ramount-input4").value;
        let notes = document.getElementById("notes-input4").value;
        let rdate = document.getElementById("rdate-input4").value;
        console.log(type + ramount + notes);
        let createForm = JSON.stringify({
            "Rem": [{
                type: type,
                ramount: ramount,
                rdate: rdate,
                reciept: 'none',
                notes: notes,
                emplId: emplIdx,
                managerId: managerIdx,
                adate: 'pending',
                amount: '0',
                comment: 'pending',
                status: 'pending'
            }]
        });
        console.log(createForm);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", url);
    xhr.onreadystatechange = function () {
     	if (xhr.readyState == 4 && xhr.status == 200) {
             callback(xhr.response);
     	} else {console.log("your stupid");}
    }
    xhr.send(createForm);
    }
function displayMessage(xhr) {
    let responseMessage = JSON.parse(xhr.response);
    console.log(responseMessage);
}



let e2 = document.getElementById("search-btn")
if (e2) {
    e2.addEventListener('click', find, false);
}
function find() {
let url = "http://localhost:8080/Project1/reimbursementid";
findAjax(url,displayRecord);
}
 function findAjax(url, callback) {
     let rId = document.getElementById("rid-input").value;
     console.log(rId);
     let xhr = new XMLHttpRequest();
     xhr.open("POST", url);
     xhr.onreadystatechange = function () {
       if (this.readyState === 4 && this.status === 200) {
           callback(this);
       } else if (this.readyState === 4) {
           window.location.href = "http://localhost:8080/Project1/index";
       }
     }
     xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
     let requestBody = `rem=${rId}`;
     console.log(requestBody);
     xhr.send(requestBody);
 }

function displayRecord(xhr) {
    let o = JSON.parse(xhr.response);
    console.log(o);
    // var iterator = jsonObj.values();
    // for(let elements of iterator){
    // console.log(elements);
    // }
let table = document.getElementById("master");
let tableHead = document.createElement("thead");
let firstRow = document.createElement("tr");
firstRow.innerHTML = `<th scope = "col"> # </th><th scope = "col"> Type </th><th scope = "col"> Requested Amount </th><th scope = "col"> Date Requested </th>
<th scope = "col"> Submitted Documents </th><th scope = "col"> Requestor Notes </th><th scope = "col"> Requestor Id </th><th scope = "col"> Assigned Id </th>
<th scope = "col"> Date Completed </th><th scope = "col"> Approved Amount </th><th scope = "col"> Manager Notes </th><th scope = "col"> Status </th>`;
table.appendChild(tableHead);
tableHead.appendChild(firstRow);
    for(let jsonObj of o) {
    let newRow = document.createElement("tr");
     newRow.innerHTML = `<td>${jsonObj.remId}</td><td>${jsonObj.remType}</td><td>${jsonObj.remRequestedAmount}</td><td>${jsonObj.remRequestDate}</td>${jsonObj.remReciept}<td>${jsonObj.remNotes}</td><td>${jsonObj.empId}</td>
     <td>${jsonObj.managerId}</td><td>${jsonObj.remApprovedDate}</td ><td>${jsonObj.remApprovedAmount}</td><td>${jsonObj.remComment}</td><td>${jsonObj.remStatus}</td> `;
     table.appendChild(newRow);   }
}

let el3 = document.getElementById("history-button")
if (el3) {
    el3.addEventListener('click', history, false);
}

function history(){
    sendAjaxGet(baseurl, displayRecord);
}
// manager specific 
let el5 = document.getElementById("update-btn")
if (el5) {
    el5.addEventListener('click', update, false);
}

function update() {
    let url = "http://localhost:8080/Project1/reimbursementupdate";

    updateAjax(url, displayUpdate);
}

function updateAjax(url, callback) {
    let id = document.getElementById("id-input").value;
    let adate = document.getElementById("date-input").value;
    let amount = document.getElementById("amount-input").value;
    let comment = document.getElementById("comment-input").value;
    let status = document.getElementById("status-input").value;
    let createForm = JSON.stringify({
        "Rem": [{
            id: id,
            adate: adate,
            amount: amount,
            comment: comment,
            status: status,
        }]
    });


    let xhr = new XMLHttpRequest();
    xhr.open("POST", url);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            callback(xhr.response);
        } else {
            console.log("your stupid");
        }
    }
    xhr.send(createForm);
}

function displayUpdate(xhr) {
    let responseMessage = JSON.parse(xhr.response);
    console.log(responseMessage);
}

let e8 = document.getElementById("report-btn")
if (e8) {
    e8.addEventListener('click', report, false);
}

function report() {
    let url1 = "http://localhost:8080/Project1/api/employeeall";
    let url2 = "http://localhost:8080/Project1/api/reimbursementall";
    let url3 = "http://localhost:8080/Project1/employeeById";
    let url4 = "http://localhost:8080/Project1/reimbursementByType";
    let all = document.getElementById("report-input").value;
    switch(all) {
        case 'All Reimbursements':
            reportAjax1(url2, displayAll);
            break;
        case 'All Assigned Employees':
             reportAjax1(url1, displayAll2);
             break;
        case 'Employee By Id':
              reportAjax2(url3, displayAll2);
              break;
        case 'Reimbursement By Type':
              reportAjax2(url4, displayAll);
              break;
        default: reportAjax1(url1, displayAll2);
    }
}

function reportAjax1(url, callback) {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", url);
    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            callback(this);
        } else if (this.readyState === 4) {
            window.location.href = "http://localhost:8080/Project1/index";
        }
    }
    xhr.send();
}

 function reportAjax2(url, callback) {
     let cInput = document.getElementById("c-input").value;
     let xhr = new XMLHttpRequest();
     xhr.open("POST", url);
     xhr.onreadystatechange = function () {
         if (this.readyState === 4 && this.status === 200) {
             callback(this);
         } else if (this.readyState === 4) {
             window.location.href = "http://localhost:8080/Project1/index";
         }
     }
     xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
     let requestBody = `id=${cInput}`;
     console.log(requestBody);
     xhr.send(requestBody);
 }

function displayAll(xhr) {
    let o = JSON.parse(xhr.response);
    console.log(o);
    // var iterator = jsonObj.values();
    // for(let elements of iterator){
    // console.log(elements);
    // }
    let table = document.getElementById("master");
    let tableHead = document.createElement("thead");
    let firstRow = document.createElement("tr");
    firstRow.innerHTML = `<th scope = "col"> # </th><th scope = "col"> Type </th><th scope = "col"> Requested Amount </th><th scope = "col"> Date Requested </th>
<th scope = "col"> Submitted Documents </th><th scope = "col"> Requestor Notes </th><th scope = "col"> Requestor Id </th><th scope = "col"> Assigned Id </th>
<th scope = "col"> Date Completed </th><th scope = "col"> Approved Amount </th><th scope = "col"> Manager Notes </th><th scope = "col"> Status </th>`;
    table.appendChild(tableHead);
    tableHead.appendChild(firstRow);
    for (let jsonObj of o) {
        let newRow = document.createElement("tr");
        newRow.innerHTML = `<td>${jsonObj.remId}</td><td>${jsonObj.remType}</td><td>${jsonObj.remRequestedAmount}</td><td>${jsonObj.remRequestDate}</td>${jsonObj.remReciept}<td>${jsonObj.remNotes}</td><td>${jsonObj.empId}</td>
     <td>${jsonObj.managerId}</td><td>${jsonObj.remApprovedDate}</td ><td>${jsonObj.remApprovedAmount}</td><td>${jsonObj.remComment}</td><td>${jsonObj.remStatus}</td> `;
        table.appendChild(newRow);
    }
}

function displayAll2(xhr) {
    let o = JSON.parse(xhr.response);
    console.log(o);
    // var iterator = jsonObj.values();
    // for(let elements of iterator){
    // console.log(elements);
    // }
    let table = document.getElementById("master");
    let tableHead = document.createElement("thead");
    let firstRow = document.createElement("tr");
    firstRow.innerHTML = `<th scope = "col"> # </th><th scope = "col"> First Name </th><th scope = "col"> Last Name </th><th scope = "col"> Email </th>
<th scope = "col"> Title </th>`;
    table.appendChild(tableHead);
    tableHead.appendChild(firstRow);
    for (let jsonObj of o) {
        let newRow = document.createElement("tr");
        newRow.innerHTML = `<td>${jsonObj.employeeId}</td><td>${jsonObj.firstName}</td><td>${jsonObj.lastName}</td><td>${jsonObj.email}</td><td>${jsonObj.title}</td>`;
        table.appendChild(newRow);
    }
}