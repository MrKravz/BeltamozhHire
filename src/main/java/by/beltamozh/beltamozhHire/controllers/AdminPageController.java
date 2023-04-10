package by.beltamozh.beltamozhHire.controllers;

import by.beltamozh.beltamozhHire.services.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminPageController {

    private final AdminService adminService;

    public AdminPageController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping()
    public String index()
    {
        return "adminPageViews/index";
    }

    @GetMapping("/users")
    public String users(Model model)
    {
        model.addAttribute("users", adminService.getAllUsers());
        return "adminPageViews/users";
    }

    @GetMapping("/categories")
    public String categories(Model model)
    {
        model.addAttribute("categories", adminService.getAllCategories());
        return "adminPageViews/categories";
    }

    @GetMapping("/responses")
    public String responses(Model model)
    {
        model.addAttribute("responses", adminService.getAllHrResponses());
        return "adminPageViews/hr_responses";
    }

    @GetMapping("/skillLevels")
    public String skillLevels(Model model)
    {
        model.addAttribute("skillLevels", adminService.getAllSkillLevels());
        return "adminPageViews/skill_levels";
    }

    @GetMapping("/technologies")
    public String technologies(Model model)
    {
        model.addAttribute("technologies", adminService.getAllTechnologies());
        return "adminPageViews/technologies";
    }
}
