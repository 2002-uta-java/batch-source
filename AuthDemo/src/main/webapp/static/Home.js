/**
 * 
 */

window.onload = function() {
	const p = document.getElementById("token");
	const token = sessionStorage.getItem("token");
	console.log(token);
	
	p.innerText = token;
}