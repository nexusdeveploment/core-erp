const API_BASE_URL = "http://localhost:8080";

async function apiRequest(endpoint, options = {}) {
  const response = await fetch(`${API_BASE_URL}${endpoint}`, {
    headers: {
      "Content-Type": "application/json",
      ...(options.headers || {})
    },
    ...options
  });

  if (!response.ok) {
    const message = await response.text();
    throw new Error(message || "Erro na requisição");
  }

  const contentType = response.headers.get("content-type") || "";
  if (contentType.includes("application/json")) {
    return response.json();
  }

  return response.text();
}

/* DASHBOARD */
async function getDashboard() {
  return apiRequest("/dashboard");
}

/* PRODUTOS */
async function getProdutos(page = 0, size = 10) {
  return apiRequest(`/produtos?page=${page}&size=${size}`);
}

async function getProdutoById(id) {
  return apiRequest(`/produtos/${id}`);
}

async function createProduto(produto) {
  return apiRequest("/produtos", {
    method: "POST",
    body: JSON.stringify(produto)
  });
}

async function updateProduto(id, produto) {
  return apiRequest(`/produtos/${id}`, {
    method: "PUT",
    body: JSON.stringify(produto)
  });
}

async function deleteProduto(id) {
  return apiRequest(`/produtos/${id}`, {
    method: "DELETE"
  });
}

async function buscarProdutoPorNome(nome) {
  return apiRequest(`/produtos/buscar/nome?nome=${encodeURIComponent(nome)}`);
}

async function buscarProdutoPorCodigo(codigo) {
  return apiRequest(`/produtos/buscar/codigo?codigo=${encodeURIComponent(codigo)}`);
}

async function getEstoqueBaixo() {
  return apiRequest("/produtos/estoque-baixo");
}

/* MOVIMENTAÇÕES */
async function criarMovimentacao(payload) {
  return apiRequest("/movimentacoes", {
    method: "POST",
    body: JSON.stringify(payload)
  });
}

async function getMovimentacoesPorProduto(id) {
  return apiRequest(`/movimentacoes/produto/${id}`);
}

/* RELATÓRIOS */
async function getRelatorioEstoque() {
  return apiRequest("/relatorios/estoque");
}