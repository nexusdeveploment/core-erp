package com.example.backend.controller;

import com.example.backend.entity.Produto;
import com.example.backend.service.ProdutoService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
@CrossOrigin("*")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    // LISTAR PRODUTOS COM PAGINAÇÃO
    @GetMapping
    public Page<Produto> listarProdutos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return produtoService.listarProdutos(pageable);
    }

    // BUSCAR PRODUTO POR ID
    @GetMapping("/{id}")
    public Optional<Produto> buscarProduto(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }

    // CRIAR PRODUTO
    @PostMapping
    public Produto criarProduto(@RequestBody Produto produto) {
        return produtoService.criarProduto(produto);
    }

    // ATUALIZAR PRODUTO
    @PutMapping("/{id}")
    public Produto atualizarProduto(@PathVariable Long id, @RequestBody Produto produto) {
        return produtoService.atualizarProduto(id, produto);
    }

    // DELETAR PRODUTO
    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
    }

    // PRODUTOS COM ESTOQUE BAIXO
    @GetMapping("/estoque-baixo")
    public List<Produto> listarEstoqueBaixo() {
        return produtoService.listarEstoqueBaixo();
    }

    // BUSCAR POR NOME
    @GetMapping("/buscar/nome")
    public List<Produto> buscarPorNome(@RequestParam String nome) {
        return produtoService.buscarPorNome(nome);
    }

    // BUSCAR POR CÓDIGO
    @GetMapping("/buscar/codigo")
    public List<Produto> buscarPorCodigo(@RequestParam String codigo) {
        return produtoService.buscarPorCodigo(codigo);
    }
}