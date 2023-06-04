package by.beltamozh.beltamozhHire.controllers;

import by.beltamozh.beltamozhHire.dto.ResumeDto;
import by.beltamozh.beltamozhHire.models.Resume;
import by.beltamozh.beltamozhHire.models.User;
import by.beltamozh.beltamozhHire.services.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("user/{id}/resumes")
@AllArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;
    private final UserService userService;
    private final SkillLevelService skillLevelService;
    private final TechnologyService technologyService;

    //region info
    @GetMapping()
    private String index(@PathVariable int id,
                         @RequestParam(defaultValue = "") String filter,
                         @RequestParam(defaultValue = "") String sortBy,
                         Model model) {
        var resumes = resumeService.findAllByNameAndOwnerIdSorted(id, filter, sortBy);
        var user = userService.findById(id);
        if (resumes.isEmpty()) {
            model.addAttribute("resumes", Collections.<ResumeDto>emptyList());
            return "resumePageViews/resumes";
        }
        model.addAttribute("resumes", resumes.get());
        model.addAttribute("user", user.get());
        return "resumePageViews/resumes";
    }

    @GetMapping("/resume_details/{resume_id}")
    private String resumeDetails(@PathVariable int id, @PathVariable int resume_id, Model model) {
        var resume = resumeService.findDtoById(resume_id);
        var user = userService.findById(id);
        if (resume.isEmpty()) {
            return "resumePageViews/resumes";
        }
        model.addAttribute("resume", resume.get());
        model.addAttribute("user", user.get());
        return "resumePageViews/resume_details";
    }

    //endregion
    //region create
    @GetMapping("/new")
    private String newResume(@ModelAttribute("resume") Resume resume,
                             @PathVariable("id") int id,
                             Model model) {
        var skillLevels = skillLevelService.findAllDto();
        var technologies = technologyService.findAllDto();
        var user = userService.findById(id);
        if (skillLevels.isEmpty() || technologies.isEmpty() || user.isEmpty()) {
            return "resumePageViews/resumes";
        }
        model.addAttribute("user", user.get());
        model.addAttribute("skillLevels", skillLevels.get());
        model.addAttribute("technologies", technologies.get());
        return "resumePageViews/create_resume";
    }

    @PostMapping("/create")
    private String create(@PathVariable("id") int id,
                          @ModelAttribute("resume") @Valid Resume resume,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors())
        {
            return "redirect:/user/"+ id +"/resumes/new";
        }
        var user = userService.findById(id);
        resume.setOwner(user.get());
        resume.setId(0);
        resumeService.save(resume);
        return "redirect:/user/" + id + "/resumes";
    }

    //endregion
    //region edit
    @GetMapping("/resume_details/{resume_id}/edit")
    private String edit(@PathVariable("id") int id, @PathVariable int resume_id, Model model) {
        var user = userService.findDtoById(id);
        var resume = resumeService.findById(resume_id);
        var skillLevels = skillLevelService.findAll();
        var technologies = technologyService.findAll();
        if (resume.isEmpty() || skillLevels.isEmpty() || technologies.isEmpty()) {
            return "resumePageViews/resumes";
        }
        model.addAttribute("user", user.get());
        model.addAttribute("resume", resume.get());
        model.addAttribute("skillLevels", skillLevels.get());
        model.addAttribute("technologies", technologies.get());
        return "resumePageViews/edit_resume";
    }

    @PatchMapping("/resume_details/{resume_id}")
    private String change(@ModelAttribute("resume") @Valid Resume resume,
                          BindingResult bindingResult,
                          @PathVariable("id") int user_id,
                          @PathVariable("resume_id") int resume_id) {
        if (bindingResult.hasErrors())
        {
            return "redirect:/user/"+ user_id +"/resumes/resume_details/{resume_id}/edit";
        }
        resumeService.update(resume, resume_id);
        return "redirect:/user/" + user_id + "/resumes";
    }

    // endregion
    // region delete
    @DeleteMapping("/resume_details/{resume_id}/delete")
    private String delete(@PathVariable int resume_id, @PathVariable int id) {
        resumeService.delete(resume_id);
        return "redirect:/user/" + id + "/resumes";
    }
    // endregion
}
