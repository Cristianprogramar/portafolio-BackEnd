package com.portfolio.cristian.Repository;

import com.portfolio.cristian.Entity.Education;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface REducation extends JpaRepository<Education, Integer> {
    public Optional<Education> findByNameEdu(String nameEdu);
    public boolean existsByNameEdu(String nameEdu);
}
