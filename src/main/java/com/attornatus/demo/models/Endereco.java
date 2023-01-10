package com.attornatus.demo.models;
import com.attornatus.demo.Enum.EnderecoEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;


@Entity
@Table(name = "endereco")
public class Endereco{


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @NotNull
    @Column(nullable = false,name = "logradouro")
    private String logradouro;
    @NotNull
    @Column(nullable = false,name = "caixapostal")
    private String caixapostal;
    @NotNull
    @Column(nullable = false,name = "numero")
    private String numero;
    @NotNull
    @Column(nullable = false,name = "cidade")
    private String cidade;
    @NotNull
    private EnderecoEnum typeendereco;
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    @JsonBackReference
    private Pessoa pessoa;

    public Endereco(){}

    public Endereco(int id,String logradouro,String caixapostal,String numero,String cidade,EnderecoEnum typeendereco,Pessoa pessoa){
        this.id = id;
        this.logradouro = logradouro;
        this.caixapostal = caixapostal;
        this.numero = numero;
        this.cidade = cidade;
        this.typeendereco = typeendereco;
        this.pessoa = pessoa;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCaixapostal() {
        return caixapostal;
    }

    public void setCaixapostal(String caixapostal) {
        this.caixapostal = caixapostal;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public EnderecoEnum getTypeendereco() {
        return typeendereco;
    }

    public void setTypeendereco(EnderecoEnum typeendereco) {
        this.typeendereco = typeendereco;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}


