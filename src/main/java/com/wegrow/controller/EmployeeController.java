package com.wegrow.controller;

import com.wegrow.model.Employee;
import com.wegrow.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<Employee>>getAll(@PathVariable Long departmentId){
        return ResponseEntity.ok(employeeService.getAll(departmentId));
    }

    @PostMapping("/department/{departmentId}")
    public ResponseEntity<Employee> create(@RequestBody Employee employee, @PathVariable Long departmentId){
        Employee createdEmpl = employeeService.create(employee, departmentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmpl);    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/department/{departmentId}")
    public ResponseEntity<Employee> update(@RequestBody Employee employee , @PathVariable Long departmentId, @PathVariable Long id){
        employee.setId(id);
        Employee updateEmployee = employeeService.update(employee, departmentId);
        return ResponseEntity.ok(updateEmployee);
    }

}
