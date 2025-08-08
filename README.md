# Task Manager API

API REST CRUD para gestión de tareas, desarrollada con **Spring Boot**, **JPA/H2** y documentada con **Swagger/OpenAPI**.  
Inspirada en el enfoque de **arquitectura OPEN** utilizada en entornos corporativos.

## Tecnologías usadas
- Java 17
- Spring Boot 3
- Spring Data JPA
- Base de datos H2 en memoria
- Swagger / OpenAPI 3
- Maven

## Funcionalidades
- Crear, listar, obtener, actualizar y eliminar tareas.
- Validación de datos con `jakarta.validation`.
- Documentación interactiva con Swagger UI.
- Ejemplo de estructura inspirada en arquitectura hexagonal/OPEN:
  - **Domain**: lógica de negocio (`Task`, `TaskRepository`)
  - **Application**: casos de uso (`TaskService`)
  - **Infrastructure**: persistencia (`TaskRepositoryImpl`, `TaskEntity`, `SpringDataTaskRepository`)
  - **API**: controladores y DTOs
 
## Documentación Swagger
Una vez en ejecución, accede a: http://localhost:8080/swagger-ui.html

## Ejemplo de endpoints
- GET /api/tasks → Lista todas las tareas
- POST /api/tasks → Crea una nueva tarea
- GET /api/tasks/{id} → Obtiene una tarea por ID
- PUT /api/tasks/{id} → Actualiza una tarea
- DELETE /api/tasks/{id} → Elimina una tarea

## Estructura de carpetas
```plaintext
src/main/java/com/example/taskmanager
├── api               # Controladores REST y DTOs
├── application       # Lógica de aplicación
├── domain            # Entidades y contratos de dominio
└── infrastructure    # Adaptadores de persistencia
