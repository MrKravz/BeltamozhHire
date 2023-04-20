package by.beltamozh.beltamozhHire.models;

import lombok.*;

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
    @ToString.Exclude
    private List<Resume> resumes;

    @ManyToMany(mappedBy = "technologies", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Vacancy> vacancies;
}
