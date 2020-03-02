/**
 * file which sets up the home page (basically just displays welcome to the user
 */

window.onload = function() {
	const jumbo = document.getElementById("welcome-display");
	const firstName = getCookie(FIRST_NAME_HEADER);
	const lastName = getCookie(LAST_NAME_HEADER);

	jumbo.innerText = "Welcome " + firstName + " " + lastName;
}
