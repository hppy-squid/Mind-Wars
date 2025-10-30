# ===== Byggfas =====
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

# Kopiera projektfiler
COPY . .

# Bygg JAR-fil (hoppa över tester)
RUN mvn clean package -DskipTests

# ===== Runtime =====
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Kopiera byggd JAR från byggfasen
COPY --from=build /app/target/*.jar app.jar

# Exponera Spring Boot port
EXPOSE 8080

# Kör JAR:en med miljövariabler
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]