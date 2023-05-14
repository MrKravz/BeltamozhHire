package by.beltamozh.beltamozhHire.models;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "resumes")
@Data
public class Resume {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne()
    @JoinColumn(
            name = "user_id",
            referencedColumnName =  "id"
    )
    private User owner;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "resumes_companies",
            joinColumns = @JoinColumn(name = "resume_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id")
    )
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Company> companies;

    @Column(name = "name")
   /* @Size(min = 2, max = 50, message = "Имя не правильного размера")*/
    private String name;

    @Column(name = "desired_position")
  /*  @Size(min = 2, max = 50, message = "Имя не правильного размера")*/
    private String desiredPosition;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "resumes_technologies",
            joinColumns = @JoinColumn(name = "resume_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id")
    )
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Technology> technologies;

    @ManyToOne
    @JoinColumn(
            name = "skill_level_id",
            referencedColumnName =  "id"
    )
    private SkillLevel skillLevel;

    @Column(name = "desired_salary")
   /* @Min(value = 1)
    @Max(value = 1000000)*/
    private float desiredSalary;

    @Column(name = "about")
   /* @Size(max = 100, message = "Имя не правильного размера")*/
    private String about;

    @ManyToOne()
    @JoinColumn(
            name = "category_id",
            referencedColumnName =  "id"
    )
    private Category category;

    @OneToMany(mappedBy = "resume", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ToString.Exclude
    private List<ResumeResponse> resumeResponse;
}
