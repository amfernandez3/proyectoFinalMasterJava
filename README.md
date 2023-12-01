# proyectoFinalMasterJava


# Nombre del Proyecto

Proyecto de Microservicios para Gestión de Personajes, Batallas y Estadísticas

## Descripción

Este proyecto consiste en tres microservicios desarrollados con Spring Boot para gestionar personajes, batallas y estadísticas. Cada microservicio tiene su propia responsabilidad y se comunica con los demás para proporcionar una funcionalidad completa.

## Microservicios

### 1. Personajes

Este microservicio gestiona la información relacionada con los personajes. Incluye operaciones como guardar, obtener y actualizar información de personajes.

### 2. Batallas

El microservicio de batallas se encarga de gestionar las batallas entre los personajes. Proporciona operaciones para realizar batallas y obtener información sobre las mismas.

### 3. Estadísticas

El microservicio de estadísticas crea informes sobre los personajes y las batallas. Ofrece funciones para obtener informes sobre el número de personajes, el nivel medio, y otros datos estadísticos relevantes.

## Tecnologías Utilizadas

- Java
- Spring Boot
- JPA (Java Persistence API)
- Hibernate
- Maven
- Lombok
- Swagger

## Configuración y Uso

Asegúrate de tener Java y Maven instalados en tu sistema. Para cada microservicio, puedes usar los siguientes comandos:

```bash
# Clonar el repositorio
git clone https://github.com/tuusuario/nombre-del-repositorio.git

# Cambiarse al directorio del microservicio (personajes, batallas, estadisticas)
cd nombre-del-microservicio

# Configurar la Base de Datos (puedes ajustar la configuración en application.properties)
# Crear la base de datos
# Ejemplo para MySQL: create database bdprueba;

# Compilar y ejecutar
mvn spring-boot:run


Documentación de la API
Cada microservicio tiene su propia documentación de API generada automáticamente con Swagger. Puedes acceder a la documentación visitando las siguientes URLs:

Personajes: http://localhost:8080/swagger-ui.html
Batallas: http://localhost:8081/swagger-ui.html
Estadísticas: http://localhost:8082/swagger-ui.html
