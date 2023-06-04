package by.beltamozh.beltamozhHire.models;


import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Size(min = 2, max = 50, message = "Имя не правильного размера")
    @NotNull
    @NotEmpty
    private String name;

    @Column(name = "login")
    @Size(min = 2, max = 50, message = "Логин не правильного размера")
    @NotNull
    @NotEmpty
    private String login;

    @Column(name = "password")
    @Size(min = 5, max = 30, message = "Пароль не правильного размера")
    private String password;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ToString.Exclude
    private List<Resume> resumes;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ToString.Exclude
    private List<Role> roles;

    @OneToOne(mappedBy = "user")
    @ToString.Exclude
    private BadReputationUser badReputationUser;
}
