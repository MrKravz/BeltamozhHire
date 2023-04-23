package by.beltamozh.beltamozhHire.models;


import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
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
    //@Size(min = 2, max = 50, message = "Имя не правильного размера")
    private String name;

    @Column(name = "login")
    //@Size(min = 4, max = 50, message = "Логин не правильного размера")
    private String login;

    @Column(name = "password")
   /* @Size(min = 5, max = 50, message = "Размер пароля не должен быть " +
            "больше 50 символов и меньше 5")*/
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
