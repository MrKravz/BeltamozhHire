package by.beltamozh.beltamozhHire.controllers;

import by.beltamozh.beltamozhHire.dto.*;
import by.beltamozh.beltamozhHire.models.SkillLevel;
import by.beltamozh.beltamozhHire.models.Technology;
import by.beltamozh.beltamozhHire.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final CategoryService categoryService;
    private final HrResponseService hrResponseService;
    private final SkillLevelService skillLevelService;
    private final TechnologyService technologyService;

    public AdminController(UserService userService, CategoryService categoryService,
                           HrResponseService hrResponseService, SkillLevelService skillLevelService, TechnologyService technologyService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.hrResponseService = hrResponseService;
        this.skillLevelService = skillLevelService;
        this.technologyService = technologyService;
    }

    //region info tables
    @GetMapping()
    public String index() {
        return "adminPageViews/index";
    }

    @GetMapping("/users")
    public String users(Model model) {
        Optional<List<UserDto>> users = userService.findAllDto();
        if (users.isEmpty()) {
            return "adminPageViews/index";
        }
        model.addAttribute("users", users.get());
        return "adminPageViews/users";
    }

    @GetMapping("/categories")
    public String categories(Model model) {
        Optional<List<CategoryDto>> categories = categoryService.findAllDto();
        if (categories.isEmpty()) {
            return "adminPageViews/index";
        }
        model.addAttribute("categories", categories.get());
        return "adminPageViews/categories";
    }

    @GetMapping("/hr_responses")
    public String hrResponses(Model model) {
        Optional<List<HrResponseDto>> hrResponses = hrResponseService.findAllDto();
        if (hrResponses.isEmpty()) {
            return "adminPageViews/index";
        }
        model.addAttribute("hrResponses", hrResponses.get());
        return "adminPageViews/hr_responses";
    }

    @GetMapping("/skill_levels")
    public String skillLevels(Model model) {
        Optional<List<SkillLevelDto>> skillLevels = skillLevelService.findAllDto();
        if (skillLevels.isEmpty()) {
            return "adminPageViews/index";
        }
        model.addAttribute("skillLevels", skillLevels.get());
        return "adminPageViews/skill_levels";
    }

    @GetMapping("/technologies")
    public String technologies(Model model) {
        Optional<List<TechnologyDto>> technologies = technologyService.findAllDto();
        if (technologies.isEmpty()) {
            return "adminPageViews/index";
        }
        model.addAttribute("technologies", technologies.get());
        return "adminPageViews/technologies";
    }
    //endregion
    // region post

    @GetMapping("/categories/new")
    public String newCategory(@ModelAttribute CategoryDto category) {
        return "adminPageViews/new_category";
    }

    @PostMapping("/categories")
    public String createCategory(@ModelAttribute CategoryDto category) {
        categoryService.saveDto(category);
        return "adminPageViews/index";
    }

    @GetMapping("/hr_responses/new")
    public String newResponse(@ModelAttribute HrResponseDto hrResponse) {
        return "adminPageViews/new_hr_response";
    }

    @PostMapping("/hr_responses")
    public String createResponse(@ModelAttribute HrResponseDto hrResponse) {
        hrResponseService.saveDto(hrResponse);
        return "adminPageViews/index";
    }

    @GetMapping("/skill_levels/new")
    public String newSkillLevel(@ModelAttribute SkillLevelDto skillLevel) {
        return "adminPageViews/new_skill_level";
    }

    @PostMapping("/skill_levels")
    public String createSkillLevel(@ModelAttribute SkillLevelDto skillLevel) {
        skillLevelService.saveDto(skillLevel);
        return "adminPageViews/index";
    }

    @GetMapping("/technologies/new")
    public String newTechnology(@ModelAttribute TechnologyDto technology) {
        return "adminPageViews/new_technology";
    }

    @PostMapping("/technologies")
    public String createTechnology(@ModelAttribute TechnologyDto technology) {
        technologyService.saveDto(technology);
        return "adminPageViews/index";
    }

    // endregion
    // region edit
    @GetMapping("/categories/{id}/edit")
    public String editCategory(@PathVariable int id, Model model) {
        Optional<CategoryDto> category = categoryService.findDtoById(id);
        if (category.isEmpty()) {
            return "adminPageViews/categories";
        }
        model.addAttribute("category", category.get());
        return "adminPageViews/category_details";
    }

    @PatchMapping("/categories/{id}")
    public String applyCategoryChanges(@PathVariable int id, @ModelAttribute CategoryDto category) {
        categoryService.updateDto(category, id);
        return "adminPageViews/index";
    }

    @GetMapping("/hr_responses/{id}/edit")
    public String editHrResponse(@PathVariable int id, Model model) {
        Optional<HrResponseDto> hrResponse = hrResponseService.findDtoById(id);
        if (hrResponse.isEmpty()) {
            return "adminPageViews/hr_responses";
        }
        model.addAttribute("hrResponse", hrResponse.get());
        return "adminPageViews/hr_response_details";
    }

    @PatchMapping("/hr_responses/{id}")
    public String applyHrResponseChanges(@PathVariable int id, @ModelAttribute HrResponseDto hrResponse) {
        hrResponseService.updateDto(hrResponse, id);
        return "adminPageViews/index";
    }

    @GetMapping("/skill_levels/{id}/edit")
    public String editSkillLevel(@PathVariable int id, Model model) {
        Optional<SkillLevelDto> skillLevel = skillLevelService.findDtoById(id);
        if (skillLevel.isEmpty()) {
            return "adminPageViews/skill_levels";
        }
        model.addAttribute("skillLevel", skillLevel.get());
        return "adminPageViews/skill_level_details";
    }

    @PatchMapping("/skill_levels/{id}")
    public String applySkillLevelChanges(@PathVariable int id, @ModelAttribute SkillLevelDto skillLevel) {
        skillLevelService.updateDto(skillLevel, id);
        return "adminPageViews/index";
    }

    @GetMapping("/technologies/{id}/edit")
    public String editTechnology(@PathVariable int id, Model model) {
        Optional<TechnologyDto> technology = technologyService.findDtoById(id);
        if (technology.isEmpty()) {
            return "adminPageViews/technologies";
        }
        model.addAttribute("technology", technology.get());
        return "adminPageViews/technology_details";
    }

    @PatchMapping("/technologies/{id}")
    public String applyTechnologyChanges(@PathVariable int id, @ModelAttribute TechnologyDto technology) {
        technologyService.updateDto(technology, id);
        return "adminPageViews/index";
    }

    //endregion
    // region delete
    @DeleteMapping("/categories/{id}/delete")
    private String deleteCategory(@PathVariable int id) {
        categoryService.delete(id);
        return "adminPageViews/categories";
    }

    @DeleteMapping("/users/{id}/delete")
    private String deleteUser(@PathVariable int id) {
        userService.delete(id);
        return "adminPageViews/users";
    }

    @DeleteMapping("/hr_responses/{id}/delete")
    private String deleteHrResponse(@PathVariable int id) {
        hrResponseService.delete(id);
        return "adminPageViews/hr_responses";
    }

    @DeleteMapping("/skill_levels/{id}/delete")
    private String deleteSkillLevel(@PathVariable int id) {
        skillLevelService.delete(id);
        return "adminPageViews/skill_levels";
    }

    @DeleteMapping("/technologies/{id}/delete")
    private String deleteTechnology(@PathVariable int id) {
        technologyService.delete(id);
        return "adminPageViews/technologies";
    }
    // endregion
}