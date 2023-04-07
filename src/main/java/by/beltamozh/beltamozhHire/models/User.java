package by.beltamozh.beltamozhHire.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Size(min = 2, max = 50, message = "Имя не правильного размера")
    private String name;

    @Column(name = "login")
    @Size(min = 4, max = 50, message = "Логин не правильного размера")
    private String login;

    @Column(name = "password")
    @Size(min = 5, max = 50, message = "Размер пароля не должен быть " +
            "больше 50 символов и меньше 5")
    private String password;

    @OneToMany(mappedBy = "owner")
    @Lazy
    private List<Resume> resumes;
}
