package com.senai.kaiquebt.biblioteca.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "tipo")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Carro.class, name = "CARRO"),
        @JsonSubTypes.Type(value = Moto.class, name = "MOTO")
})
public abstract class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;

    private Boolean disponivel;

    public abstract double calcularValorDiaria(); // polimórfico

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veiculo veiculo = (Veiculo) o;
        return Objects.equals(id, veiculo.id) && Objects.equals(modelo, veiculo.modelo) && Objects.equals(disponivel, veiculo.disponivel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, modelo, disponivel);
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "id=" + id +
                ", modelo='" + modelo + '\'' +
                ", disponivel=" + disponivel +
                '}';
    }
}
