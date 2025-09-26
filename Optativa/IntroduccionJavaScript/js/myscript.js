//alert("Hello to JS");

console.log("Hola K ASE!");

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
console.log(arrayfinal);