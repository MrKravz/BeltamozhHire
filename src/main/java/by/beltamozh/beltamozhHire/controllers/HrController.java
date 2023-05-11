package by.beltamozh.beltamozhHire.controllers;

import by.beltamozh.beltamozhHire.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hr")
public class HrController {

    private final UserService userService;

    public HrController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index()
    {
        return "hrPageViews/index";
    }

    @GetMapping("/show")
    public String vacancies()
    {
        return "redirect:/hr/vacancies";
    }

}
