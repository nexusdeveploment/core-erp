function formatarMoeda(valor) {
  return Number(valor || 0).toLocaleString("pt-BR", {
    style: "currency",
    currency: "BRL"
  });
}

function setTopbarTitle(title) {
  const el = document.getElementById("topbar-title");
  if (el) el.textContent = title;
}

function ativarLinkSidebar(rota) {
  document.querySelectorAll(".sidebar-link").forEach(link => {
    const href = link.getAttribute("href");
    link.classList.toggle("active", href === `#${rota}`);
  });
}

function getStatusEstoque(produto) {
  const qtd = Number(produto.quantidade || 0);
  const minimo = Number(produto.estoqueMinimo || 0);

  if (qtd <= 0) {
    return { texto: "Sem estoque", classe: "badge-danger" };
  }

  if (qtd <= minimo) {
    return { texto: "Baixo estoque", classe: "badge-warning" };
  }

  return { texto: "Em estoque", classe: "badge-success" };
}