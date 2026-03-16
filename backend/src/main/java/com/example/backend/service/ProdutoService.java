package com.example.backend.service;

import com.example.backend.dto.RelatorioEstoqueDTO;
import com.example.backend.entity.Produto;
import com.example.backend.repository.ProdutoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // LISTAR PRODUTOS
    public Page<Produto> listarProdutos(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    // BUSCAR POR ID
    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    // CRIAR PRODUTO
    public Produto criarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    // ATUALIZAR PRODUTO
    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {

        Produto produto = produtoRepository.findById(id).orElseThrow();

        produto.setNome(produtoAtualizado.getNome());
        produto.setCodigo(produtoAtualizado.getCodigo());
        produto.setPreco(produtoAtualizado.getPreco());
        produto.setQuantidade(produtoAtualizado.getQuantidade());
        produto.setEstoqueMinimo(produtoAtualizado.getEstoqueMinimo());

        return produtoRepository.save(produto);
    }

    // DELETAR PRODUTO
    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    // PRODUTOS COM ESTOQUE BAIXO
    public List<Produto> listarEstoqueBaixo() {
        return produtoRepository.buscarProdutosEstoqueBaixo();
    }

    // BUSCAR POR NOME
    public List<Produto> buscarPorNome(String nome) {
        return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }

    // BUSCAR POR CÓDIGO
    public List<Produto> buscarPorCodigo(String codigo) {
        return produtoRepository.findByCodigo(codigo);
    }

    // RELATÓRIO DE ESTOQUE
    public List<RelatorioEstoqueDTO> gerarRelatorioEstoque() {
        return produtoRepository.gerarRelatorioEstoque();
    }
}