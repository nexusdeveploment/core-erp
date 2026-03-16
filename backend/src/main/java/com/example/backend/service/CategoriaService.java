package com.example.backend.service;

import com.example.backend.entity.Categoria;
import com.example.backend.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria criarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
}
