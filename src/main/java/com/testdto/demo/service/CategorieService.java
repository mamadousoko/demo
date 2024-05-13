package com.testdto.demo.service;

import com.testdto.demo.dto.CategorieDTO;
import com.testdto.demo.model.Categorie;
import com.testdto.demo.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    public List<CategorieDTO> getAllCategories() {
        List<Categorie> categories = categorieRepository.findAll();
        return categories.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CategorieDTO getCategorieById(Long id) {
        Optional<Categorie> categorieOptional = categorieRepository.findById(id);
        return categorieOptional.map(this::convertToDTO).orElse(null);
    }

    public CategorieDTO createCategorie(CategorieDTO categorieDTO) {
        Categorie categorie = convertToEntity(categorieDTO);
        categorie = categorieRepository.save(categorie);
        return convertToDTO(categorie);
    }

    public CategorieDTO updateCategorie(Long id, CategorieDTO categorieDTO) {
        Categorie existingCategorie = categorieRepository.findById(id).orElse(null);
        if (existingCategorie != null) {
            existingCategorie.setNom(categorieDTO.getNom());
            existingCategorie = categorieRepository.save(existingCategorie);
            return convertToDTO(existingCategorie);
        }
        return null;
    }

    public void deleteCategorie(Long id) {
        categorieRepository.deleteById(id);
    }

    private CategorieDTO convertToDTO(Categorie categorie) {
        CategorieDTO dto = new CategorieDTO();
        dto.setId(categorie.getId());
        dto.setNom(categorie.getNom());
        return dto;
    }

    private Categorie convertToEntity(CategorieDTO dto) {
        Categorie categorie = new Categorie();
        categorie.setId(dto.getId());
        categorie.setNom(dto.getNom());
        return categorie;
    }
}
