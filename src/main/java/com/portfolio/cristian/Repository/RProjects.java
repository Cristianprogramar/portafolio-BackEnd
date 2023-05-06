package com.portfolio.cristian.Repository;

import com.portfolio.cristian.Entity.Projects;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RProjects extends JpaRepository<Projects, Integer> {
    public Optional<Projects> findByNameP(String nameP);
    public boolean existsByNameP(String nameP);
}