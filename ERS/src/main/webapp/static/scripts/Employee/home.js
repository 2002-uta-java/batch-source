/**
 * file which sets up the home page (basically just displays welcome to the user
 */

window.onload = function() {
	// setup links for navigation
	document.getElementById("logout-link").setAttribute("href", LOGOUT_API);

	const jumbo = document.getElementById("welcome-display");
	const firstName = getCookie(FIRST_NAME_HEADER);
	const lastName = getCookie(LAST_NAME_HEADER);

	jumbo.innerText = "Welcome " + firstName + " " + lastName;
}
