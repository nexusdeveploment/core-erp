package com.example.backend.repository;

import com.example.backend.dto.RelatorioEstoqueDTO;
import com.example.backend.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT COUNT(p) FROM Produto p")
    Long contarProdutos();

    @Query("SELECT COUNT(p) FROM Produto p WHERE p.quantidade <= p.estoqueMinimo")
    Long contarEstoqueBaixo();

    @Query("SELECT SUM(p.preco * p.quantidade) FROM Produto p")
    Double valorTotalEstoque();

    @Query("SELECT p FROM Produto p WHERE p.quantidade <= p.estoqueMinimo")
    List<Produto> buscarProdutosEstoqueBaixo();

    List<Produto> findByNomeContainingIgnoreCase(String nome);

    List<Produto> findByCodigo(String codigo);

    @Query("""
SELECT new com.example.backend.dto.RelatorioEstoqueDTO(
    p.nome,
    p.quantidade,
    p.preco,
    (p.preco * p.quantidade),
    f.nome,
    c.nome
)
FROM Produto p
LEFT JOIN p.fornecedor f
LEFT JOIN p.categoria c
""")
    List<RelatorioEstoqueDTO> gerarRelatorioEstoque();
}
