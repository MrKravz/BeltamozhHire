package by.beltamozh.beltamozhHire.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "vacancies_resumes")
@Data
@NoArgsConstructor
public class ResumeResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(
            name = "vacancy_id",
            referencedColumnName =  "id")
    private Vacancy vacancy;

    @ManyToOne
    @JoinColumn(
            name = "resume_id",
            referencedColumnName =  "id")
    private Resume resume;

    @ManyToOne
    @JoinColumn(
            name = "hr_response_id",
            referencedColumnName =  "id")
    private HrResponse hrResponse;

    public ResumeResponse(Vacancy vacancy, Resume resume)
    {
        this.vacancy = vacancy;
        this.resume = resume;
    }
}
