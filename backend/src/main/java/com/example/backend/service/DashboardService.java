package com.example.backend.service;

import com.example.backend.dto.DashboardDTO;
import com.example.backend.repository.MovimentacaoRepository;
import com.example.backend.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final ProdutoRepository produtoRepository;
    private final MovimentacaoRepository movimentacaoRepository;

    public DashboardService(ProdutoRepository produtoRepository,
                            MovimentacaoRepository movimentacaoRepository) {

        this.produtoRepository = produtoRepository;
        this.movimentacaoRepository = movimentacaoRepository;
    }

    public DashboardDTO obterDadosDashboard() {

        Long totalProdutos = produtoRepository.contarProdutos();
        Long produtosEstoqueBaixo = produtoRepository.contarEstoqueBaixo();
        Long movimentacoesHoje = movimentacaoRepository.contarMovimentacoesHoje();
        Double valorTotalEstoque = produtoRepository.valorTotalEstoque();

        return new DashboardDTO(
                totalProdutos,
                produtosEstoqueBaixo,
                movimentacoesHoje,
                valorTotalEstoque
        );
    }
}
