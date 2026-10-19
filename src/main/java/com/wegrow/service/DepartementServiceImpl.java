package com.wegrow.service;

import com.wegrow.model.Departement;
import com.wegrow.repository.DepartementRepository;
import com.wegrow.repository.EmployeeRepository;

import java.util.List;

public class DepartementServiceImpl implements DepartementService{

    private DepartementRepository departementRepository ;
    private  EmployeeRepository employeeRepository;

    public DepartementServiceImpl(DepartementRepository departementRepository , EmployeeRepository employeeRepository) {
        this.departementRepository = departementRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Departement create(Departement departement) {
        if(departementRepository.existsByName(departement.getName())){
            throw new RuntimeException("this department already exists");
        }
            return departementRepository.save(departement);
    }

    @Override
    public List<Departement> getAll() {
        List<Departement> departments = departementRepository.findAll();
        for(Departement departement : departments){
            long count = employeeRepository.countByDepartement_Id(departement.getId());
            departement.setNombreEmployes((int) count);
        }
        return departments;
    }

    @Override
    public void delete(Long id) {
        if(!departementRepository.existsById(id)){
            throw new RuntimeException("departement not found");
        }
        if(employeeRepository.countByDepartement_Id(id) > 0){
            throw new RuntimeException("Cannot delete a department that has employees");
        }
        departementRepository.deleteById(id);
    }
}
