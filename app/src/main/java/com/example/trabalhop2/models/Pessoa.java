package com.example.trabalhop2.models;

public class Pessoa {
    String cpf;
    Endereco endereco;
    String nome;

    public String getCpf() { return cpf; }

    public void setCpf(String cpf) { this.cpf = cpf; }

    public Endereco getEndereco() { return endereco; }

    public void setEndereco(Endereco endereco) { this.endereco = endereco; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }
}
