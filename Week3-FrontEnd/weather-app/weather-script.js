
let baseUrl = "http://api.weatherstack.com/current?access_key=93d3364c99aef5709d1caaabc64709b7&units=f&query=";

document.getElementById("submit-btn").addEventListener("click", searchWeather);

function searchWeather(){
    // obtain user input and invoke the sendAjaxGet with that url + a callback which displays important weather info
    let zipInput = document.getElementById("zip-input").value;
    sendAjaxGet(baseUrl+zipInput, displayWeather);
    
}

function sendAjaxGet(url, callback){
    let xhr = new XMLHttpRequest();
    xhr.open("GET", url);
    xhr.onreadystatechange = function(){
        if(xhr.readyState==4 && xhr.status==200){
            callback(xhr.response);
        }
    }
    xhr.send();
}

function displayWeather(response){
    // console.log(response);
    let weatherInfo = JSON.parse(response);
    document.getElementById("result").hidden = false;
    document.getElementById("location").innerText = `Weather for ${weatherInfo.location.name}, ${weatherInfo.location.region}`;
    document.getElementById("condition").innerText = weatherInfo.current.weather_descriptions[0];
    document.getElementById("icon").src = weatherInfo.current.weather_icons[0];
    document.getElementById("temp").innerText = weatherInfo.current.temperature + "° F";

}

