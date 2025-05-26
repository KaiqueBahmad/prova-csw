package com.senai.kaiquebt.biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.kaiquebt.biblioteca.dto.AtualizarLivroDTO;
import com.senai.kaiquebt.biblioteca.dto.CriarLivroDTO;
import com.senai.kaiquebt.biblioteca.entity.Livro;
import com.senai.kaiquebt.biblioteca.repository.LivroRepository;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    private Livro criarLivro(CriarLivroDTO criarLivroDTO) {
        Livro livro = new Livro();
        livro.setAutor(criarLivroDTO.autor());
        livro.setAnoPublicacao(criarLivroDTO.anoPublicacao());
        livro.setEditora(criarLivroDTO.editora());
        livro.setTitulo(criarLivroDTO.titulo());
        return this.livroRepository.save(livro);
    }

    private Livro atualizarLivro(AtualizarLivroDTO criarLivroDTO) {
        Livro livro = new Livro();
        livro.setAutor(criarLivroDTO.autor());
        livro.setAnoPublicacao(criarLivroDTO.anoPublicacao());
        livro.setEditora(criarLivroDTO.editora());
        livro.setTitulo(criarLivroDTO.titulo());
        return this.livroRepository.save(livro);
    }

    public boolean removerLivro(Livro livro) {
        if (livro == null || livro.getId() == null || livro.getId() == 0) {
            throw new RuntimeException("Não foi possível excluir o livro, livro não recebido como parametro.");
        }

        this.livroRepository.deleteById(livro.getId());
        return true;
    }

    public boolean removerLivro(Long id) {
        if (id == null || id == 0) {
            throw new RuntimeException("Não foi possível excluir o livro, livro não recebido como parametro.");
        }

        this.livroRepository.deleteById(id);
        return true;
    }

    public List<Livro> buscarLivros() {
        return this.livroRepository.findAll();
    }

    public Optional<Livro> buscarLivro(Long id) {
        return this.livroRepository.findById(id);
    }

}
