package com.senai.kaiquebt.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.senai.kaiquebt.biblioteca.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
