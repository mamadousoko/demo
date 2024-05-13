package com.testdto.demo.repository;

import com.testdto.demo.model.Sortie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SortieRepository extends JpaRepository<Sortie, Long> {
}
