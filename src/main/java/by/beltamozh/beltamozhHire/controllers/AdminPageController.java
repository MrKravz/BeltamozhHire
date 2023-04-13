package by.beltamozh.beltamozhHire.controllers;

import by.beltamozh.beltamozhHire.models.*;
import by.beltamozh.beltamozhHire.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminPageController {

    private final CrudService<User> userService;
    private final CrudService<Category> categoryService;
    private final CrudService<HrResponse> hrResponseService;
    private final CrudService<SkillLevel> skillLevelService;
    private final CrudService<Technology> technologyService;

    public AdminPageController(UserService userService, CategoryService categoryService, HrResponseService hrResponseService, SkillLevelService skillLevelService, TechnologyService technologyService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.hrResponseService = hrResponseService;
        this.skillLevelService = skillLevelService;
        this.technologyService = technologyService;
    }

    @GetMapping()
    public String index()
    {
        return "adminPageViews/index";
    }

    @GetMapping("/users")
    public String users(Model model)
    {
        model.addAttribute("users", userService.findAll());
        return "adminPageViews/users";
    }

    @GetMapping("/categories")
    public String categories(Model model)
    {
        model.addAttribute("categories", categoryService.findAll());
        return "adminPageViews/categories";
    }

    @GetMapping("/responses")
    public String responses(Model model)
    {
        model.addAttribute("responses", hrResponseService.findAll());
        return "adminPageViews/hr_responses";
    }

    @GetMapping("/skillLevels")
    public String skillLevels(Model model)
    {
        model.addAttribute("skillLevels", skillLevelService.findAll());
        return "adminPageViews/skill_levels";
    }

    @GetMapping("/technologies")
    public String technologies(Model model)
    {
        model.addAttribute("technologies", technologyService.findAll());
        return "adminPageViews/technologies";
    }
}
