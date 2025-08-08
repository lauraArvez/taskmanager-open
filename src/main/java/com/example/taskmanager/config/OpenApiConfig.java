package com.example.taskmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.info.Info;

/**
 * Configuración de OpenAPI/Swagger para documentar la API REST.
 * Define metadatos que aparecerán en Swagger UI.
 */
@Configuration
public class OpenApiConfig {
    
    /**
     * Construye el objeto OpenAPI con información de la API, licencia y enlaces.
     *
     * @return instancia configurada de {@link OpenAPI}
     */
    @Bean
    public OpenAPI taskManagerOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Task Manager API")
                        .description("API de gestión de tareas (CRUD) - Demo inspirada en arquitectura OPEN")
                        .version("v1")
                        .contact(new Contact()
                                .name("Laura Arvez")
                                .email("arvezlau@hotmail.com")
                                .url("https://lauraarvez.github.io"))
                        .license(new License().name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("Portafolio / Documentación adicional")
                        .url("https://lauraarvez.github.io"));
    }
}
