update usuario
set nombre = :nombre,
	email = :email,
	ciudad = :ciudad,
	direccion = :direccion,
	fecha_sancion = :fechaSancion
where id = :id