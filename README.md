![Commit Stage](https://github.com/pebrisulistiyo/order-service/actions/workflows/commit-stage.yml/badge.svg)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=pebrisulistiyo_order-service&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=pebrisulistiyo_order-service)
# order-service

### Test the project
```shell
mvn test
```

### Run apps with Docker
Build Image Project
```shell
docker build \
  -t order-service .
```

Build Image with buildpack and automatic push it to registry
```shell
./mvnw spring-boot:build-image \
  -Ddocker.publishRegistry.username=<username> \
  -Ddocker.publishRegistry.password=<token> \
  -Ddocker.publishRegistry.url=ghcr.io \
  -Dspring-boot.build-image.publish=true \
  -Dspring-boot.build-image.imageName=ghcr.io/<username>/order-service:0.0.1
```

Build Image Local without push to registry
```shell
./mvnw spring-boot:build-image -DskipTests -Dspring-boot.build-image.imageName=order-service
```

Load Image to minikube
```shell
minikube image load order-service --profile books
```
