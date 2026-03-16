package com.example.backend.dto;

public class DashboardDTO {

    private Long totalProdutos;
    private Long produtosEstoqueBaixo;
    private Long movimentacoesHoje;
    private Double valorTotalEstoque;

    public DashboardDTO(Long totalProdutos, Long produtosEstoqueBaixo, Long movimentacoesHoje, Double valorTotalEstoque) {
        this.totalProdutos = totalProdutos;
        this.produtosEstoqueBaixo = produtosEstoqueBaixo;
        this.movimentacoesHoje = movimentacoesHoje;
        this.valorTotalEstoque = valorTotalEstoque;
    }


    public Long getTotalProdutos() {
        return totalProdutos;
    }

    public Long getProdutosEstoqueBaixo() {
        return produtosEstoqueBaixo;
    }

    public Long getMovimentacoesHoje() {
        return movimentacoesHoje;
    }

    public Double getValorTotalEstoque() {
        return valorTotalEstoque;
    }

}
