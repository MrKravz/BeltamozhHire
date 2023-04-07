package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.models.Resume;
import by.beltamozh.beltamozhHire.models.User;
import by.beltamozh.beltamozhHire.repositories.ResumeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ResumeService {

    private final ResumeRepository repository;

    public ResumeService(ResumeRepository repository) {
        this.repository = repository;
    }
    public Optional<List<Resume>> getResumesOfUserById(int id)
    {
        return Optional.of(repository.getResumeByOwnerId(id));
    }
    public Optional<Resume> getResumeById(int id)
    {
        return Optional.of(repository.getResumeById(id));
    }
    @Transactional
    public void save(Resume resume){
        repository.save(resume);
    }
    @Transactional
    public void deleteResumeById(int id)
    {
        repository.deleteResumeById(id);
    }

}
