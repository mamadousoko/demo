package com.testdto.demo.service;

import com.testdto.demo.dto.ProduitDTO;
import com.testdto.demo.model.Categorie;
import com.testdto.demo.model.Produit;
import com.testdto.demo.repository.CategorieRepository;
import com.testdto.demo.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    public List<ProduitDTO> getAllProduits() {
        List<Produit> produits = produitRepository.findAll();
        return produits.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProduitDTO getProduitById(Long id) {
        Optional<Produit> produitOptional = produitRepository.findById(id);
        return produitOptional.map(this::convertToDTO).orElse(null);
    }

    public ProduitDTO createProduit(ProduitDTO produitDTO) {
        Produit produit = convertToEntity(produitDTO);
        Categorie categorie = categorieRepository.findById(produitDTO.getCategorieId()).orElse(null);
        produit.setCategorie(categorie);
        produit = produitRepository.save(produit);
        return convertToDTO(produit);
    }

    public ProduitDTO updateProduit(Long id, ProduitDTO produitDTO) {
        Produit existingProduit = produitRepository.findById(id).orElse(null);
        if (existingProduit != null) {
            existingProduit.setLibelle(produitDTO.getLibelle());
            existingProduit.setDateDexp(produitDTO.getDateDexp());
            existingProduit.setPrix(produitDTO.getPrix());
            existingProduit.setQuantite(produitDTO.getQuantite());
            Categorie categorie = categorieRepository.findById(produitDTO.getCategorieId()).orElse(null);
            existingProduit.setCategorie(categorie);
            existingProduit = produitRepository.save(existingProduit);
            return convertToDTO(existingProduit);
        }
        return null;
    }

    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }

    private ProduitDTO convertToDTO(Produit produit) {
        ProduitDTO dto = new ProduitDTO();
        dto.setId(produit.getId());
        dto.setLibelle(produit.getLibelle());
        dto.setDateDexp(produit.getDateDexp());
        dto.setPrix(produit.getPrix());
        dto.setQuantite(produit.getQuantite());
        dto.setCategorieId(produit.getCategorie() != null ? produit.getCategorie().getId() : null);
        return dto;
    }

    private Produit convertToEntity(ProduitDTO dto) {
        Produit produit = new Produit();
        produit.setId(dto.getId());
        produit.setLibelle(dto.getLibelle());
        produit.setDateDexp(dto.getDateDexp());
        produit.setPrix(dto.getPrix());
        produit.setQuantite(dto.getQuantite());
        return produit;
    }
}
