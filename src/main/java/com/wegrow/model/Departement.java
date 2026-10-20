package com.wegrow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "departement")
public class Departement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @Column(nullable = false , unique = true)
    private String name ;

    private String description;

    @OneToMany(mappedBy = "departement")
    @JsonIgnore
    private List<Employee> employees;

    @Transient
    private long  nombreEmployes;


    public Departement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public long getNombreEmployes() {
        return nombreEmployes;
    }

    public void setNombreEmployes(long nombreEmployes) {
        this.nombreEmployes = nombreEmployes;
    }

}
