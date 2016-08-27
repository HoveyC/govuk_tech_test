# GovUK-Tech-Test
========

Instructions
------------

- Postgres 9.0.0 + is required to run program

To create database once postgres is installed:

    createdb -U postgres homeoffice
    
The schema and test data is located in /govuk_tech_test/src/main/resources/data/govuk.sql.
to import the schema and test data run:

    psql -U postgres homeoffice < govuk.sql

- Test/Build :

To test the project and create the fatjar, javadocs, javasource packages:

    mvn -Prelease-profile clean install
    
- Run :

To run the project, build it first and navigate to target directory and execute:

    java -jar GovUK-Tech-Test-1.0-SNAPSHOT-fat.jar

The following indicates the program is now running:

    INFO: Succeeded in deploying verticle

Developer
-----------
Chris Hovey
