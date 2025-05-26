package com.senai.kaiquebt.biblioteca;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(title = "${info.build.name}",
                version = "${info.build.version}",
                description = "${info.app.description}",
                contact = @io.swagger.v3.oas.annotations.info.Contact(
                        name = "Time Arquitetura e APIs",
                        email = "jonatas.edo@gmail.com")))
@SpringBootApplication
public class AluguelVeiculoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AluguelVeiculoApiApplication.class, args);
    }

}
