/**
 * 
 */

function setupNav() {
	// setup links for navigation
	document.getElementById("logout-link").setAttribute("href", LOGOUT_API);
	document.getElementById("submit-reimb-link").setAttribute("href",
			SUBMIT_REIMBURSEMENT_PAGE);
	console.log("Setting the hrefs: " + LOGOUT_API + " and "
			+ SUBMIT_REIMBURSEMENT_PAGE);
}