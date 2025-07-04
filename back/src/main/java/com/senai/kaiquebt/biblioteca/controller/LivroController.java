package com.senai.kaiquebt.biblioteca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.kaiquebt.biblioteca.BibliotecaApplication;
import com.senai.kaiquebt.biblioteca.dto.AtualizarLivroDTO;
import com.senai.kaiquebt.biblioteca.dto.CriarLivroDTO;
import com.senai.kaiquebt.biblioteca.entity.Livro;
import com.senai.kaiquebt.biblioteca.service.LivroService;


@RequestMapping("/api/livro")
@RestController
@CrossOrigin("*")
public class LivroController {
    @Autowired
    private LivroService livroService;

    @PostMapping
    public ResponseEntity<Livro> addLivro(@RequestBody CriarLivroDTO livroDto) {
        return ResponseEntity.ok(this.livroService.criarLivro(livroDto));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Livro> updLivro(@PathVariable Long id, @RequestBody AtualizarLivroDTO livroDto) {
        return ResponseEntity.ok(this.livroService.atualizarLivro(id, livroDto));
    }

    @GetMapping
    public ResponseEntity<List<Livro>> getLivros() {
        return ResponseEntity.ok(this.livroService.buscarLivros());
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<String> deletarLivro(@PathVariable Long id) {
        if (this.livroService.removerLivro(id)) {
            return ResponseEntity.ok(BibliotecaApplication.asMessage("Livro deletado com sucesso"));
        } else {
            return ResponseEntity.badRequest().body(BibliotecaApplication.asMessage("Livro não foi deletado"));
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Livro> getLivro(@PathVariable Long id) {
        Optional<Livro> livroOpt = this.livroService.buscarLivro(id);
        if (livroOpt.isPresent()) {
            return ResponseEntity.ok(livroOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    

}
