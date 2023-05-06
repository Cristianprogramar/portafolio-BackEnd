package com.portfolio.cristian.Dto;

import javax.validation.constraints.NotBlank;

public class dtoProjects {
    @NotBlank
    private String nameP;
    
    @NotBlank
    private String descriptionP;
    
    //Constructores
    public dtoProjects() {
    }

    public dtoProjects(String nameP, String descriptionP) {
        this.nameP = nameP;
        this.descriptionP = descriptionP;
    }
    
    //Getters and setters
    public String getNameP() {
        return nameP;
    }

    public void setNameP(String nameP) {
        this.nameP = nameP;
    }

    public String getDescriptionP() {
        return descriptionP;
    }

    public void setDescriptionP(String descriptionP) {
        this.descriptionP = descriptionP;
    } 
}
