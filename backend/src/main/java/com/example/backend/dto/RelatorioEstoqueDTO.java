package com.example.backend.dto;

import java.math.BigDecimal;

public class RelatorioEstoqueDTO {

    private String produto;
    private Integer quantidade;
    private BigDecimal preco;
    private BigDecimal valorTotal;
    private String fornecedor;
    private String categoria;

    public RelatorioEstoqueDTO(String produto, Integer quantidade, BigDecimal preco,
                               BigDecimal valorTotal, String fornecedor, String categoria) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = preco;
        this.valorTotal = valorTotal;
        this.fornecedor = fornecedor;
        this.categoria = categoria;
    }

    public String getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public String getCategoria() {
        return categoria;
    }
}