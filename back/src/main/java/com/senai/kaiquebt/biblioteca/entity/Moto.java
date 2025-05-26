package com.senai.kaiquebt.biblioteca.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MOTO")
public class Moto extends Veiculo {
    @Override
    public double calcularValorDiaria() {
        return 100.0;
    }
}
