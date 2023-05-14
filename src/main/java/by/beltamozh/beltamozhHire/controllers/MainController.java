package by.beltamozh.beltamozhHire.controllers;

import by.beltamozh.beltamozhHire.models.Role;
import by.beltamozh.beltamozhHire.models.User;
import by.beltamozh.beltamozhHire.services.SecurityService;
import by.beltamozh.beltamozhHire.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class MainController {
    private final UserService userService;
    private final SecurityService securityService;

    public MainController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @GetMapping()
    public String index() {
        return "mainPageViews/index";
    }

    @GetMapping("/registration")
    public String register(@ModelAttribute("user") User user) {
        return "mainPageViews/registration";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute User user) {
        return "mainPageViews/login";
    }

    @PostMapping("/login/auth")
    public String auth(@ModelAttribute User user) {
        securityService.autoLogin(user.getLogin(), user.getPassword());
        int id = userService.findByLogin(user.getLogin()).get().getId();
        Optional<Role> role = userService.findById(id).get().getRoles().stream().findFirst();
        if (role.isEmpty()) {
            return "mainPageViews/index";
        }
        if (role.get().getName().equals("ADMIN")) {
            return "redirect:/admin";
        }
        if (role.get().getName().equals("HR")) {
            return "redirect:/hr";
        }
        return "redirect:/user/" + id;
    }

    @GetMapping("/about")
    public String about() {
        return "mainPageViews/login";
    }
}
