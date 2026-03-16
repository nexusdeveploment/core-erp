package com.example.backend.controller;

import com.example.backend.entity.Fornecedor;
import com.example.backend.service.FornecedorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @GetMapping
    public List<Fornecedor> listarFornecedores() {
        return fornecedorService.listarFornecedores();
    }

    @GetMapping("/{id}")
    public Optional<Fornecedor> buscarFornecedor(@PathVariable Long id) {
        return fornecedorService.buscarPorId(id);
    }

    @PostMapping
    public Fornecedor criarFornecedor(@RequestBody Fornecedor fornecedor) {
        return fornecedorService.criarFornecedor(fornecedor);
    }

    @DeleteMapping("/{id}")
    public void deletarFornecedor(@PathVariable Long id) {
        fornecedorService.deletarFornecedor(id);
    }
}
