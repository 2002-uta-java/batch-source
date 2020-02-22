/**
 * 
 */

lemurJSON = `[{
"id": 3, 
"name":"Zoboomafo Jr", 
"age": 8, 
"type": "Ring-Tailed"},
{
"id": 5,
"name": "Lola",
"age": 19,
"type": "Sifaka"}
]`

window.onload = function() {
	console.log(lemurJSON);
	let lemurs = JSON.parse(lemurJSON);
	console.log(lemurs);
	
	for(let lemur of lemurs){
		this.addTableRow(lemur.name, lemur.age, lemur.type, lemur.id);
	}
}

document.getElementById("add-lemur-btn").addEventListener("click", addNewLemur);

let counter = 5;

function addTableRow(name, age, type, id){
	if(!id)
		id = ++counter;
	let table = document.getElementById("lemur-table");
	let newRow = document.createElement("tr");
	
	newRow.innerHtml = `<td>${id}<td>${name}<td>${age}<td>${type}`;
	table.appendChild(newRow);
}

function addNewLemur(){
	let name = document.getElementById("name-input").value;
	let age = document.getElementById("age-input").value;
	let type = document.getElementById("type-select").value;
	console.log(`You have submitted: ${name}, ${age}, ${type}`);
	addTableRow(name, age, type);
}