# build stage
FROM maven:3.9-amazoncorretto-17 AS build
WORKDIR /app

COPY . .

RUN mvn clean package -DwarName=404notfound-api

# run stage
FROM tomcat:10.1.11-jre17

COPY --from=build /app/target/404notfound-api.war $CATALINA_HOME/webapps/