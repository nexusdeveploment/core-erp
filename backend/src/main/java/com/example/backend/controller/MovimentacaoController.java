package com.example.backend.controller;

import com.example.backend.entity.Movimentacao;
import com.example.backend.service.MovimentacaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    private final MovimentacaoService movimentacaoService;

    public MovimentacaoController(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    @GetMapping
    public List<Movimentacao> listarMovimentacoes() {
        return movimentacaoService.listarMovimentacoes();
    }

    @PostMapping
    public Movimentacao registrarMovimentacao(@RequestBody Movimentacao movimentacao) {
        return movimentacaoService.registrarMovimentacao(movimentacao);
    }

    @GetMapping("/produto/{produtoId}")
    public List<Movimentacao> buscarPorProduto(@PathVariable Long produtoId) {
        return movimentacaoService.buscarPorProduto(produtoId);
    }
}
