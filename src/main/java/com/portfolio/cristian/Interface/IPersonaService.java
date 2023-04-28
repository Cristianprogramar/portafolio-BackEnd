package com.portfolio.cristian.Interface;

import com.portfolio.cristian.Entity.Persona;
import java.util.List;

public interface IPersonaService {
    
    //Traer una lista de personas
    public List<Persona> getPersona();
    
    //Guardar un objeto de tipo Persona
    public void savePersona(Persona persona);
    
    //Eliminar un objeto y lo buscamos por ID
    public void deletePersona(Long id);
    
    //Buscar una persona por ID
    public Persona findPersona(Long id);
}
