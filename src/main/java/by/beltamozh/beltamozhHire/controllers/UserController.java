package by.beltamozh.beltamozhHire.controllers;

import by.beltamozh.beltamozhHire.dto.UserDto;
import by.beltamozh.beltamozhHire.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/user/{id}")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index(@PathVariable int id, Model model)
    {
        Optional<UserDto> user = userService.findDtoById(id);
        if (user.isEmpty())
        {
            return "";
        }
        model.addAttribute("user", user.get());
        return "userPageViews/index";
    }

    @GetMapping("/show")
    public String resumes(@PathVariable int id)
    {
        return "redirect:/user/{id}/resumes";
    }

    @GetMapping("/info")
    public String info(@PathVariable int id, Model model)
    {
        Optional<UserDto> user = userService.findDtoById(id);
        if (user.isEmpty())
        {
            return "";
        }
        model.addAttribute("user", user.get());
        return "userPageViews/info";
    }

    @PatchMapping("/edit")
    public String edit(@ModelAttribute("user") UserDto user, @PathVariable int id) // TODO edit user info
    {
        userService.updateDto(user, id);
        return "redirect:/user/{id}";
    }


}
