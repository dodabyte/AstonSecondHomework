package com.example.astonsecondhomework.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class SwaggerConfig {
    @Bean
    OpenAPI apiInfo() {
        return new OpenAPI().info(new Info()
                        .title("SecondAstonHomework OpenAPI specification")
                        .version("0.0.1")
                        .description("This API is a homework assignment for the second month of the Aston Java Intensive.")
                        .contact(new Contact()
                                .name("dodabyte")
                                .url("https://github.com/dodabyte")))
                .servers(Collections.singletonList(new Server()
                        .url("http://localhost:8080")
                        .description("Dev service")));
    }
}
