console.log("hello from main.js");

let picElement = document.getElementById("main-pic");
// console.log(picElement);
picElement.src = "https://img-s-msn-com.akamaized.net/tenant/amp/entityid/AAHgqfo.img?h=630&w=1119&m=6&q=60&o=f&l=f";
picElement.alt = "a pair of wombats";
picElement.width = 500;

let figCaptions = document.getElementsByTagName("figcaption");
console.log(figCaptions);
let mainPicCapt = figCaptions[0];
mainPicCapt.innerText = "Check out this mama wombat with her joey";

// let contentDiv = document.getElementById("content-div");
let chess2Link = document.getElementById("chess2-link");
// contentDiv.removeChild(chess2Link);
chess2Link.remove();

let gameList = document.getElementById("game-list");
let newListItem = document.createElement("li");
newListItem.innerText = "Chess: Outback Edition";
newListItem.className = "list-group-item";
// gameList.appendChild(newListItem);
// gameList.insertBefore(newListItem, gameList.children[0]);
gameList.prepend(newListItem);






