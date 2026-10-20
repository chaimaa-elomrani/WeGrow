package com.wegrow.service;

import com.wegrow.model.Departement;
import com.wegrow.model.Employee;
import com.wegrow.repository.DepartementRepository;
import com.wegrow.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "An employee with matricule '" + employee.getMatricule() + "' already exists");
        }
        Departement department = departementRepository.findById(departmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Department " + departmentId + " does not exist"));
        employee.setDepartement(department);
        return employeeRepository.save(employee);
    }


    @Override
    public List<Employee> getAll(Long departmentId) {
         if(!departementRepository.existsById(departmentId)){
             throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                     "Department " + departmentId + " does not exist");
         }
         return employeeRepository.findByDepartement_Id(departmentId);
    }

    @Override
    public Employee update(Employee employee, Long departmentId) {
        Employee existingEmployee = employeeRepository.findById(employee.getId())
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                       "Employee " + employee.getId() + " does not exist"));

        if(employeeRepository.existsByMatriculeAndIdNot(employee.getMatricule() , employee.getId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "An employee with matricule '" + employee.getMatricule() + "' already exists");
        }

        Departement department = departementRepository.findById(departmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Department " + departmentId + " does not exist"));

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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Employee " + id + " does not exist");
        }
         employeeRepository.deleteById(id);
    }
}
