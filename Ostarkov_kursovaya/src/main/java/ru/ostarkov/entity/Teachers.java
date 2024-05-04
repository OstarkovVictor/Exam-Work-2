package ru.ostarkov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TEACHERS")
public class Teachers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "course")
    private String course;

    @Column(name = "classroom", columnDefinition = "DECIMAL(20)")
    private BigInteger classroom;

    @Column(name = "email") // Добавляем поле для email
    private String email;

    // Геттер для поля email
    public String getEmail() {
        return email;
    }

    // Сеттер для поля email
    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "user_email") // New field for user email
    private String userEmail;

    @Column(name = "created_by")
    private String createdBy;

    // Геттер для поля createdBy
    public String getCreatedBy() {
        return createdBy;
    }

    // Сеттер для поля createdBy
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    // Сеттер для поля userEmail
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }



}
