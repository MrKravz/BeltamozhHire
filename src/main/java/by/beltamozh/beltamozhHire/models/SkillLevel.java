package by.beltamozh.beltamozhHire.models;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "skill_levels")
@Data
public class SkillLevel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name_of_skill_level")
    @Size(min = 2, max = 50, message = "Имя не правильного размера")
    private String name;

    @OneToMany(mappedBy = "skillLevel")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ToString.Exclude
    private List<Resume> resumes;

    @OneToMany(mappedBy = "requiredSkillLevel", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ToString.Exclude
    private List<Vacancy> vacancies;
}
