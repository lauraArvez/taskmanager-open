package com.example.taskmanager.api.infrastructure.dataservice;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.taskmanager.domain.Task;
import com.example.taskmanager.domain.TaskRepository;

/**
 * Implementación del repositorio de dominio para {@link Task} usando JPA.
 * <p>
 * Esta clase actúa como un adaptador de infraestructura que traduce entre:
 * <ul>
 *     <li>El modelo de dominio {@link Task}, usado en la lógica de negocio.</li>
 *     <li>El modelo de persistencia {@link TaskEntity}, usado por JPA.</li>
 * </ul>
 * Cumple con el contrato definido en {@link TaskRepository} y delega
 * las operaciones de almacenamiento a {@link SpringDataTaskRepository}.
 */
@Repository
public class TaskRepositoryImpl implements TaskRepository {

    /** Repositorio JPA autogenerado por Spring Data para acceso a base de datos */
    private final SpringDataTaskRepository jpa;

    /**
     * Constructor con inyección del repositorio JPA.
     *
     * @param jpa repositorio de Spring Data JPA para {@link TaskEntity}
     */
    public TaskRepositoryImpl(SpringDataTaskRepository jpa) {
        this.jpa = jpa;
    }

    /**
     * Guarda una tarea en base de datos, convirtiendo entre el modelo
     * de dominio y el modelo de persistencia.
     *
     * @param task objeto de dominio que se desea guardar
     * @return objeto de dominio guardado, con ID asignado si es nuevo
     */
    @Override
    public Task save(Task task) {
        TaskEntity entity = new TaskEntity();
        entity.setId(task.getId());
        entity.setTitle(task.getTitle());
        entity.setDescription(task.getDescription());
        entity.setCompleted(task.isCompleted());
        TaskEntity saved = jpa.save(entity);
        return new Task(saved.getId(), saved.getTitle(), saved.getDescription(), saved.isCompleted());
    }

    /**
     * Busca una tarea por su identificador único.
     *
     * @param id identificador de la tarea
     * @return un {@link Optional} con la tarea encontrada, o vacío si no existe
     */
    @Override
    public Optional<Task> findById(Long id) {
        return jpa.findById(id)
                .map(e -> new Task(e.getId(), e.getTitle(), e.getDescription(), e.isCompleted()));
    }

    /**
     * Devuelve todas las tareas almacenadas en la base de datos.
     *
     * @return lista de objetos de dominio {@link Task}
     */
    @Override
    public List<Task> findAll() {
        return jpa.findAll().stream()
                .map(e -> new Task(e.getId(), e.getTitle(), e.getDescription(), e.isCompleted()))
                .collect(Collectors.toList());
    }

    /**
     * Elimina una tarea de la base de datos por su identificador.
     *
     * @param id identificador de la tarea a eliminar
     */
    @Override
    public void deleteById(Long id) {
        jpa.deleteById(id);
    }

}
