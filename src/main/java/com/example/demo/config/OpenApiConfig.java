package com.example.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(
        contact = @Contact(
            name = "Kim Fritzon-Ölander",
            email = "kim.fritzon@gmail.com",
            url = "https://www.google.com"
        ),
        description = "OpenAPI - description for may System",
        title = "The Title!!",
        version = "1.0"
    ),
    servers = {
        @Server(
            description = "The working local url",
            url = "http://localhost:8080/"
        ),
        @Server(
            description = "Nonworking production url",
            url = "http://localhost:9090/"
        )
    }
)
public class OpenApiConfig {
}
