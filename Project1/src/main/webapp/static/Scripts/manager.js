let token = sessionStorage.getItem("token");
let headerInformation = sessionStorage.getItem("token");
var arr = headerInformation.split(":");
console.log(arr);
const clientId = arr[0];
const permission = arr[1];
const emplIdx = arr[2];
const managerIdx = arr[3];

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
        newRow.innerHTML = `<td>${message.remId}</td>${message.remStatus}<td>${message.remNotes}</td><td>${message.remRequestDate}</td>`;
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
        console.log(type + ramount + notes);
        let createForm = JSON.stringify({
            "Rem": [{
                type: type,
                ramount: ramount,
                rdate: '20200302',
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
             callback();
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
    e2.addEventListener('click', getRemAjax, false);
}

let url = "http://localhost:8080/Project1/api/reimbursementid";
let callback = displayRem;

function getRemAjax(url, callback) {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", url);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            callback(xhr.response);
        }
    }
    let rId = document.getElementById("rid-input").value;
    let rIdRequest= `remId = ${rId}`;
    xhr.send(rIdRequest);
}


function displayRem(remJson) {
    let rem = JSON.parse(remJson);
    console.log(rem);
    let table = document.getElementById("master-table");
    let newRow = document.createElement("tr");
    newRow.innerHTML = `<td>${rem.remId}</td><td>${rem.remType}</td><td>${rem.remRequestedDate}</td>`;
    table.appendChild(newRow);
}