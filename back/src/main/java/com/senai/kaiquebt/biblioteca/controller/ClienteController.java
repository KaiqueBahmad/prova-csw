package com.senai.kaiquebt.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.senai.kaiquebt.biblioteca.entity.Cliente;
import com.senai.kaiquebt.biblioteca.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("*")
public class ClienteController {
    @Autowired
    private ClienteService repo;

    @PostMapping
    public Cliente salvar(@RequestBody Cliente c) {
        return repo.salvar(c);
    }

    @GetMapping
    public List<Cliente> listar() {
        return repo.listarTodos();
    }
}
