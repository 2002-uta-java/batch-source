function doSomething(){
    for(let i=0; i<5; i++){
        console.log(i);
    }
}

// doSomething();

let x: number;
x = 5;
// x = true;

let bool: boolean;
let str: string;
let obj: object;

function myVoidFunction(): void{
    console.log("Hello World");
}

function returnNum(){
    return 5;
} 

let foreverFunction = function(): never{
    while(true){
        console.log("hello world");
    }
}

let anotherNeverFunction = function(value: any){
    if(typeof value == "string" && typeof value == "number"){
        return value;
    }
}

let myArr: any[] = [true, 4];
// let myArr2: string[] = [true, "Hello"];


let drawPoint = (x: number,y: number) =>{
    console.log(x+", "+y);
}

drawPoint(3,4);
// drawPoint({}, "string");

// let drawPoint2 = (point: object)=>{
//     console.log(point.x +", "+point.y);
// }

// drawPoint2({x:3, y:5});
// drawPoint2(true);
// drawPoint2({name:"Joe", email:"JoeShmo@gmail.com"});

let drawPoint3 = (point: {x:number, y:number}) =>{
    console.log(point.x+", "+point.y);
}

// drawPoint3({name:"Joe", email:"JoeShmo@gmail.com"});
drawPoint3({x:3, y:5});

interface Point {
    x:number;
    y:number;
}

let drawPoint4 = (point: Point) => {
    console.log(point.x+", "+point.y);
}

drawPoint4({x:3, y:5});


class MyPoint{
    x:number;
    y:number;

    constructor(_x:number, _y:number){
        this.x = _x;
        this.y = _y;
    }

    drawPoint = () =>{
        console.log(this.x+", "+this.y);
    }
}

let drawPoint5 = (point: MyPoint) =>{
    console.log(point.x+", "+point.y);
}

let p: MyPoint = new MyPoint(4,2);
p.drawPoint();
