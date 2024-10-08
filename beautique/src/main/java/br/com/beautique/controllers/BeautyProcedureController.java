package br.com.beautique.controllers;

import br.com.beautique.dtos.BeautyProcedureDTO;
import br.com.beautique.services.BeautyProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("beauty-procedures")
public class BeautyProcedureController {

    @Autowired
    private BeautyProcedureService beautyProcedureService;

    @PostMapping // http://localhost:8082/ms-beautique/beauty-procedures
    ResponseEntity<BeautyProcedureDTO> create(@RequestBody BeautyProcedureDTO beautyProcedureDTO) {
        return ResponseEntity.ok(beautyProcedureService.create(beautyProcedureDTO));
    }

    @DeleteMapping("/{id}") // http://localhost:8082/ms-beautique/beauty-procedures/2
    ResponseEntity<Void> delete(@PathVariable Long id) {
        beautyProcedureService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping // http://localhost:8082/ms-beautique/beauty-procedures/
    ResponseEntity<BeautyProcedureDTO> update(@RequestBody BeautyProcedureDTO beautyProcedureDTO) {
        return ResponseEntity.ok(beautyProcedureService.update(beautyProcedureDTO));
    }
}
