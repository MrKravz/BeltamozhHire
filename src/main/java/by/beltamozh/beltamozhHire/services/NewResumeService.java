package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.models.Resume;
import by.beltamozh.beltamozhHire.models.User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class NewResumeService {
    private final CompanyService companyService;
    private final BadReputationUserService badReputationUserService;
    private final CategoryService categoryService;
    private final SkillLevelService skillLevelService;

    public NewResumeService(CompanyService companyService, BadReputationUserService badReputationUserService, CategoryService categoryService, SkillLevelService skillLevelService) {
        this.companyService = companyService;
        this.badReputationUserService = badReputationUserService;
        this.categoryService = categoryService;
        this.skillLevelService = skillLevelService;
    }

    public Resume newResume(User user) {
        var resume = new Resume();
        resume.setTechnologies(Collections.emptyList());
        resume.setSkillLevel(skillLevelService.findAll().get()
                .stream()
                .filter(x -> x.getName().equals("Trainee"))
                .findFirst()
                .get());
        resume.setOwner(user);
        return resume;
    }

    public Resume categorizeResume(Resume resumeToCategorize) {
        if (IsMatchRefusesConditions(resumeToCategorize)) {
            var category = categoryService.findAll().get().stream().filter(x -> x.getName().equals("Refuses")).findFirst().get();
            resumeToCategorize.setCategory(category);
            return resumeToCategorize;
        }
        if (IsMatchLatestConditions(resumeToCategorize)) {
            var category = categoryService.findAll().get().stream().filter(x -> x.getName().equals("Latest")).findFirst().get();
            resumeToCategorize.setCategory(category);
            return resumeToCategorize;
        }
        if (IsMatchSecondaryConditions(resumeToCategorize)) {
            var category = categoryService.findAll().get().stream().filter(x -> x.getName().equals("Secondary")).findFirst().get();
            resumeToCategorize.setCategory(category);
            return resumeToCategorize;
        }
        if (IsMatchFirstlyConditions(resumeToCategorize)) {
            var category = categoryService.findAll().get().stream().filter(x -> x.getName().equals("Firstly")).findFirst().get();
            resumeToCategorize.setCategory(category);
            return resumeToCategorize;
        }
        return resumeToCategorize;
    }

    private boolean IsMatchRefusesConditions(Resume resume) {
        return badReputationUserService.findAll().get().contains(resume.getOwner());
    }

    private boolean IsMatchLatestConditions(Resume resume) {
        return resume.getDesiredSalary() > 3000 || companyService.findAll().get().contains(resume.getCompanies());
    }

    private boolean IsMatchSecondaryConditions(Resume resume) {
        var juniorCategory = categoryService.findAll().get().stream().filter(x -> x.getName().equals("Junior")).findFirst().get();
        var seniorCategory = categoryService.findAll().get().stream().filter(x -> x.getName().equals("Senior")).findFirst().get();
        return resume.getDesiredSalary() > 0 && resume.getDesiredSalary() < 1000 && resume.getSkillLevel().getName().equals(juniorCategory)
                || resume.getDesiredSalary() > 2000 && resume.getDesiredSalary() <= 3000  && resume.getSkillLevel().getName().equals(seniorCategory);
    }

    private boolean IsMatchFirstlyConditions(Resume resume) {
        var middleCategory = categoryService.findAll().get().stream().filter(x -> x.getName().equals("Middle")).findFirst().get();
        return resume.getDesiredSalary() > 1000 && resume.getDesiredSalary() < 2000 && resume.getSkillLevel().getName().equals(middleCategory);
    }
}
