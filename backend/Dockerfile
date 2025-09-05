# Etapa 1: build
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Traer dependencias primero (cache eficiente)
COPY pom.xml .
RUN mvn -B -ntp dependency:go-offline

# Copiar código y compilar
COPY src ./src
RUN mvn -B -ntp clean package -DskipTests

# Etapa 2: runtime
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copiar el jar
COPY --from=build /app/target/*.jar app.jar

# Puerto dinámico para Render
ENV PORT=8080
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]