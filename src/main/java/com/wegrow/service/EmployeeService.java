package com.wegrow.service;

import com.wegrow.model.Employee;

import java.util.List;

public interface EmployeeService  {

    Employee create(Employee employee , Long departmentId);

    List<Employee> getAll(Long departmentId);

    Employee update(Employee employee,  Long departmentId);

    void delete(Long id);


}
