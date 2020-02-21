/*
    Closures take advantage of JavaScripts lexical scoping to accomplish encapsulation
*/

/*
let counter = 0;
function add(){
    counter += 1;
}
*/

/*
function add2(){
    let counter = 0;
    counter += 1;
    return counter;
}
*/

function add3(){
    let counter = 0;
    return function(){counter+=1; return counter};
}
// let count = add3();

let count = (function(){
    let counter = 0;
    return function(){counter+=1; return counter};
})();