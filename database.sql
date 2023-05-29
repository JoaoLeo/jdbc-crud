/* CREATE DATABASE coursejdbc -- rodar para criar o banco */

USE coursejdbc

CREATE TABLE departamento (
                            Id int(11) NOT NULL AUTO_INCREMENT,
                            Nome varchar(60) DEFAULT NULL,
                            PRIMARY KEY (Id)
);

CREATE TABLE vendedor (
                        Id int(11) NOT NULL AUTO_INCREMENT,
                        Nome varchar(60) NOT NULL,
                        Email varchar(100) NOT NULL,
                        DtAniversario datetime NOT NULL,
                        salarioBase double NOT NULL,
                        DepartamentoId int(11) NOT NULL,
                        PRIMARY KEY (Id),
                        FOREIGN KEY (DepartamentoId) REFERENCES departamento (id)
);