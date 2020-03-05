window.onload = function(){
    if (!(sessionStorage.getItem("token"))){
        logout2();
    }
}

document.getElementById("logout2").addEventListener("click", logout2)

function logout2(){
    sessionStorage.removeItem("token");
    window.location.href ="http://localhost:8080/ExpenseReimbursement/static/views/index.html";
}
