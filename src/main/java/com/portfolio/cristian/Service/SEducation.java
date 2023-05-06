package com.portfolio.cristian.Service;

import com.portfolio.cristian.Entity.Education;
import com.portfolio.cristian.Repository.REducation;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SEducation {
    @Autowired
    REducation rEducation;

    public List<Education> list() {
        return rEducation.findAll();
    }

    public Optional<Education> getOne(int id) {
        return rEducation.findById(id);
    }

    public Optional<Education> getByNameEdu(String nameEdu) {
        return rEducation.findByNameEdu(nameEdu);
    }

    public void save(Education edu) {
        rEducation.save(edu);
    }

    public void delete(int id) {
        rEducation.deleteById(id);
    }

    public boolean existsById(int id) {
        return rEducation.existsById(id);
    }

    public boolean existsByNameEdu(String nameEdu) {
        return rEducation.existsByNameEdu(nameEdu);
    }
}
