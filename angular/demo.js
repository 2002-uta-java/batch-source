/*
 * Typescript demo
 */
// just like javascript (sort of)
function doSomething() {
    for (var i = 0; i < 5; ++i) {
        console.log(i);
    }
}
// use angular to execute
// need to transpile it first: 
// >tsc demo.ts
//  --> creates a js file
// >tsc demo.ts -t ES6
//     --> converts to javascript compliant with ES6
// run js file with node
// >node demo.ts
doSomething();
// type safety
var x;
x = true; //<-- red squiggly (compile time error)
var bool;
var str;
var obj;
// void function (no colon means void)
function myVoidFunction() {
    console.log("Hello World");
}
// specify return type (can specify void)
function returnNum() {
    return 5;
}
var foreverFunction = function () {
    while (true) {
        console.log("Hello world");
    }
};
// any is, well, any type
var anotherForeverFunction = function (value) {
    if (typeof value == "string" && typeof value == "number")
        return value;
};
// normal javascript array
var myArr = [true, 4, "hello"];
var myArr2 = [true, "four"];
