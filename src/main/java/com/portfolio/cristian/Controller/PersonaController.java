package com.portfolio.cristian.Controller;

import com.portfolio.cristian.Entity.Persona;
import com.portfolio.cristian.Interface.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PersonaController {
    @Autowired
    IPersonaService ipersonaService;

    //Traer la lista de usuarios
    @GetMapping("user/get")
    public List<Persona> getPersona() {
        List<Persona> personas = ipersonaService.getPersona();
        if (personas.isEmpty()) {
            throw new IllegalStateException("No se pudo obtener la lista.");
        }
        return personas;
    }

    //Crear la persona
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/user/create")
    public String createPersona(@RequestBody Persona persona) {
        if (persona.getNombre() == null || persona.getNombre().isEmpty()
                || persona.getApellido() == null || persona.getApellido().isEmpty()
                || persona.getImg() == null || persona.getImg().isEmpty()) {
            throw new IllegalArgumentException("Todos los campos son obligatorios.");
        }
        ipersonaService.savePersona(persona);
        return "El usuario fue creado.";
    }

    //Eliminar la persona por ID
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<String> deletePersona(@PathVariable Long id) {
        try {
            ipersonaService.deletePersona(id);
            return ResponseEntity.ok("El usuario fue eliminado.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("No se pudo eliminar el usuario.");
        }
    }

    //Editar la persona por ID
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

    //Traer mi nombre al front
    @GetMapping("/user/get/profile")
    public Persona findPersona() {
        return ipersonaService.findPersona((long) 1);
    }

    //Manejar las excepciones
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException ex) {
        return ex.getMessage();
    }
}
