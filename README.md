CS 157A Final Project

### Development setup

Prerequisite: Docker Desktop, OpenJDK 17
```bash
brew install openjdk@17
Create symbolic link
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
cd server
./gradlew bootJar
cd ..
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