package by.beltamozh.beltamozhHire.controllers;

import by.beltamozh.beltamozhHire.models.User;
import by.beltamozh.beltamozhHire.services.CrudService;
import by.beltamozh.beltamozhHire.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/user/{id}")
public class UserPageController {
    private final CrudService<User> userService;

    public UserPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index(@PathVariable int id, Model model)
    {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty())
        {
            return "";
        }
        model.addAttribute("user", user.get());
        return "userPageViews/index";
    }

    @GetMapping("/show")
    public String resumes(@PathVariable String id)
    {
        return "redirect:/user/{id}/resumes";
    }

    @GetMapping("/info")
    public String info(@PathVariable int id, Model model)
    {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty())
        {
            return "";
        }
        model.addAttribute("user", user.get());
        return "userPageViews/info";
    }

    @PatchMapping("/edit")
    public String edit(@ModelAttribute("user") User user, @PathVariable int id) // TODO edit user info
    {
        userService.update(user, id);
        return "redirect:/user/{id}";
    }


}
