CREATE TABLE usuario (
  id INT NOT NULL auto_increment,
  nombre VARCHAR(45) NOT NULL,
  numero_identificacion INT NOT NULL UNIQUE,
  email VARCHAR(45) NOT NULL,
  ciudad VARCHAR(45) NOT NULL,
  direccion VARCHAR(45) NOT NULL,
  fecha_sancion DATETIME NULL,
  PRIMARY KEY (id));


CREATE TABLE programa (
  id INT NOT NULL auto_increment,
  nombre VARCHAR(45) NOT NULL,
  precio DOUBLE NOT NULL,
  recargo DOUBLE NOT NULL,
  dias_para_recargo INT NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE matricula (
  id INT NOT NULL auto_increment,
  valor DOUBLE NULL,
  recargo BOOLEAN NULL,
  estado VARCHAR(45) NULL,
  fecha_creacion DATETIME NULL,
  fecha_limite_pago_sin_recargo DATETIME NULL,
  fecha_maxima_pago DATETIME NULL,
  programa_id INT NULL,
  usuario_id INT NULL,
  PRIMARY KEY (id));

ALTER TABLE matricula
ADD CONSTRAINT matricula_programa
    FOREIGN KEY (programa_id)
    REFERENCES programa (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;

ALTER TABLE matricula
ADD CONSTRAINT matricula_usuario
  FOREIGN KEY (usuario_id)
  REFERENCES usuario (id)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;