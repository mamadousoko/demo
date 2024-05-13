package com.testdto.demo.repository;

import com.testdto.demo.model.Entree ;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntreeRepository extends JpaRepository<Entree, Long> {
}
