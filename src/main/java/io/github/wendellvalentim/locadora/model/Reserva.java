package io.github.wendellvalentim.locadora.model;

import io.github.wendellvalentim.locadora.exceptions.ReservaInvalidaException;
import lombok.Data;

@Data
public class Reserva {

    private Carro carro;
    private  Cliente cliente;
    private int dias;


    public Reserva(Carro carro, Cliente cliente, int dias) {
        if ( dias < 1) {
            throw new ReservaInvalidaException("Reserva invalida!");
        }
        this.carro = carro;
        this.cliente = cliente;
        this.dias = dias;
    }

    public double calcularReserva () {
        return carro.valorAluguel(dias);
    }
}
