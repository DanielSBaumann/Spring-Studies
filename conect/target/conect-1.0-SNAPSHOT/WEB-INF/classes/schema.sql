CREATE TABLE cliente(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    cpf VARCHAR(11)
);

CREATE TABLE produto(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(100),
    preco_unitario NUMERIC(20,2)
);

CREATE TABLE pedido(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    client_id INTEGER REFERENCES cliente (id),
    data_pedido TIMESTAMP,
    status VARCHAR(20),
    total NUMERIC(20,2)
);

CREATE TABLE item_pedido(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    pedido_id INTEGER REFERENCES pedido (id),
    produto_id INTEGER REFERENCES produto (id),
    quantidade INTEGER
);

CREATE TABLE usuario(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    login VARCHAr(50) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    admin BOOL DEFAULT FALSE
);