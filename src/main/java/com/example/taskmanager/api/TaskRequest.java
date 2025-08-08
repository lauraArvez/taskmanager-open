package com.example.taskmanager.api;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO que representa la entrada de datos para crear o actualizar una tarea
 * a través de la API REST.
 *
 * Se usa como cuerpo del request HTTP en los métodos POST y PUT.
 * Incluye validaciones para asegurar la integridad de los datos.
 */
public class TaskRequest {


    /** Título obligatorio de la tarea */
    @NotBlank(message = "El título no puede estar vacío")
    private String title;

    /** Descripción opcional */
    private String description;

    /** Estado de finalización */
    private boolean completed;

    /* se evita Lombok en DTOs para tener control explícito de lo que se expone. */
    public String getTitle() { return title; }  /** @return el título ingresado por el cliente */
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; } /** @return la descripción ingresada por el cliente */
    public void setDescription(String description) { this.description = description; }

    public boolean isCompleted() { return completed; } /** @return si el cliente marcó la tarea como completada */
    public void setCompleted(boolean completed) { this.completed = completed; }
    
}
