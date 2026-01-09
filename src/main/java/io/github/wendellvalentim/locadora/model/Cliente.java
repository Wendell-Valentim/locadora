package io.github.wendellvalentim.locadora.model;

import lombok.Data;

@Data
public class Cliente {

    public String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }
}
