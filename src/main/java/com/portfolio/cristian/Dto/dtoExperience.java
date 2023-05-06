package com.portfolio.cristian.Dto;

import javax.validation.constraints.NotBlank;

public class dtoExperience {
    @NotBlank
    private String nameE;
    @NotBlank
    private String descriptionE;
    
    //Constructores
    public dtoExperience() {
    }

    public dtoExperience(String nameE, String descriptionE) {
        this.nameE = nameE;
        this.descriptionE = descriptionE;
    }
    
    //Getters and setters
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