package com.portfolio.cristian.Entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Persona implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(min = 1, max = 50, message = "Debe ingresar al menos un dato.")
    private String nombre;
    
    @NotNull
    @Size(min = 1, max = 50, message = "Debe ingresar al menos un dato.")
    private String apellido;
    
    @Size(min = 1, max = 50, message = "Debe ingresar al menos un dato.")
    private String img;
}