package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.models.*;
import by.beltamozh.beltamozhHire.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AdminService {
    private final CategoryRepository categoryRepository;
    private final HrResponseRepository hrResponseRepository;
    private final SkillLevelRepository skillLevelRepository;
    private final TechnologyRepository technologyRepository;
    private final UserRepository userRepository;

    public AdminService(CategoryRepository categoryRepository, HrResponseRepository hrResponseRepository, SkillLevelRepository skillLevelRepository, TechnologyRepository technologyRepository, UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.hrResponseRepository = hrResponseRepository;
        this.skillLevelRepository = skillLevelRepository;
        this.technologyRepository = technologyRepository;
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }
    public List<Technology> getAllTechnologies()
    {
        return (List<Technology>) technologyRepository.findAll();
    }
    public List<SkillLevel> getAllSkillLevels()
    {
        return skillLevelRepository.findAll();
    }
    public List<HrResponse> getAllHrResponses()
    {
        return hrResponseRepository.findAll();
    }
    public List<Category> getAllCategories()
    {
        return categoryRepository.findAll();
    }
}
