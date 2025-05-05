CS 157A Final Project

### Development setup
```bash
cd backend
./gradlew bootJar
docker compose -f docker-compose.dev.yml up --build
...
```

```bash
docker ps
docker exec -it {CONTAINER ID} mysql -uroot -ppassword
USE mlatsjsu_db
...
MySQL queries
...
\quit
```