package io.github.wendellvalentim.locadora.model;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarroTest {


    @Test
    @DisplayName("Deve calcular o valor correto do aluguel!")
    void deveCalcularValorAluguel() {
        Carro carro = new Carro("Sedan",100.0);
        double total = carro.valorAluguel(3);
        Assertions.assertEquals(300.0, total);
    }
}
