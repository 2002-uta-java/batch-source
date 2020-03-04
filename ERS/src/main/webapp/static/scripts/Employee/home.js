/**
 * file which sets up the home page (basically just displays welcome to the user
 */

window.onload = function() {
	setupNav();
	const jumbo = document.getElementById("welcome-display");
	const firstName = getCookie(FIRST_NAME_HEADER);
	const lastName = getCookie(LAST_NAME_HEADER);

	jumbo.innerText = "Welcome " + firstName + " " + lastName;

	// see if there's a message, if so display it
	const message = getCookie(SUCCESS_COOKIE);
	if (message == FAIL_VALUE) {
		document.getElementById("message").innerText = "There was an error. Please try again later.";
	} else if(message == SUCCESS_VALUE){
		document.getElementById("message").innerText = "Success!!!";
	}

}
