/**
 * 
 */

document.getElementById("login-btn").addEventListener("click", requestLogin);

function requestLogin() {
    let user = document.getElementById("username").value;
    let pass = document.getElementById("password").value;

    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8080/ers/login";
    xhr.open("POST", url);

    xhr.onreadystatechange = function() {
        if (xhr.readyState == 4 && xhr.status == 200) {
            let auth = xhr.getResponseHeader("Authorization");
            sessionStorage.setItem("token", auth);
            window.location.href = "http://localhost:8080/ers/employeehome";
        } else if (xhr.readyState == 4) {
            console.log("Incorrect Credentials");
        }
    }

    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    let requestBody = `username=${user}&password=${pass}`;
    xhr.send(requestBody);
}