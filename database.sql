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

create table venda (
    id_venda BIGINT AUTO_INCREMENT PRIMARY KEY,
    data_venda timestamp,
    valor_total decimal,
    id_usuario BIGINT NOT NULL,
    status varchar(20),

    CONSTRAINT fk_uservenda
        FOREIGN KEY (id_usuario)
        REFERENCES usuarios(id)
);

alter table venda add (
subtotal DECIMAL(10,2),
desconto DECIMAL(10,2)
);

create table item_venda(
    id_itemvenda BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_venda BIGINT NOT NULL,
    id_produto BIGINT NOT NULL,
    quantidade int,
    preco_unitario decimal,
    subtotal decimal,

    constraint fk_idvenda
    foreign key(id_venda)
    references venda(id_venda),

    constraint fk_produtodavenda
    foreign key(id_produto)
    references produtos(id)
);

DROP TABLE item_venda;
DROP TABLE venda;






