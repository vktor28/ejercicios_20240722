
-- 5.1- Obtener los libros publicados después de 2005
SELECT * FROM libreria.libros WHERE year>2005;

-- 5.2- Obtener los clientes cuyo nombre contiene la letra “e”
SELECT * FROM libreria.clientes WHERE nombre LIKE '%e%' ;

-- 5.3- Contar el número total de libros
SELECT COUNT(*) as "Número Total Libros" FROM libreria.libros;

-- 5.4- ¿Cuál es el título del libro con el ID 3?
SELECT titulo FROM libreria.libros WHERE id=3;

-- 5.5- ¿Cuántos clientes tienen una dirección de correo electrónico que termina en “@gmail.com”?
SELECT COUNT(*) as "Clientes correo Gmail" FROM libreria.clientes WHERE email LIKE '%@gmail.com';

-- 5.6- ¿Cuál es el título del libro más antiguo?
SELECT titulo FROM libreria.libros WHERE year = (SELECT MIN(YEAR) FROM libreria.libros);

-- 5.7- JOIN: Obtener los libros junto con los nombres de los clientes que los poseen
SELECT * FROM libreria.libros JOIN  libreria.clientes ON libros.clienteId=clientes.idcliente;

-- 5.8- JOIN: Obtener los clientes que no tienen libros
SELECT * FROM libreria.clientes LEFT JOIN libreria.libros ON libros.clienteId=clientes.idcliente WHERE libros.id IS NULL;