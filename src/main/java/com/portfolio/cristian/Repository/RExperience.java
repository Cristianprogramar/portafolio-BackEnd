package com.portfolio.cristian.Repository;

import com.portfolio.cristian.Entity.Experience;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RExperience extends JpaRepository<Experience, Integer> {
    public Optional<Experience> findByNameE(String nameE);
    public boolean existsByNameE(String nameE);
}