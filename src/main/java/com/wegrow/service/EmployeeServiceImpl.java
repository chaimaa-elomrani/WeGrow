package com.wegrow.service;

import com.wegrow.model.Departement;
import com.wegrow.model.Employee;
import com.wegrow.repository.DepartementRepository;
import com.wegrow.repository.EmployeeRepository;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;
    private final DepartementRepository departementRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartementRepository departementRepository) {
        this.employeeRepository = employeeRepository;
        this.departementRepository = departementRepository;
    }

    @Override
    public Employee create(Employee employee, Long departmentId) {
        if(employeeRepository.existsByMatricule(employee.getMatricule())){
            throw new RuntimeException("matricule can't be duplicated");
        }
        Departement department = departementRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("department doesn't exist"));
        employee.setDepartement(department);
        return employeeRepository.save(employee);
    }


    @Override
    public List<Employee> getAll(Long departmentId) {
         if(!departementRepository.existsById(departmentId)){
             throw new RuntimeException("Department doesn't exist");
         }
         return employeeRepository.findByDepartement_Id(departmentId);
    }

    @Override
    public Employee update(Employee employee, Long departmentId) {
       Employee existingEmployee = employeeRepository.findById(employee.getId())
               .orElseThrow(() -> new RuntimeException("employee doesnt exist"));

        if(employeeRepository.existsByMatriculeAndIdNot(employee.getMatricule() , employee.getId())){
            throw new RuntimeException("Matricule already exists");
        }

        Departement department = departementRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("makanach depart"));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setMatricule(employee.getMatricule());
        existingEmployee.setPoste(employee.getPoste());
        existingEmployee.setContrat(employee.getContrat());
        existingEmployee.setDepartement(department);
        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void delete(Long id) {
        if (!employeeRepository.existsById(id)){
            throw new RuntimeException("empl makaynch");
        }
         employeeRepository.deleteById(id);
    }
}
