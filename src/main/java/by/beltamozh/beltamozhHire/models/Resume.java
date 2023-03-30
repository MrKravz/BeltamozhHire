package by.beltamozh.beltamozhHire.models;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.*;

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
}
