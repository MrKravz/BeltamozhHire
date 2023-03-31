package by.beltamozh.beltamozhHire.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Entity
@Table(name = "skill_levels")
public class SkillLevel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private int id;

    @Column(name = "name_of_skill_level")
    @Size(min = 2, max = 50, message = "Имя не правильного размера")
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String name;

    @OneToMany(mappedBy = "skillLevel")
    @Lazy
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private List<Resume> resumes;

    public SkillLevel() {
    }
}
