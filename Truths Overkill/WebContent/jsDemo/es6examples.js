/**
 * some additions to ES6 - let, const - classes - arrow notation - template
 * literal - symbol data type - for of loop (for in was already there) -
 * promises
 */

// var x = 5;
// var x = 10;
//
// let y = 5;
// // let y = 10;
// y = "Hello World";
//
// const z = true;
// //z = 4;
//
// function myFunc(){
// var pass = true;
// let myNum = 25;
// console.log(pass);
// console.log(myNum);
// }
//
// var pass = false;
// var score = 80;
//
// myFunc();
// console.log(pass);
// console.log(pass);
//
// if(score > 75){
// var pass = true;
// }
//
// console.log(pass);
//
// let passL = false;
// let scoreL = 75;
// if(scoreL > 80){
// let passL = true;
// }
//
// console.log(passL);

// console.log(myVar);
// var myVar = 15;
// console.log(myVar);
//
// console.log(myVar2); // error
// let myVar2 = 67;
// console.log(myVar2);

// template literals
let myString = "Hello World";
let myTemplateLiteral = `here is a
template literal
that takes up a few lines
I can inject some values like this
one: ${myString}`;

console.log(myTemplateLiteral);

let testString = 'hello';
let testTemplate = `hello`;

if(testString == testTemplate)
	console.log(`${testString} == ${testTemplate}`);
else
	console.log(`${testString} != ${testTemplate}`);

if(testString === testTemplate)
	console.log(`${testString} === ${testTemplate}`);
else
	console.log(`${testString} !== ${testTemplate}`);


let findSum3 = function(){
	for(let argument of arguments){
		console.log(argument);
	}
}

// arrow notation for functions
let findSum4 = (num1, num2) => {return num1 + num2};
	
	
	
	
	
	
	
	
	