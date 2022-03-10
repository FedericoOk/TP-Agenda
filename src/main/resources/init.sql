-- Correr el contenedor
    -- docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=root MYSQL_DATABASE=agenda -d mysql:latest
-- Conectarse a la instancia
    -- docker exec -it mysql bash
-- Conectarse a la base de datos
    -- mysql -u root -proot

USE agenda;

CREATE TABLE personas(
    idPersona INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nombre varchar(100),
    telefono varchar(20)
);