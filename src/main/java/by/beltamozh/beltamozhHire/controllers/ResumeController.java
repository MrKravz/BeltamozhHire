package by.beltamozh.beltamozhHire.controllers;

import by.beltamozh.beltamozhHire.dto.ResumeDto;
import by.beltamozh.beltamozhHire.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("user/{id}/resumes")
public class ResumeController {
    private final ResumeService resumeService;
    private final NewResumeService newResumeService;
    private final UserService userService;
    private final SkillLevelService skillLevelService;
    private final TechnologyService technologyService;

    public ResumeController(ResumeService resumeService, NewResumeService newResumeService, UserService userService, SkillLevelService skillLevelService, TechnologyService technologyService) {
        this.resumeService = resumeService;
        this.newResumeService = newResumeService;
        this.userService = userService;
        this.skillLevelService = skillLevelService;
        this.technologyService = technologyService;
    }

    //region info
    @GetMapping()
    private String index(@PathVariable int id, Model model) {
        var resumes = resumeService.findAllDtoByOwnerId(id);
        if (resumes.isEmpty()) {
            model.addAttribute("resumes", Collections.<ResumeDto>emptyList());
            return "resumePageViews/resumes";
        }
        model.addAttribute("resumes", resumes.get());
        return "resumePageViews/resumes";
    }

    @GetMapping("/resume_details/{resume_id}")
    private String resumeDetails(@PathVariable int resume_id, Model model) {
        var resume = resumeService.findDtoById(resume_id);
        if (resume.isEmpty()) {
            return "resumePageViews/resumes";
        }
        model.addAttribute("resume", resume.get());
        return "resumePageViews/resume_details";
    }

    //endregion
    //region create
    @GetMapping("/new")
    private String newResume(Model model) {
        var skillLevels = skillLevelService.findAllDto();
        var technologies = technologyService.findAllDto();
        var resume = newResumeService.newResume();
        if (skillLevels.isEmpty() || technologies.isEmpty()) {
            return "resumePageViews/resumes";
        }

        model.addAttribute("skillLevels", skillLevels.get());
        model.addAttribute("resume", resume);
        model.addAttribute("technologies", technologies.get());
        return "resumePageViews/create_resume";
    }

    @PostMapping("/create")
    private String create(@PathVariable("id") int id, @ModelAttribute("resume") ResumeDto resume) {
        var user = userService.findById(id);
        resume.setOwner(user.get());
        resumeService.saveDto(resume);
        return "redirect:/user/" + id + "/resumes";
    }

    //endregion
    //region edit
    @GetMapping("/resume_details/{resume_id}/edit")
    private String edit(@PathVariable int resume_id, Model model) {
        var resume = resumeService.findDtoById(resume_id);
        var skillLevels = skillLevelService.findAll();
        var technologies = technologyService.findAll();
        if (resume.isEmpty() || skillLevels.isEmpty() || technologies.isEmpty()) {
            return "resumePageViews/resumes";
        }
        model.addAttribute("resume", resume.get());
        model.addAttribute("skillLevels", skillLevels.get());
        model.addAttribute("technologies", technologies.get());
        return "resumePageViews/edit_resume";
    }

    @PatchMapping("/resume_details/{resume_id}")
    private String change(@ModelAttribute("resume") ResumeDto resumeDto,
                          @PathVariable("id") int user_id,
                          @PathVariable("resume_id") int resume_id) {
        resumeService.updateDto(resumeDto, resume_id);
        resumeDto.getTechnologies().forEach(x->technologyService.update(x, x.getId()));
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
