package com.example.backend.repository;

import com.example.backend.entity.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

    @Query("SELECT COUNT(m) FROM Movimentacao m WHERE DATE(m.dataMovimentacao) = CURRENT_DATE")
    Long contarMovimentacoesHoje();

    List<Movimentacao> findByProdutoId(Long produtoId);

}
