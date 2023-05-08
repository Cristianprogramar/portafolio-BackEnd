package com.portfolio.cristian.Entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Persona implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @Size(min = 1, max = 50, message = "Debe ingresar al menos un dato.")
    private String nombre;
    
    @NotNull
    @Size(min = 1, max = 50, message = "Debe ingresar al menos un dato.")
    private String apellido;
    
    @Size(min = 1, max = 500, message = "Debe ingresar al menos un dato.")
    @NotNull
    private String description;
    
    private String img;
    
    //Constructores
    public Persona() {
    }

    public Persona(String nombre, String apellido, String description, String img) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.description = description;
        this.img = img;
    }
    
    //Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}