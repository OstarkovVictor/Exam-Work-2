package ru.ostarkov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ostarkov.entity.UserAction;

public interface UserActionRepository extends JpaRepository<UserAction, Long> {

    void deleteAll();
}

