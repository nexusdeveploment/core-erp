async function initDashboard() {
  setTopbarTitle("Dashboard");

  try {
    const produtosPage = await getProdutos(0, 100);
    const produtos = produtosPage.content || [];

    const totalProdutos = produtos.length;
    const baixoEstoque = produtos.filter(p => Number(p.quantidade) <= Number(p.estoqueMinimo || 0) && Number(p.quantidade) > 0).length;
    const semEstoque = produtos.filter(p => Number(p.quantidade) <= 0).length;
    const valorEstoque = produtos.reduce((acc, p) => {
      return acc + (Number(p.preco || 0) * Number(p.quantidade || 0));
    }, 0);

    document.getElementById("card-total-produtos").textContent = totalProdutos;
    document.getElementById("card-baixo-estoque").textContent = baixoEstoque;
    document.getElementById("card-valor-estoque").textContent = formatarMoeda(valorEstoque);
    document.getElementById("card-sem-estoque").textContent = semEstoque;
  } catch (error) {
    console.error(error);
  }
}

initDashboard();