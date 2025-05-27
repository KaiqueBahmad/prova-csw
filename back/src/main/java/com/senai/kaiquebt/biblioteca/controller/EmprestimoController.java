package com.senai.kaiquebt.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.kaiquebt.biblioteca.entity.Emprestimo;
import com.senai.kaiquebt.biblioteca.service.EmprestimoService;

@RestController
@RequestMapping("/api/livro")
@CrossOrigin("*")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping("/{livroId}/emprestar-para/{usuarioId}")
    public ResponseEntity<Emprestimo> emprestar(@PathVariable Long livroId, @PathVariable Long usuarioId) {
        return ResponseEntity.ok(this.emprestimoService.emprestar(livroId, usuarioId));
    }

    @PostMapping("/emprestimo/{emprestimoId}/registrar-devolucao")
    public ResponseEntity<Emprestimo> devolver(@PathVariable Long emprestimoId) {
        return ResponseEntity.ok(this.emprestimoService.devolver(emprestimoId));
    }

    @GetMapping("/emprestimo")
    public ResponseEntity<List<Emprestimo>> getAll() {
        return ResponseEntity.ok(this.emprestimoService.buscarTodos());
    }
}
