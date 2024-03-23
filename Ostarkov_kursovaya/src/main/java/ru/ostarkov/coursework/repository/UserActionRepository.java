package ru.ostarkov.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ostarkov.coursework.entity.UserAction;

public interface UserActionRepository extends JpaRepository<UserAction, Long> {


}

