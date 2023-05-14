package by.beltamozh.beltamozhHire.controllers;

import by.beltamozh.beltamozhHire.dto.VacancyDto;
import by.beltamozh.beltamozhHire.models.ResumeResponse;
import by.beltamozh.beltamozhHire.models.Vacancy;
import by.beltamozh.beltamozhHire.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping("/hr/vacancies")
public class VacancyController {
    private final VacancyService vacancyService;
    private final SkillLevelService skillLevelService;
    private final TechnologyService technologyService;
    private final HrResponseService hrResponseService;
    private final ResumeResponseService resumeResponseService;

    public VacancyController(VacancyService vacancyService, SkillLevelService skillLevelService,
                             TechnologyService technologyService, HrResponseService hrResponseService,
                             ResumeResponseService resumeResponseService) {
        this.vacancyService = vacancyService;
        this.skillLevelService = skillLevelService;
        this.technologyService = technologyService;
        this.hrResponseService = hrResponseService;
        this.resumeResponseService = resumeResponseService;
    }
    // region get
    @GetMapping()
    public String index(Model model)
    {
        var vacancies = vacancyService.findAllDto();
        if (vacancies.isEmpty())
        {
            return "";
        }
        model.addAttribute("vacancies", vacancies.get());
        return "vacancyPageViews/index";
    }

    @GetMapping("/vacancy_details/{vacancy_id}")
    private String vacancyDetails(@PathVariable int vacancy_id, Model model) {
        var resume = vacancyService.findDtoById(vacancy_id);
        if (resume.isEmpty()) {
            return "vacancyPageViews/index";
        }
        model.addAttribute("vacancy", resume.get());
        return "vacancyPageViews/vacancy_details";
    }
    //endregion
    //region feedback to responses
    @GetMapping("/vacancy_details/{vacancy_id}/responds")
    public String responds(Model model, @PathVariable("vacancy_id") int vacancy_id)
    {
        var resumeResponses = resumeResponseService.findAllByVacancyId(vacancy_id);
        if (resumeResponses.isEmpty())
        {
            return "";
        }
        model.addAttribute("resumeResponse", resumeResponses.get());
        return "vacancyPageViews/responded_resumes";
    }
    @GetMapping("/vacancy_details/{vacancy_id}/responds/{resume_id}")
    public String respondedResumeDetails(Model model, @PathVariable("vacancy_id") int vacancy_id, @PathVariable("resume_id") int resume_id)
    {
        var resumeResponse = resumeResponseService.findByResumeIdAndVacancyId(resume_id, vacancy_id);
        var hrResponses = hrResponseService.findAll();
        if (resumeResponse.isEmpty())
        {
            return "";
        }
        model.addAttribute("resumeResponse", resumeResponse.get());
        model.addAttribute("hrResponses", hrResponses.get());
        return "vacancyPageViews/resume_details";
    }
    @PatchMapping("/vacancy_details/{vacancy_id}/responds/{resume_id}")
    public String sendFeedback(@ModelAttribute("resumeResponse") ResumeResponse resumeResponse)
    {
        resumeResponseService.update(resumeResponse, resumeResponse.getId());
        return "redirect:/hr/vacancies";
    }
    //endregion
    // region post
    @GetMapping("/new")
    private String newVacancy(Model model) {
        var skillLevels = skillLevelService.findAllDto();
        var technologies = technologyService.findAllDto();
        var vacancy = new Vacancy();
        vacancy.setRequiredSkillLevel(skillLevelService.findAll().get().stream().filter(x->x.getName().equals("Trainee")).findFirst().get());
        vacancy.setTechnologies(Collections.emptyList());
        if (skillLevels.isEmpty() || technologies.isEmpty()) {
            return "resumePageViews/resumes";
        }

        model.addAttribute("skillLevels", skillLevels.get());
        model.addAttribute("vacancy", vacancy);
        model.addAttribute("technologies", technologies.get());
        return "vacancyPageViews/create_vacancy";
    }

    @PostMapping("/create")
    private String create(@ModelAttribute("vacancy") Vacancy vacancy) {
        vacancyService.save(vacancy);
        return "redirect:/hr/vacancies";
    }
    //endregion
    //region patch
    @GetMapping("/vacancy_details/{vacancy_id}/edit")
    public String edit(Model model, @PathVariable("vacancy_id") int vacancy_id)
    {
        var vacancy = vacancyService.findDtoById(vacancy_id);
        var skillLevels = skillLevelService.findAll();
        var technologies = technologyService.findAll();
        if (vacancy.isEmpty() || skillLevels.isEmpty() || technologies.isEmpty())
        {
            return "";
        }
        model.addAttribute("skillLevels", skillLevels.get());
        model.addAttribute("vacancy", vacancy.get());
        model.addAttribute("technologies", technologies.get());
        return "vacancyPageViews/edit_vacancy";
    }

    @PatchMapping("/vacancy_details/{vacancy_id}/change")
    public String change(@ModelAttribute("vacancy") VacancyDto vacancyDto, @PathVariable("vacancy_id") int vacancy_id)
    {
        vacancyService.updateDto(vacancyDto, vacancy_id);
        vacancyDto.getTechnologies().forEach(x->technologyService.update(x, x.getId()));
        return "redirect:/hr/vacancies";
    }
    //endregion
    //region delete
    @DeleteMapping("/vacancy_details/{vacancy_id}/delete")
    private String delete(@PathVariable("vacancy_id") int vacancy_id) {
        vacancyService.delete(vacancy_id);
        return "redirect:/hr/vacancies";
    }
    //endregion
}
