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
    public void save(Resume entity){
        resumeRepository.save(entity);
    }

    @Override
    @Transactional
    public void update(Resume entity, int id){
        if (resumeRepository.findById(id).isEmpty())
        {
            return;
        }
        Resume resumeToUpdate = resumeRepository.findById(id).get();
        resumeToUpdate.setOwner(entity.getOwner());
        resumeToUpdate.setName(entity.getName());
        resumeToUpdate.setDesiredPosition(entity.getDesiredPosition());
        resumeToUpdate.setSkillLevel(entity.getSkillLevel());
        resumeToUpdate.setDesiredSalary(entity.getDesiredSalary());
        resumeToUpdate.setAbout(entity.getAbout());
        resumeToUpdate.setTechnologies(entity.getTechnologies());
        resumeToUpdate.setCategory(entity.getCategory());
        resumeToUpdate.setHrResponse(entity.getHrResponse());
        resumeRepository.save(resumeToUpdate);
    }

    @Override
    @Transactional
    public void delete(int id)
    {
        resumeRepository.deleteResumeById(id);
    }

}
