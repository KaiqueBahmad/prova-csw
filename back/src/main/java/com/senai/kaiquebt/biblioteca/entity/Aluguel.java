package com.senai.kaiquebt.biblioteca.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Veiculo veiculo;

    private LocalDate dataInicio;
    private LocalDate dataFim;

    private boolean ativo = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluguel aluguel = (Aluguel) o;
        return ativo == aluguel.ativo && Objects.equals(id, aluguel.id) && Objects.equals(cliente, aluguel.cliente) && Objects.equals(veiculo, aluguel.veiculo) && Objects.equals(dataInicio, aluguel.dataInicio) && Objects.equals(dataFim, aluguel.dataFim);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cliente, veiculo, dataInicio, dataFim, ativo);
    }

    @Override
    public String toString() {
        return "Aluguel{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", veiculo=" + veiculo +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", ativo=" + ativo +
                '}';
    }
}
