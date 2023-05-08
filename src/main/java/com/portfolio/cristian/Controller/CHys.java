package com.portfolio.cristian.Controller;

import com.portfolio.cristian.Dto.dtoHys;
import com.portfolio.cristian.Entity.Hys;
import com.portfolio.cristian.Security.Controller.Mensaje;
import com.portfolio.cristian.Service.SHys;
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
@RequestMapping("/hys")
@CrossOrigin(origins = "http://localhost:4200")
public class CHys {
    @Autowired
    SHys sHys;

    //Obtener una lista de todas las skills
    @GetMapping("/list")
    public ResponseEntity<List<Hys>> list() {
        List<Hys> list = sHys.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    //Obtener una skill por ID
    @GetMapping("/detail/{id}")
    public ResponseEntity<Hys> getById(@PathVariable("id") int id) {
        if (!sHys.existsById(id)) {
            return new ResponseEntity(new Mensaje("No se encontró la skill."), HttpStatus.NOT_FOUND);
        }
        Hys hYs = sHys.getOne(id).get();
        return new ResponseEntity(hYs, HttpStatus.OK);
    }

    //Eliminar una skill por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sHys.existsById(id)) {
            return new ResponseEntity(new Mensaje("No se encontró la skill."), HttpStatus.NOT_FOUND);
        }
        sHys.delete(id);
        return new ResponseEntity(new Mensaje("La skill fue eliminada."), HttpStatus.OK);
    }

    //Crear una nueva skill
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHys dtohys) {
        if (StringUtils.isBlank(dtohys.getName())) {
            return new ResponseEntity(new Mensaje("Los campos son obligatorios."), HttpStatus.BAD_REQUEST);
        }
        if (sHys.existsByName(dtohys.getName())) {
            return new ResponseEntity(new Mensaje("Ya existe esta skill."), HttpStatus.BAD_REQUEST);
        }

        Hys hYs = new Hys(dtohys.getName(), dtohys.getPercent());
        sHys.save(hYs);

        return new ResponseEntity(new Mensaje("La skill fue agregada."), HttpStatus.OK);
    }

    //Actualizar una skill por ID
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHys dtohys) {
        //Validamos si existe el ID
        if (!sHys.existsById(id)) {
            return new ResponseEntity(new Mensaje("No se encontró la skill."), HttpStatus.BAD_REQUEST);
        }
        
        if (sHys.existsByName(dtohys.getName()) && sHys.getByName(dtohys.getName()).get()
                .getId() != id) {
            return new ResponseEntity(new Mensaje("Ya existe esta skill."), HttpStatus.BAD_REQUEST);
        }
        
        if (StringUtils.isBlank(dtohys.getName())) {
            return new ResponseEntity(new Mensaje("Los campos son obligatorios."), HttpStatus.BAD_REQUEST);
        }

        Hys hYs = sHys.getOne(id).get();
        hYs.setName(dtohys.getName());
        hYs.setPercent(dtohys.getPercent());

        sHys.save(hYs);
        return new ResponseEntity(new Mensaje("La skill fue actualizada."), HttpStatus.OK);
    }
}