/**
 * 
 */

window.onload = function() {
	setupNav();

	document.getElementById("profile-name").innerText = getFirstName() + " "
			+ getLastName();
	document.getElementById("profile-email").innerText = getEmail();

	const changeForm = document.getElementById("change-form");
	const changeFirstName = document.getElementById("change-first-name");
	const changeLastName = document.getElementById("change-last-name");
	const changeEmail = document.getElementById("change-email");
	const changePassword = document.getElementById("change-password");

	// set text from cookie
	changeFirstName.setAttribute("value", getFirstName());
	changeLastName.setAttribute("value", getLastName());
	changeEmail.setAttribute("value", getEmail());
	// don't set password (leave it empty)

	// setup the names (for form submission)
	changeFirstName.setAttribute("name", CHANGE_FIRST_NAME_ID);
	changeLastName.setAttribute("name", CHANGE_LAST_NAME_ID);
	changeEmail.setAttribute("name", CHANGE_EMAIL_ID);
	changePassword.setAttribute("name", CHANGE_PASSWORD_ID);

	// setup form submission
	changeForm.setAttribute("method", "POST");
	changeForm.setAttribute("action", CHANGE_PROFILE_API);
}