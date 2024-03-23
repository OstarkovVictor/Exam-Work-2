package ru.ostarkov.coursework.service;

import ru.ostarkov.coursework.dto.UserDto;
import ru.ostarkov.coursework.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);
    //void giveUserRole(String userEmail);
    User findUserByEmail(String email);
    List<Object> findAllUsers();

    void addAdminRoleToUser(String userEmail);

    void addReadRoleToUser(String userEmail);

    void addUserRoleToUser(String userEmail);

    void toggleAdminRole(String userEmail);

    void toggleReadRole(String userEmail);

    void toggleUserRole(String userEmail);

}

