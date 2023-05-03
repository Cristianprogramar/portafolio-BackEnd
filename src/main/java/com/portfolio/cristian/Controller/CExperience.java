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
@CrossOrigin(origins = "*")
public class CExperience {

    @Autowired
    SExperience sExperience;

    @GetMapping("/list")
    public ResponseEntity<List<Experience>> list() {
        List<Experience> list = sExperience.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Experience> getById(@PathVariable("id") int id) {
        if (!sExperience.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Experience experience = sExperience.getOne(id).get();
        return new ResponseEntity(experience, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sExperience.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        sExperience.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoExperience dtoexp) {
        if (StringUtils.isBlank(dtoexp.getNameE())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio."), HttpStatus.BAD_REQUEST);
        }

        Experience experiencia = new Experience(dtoexp.getNameE(), dtoexp.getDescriptionE());
        sExperience.save(experiencia);

        return new ResponseEntity(new Mensaje("Experiencia añadida."), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoExperience dtoexp) {
        //Validamos si existe el ID
        if (!sExperience.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe."), HttpStatus.BAD_REQUEST);
        }

        //Compara nombre de experiencias
        if (sExperience.existsByNameE(dtoexp.getNameE()) && sExperience.getByNameE(dtoexp.getNameE()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esta experiencia ya existe."), HttpStatus.BAD_REQUEST);
        }

        //No puede estar vacio
        if (StringUtils.isBlank(dtoexp.getNameE())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio."), HttpStatus.BAD_REQUEST);
        }

        Experience experiencia = sExperience.getOne(id).get();
        experiencia.setNameE(dtoexp.getNameE());
        experiencia.setDescriptionE((dtoexp.getDescriptionE()));

        sExperience.save(experiencia);
        return new ResponseEntity(new Mensaje("Experiencia actualizada."), HttpStatus.OK);
    }
}