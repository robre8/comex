# Etapa de construcción: Usa Maven con OpenJDK 17
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app-comex

# Copiar el archivo pom.xml y descargar dependencias (optimiza la caché)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar el código fuente
COPY src ./src

# Compilar y empaquetar el proyecto
RUN mvn clean package -DskipTests

# Etapa final: Usa OpenJDK 17 sin Maven para ejecutar el JAR
FROM eclipse-temurin:17-jdk

# Establecer el directorio de trabajo
WORKDIR /app-comex

# Copiar el JAR generado desde la fase de compilación
COPY --from=build /app-comex/target/comex-0.0.1-SNAPSHOT.jar app.jar

# Copiar recursos adicionales (como pedidos.csv)
COPY src/main/resources /app-comex/resources

# Exponer el puerto en el que corre la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]



