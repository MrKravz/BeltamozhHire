package by.beltamozh.beltamozhHire.controllers;

import by.beltamozh.beltamozhHire.models.*;
import by.beltamozh.beltamozhHire.services.ResumeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("user/{id}/resumes")
public class ResumePageController {
    private final ResumeService resumeService;

    public ResumePageController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }
    //region info
    @GetMapping()
    private String index(@PathVariable int id, Model model)
    {
        Optional<List<Resume>> resumes = resumeService.getResumesOfUserById(id);
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
        Optional<Resume> resume = resumeService.getResumeById(resume_id);
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
        User user = resumeService.getUserById(id).get();
        resume.setOwner(user);
        List<SkillLevel> skillLevels = resumeService.getAllSkillLevels();
        List<Technology> technologies = resumeService.getAllTechnologies();
        model.addAttribute("skillLevels", skillLevels);
        model.addAttribute("technologies", technologies);
        return "resumePageViews/create_resume";
    }
    @PostMapping("/create")
    private String create(@PathVariable("id") int id, @ModelAttribute("resume") Resume resume)
    {
        User user = resumeService.getUserById(id).get();
        resume.setOwner(user);
        resumeService.save(resume);
        return "redirect:/user/" + id + "/resumes";
    }
    //endregion
    //region edit
    @GetMapping("/resume_details/{resume_id}/edit")
    private String edit(@PathVariable int resume_id, Model model)
    {
        Optional<Resume> resume = resumeService.getResumeById(resume_id);
        List<SkillLevel> skillLevels = resumeService.getAllSkillLevels();
        List<Technology> technologies = resumeService.getAllTechnologies();
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
            if (resumeService.getOwnerByResumeId(resume_id).isEmpty())
            {
                return "";
            }
            resume.setOwner(resumeService.getOwnerByResumeId(resume_id).get());
        }
        resumeService.update(resume, resume_id);
        return "redirect:/user/" + user_id + "/resumes";
    }
    // endregion
    @DeleteMapping ("/resume_details/{resume_id}/delete")
    private String delete(@PathVariable int resume_id, @PathVariable int id)
    {
        resumeService.deleteResumeById(resume_id);
        return "redirect:/user/" + id + "/resumes";
    }
}
