package com.portfolio.cristian.Service;

import com.portfolio.cristian.Entity.Projects;
import com.portfolio.cristian.Repository.RProjects;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SProjects {
    @Autowired
    RProjects rProjects;
    
    public List<Projects> list() {
        return rProjects.findAll();
    }

    public Optional<Projects> getOne(int id) {
        return rProjects.findById(id);
    }

    public Optional<Projects> getByNameP(String nameP) {
        return rProjects.findByNameP(nameP);
    }

    public void save(Projects pro) {
        rProjects.save(pro);
    }

    public void delete(int id) {
        rProjects.deleteById(id);
    }

    public boolean existsById(int id) {
        return rProjects.existsById(id);
    }

    public boolean existsByNameEdu(String nameP) {
        return rProjects.existsByNameP(nameP);
    }
}