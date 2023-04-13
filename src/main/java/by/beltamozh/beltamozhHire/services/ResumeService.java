package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.models.Resume;
import by.beltamozh.beltamozhHire.repositories.ResumeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ResumeService implements CrudService<Resume> {
    private final ResumeRepository resumeRepository;

    public ResumeService(ResumeRepository repository) {
        this.resumeRepository = repository;
    }

    @Override
    public Optional<List<Resume>> findAll()
    {
        return Optional.of(resumeRepository.findAll());
    }

    @Override
    public Optional<Resume> findById(int id)
    {
        return Optional.of(resumeRepository.getResumeById(id));
    }

    @Override
    @Transactional
    public void save(Resume resume){
        resumeRepository.save(resume);
    }

    @Override
    @Transactional
    public void update(Resume resume, int id){
        if (resumeRepository.findById(id).isEmpty())
        {
            return;
        }
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

    @Override
    @Transactional
    public void delete(int id)
    {
        resumeRepository.deleteResumeById(id);
    }

}
