package com.example.backend.controller;

import com.example.backend.dto.RelatorioEstoqueDTO;
import com.example.backend.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    private final ProdutoService produtoService;

    public RelatorioController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/estoque")
    public List<RelatorioEstoqueDTO> relatorioEstoque() {
        return produtoService.gerarRelatorioEstoque();
    }
}