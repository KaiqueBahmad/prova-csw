package com.senai.kaiquebt.biblioteca.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.kaiquebt.biblioteca.entity.Emprestimo;
import com.senai.kaiquebt.biblioteca.entity.EmprestimoStatus;
import com.senai.kaiquebt.biblioteca.entity.Livro;
import com.senai.kaiquebt.biblioteca.entity.Usuario;
import com.senai.kaiquebt.biblioteca.repository.EmprestimoRepository;
import com.senai.kaiquebt.biblioteca.repository.LivroRepository;
import com.senai.kaiquebt.biblioteca.repository.UsuarioRepository;

@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Emprestimo> buscarTodos() {
        return this.emprestimoRepository.findAll();
    }

    public Emprestimo emprestar(Long livroId, Long usuarioId) {
        Optional<Livro> livroOpt = this.livroRepository.findById(livroId);
        if (livroOpt.isEmpty()) {
            throw new IllegalArgumentException("Livro não encontrado");
        } 
        Optional<Usuario> usuarioOpt = this.usuarioRepository.findById(usuarioId);
        if (usuarioOpt.isEmpty()) {
            throw new IllegalArgumentException("Usuario não encontrado");
        }

        Usuario usuario = usuarioOpt.get();
        Livro livro = livroOpt.get();

        boolean emprestado = false;
        List<Emprestimo> emprestimosPorLivro = this.emprestimoRepository.findAllByLivroId(livroId);
        for (Emprestimo emprestimo: emprestimosPorLivro) {
            if (emprestimo.getEmprestimoStatus() != EmprestimoStatus.DEVOLVIDO) {
                emprestado = true;
                break;
            }
        }
        if (emprestado) {
            throw new IllegalArgumentException("Não foi possível emprestar, livro já está emprestado");
        }

        int emPosseUsuario = 0;
        List<Emprestimo> emprestimosPorUsuario = this.emprestimoRepository.findAllByUsuarioId(usuarioId);
        for (Emprestimo emprestimo: emprestimosPorUsuario) {
            if (emprestimo.getEmprestimoStatus() != EmprestimoStatus.DEVOLVIDO) {
                emPosseUsuario++;
            }
        }
        if (emPosseUsuario >= 3) {
            throw new IllegalArgumentException("Não foi possível emprestar, Usuario já atingiu limite de 3 livros emprestado ao mesmo tempo");
        }

        Emprestimo novo = new Emprestimo();
        novo.setDataEmprestimo(LocalDate.now());
        novo.setDataDevolucaoPrevista(
            novo.getDataEmprestimo().plusWeeks(2)
        );
        novo.setEmprestimoStatus(EmprestimoStatus.EMPRESTADO);
        novo.setLivro(livro);
        novo.setUsuario(usuario);
        return this.emprestimoRepository.save(novo);
    }

    public Emprestimo devolver(Long emprestimoId) {
        Optional<Emprestimo> emprestimoOpt = this.emprestimoRepository.findById(emprestimoId);
        if (emprestimoOpt.isEmpty()) {
            throw new IllegalArgumentException("Empréstimo não encontrado");
        }

        Emprestimo emprestimo = emprestimoOpt.get();
        if (emprestimo.getEmprestimoStatus() == EmprestimoStatus.DEVOLVIDO) {
            throw new IllegalArgumentException("O livro já foi devolvido");
        }

        emprestimo.setEmprestimoStatus(EmprestimoStatus.DEVOLVIDO);
        emprestimo.setDataDevolucao(LocalDate.now());
        return this.emprestimoRepository.save(emprestimo);
    }

}
