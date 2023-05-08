package com.portfolio.cristian.Service;

import com.portfolio.cristian.Entity.Hys;
import com.portfolio.cristian.Repository.RHys;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class SHys {
    @Autowired
    RHys rHys;
    
    public List<Hys> list() {
        return rHys.findAll();
    }
    
    public Optional<Hys> getOne(int id) {
        return rHys.findById(id);
    }
    
    public Optional<Hys> getByName(String name) {
        return rHys.findByName(name);
    }
    
    public void save(Hys skill) {
        rHys.save(skill);
    }
    
    public void delete(int id) {
        rHys.deleteById(id);
    }
    
    public boolean existsById(int id) {
        return rHys.existsById(id);
    }
    
    public boolean existsByName(String name) {
        return rHys.existsByName(name);
    }
}
