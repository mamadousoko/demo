package com.testdto.demo.service;

import com.testdto.demo.dto.SortieDTO;
import com.testdto.demo.model.Sortie;
import com.testdto.demo.repository.ProduitRepository;
import com.testdto.demo.repository.SortieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SortieService {

    @Autowired
    private SortieRepository sortieRepository;

    @Autowired
    private ProduitRepository produitRepository;

    public List<SortieDTO> getAllSorties() {
        List<Sortie> sorties = sortieRepository.findAll();
        return sorties.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SortieDTO getSortieById(Long id) {
        Optional<Sortie> sortieOptional = sortieRepository.findById(id);
        return sortieOptional.map(this::convertToDTO).orElse(null);
    }

    public SortieDTO createSortie(SortieDTO sortieDTO) {
        Sortie sortie = convertToEntity(sortieDTO);
        sortie = sortieRepository.save(sortie);
        return convertToDTO(sortie);
    }

    public SortieDTO updateSortie(Long id, SortieDTO sortieDTO) {
        Sortie existingSortie = sortieRepository.findById(id).orElse(null);
        if (existingSortie != null) {
            existingSortie.setProduit(produitRepository.findById(sortieDTO.getProduitId()).orElse(null));
            existingSortie.setQuantite(sortieDTO.getQuantite());
            existingSortie.setDate(sortieDTO.getDate());
            existingSortie = sortieRepository.save(existingSortie);
            return convertToDTO(existingSortie);
        }
        return null;
    }

    public void deleteSortie(Long id) {
        sortieRepository.deleteById(id);
    }

    private SortieDTO convertToDTO(Sortie sortie) {
        SortieDTO dto = new SortieDTO();
        dto.setId(sortie.getId());
        dto.setProduitId(sortie.getProduit() != null ? sortie.getProduit().getId() : null);
        dto.setQuantite(sortie.getQuantite());
        dto.setDate(sortie.getDate());
        return dto;
    }

    private Sortie convertToEntity(SortieDTO dto) {
        Sortie sortie = new Sortie();
        sortie.setId(dto.getId());
        sortie.setProduit(produitRepository.findById(dto.getProduitId()).orElse(null));
        sortie.setQuantite(dto.getQuantite());
        sortie.setDate(dto.getDate());
        return sortie;
    }
}
