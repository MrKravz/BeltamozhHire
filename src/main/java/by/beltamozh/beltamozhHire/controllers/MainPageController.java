package by.beltamozh.beltamozhHire.controllers;

import by.beltamozh.beltamozhHire.models.User;
import by.beltamozh.beltamozhHire.services.CrudService;
import by.beltamozh.beltamozhHire.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class MainPageController {
    private final CrudService<User> userService;

    public MainPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index()
    {
        return "mainPageViews/index";
    }

    @GetMapping("/registration")
    public String register(@ModelAttribute("user") User user)
    {
        return "mainPageViews/registration";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login()
    {
        return "redirect:/admin"; // TODO develop login logic
    }
}
