package com.testdto.demo.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProduitDTO {
    private Long id;
    private String libelle;
    private LocalDate dateDexp;
    private double prix;
    private int quantite;
    private Long categorieId;
}
