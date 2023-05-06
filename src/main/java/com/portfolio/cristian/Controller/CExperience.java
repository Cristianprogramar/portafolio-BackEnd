package com.portfolio.cristian.Controller;

import com.portfolio.cristian.Dto.dtoExperience;
import com.portfolio.cristian.Entity.Experience;
import com.portfolio.cristian.Security.Controller.Mensaje;
import com.portfolio.cristian.Service.SExperience;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("workexp")
@CrossOrigin(origins = "http://localhost:4200")
public class CExperience {

    @Autowired
    SExperience sExperience;

    //Obtiene una lista de todas las experiencias
    @GetMapping("/list")
    public ResponseEntity<List<Experience>> list() {
        List<Experience> list = sExperience.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    //Obtiene una experiencia por ID
    @GetMapping("/detail/{id}")
    public ResponseEntity<Experience> getById(@PathVariable("id") int id) {
        if (!sExperience.existsById(id)) {
            return new ResponseEntity(new Mensaje("No se encontró el ID."), HttpStatus.NOT_FOUND);
        }
        Experience experience = sExperience.getOne(id).get();
        return new ResponseEntity(experience, HttpStatus.OK);
    }

    //Elimina una experiencia por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        sExperience.delete(id);
        return new ResponseEntity(new Mensaje("La experiencia fue eliminada."), HttpStatus.OK);
    }

    //Crea una nueva experiencia
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoExperience dtoexp) {
        if (StringUtils.isBlank(dtoexp.getNameE())) {
            return new ResponseEntity(new Mensaje("Los campos son obligatorios."), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoexp.getDescriptionE())) {
            return new ResponseEntity(new Mensaje("Los campos son obligatorios."), HttpStatus.BAD_REQUEST);
        }

        Experience experience = new Experience(dtoexp.getNameE(), dtoexp.getNameE());
        sExperience.save(experience);

        return new ResponseEntity(new Mensaje("Experiencia agregada."), HttpStatus.OK);
    }

    //Actualiza una experiencia por ID
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoExperience dtoexp) {
        if (!sExperience.existsById(id)) {
            return new ResponseEntity(new Mensaje("Ya existe el ID."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoexp.getNameE())) {
            return new ResponseEntity(new Mensaje("Los campos son obligatorios."), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoexp.getDescriptionE())) {
            return new ResponseEntity(new Mensaje("Los campos son obligatorios."), HttpStatus.BAD_REQUEST);
        }

        Experience experience = sExperience.getOne(id).get();
        experience.setNameE(dtoexp.getNameE());
        experience.setDescriptionE((dtoexp.getDescriptionE()));

        sExperience.save(experience);
        return new ResponseEntity(new Mensaje("La experiencia fue actualizada."), HttpStatus.OK);
    }
}
