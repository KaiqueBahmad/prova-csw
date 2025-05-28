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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.kaiquebt.biblioteca.dto.AtualizarUsuarioDTO;
import com.senai.kaiquebt.biblioteca.dto.CriarUsuarioDTO;
import com.senai.kaiquebt.biblioteca.entity.Usuario;
import com.senai.kaiquebt.biblioteca.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> addUsuario(@RequestBody CriarUsuarioDTO criarUsuarioDTO) {
        return ResponseEntity.ok(this.usuarioService.criarUsuario(criarUsuarioDTO));
    }
    
    @PostMapping("/{id}")
    public ResponseEntity<Usuario> updUsuario(@PathVariable Long id, @RequestBody AtualizarUsuarioDTO atualizarUsuarioDTO) {
        return ResponseEntity.ok(this.usuarioService.atualizarUsuario(id, atualizarUsuarioDTO));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios() {
        return ResponseEntity.ok(
            this.usuarioService.buscarUsuarios()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Long id) {
        Optional<Usuario> usuarioOpt = this.usuarioService.buscarUsuario(id);
        if (usuarioOpt.isPresent()) {
            return ResponseEntity.ok(usuarioOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarUsuario(@PathVariable Long id) {
        if (this.usuarioService.removerUsuario(id)) {
            return ResponseEntity.ok("Usuario deletado com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("Usuario não deletado");
        }
    }


}
