package com.example.backend.dto;

public class RelatorioEstoqueDTO {

    private String produto;
    private Integer quantidade;
    private Double preco;
    private Double valorTotal;
    private String fornecedor;
    private String categoria;

    public RelatorioEstoqueDTO(String produto, Integer quantidade, Double preco,
                               Double valorTotal, String fornecedor, String categoria) {
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

    public Double getPreco() {
        return preco;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public String getCategoria() {
        return categoria;
    }
}