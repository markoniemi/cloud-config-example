# Spring cloud config example

### Run cloud-config-client w/o config server, using local defaults: 

```
mvn spring-boot:run -P local
```

or

```
java -jar cloud-config-client/target/cloud-config-client-0.1-SNAPSHOT.jar -Dspring.cloud.config.enabled=false -Dspring.profiles.active=local
```

### Run cloud-config-client with config server:


```
mvn -f cloud-config-server/pom.xml spring-boot:run
mvn -f cloud-config-client/pom.xml spring-boot:run
```

or

```
java -jar cloud-config-server/target/cloud-config-server-0.1-SNAPSHOT.jar
java -jar cloud-config-client/target/cloud-config-client-0.1-SNAPSHOT.jar
```
