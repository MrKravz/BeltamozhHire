package by.beltamozh.beltamozhHire.controllers;

import by.beltamozh.beltamozhHire.dto.*;
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
        Optional<VacancyDto> vacancy = vacancyService.findDtoById(vacancy_id);
        Optional<List<ResumeDto>> resumes = resumeService.findAllDtoByOwnerId(id);
        if (vacancy.isEmpty()) {
            return "vacancyPageViews/index";
        }
        model.addAttribute("vacancy", vacancy.get());
        model.addAttribute("resumes", resumes.get());
        return "vacancyPageViews/vacancy_details";
    }

    @PatchMapping("/vacancy_details/{vacancy_id}")
    public String respondToVacancy(@PathVariable("vacancy_id") int id, @ModelAttribute("vacancy") VacancyDto vacancy,
                                   @ModelAttribute("resume") ResumeDto resume) {
        // Todo resume added and update in list
        vacancyResponseService.addResponse(vacancy, resume);
        vacancyService.updateDto(vacancy, id);
        return "vacancyPageViews/vacancy_details";
    }
}
