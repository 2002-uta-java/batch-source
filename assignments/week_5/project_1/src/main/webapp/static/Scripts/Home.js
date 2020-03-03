// Token ------------------------------------------------------------------------------------------

// get token from session storage and split in two [id, isManager flag]
let tokenArray = sessionStorage.token.split(':',2);
console.log(tokenArray);


// AJAX GET FUNCTION ------------------------------------------------------------------------------
function sendAjaxGet(url, callback){
    let xhr = new XMLHttpRequest();
    xhr.open("GET", url);
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4 && xhr.status == 200) {
            callback(xhr.response);
        }
    }

    xhr.send();
}