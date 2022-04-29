CREATE TABLE item (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	tipo SMALLINT NOT NULL,
	codigo VARCHAR(10) NOT NULL,
	descricao VARCHAR(50) NOT NULL,
	data_cadastro DATE NOT NULL,
	ativo BOOLEAN NOT NULL,
	abc VARCHAR(1) NOT NULL,
	preco DECIMAL(10,2) NOT NULL,
	CONSTRAINT uk_item_tipo_codigo UNIQUE (tipo, codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO item (tipo, codigo, descricao, data_cadastro, ativo, abc, preco) 
VALUES (0, '001', 'Leite', '2022-01-01', true, 'A', 4.55);

INSERT INTO item (tipo, codigo, descricao, data_cadastro, ativo, abc, preco) 
VALUES (1, '002', 'Bolo de Chocolate', '2022-02-01', true, 'B', 30.76);

INSERT INTO item (tipo, codigo, descricao, data_cadastro, ativo, abc, preco) 
VALUES (2, '003', 'Alicate', '2022-03-01', false, 'A', 13.43);