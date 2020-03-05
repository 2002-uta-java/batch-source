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

 let url = "http://localhost:8080/Project1/employeeById";
employeeAjax(url, displayEmployee);
function employeeAjax(url, callback) {
     let cInput = emplIdx;
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

 function displayEmployee(xhr) {
     let o = JSON.parse(xhr.response);
     console.log(o);
      for (let jsonObj of o) {
        document.getElementById("name").innerHTML = `${jsonObj.firstName} ${jsonObj.lastName}`;
        document.getElementById("emailAddress").innerHTML = `${jsonObj.email}`;
        document.getElementById("title").innerHTML = `${jsonObj.title}`;
      }
 }

 let ew = document.getElementById("newPassword-btn")
 if (ew) {
     ew.addEventListener('click', newPassword, false);
 }

 function newPassword() {
     let url = "http://localhost:8080/Project1/clientupdate";

     newPasswordAjax(url, displayPassword);
 }

 function newPasswordAjax(url, callback) {
     let cInput = document.getElementById("newPassword-input").value;
     
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
     let requestBody = `clientPassword=${cInput}&clientId${clientId}`;
     console.log(requestBody);
     xhr.send(requestBody);
 }

 function displayPassword(xhr) {
     let responseMessage = JSON.parse(xhr.response);
     console.log(responseMessage);
 }

 let ew1 = document.getElementById("demo-btn")
 if (ew1) {
     ew1.addEventListener('click', update1, false);
 }

 function update1() {
     let url1 = "http://localhost:8080/Project1/employeeUpdateFname";
     let url2 = "http://localhost:8080/Project1/employeeUpdateLname";
     let url3 = "http://localhost:8080/Project1/employeeUpdateTitle";
     let all = document.getElementById("demo-input").value;
     switch (all) {
         case 'First Name':
             updateAjax(url1, displayUpdate);
             break;
         case 'Last Name':
             updateAjax(url2, displayUpdate);
             break;
         case 'Title':
             updateAjax(url3, displayUpdate);
             break;
         default:
             updateAjax(url1, displayUpdate);
     }
 }

  function updateAjax(url, callback) {
      let cInput = document.getElementById("info-input").value;
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
      let requestBody = `info=${cInput}&id=${emplIdx}`;
      console.log(requestBody);
      xhr.send(requestBody);
  }

  function displayUpdate(xhr) {
      let o = JSON.parse(xhr.response);
      console.log(o);
    }
  