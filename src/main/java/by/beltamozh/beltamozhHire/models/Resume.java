package by.beltamozh.beltamozhHire.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.*;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Entity
@Table(name = "resumes")
public class Resume {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private int id;

    @ManyToOne()
    @JoinColumn(
            name = "user_id",
            referencedColumnName =  "id"
    )
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private User owner;

    @Column(name = "name")
    @Size(min = 2, max = 50, message = "Имя не правильного размера")
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String name;

    @Column(name = "desired_position")
    @Size(min = 2, max = 50, message = "Имя не правильного размера")
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String desiredPosition;

    @ManyToMany(mappedBy = "resumes")
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private List<Technology> technologies;

    @ManyToOne()
    @JoinColumn(
            name = "skill_level_id",
            referencedColumnName =  "id"
    )
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private SkillLevel skillLevel;

    @Column(name = "desired_salary")
    @Min(value = 1)
    @Max(value = 1000000)
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private float desiredSalary;

    @Column(name = "about")
    @Size(max = 100, message = "Имя не правильного размера")
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String about;

    @ManyToOne()
    @JoinColumn(
            name = "category_id",
            referencedColumnName =  "id"
    )
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private Category category;

    @ManyToOne()
    @JoinColumn(
            name = "hr_response_id",
            referencedColumnName =  "id"
    )
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private HrResponse hrResponse;

    @Column(name = "is_deleted")
    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private boolean is_deleted;

    public Resume() {
    }
}
