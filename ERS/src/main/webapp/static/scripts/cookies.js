/**
 * A function to read cookie values, taken directly from
 * https://www.w3schools.com/js/js_cookies.asp
 */

function getCookie(cname) {
	var name = cname + "=";
	var decodedCookie = decodeURIComponent(document.cookie);
	var ca = decodedCookie.split(';');
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ') {
			c = c.substring(1);
		}
		if (c.indexOf(name) == 0) {
			return c.substring(name.length, c.length);
		}
	}
	return "";
}

function getFirstName() {
	return getCookie(FIRST_NAME_HEADER);
}

function getLastName() {
	return getCookie(LAST_NAME_HEADER)
}

function getEmail() {
	return getCookie(EMAIL_COOKIE);
}