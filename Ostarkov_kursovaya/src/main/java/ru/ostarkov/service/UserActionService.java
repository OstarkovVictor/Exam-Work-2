package ru.ostarkov.service;

import org.springframework.stereotype.Service;
import ru.ostarkov.entity.UserAction;
import ru.ostarkov.repository.UserActionRepository;

import java.time.LocalDateTime;

import java.util.List;

@Service
public class UserActionService {

    private final UserActionRepository userActionRepository;

    public UserActionService(UserActionRepository userActionRepository) {
        this.userActionRepository = userActionRepository;
    }

    public void logUserAction(String userEmail, String action) {
        System.out.println("Received userEmail: " + userEmail);
        UserAction userAction = new UserAction();
        userAction.setUserEmail(userEmail);
        userAction.setAction(action);
        userAction.setTimestamp(LocalDateTime.now());

        userActionRepository.save(userAction);
    }
    public void clearAllUserActions() {
        userActionRepository.deleteAll();
    }
    public List<UserAction> getAllUserActions() {
        return userActionRepository.findAll();
    }
}
