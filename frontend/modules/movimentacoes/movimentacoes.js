// 🔥 FORMATAR DATA
function formatarData(data) {
  if (!data) return "";
  return new Date(data).toLocaleString("pt-BR");
}


// 🔥 CARREGAR MOVIMENTAÇÕES
async function carregarMovimentacoes() {
  try {
    const tabela = document.getElementById("movimentacoesTabela");

    // 🔥 evita erro quando a tela ainda não carregou
    if (!tabela) {
      return;
    }

    const res = await fetch("http://localhost:8080/movimentacoes");

    if (!res.ok) {
      throw new Error("Erro ao buscar movimentações");
    }

    const data = await res.json();

    tabela.innerHTML = "";

    data.forEach(mov => {
      tabela.innerHTML += `
        <tr>
          <td>${mov.id}</td>
          <td>${mov.produto?.nome || "Sem produto"}</td>
          <td style="color: ${mov.tipo === "ENTRADA" ? "green" : "red"}">
            ${mov.tipo}
          </td>
          <td>${mov.quantidade}</td>
          <td>${formatarData(mov.dataMovimentacao)}</td>
        </tr>
      `;
    });

  } catch (e) {
    console.error("Erro ao carregar movimentações:", e);
  }
}


// 🔥 REGISTRAR MOVIMENTAÇÃO
async function registrarMovimentacao() {
  try {
    const produtoId = document.getElementById("produtoId").value;
    const tipo = document.getElementById("tipoMov").value;
    const quantidade = document.getElementById("quantidadeMov").value;

    const response = await fetch("http://localhost:8080/movimentacoes", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        produto: { id: Number(produtoId) },
        usuario: { id: 1 },
        tipo: tipo,
        quantidade: Number(quantidade)
      })
    });

    if (!response.ok) {
      throw new Error("Erro ao salvar movimentação");
    }

    alert("Movimentação registrada!");

    await carregarMovimentacoes();

  } catch (e) {
    console.error(e);
    alert("Erro ao registrar movimentação");
  }
}


// 🔥 FUNÇÃO CENTRAL (resolve seu problema)
function verificarTelaMovimentacoes() {
  if (window.location.hash === "#movimentacoes") {
    carregarMovimentacoes();
  }
}


// 🔥 roda quando entra no site
document.addEventListener("DOMContentLoaded", verificarTelaMovimentacoes);

// 🔥 roda quando troca de aba
window.addEventListener("hashchange", verificarTelaMovimentacoes);