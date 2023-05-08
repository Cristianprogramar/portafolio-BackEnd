package com.portfolio.cristian.Repository;

import com.portfolio.cristian.Entity.Hys;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RHys extends JpaRepository<Hys, Integer> {
    Optional<Hys> findByName(String name);
    public boolean existsByName(String name);
}