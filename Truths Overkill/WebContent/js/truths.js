/**
 * 
 */

var truth1, truth2, truth3;
var clicked = 0;
var content_div;

window.onload = function setup() {
	// get the three p elements and store them into variables
	truth1 = document.getElementById("truth_1");
	truth2 = document.getElementById("truth_2");
	truth3 = document.getElementById("truth_3");
	content_div = document.getElementById("content_div");

	// set the onclick properties
	truth1.onclick = click1;
	truth2.onclick = click2;
	truth3.onclick = click3;

	console.log("hello");
	 randomize();
}

function randomize() {
	truth1.remove();
	truth2.remove();
	truth3.remove();

	// hard coding randomized
	switch (Math.floor(6 * Math.random())) {
	case 0:
		content_div.appendChild(truth1);
		content_div.appendChild(truth2);
		content_div.appendChild(truth3);
		break;
	case 1:
		content_div.appendChild(truth1);
		content_div.appendChild(truth3);
		content_div.appendChild(truth2);
		break;
	case 2:
		content_div.appendChild(truth2);
		content_div.appendChild(truth1);
		content_div.appendChild(truth3);
		break;
	case 3:
		content_div.appendChild(truth2);
		content_div.appendChild(truth3);
		content_div.appendChild(truth1);
		break;
	case 4:
		content_div.appendChild(truth3);
		content_div.appendChild(truth1);
		content_div.appendChild(truth2);
		break;
	case 5:
		content_div.appendChild(truth3);
		content_div.appendChild(truth2);
		content_div.appendChild(truth1	);
		break;
	}
}

function click1() {
	if (clicked == 0) {
		// hide the second
		truth2.remove();
		clicked = 1;
	} else if (clicked == 1) {
		// the you've clicked once and didn't choose the lie
		truth1.id = "wrong";
		clicked = 2;
	}// else do nothing
}

function click2() {
	if (clicked == 0) {
		// hide first
		truth1.remove();
		clicked = 1;
	} else if (clicked == 1) {
		truth2.id = "wrong";
		clicked = 2;
	}
}

function click3() {
	// randomly remove one of the truths
	if (clicked == 0) {
		switch (Math.floor(2 * Math.random())) {
		case 0:
			truth1.remove();
			break;
		case 1:
			truth2.remove();
			break;
		}
		clicked = 1;
	} else if(clicked == 1) {
		// they chose correct
		truth3.id = "correct";
		clicked = 2;
	}
}
