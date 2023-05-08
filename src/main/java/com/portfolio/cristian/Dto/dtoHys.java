package com.portfolio.cristian.Dto;

import javax.validation.constraints.NotBlank;

public class dtoHys {
    @NotBlank
    private String name;
    
    @NotBlank
    private int percent;
    
    //Constructores
    public dtoHys() {
    }

    public dtoHys(String name, int percent) {
        this.name = name;
        this.percent = percent;
    }
   
    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    } 
}
