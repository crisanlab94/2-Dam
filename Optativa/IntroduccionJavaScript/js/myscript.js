function $(selector) {
    return document.querySelectorAll(selector);
}

//alert("Hello to JS");

/*console.log("Hola K ASE!");

var table = "Normal table"; // más amplias
let chair = "One chair"; //cosas concretas
console.log(table);
console.log(chair);

let testBoolean = true;
console.log(testBoolean);


let testNumber = 10;
console.log(testNumber);

let testString ='text';
console.log(testString);

let testBooleanObject = new Boolean(true);
console.log(testBooleanObject);

let testNumberObject= new Number(10);
console.log(testNumberObject);

let testStringObject = new String ('String');
console.log(testStringObject);

let testSt= new String ('Hola que pasa?');
console.log(testSt.toUpperCase);

//Concatenar
let prueba=table + chair;
console.log(prueba);

let name ='John';
let surname='Done';
let age = 23;
let question =`How old is ${name} ${surname}?`;
let answer = "He is "+age+" years old";
console.log(question);
console.log(answer);

let operador_a= 3;
let operador_b = 3;

let expo = operador_a ** operador_b;
let inc = ++operador_a;
let dec = --operador_a;

console.log(expo);
console.log(inc);
console.log(dec);

//Typeof ( ver el tipo)

console.log(typeof(testBoolean));

//null
let testNull=null;
console.log(typeof(testNull));

//undefined 
let testUndefined;
console.log(testUndefined);

//Array
var first_array=[];
var second_array = new Array(3);
var third_array= new Array (3,5);
var fourth_array = new Array (3,5,"Seville",true,third_array);
console.log(first_array);
console.log(second_array);
console.log(third_array);
console.log(fourth_array);

//Array access
console.log(third_array[1]);
console.log(fourth_array[2][1]);

//Array push
console.log(fourth_array.push("Spain"));
console.log(fourth_array[5]);

third_array[1] = 9;
console.log(third_array);

var arrayfinal = new Array (second_array,third_array);
console.log(arrayfinal);
//fila y después columnas
arrayfinal[1][2] = 2;
console.log(arrayfinal);*/


/*
var third_array = new Array(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20);
*/
/*
for normal
for (let index = 0; index < third_array.length; index++) {
    const element = third_array[index];
    console.log(element);
}
*/


//For inverso con variable dentro
/*
for(let i =third_array.length-1;i>0;i--){
    console.log(`Iteración de  ${third_array[i]}`);
} 
    */
/*
var iM= third_array.length-1;
*/

//For inverso con variable fuera

/*
for(;iM>0;iM--){
    console.log(`Iteración de  ${third_array[iM]}`);
}
    */

/*foreach*/

/*
third_array.forEach(function myFunction(item) {
  console.log(item); 
});



console.log(third_array.forEach(myFuntion(iteam)));
*/


let today = new Date();
let first_october = new Date(2025, 9, 1);
console.log(today);
console.log(first_october);
console.log(today.getDay());


if (today > first_october) {
    console.log("mayor");

}

else {
    console.log("menor");
}


function myFirstFunction() {
    console.log("Gracias")
}

function mySecondFunction() {
    console.log("Gracias por tu interés")
}

function myThirdFunction(mensaje) {
    console.log("Pesado")
    console.log("mensaje")
}

// Select DOM
var div = document.getElementById("my_div");
div.classList.add("my_class");
console.log(div);

// Select DOM II
var div2 = document.getElementsByTagName("div");
console.log(div2);

var second_div = document.querySelector(".my_class");
console.log(second_div);


console.log($("#my_third_div"));
console.log($(".prueba"));

//Create DOM Nodes I
$("#btn").addEventListener("click", function () {
    var input = document.createElement("input");
    input.setAttribute("type", "email");
    input.setAttribute("placeholder", "E-mail");
    input.setAttribute("name", "emails[]")
    $("#form").appendChild(input);
    myAlert("Add new email input")
});

function myAlert(msg) {
    console.log($("body").children[1]);
    var div = document.createElement("div");
    div.classList.add("alert");
    div.innerHTML = msg;
    var close = document.createElement("span");
    close.style.float = "right";
    close.classList.add("close");
    close.innerHTML = "X";
    div.appendChild(close);
    $("body").insertBefore(div, $("body").firstChild);
    bind_close();

}

function bind_close() {
    let elements = document.querySelectorAll(".close");
    for (var i = elements.length - 1; i >= 0; i--) {
        let el = elements[i];
        el.addEventListener("click", function () {
            this.parentNode.style.display = "none";
        });
    }
}

function $(selector) {
    return document.querySelector(selector);
}



