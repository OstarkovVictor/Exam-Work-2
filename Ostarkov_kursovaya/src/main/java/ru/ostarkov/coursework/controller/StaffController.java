package ru.ostarkov.coursework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.ostarkov.coursework.repository.StaffRepository;
import ru.ostarkov.coursework.entity.Staff;
import ru.ostarkov.coursework.service.UserActionService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class StaffController {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private UserActionService userActionService; // Инжектирование UserActionService

    @GetMapping("/staff")
    public ModelAndView getAllStaff() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Получение email текущего аутентифицированного пользователя
        System.out.println("Received userEmail: " + email);

        ModelAndView mav = new ModelAndView("list-staff");

        List<Staff> staffList = staffRepository.findAll();
        mav.addObject("staff", staffList);
        mav.addObject("currentUserEmail", email); // Передаем email текущего пользователя в представление

        return mav;
    }


    @GetMapping("/addStaffForm")
    public ModelAndView addStaffForm() {
        ModelAndView mav = new ModelAndView("add-staff-form");
        Staff staff = new Staff();
        mav.addObject("staff", staff);
        return mav;
    }

    @PostMapping("/saveStaff")
    public RedirectView saveStaff(@ModelAttribute Staff staff) {
        // Получение email текущего аутентифицированного пользователя
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName(); // предполагается, что email пользователя хранится в имени пользователя

        // Установка userEmail для сотрудника
        staff.setUserEmail(userEmail);

        // Установка createdBy для сотрудника
        staff.setCreatedBy(userEmail);

        // Сохранение staff с указанием email пользователя
        Staff savedStaff = staffRepository.save(staff);

        // Логирование действия пользователя
        userActionService.logUserAction(userEmail, "Added new staff: " + savedStaff.getName() + " " + savedStaff.getSurname());

        return new RedirectView("staff");
    }


    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long staffId) {
        ModelAndView mav = new ModelAndView("add-staff-form");
        Optional<Staff> optionalStaff = staffRepository.findById(staffId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        userActionService.logUserAction(userEmail, "Show Update Form");
        Staff staff = new Staff();
        if (optionalStaff.isPresent()) {
            staff = optionalStaff.get();
        }
        mav.addObject("staff", staff);
        return mav;
    }

    @GetMapping("/deleteStaff")
    public RedirectView deleteStaff(@RequestParam Long staffId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        // Получение информации о сотруднике перед удалением
        Optional<Staff> optionalStaff = staffRepository.findById(staffId);
        if (optionalStaff.isPresent()) {
            Staff staff = optionalStaff.get();
            userActionService.logUserAction(userEmail, "Deleted staff: " + staff.getName() + " " + staff.getSurname());
        }

        // Удаление сотрудника
        staffRepository.deleteById(staffId);

        return new RedirectView("staff");
    }


}
