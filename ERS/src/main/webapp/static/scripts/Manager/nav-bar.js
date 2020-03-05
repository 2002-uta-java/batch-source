/**
 * 
 */

function setupNav() {
	// setup links for navigation

	document.getElementById("all-employees").setAttribute("href",
			VIEW_EMPLOYEES_PAGE);
	document.getElementById("profile-home").setAttribute("href", HOME);
	document.getElementById("logout-link").setAttribute("href", LOGOUT_API);
	document.getElementById("submit-reimb-link").setAttribute("href",
			SUBMIT_REIMBURSEMENT_PAGE);
	document.getElementById("view-pending-link").setAttribute("href",
			VIEW_PENDING_PAGE);
	document.getElementById("view-processed-link").setAttribute("href",
			VIEW_PROCESSED_PAGE);
	document.getElementById("profile-link").setAttribute("href", PROFILE_PAGE);
	console.log("Setting the hrefs: " + LOGOUT_API + " and "
			+ SUBMIT_REIMBURSEMENT_PAGE);
}