package io.github.wendellvalentim.locadora.controller;

import io.github.wendellvalentim.locadora.entity.CarroEntity;
import io.github.wendellvalentim.locadora.exceptions.EntityNotFoundException;
import io.github.wendellvalentim.locadora.service.CarroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/carros")
@RequiredArgsConstructor
public class CarroController {

    private final CarroService carroService;

    @PostMapping
    public ResponseEntity<Object> salvar (@RequestBody CarroEntity carro) {

        try {
            var carroSalvo = carroService.salvar(carro);
            return ResponseEntity.status(HttpStatus.CREATED).body(carroSalvo);
    } catch(IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(e.getMessage());
        }

        }


     @GetMapping("{id}")
        public ResponseEntity<CarroEntity> buscar(@PathVariable("id") Long id) {
        try {
            var carro = carroService.buscarPorId(id);
            return ResponseEntity.ok(carro);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
     }

     @PutMapping("{id}")
    public ResponseEntity<Void> atualizarCarro(@PathVariable("id") Long id,@RequestBody
     CarroEntity carro) {
        try{
        carroService.atualizar(id, carro);
        return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

     }

     @DeleteMapping("{id}")
    public ResponseEntity<Void> atualizarCarro(@PathVariable("id") Long id) {
        try{
            carroService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

}
