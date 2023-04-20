package by.beltamozh.beltamozhHire.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hr")
public class HrController {

    @GetMapping()
    public String index()
    {
        return "hrPageViews/index";
    }
}