package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.models.Resume;
import by.beltamozh.beltamozhHire.models.Vacancy;
import by.beltamozh.beltamozhHire.repositories.ResumeRepository;
import by.beltamozh.beltamozhHire.repositories.VacancyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class VacancyPaginationService implements PaginationService<Vacancy> {
    private final VacancyRepository repository;

    public VacancyPaginationService(VacancyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Vacancy> getPageableList(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<Vacancy> getSortedAndPageableList(int page, int size, String fieldName) {
        return repository.findAll(PageRequest.of(page, size, Sort.by(fieldName)));
    }
}
