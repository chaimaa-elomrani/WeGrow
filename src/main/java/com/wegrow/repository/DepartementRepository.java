package com.wegrow.repository;

import com.wegrow.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement, Long> {

    boolean existsByName(String name);
}
