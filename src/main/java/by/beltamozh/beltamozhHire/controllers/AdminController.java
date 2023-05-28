package by.beltamozh.beltamozhHire.controllers;

import by.beltamozh.beltamozhHire.dto.*;
import by.beltamozh.beltamozhHire.models.*;
import by.beltamozh.beltamozhHire.services.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final UserService userService;
    private final CategoryService categoryService;
    private final HrResponseService hrResponseService;
    private final SkillLevelService skillLevelService;
    private final TechnologyService technologyService;
    private final VacancyService vacancyService;

    //region info tables
    @GetMapping()
    public String index() {
        return "adminPageViews/index";
    }

    @GetMapping("/users")
    public String users(Model model) {
        var users = userService.findAllDto();
        if (users.isEmpty()) {
            return "adminPageViews/index";
        }
        model.addAttribute("users", users.get());
        return "adminPageViews/users";
    }

    @GetMapping("/categories")
    public String categories(Model model) {
        var categories = categoryService.findAllDto();
        if (categories.isEmpty()) {
            return "adminPageViews/index";
        }
        model.addAttribute("categories", categories.get());
        return "adminPageViews/categories";
    }

    @GetMapping("/hr_responses")
    public String hrResponses(Model model) {
        var hrResponses = hrResponseService.findAllDto();
        if (hrResponses.isEmpty()) {
            return "adminPageViews/index";
        }
        model.addAttribute("hrResponses", hrResponses.get());
        return "adminPageViews/hr_responses";
    }

    @GetMapping("/skill_levels")
    public String skillLevels(Model model) {
        var skillLevels = skillLevelService.findAllDto();
        if (skillLevels.isEmpty()) {
            return "adminPageViews/index";
        }
        model.addAttribute("skillLevels", skillLevels.get());
        return "adminPageViews/skill_levels";
    }

    @GetMapping("/technologies")
    public String technologies(Model model) {
        var technologies = technologyService.findAllDto();
        if (technologies.isEmpty()) {
            return "adminPageViews/index";
        }
        model.addAttribute("technologies", technologies.get());
        return "adminPageViews/technologies";
    }
    @GetMapping("/vacancies")
    public String vacancies(Model model) {
        var vacancies = vacancyService.findAll();
        if (vacancies.isEmpty()) {
            return "adminPageViews/index";
        }
        model.addAttribute("vacancies", vacancies.get());
        return "adminPageViews/vacancies";
    }
    //endregion
    // region post
    @GetMapping("/categories/new")
    public String newCategory(@ModelAttribute Category category) {
        return "adminPageViews/new_category";
    }

    @PostMapping("/categories")
    public String createCategory(@ModelAttribute Category category) {
        categoryService.save(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/hr_responses/new")
    public String newResponse(@ModelAttribute HrResponse hrResponse) {
        return "adminPageViews/new_hr_response";
    }

    @PostMapping("/hr_responses")
    public String createResponse(@ModelAttribute HrResponse hrResponse) {
        hrResponseService.save(hrResponse);
        return "redirect:/admin/hr_response";
    }

    @GetMapping("/skill_levels/new")
    public String newSkillLevel(@ModelAttribute SkillLevel skillLevel) {
        return "adminPageViews/new_skill_level";
    }

    @PostMapping("/skill_levels")
    public String createSkillLevel(@ModelAttribute SkillLevel skillLevel) {
        skillLevelService.save(skillLevel);
        return "redirect:/admin/skill_levels";
    }

    @GetMapping("/technologies/new")
    public String newTechnology(@ModelAttribute Technology technology) {
        return "adminPageViews/new_technology";
    }

    @PostMapping("/technologies")
    public String createTechnology(@ModelAttribute Technology technology) {
        technologyService.save(technology);
        return "redirect:/admin/technologies";
    }

    // endregion
    // region edit
    @GetMapping("/categories/{id}/edit")
    public String editCategory(@PathVariable int id, Model model) {
        var category = categoryService.findById(id);
        if (category.isEmpty()) {
            return "adminPageViews/categories";
        }
        model.addAttribute("category", category.get());
        return "adminPageViews/category_details";
    }

    @PatchMapping("/categories/{id}")
    public String applyCategoryChanges(@PathVariable int id, @ModelAttribute Category category) {
        categoryService.update(category, id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/hr_responses/{id}/edit")
    public String editHrResponse(@PathVariable int id, Model model) {
        var hrResponse = hrResponseService.findById(id);
        if (hrResponse.isEmpty()) {
            return "redirect:/admin/hr_responses";
        }
        model.addAttribute("hrResponse", hrResponse.get());
        return "adminPageViews/hr_response_details";
    }

    @PatchMapping("/hr_responses/{id}")
    public String applyHrResponseChanges(@PathVariable int id, @ModelAttribute HrResponse hrResponse) {
        hrResponseService.update(hrResponse, id);
        return "redirect:/admin/hr_responses";
    }

    @GetMapping("/skill_levels/{id}/edit")
    public String editSkillLevel(@PathVariable int id, Model model) {
        var skillLevel = skillLevelService.findById(id);
        if (skillLevel.isEmpty()) {
            return "redirect:/admin/skill_levels";
        }
        model.addAttribute("skillLevel", skillLevel.get());
        return "adminPageViews/skill_level_details";
    }

    @PatchMapping("/skill_levels/{id}")
    public String applySkillLevelChanges(@PathVariable int id, @ModelAttribute SkillLevel skillLevel) {
        skillLevelService.update(skillLevel, id);
        return "redirect:/admin/skill_levels";
    }

    @GetMapping("/technologies/{id}/edit")
    public String editTechnology(@PathVariable int id, Model model) {
        var technology = technologyService.findById(id);
        if (technology.isEmpty()) {
            return "redirect:/admin/technologies";
        }
        model.addAttribute("technology", technology.get());
        return "adminPageViews/technology_details";
    }

    @PatchMapping("/technologies/{id}")
    public String applyTechnologyChanges(@PathVariable int id, @ModelAttribute Technology technology) {
        technologyService.update(technology, id);
        return "redirect:/admin/technologies";
    }

    @GetMapping("/vacancies/{id}/edit")
    public String editVacancy(@PathVariable int id, Model model) {
        var vacancy = vacancyService.findById(id);
        var skillLevels = skillLevelService.findAll();
        var technologies = technologyService.findAll();
        if (vacancy.isEmpty() || skillLevels.isEmpty() || technologies.isEmpty()) {
            return "redirect:/admin/vacancies";
        }
        model.addAttribute("vacancy", vacancy.get());
        model.addAttribute("skillLevels", skillLevels.get());
        model.addAttribute("technologies", technologies.get());
        return "adminPageViews/edit_vacancy";
    }

    @PatchMapping("/vacancies/{id}")
    public String applyVacancyChanges(@PathVariable int id, @ModelAttribute Vacancy vacancy) {
        vacancyService.update(vacancy, id);
        return "redirect:/admin/vacancies";
    }
    //endregion
    // region delete
    @DeleteMapping("/categories/{id}/delete")
    private String deleteCategory(@PathVariable int id) {
        categoryService.delete(id);
        return "redirect:/admin/categories";
    }

    @DeleteMapping("/users/{id}/delete")
    private String deleteUser(@PathVariable int id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/hr_responses/{id}/delete")
    private String deleteHrResponse(@PathVariable int id) {
        hrResponseService.delete(id);
        return "redirect:/admin/hr_responses";
    }

    @DeleteMapping("/skill_levels/{id}/delete")
    private String deleteSkillLevel(@PathVariable int id) {
        skillLevelService.delete(id);
        return "redirect:/admin/skill_levels";
    }

    @DeleteMapping("/technologies/{id}/delete")
    private String deleteTechnology(@PathVariable int id) {
        technologyService.delete(id);
        return "redirect:/admin/technologies";
    }
    // endregion
}