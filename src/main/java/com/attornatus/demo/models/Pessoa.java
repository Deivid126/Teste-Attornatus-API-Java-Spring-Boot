package com.attornatus.demo.models;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @NotNull
    @Column(nullable = false,name="nome")
    private String nome;
    @NotNull
    @Column(nullable = false,name="data_nascimento")
    private LocalDate data_nascimento;
    @NotNull
    @OneToMany(mappedBy = "pessoa",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Endereco> enderecos = new ArrayList<Endereco>();


    public Pessoa(){}

    public Pessoa(int id,String nome,LocalDate data_nascimento,List<Endereco> enderecos){
        this.id = id;
        this.nome = nome;
        this.data_nascimento = data_nascimento;
        this.enderecos = enderecos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}
