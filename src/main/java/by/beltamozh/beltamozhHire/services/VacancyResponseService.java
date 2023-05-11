package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.models.HrResponse;
import by.beltamozh.beltamozhHire.models.Resume;
import by.beltamozh.beltamozhHire.models.ResumeResponse;
import by.beltamozh.beltamozhHire.models.Vacancy;
import org.springframework.stereotype.Service;

@Service
public class VacancyResponseService {
    private final ResumeResponseService resumeResponseService;

    public VacancyResponseService(ResumeResponseService resumeResponseService) {
        this.resumeResponseService = resumeResponseService;
    }

    public void addResponse(Vacancy vacancy, Resume resume)
    {
        var resumeResponse = new ResumeResponse();
        resumeResponse.setVacancy(vacancy);
        resumeResponse.setResume(resume);
        resumeResponseService.save(resumeResponse);
    }
}
