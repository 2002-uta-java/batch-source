console.log("Hello from my external file");

/*
console.log(Boolean(0));
console.log(Boolean());
console.log(Boolean(""));
console.log(Boolean(null));
console.log(Boolean(25));
console.log(Boolean(25*"three"));
console.log(Boolean([]));
console.log(Boolean({}));
*/

// console.log(kittycat); // we get a reference error here bc we used let

let kittycat = {
    name: "Duncan",
    age: 120,
    skills: [
        {name:"JavaScript",
        skillLevel:2}, 
        {name:"Sneaking",
        skillLevel:100}
    ],
    injectSemicolon: function(){
        console.log("hehe injected a semicolon");
        return function(){
            console.log("here's another function"); // we can return functions as well 
        }
    }
};

function myFunct(){
    console.log("this is my function");
}

for(let skill of kittycat.skills){
    console.log(skill);
}

// in gives us the keys of our objects or the index of our arrays 
for(let skillIndex in kittycat.skills){
    console.log(skillIndex + " " + kittycat.skills[skillIndex]);
}

for( let key in kittycat){
    console.log(key + " : " + kittycat[key]);
}

/* cannot do this because kittycat is not iterable
for(let value of kittycat){
    console.log(value);
} */