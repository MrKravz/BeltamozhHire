package by.beltamozh.beltamozhHire.controllers;

import by.beltamozh.beltamozhHire.dto.*;
import by.beltamozh.beltamozhHire.models.Resume;
import by.beltamozh.beltamozhHire.models.Vacancy;
import by.beltamozh.beltamozhHire.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user/{id}/vacancies")
public class UserResponseController {

    private final VacancyService vacancyService;
    private final ResumeService resumeService;
    private final VacancyResponseService vacancyResponseService;

    public UserResponseController(VacancyService vacancyService, ResumeService resumeService, VacancyResponseService vacancyResponseService) {
        this.vacancyService = vacancyService;
        this.resumeService = resumeService;
        this.vacancyResponseService = vacancyResponseService;
    }

    @GetMapping()
    public String index(Model model) {
        Optional<List<VacancyDto>> vacancies = vacancyService.findAllDto();
        if (vacancies.isEmpty()) {
            return "redirect:/user/{id}";
        }
        model.addAttribute("vacancies", vacancies.get());
        return "vacancyPageViews/index";
    }

    @GetMapping("/vacancy_details/{vacancy_id}")
    public String vacancyDetails(@PathVariable("vacancy_id") int vacancy_id, Model model, @PathVariable("id") int id) {
        Optional<Vacancy> vacancy = vacancyService.findById(vacancy_id);
        Optional<List<Resume>> resumes = resumeService.findAllByOwnerId(id);
        if (vacancy.isEmpty()) {
            return "vacancyPageViews/index";
        }
        model.addAttribute("vacancy", vacancy.get());
        return "vacancyPageViews/vacancy_details";
    }

    @GetMapping("/vacancy_details/{vacancy_id}/chose_response")
    public String vacancyRespond(Model model, @PathVariable("id") int id) {
        var resumes = resumeService.findAllByOwnerId(id);
        if (resumes.isEmpty()) {
            return "vacancyPageViews/index";
        }
        model.addAttribute("resumes", resumes.get());
        model.addAttribute("resume", new Resume());
        return "vacancyPageViews/vacancy_respond";
    }

    @PostMapping("/vacancy_details/{vacancy_id}/respond")
    public String respondToVacancy(@PathVariable("vacancy_id") int vacancy_id, @ModelAttribute("resume") Resume resume) {
        var vacancy = vacancyService.findById(vacancy_id);
        var resumeToAdd = resumeService.findById(resume.getId());
        if (vacancy.isEmpty())
        {
            return "vacancyPageViews/index";
        }
        if (resumeToAdd.isEmpty())
        {
            return "vacancyPageViews/index";
        }
        vacancyResponseService.addResponse(vacancy.get(), resumeToAdd.get());
        vacancyService.update(vacancy.get(), vacancy_id);
        return "redirect:/user/{id}";
    }
}
