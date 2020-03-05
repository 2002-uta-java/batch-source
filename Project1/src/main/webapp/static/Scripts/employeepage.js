let token = sessionStorage.getItem("token");
let headerInformation = sessionStorage.getItem("token");
var arr = headerInformation.split(":");
console.log(arr);
const clientId = arr[0];
const permission = arr[1];
const emplIdx = arr[2];
const managerIdx = arr[3];

let elb = document.getElementById("log-out")
if (elb) {
    elb.addEventListener('click', logout, false);
}

function logout() {
    sessionStorage.clear();
    window.location.href = "http://localhost:8080/Project1/index";
}

let baseurl = "http://localhost:8080/Project1/api/messageupdate";
sendAjaxGet1(baseurl, displayStatus1);

function sendAjaxGet1(url, callback) {
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

function displayStatus1(xhr) {
    let messages = JSON.parse(xhr.response);
    console.log(messages);
    let table = document.getElementById("dtBasicExample");
    for (let message of messages) {
        let newRow = document.createElement("tr");
        newRow.innerHTML = `<td>${message.remId}</td><td text-center>${message.remStatus}<td>${message.remNotes}</td><td>${message.remRequestDate}</td>`;
        table.appendChild(newRow);
    }
}
let elc = document.getElementById("create-btn")
if (elc) {
    elc.addEventListener('click', create1, false);
}

function create1() {
    let url = "http://localhost:8080/Project1/reimbursementnew";

    createAjax1(url, displayMessage1);
}

function createAjax1(url, callback) {
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
        } else {
            console.log("your stupid");
        }
    }
    xhr.send(createForm);
}

function displayMessage1(xhr) {
    let responseMessage = JSON.parse(xhr.response);
    console.log(responseMessage);
}



let ed = document.getElementById("search-btn")
if (ed) {
    ed.addEventListener('click', find1, false);
}

function find1() {
    let url = "http://localhost:8080/Project1/reimbursementid";
    findAjax1(url, displayRecord1);
}

function findAjax1(url, callback) {
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

function displayRecord1(xhr) {
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

let ele = document.getElementById("history-button")
if (ele) {
    ele.addEventListener('click', history1, false);
}

function history1() {
    sendAjaxGet1(baseurl, displayRecord1);
}