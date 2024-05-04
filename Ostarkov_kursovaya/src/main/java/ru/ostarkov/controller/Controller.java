package ru.ostarkov.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.ostarkov.repository.TeacherRepository;
import ru.ostarkov.entity.Teachers;
import ru.ostarkov.service.UserActionService;

import java.util.Optional;

@Slf4j
@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private UserActionService userActionService; // Инжектирование UserActionService
    @GetMapping("/about")
    public String aboutPage() {
        return "about"; // Возвращает имя представления "about"
    }


    @GetMapping("/teachers")
    public ModelAndView getAllTeachers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Получение email текущего аутентифицированного пользователя
        System.out.println("Received userEmail: " + email);

        ModelAndView mav = new ModelAndView("list-teachers");

        java.util.List<Teachers> teacherList = teacherRepository.findAll();
        mav.addObject("teacher", teacherList);
        mav.addObject("currentUserEmail", email); // Передаем email текущего пользователя в представление

        return mav;
    }


    @GetMapping("/addTeacherForm")
    public ModelAndView addTeacherForm() {
        ModelAndView mav = new ModelAndView("add-teacher");
        Teachers teacher = new Teachers();
        mav.addObject("teacher", teacher);
        return mav;
    }

    @PostMapping("/saveTeacher")
    public RedirectView saveTeacher(@ModelAttribute Teachers teacher) {
        // Получение email текущего аутентифицированного пользователя
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // предполагается, что email пользователя хранится в имени пользователя

        // Установка userEmail для сотрудника
        teacher.setUserEmail(userEmail);

        // Установка createdBy для сотрудника
        teacher.setCreatedBy(userEmail);

        // Сохранение teacher с указанием email пользователя
        Teachers savedTeacher = teacherRepository.save(teacher);

        // Логирование действия пользователя
        userActionService.logUserAction(userEmail, "Added new teacher: " + savedTeacher.getName() + " " + savedTeacher.getCourse());

        return new RedirectView("teachers");
    }


    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long teacherId) {
        ModelAndView mav = new ModelAndView("add-teacher");
        Optional<Teachers> optionalTeachers = teacherRepository.findById(teacherId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        userActionService.logUserAction(userEmail, "Show Update Form");
        Teachers teacher = new Teachers();
        if (optionalTeachers.isPresent()) {
            teacher = optionalTeachers.get();
        }
        mav.addObject("teachers", teacher);
        return mav;
    }

    @GetMapping("/deleteTeacher")
    public RedirectView deleteTeacher(@RequestParam Long teacherId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        // Получение информации о сотруднике перед удалением
        Optional<Teachers> optionalTeacher = teacherRepository.findById(teacherId);
        if (optionalTeacher.isPresent()) {
            Teachers teacher = optionalTeacher.get();
            userActionService.logUserAction(userEmail, "Deleted teacher: " + teacher.getName() + " " + teacher.getCourse());
        }

        // Удаление сотрудника
        teacherRepository.deleteById(teacherId);

        return new RedirectView("teachers");
    }


}
