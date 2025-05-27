package com.senai.kaiquebt.biblioteca.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Livro livro;

    @ManyToOne
    private Usuario usuario;

    private LocalDate dataEmprestimo;
    
    @Column(nullable = true)
    private LocalDate dataDevolucao;

    private LocalDate dataDevolucaoPrevista;

    @Enumerated(EnumType.STRING)
    private EmprestimoStatus emprestimoStatus;

    

    @Override
    public String toString() {
        return "Emprestimo [id=" + id + ", dataEmprestimo=" + dataEmprestimo + ", dataDevolucao=" + dataDevolucao
                + ", dataDevolucaoPrevista=" + dataDevolucaoPrevista + ", emprestimoStatus=" + emprestimoStatus + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public EmprestimoStatus getEmprestimoStatus() {
        return emprestimoStatus;
    }

    public void setEmprestimoStatus(EmprestimoStatus emprestimoStatus) {
        this.emprestimoStatus = emprestimoStatus;
    }

    

}
