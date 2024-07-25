

//----------------------------------------//
let objeto = {
    "nombre" : "Juan",
    "edad" : 30,
    "ocupación" : "Desarrolador - Programador",
};
for (let clave in objeto){
    console.log(clave + " : " + objeto[clave]);
}
//----------------------------------------//
let mapa = new map ();
mapa.set("nombre", "Juan");
mapa.set("edad", 30);
mapa.set("ocupación", "Desarrolador - Programador");

for (let [clave, valor]of mapa){
    console.log(clave + " : " + valor);
}
// Set= establece un valor y clave //



//----------------------------------------//



let arreglo = [
    {clave: "nombre", valor:"Juan"},
    {clave: "edad", valor:30 },
]

for (let elemento of arreglo) {
    console.log(elemento.clave + ": " + elemento.valor);
};
//----------------------------------------//
let pares = [
    ["nombre", "Juan"],
    ["edad", 30],
];

for (let[clave, valor] of pares) {
    console.log(clave + ": " + valor);   
};
//----------------------------------------//


document.addEventListener('DOMContentLoaded', function(){


});