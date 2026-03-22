let produtos = [];
let itens = [];
// 🚀 INIT (IMPORTANTE)
function initVendas() {
  carregarProdutos();
}
// formata a data
function formatarData(data) {
  if (!data) return "";
  return new Date(data).toLocaleString("pt-BR");
}

// 📦 CARREGAR PRODUTOS
async function carregarProdutos() {
  const res = await fetch("http://localhost:8080/produtos");
  const data = await res.json();

  console.log("RETORNO BACKEND:", data);

  // 🔥 AQUI ESTÁ A CORREÇÃO
  produtos = data.content;

  const select = document.getElementById("produtoSelect");
  select.innerHTML = '<option value="">Selecione um produto</option>';

  produtos.forEach(p => {
    const option = document.createElement("option");
    option.value = p.id;
    option.textContent = `${p.nome} (Estoque: ${p.quantidade})`;
    select.appendChild(option);
  });
}

// ➕ ADICIONAR ITEM
function adicionarItem() {
  const produtoId = document.getElementById("produtoSelect").value;
  const quantidade = parseInt(document.getElementById("quantidade").value);

  const produto = produtos.find(p => p.id == produtoId);

  if (!produto) {
    alert("Produto não encontrado");
    return;
  }

  if (!quantidade || quantidade <= 0) {
    alert("Quantidade inválida");
    return;
  }

  if (quantidade > produto.quantidade) {
    alert("Estoque insuficiente");
    return;
  }

  const subtotal = produto.preco * quantidade;

  itens.push({
    produto,
    quantidade,
    subtotal
  });

  document.getElementById("quantidade").value = "";

  renderizarItens();
}

// ❌ REMOVER ITEM
function removerItem(index) {
  itens.splice(index, 1);
  renderizarItens();
}

// 📊 RENDERIZAR TABELA
function renderizarItens() {
  const tbody = document.getElementById("tabelaItens");
  const empty = document.getElementById("emptyState");

  tbody.innerHTML = "";

  if (itens.length === 0) {
    empty.style.display = "block";
  } else {
    empty.style.display = "none";
  }

  let subtotal = 0;

  itens.forEach((item, index) => {
    subtotal += item.subtotal;

    const tr = document.createElement("tr");
    // aqui que fica a tabela
    tr.innerHTML = `
      <td>${item.produto.nome}</td>
      <td>${item.quantidade}</td>
      <td>R$ ${item.produto.preco}</td>
      <td>R$ ${item.subtotal.toFixed(2)}</td>
      <td>--</td>
      <td>
        <button class="btn-danger" onclick="removerItem(${index})">Remover</button>
      </td>
    `;

    tbody.appendChild(tr);
  });

  document.getElementById("subtotal").textContent = subtotal.toFixed(2);

  calcularTotal();
}

// 💰 CALCULAR TOTAL
function calcularTotal() {
  let subtotal = itens.reduce((acc, item) => acc + item.subtotal, 0);

  let desconto = parseFloat(document.getElementById("desconto").value) || 0;

  let total = subtotal - desconto;

  document.getElementById("descontoValor").textContent = desconto.toFixed(2);
  document.getElementById("total").textContent = total.toFixed(2);
}

// 🚀 FINALIZAR VENDA
async function finalizarVenda() {
  if (itens.length === 0) {
    alert("Adicione pelo menos um item");
    return;
  }

  const desconto = document.getElementById("desconto").value || 0;

  const body = itens.map(item => ({
    produto: { id: item.produto.id },
    quantidade: item.quantidade
  }));

  const res = await fetch(`http://localhost:8080/vendas?desconto=${desconto}`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(body)
  });

  if (res.ok) {
    alert("Venda realizada com sucesso!");

    itens = [];

    document.getElementById("desconto").value = "";
    renderizarItens();

    carregarProdutos(); // atualiza estoque na tela
  } else {
    const erro = await res.text();
    alert("Erro: " + erro);
  }
}