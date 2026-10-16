package com.wegrow.model;

import com.wegrow.enums.TypeContrat;
import jakarta.persistence.*;

@Entity
@Table(name = "employ")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String firstName ;
    private String lastName ;

    @Column(nullable = false , unique = true)
    private String matricule ;

    private String poste ;

    @Enumerated(EnumType.STRING)
    private TypeContrat contrat ;

    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;

    public Employee() {
    }

    public Long getId() {
        return id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public TypeContrat getContrat() {
        return contrat;
    }

    public void setContrat(TypeContrat contrat) {
        this.contrat = contrat;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }
}
