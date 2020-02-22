/**
 * 
 */

console.log("Hello from main.js");

let picElement = document.getElementById("main-pic");
picElement.src = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fi.pinimg.com%2Foriginals%2F6b%2F5f%2Fbd%2F6b5fbdb2341c97a2a6ed1f84aa92de5d.jpg&f=1&nofb=1";
picElement.width = 250;

let figCaption = document.getElementsByTagName("figcaption");
let mainPicCapt = figCaption[0];
mainPicCapt.innerText = "Chess2 sucks, quokkas are better!";

// remove chess2 link
let contentDiv = document.getElementById("content-div");
let chess2Link = document.getElementById("chess2-link");
contentDiv.removeChild(chess2Link);
// can also just do chess2Link.remove()

// add a list item
let gameList = document.getElementById("game-list");
let newListItem = document.createElement("li");
newListItem.innerText = "Chess: Outback Edition";
newListItem.className = "list-group-item";

// gameList.appendChild(newListItem);

// insert new list item before
gameList.insertBefore(newListItem, gameList.children[1]);

// events
document.getElementById("main-pic").addEventListener("click", changePic);

let toggle = true;

function changePic(event) {
	let el = event.target;
	if (toggle) {
		el.src = "https://cdn.arstechnica.net/wp-content/uploads/2014/01/chess2-1-640x360.png";
		mainPicCapt.innerText = "Check out how great Chess2 is!";
		toggle = !toggle;
	} else {
		el.src = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fi.pinimg.com%2Foriginals%2F6b%2F5f%2Fbd%2F6b5fbdb2341c97a2a6ed1f84aa92de5d.jpg&f=1&nofb=1";
		mainPicCapt.innerText = "Chess2 sucks, quokkas are better!";
		toggle = !toggle;
	}

}
