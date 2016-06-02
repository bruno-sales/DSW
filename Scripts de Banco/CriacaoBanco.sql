DROP DATABASE IF EXISTS bolsa_gogo;

CREATE DATABASE bolsa_gogo;

USE bolsa_gogo;

CREATE TABLE DebugMessages
(
	message VARCHAR(255) NOT NULL
);

CREATE TABLE usuarios 
(
	idUsuario int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nome varchar(80) NOT NULL,
	telefone varchar(20) NOT NULL,
	cpf varchar(14) NOT NULL,
	email varchar(40) NOT NULL,
	senha varchar(255) NOT NULL,
	foto BLOB,
	administrator boolean NOT NULL,
	numeroLogins int NOT NULL DEFAULT 0,
	ultimoLogin datetime,
	
	UNIQUE(email),
	UNIQUE(cpf)
);

CREATE TABLE tokens 
(
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	idUsuario int NOT NULL,
	token varchar(255) NOT NULL,
	validade datetime NOT NULL,

	CONSTRAINT FOREIGN KEY (idUsuario) REFERENCES usuarios(id) ON DELETE CASCADE
);

CREATE TABLE transferencias 
(
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	idUsuario int NOT NULL,
	data datetime NOT NULL,
	numeroBanco varchar(3) NOT NULL,
	numeroAgencia varchar(6) NOT NULL,
	numeroConta varchar(10) NOT NULL,
	valor float NOT NULL,

	CONSTRAINT FOREIGN KEY (idUsuario) REFERENCES usuarios(id)
);

CREATE TABLE personagens
(
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nome varchar(40) NOT NULL,

	UNIQUE (nome)
);

CREATE TABLE lancamentosPersonagem
(
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	idPersonagem int NOT NULL,
	idUsuario int NOT NULL,
	data datetime NOT NULL,
	historico varchar(40) NOT NULL,
	quantidade int NOT NULL,
	precoUnitario float NOT NULL,
	operacao int NOT NULL,

	CONSTRAINT FOREIGN KEY (idUsuario) REFERENCES usuarios(id),
	CONSTRAINT FOREIGN KEY (idPersonagem) REFERENCES personagens(id)
);

CREATE TABLE lancamentosDinheiro
(
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	idUsuario int NOT NULL,
	data datetime NOT NULL,
	historico varchar(40) NOT NULL,
	valor float NOT NULL,
	operacao int NOT NULL,

	CONSTRAINT FOREIGN KEY (idUsuario) REFERENCES usuarios(id)
);

CREATE TABLE ofertas
(
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	tipo int NOT NULL,
	idPersonagem int NOT NULL,
	idUsuario int NOT NULL,
	data datetime NOT NULL,
	quantidade float NOT NULL,
	quantidadeOriginal float NOT NULL,
	precoUnitario float NOT NULL,
	status int NOT NULL,
	idOrdemOriginal int,

	CONSTRAINT FOREIGN KEY (idUsuario) REFERENCES usuarios(id),
	CONSTRAINT FOREIGN KEY (idPersonagem) REFERENCES personagens(id),
	CONSTRAINT FOREIGN KEY (idOrdemOriginal) REFERENCES ofertas(id)
);

CREATE TABLE casamentosOferta
(
	idOfertaVenda int NOT NULL,
	idOfertaCompra int NOT NULL,
	dataExecucao datetime NOT NULL,

	CONSTRAINT FOREIGN KEY (idOfertaVenda) REFERENCES ofertas(id),
	CONSTRAINT FOREIGN KEY (idOfertaCompra) REFERENCES ofertas(id)
);