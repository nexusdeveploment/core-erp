package com.example.backend.service;

import com.example.backend.entity.*;
import com.example.backend.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemVendaRepository itemVendaRepository;

    public VendaService(VendaRepository vendaRepository,
                        ProdutoRepository produtoRepository,
                        ItemVendaRepository itemVendaRepository) {
        this.vendaRepository = vendaRepository;
        this.produtoRepository = produtoRepository;
        this.itemVendaRepository = itemVendaRepository;
    }

    @Transactional
    public Venda criarVenda(List<ItemVenda> itens, BigDecimal desconto) {

        BigDecimal subtotal = BigDecimal.ZERO;
        List<ItemVenda> itensProcessados = new ArrayList<>();

        for (ItemVenda item : itens) {

            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            // VALIDAR ESTOQUE
            if (produto.getQuantidade() < item.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente para: " + produto.getNome());
            }

            BigDecimal preco = produto.getPreco();

            BigDecimal quantidade = BigDecimal.valueOf(item.getQuantidade());

            BigDecimal subtotalItem = preco.multiply(quantidade);

            // ATUALIZAR ITEM
            item.setPrecoUnitario(preco);
            item.setSubtotal(subtotalItem);

            subtotal = subtotal.add(subtotalItem);

            // DIMINUIR ESTOQUE
            produto.setQuantidade(produto.getQuantidade() - item.getQuantidade());
            produtoRepository.save(produto);

            itensProcessados.add(item);
        }

        // VALIDAR DESCONTO
        if (desconto == null) {
            desconto = BigDecimal.ZERO;
        }

        if (desconto.compareTo(subtotal) > 0) {
            throw new RuntimeException("Desconto não pode ser maior que o total");
        }

        BigDecimal total = subtotal.subtract(desconto);

        Venda venda = new Venda();
        venda.setDataVenda(LocalDateTime.now());
        venda.setSubtotal(subtotal);
        venda.setDesconto(desconto);
        venda.setValorTotal(total);
        venda.setStatus("PAGO");

        Venda vendaSalva = vendaRepository.save(venda);

        // SALVAR ITENS
        for (ItemVenda item : itensProcessados) {
            item.setVenda(vendaSalva);
            itemVendaRepository.save(item);
        }

        return vendaSalva;
    }

    public List<Venda> listarVendas() {
        return vendaRepository.findAll();
    }

    public Venda buscarPorId(Long id) {
        return vendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));
    }
}