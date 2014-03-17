A Simple Open Source Java Cloud App Stack Demo Project with Guice 3.0, Hibernate 4, Jersey 1.x and Vaadin 7
=============================================================================================================

This is a proof of concept demo cloud app project with Cloud App Stack Demo Project with Guice 3.0, Hibernate 4, Jersey 1.x and Vaadin 7. It has:<br>

1. A simple PostgreSQL DB with ¡°subscriber¡± table <br>
2. Hibernate Mapping is used to map Subscriber Class to ¡°subscriber¡± table <br>
3. A simple ¡°Subscriber Web UI¡± is built on top of Vaadin 7 utilizing HbnContainer Add-on, it can be accessed at ¡°http://localhost:8080/[appName]/ui/¡± when running on a local Tomcat Server <br>
4. A simple ¡°Subscriber¡± REST Service is built on top of Jersey 1.18, it can be accessed at ¡°http://localhost:8080/[appName]/rest/subscriber/¡± when running on a local Tomcat Server <br>
5. All the components are wired together through Guice 3.0 with ServletModule and the project is built using Maven <br>

To run this demo project on your local machine with Tomcat 7 Server:<br>

1. Create a local Postgresql DB "querydslexample" under "postgres" user running on localhost port 5432 <br>
2. Execute "postgres_subscriber_table.sql" through either psql or pgAdmin to create "subscriber" table <br>
3. Run "mvn clean install" to build the whole projects <br>
4. Deploy the built war file to a local Tomcat Server running on port 8080 <br>
5. Accessing REST Subscriber Service at: http://localhost:8080/guice-hbn-jersey-vaadin/subscriber/1 <br>
6. Accessing Subscriber Web UI at: http://localhost:8080/guice-hbn-jersey-vaadin/ui/ <br>
<br>
If you want to know more about this simple Open Source Java Cloud App Stack, you can check my presentation at Slideshare at:<br>
   http://www.slideshare.net/jianwu/simple-opensource-javacloudappstack

