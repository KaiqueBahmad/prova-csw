package com.senai.kaiquebt.biblioteca.dto;

public record CriarLivroDTO (
    String titulo,
    String autor,
    String editora,
    Integer anoPublicacao
) {}
