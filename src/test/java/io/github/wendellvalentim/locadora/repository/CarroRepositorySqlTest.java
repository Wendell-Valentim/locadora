package io.github.wendellvalentim.locadora.repository;

import io.github.wendellvalentim.locadora.entity.CarroEntity;
import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.*;
import  org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class CarroRepositorySqlTest {

    @Autowired
    private CarroRepository carroRepository;

    @Test
    @Sql("/sql/popular-carros.sql")
    void deveBuscarCarroPorModelo(){
        List<CarroEntity> lista = carroRepository.findByModelo("SUV");

        var carro = lista.stream().findFirst().get() ;

        assertThat(lista.size()).isEqualTo(1);
        assertThat(carro.getValorDiaria()).isEqualTo(150.0);
        assertThat(carro.getModelo()).isEqualTo("SUV");


    }

}
