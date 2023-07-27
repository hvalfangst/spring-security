# JWT using Kotlin with Spring Boot 


## Requirements

* x86-64
* JDK 17
* Keytool
* Linux
* Docker
* Kubernetes


## Startup

The script "up" starts the application by executing the following:
```
1. docker-compose -f db/docker-compose.yml up -d
2. mvn clean install
3. mvn spring-boot:run
```


## Shutdown

The script "down" wipes the database executing the following:
```
1. docker-compose -f db/docker-compose.yml down
```


## HTTP Endpoints

The endpoints under mapping "api/users" does not require any authentication

The endpoint "api/heroes/create" requires the role "HEROES_WRITE" 

The endpoint "api/heroes/list/{USER_ID}" requires the role "HEROES_READ"

            it.requestMatchers("/api/users/**").permitAll()
            it.requestMatchers("/api/heroes/create").hasAnyAuthority("HEROES_WRITE")
            it.requestMatchers("/api/heroes/list/**").hasAnyAuthority("HEROES_READ")

### Users

POST http://localhost:8080/api/users/create 

```json
{
  "fullname": "Glossy",
  "email": "glossy@glosstradamus.com",
  "password": "yellau"
}
```


POST http://localhost:8080/api/users/{USER_ID}/roles

GET  http://localhost:8080/api/users/{USER_ID}/roles 

POST http://localhost:8080/api/users/login 

```json
{
  "email": "glossy@glosstradamus.com",
  "password": "yellau"
}
```


### Heroes
POST http://localhost:8080/api/heroes/create 

```json
{
  "userId": 1,
  "class": "Wizard",
  "level": 10,
  "hitPoints": 200,
  "attack": 10,
  "damage": 5,
  "ac": 12,
  "name": "Ernst the Wizard"
}
```


GET http://localhost:8080/api/heroes/list/{USER_ID} 

