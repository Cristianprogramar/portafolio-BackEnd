package com.portfolio.cristian.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Size(min = 5 ,max = 100, message = "Debe ingresar al menos 5 caracteres.")
    @Column(length = 50)
    private String nameE;
    
    @Size(min = 10 ,max = 500, message = "Debe ingresar al menos 10 caracteres.")
    @Column(length = 1000)
    private String descriptionE;
    
    //Constructores
    public Experience() {
    }

    public Experience(String nameE, String descriptionE) {
        this.nameE = nameE;
        this.descriptionE = descriptionE;
    }
    
   //Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameE() {
        return nameE;
    }

    public void setNameE(String nameE) {
        this.nameE = nameE;
    }

    public String getDescriptionE() {
        return descriptionE;
    }

    public void setDescriptionE(String descriptionE) {
        this.descriptionE = descriptionE;
    }
}