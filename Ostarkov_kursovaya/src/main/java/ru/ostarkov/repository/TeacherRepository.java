package ru.ostarkov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ostarkov.entity.Teachers;

@Repository
public interface TeacherRepository extends JpaRepository<Teachers, Long> {
    java.util.List<Teachers> findByUserEmail(String userEmail);

}
