CREATE TABLE usuario (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	senha VARCHAR(150) NOT NULL,
	CONSTRAINT uk_usuario_nome UNIQUE (nome),
	CONSTRAINT uk_usuario_email UNIQUE (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE perfil (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	CONSTRAINT uk_perfil_nome UNIQUE (nome)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario_perfil (
	id_usuario BIGINT(20) NOT NULL,
	id_perfil BIGINT(20) NOT NULL,
	PRIMARY KEY (id_usuario, id_perfil),
	FOREIGN KEY (id_usuario) REFERENCES usuario(id),
	FOREIGN KEY (id_perfil) REFERENCES perfil(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuario (nome, email, senha) 
VALUES ('Administrador', 'administrador@email.com.br', '$2a$10$9NBMiVYe./TMCds17ihkWuLhjBIssUgHC3crWFrFANPgXipj46xvW');

INSERT INTO usuario (nome, email, senha) 
VALUES ('Usuario', 'usuario@email.com.br', '$2a$10$EmIzff0I3v7pU4KcjYqFfumT6DmdnlTkl7n4bgPlxQPVtEVGEIJgG');

INSERT INTO perfil (nome) VALUES ('ROLE_ADMINISTRADOR');

INSERT INTO perfil (nome) VALUES ('ROLE_USUARIO');

INSERT INTO usuario_perfil (id_usuario, id_perfil) VALUES (1, 1);

INSERT INTO usuario_perfil (id_usuario, id_perfil) VALUES (2, 2);