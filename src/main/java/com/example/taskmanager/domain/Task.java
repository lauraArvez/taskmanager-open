package com.example.taskmanager.domain;

/**
 * Representa una tarea dentro del dominio de la aplicación.
 * 
 * Esta clase forma parte del modelo de negocio y no tiene dependencias 
 * con frameworks externos. Se utiliza en la lógica de aplicación para 
 * manipular tareas de forma independiente a la tecnología de persistencia.
 */
public class Task {

    /** Identificador único de la tarea */
    private Long id;

    /** Título o nombre breve de la tarea */
    private String title;

    /** Descripción opcional con más detalles */
    private String description;

    /** Indica si la tarea está completada */
    private boolean completed;

    /**
     * Constructor principal.
     * 
     *@param id Identificador de la tarea
     * @param title Título de la tarea
     * @param description Descripción de la tarea
     * @param completed Estado de finalización
     */
    public Task(Long id, String title, String description, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }
    
    public Long getId() { return id; } /** @return el ID de la tarea */
    public String getTitle() { return title; } /** @return el título de la tarea */
    public String getDescription() { return description; } /** @return la descripción de la tarea */
    public boolean isCompleted() { return completed; }  /** @return si la tarea está completada */

    /**
     * Crea una nueva instancia de la tarea con un ID actualizado.
     * se usa cuando se quiere crear una nueva instancia de Task cambiando solo el ID, 
     * y dejando el resto igual.
     * 
     * @param newId el nuevo ID a asignar
     * @return una copia de la tarea con el nuevo ID
     */
    public Task withId(Long newId) {
        return new Task(newId, this.title, this.description, this.completed);
    }
}
