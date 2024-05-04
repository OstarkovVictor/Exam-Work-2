package ru.ostarkov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ostarkov.entity.UserAction;
import ru.ostarkov.repository.UserActionRepository;
import ru.ostarkov.service.UserActionService;

import java.util.List;

@Controller
public class UserActionController {

    private final UserActionService userActionService;

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
    @GetMapping("/user-actions/clear")
    public String clearUserActions() {
        userActionService.clearAllUserActions();
        return "redirect:/user-actions";
    }
}