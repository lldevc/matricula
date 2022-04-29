INSERT INTO usuario(id, nombre, numero_identificacion, email, ciudad, direccion, fecha_sancion) VALUES(1, 'test', 1111, 'test@test.com', 'testCiudad', 'testDireccion', '2022-10-18');
INSERT INTO usuario(id, nombre, numero_identificacion, email, ciudad, direccion, fecha_sancion) VALUES(2, 'test2', 1112, 'test2@test.com', 'testCiudad2', 'testDireccion2', null);
INSERT INTO usuario(id, nombre, numero_identificacion, email, ciudad, direccion, fecha_sancion) VALUES(3, 'test3', 1113, 'test3@test.com', 'testCiudad3', 'testDireccion3', null);


INSERT INTO programa (id, nombre, precio, recargo, dias_para_recargo) VALUES(1, 'Ingles', 700000, 0.2, 5);
INSERT INTO programa (id, nombre, precio, recargo, dias_para_recargo) VALUES(2, 'Frances', 850000, 0.2, 7);
INSERT INTO programa (id, nombre, precio, recargo, dias_para_recargo) VALUES(3, 'Aleman', 980000, 0.2, 9);


INSERT INTO matricula(id, valor, recargo, estado, fecha_creacion, fecha_limite_pago_sin_recargo, fecha_maxima_pago, programa_id, usuario_id)
VALUES(1000, 700000, false, 'PENDIENTE', '2022-04-01', '2022-04-09', '2022-04-18', 1, 1);

INSERT INTO matricula(id, valor, recargo, estado, fecha_creacion, fecha_limite_pago_sin_recargo, fecha_maxima_pago, programa_id, usuario_id)
VALUES(1001, 850000, false, 'PENDIENTE', '2022-04-06', '2022-04-15', '2022-04-20', 2, 2);

INSERT INTO matricula(id, valor, recargo, estado, fecha_creacion, fecha_limite_pago_sin_recargo, fecha_maxima_pago, programa_id, usuario_id)
VALUES(1002, 700000, false, 'PENDIENTE', '2022-04-13', '2022-04-20', '2022-04-27', 1, 3);