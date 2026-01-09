package io.github.wendellvalentim.locadora.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data

public class Carro {
   

    private String modelo;

    private Double valorDiario;

    public Carro(String modelo, Double valorDiario) {
        this.modelo = modelo;
        this.valorDiario = valorDiario;
    }

    public double valorAluguel(int dias){
        double desc = 0;
        if( dias >= 5){
            desc = 50;
        }
        return (dias * valorDiario) - desc;
    }
}
