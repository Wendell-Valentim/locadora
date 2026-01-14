package io.github.wendellvalentim.locadora.service;

import io.github.wendellvalentim.locadora.entity.CarroEntity;
import io.github.wendellvalentim.locadora.exceptions.EntityNotFoundException;
import io.github.wendellvalentim.locadora.repository.CarroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarroService {

    private final CarroRepository repository;

    public CarroEntity salvar(CarroEntity carro){
        if(carro.getValorDiaria() <= 0){
            throw new IllegalArgumentException("Preço da diaria não pode ser negativo.");
        }
        return repository.save(carro);

    }

    public CarroEntity atualizar(Long id, CarroEntity carroAtualizado) {
        var carroExistente = repository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Carro não encontrado."));
        carroExistente.setAno(carroAtualizado.getAno());
        carroExistente.setModelo(carroAtualizado.getModelo());
        carroExistente.setValorDiaria(carroExistente.getValorDiaria());

        return repository.save(carroExistente);

    }

    public void deletar(Long id) {
        var carroExistente = repository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Carro não encontrado."));
        repository.deleteById(id);
    }

    public CarroEntity buscarPorId(Long id) {
        return  repository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Carro não encontrado."));

    }

    public List<CarroEntity> listarTodos() {
        return  repository.findAll();
    }
}
