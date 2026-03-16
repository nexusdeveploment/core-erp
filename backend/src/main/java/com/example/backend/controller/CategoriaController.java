package com.example.backend.controller;

import com.example.backend.entity.Categoria;
import com.example.backend.service.CategoriaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<Categoria> listarCategorias() {
        return categoriaService.listarCategorias();
    }

    @PostMapping
    public Categoria criarCategoria(@RequestBody Categoria categoria) {
        return categoriaService.criarCategoria(categoria);
    }
}
