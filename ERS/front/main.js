

let baseUrl = "http://api.weatherstack.com/current?access_key=93d3364c99aef5709d1caaabc64709b7&units=f&query=";

document.getElementById("submit-btn").addEventListener("click", searchWeather);

function searchWeather() {
    let zip_input = document.getElementById("zip-input").value;
    sendAjaxGet(baseUrl+zip_input, displayWeather);
}

function sendAjaxGet(url, callback) {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", url);
    xhr.onreadystatechange = function(){
      if(xhr.readyState == 4 && xhr.status == 200){
          callback(xhr.response);
      }
    };
    xhr.send();
}

function displayWeather() {

}