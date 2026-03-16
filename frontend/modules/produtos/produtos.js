var produtosPaginaAtual = 0;

function abrirFormularioProduto() {
  document.getElementById("produto-form-container").style.display = "block";
}

function fecharFormularioProduto() {
  document.getElementById("produto-form-container").style.display = "none";
}

function limparFormularioProduto() {
  document.getElementById("produto-nome").value = "";
  document.getElementById("produto-codigo").value = "";
  document.getElementById("produto-preco").value = "";
  document.getElementById("produto-quantidade").value = "";
  document.getElementById("produto-estoque-minimo").value = "";
  document.getElementById("produto-fornecedor-id").value = "";
  document.getElementById("produto-categoria-id").value = "";
}

function renderTabelaProdutos(produtos) {
  const tbody = document.getElementById("produtos-tbody");
  tbody.innerHTML = "";

  if (!produtos || produtos.length === 0) {
    tbody.innerHTML = `
      <tr>
        <td colspan="7">
          <div class="empty-state">Nenhum produto encontrado.</div>
        </td>
      </tr>
    `;
    return;
  }

  produtos.forEach(produto => {
    const status = getStatusEstoque(produto);

    tbody.innerHTML += `
      <tr>
        <td>${produto.id ?? "-"}</td>
        <td>${produto.nome ?? "-"}</td>
        <td>${produto.codigo ?? "-"}</td>
        <td>${formatarMoeda(produto.preco)}</td>
        <td>${produto.quantidade ?? 0}</td>
        <td>
          <span class="badge ${status.classe}">${status.texto}</span>
        </td>
        <td>
          <div class="actions">
            <button class="btn-danger" onclick="removerProduto(${produto.id})">Excluir</button>
          </div>
        </td>
      </tr>
    `;
  });
}

async function carregarProdutos(page = 0) {
  setTopbarTitle("Produtos");

  try {
    produtosPaginaAtual = page;
    const data = await getProdutos(page, 10);
    renderTabelaProdutos(data.content || []);
  } catch (error) {
    console.error(error);
    alert("Erro ao carregar produtos.");
  }
}

async function pesquisarProdutos() {
  try {
    const nome = document.getElementById("buscaNome").value.trim();

    if (!nome) {
      carregarProdutos(produtosPaginaAtual);
      return;
    }

    const produtos = await buscarProdutoPorNome(nome);
    renderTabelaProdutos(produtos || []);
  } catch (error) {
    console.error(error);
    alert("Erro ao buscar produtos.");
  }
}

async function salvarProduto() {
  try {
    const payload = {
      nome: document.getElementById("produto-nome").value,
      codigo: document.getElementById("produto-codigo").value,
      preco: Number(document.getElementById("produto-preco").value),
      quantidade: Number(document.getElementById("produto-quantidade").value),
      estoqueMinimo: Number(document.getElementById("produto-estoque-minimo").value),
      fornecedor: document.getElementById("produto-fornecedor-id").value
        ? { id: Number(document.getElementById("produto-fornecedor-id").value) }
        : null,
      categoria: document.getElementById("produto-categoria-id").value
        ? { id: Number(document.getElementById("produto-categoria-id").value) }
        : null
    };

    await createProduto(payload);
    limparFormularioProduto();
    fecharFormularioProduto();
    carregarProdutos();
    alert("Produto cadastrado com sucesso.");
  } catch (error) {
    console.error(error);
    alert("Erro ao salvar produto.");
  }
}

async function removerProduto(id) {
  try {
    await deleteProduto(id);
    carregarProdutos(produtosPaginaAtual);
    alert("Produto excluído com sucesso.");
  } catch (error) {
    console.error(error);
    alert("Erro ao excluir produto.");
  }
}

carregarProdutos();