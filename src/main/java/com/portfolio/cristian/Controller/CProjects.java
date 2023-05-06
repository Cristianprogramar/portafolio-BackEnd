package com.portfolio.cristian.Controller;

import com.portfolio.cristian.Dto.dtoProjects;
import com.portfolio.cristian.Entity.Projects;
import com.portfolio.cristian.Security.Controller.Mensaje;
import com.portfolio.cristian.Service.SProjects;
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
@RequestMapping("projects")
@CrossOrigin(origins = "http://localhost:4200")
public class CProjects {
    @Autowired
    SProjects sProjects;

    //Obtiene una lista de todos los proyectos
    @GetMapping("/list")
    public ResponseEntity<List<Projects>> list() {
        List<Projects> list = sProjects.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    //Obtiene un proyecto por ID
    @GetMapping("/detail/{id}")
    public ResponseEntity<Projects> getById(@PathVariable("id") int id) {
        if (!sProjects.existsById(id)) {
            return new ResponseEntity(new Mensaje("No se encontró el ID."), HttpStatus.NOT_FOUND);
        }
        Projects projects = sProjects.getOne(id).get();
        return new ResponseEntity(projects, HttpStatus.OK);
    }

    //Elimina un proyecto por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        sProjects.delete(id);
        return new ResponseEntity(new Mensaje("El proyecto fue eliminado."), HttpStatus.OK);
    }

    //Crea un nuevo proyecto
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoProjects dtopro) {
        if (StringUtils.isBlank(dtopro.getNameP())) {
            return new ResponseEntity(new Mensaje("Los campos son obligatorios."), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtopro.getDescriptionP())) {
            return new ResponseEntity(new Mensaje("Los campos son obligatorios."), HttpStatus.BAD_REQUEST);
        }

        Projects projects = new Projects(dtopro.getNameP(), dtopro.getDescriptionP());
        sProjects.save(projects);

        return new ResponseEntity(new Mensaje("Proyecto agregado."), HttpStatus.OK);
    }

    //Actualiza un proyecto por ID
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoProjects dtopro) {
        if (!sProjects.existsById(id)) {
            return new ResponseEntity(new Mensaje("Ya existe el ID."), HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(dtopro.getNameP())) {
            return new ResponseEntity(new Mensaje("Los campos son obligatorios."), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtopro.getDescriptionP())) {
            return new ResponseEntity(new Mensaje("Los campos son obligatorios."), HttpStatus.BAD_REQUEST);
        }

        Projects projects = sProjects.getOne(id).get();
        projects.setNameP(dtopro.getNameP());
        projects.setDescriptionP(dtopro.getDescriptionP());

        sProjects.save(projects);
        return new ResponseEntity(new Mensaje("El proyecto fue actualizado."), HttpStatus.OK);
    }
}
