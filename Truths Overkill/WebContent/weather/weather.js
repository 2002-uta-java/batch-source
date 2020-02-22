/**
 * 
 */
const BASE_WEATHER_URL = "http://api.weatherstack.com/current?access_key=93d3364c99aef5709d1caaabc64709b7&units=f&query=";

window.onload = function() {
	console.log("window loaded");
	document.getElementById("submit-btn").addEventListener("click",
			searchWeather);
}

function searchWeather() {
	console.log("button clicked");
	// obtain user input and send Ajax GET with URL + callback that displays
	// important weather info
	const zipInput = document.getElementById("zip-input").value;
	sendAjaxGet(BASE_WEATHER_URL + zipInput, displayWeather);
}

function sendAjaxGet(url, callback) {
	console.log(`calling xhr(${url} with ${callback})`);
	const xhr = new XMLHttpRequest();

	xhr.open("GET", url);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			callback(xhr.response);
		}
	}
	xhr.send();
	console.log(xhr);
}

function displayWeather(response) {
	const weatherInfo = JSON.parse(response);
	console.log(weatherInfo);
//	document.getElementById("results").hidden = "false";
	document.getElementById("location").innerText = `Weather for ${weatherInfo.location.name}, ${weatherInfo.location.region}`;
	document.getElementById("condition").innerText = weatherInfo.current.weather_descriptions[0];
	document.getElementById("icon").src = weatherInfo.current.weather_icons[0];
	document.getElementById("temp").innerText = weatherInfo.current.temperature;
}