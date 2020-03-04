function invalidAlert(){
    document.getElementById("email-label").innerText="Invalid credentials. Please try again.";
}

// login function
document.getElementById("index-login").addEventListener("click", login)

function login(){
    let email = document.getElementById("email").value
    let pass = document.getElementById("pass").value

    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8080/ExpenseReimbursement/login";
    
    console.log("Called login");

    xhr.open("POST", url);

    xhr.onreadystatechange = function(){
        if(xhr.readyState===4 && xhr.status===200){
            console.log("SUCCESS");
            let auth = xhr.getResponseHeader("Authorization");
            sessionStorage.setItem("token", auth);
            console.log(auth);
            let check = auth;
            //localStorage.setItem("loggedin", auth);

            // check if manager or employee
            // http://localhost:8080/ExpenseReimbursement/home
            if (isManager(check) === true){
                window.location.href ="http://localhost:8080/ExpenseReimbursement/static/views/manager.html";
            } else {
                window.location.href = "http://localhost:8080/ExpenseReimbursement/static/views/employee.html";
            }

        } else if (xhr.readyState == 4) {
            invalidAlert();
        }
    }

    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let requestBody = `email=${email}&user_pass=${pass}`;
    xhr.send(requestBody);
    console.log(`email:${email}$user_pass=${pass}`)
    
}

// checks if logged in user is manager or not
function isManager(check){
    let splitToken = check.split(":");
    if(splitToken[1] === "true"){
        return true;
    } else{
        return false;
    }
}



// function sendAjaxGet(url, callback){
//     let xhr = new XMLHttpRequest();
//     xhr.open("GET", url);
//     xhr.onreadystatechange = function(){
//         if(xhr.readyState==4 && xhr.status==200){
//             callback(xhr.response);
//         }
//     }
//     xhr.send();
// }







