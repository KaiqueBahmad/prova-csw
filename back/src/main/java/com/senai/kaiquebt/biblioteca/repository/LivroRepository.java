package com.senai.kaiquebt.biblioteca.repository;

import org.springframework.stereotype.Repository;

import com.senai.kaiquebt.biblioteca.entity.Livro;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    
}
