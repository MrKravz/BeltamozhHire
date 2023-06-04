package by.beltamozh.beltamozhHire.controllers;

import by.beltamozh.beltamozhHire.dto.UserDto;
import by.beltamozh.beltamozhHire.models.ResumeResponse;
import by.beltamozh.beltamozhHire.models.User;
import by.beltamozh.beltamozhHire.services.*;
import by.beltamozh.beltamozhHire.util.SortOptions;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Optional;

@Controller
@RequestMapping("/user/{id}")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final VacancyService vacancyService;
    private final ResumeService resumeService;
    private final ResumeResponseService resumeResponseService;
    private final VacancyResponseService vacancyResponseService;

    @GetMapping()
    public String index(@PathVariable int id,
                        Model model) {
        Optional<UserDto> user = userService.findDtoById(id);
        if (user.isEmpty()) {
            return "";
        }
        model.addAttribute("user", user.get());
        return "userPageViews/index";
    }

    @GetMapping("/show_resumes")
    public String resumes() {
        return "redirect:/user/{id}/resumes";
    }

    @GetMapping("/info")
    public String info(@PathVariable int id,
                       Model model) {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) {
            return "";
        }
        model.addAttribute("user", user.get());
        return "userPageViews/info";
    }

    @PatchMapping("/edit")
    public String edit(@ModelAttribute("user") @Valid User user,
                       BindingResult bindingResult,
                       @PathVariable int id) {
        if (bindingResult.hasErrors())
        {
            return "redirect:/user/{id}/info";
        }
        userService.update(user, id);
        return "redirect:/user/{id}";
    }

    @GetMapping("/vacancies/options")
    public String sort(@PathVariable int id,
                       @ModelAttribute SortOptions sortOptions,
                       Model model) {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) {
            return "";
        }
        model.addAttribute("user", user.get());
        return "userPageViews/sort_filter";
    }

    @PostMapping("/vacancies/options/apply")
    public String apply(@ModelAttribute("sortOptions") SortOptions sortOptions) {
        if (sortOptions.getFieldToSort().equals("") && sortOptions.getTextToFilter().equals("")) {
            return "redirect:/user/{id}/vacancies";
        }
        if (sortOptions.getFieldToSort().equals("") && !sortOptions.getTextToFilter().equals("")) {
            return "redirect:/user/{id}/vacancies?filter=" + sortOptions.getTextToFilter();
        }
        if (!sortOptions.getFieldToSort().equals("") && sortOptions.getTextToFilter().equals("")) {
            return "redirect:/user/{id}/vacancies?sortBy=" + sortOptions.getFieldToSort();
        }
        return "redirect:/user/{id}/vacancies?filter=" + sortOptions.getTextToFilter()
                + "&sortBy=" + sortOptions.getFieldToSort();
    }

    @GetMapping("/vacancies")
    public String vacancies(Model model,
                            @PathVariable int id,
                            @RequestParam(defaultValue = "") String filter,
                            @RequestParam(defaultValue = "") String sortBy) {
        var vacancies = vacancyService.findAllByNameSorted(filter, sortBy);
        var user = userService.findDtoById(id);
        if (vacancies.isEmpty() || user.isEmpty()) {
            return "redirect:/user/{id}";
        }
        model.addAttribute("vacancies", vacancies.get());
        model.addAttribute("user", user.get());
        return "userPageViews/vacancies";
    }

    @GetMapping("/vacancies/vacancy_details/{vacancy_id}")
    public String vacancyDetails(Model model,
                                 @PathVariable("id") int id,
                                 @PathVariable("vacancy_id") int vacancy_id) {
        var vacancy = vacancyService.findById(vacancy_id);
        var resumes = resumeService.findAllByOwnerId(id);
        var user = userService.findDtoById(id);
        if (vacancy.isEmpty() || user.isEmpty() || resumes.isEmpty()) {
            return "redirect:/user/{id}";
        }
        model.addAttribute("resumeRespond", new ResumeResponse());
        model.addAttribute("vacancy", vacancy.get());
        model.addAttribute("resumes", resumes.get());
        model.addAttribute("user", user.get());
        return "userPageViews/vacancy_details";
    }

    @PostMapping("/vacancies/vacancy_details/{vacancy_id}/response")
    public String respondToVacancy(@PathVariable("vacancy_id") int vacancy_id,
                                   @ModelAttribute("resumeRespond") ResumeResponse resumeResponse) {
        var vacancy = vacancyService.findById(vacancy_id);
        var resumeToAdd = resumeService.findById(resumeResponse.getResume().getId());
        boolean isResponseExist = resumeResponseService
                .findByResumeIdAndVacancyId(resumeResponse.getResume().getId(), vacancy_id)
                .isPresent();
        if (vacancy.isEmpty() || resumeToAdd.isEmpty() || isResponseExist) {
            return "redirect:/user/{id}";
        }
        vacancyResponseService.addResponse(vacancy.get(), resumeToAdd.get());
        vacancyService.update(vacancy.get(), vacancy_id);
        return "redirect:/user/{id}/vacancies";
    }

    @GetMapping("/responds")
    public String hrResponds(Model model,
                             @PathVariable("id") int id) {
        var responds = resumeResponseService
                .findAllByResumeOwnerId(id);
        var user = userService.findById(id);
        if (responds.isEmpty()) {
            return "redirect:/user/{id}";
        }
        var filteredResponds = responds.get()
                .stream()
                .filter(x -> x.getHrResponse() != null)
                .toList();
        model.addAttribute("resumeResponds", filteredResponds);
        model.addAttribute("user", user.get());
        return "userPageViews/responds";
    }

    @GetMapping("/responds/vacancy/{vacancy_id}/resume/{resume_id}")
    public String hrRespondDetails(@PathVariable("vacancy_id") int vacancy_id,
                                   @PathVariable("id") int id,
                                   @PathVariable("resume_id") int resume_id,
                                   Model model) {
        var responds = resumeResponseService
                .findByResumeIdAndVacancyId(resume_id, vacancy_id);
        var user = userService.findById(id);
        if (responds.isEmpty()) {
            return "redirect:/user/{id}";
        }
        model.addAttribute("resumeResponse", responds.get());
        model.addAttribute("user", user.get());
        return "userPageViews/response_details";
    }
}
