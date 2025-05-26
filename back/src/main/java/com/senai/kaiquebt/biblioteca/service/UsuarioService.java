package com.senai.kaiquebt.biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.kaiquebt.biblioteca.dto.AtualizarLivroDTO;
import com.senai.kaiquebt.biblioteca.dto.AtualizarUsuarioDTO;
import com.senai.kaiquebt.biblioteca.dto.CriarLivroDTO;
import com.senai.kaiquebt.biblioteca.dto.CriarUsuarioDTO;
import com.senai.kaiquebt.biblioteca.entity.Livro;
import com.senai.kaiquebt.biblioteca.entity.Usuario;
import com.senai.kaiquebt.biblioteca.repository.LivroRepository;
import com.senai.kaiquebt.biblioteca.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(CriarUsuarioDTO criarUsuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setCurso(criarUsuarioDTO.curso());
        usuario.setMatricula(criarUsuarioDTO.matricula());
        usuario.setNome(criarUsuarioDTO.nome());
        return this.usuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(Long id, AtualizarUsuarioDTO atualizarUsuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setCurso(atualizarUsuarioDTO.curso());
        usuario.setMatricula(atualizarUsuarioDTO.matricula());
        usuario.setNome(atualizarUsuarioDTO.nome());
        usuario.setId(id);

        return this.usuarioRepository.save(usuario);
    }

    public boolean removerUsuario(Usuario usuario) {
        if (usuario == null || usuario.getId() == null || usuario.getId() == 0) {
            throw new RuntimeException("Não foi possível excluir o usuário, usuário não recebido como parâmetro.");
        }

        this.usuarioRepository.deleteById(usuario.getId());
        return true;
    }

    public boolean removerUsuario(Long id) {
        if (id == null ||id == 0) {
            throw new RuntimeException("Não foi possível excluir o usuário, usuário não recebido como parâmetro.");
        }

        this.usuarioRepository.deleteById(id);
        return true;
    }

    public List<Usuario> buscarUsuarios() {
        return this.usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarUsuario(Long id) {
        return this.usuarioRepository.findById(id);
    }

}
