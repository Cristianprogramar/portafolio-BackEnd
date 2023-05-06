package com.portfolio.cristian.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    private String nameEdu;

    @Column(length = 1000)
    private String descriptionEdu;
    
    //Constructores
    public Education() {
    }

    public Education(String nameEdu, String descriptionEdu) {
        this.nameEdu = nameEdu;
        this.descriptionEdu = descriptionEdu;
    }
    
    //Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameEdu() {
        return nameEdu;
    }

    public void setNameEdu(String nameEdu) {
        this.nameEdu = nameEdu;
    }

    public String getDescriptionEdu() {
        return descriptionEdu;
    }

    public void setDescriptionEdu(String descriptionEdu) {
        this.descriptionEdu = descriptionEdu;
    }
}
