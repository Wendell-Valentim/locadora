package io.github.wendellvalentim.locadora.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ClienteTest {

    @Test
    void criarClienteComNome () {
        //1 Cenario
        var cliente = new Cliente("Maria");

        //2 Execução
        String nome = cliente.getNome();

        //3 Verificações
        assertNotNull(nome);

        assertThat(nome).isEqualTo("Maria");
        assertThat(nome).isLessThan("Maria5");

        assertTrue(nome.startsWith("M"));
        assertFalse(nome.length() == 100);

        assertThat(nome.length()).isLessThan(100);

    }

    @Test
    void deveCriarClienteSemNome() {
        var cliente = new Cliente(null);

        String nome = cliente.getNome();

        assertNull(nome);
    }
}
