package ru.ostarkov.coursework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ostarkov.coursework.entity.UserAction;
import ru.ostarkov.coursework.service.UserActionService;

import java.util.List;

@Controller
public class UserActionController {

    private final UserActionService userActionService; // Предположим, что у вас есть сервис для работы с действиями пользователей

    @Autowired
    public UserActionController(UserActionService userActionService) {
        this.userActionService = userActionService;
    }

    @GetMapping("/user-actions")
    public String showUserActions(Model model) {
        List<UserAction> userActions = userActionService.getAllUserActions();
        model.addAttribute("userActions", userActions);
        return "actions";
    }
}
