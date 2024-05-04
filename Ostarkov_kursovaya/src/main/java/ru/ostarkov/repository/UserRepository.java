package ru.ostarkov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ostarkov.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
