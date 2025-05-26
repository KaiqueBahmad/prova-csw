package com.senai.kaiquebt.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senai.kaiquebt.biblioteca.entity.Aluguel;

import java.util.List;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
    List<Aluguel> findByAtivoTrue();
    List<Aluguel> findByAtivoFalse();

}
