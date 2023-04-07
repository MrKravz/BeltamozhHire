package by.beltamozh.beltamozhHire.controllers;

import by.beltamozh.beltamozhHire.models.Resume;
import by.beltamozh.beltamozhHire.models.User;
import by.beltamozh.beltamozhHire.services.ResumeService;
import by.beltamozh.beltamozhHire.services.UserService;
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
        if (!resumes.isPresent())
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
        if (!resume.isPresent())
        {
            return "";
        }
        model.addAttribute("resume", resume.get());
        return "resumePageViews/resume_details";
    }
    @PatchMapping("/resume_details/{resume_id}/edit")
    private String edit(@PathVariable int resume_id, Model model)
    {
        Optional<Resume> resume = resumeService.getResumeById(resume_id);
        if (!resume.isPresent())
        {
            return "";
        }
        model.addAttribute("resume", resume.get());
        return "resumePageViews/resume_details";
    }
    @DeleteMapping ("/resume_details/{resume_id}/delete")
    private String delete(@PathVariable int resume_id, Model model)
    {
        Optional<Resume> resume = resumeService.getResumeById(resume_id);
        if (!resume.isPresent())
        {
            return "";
        }
        model.addAttribute("resume", resume.get());
        return "resumePageViews/resume_details";
    }
}
