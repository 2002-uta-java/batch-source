/*
 * Typescript demo
 */

 // just like javascript (sort of)
 function doSomething() {
     for(let i = 0; i < 5; ++i){
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
 let x:number;
 x = true; //<-- red squiggly (compile time error)
 
 let bool: boolean;
 let str: string;
 let obj: object;

// void function (no colon means void)
 function myVoidFunction() {
     console.log("Hello World");
 }

 // specify return type (can specify void)
 function returnNum():number {
     return 5;
 }

 let foreverFunction = function() {
     while(true){
         console.log("Hello world");
     }
 }

// any is, well, any type
 let anotherForeverFunction = function(value: any) {
    if(typeof value == "string" && typeof value == "number")
        return value;
 }

 // normal javascript array
 let myArr: any[] = [true, 4, "hello"];
 let myArr2: string[] = [true, "four"];