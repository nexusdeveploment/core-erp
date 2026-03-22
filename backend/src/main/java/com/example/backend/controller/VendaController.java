package com.example.backend.controller;

import com.example.backend.entity.ItemVenda;
import com.example.backend.entity.Venda;
import com.example.backend.service.VendaService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/vendas")
@CrossOrigin
public class VendaController {

    private final VendaService vendaService;

    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @PostMapping
    public Venda criarVenda(
            @RequestBody List<ItemVenda> itens,
            @RequestParam(required = false) BigDecimal desconto) {

        return vendaService.criarVenda(itens, desconto);
    }

    @GetMapping
    public List<Venda> listar() {
        return vendaService.listarVendas();
    }

    @GetMapping("/{id}")
    public Venda buscar(@PathVariable Long id) {
        return vendaService.buscarPorId(id);
    }
}