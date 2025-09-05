# Etapa 1: build
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copiar descriptor y dependencias primero (cache eficiente)
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
COPY mvnw.cmd .
RUN ./mvnw dependency:go-offline -B

# Copiar código fuente
COPY src ./src

# Compilar
RUN ./mvnw clean package -DskipTests

# Etapa 2: runtime
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copiar jar generado
COPY --from=build /app/target/*.jar app.jar

# Render asigna PORT dinámico
ENV PORT=8080
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
