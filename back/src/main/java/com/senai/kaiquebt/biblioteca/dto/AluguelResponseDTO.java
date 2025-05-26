package com.senai.kaiquebt.biblioteca.dto;

import java.time.LocalDate;

import com.senai.kaiquebt.biblioteca.entity.TipoVeiculo;

public record AluguelResponseDTO(
    Long id,
    String clienteNome,
    String veiculoModelo,
    LocalDate dataInicio,
    LocalDate dataFim,
    boolean ativo
) {}
