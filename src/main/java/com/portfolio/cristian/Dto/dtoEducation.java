package com.portfolio.cristian.Dto;

import javax.validation.constraints.NotBlank;

public class dtoEducation {
    @NotBlank
    private String nameEdu;

    @NotBlank
    private String descriptionEdu;
    
    //Constructores
    public dtoEducation() {
    }

    public dtoEducation(String nameEdu, String descriptionEdu) {
        this.nameEdu = nameEdu;
        this.descriptionEdu = descriptionEdu;
    }
    
    //Getters and setters
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
