package io.github.wendellvalentim.locadora.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import io.github.wendellvalentim.locadora.exceptions.ReservaInvalidaException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReservaTest {

    private Cliente cliente;
    private Carro carro;

    @BeforeEach
    void setUp(){
        cliente = new Cliente("Kalel");
        carro = new Carro("Sedan", 100.0);
    }

    @Test
    void criarReserva() {

        var dias = 5;
        var reserva = new Reserva(carro, cliente, dias);

        double total = reserva.calcularReserva();

        assertEquals(450.0, total);
        assertThat(reserva).isNotNull();
    }

    @Test
    void deveDarErroAoCriarUmaReservaComDiasNegativos() {
        // JUnit
        assertThrows(ReservaInvalidaException.class, () -> new Reserva(carro, cliente, 0));
        assertDoesNotThrow(() -> new Reserva(carro, cliente, 1));
    }

    @Test
    void calcularTotalDoAluguel() {
        var reserva = new Reserva(carro,cliente,3);

        var total = reserva.calcularReserva();

        assertThat(total).isEqualTo(300.0);
    }


}
