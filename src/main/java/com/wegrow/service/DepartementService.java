package com.wegrow.service;

import com.wegrow.model.Departement;

import java.util.List;

public interface DepartementService {

     Departement create(Departement departement);

     List<Departement> getAll();

     void delete(Long id );
}
