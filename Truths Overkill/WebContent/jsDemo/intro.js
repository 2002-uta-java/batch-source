/**
 * 
 */

console.log("Hello from external script");

// console.log(Boolean(0));
// console.log(Boolean(""));
// console.log(Boolean(null));
// console.log(Boolean(25));
// console.log(Boolean(25*"three"));
// console.log(Boolean([]));
// console.log(Boolean({}));

// object literal
var kittycat = {
	name : "Duncan",
	age : 120,
	skills : [ {
		name : "Javascript",
		skilllevel : 2
	}, {
		name : "Sneaking",
		skilllevel : 100
	} ],
	injectSemicolon : function() {
		console.log("hehe injected semicolon!!!");
	}
};

function myFunct() {
	console.log("this is my function");
}

kittycat.injectSemicolon();
kittycat.color = "tabby";

// for each loop (over array)
for(let skill of kittycat.skills)
	console.log(skill);

// iterate over indexes
for(let i in kittycat.skills)
	console.log(kittycat.skills[i]);

for(let i in kittycat)
	console.log(kittycat[i]);



