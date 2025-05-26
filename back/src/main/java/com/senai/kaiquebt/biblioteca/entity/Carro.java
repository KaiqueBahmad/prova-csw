package com.senai.kaiquebt.biblioteca.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CARRO")
public class Carro extends Veiculo {
    @Override
    public double calcularValorDiaria() {
        return 150.0;
    }
}
