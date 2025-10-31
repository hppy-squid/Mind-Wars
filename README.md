# Mind-Wars

Deployad app - https://classy-fox-c48e7a.netlify.app/

Inlämningsuppgift extra allt

Detta är backend-delen av Mind War-applikationen. Backend är byggd med Spring Boot, och JPA/Hibernate. API:et tillhandahåller endpoints för  CRUD-operationer för användare, gåtor samt en live chat via Websocket. 

## Funktioner
* Inloggning & registrering 
* Gåtor genererade via OpenAi
* Live chat via websocket
* Persistens via JPA/Hibernate till SQL-databas
## Teknologier
* Java 21
* Spring Boot
* JPA/Hibernate
* Maven
## Kom igång
1. Klona repot
````
git clone <backend-repo-url>
cd mind-wars
````
2. Konfigurera databasen i application.properties ( MySQL).

**application.properties**
````
spring.datasource.url=jdbc:mysql://localhost:3306/mindwar
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
````
3. Kör projektet
````
mvn spring-boot:run
````
API:et finns nu på:

http://localhost:8080


