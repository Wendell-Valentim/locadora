package io.github.wendellvalentim.locadora.service;

import io.github.wendellvalentim.locadora.entity.CarroEntity;
import io.github.wendellvalentim.locadora.repository.CarroRepository;
import static  org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarroServiceTest {

    CarroEntity carro;

    @InjectMocks
    CarroService service;

    @Mock
    CarroRepository repository;


    @BeforeEach
    void setUp() {
          carro = new CarroEntity("Sedan", 10.0, 2027);
          carro.setId(1L);

    }

    @Test
    void deveSalvarUmCarro() {
        Mockito.when(repository.save(Mockito.any())).thenReturn(carro);

       var carroSalvo = service.salvar(carro);

        assertThat(carroSalvo).isNotNull();
        assertThat(carroSalvo.getModelo()).isEqualTo("Sedan");

        Mockito.verify(repository).save(Mockito.any());

        System.out.println(carro.getId());

    }

    @Test
    void deveDarErroAoTentarSalvarCarroComDiariaNegativa() {
        carro.setValorDiaria(0);

        var erro = catchThrowable(() -> service.salvar(carro));

        assertThat(erro).isInstanceOf(IllegalArgumentException.class);

        Mockito.verify(repository, Mockito.never()).save(Mockito.any());


    }

    @Test
    void deveAtualizarUmcarro () {

        var carroExistente = carro;
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(carroExistente));

        var carroAtualizado = new CarroEntity("Gol", 80.0, 2027);
        Mockito.when(repository.save(Mockito.any())).thenReturn(carroAtualizado);

        Long id = 1L;
        var carro = new CarroEntity("Sedan", 0, 2023);

        var resultado = service.atualizar(id, carro);

    }
}