package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.models.Resume;
import by.beltamozh.beltamozhHire.models.SkillLevel;
import by.beltamozh.beltamozhHire.models.Technology;
import by.beltamozh.beltamozhHire.models.User;
import by.beltamozh.beltamozhHire.repositories.ResumeRepository;
import by.beltamozh.beltamozhHire.repositories.SkillLevelRepository;
import by.beltamozh.beltamozhHire.repositories.TechnologyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final SkillLevelRepository skillLevelRepository;
    private final TechnologyRepository technologyRepository;

    public ResumeService(ResumeRepository repository, SkillLevelRepository skillLevelRepository, TechnologyRepository technologyRepository) {
        this.resumeRepository = repository;
        this.skillLevelRepository = skillLevelRepository;
        this.technologyRepository = technologyRepository;
    }
    public Optional<List<Resume>> getResumesOfUserById(int id)
    {
        return Optional.of(resumeRepository.getResumeByOwnerId(id));
    }
    public Optional<Resume> getResumeById(int id)
    {
        return Optional.of(resumeRepository.getResumeById(id));
    }
    public Optional<User> getOwnerByResumeId(int id)
    {
        return Optional.of(getResumeById(id).get().getOwner());
    }

    public List<SkillLevel> getAllSkillLevels()
    {
        return (List<SkillLevel>) skillLevelRepository.findAll();
    }
    public List<Technology> getAllTechnologies()
    {
        return (List<Technology>) technologyRepository.findAll();
    }
    @Transactional
    public void save(Resume resume){
        resumeRepository.save(resume);
    }
    @Transactional
    public void update(Resume resume, int id){
        Resume resumeToUpdate = resumeRepository.findById(id).get();
        resumeToUpdate.setOwner(resume.getOwner());
        resumeToUpdate.setName(resume.getName());
        resumeToUpdate.setDesiredPosition(resume.getDesiredPosition());
        resumeToUpdate.setSkillLevel(resume.getSkillLevel());
        resumeToUpdate.setDesiredSalary(resume.getDesiredSalary());
        resumeToUpdate.setAbout(resume.getAbout());
        resumeToUpdate.setTechnologies(resume.getTechnologies());
        resumeToUpdate.setCategory(resume.getCategory());
        resumeToUpdate.setHrResponse(resume.getHrResponse());
        resumeRepository.save(resumeToUpdate);
    }
    @Transactional
    public void deleteResumeById(int id)
    {
        resumeRepository.deleteResumeById(id);
    }

}
