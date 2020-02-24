let lemurJSON = `[
    {
        "id": 3,
        "name" : "Zoboomafoo Jr",
        "age" : 8,  
        "type" : "Ring-tailed"
    }, {
        "id" : 4,
        "name" : "Reginald",
        "age" : 1,
        "type" : "Pygmy Mouse"
    }, {
        "id" : 5,
        "name" : "Lola",
        "age" : 19,
        "type" : "Sifaka"
    }
]`

window.onload = function(){
    console.log(lemurJSON);
    let lemurs = JSON.parse(lemurJSON);
    console.log(lemurs);
    for(let lemur of lemurs){
        // create a row and input lemur data
        this.addTableRow(lemur.name, lemur.age, lemur.type, lemur.id);
    }
}

document.getElementById("add-lemur-btn").addEventListener("click", addNewLemur);
let counter = 5;

function addTableRow(name, age, type, id){
    let newId;

    if(id){
        newId = id
    } else {
        newId = ++counter;
    }

    let table = document.getElementById("lemur-table");

    let newRow = document.createElement("tr");

    newRow.innerHTML = `<td>${newId}</td><td>${name}</td><td>${age}</td><td>${type}</td>`;

    table.appendChild(newRow);

}

function addNewLemur(){
    let name = document.getElementById("name-input").value;
    let age = document.getElementById("age-input").value;
    let type = document.getElementById("type-select").value;
    console.log(`You have submitted: ${name}, ${age}, ${type}`);

    let errorPar = document.getElementById("error-message");

    if(name && age){
        errorPar.hidden = true;
        addTableRow(name, age, type);
        // send this data back to our db
    } else {
        errorPar.hidden = false;
    }
}