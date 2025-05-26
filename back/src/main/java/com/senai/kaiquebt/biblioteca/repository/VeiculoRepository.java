package com.senai.kaiquebt.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senai.kaiquebt.biblioteca.entity.Veiculo;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    List<Veiculo> findByDisponivelTrue();
}

