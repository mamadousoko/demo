package com.testdto.demo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SortieDTO {
    private Long id;
    private Long produitId;
    private int quantite;
    private LocalDateTime date;
}
