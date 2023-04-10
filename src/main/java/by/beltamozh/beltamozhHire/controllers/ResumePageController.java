package by.beltamozh.beltamozhHire.controllers;

import by.beltamozh.beltamozhHire.models.*;
import by.beltamozh.beltamozhHire.services.ResumeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/{id}/resumes")
public class ResumePageController {
    private final ResumeService resumeService;

    public ResumePageController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping()
    private String index(@PathVariable int id, Model model)
    {
        Optional<List<Resume>> resumes = resumeService.getResumesOfUserById(id);
        if (resumes.isEmpty())
        {
            return "";
        }
        model.addAttribute("resumes", resumes.get());
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
    @PatchMapping("/resume_details/{resume_id}")
    private String change(@ModelAttribute("user") Resume resume, @PathVariable int user_id,
                          @PathVariable int resume_id)
    {
        resumeService.update(resume, resume_id);
        return "redirect:/" + user_id + "/resumes";
    }
    @DeleteMapping ("/resume_details/{resume_id}/delete")
    private String delete(@PathVariable int resume_id, @PathVariable int id)
    {
        resumeService.deleteResumeById(resume_id);
        return "redirect:/" + id + "/resumes";
    }
}
