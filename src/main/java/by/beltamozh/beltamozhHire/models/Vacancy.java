package by.beltamozh.beltamozhHire.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Entity
@Table(name = "vacancies")
@Data
@Lazy
public class Vacancy {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "about")
    private String about;

    @Column(name = "estimated_salary")
    private float estimatedSalary;

    @ManyToOne
    @JoinColumn(
            name = "skill_level_id",
            referencedColumnName = "id"
    )
    @ToString.Exclude
    private SkillLevel requiredSkillLevel;

    @Column(name = "required_working_experience")
    private int requiredWorkingExperience;

    @ManyToMany
    @JoinTable(
            name = "vacancies_technologies",
            joinColumns = @JoinColumn(name = "vacancy_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id")
    )
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ToString.Exclude
    private List<Technology> technologies;
}
