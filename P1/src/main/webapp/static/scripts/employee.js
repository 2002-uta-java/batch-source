// window.onload = function(){
//     if (!(sessionStorage.getItem("token"))){
//         logout1();
//     }
// }

let currentToken = sessionStorage.getItem("token");

// document.getElementById("submit-profile").addEventListener("click", submit);
// document.getElementById("update-profile").addEventListener("submit", postUpdateProfile);


// document.getElementById("submit-r").addEventListener("click", submitreq)
// document.getElementById("view-r").addEventListener("click")
// document.getElementById("view-u").addEventListener("click")
// document.getElementById("view-profile").addEventListener("click")

// function submitreq(){

// }

document.getElementById("logout1").addEventListener("click", logout1)

function logout1(){
    sessionStorage.removeItem("token");
    window.location.href ="http://localhost:8080/ExpenseReimbursement/static/views/index.html";
}


let coreUrl = "http://localhost:8080/ExpenseReimbursement/api/employee";

// ajax get request
function sendAjaxGet(url, callback){
    let xhr = new XMLHttpRequest();
    xhr.open("GET", url);
    xhr.onreadystatechange = function(){
        if(this===4 && this===200){
            callback(this);
        } else if (this===4){
            console.log("server error in ajax get")
        }
    }
    xhr.setRequestHeader("Authorization", token);
    xhr.send();
}

// let splitToken = check.split(":");
//     if(splitToken[1] === "true"){

function uniqueProfile(){
    let newUrl = coreUrl + "/" + (currentToken.split(":"))[0];
    sendAjaxGet(newUrl, showProfile);
}


function showProfile(xhr){
    let prof = JSON.parse(xhr.response);
    console.log(prof);
    document.getElementById("prof-email").value = prof.email;
    document.getElementById("prof-pass").value = prof.pass;
    document.getElementById("prof-fn").value = prof.firsname;
    document.getElementById("prof-ln").value = prof.lastname;
}


function postUpdateProfile(){
    console.log("updating profile...");

    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8080/ExpenseReimbursement/update";
    xhr.open("POST", url);

    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4 && CharacterData.status == 200){
            console.log("successfully updated user");
            window.location.href="http://localhost:8080/ExpenseReimbursement/employee";
        } else if (xhr.readyState == 4){
            console.log("invalid data");
        }
    }

    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlendcoded");
    let requestBody = `email=${email}&pass=${pass}&firstname=${firstname}&lastname=${lastname}`;
    xhr.send(requestBody);
}