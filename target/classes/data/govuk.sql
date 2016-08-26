/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  chrishovey
 * Created: Aug 25, 2016

 */

CREATE TABLE candidate (
    id SERIAL,
    name character varying NOT NULL,
    dob date NOT NULL,
    experience character varying NOT NULL
);

INSERT INTO candidate (name, dob, experience) VALUES('John Smith', '01/10/1978', 'Spring, Hibernate, Java');
INSERT INTO candidate (name, dob, experience) VALUES ('Jo Bloggs', '02/15/1988', 'Postgres, JavaScript, node.js');
INSERT INTO candidate (name, dob, experience) VALUES ('Dave Bloggs', '05/17/1990', 'MongoDB, Vertx.io');
INSERT INTO candidate (name, dob, experience) VALUES ('Laura Paine', '06/11/1988', 'Java, ActiveMQ, Redis');