# JWT using Kotlin with Spring Boot 


## Requirements

* x86-64
* JDK 17
* Linux
* Docker
* Kubernetes


## Setup

The script "up" runs our docker-compose file, which creates a database container. See folder "db" 
```
λ sh up.sh  
```


## Running

One may start the application by utilizing the maven plugin spring-boot as such:
```
λ mvn spring-boot:run
```

## Destroying resources

The shell script "down.sh" runs docker-compose down


## HTTP Endpoints

The endpoints under mapping "api/users" does not require any authentication

The endpoint "api/heroes/create" requires the role "HEROES_WRITE" 

The endpoint "api/heroes/list/{USER_ID}" requires the role "HEROES_READ"

            it.requestMatchers("/api/users/**").permitAll()
            it.requestMatchers("/api/heroes/create").hasAnyAuthority("HEROES_WRITE")
            it.requestMatchers("/api/heroes/list/**").hasAnyAuthority("HEROES_READ")

### Users

POST http://localhost:8080/api/users/create -> |CREATE USER|

POST http://localhost:8080/api/users/{USER_ID}/roles -> |ASSIGN ROLE TO USER|

GET  http://localhost:8080/api/users/{USER_ID}/roles -> |LIST ROLES FOR USER|

POST http://localhost:8080/api/users/login -> |GENERATES JWT|


### Heroes
POST http://localhost:8080/api/heroes/create -> |CREATE HERO|

GET http://localhost:8080/api/heroes/list/{USER_ID} -> |LIST HEROES FOR USER|

