package io.github.wendellvalentim.locadora.repository;

import io.github.wendellvalentim.locadora.entity.CarroEntity;
import io.github.wendellvalentim.locadora.model.Carro;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class CarroRepositoryTest {

    @Autowired
    private CarroRepository carroRepository;

    private CarroEntity carro;

    @BeforeEach
    void setUp() {
        carro = new CarroEntity("Honda Civic", 100.0, 2027);
    }

    @Test
    void deveSalvarUmCarro() {
        carroRepository.save(carro);

        assertNotNull(carro.getId());
    }

    @Test
    void deveBuscarCarroPorId() {

        var carroSalvo = carroRepository.save(carro);

        Optional<CarroEntity> carroEncontrado = carroRepository.findById(carroSalvo.getId());

        assertThat(carroEncontrado).isPresent();
        assertThat(carroEncontrado.get().getModelo()).isEqualTo("Honda Civic");
    }

    @Test
    void deveAtualizarCarro() {
        var carroSalvo = carroRepository.save(carro);

        carroSalvo.setAno(2028);
        var carroAtualizado = carroRepository.save(carroSalvo);

        assertThat(carroAtualizado.getAno()).isEqualTo(2028);
    }

    @Test
    void deveDeletarCarro() {
        var CarroSalvo = carroRepository.save(carro);

        carroRepository.deleteById(CarroSalvo.getId());

        Optional<CarroEntity> carroEncontrado = carroRepository.findById(CarroSalvo.getId());

        assertThat(carroEncontrado).isEmpty();
    }

}