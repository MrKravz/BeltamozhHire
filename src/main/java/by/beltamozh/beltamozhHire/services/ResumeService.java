package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.repositories.ResumeRepository;
import org.springframework.stereotype.Service;

@Service
public class ResumeService {

    private final ResumeRepository repository;

    public ResumeService(ResumeRepository repository) {
        this.repository = repository;
    }

}
