

document.getElementById("login").addEventListener("click", empLoginIn);

//storing the value from our dropdown
let empChoice = document.getElementById("workers").value;
//sending a post request for our employee name and pw
function empLoginIn() {
	let empChoice = document.getElementById("workers").value;
   const url = "http://localhost:8080/PJ1/login.html";

    let xhr = new XMLHttpRequest();
    xhr.open("POST", url);
    xhr.onreadystatechange = function () {
    	console.log("from js" + xhr);
        if (xhr.readyState === 4 && xhr.status === 200) {
          
            let auth = xhr.getResponseHeader("Authorization");
            sessionStorage.setItem("token", auth);
            console.log("from js if()" + xhr);
            //                        comparing the values in our dropdown to take the user to either the manger page or employee page
            if (empChoice.localeCompare("employee") === 0) {
                //            take them to the employee home page
                window.location.href = "http://localhost:8080/PJ1/employee";
            } else {
                //            take them to the manager home page
                window.location.href = "http://localhost:8080/PJ1/manager";
            }
            //            //400 bad request so we clear out our input
        } else if (xhr.status === 400) {

            document.getElementById("invalid").hidden = false;
            document.getElementById("name").value = "";
            document.getElementById("password1").value = "";
        }
    }
    //now we get our employee name and pw
    let user = document.getElementById("name").value;
    let pass = document.getElementById("password1").value;

    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    //sending name and password to our request body
    let reqBody = `name=${user}&password=${pass}`;

    xhr.send(reqBody);
}
