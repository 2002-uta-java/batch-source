



document.getElementById("index-login").addEventListener("click", login)

let login = function(){
    let uname = document.getElementById("uname").value
    let pass = document.getElementById("pass").value

    let xhr = new XMLHttpRequest();
    let url = "http://localhost:8080/ExpenseReimbursement/login";

    xhr.open("POST", url);

    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){
            console.log("success!!!!!!!!!!");
            let auth = xhr.getResponseHeader("Authorization");
            sessionStorage.setItem("token", auth);
            console.log(auth);
            window.location.href="http://localhost:8080/ExpenseReimbursement/home";
        } else if (xhr.readyState == 4) {
            alert("Invalid Credentials, please try again.");
        }
    }

    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	let requestBody = `uname=${uname}&pass=${pass}`;
    xhr.send(requestBody);
    
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







