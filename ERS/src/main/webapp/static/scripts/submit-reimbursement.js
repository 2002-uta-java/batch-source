/**
 * does some setting up for the form elements and validates the description's
 * input.
 */

window.onload = function() {
	setupNav();
	
	const form = document.getElementById("form");
	form.setAttribute("method", "POST");
	form.setAttribute("action", SUBMIT_REIMBURSEMENT_API);

	const description = document.getElementById("description");
	const placeholder =
`Good: Stayed at a Holiday Inn
Bad: Ran up a pretty big tab at the hotel bar`;
	description.setAttribute("name", DESCRIPTION_ID);
	
	const amount = document.getElementById("amount");
	amount.setAttribute("name", AMOUNT_ID);
	
	const datePicker = document.getElementById(DATE_ID);
	datePicker.setAttribute("name", DATE_ID);
	
	description.setAttribute("placeholder", placeholder);
	description.setAttribute("maxlength", MAX_LENGTH_DESC);
	$('textarea').maxlength({
		alwaysShow : true,
		threshold : 10,
		warningClass : "label label-success",
		limitReachedClass : "label label-danger",
		separator : ' out of ',
		preText : '',
		postText : ' chars',
		validate : true
	});
	
	// had to add this line because...well I don't really no why (but something
	// is adding JQuery multiple times)
	const $j = jQuery.noConflict();
	$j("#date").datepicker({
		format: 'mm/dd/yyyy',
		maxDate: new Date()
	});
}