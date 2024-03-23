package ru.ostarkov.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ostarkov.coursework.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
