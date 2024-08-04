![Commit Stage](https://github.com/pebrisulistiyo/order-service/actions/workflows/commit-stage.yml/badge.svg)
# order-service

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
