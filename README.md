Geocoder
============

Geocode using a PostgreSQL database that uses the PostGIS extension and TIGER data. This project adds functionality to an existing Java application, but can be run on it's own [an example JUnit test exists in the src/main/test directory](src/test/java/net/ljcomputing/geocoder/service/impl/GeocoderServiceImplTest.java).

Requirements:
---------------

1. [PostgreSQL](http://postgresql.org/)
2. [PostGIS extension for PostgreSQL](https://postgis.net/)
3. [Java JDK - version 8](https://java.com/en/download/faq/develop.xml)
4. [Maven](https://maven.apache.org/)

Building:
---------------
1. mvn clean compile

Preparation:
-------------
You must update the [application.properties](/src/test/resources/application.properties) file before running the JUnit test(s).

```
logging.config=file\:./src/test/resources/logback.xml

spring.datasource.url= jdbc:postgresql://HOST:PORT/DATABASE
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.username=USERNAME GOES HERE
spring.datasource.password=PASSWORD GOES HERE

spring.jpa.database-platform=org.hibernate.spatial.dialect.postgis.PostgisDialect
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
```

Testing:
---------------
1. mvn clean compile test

Inclusion with existing Java application:
-----------------------------------------
[The project is available using JitPack](https://jitpack.io/#willmorejg/net.ljcomputing.geocoder/)


Resources:
1. [https://experimentalcraft.wordpress.com/2017/11/01/how-to-make-a-postgis-tiger-geocoder-in-less-than-5-days/](https://experimentalcraft.wordpress.com/2017/11/01/how-to-make-a-postgis-tiger-geocoder-in-less-than-5-days/)
2. [https://blog.trigent.com/eight-brutally-efficient-steps-to-geo-code-reverse-geo-code-with-tiger-db/](https://blog.trigent.com/eight-brutally-efficient-steps-to-geo-code-reverse-geo-code-with-tiger-db/)

