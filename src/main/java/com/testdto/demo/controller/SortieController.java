package com.testdto.demo.controller;

import com.testdto.demo.dto.SortieDTO;
import com.testdto.demo.service.SortieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sorties")
public class SortieController {

    @Autowired
    private SortieService sortieService;

    @GetMapping
    public ResponseEntity<List<SortieDTO>> getAllSorties() {
        List<SortieDTO> sorties = sortieService.getAllSorties();
        return new ResponseEntity<>(sorties, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SortieDTO> getSortieById(@PathVariable("id") Long id) {
        SortieDTO sortieDTO = sortieService.getSortieById(id);
        if (sortieDTO != null) {
            return new ResponseEntity<>(sortieDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<SortieDTO> createSortie(@RequestBody SortieDTO sortieDTO) {
        SortieDTO createdSortie = sortieService.createSortie(sortieDTO);
        return new ResponseEntity<>(createdSortie, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SortieDTO> updateSortie(@PathVariable("id") Long id, @RequestBody SortieDTO sortieDTO) {
        SortieDTO updatedSortie = sortieService.updateSortie(id, sortieDTO);
        if (updatedSortie != null) {
            return new ResponseEntity<>(updatedSortie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSortie(@PathVariable("id") Long id) {
        sortieService.deleteSortie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
