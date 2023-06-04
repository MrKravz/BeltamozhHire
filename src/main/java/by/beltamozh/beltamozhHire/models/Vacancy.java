package by.beltamozh.beltamozhHire.models;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "vacancies")
@Data
public class Vacancy {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Size(min = 2, max = 50, message = "Имя не правильного размера")
    private String name;

    @Column(name = "about")
    @Size(max = 100, message = "О вакансии не правильного размера")
    private String about;

    @Column(name = "estimated_salary")
    @Min(value = 100, message = "Зп меньше минимума")
    @Max(value = 1000000, message = "Зп больше максимума")
    private float estimatedSalary;

    @ManyToOne
    @JoinColumn(
            name = "skill_level_id",
            referencedColumnName = "id"
    )
    @ToString.Exclude
    private SkillLevel requiredSkillLevel;

    @Column(name = "required_working_experience")
    @Min(value = 0, message = "Опыт меньше минимума")
    @Max(value = 50, message = "Опыт больше максимума")
    private int requiredWorkingExperience;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "vacancies_technologies",
            joinColumns = @JoinColumn(name = "vacancy_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id")
    )
    @ToString.Exclude
    private List<Technology> technologies;

    @OneToMany(mappedBy = "vacancy", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ToString.Exclude
    private List<ResumeResponse> resumeResponse;
}
