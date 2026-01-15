package io.github.wendellvalentim.locadora.service;

import io.github.wendellvalentim.locadora.entity.CarroEntity;
import io.github.wendellvalentim.locadora.exceptions.EntityNotFoundException;
import io.github.wendellvalentim.locadora.repository.CarroRepository;
import static  org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        when(repository.save(any())).thenReturn(carro);

       var carroSalvo = service.salvar(carro);

        assertThat(carroSalvo).isNotNull();
        assertThat(carroSalvo.getModelo()).isEqualTo("Sedan");

        verify(repository).save(any());

        System.out.println(carro.getId());

    }

    @Test
    void deveDarErroAoTentarSalvarCarroComDiariaNegativa() {
        carro.setValorDiaria(0);

        var erro = catchThrowable(() -> service.salvar(carro));

        assertThat(erro).isInstanceOf(IllegalArgumentException.class);

        verify(repository, never()).save(any());


    }

    @Test
    void deveAtualizarUmcarro () {

        var carroExistente = carro;
        when(repository.findById(1L)).thenReturn(Optional.of(carroExistente));

        var carroAtualizado = new CarroEntity("Gol", 80.0, 2027);
        when(repository.save(any())).thenReturn(carroAtualizado);

        Long id = 1L;
        var carro = new CarroEntity("Sedan", 0, 2023);

        var resultado = service.atualizar(id, carro);

        assertEquals(resultado.getModelo(), "Gol");
        verify(repository, times(1)).save(any());

    }

    @Test
    void deveDarErroAoTentarAtualizarCarroInexistente() {
        Long id = 1L;

        when(repository.findById(any())).thenReturn(Optional.empty());

        var erro = catchThrowable(() -> service.atualizar(id, carro));

        assertThat(erro).isInstanceOf(EntityNotFoundException.class);

        verify(repository, never()).save(any());

    }

    @Test
    void deveDeletarUmCarro () {
        when(repository.findById(any())).thenReturn(Optional.of(carro));
        Long id = 1L;

        service.deletar(id);

        verify(repository, times(1)).delete(any());

    }

    @Test
    void deveDarUmErroAoTentarDeletarUmCarro () {
        Long id = 1L;

        when(repository.findById(any())).thenReturn(Optional.empty());

        var erro = catchThrowable((() -> service.deletar(id)));

        assertThat(erro).isInstanceOf(EntityNotFoundException.class);

       

    }

    @Test
    void deveBuscarUmCarro() {
        Long id = 1L;
        when(repository.findById(any())).thenReturn(Optional.of(carro));

        var carroEncontado = service.buscarPorId(id);

        assertThat(carroEncontado.getModelo()).isEqualTo("Sedan");
        assertThat(carroEncontado.getValorDiaria()).isEqualTo(10.0);
        assertThat(carroEncontado.getAno()).isEqualTo(2027);

        verify(repository, times(1)).findById(any());

    }

    @Test
    void deveDarErroAoBuscarUmCarro() {
        Long id = 1L;
        when(repository.findById(any())).thenReturn(Optional.empty());
        var erro = catchThrowable(() -> service.buscarPorId(id));

        assertThat(erro).isInstanceOf(EntityNotFoundException.class);

        verify(repository, times(1)).findById(id);


    }

    @Test
    void deveListarTodosOsCarros() {
        var carro2 = new CarroEntity(1L,"Hatch", 10.0, 2020);

         var lista = List.of(carro, carro2);

         when(repository.findAll()).thenReturn(lista);

        List<CarroEntity> resultado = service.listarTodos();

        assertThat(resultado).hasSize(2);

        verify(repository, times(1)).findAll();

        verifyNoMoreInteractions(repository);
    }
}