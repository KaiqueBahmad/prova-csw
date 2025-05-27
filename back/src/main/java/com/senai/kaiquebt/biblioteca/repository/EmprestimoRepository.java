package com.senai.kaiquebt.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.kaiquebt.biblioteca.entity.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    public List<Emprestimo> findAllByLivroId(Long livroId); 
    public List<Emprestimo> findAllByUsuarioId(Long usuarioId); 
}
