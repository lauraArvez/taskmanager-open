package com.example.taskmanager.api.infrastructure.dataservice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entidad JPA que representa una tarea en la base de datos.
 * <p>
 * Este es el modelo de persistencia, diferente al modelo de dominio ({@link com.example.taskmanager.domain.Task}).
 * Se usa exclusivamente en la capa de infraestructura y está acoplado a JPA.
 */
@Entity
public class TaskEntity {

    /** Identificador único, generado automáticamente por la base de datos */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Título de la tarea */
    private String title;

    /** Descripción de la tarea */
    private String description;

    /** Estado de finalización */
    private boolean completed;

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}