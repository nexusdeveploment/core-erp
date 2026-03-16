async function registrarMovimentacao(){

try{

const produtoId = document.getElementById("produtoId").value;
const tipo = document.getElementById("tipoMov").value;
const quantidade = document.getElementById("quantidadeMov").value;

await fetch("http://localhost:8080/movimentacoes",{

method:"POST",

headers:{
"Content-Type":"application/json"
},

body:JSON.stringify({

produto:{
id:Number(produtoId)
},

usuario:{
id:1
},

tipo:tipo,

quantidade:Number(quantidade)

})

});

alert("Movimentação registrada");

}catch(e){

console.error(e);
alert("Erro ao registrar movimentação");

}

}