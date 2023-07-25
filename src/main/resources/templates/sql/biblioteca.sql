
-- Base de datos: `Biblioteca (cliente libro autor prestamo)`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `autor`
--

CREATE TABLE `autor` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `alta` bit(1) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `autor`
--

INSERT INTO `autor` (`id`, `alta`, `nombre`) VALUES
(8000, b'0', 'gabriel garcia marquez');

INSERT INTO `autor` (`alta`, `nombre`) VALUES
(b'0', 'roberto arlt cambiado'),
(b'0', 'miguel de servantes saavedra'),
(b'0', 'jose de los  ortega gasset'),
(b'1', 'julio verne,'),
(b'1', 'gabriel garcia marquez'),
(b'0', 'robert fisher'),
(b'1', 'tolkien'),
(b'1', 'robert fisher');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `alta` bit(1) NOT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `dni` bigint NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id`, `alta`, `apellido`, `dni`, `nombre`, `telefono`) VALUES
(3001, b'1', 'martinez', 7896555, 'josé manuel', '1233222');
INSERT INTO `cliente` (`alta`, `apellido`, `dni`, `nombre`, `telefono`) VALUES

(b'1', 'cambiado', 7896560, 'laurent serra', '1233227'),
(b'1', 'ortiz', 7896549, 'juan carlos', '1233216'),
(b'1', 'maradona', 198645, 'diego', '26145678'),
(b'1', 'gardening', 55578845, 'esteban', '12765521'),
(b'1', 'magaña', 7896544, 'marcos', '1233211'),
(b'1', 'lópez', 7896545, 'ruben', '1233212'),
(b'1', 'soria', 7896546, 'alberto', '1233213'),
(b'1', 'solís', 7896547, 'maria', '1233214'),
(b'1', 'rosas', 7896548, 'felipe', '1233215'),
(b'1', 'soria', 7896550, 'carlos', '1233217'),
(b'1', 'lópez', 7896551, 'mariano', '1233218'),
(b'1', 'rodriguez', 7896553, 'hilario', '1233220'),
(b'1', 'magaña', 7896554, 'emmanuel', '1233221'),
(b'1', 'palma', 7896556, 'david', '1233223'),
(b'1', 'palma', 7896557, 'oscar', '1233224'),
(b'1', 'fignon', 7896558, 'francois', '1233225'),
(b'1', 'narvaez', 7896559, 'lionel', '1233226'),
(b'1', 'bolton', 7896561, 'michael', '1233228'),
(b'1', 'sanchez', 7896562, 'hilary', '1233229'),
(b'1', 'martinez', 7896555, 'josé manuel', '1233222'),
(b'1', 'ortiz', 7896549, 'juan carlos', '1233216'),
(b'1', 'maradona', 198645, 'diego', '26145678'),
(b'1', 'gardening', 55578845, 'esteban', '12765521'),
(b'1', 'magaña', 7896544, 'marcos', '1233211'),
(b'1', 'lópez', 7896545, 'ruben', '1233212'),
(b'1', 'soria', 7896546, 'alberto', '1233213'),
(b'1', 'solís', 7896547, 'maria', '1233214'),
(b'1', 'rosas', 7896548, 'felipe', '1233215'),
(b'1', 'soria', 7896550, 'carlos', '1233217'),
(b'1', 'lópez', 7896551, 'mariano', '1233218'),
(b'1', 'rodriguez', 7896553, 'hilario', '1233220'),
(b'1', 'palma', 7896556, 'david', '1233223'),
(b'1', 'palma', 7896557, 'oscar', '1233224'),
(b'1', 'fignon', 7896558, 'francois', '1233225'),
(b'1', 'narvaez', 7896559, 'lionel', '1233226'),
(b'1', 'sanchez', 7896562, 'hilary', '1233229'),
(b'1', 'zaldivar', 123345, 'pedro', '43434343434');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `editorial`
--

CREATE TABLE `editorial` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `alta` bit(1) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `editorial`
--

INSERT INTO `editorial` (`id`, `alta`, `nombre`) VALUES
(5000, b'0', 'editoria sudamericana');

INSERT INTO `editorial` (`alta`, `nombre`) VALUES

(b'0', 'enrique s. rueda'),
(b'1', 'andres bello'),
(b'0', 'salvat'),
(b'1', 'malva sdfsdfsdf'),
(b'1', 'obelisco'),
(b'1', 'minotauro'),
(b'1', 'pacho'),
(b'1', 'LUIS ESPINOZA'),
(b'1', 'malena ramos'),
(b'1', 'LUIS ALBERTO'),
(b'0', 'nueva editorial'),
(b'1', 'nuevo editorial 123'),
(b'1', 'mansaEditorial'),
(b'0', 'nuevaeditorial 852'),
(b'0', 'new editorial'),
(b'1', 'pacho'),
(b'1', 'asdasd');

--
-- Estructura de tabla para la tabla `libro`
--

CREATE TABLE `libro` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `alta` bit(1) DEFAULT NULL,
  `anio` date DEFAULT NULL,
  `ejemplares` int DEFAULT NULL,
  `ejemplares_prestados` int DEFAULT NULL,
  `ejemplares_restantes` int DEFAULT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `autor_fk` bigint DEFAULT NULL,
  `editorial_fk` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`editorial_fk`) REFERENCES `editorial` (`id`),
  FOREIGN KEY (`autor_fk`) REFERENCES `autor` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `libro`
--

INSERT INTO `libro` (`id`, `alta`, `anio`, `ejemplares`, `ejemplares_prestados`, `ejemplares_restantes`, `isbn`, `titulo`, `autor_fk`, `editorial_fk`) VALUES
(1001, b'0', '1995-02-01', 100, 0, 100, '9789500726078', 'cronica de una muerte anunciada', 8000, 5000);

INSERT INTO `libro` (`alta`, `anio`, `ejemplares`, `ejemplares_prestados`, `ejemplares_restantes`, `isbn`, `titulo`, `autor_fk`, `editorial_fk`) VALUES

(b'1', '1983-10-01', 102, 2, 100, '9561315777', 'don quijote de la mancha', 8002, 5000),
(b'1', '1970-01-01', 103, 3, 100, '9788441403215', 'el espectador', 8003, 5005),
(b'1', '1111-11-11', 104, 4, 100, '9789871093557', 'la vuelta al mundo en 80 dias', 8004, 5003),
(b'1', '1981-01-01', 105, 5, 100, '9500705516', 'el general en su laberinto', 8000, 5000),
(b'1', '2010-01-01', 106, 6, 100, '9789508200273', 'el regreso del caballero de la armadura oxidada', 8006, 5006),
(b'1', '2014-01-01', 107, 7, 100, '9789505470679', 'la comunidad del anillo', 8007, 5006),
(b'0', '2023-02-21', 126, 46, 80, '123654444444', 'las mil y una', 8003, 5005),
(b'1', '1111-01-01', 250, 0, 250, '125', 'la bibla', 8003, 5001),
(b'1', '1981-11-14', 150, 0, 150, '141414', 'diccionario ingles', 8005, 5008),
(b'0', '1647-12-25', 1600, 0, 1600, '33333333', 'diccioonario', 8007, 5001),
(b'0', '1981-11-14', 200, 0, 200, '123433', 'java lo mejor', 8002, 5001);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestamo`
--

CREATE TABLE `prestamo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `alta` bit(1) NOT NULL,
  `devolucion` date DEFAULT NULL,
  `prestamo` date DEFAULT NULL,
  `cliente_id` bigint DEFAULT NULL,
  `libro_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`libro_id`) REFERENCES `libro` (`id`),
  FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `prestamo`
--

INSERT INTO `prestamo` (`id`, `alta`, `devolucion`, `prestamo`, `cliente_id`, `libro_id`) VALUES
(6000, b'1', '2023-04-22', '2023-02-22', 3003, 1002);

INSERT INTO `prestamo` (`alta`, `devolucion`, `prestamo`, `cliente_id`, `libro_id`) VALUES

(b'1', '2023-03-02', '2023-02-26', 3005, 1005),
(b'1', '2023-03-11', '2023-02-26', 3024, 1005),
(b'1', '2023-02-28', '2023-02-26', 3011, 1011);

