package com.example.taskmanager.api.infrastructure.dataservice;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio JPA para {@link TaskEntity}, gestionado por Spring Data.
 * <p>
 * Esta interfaz extiende {@link JpaRepository}, lo que proporciona
 * automáticamente métodos CRUD y de paginación/ordenación sin necesidad
 * de implementación manual.
 */
public interface SpringDataTaskRepository extends JpaRepository<TaskEntity, Long> {
    
}
