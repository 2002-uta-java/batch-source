addTableRow();

function addTableRow() {
    let table = document.getElementById("rTable");

    let newRow = document.createElement("tr");

    newRow.innerHTML = "<td>0</td><td>type</td><td>200</td><td>$50</td><td>Time created</td><td>Time submitted</td>";

    table.appendChild(newRow);
}