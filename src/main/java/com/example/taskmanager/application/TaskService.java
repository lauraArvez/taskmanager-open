package com.example.taskmanager.application;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.taskmanager.domain.Task;
import com.example.taskmanager.domain.TaskRepository;

/**
 * Servicio de aplicación que contiene la lógica de negocio para las tareas.
 * 
 * Esta clase representa la "capa de aplicación" en una arquitectura hexagonal o OPEN.
 * Orquesta los casos de uso, delegando el almacenamiento al repositorio de dominio.
 */
@Service
public class TaskService {

    private final TaskRepository repository;

    /**
     * Constructor con inyección del repositorio del dominio.
     *
     * @param repository contrato para persistencia de tareas
     */
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    /**
     * Crea una nueva tarea.
     *
     * @param task objeto del dominio con los datos de la tarea
     * @return la tarea guardada (con ID asignado)
     */
    public Task create(Task task) {
        return repository.save(task);
    }

    /**
     * Obtiene la lista completa de tareas.
     *
     * @return lista de tareas existentes
     */
    public List<Task> findAll() {
        return repository.findAll();
    }

    /**
     * Busca una tarea por su ID.
     *
     * @param id identificador de la tarea
     * @return tarea encontrada o vacío si no existe
     */
    public Optional<Task> findById(Long id) {
        return repository.findById(id);
    }

    /**
     * Actualiza una tarea existente si se encuentra en el repositorio.
     *
     * @param id identificador de la tarea a actualizar
     * @param updated objeto con los nuevos datos
     * @return tarea actualizada si existía, o vacío si no se encontró
     */
    public Optional<Task> update(Long id, Task updated) {
        return repository.findById(id)
                .map(existing -> repository.save(new Task(id, updated.getTitle(), updated.getDescription(), updated.isCompleted())));
    }

    /**
     * Elimina una tarea por su ID.
     *
     * @param id identificador de la tarea
     */
    public void delete(Long id) {
        repository.deleteById(id);
    }
}