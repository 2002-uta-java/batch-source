/**
 * Closures take advantage of JavaScripts lexical scoping to
 */

// problem with this is that you can just reset counter anytime you want (trying
// to prevent this)
// let counter = 0;
//
// function add() {
// return counter++;
// }
// function add(){
// let counter = 0;
// ++counter;
// return counter;
// }
// create a method that returns a function (with internalized variable)
function adder() {
	let counter = 0;
	return function() {
		return ++counter;
	}
}

// store into a variable, now call that variable (as a function)
let add = adder();


let count = (function() {
	let counter = 0;
	return function(){
		return ++counter;
	}
})();







