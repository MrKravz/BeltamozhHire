package by.beltamozh.beltamozhHire.controllers;

import by.beltamozh.beltamozhHire.mappers.*;
import by.beltamozh.beltamozhHire.models.*;
import by.beltamozh.beltamozhHire.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final CrudService<User> userService;
    private final CrudService<Category> categoryService;
    private final CrudService<HrResponse> hrResponseService;
    private final CrudService<SkillLevel> skillLevelService;
    private final CrudService<Technology> technologyService;

    private final UserDtoMapper userMapper;
    private final CategoryDtoMapper categoryMapper;
    private final HrResponseDtoMapper hrResponseMapper;
    private final SkillLevelDtoMapper skillLevelMapper;
    private final TechnologyDtoMapper technologyMapper;

    public AdminController(UserService userService, UserDtoMapper userMapper, CategoryService categoryService,
                           HrResponseService hrResponseService, SkillLevelService skillLevelService, TechnologyService technologyService,
                           CategoryDtoMapper categoryMapper, HrResponseDtoMapper hrResponseMapper, SkillLevelDtoMapper skillLevelMapper,
                           TechnologyDtoMapper technologyMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.categoryService = categoryService;
        this.hrResponseService = hrResponseService;
        this.skillLevelService = skillLevelService;
        this.technologyService = technologyService;
        this.categoryMapper = categoryMapper;
        this.hrResponseMapper = hrResponseMapper;
        this.skillLevelMapper = skillLevelMapper;
        this.technologyMapper = technologyMapper;
    }

    //region info tables
    @GetMapping()
    public String index() {
        return "adminPageViews/index";
    }

    @GetMapping("/users")
    public String users(Model model) {
        Optional<List<User>> users = userService.findAll();
        if (users.isEmpty()) {
            return "adminPageViews/index";
        }
        model.addAttribute("users", users.get().stream().map(userMapper).toList());
        return "adminPageViews/users";
    }

    @GetMapping("/categories")
    public String categories(Model model) {
        Optional<List<Category>> categories = categoryService.findAll();
        if (categories.isEmpty()) {
            return "adminPageViews/index";
        }
        model.addAttribute("categories", categories.get().stream().map(categoryMapper).toList());
        return "adminPageViews/categories";
    }

    @GetMapping("/hr_responses")
    public String hrResponses(Model model) {
        Optional<List<HrResponse>> hrResponses = hrResponseService.findAll();
        if (hrResponses.isEmpty()) {
            return "adminPageViews/index";
        }
        model.addAttribute("hrResponses", hrResponses.get().stream().map(hrResponseMapper).toList());
        return "adminPageViews/hr_responses";
    }

    @GetMapping("/skill_levels")
    public String skillLevels(Model model) {
        Optional<List<SkillLevel>> skillLevels = skillLevelService.findAll();
        if (skillLevels.isEmpty()) {
            return "adminPageViews/index";
        }
        model.addAttribute("skillLevels", skillLevels.get().stream().map(skillLevelMapper).toList());
        return "adminPageViews/skill_levels";
    }

    @GetMapping("/technologies")
    public String technologies(Model model) {
        Optional<List<Technology>> technologies = technologyService.findAll();
        if (technologies.isEmpty()) {
            return "adminPageViews/index";
        }
        model.addAttribute("technologies", technologies.get().stream().map(technologyMapper).toList());
        return "adminPageViews/technologies";
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
        return "adminPageViews/index";
    }

    @GetMapping("/hr_responses/new")
    public String newResponse(@ModelAttribute HrResponse hrResponse) {
        return "adminPageViews/new_hr_response";
    }

    @PostMapping("/hr_responses")
    public String createResponse(@ModelAttribute HrResponse hrResponse) {
        hrResponseService.save(hrResponse);
        return "adminPageViews/index";
    }

    @GetMapping("/skill_levels/new")
    public String newSkillLevel(@ModelAttribute SkillLevel skillLevel) {
        return "adminPageViews/new_skill_level";
    }

    @PostMapping("/skill_levels")
    public String createSkillLevel(@ModelAttribute SkillLevel skillLevel) {
        skillLevelService.save(skillLevel);
        return "adminPageViews/index";
    }

    @GetMapping("/technologies/new")
    public String newTechnology(@ModelAttribute Technology technology) {
        return "adminPageViews/new_technology";
    }

    @PostMapping("/technologies")
    public String createTechnology(@ModelAttribute Technology technology) {
        technologyService.save(technology);
        return "adminPageViews/index";
    }

    // endregion
    // region edit
    @GetMapping("/categories/{id}/edit")
    public String editCategory(@PathVariable int id, Model model) {
        Optional<Category> category = categoryService.findById(id);
        if (category.isEmpty()) {
            return "adminPageViews/categories";
        }
        model.addAttribute("category", category.get());
        return "adminPageViews/category_details";
    }

    @PatchMapping("/categories/{id}")
    public String applyCategoryChanges(@PathVariable int id, @ModelAttribute Category category) {
        categoryService.update(category, id);
        return "adminPageViews/index";
    }

    @GetMapping("/hr_responses/{id}/edit")
    public String editHrResponse(@PathVariable int id, Model model) {
        Optional<HrResponse> hrResponse = hrResponseService.findById(id);
        if (hrResponse.isEmpty()) {
            return "adminPageViews/hr_responses";
        }
        model.addAttribute("hrResponse", hrResponse.get());
        return "adminPageViews/hr_response_details";
    }

    @PatchMapping("/hr_responses/{id}")
    public String applyHrResponseChanges(@PathVariable int id, @ModelAttribute HrResponse hrResponse) {
        hrResponseService.update(hrResponse, id);
        return "adminPageViews/index";
    }

    @GetMapping("/skill_levels/{id}/edit")
    public String editSkillLevel(@PathVariable int id, Model model) {
        Optional<SkillLevel> skillLevel = skillLevelService.findById(id);
        if (skillLevel.isEmpty()) {
            return "adminPageViews/skill_levels";
        }
        model.addAttribute("skillLevel", skillLevel.get());
        return "adminPageViews/skill_level_details";
    }

    @PatchMapping("/skill_levels/{id}")
    public String applySkillLevelChanges(@PathVariable int id, @ModelAttribute SkillLevel skillLevel) {
        skillLevelService.update(skillLevel, id);
        return "adminPageViews/index";
    }

    @GetMapping("/technologies/{id}/edit")
    public String editTechnology(@PathVariable int id, Model model) {
        Optional<Technology> technology = technologyService.findById(id);
        if (technology.isEmpty()) {
            return "adminPageViews/technologies";
        }
        model.addAttribute("technology", technology.get());
        return "adminPageViews/technology_details";
    }

    @PatchMapping("/technologies/{id}")
    public String applyTechnologyChanges(@PathVariable int id, @ModelAttribute Technology technology) {
        technologyService.update(technology, id);
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