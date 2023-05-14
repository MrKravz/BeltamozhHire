package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.models.Resume;
import by.beltamozh.beltamozhHire.repositories.ResumeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ResumePaginationService implements PaginationService<Resume> {
    private final ResumeRepository repository;

    public ResumePaginationService(ResumeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Resume> getPageableList(int page, int size) {
        var pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }

    @Override
    public Page<Resume> getSortedAndPageableList(int page, int size, String fieldName) {
        var pageable = PageRequest.of(page, size, Sort.by(fieldName));
        return repository.findAll(pageable);
    }
}
