package com.example.backend.service;

import com.example.backend.entity.Movimentacao;
import com.example.backend.entity.Produto;
import com.example.backend.entity.TipoMovimentacao;
import com.example.backend.repository.MovimentacaoRepository;
import com.example.backend.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;
    private final ProdutoRepository produtoRepository;

    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository,
                               ProdutoRepository produtoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<Movimentacao> listarMovimentacoes() {
        return movimentacaoRepository.findAll();
    }

    public Movimentacao registrarMovimentacao(Movimentacao movimentacao) {

        Produto produto = produtoRepository
                .findById(movimentacao.getProduto().getId())
                .orElseThrow();

        if (movimentacao.getTipo() == TipoMovimentacao.ENTRADA) {
            produto.setQuantidade(produto.getQuantidade() + movimentacao.getQuantidade());
        }

        if (movimentacao.getTipo() == TipoMovimentacao.SAIDA) {
            produto.setQuantidade(produto.getQuantidade() - movimentacao.getQuantidade());
        }

        if (movimentacao.getTipo() == TipoMovimentacao.SAIDA) {

            if (produto.getQuantidade() < movimentacao.getQuantidade()) {
                throw new IllegalArgumentException("Estoque insuficiente");
            }

            produto.setQuantidade(produto.getQuantidade() - movimentacao.getQuantidade());
        }

        produtoRepository.save(produto);

        movimentacao.setProduto(produto);

        return movimentacaoRepository.save(movimentacao);
    }

    public List<Movimentacao> buscarPorProduto(Long produtoId) {
        return movimentacaoRepository.findByProdutoId(produtoId);
    }

    public List<Movimentacao> listarTodas(){
        return movimentacaoRepository.findAll();
    }
}
