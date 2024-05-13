package com.testdto.demo.service;

import com.testdto.demo.dto.EntreeDTO;
import com.testdto.demo.model.Entree;
import com.testdto.demo.repository.EntreeRepository;
import com.testdto.demo.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntreeService {

    @Autowired
    private EntreeRepository entreeRepository;

    @Autowired
    private ProduitRepository produitRepository;

    public List<EntreeDTO> getAllEntrees() {
        List<Entree> entrees = entreeRepository.findAll();
        return entrees.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EntreeDTO getEntreeById(Long id) {
        Optional<Entree> entreeOptional = entreeRepository.findById(id);
        return entreeOptional.map(this::convertToDTO).orElse(null);
    }

    public EntreeDTO createEntree(EntreeDTO entreeDTO) {
        Entree entree = convertToEntity(entreeDTO);
        entree = entreeRepository.save(entree);
        return convertToDTO(entree);
    }

    public EntreeDTO updateEntree(Long id, EntreeDTO entreeDTO) {
        Entree existingEntree = entreeRepository.findById(id).orElse(null);
        if (existingEntree != null) {
            existingEntree.setProduit(produitRepository.findById(entreeDTO.getProduitId()).orElse(null));
            existingEntree.setQuantite(entreeDTO.getQuantite());
            existingEntree.setDate(entreeDTO.getDate());
            existingEntree = entreeRepository.save(existingEntree);
            return convertToDTO(existingEntree);
        }
        return null;
    }

    public void deleteEntree(Long id) {
        entreeRepository.deleteById(id);
    }

    private EntreeDTO convertToDTO(Entree entree) {
        EntreeDTO dto = new EntreeDTO();
        dto.setId(entree.getId());
        dto.setProduitId(entree.getProduit() != null ? entree.getProduit().getId() : null);
        dto.setQuantite(entree.getQuantite());
        dto.setDate(entree.getDate());
        return dto;
    }

    private Entree convertToEntity(EntreeDTO dto) {
        Entree entree = new Entree();
        entree.setId(dto.getId());
        entree.setProduit(produitRepository.findById(dto.getProduitId()).orElse(null));
        entree.setQuantite(dto.getQuantite());
        entree.setDate(dto.getDate());
        return entree;
    }
}
