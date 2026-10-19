package com.wegrow.repository;

import com.wegrow.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    long countByDepartement_Id(Long id);
    boolean existsByMatricule(String matricule);

    List<Employee> findByDepartement_Id(Long id);
    boolean existsByMatriculeAndIdNot(String matricule, Long id);

}
