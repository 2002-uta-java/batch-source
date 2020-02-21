let myWombat = {name: "Ludwig", weight: 50, maxSpeed: 25};

/*
function Wombat(name, weight, maxSpeed){
    this.name = name;
    this.weight = weight;
    this.maxSpeed = maxSpeed;
    this.burrow = function(){
        console.log(name + " is burrowing");
    }
}
*/



function Wombat(nameInput, weightInput, maxSpeedInput){
    let name = nameInput;
    let weight = weightInput;
    let maxSpeed = maxSpeedInput;

    this.setName = function(newName){
        name = newName;
    }
    this.getName = function(){
        return name;
    }

    this.setWeight = function(newWeight){
        if(newWeight>44 && newWeight<77){
            weight = newWeight;
        }
    }
    this.getWeight = function(){
        return weight;
    }

    this.burrow = function(){
        console.log(name + " is burrowing");
    }
}