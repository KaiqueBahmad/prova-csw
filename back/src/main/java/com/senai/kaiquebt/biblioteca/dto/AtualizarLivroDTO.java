package com.senai.kaiquebt.biblioteca.dto;

public record AtualizarLivroDTO (
    String titulo,
    String autor,
    String editora,
    Integer anoPublicacao
) {}
