package by.beltamozh.beltamozhHire.models;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "technologies")
@Data
public class Technology {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_of_technology")
    @Size(min = 2, max = 50, message = "Имя не правильного размера")
    private String name;

    @ManyToMany(mappedBy = "technologies", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ToString.Exclude
    private List<Resume> resumes;

    @ManyToMany(mappedBy = "technologies", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ToString.Exclude
    private List<Vacancy> vacancies;
}
