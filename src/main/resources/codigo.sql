DROP DATABASE IF EXISTS MAGIC_WATER;

CREATE DATABASE MAGIC_WATER;
USE MAGIC_WATER;

CREATE TABLE usuario (
     nif varchar(9) primary key, -- Se usará como login.
     password varchar(200),
     activo tinyint,
     permiso varchar(25), -- SUPERVISOR o TRABAJADOR
     categoria varchar(30),
     nombre varchar(50),
     apellidos varchar(100),
     email varchar(150),
     tlf varchar(25)
);

CREATE TABLE proyecto (
    idproyecto int primary key,
    nombre varchar(200),
    descripcion varchar(1000),
    zona varchar(30),
    fecha date
);

CREATE TABLE tarea (
    idtarea int primary key,
    titulo varchar(200),
    descripcion varchar(1000),
    inicioprevisto date,
    finprevisto date,
    inicioreal date,
    finreal date,
    estado varchar(50),
    nif varchar(9),
    foreign key (nif) references usuario(nif),
    idproyecto int,
    foreign key (idproyecto) references proyecto(idproyecto)
);

INSERT INTO usuario (nif, password, activo, permiso, categoria, nombre, apellidos, email, tlf) VALUES
('12345678A', '$2a$10$qeYAp8x8IAifRwQ4skLDC.SGaSDcTWEsdcvWSc9qT/1vispN5DiJW', 1, 'SUPERVISOR', 'Programador', 'Álvaro', 'Barrena Revilla', 'alvaro.barrena@gmail.com', '666666666'),
('23456789B', '$2a$10$qeYAp8x8IAifRwQ4skLDC.SGaSDcTWEsdcvWSc9qT/1vispN5DiJW', 1, 'TRABAJADOR', 'Analista', 'Beatriz', 'Campos Moreno', 'beatriz.campos@example.com', '666777888'),
('34567890C', '$2a$10$qeYAp8x8IAifRwQ4skLDC.SGaSDcTWEsdcvWSc9qT/1vispN5DiJW', 1, 'TRABAJADOR', 'Diseñador', 'Carlos', 'Díaz López', 'carlos.diaz@example.com', '666888999'),
('45678901D', '$2a$10$qeYAp8x8IAifRwQ4skLDC.SGaSDcTWEsdcvWSc9qT/1vispN5DiJW', 1, 'SUPERVISOR', 'Gestor', 'Diana', 'Esteban Martín', 'diana.esteban@example.com', '666999000'),
('56789012E', '$2a$10$qeYAp8x8IAifRwQ4skLDC.SGaSDcTWEsdcvWSc9qT/1vispN5DiJW', 1, 'TRABAJADOR', 'Programador', 'Elena', 'Fuentes Núñez', 'elena.fuentes@example.com', '667000111'),
('67890123F', '$2a$10$qeYAp8x8IAifRwQ4skLDC.SGaSDcTWEsdcvWSc9qT/1vispN5DiJW', 1, 'TRABAJADOR', 'Tester', 'Fernando', 'García Ruiz', 'fernando.garcia@example.com', '667111222');


INSERT INTO proyecto (idproyecto, nombre, descripcion, zona, fecha) VALUES
(1, 'Magic Water', 'Desarrollo de aplicción web para MagicWater', 'Getafe', '2023-01-01'),
(2, 'Proyecto Agua Pura', 'Optimización del tratamiento de agua', 'Madrid', '2023-03-01'),
(3, 'Proyecto H2O', 'Estudio de nuevas fuentes de agua potable', 'Barcelona', '2023-04-01'),
(4, 'AquaTech', 'Desarrollo de tecnología para desalinización', 'Valencia', '2023-05-01'),
(5, 'WaterFlow', 'Sistema de gestión de distribución de agua', 'Sevilla', '2023-06-01'),
(6, 'EcoWater', 'Proyecto para la reducción de la huella hídrica', 'Bilbao', '2023-07-01');


INSERT INTO tarea (idtarea, titulo, descripcion, inicioprevisto, finprevisto, inicioreal, finreal, estado, nif, idproyecto) VALUES
(1, 'Configuración de seguridad', 'Manejar seguridad de Spring', '2023-01-01', '2023-02-01', '2023-01-01', '2023-02-01', 'En progreso', '12345678A', 1),
(2, 'Diseño de interfaz', 'Creación de UI/UX para la app', '2023-02-01', '2023-03-01', NULL, NULL, 'Pendiente', '23456789B', 2),
(3, 'Análisis de viabilidad', 'Estudio económico y técnico del proyecto', '2023-02-15', '2023-04-15', NULL, NULL, 'Pendiente', '34567890C', 3),
(4, 'Implementación de API', 'Desarrollo de backend y API', '2023-03-01', '2023-04-01', '2023-03-01', NULL, 'En progreso', '23456789B', 4),
(5, 'Pruebas de estrés', 'Ejecución de pruebas de carga y estrés en el sistema', '2023-04-01', '2023-05-01', NULL, NULL, 'Pendiente', '67890123F', 5),
(6, 'Documentación técnica', 'Elaboración de la documentación del proyecto', '2023-03-15', '2023-06-15', NULL, NULL, 'Finalizado', '56789012E', 6);