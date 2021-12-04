## Hometask lecture 15

add mock test for jdbctemplate dao add mock test for datasource dao

## What is done :

1. CustomerDaoTest.class -> mock test for jdbctemplate;
2. ItemDaoTest.class -> mock test for datasource;
3. OrderDaoTest.class -> mock test for namedParameterJdbcTemplate;

## Technologies

1. Java version: 11;
2. Postgres database;
3. docker-compose;
4. Flyway;
5. Slf4j+logback;
6. Lombok

## How to use?

1. $mvn clean install
2. $docker-compose up -d
3. mvn flyway:migrate
4. Run Runner.class




