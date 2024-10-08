package br.com.ms_beautique_query.controllers;

import br.com.ms_beautique_query.dtos.BeautyProcedureDTO;
import br.com.ms_beautique_query.services.BeautyProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/beauty-procedure")
public class BeautyProcedureController {

    @Autowired
    private BeautyProcedureService beautyProcedureService;

    @GetMapping
    ResponseEntity<List<BeautyProcedureDTO>> listAllBeautyProcedures() {
        return ResponseEntity.ok(beautyProcedureService.listAllBeautyProcedures());
    }

    @GetMapping("/name/{name}") // http://localhost:8084/ms-beautique-query/beauty-procedure
    ResponseEntity<List<BeautyProcedureDTO>> listByNameLikeIgnoreCase(@PathVariable String name) {
        return ResponseEntity.ok(beautyProcedureService.listByNameIgnoreCase(name));
    }

    @GetMapping("/description/{description}")
    ResponseEntity<List<BeautyProcedureDTO>> listByDescriptionAllBeautyProcedures(@PathVariable String description) {
        return ResponseEntity.ok(beautyProcedureService.listByDescriptionIgnoreCase(description));
    }
}
