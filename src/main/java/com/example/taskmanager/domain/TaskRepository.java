package com.example.taskmanager.domain;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz del dominio que define las operaciones para persistencia de tareas.
 *
 * Este repositorio representa un puerto en la arquitectura hexagonal o OPEN,
 * y debe ser implementado por una clase de infraestructura.
 */
public interface TaskRepository {

    /**
     * Guarda una tarea (nueva o existente).
     *
     * @param task la tarea a guardar
     * @return la tarea guardada, posiblemente con ID asignado
     */
    Task save(Task task);

    /**
     * Busca una tarea por su ID.
     *
     * @param id identificador de la tarea
     * @return tarea encontrada o vacío si no existe
     */
    Optional<Task> findById(Long id);

    /**
     * Devuelve todas las tareas almacenadas.
     *
     * @return lista de tareas
     */
    List<Task> findAll();

    /**
     * Elimina una tarea por su ID.
     *
     * @param id identificador de la tarea
     */
    void deleteById(Long id);
}
