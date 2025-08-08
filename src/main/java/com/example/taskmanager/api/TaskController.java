package com.example.taskmanager.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.application.TaskService;
import com.example.taskmanager.domain.Task;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.*;
import jakarta.validation.Valid;

/**
 * Controlador REST que expone los endpoints para la gestión de tareas.
 * 
 * Esta clase representa la entrada del sistema desde el punto de vista de la arquitectura OPEN.
 * Recibe las peticiones HTTP, valida los datos, convierte los DTOs en objetos del dominio,
 * y delega en el servicio de aplicación (`TaskService`) la ejecución de los casos de uso.
 */
@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Tasks", description = "Operaciones de gestión de tareas")
public class TaskController {

    private final TaskService service;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param service Servicio de aplicación que contiene la lógica de negocio
     */
    public TaskController(TaskService service) {
        this.service = service;
    }

    /**
     * Crea una nueva tarea a partir de los datos recibidos.
     *
     * @param request DTO con los datos de la tarea, validado automáticamente con @Valid
     * @return la tarea creada, incluyendo su ID generado
     */
    @PostMapping
    @Operation(
        summary = "Crear tarea",
        description = "Crea una nueva tarea con título, descripción y estado",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos para crear la tarea",
            required = true,
            content = @Content(schema = @Schema(implementation = TaskRequest.class))
        )
    )
    @ApiResponse(responseCode = "200", description = "Tarea creada",
        content = @Content(schema = @Schema(implementation = Task.class)))
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    public ResponseEntity<Task> create(
            @Valid @org.springframework.web.bind.annotation.RequestBody TaskRequest request){
        Task task = new Task(null, request.getTitle(), request.getDescription(), request.isCompleted());
        return ResponseEntity.ok(service.create(task));
    }

    /**
     * Devuelve la lista de todas las tareas existentes.
     *
     * @return lista de tareas en formato JSON
     */
    @GetMapping
    @Operation(summary = "Listar tareas", description = "Devuelve todas las tareas")
    @ApiResponse(responseCode = "200", description = "Listado de tareas",
        content = @Content(array = @ArraySchema(schema = @Schema(implementation = Task.class))))
    public List<Task> list() {
        return service.findAll();
    }

    /**
     * Obtiene una tarea concreta por su ID.
     *
     * @param id identificador único de la tarea
     * @return la tarea si existe, o un 404 si no se encuentra
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener tarea por ID")
    @ApiResponse(
        responseCode = "200",
        description = "Tarea encontrada",
        content = @Content(schema = @Schema(implementation = Task.class))
    )
    @ApiResponse(responseCode = "404", description = "No encontrada")
    public ResponseEntity<Task> get(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Actualiza una tarea existente con nuevos datos.
     *
     * @param id identificador de la tarea a actualizar
     * @param request DTO con los nuevos valores, validado con @Valid
     * @return la tarea actualizada, o un 404 si la tarea no existe
     */
    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar tarea por ID",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos para actualizar la tarea",
            required = true,
            content = @Content(schema = @Schema(implementation = TaskRequest.class))
        )
    )
    @ApiResponse(
        responseCode = "200",
        description = "Tarea actualizada",
        content = @Content(schema = @Schema(implementation = Task.class))
    )
    @ApiResponse(responseCode = "404", description = "No encontrada")
    public ResponseEntity<Task> update(@PathVariable Long id, @Valid @RequestBody TaskRequest request) {
        Task updated = new Task(id, request.getTitle(), request.getDescription(), request.isCompleted());
        return service.update(id, updated)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Elimina una tarea por su ID.
     *
     * @param id identificador de la tarea a eliminar
     * @return 204 No Content si se eliminó, o 404 si no existía
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar tarea por ID")
    @ApiResponse(responseCode = "204", description = "Eliminada")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

/**
 * USO inyección por constructor en lugar de @Autowired, que es la forma recomendada por Spring 
 * (más limpia, segura y testeable).
 * Spring detecta el @Service automáticamente y, como esta clase tiene solo un constructor, 
 * lo inyecta sin necesidad de @Autowired.
 * Desde Spring 4.3 en adelante, @Autowired ya no es necesaria si solo hay un constructor. 
 */