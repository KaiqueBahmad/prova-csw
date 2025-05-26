package com.senai.kaiquebt.biblioteca.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.senai.kaiquebt.biblioteca.dto.AluguelRequestDTO;
import com.senai.kaiquebt.biblioteca.dto.AluguelResponseDTO;
import com.senai.kaiquebt.biblioteca.service.AluguelService;

import java.util.List;

@RestController
@RequestMapping("/api/alugueis")
@CrossOrigin("*")
public class AluguelController {

    private final AluguelService aluguelService;

    public AluguelController(AluguelService aluguelService) {
        this.aluguelService = aluguelService;
    }

    @PostMapping
    public ResponseEntity<AluguelResponseDTO> alugar(@RequestBody @Validated AluguelRequestDTO dto) {
        AluguelResponseDTO response = aluguelService.alugarVeiculo(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}/devolver")
    public ResponseEntity<Void> devolver(@PathVariable Long id) {
        aluguelService.devolverVeiculo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ativos")
    public List<AluguelResponseDTO> listarAtivos() {
        return aluguelService.listarAtivos();
    }

    @GetMapping
    public List<AluguelResponseDTO> listar(@RequestParam(required = false) Boolean ativo) {
        return aluguelService.listarPorAtivo(ativo);
    }

}
