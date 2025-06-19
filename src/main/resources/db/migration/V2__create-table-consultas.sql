CREATE TABLE consultas (
	id bigserial NOT NULL,
	paciente varchar NOT NULL,
	"data" timestamp NOT NULL,
	medico_id bigint NULL,
	CONSTRAINT consultas_pk PRIMARY KEY (id),
	CONSTRAINT consultas_medicos_fk FOREIGN KEY (medico_id) REFERENCES public.medicos(id)
);