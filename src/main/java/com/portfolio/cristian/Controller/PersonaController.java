package com.portfolio.cristian.Controller;

import com.portfolio.cristian.Entity.Persona;
import com.portfolio.cristian.Interface.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//Permito el acceso a el front
@CrossOrigin(origins = "http://localhost:4200")

public class PersonaController {
    
    @Autowired IPersonaService ipersonaService;
    
    //Traigo a la persona
    @GetMapping("user/get")
    public List<Persona> getPersona() {
        return ipersonaService.getPersona();
    }
    
    //Creo la persona
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/user/create")
    public String createPersona(@RequestBody Persona persona) {
        ipersonaService.savePersona(persona);
        return "El usuario fue creado.";
    }
    
    //Elimino la persona por ID
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/user/delete/{id}")
    public String deletePersona(@PathVariable Long id) {
        ipersonaService.deletePersona(id);
        return "El usuario fue eliminado.";
    }
    
    //Edito la persona por ID
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/user/edit/{id}")
    public Persona editPersona(@PathVariable Long id, 
            @RequestParam("nombre") String nuevoNombre,
            @RequestParam("apellido") String nuevoApellido,
            @RequestParam("img") String nuevoImg) {
        Persona persona = ipersonaService.findPersona(id);
        
        persona.setNombre(nuevoNombre);
        persona.setApellido(nuevoApellido);
        persona.setImg(nuevoImg);
        
        ipersonaService.savePersona(persona);
        return persona;
    }
    
    //Traigo mi nombre al front-end
    @GetMapping("/user/get/profile")
    public Persona findPersona() {
        return ipersonaService.findPersona((long) 1);
    }
}