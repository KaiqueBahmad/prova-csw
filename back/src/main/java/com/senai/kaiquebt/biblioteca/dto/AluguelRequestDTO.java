package com.senai.kaiquebt.biblioteca.dto;

import java.time.LocalDate;

public record AluguelRequestDTO(
    Long clienteId,
    Long veiculoId,
    LocalDate dataInicio,
    LocalDate dataFim
) {}
