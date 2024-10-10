INSERT INTO usuario (nombre,apellido,email,telefono,direccion,rol,estado) VALUES('David', 'Maldonado', 'davidmp@gmail.com', 992836873, 'Calle Los Castaños 321, San Borja', 'Administrador', 'Activo');

INSERT INTO cochesemi (descripcion,marca,modelo,tipo,localizacion,precio) VALUES('Un sedán espacioso y eficiente, ideal para familias.', 'Nissan', 'Altima', 'Sedán','Lima, Perú', 18000);
INSERT INTO cochesemi (descripcion,marca,modelo,tipo,localizacion,precio) VALUES('SUV con bajo kilometraje (25,000 km), ideal para aventuras en familia o viajes por carretera.', 'Ford', 'Escape', 'SUV','Arequipa, Perú', 25000);
INSERT INTO cochesemi (descripcion,marca,modelo,tipo,localizacion,precio) VALUES('Coupé deportivo en estado impecable, con características de lujo y un rendimiento excepcional.', 'BMW', 'Serie 3', 'Deportivo','Piura, Perú', 30000);


INSERT INTO auditoria (id_registro, fecha, tabla, tipo, usuario) VALUES (1, NOW(), 'usuario', 'guardar', 'admin');



