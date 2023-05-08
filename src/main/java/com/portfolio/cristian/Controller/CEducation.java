package com.portfolio.cristian.Controller;

import com.portfolio.cristian.Dto.dtoEducation;
import com.portfolio.cristian.Entity.Education;
import com.portfolio.cristian.Security.Controller.Mensaje;
import com.portfolio.cristian.Service.SEducation;
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
@RequestMapping("/education")
@CrossOrigin(origins = "https://argentinaprograma-f1d32.web.app")
public class CEducation {
    @Autowired
    SEducation sEducation;

    //Obtener una lista de todas las educaciones
    @GetMapping("/list")
    public ResponseEntity<List<Education>> list() {
        List<Education> list = sEducation.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    //Obtener una educación por ID
    @GetMapping("/detail/{id}")
    public ResponseEntity<Education> getById(@PathVariable("id") int id) {
        if (!sEducation.existsById(id)) {
            return new ResponseEntity(new Mensaje("No se encontró esta educación."), HttpStatus.NOT_FOUND);
        }
        Education education = sEducation.getOne(id).get();
        return new ResponseEntity(education, HttpStatus.OK);
    }

    //Eliminar una educación por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        sEducation.delete(id);
        return new ResponseEntity(new Mensaje("La educación fue eliminada."), HttpStatus.OK);
    }

    //Crear una nueva educación
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoEducation dtoedu) {
        if (StringUtils.isBlank(dtoedu.getNameEdu())) {
            return new ResponseEntity(new Mensaje("Los campos son obligatorios."), HttpStatus.BAD_REQUEST);
        }

        Education education = new Education(dtoedu.getNameEdu(), dtoedu.getDescriptionEdu());
        sEducation.save(education);

        return new ResponseEntity(new Mensaje("La educación fue agregada."), HttpStatus.OK);
    }

    //Actualizar una educación por ID
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEducation dtoedu) {
        if (!sEducation.existsById(id)) {
            return new ResponseEntity(new Mensaje("Ya existe esta educación."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtoedu.getNameEdu())) {
            return new ResponseEntity(new Mensaje("Los campos son obligatorios."), HttpStatus.BAD_REQUEST);
        }

        Education education = sEducation.getOne(id).get();
        education.setNameEdu(dtoedu.getNameEdu());
        education.setDescriptionEdu(dtoedu.getDescriptionEdu());

        sEducation.save(education);
        return new ResponseEntity(new Mensaje("La educación fue actualizada."), HttpStatus.OK);
    }
}
