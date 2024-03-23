package ru.ostarkov.coursework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    @GetMapping("/about")
    public String aboutPage() {
        return "about"; // Возвращает имя представления "about"
    }
}

