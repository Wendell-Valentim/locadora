package io.github.wendellvalentim.locadora.repository;

import io.github.wendellvalentim.locadora.entity.CarroEntity;
import io.github.wendellvalentim.locadora.model.Carro;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")

class CarroRepositoryTest {

    @Autowired
    private CarroRepository carroRepository;

    @Test
    void deveSalvarUmCarro(){
        var entity = new CarroEntity("Sedan", 100.0);

        carroRepository.save(entity);

        assertNotNull(entity.getId());
    }

}