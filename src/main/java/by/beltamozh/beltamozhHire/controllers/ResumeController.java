package by.beltamozh.beltamozhHire.controllers;

import by.beltamozh.beltamozhHire.models.*;
import by.beltamozh.beltamozhHire.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("user/{id}/resumes")
public class ResumeController {
    private final CrudService<Resume> resumeService;
    private final CrudService<User> userService;
    private final CrudService<SkillLevel> skillLevelService;
    private final CrudService<Technology> technologyService;

    public ResumeController(ResumeService resumeService, UserService userService, SkillLevelService skillLevelService, TechnologyService technologyService) {
        this.resumeService = resumeService;
        this.userService = userService;
        this.skillLevelService = skillLevelService;
        this.technologyService = technologyService;
    }
    //region info
    @GetMapping()
    private String index(@PathVariable int id, Model model)
    {
        Optional<List<Resume>> resumes = Optional.of(resumeService.findAll()
                .get()
                .stream()
                .filter(x -> x.getOwner().getId() == id)
                .toList());
        if (resumes.isEmpty())
        {
            return "";
        }
        if (resumes.get().isEmpty())
        {
            model.addAttribute("resumes", Collections.<Resume>emptyList());
            model.addAttribute("id", id);
            return "resumePageViews/resumes";
        }
        model.addAttribute("resumes", resumes.get());
        model.addAttribute("id", id);
        return "resumePageViews/resumes";
    }

    @GetMapping("/resume_details/{resume_id}")
    private String details(@PathVariable int resume_id, Model model)
    {
        Optional<Resume> resume = resumeService.findById(resume_id);
        if (resume.isEmpty())
        {
            return "";
        }
        model.addAttribute("resume", resume.get());
        return "resumePageViews/resume_details";
    }
    //endregion
    //region create
    @GetMapping("/new")
    private String newResume(@PathVariable("id") int id, @ModelAttribute("resume") Resume resume, Model model)
    {
        User user = userService.findById(id).get();
        resume.setOwner(user);
        List<SkillLevel> skillLevels = skillLevelService.findAll().get();
        List<Technology> technologies = technologyService.findAll().get();
        model.addAttribute("skillLevels", skillLevels);
        model.addAttribute("technologies", technologies);
        return "resumePageViews/create_resume";
    }
    @PostMapping("/create")
    private String create(@PathVariable("id") int id, @ModelAttribute("resume") Resume resume)
    {
        User user = userService.findById(id).get();
        resume.setOwner(user);
        resumeService.save(resume);
        return "redirect:/user/" + id + "/resumes";
    }
    //endregion
    //region edit
    @GetMapping("/resume_details/{resume_id}/edit")
    private String edit(@PathVariable int resume_id, Model model)
    {
        Optional<Resume> resume = resumeService.findById(resume_id);
        List<SkillLevel> skillLevels = skillLevelService.findAll().get();
        List<Technology> technologies = technologyService.findAll().get();
        if (resume.isEmpty())
        {
            return "";
        }
        model.addAttribute("resume", resume.get());
        model.addAttribute("skillLevels", skillLevels);
        model.addAttribute("technologies", technologies);
        return "resumePageViews/edit_resume";
    }
    @PatchMapping ("/resume_details/{resume_id}")
    private String change(@ModelAttribute("resume") Resume resume,
                          @ModelAttribute("technologies") List<Technology> technologies,
                          @PathVariable("id") int user_id,
                          @PathVariable int resume_id)
    {
        if (resume.getOwner() == null)
        {
            if (resumeService.findById(resume_id).isEmpty())
            {
                return "";
            }
            resume.setOwner(resumeService.findById(resume_id).get().getOwner());
        }
        resumeService.update(resume, resume_id);
        return "redirect:/user/" + user_id + "/resumes";
    }
    // endregion
    @DeleteMapping ("/resume_details/{resume_id}/delete")
    private String delete(@PathVariable int resume_id, @PathVariable int id)
    {
        resumeService.delete(resume_id);
        return "redirect:/user/" + id + "/resumes";
    }
}
