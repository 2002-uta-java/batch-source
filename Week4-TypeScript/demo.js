function doSomething() {
    for (var i = 0; i < 5; i++) {
        console.log(i);
    }
}
// doSomething();
var x;
x = 5;
// x = true;
var bool;
var str;
var obj;
function myVoidFunction() {
    console.log("Hello World");
}
function returnNum() {
    return 5;
}
var foreverFunction = function () {
    while (true) {
        console.log("hello world");
    }
};
var anotherNeverFunction = function (value) {
    if (typeof value == "string" && typeof value == "number") {
        return value;
    }
};
var myArr = [true, 4];
// let myArr2: string[] = [true, "Hello"];
var drawPoint = function (x, y) {
    console.log(x + ", " + y);
};
drawPoint(3, 4);
// drawPoint({}, "string");
// let drawPoint2 = (point: object)=>{
//     console.log(point.x +", "+point.y);
// }
// drawPoint2({x:3, y:5});
// drawPoint2(true);
// drawPoint2({name:"Joe", email:"JoeShmo@gmail.com"});
var drawPoint3 = function (point) {
    console.log(point.x + ", " + point.y);
};
// drawPoint3({name:"Joe", email:"JoeShmo@gmail.com"});
drawPoint3({ x: 3, y: 5 });
var drawPoint4 = function (point) {
    console.log(point.x + ", " + point.y);
};
drawPoint4({ x: 3, y: 5 });
var MyPoint = /** @class */ (function () {
    function MyPoint(_x, _y) {
        var _this = this;
        this.drawPoint = function () {
            console.log(_this.x + ", " + _this.y);
        };
        this.x = _x;
        this.y = _y;
    }
    return MyPoint;
}());
var drawPoint5 = function (point) {
    console.log(point.x + ", " + point.y);
};
var p = new MyPoint(4, 2);
p.drawPoint();
