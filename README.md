# JWT using Kotlin with Spring Boot 3 and Spring Security 6


## Requirements

* x86-64
* JDK 17
* Linux
* Docker
* Kubernetes


## Setup

```
λ sh up.sh  
```


## Running

Local runs are achieved by means of the maven plugin spring-boot.
```
λ mvn spring-boot:run
```

## Destroying resources

The shell script "down.sh" frees up allocated resources.


## HTTP Endpoints

The admin deployment has access to our HTTP endpoints via the AdminController

POST http://localhost:8080/kafka/createUser

GET http://localhost:8080/kafka/validateUser

POST http://localhost:8080/kafka/startQuicksort

