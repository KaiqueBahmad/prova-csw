package com.senai.kaiquebt.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.senai.kaiquebt.biblioteca.dto.VeiculoRequestDTO;
import com.senai.kaiquebt.biblioteca.entity.Veiculo;
import com.senai.kaiquebt.biblioteca.service.VeiculoService;

import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
@CrossOrigin("*")
public class VeiculoController {
    @Autowired
    private VeiculoService repo;

    @GetMapping
    public List<Veiculo> listar() {
        return repo.listarTodos();
    }

    @GetMapping(value = "/disponiveis")
    public List<Veiculo> listarDisponiveis() {
        return repo.listarDisponiveis();
    }

    @PostMapping
    public ResponseEntity<Veiculo> criarVeiculo(@RequestBody VeiculoRequestDTO veiculoDTO) {
        Veiculo veiculo = repo.criarVeiculo(veiculoDTO);
        return ResponseEntity.ok(veiculo);
    }

}

