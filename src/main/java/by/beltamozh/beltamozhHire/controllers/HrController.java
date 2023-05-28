package by.beltamozh.beltamozhHire.controllers;

import by.beltamozh.beltamozhHire.dto.VacancyDto;
import by.beltamozh.beltamozhHire.models.ResumeResponse;
import by.beltamozh.beltamozhHire.models.User;
import by.beltamozh.beltamozhHire.models.Vacancy;
import by.beltamozh.beltamozhHire.services.*;
import by.beltamozh.beltamozhHire.util.SortOptions;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/hr")
@AllArgsConstructor
public class HrController {
    private final VacancyService vacancyService;
    private final ResumeResponseService resumeResponseService;
    private final HrResponseService hrResponseService;
    private final SkillLevelService skillLevelService;
    private final TechnologyService technologyService;

    @GetMapping()
    public String index()
    {
        return "hrPageViews/index";
    }
    //region get
    @GetMapping("/vacancies")
    public String vacancies(Model model,
                            @RequestParam(defaultValue = "") String filter,
                            @RequestParam(defaultValue = "") String sortBy)
    {
        var vacancies = vacancyService.findAllByNameSorted(filter, sortBy);
        if (vacancies.isEmpty())
        {
            return "redirect:/hr";
        }
        model.addAttribute("vacancies", vacancies.get());
        return "hrPageViews/vacancies";
    }

    @GetMapping("/vacancies/vacancy_details/{vacancy_id}")
    private String vacancyDetails(@PathVariable int vacancy_id, Model model) {
        var resume = vacancyService.findDtoById(vacancy_id);
        if (resume.isEmpty()) {
            return "redirect:/hr/vacancies";
        }
        model.addAttribute("vacancy", resume.get());
        return "hrPageViews/vacancy_details";
    }
    //endregion
    //region feedback to responses
    @GetMapping("/vacancies/vacancy_details/{vacancy_id}/responds")
    public String responds(Model model,
                           @PathVariable("vacancy_id") int vacancy_id)
    {
        var resumeResponses = resumeResponseService.findAllByVacancyId(vacancy_id);
        if (resumeResponses.isEmpty())
        {
            return "";
        }
        model.addAttribute("resumeResponse", resumeResponses.get());
        return "hrPageViews/responded_resumes";
    }
    @GetMapping("/vacancies/vacancy_details/{vacancy_id}/responds/{resume_id}")
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
        return "hrPageViews/resume_details";
    }
    @PatchMapping("vacancies/vacancy_details/{vacancy_id}/responds/{resume_id}/send_feedback")
    public String sendFeedback(@ModelAttribute("resumeResponse") ResumeResponse resumeResponse)
    {
        resumeResponseService.update(resumeResponse, resumeResponse.getId());
        return "redirect:/hr/vacancies";
    }
    //endregion
    // region post
    @GetMapping("/vacancies/new")
    private String newVacancy(Model model, @ModelAttribute("vacancy") Vacancy vacancy) {
        var skillLevels = skillLevelService.findAllDto();
        var technologies = technologyService.findAllDto();
        if (skillLevels.isEmpty() || technologies.isEmpty()) {
            return "resumePageViews/resumes";
        }
        model.addAttribute("skillLevels", skillLevels.get());
        model.addAttribute("technologies", technologies.get());
        return "hrPageViews/create_vacancy";
    }

    @PostMapping("/vacancies/create")
    private String create(@ModelAttribute("vacancy") Vacancy vacancy) {
        vacancyService.save(vacancy);
        return "redirect:/hr/vacancies";
    }
    //endregion
    //region patch
    @GetMapping("vacancies/vacancy_details/{vacancy_id}/edit")
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
        return "hrPageViews/edit_vacancy";
    }

    @PatchMapping("vacancies/vacancy_details/{vacancy_id}/change")
    public String change(@ModelAttribute("vacancy") VacancyDto vacancyDto, @PathVariable("vacancy_id") int vacancy_id)
    {
        vacancyService.updateDto(vacancyDto, vacancy_id);
        vacancyDto.getTechnologies().forEach(x->technologyService.update(x, x.getId()));
        return "redirect:/hr/vacancies";
    }
    //endregion
    //region delete
    @DeleteMapping("vacancies/vacancy_details/{vacancy_id}/delete")
    private String delete(@PathVariable("vacancy_id") int vacancy_id) {
        vacancyService.delete(vacancy_id);
        return "redirect:/hr/vacancies";
    }
    //endregion
    @GetMapping("/vacancies/options")
    public String sort(@ModelAttribute SortOptions sortOptions) {
        return "hrPageViews/sort_filter";
    }

    @PostMapping("/vacancies/options/apply")
    public String apply(@ModelAttribute("sortOptions") SortOptions sortOptions) {
        if (sortOptions.getFieldToSort().equals("") && sortOptions.getTextToFilter().equals("")) {
            return "redirect:/hr/vacancies";
        }
        if (sortOptions.getFieldToSort().equals("") && !sortOptions.getTextToFilter().equals("")) {
            return "redirect:/hr/vacancies?filter=" + sortOptions.getTextToFilter();
        }
        if (!sortOptions.getFieldToSort().equals("") && sortOptions.getTextToFilter().equals("")) {
            return "redirect:/hr/vacancies?sortBy=" + sortOptions.getFieldToSort();
        }
        return "redirect:/hr/vacancies?filter=" + sortOptions.getTextToFilter()
                + "&sortBy=" + sortOptions.getFieldToSort();
    }
}
