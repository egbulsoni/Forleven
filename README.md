# Forleven students API

REST API made for a challenge at Forleven

## Getting Started

clone the repository and run "StudentsApplication"

### Prerequisites

- JDK 11
- Maven

```
CLI Usage:

curl --request POST -H 'Content-Type: application/json' -d '{ "alumniCode":42003, "name":"Aluno", "surName":"L One" }' http://localhost:8080/students
curl --request DELETE http://localhost:8080/students/42003

```

## Running the tests

Just execute the tests file.

## Built With

* [Spring](https://spring.io/) - Java Framework
* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Eduardo Bulsoni** - [egbulsoni](https://github.com/egbulsoni)

## License

This project is licensed under the Unlicense - see the [UNLICENSE.md](UNLICENSE.md) file for details

