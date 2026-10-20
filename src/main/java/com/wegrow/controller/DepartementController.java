package com.wegrow.controller;

import com.wegrow.model.Departement;
import com.wegrow.service.DepartementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartementController {

    private final DepartementService departementService;

    public DepartementController(DepartementService departementService) {
        this.departementService = departementService;
    }

    @PostMapping
    public ResponseEntity<Departement> create(@RequestBody Departement departement){
        Departement createdDep = departementService.create(departement);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDep);
    }

    @GetMapping
    public ResponseEntity<List<Departement>> getAll(){
        return ResponseEntity.ok(departementService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        departementService.delete(id);
        return ResponseEntity.noContent().build(); // in this line the noCOntent returns response 204 indicating that the operation succeed without returning data
    }


}
