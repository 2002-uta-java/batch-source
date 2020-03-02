/**
 * 
 */

function authenticate() {
	// contact server and see if your local token (if it exists) is valid, if not redirect to the login page
	const xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
		if(xhr.readyState == 4){
			if(xhr.status == 401){
				window.href = ""
			}
		}
	}
}