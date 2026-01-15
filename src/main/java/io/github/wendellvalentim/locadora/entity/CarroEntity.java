package io.github.wendellvalentim.locadora.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Carro")
@Data
public class CarroEntity {

    public CarroEntity() {
    }

    public CarroEntity(String modelo, double valorDiaria, int ano) {

        this.modelo = modelo;
        this.valorDiaria = valorDiaria;
        this.ano = ano;
    }

    public CarroEntity(Long id, String modelo, double valorDiaria, int ano) {
        this(modelo, valorDiaria,ano);
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;

    private double valorDiaria;

    private int ano;
}
