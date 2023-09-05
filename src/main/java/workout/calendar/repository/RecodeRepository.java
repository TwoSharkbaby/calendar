package workout.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import workout.calendar.domain.entity.Recode;

import java.util.List;

public interface RecodeRepository extends JpaRepository<Recode, Long> {

    List<Recode> findByUserId(Long id);
}
