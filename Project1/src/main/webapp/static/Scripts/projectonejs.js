// this effects password n indexpage
function showPassword() {
    var x = document.getElementById("password-input");
    if (x.type === "password") {
      x.type = "text";
    } else {
      x.type = "password";
    }
  }
// this vaildates correct email is inputted
const form  = document.getElementsById("f1")[0];
const email = document.getElementById("email-input");
const emailError = document.querySelector('#email-input + span.error');
email.addEventListener('input', function (event) {
    if (email.validity.valid) {
      emailError.innerHTML = 'Enter a valid email address'; 
      emailError.className = 'error'; 
    } else {
      showError();
    }
  });
// This displays error message for incorrect email
  function showError() {
    if(email.validity.valueMissing) {
      emailError.textContent = 'Continue to input email';
    } else if(email.validity.typeMismatch) {
      emailError.textContent = 'Enter a valid email address.';
    } else if(email.validity.tooShort) {
      emailError.textContent = `Email should be at least ${ email.minLength } characters; you entered ${ email.value.length }`;
        }
    }
// This post the email and password to server to verify with database
  let baseUrl = "";
  document.getElementById("submit-btn1").addEventListener("click", logOn);
  function logOn(){
      let emailInput = document.getElementById("email-input").value;
      let passwordInput = document.getElementById("password-input").value;
      let data = (emailInput+passwordInput);
      let dataString = JSON.stringify(data);
      sendAjaxPost(baseUrl+dataString, result);
  }
// Post request with AJAX
  function sendAjaxPost(url, callback) {
      let xhr = new HttpRequest();
      xhr.open("POST", url);
      xhr.onreadystatechange = function () {
      if(xhr.onreadyState==4 && xhr.status==201){
          callback(xhr.response);
      }
  }
  xhr.send();
  }
// Response Boolean for validation password and email
  function result(response) {
    let resultInfo = JSON.parse(response);
    if(resultInfo === true) {
        let emailInput = JSON.stringify("email-input").value;
        sendAjaxGet(baseurl+emailInput, result);
    } else {
        alert("Credentials unable to be verfied");
    }
  }
// Get request for permission, empl_id
  function sendAjaxGet(url, callback) {
      let xhr = new HttpRequest();
      xhr.open("GET", url);
      xhr.onreadystatechange = function () {
      if(xhr.onreadyState==4 && xhr.status==200){
          callback(xhr.response);
      }
  }
  xhr.send();
  }
// Response of permission will determine direction - - 
  var id;
  function result(response) {
      let resultBack = JSON.parse(response);
      let p = resultBack[0]; id = resultBack[1];
      if(p === 0) {
          window.location.href = "C:\Spring\practice\Project1\employeepage.html";
      } else {
          window.location.href = "C:\Spring\practice\Project1\managerpage.html";
      }
  }
//-----------------------------------------------------------------------------------//
//Employee JS
