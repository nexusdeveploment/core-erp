create database core_db;

use core_db;

create table usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(80),
    email VARCHAR(80) UNIQUE,
    senha VARCHAR(100),
    empresa VARCHAR(100),
    tipo VARCHAR(25)
);

CREATE TABLE produtos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100),
    codigo VARCHAR(50),
    preco DECIMAL(10,2),
    quantidade INT,
    estoque_minimo INT
);

CREATE TABLE movimentacoes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    produto_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    quantidade INT NOT NULL,
    data_movimentacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_produto
        FOREIGN KEY (produto_id)
        REFERENCES produtos(id),

    CONSTRAINT fk_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuarios(id)
);

SELECT * FROM usuarios;

CREATE TABLE fornecedores (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cnpj VARCHAR(20),
    telefone VARCHAR(20),
    email VARCHAR(100)
);

ALTER TABLE produtos
ADD fornecedor_id BIGINT;

ALTER TABLE produtos
ADD CONSTRAINT fk_produto_fornecedor
FOREIGN KEY (fornecedor_id)
REFERENCES fornecedores(id);

CREATE TABLE categorias (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

ALTER TABLE produtos
ADD categoria_id BIGINT;

ALTER TABLE produtos
ADD CONSTRAINT fk_produto_categoria
FOREIGN KEY (categoria_id)
REFERENCES categorias(id);







