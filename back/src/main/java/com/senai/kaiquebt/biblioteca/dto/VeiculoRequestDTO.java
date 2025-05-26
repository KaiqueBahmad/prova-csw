package com.senai.kaiquebt.biblioteca.dto;

import com.senai.kaiquebt.biblioteca.entity.TipoVeiculo;

public class VeiculoRequestDTO {
    private String modelo;
    private Boolean disponivel;
    private TipoVeiculo tipo;

    // Getters e Setters
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public TipoVeiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoVeiculo tipo) {
        this.tipo = tipo;
    }
}