# DesafioPerc
App builded as independent Microservice, with Postgres 17, Java 21 and Spring Boot 3 as base.

- Main url: https://desafioPerc-mdveloper.onrender.com/

- Swagger: https://desafioPerc-mdveloper.onrender.com/swagger-ui/index.html#/
--------------------------------------------------------------------------------------------------------

// Pre-Requierements: 
==========================

[Docker](https://www.docker.com/get-started/)  (Optional)

[Oracle Java JDK 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)

[Postgres 17](https://www.postgresql.org/download/)

- After have Postgres installed:
```bash
Execute .sql DDL files into it in order to create tables. (Can be done through cmd or any software manager like mysql Workbench, dbeaver, heidi,etc
```

- After have installed Java JDK 21:
```bash
1) Find your java jdk BIN folder (normally: C:\Program Files\Java\jdk-21\bin)
2) After know where is your java jdk 21 installed, go to the root folder of the project, you have a batch file called: "Start App", open with any text editor
3) Replace the line: PATH = "C:\Program Files\Java\jdk-21\bin" with yours.
```

- Now edit config file:
```bash
1) Go to: "ms-db\src\main\resources" and open file: "application-test.properties"
2) Inside this file need edit "EXAMPLE" word with your info:

DBCP_MSDB_URL=jdbc:mysql://localhost:3306/EXAMPLE?useSSL=false&serverTimezone=UTC-3
DBCP_MSDB_USER=EXAMPLE
DBCP_MSDB_PASS=EXAMPLE
```

- Start the application:
```bash
Go to the root folder again and double click on "Start App" batch file.
```

--------------------------------------------------------------------------------------------------------

// Extras implemented/done:
==========================
- Some extra endpoints to generate more data randomly
- Postman Collection adjunted
- Unit test (simple and basic to cover controller and services)
- Checkstyle
- Pre-load database wipe+random data for fast test

--------------------------------------------------------------------------------------------------------

// Endpoints: 
==========================
```c
# type: [GET]
// Endpoint used to get the full total distribution of comitentes by country and market
/comitentes/stats

# type: [GET]
// This endpoint was used to test performance between send a Map and json auto-serialize it. While "stats" EP map it manually and then send it,
// when was using LAZY method on relation, with more than 10k comitentes start see 10-15ms ~ less response time with this method, now using FetchType EAGER no difference.
// PS: EAGER bcoz we always need all info from both tables.
/comitentes/stats1

# type: [GET]
// A extra endpoint to create fast and easy new Meracdos with random info inside.
/comitentes/createMercados?count=10

# type: [GET]
// A extra endpoint to create fast and easy new Comitentes with random info and asign them to a random Mercado
/comitentes/createComitentes?count=100
```

--------------------------------------------------------------------------------------------------------

// Muestras de performance:
==========================

```bash
General concepts and information:
Deploy its at US, Database at Canada server) so the path would be:
	current test PC -> Our deployment at "Render" US-EAST -> DB at Canada -> Back to deployment -> Back to current test PC
```

- JMeter:
```bash
Inside "Tests/performance/Reports" folder have local and deploy test done using JMeter.
This performance test with JMeter was done to stress test the app with 1.100 request per second.

Local test with db: tolerance time was shorter to test if app works fine. Set to 800ms
Deploy test with db: based on "normal answers time" sum of all + JMeter proccess, I set tolerance time to 45000ms
Deploy test without db: tolerance time to 1000ms
```

- Postman
```bash
Inside "tests/Postman" you have the collection export to test it too and check full response time from postman
```

- Server-console
```bash
Inside "tests/performance" you have local-application.txt file with some non-relevant info bcoz change due to PC specifications,etc.
```

--------------------------------------------------------------------------------------------------------
