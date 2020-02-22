/**
 * 
 */

const BASE_URL = "https://newton.now.sh/";

window.onload = function() {
	document.getElementById("submit-btn").addEventListener("click", submit);
}

function sendAjaxMath(url) {
	const xhr = new XMLHttpRequest();

	xhr.open("GET", url);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			console.log(xhr.response);
			console.log(xhr.status);
			if (xhr.status == 200) {
				displayResult(xhr.response);
			}
		}
	};

	xhr.send();
}

function submit() {
	const op = document.getElementById("op-select").value;
	const eq = document.getElementById("eq-input").value;
	const url = `${BASE_URL}${op}/${eq}`;
	sendAjaxMath(encodeURI(url));
}

function displayResult(response) {
	console.log(response);
	document.getElementById("result").innerText = response.result;
}