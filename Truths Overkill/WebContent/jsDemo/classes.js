/**
 * Creating objects.
 */

let myWombat = {
	name : "Ludwig",
	weight : 50,
	maxSpeed : 25
};

// function Wombat(name, weight, maxSpeed){
// this.name = name;
// this.weight = weight;
// this.maxSpeed = maxSpeed;
// this.burrow = function(){
// console.log(name, "is burrowing");
// }
// }

class Wombat{
constructor(name_, weight_, maxSpeed_) {
	let name = name_;
	let weight = weight_;
	let maxSpeed = maxSpeed_;

	this.setName = function(_name) {
		name = _name;
	};
	this.getName = function() {
		return name;
	};
	this.setWeight =  function(_weight)
	{
		weight = _weight;
	}
	;
	this.getWeight = function()
	{
		return weight;
	}
	;
	this.setMaxSpeed = function(_maxSpeed)
	{
		maxSpeed = _maxSpeed;
	}
	;
	this.getMaxSpeed = function()
	{
		return maxSpeed;
	}
	;
}
}