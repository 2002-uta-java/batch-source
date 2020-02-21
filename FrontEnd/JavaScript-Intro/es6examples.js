/*
some additions to ES6
- let, const
- classes
- arrow notation
- template literal
- symbol
- for/of 
- promises
 */


var x = 5;
var x = 10;


let y = 24;
// let y = "Hello World";
y = "Hello World";

const z = true;
// z = 4;



function myFunc(){
    let myNum = 25; // this is function or lexical scope
    console.log("my num is "+ myNum);
}

/* because var does not have a block scope, the second var pass is the same variable as the global variable
var pass = false;
var score = 80;
if(score>75){
    var pass = true;
}
console.log(pass);
*/

/*
let pass = false;
let score = 80;
if(score>75){
    let pass = true; // this is block scope
    console.log(pass)
}
console.log(pass);
*/

/*
console.log(myVar);
var myVar = 15;
console.log(myVar);
console.log(myVar2);
let myVar2 = 67;
console.log(myVar2);
*/

// let myFunc = function(){
// }

let myString = "Hello World";

let myTemplateLiteral = `here is a 
template literal 
that takes up a few lines.
I can inject some values like this 
one: ${myString}`;

function findSum1(num1, num2){
    return num1+num2;
}

let findSum2 = function(num1, num2){ /// if we invoke functions with too few params, the ones not provided are undefined
    console.log(num1,num2)
    return num1+num2;
}

let findSum3 = function(){
    let sum = 0;
    for(let argument of arguments){
        // console.log(argument);
        sum+=argument;
    }
    return sum;
}

let findSum4 = (num1, num2) => {return num1+num2};