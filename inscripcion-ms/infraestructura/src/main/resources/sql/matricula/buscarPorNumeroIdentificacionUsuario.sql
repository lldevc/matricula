SELECT m.*, p.id as id_programa, p.nombre as nombre_programa, p.precio as precio_programa,
p.recargo as recargo_programa, p.dias_para_recargo as dias_para_recargo_programa, 
u.id as id_usuario, u.nombre as nombre_usuario, u.numero_identificacion as numero_identidicacion_usuario,
u.email as email_usuario, u.ciudad as ciudad_usuario, u.direccion as direccion_usuario, u.fecha_sancion as fecha_sancion_usuario
FROM matricula as m INNER JOIN programa as p ON (m.programa_id = p.id)
INNER JOIN usuario as u ON (m.usuario_id = u.id) WHERE u.numero_identificacion = :id;