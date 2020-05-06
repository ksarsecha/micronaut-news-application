## Sample Micronaut Application
Repo to learn micronaut

#### Tech stack 
- Java
- [Reactor-core](https://projectreactor.io/)
- MongoDB
- [consul](https://www.consul.io/)

#### Steps to run application
1. Run MongoDB          : `docker-compose up mongo`
2. Run consul           : `docker-compose up consul`
3. Build                : `/gradlew build`
4. Run the application  : `java -jar ./build/libs/hacker-news-0.1.jar`
