CREATE TABLE medicos (
	id bigserial NOT NULL,
	nome varchar NOT NULL,
	email varchar NOT NULL,
	telefone varchar NOT NULL,
	crm varchar NOT NULL,
	especialidade varchar NOT NULL,
	CONSTRAINT medicos_pk PRIMARY KEY (id),
	CONSTRAINT medicos_unique UNIQUE (email),
	CONSTRAINT medicos_unique_1 UNIQUE (crm)
);
