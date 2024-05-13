package com.testdto.demo.controller;

import com.testdto.demo.dto.EntreeDTO;
import com.testdto.demo.service.EntreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entrees")
public class EntreeController {

    @Autowired
    private EntreeService entreeService;

    @GetMapping
    public ResponseEntity<List<EntreeDTO>> getAllEntrees() {
        List<EntreeDTO> entrees = entreeService.getAllEntrees();
        return new ResponseEntity<>(entrees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntreeDTO> getEntreeById(@PathVariable("id") Long id) {
        EntreeDTO entreeDTO = entreeService.getEntreeById(id);
        if (entreeDTO != null) {
            return new ResponseEntity<>(entreeDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<EntreeDTO> createEntree(@RequestBody EntreeDTO entreeDTO) {
        EntreeDTO createdEntree = entreeService.createEntree(entreeDTO);
        return new ResponseEntity<>(createdEntree, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntreeDTO> updateEntree(@PathVariable("id") Long id, @RequestBody EntreeDTO entreeDTO) {
        EntreeDTO updatedEntree = entreeService.updateEntree(id, entreeDTO);
        if (updatedEntree != null) {
            return new ResponseEntity<>(updatedEntree, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntree(@PathVariable("id") Long id) {
        entreeService.deleteEntree(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
