package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.models.Company;
import by.beltamozh.beltamozhHire.repositories.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CompanyService implements CrudService<Company> {

    private final CompanyRepository repository;

    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<List<Company>> findAll() {
        return Optional.of(repository.findAll());
    }

    @Override
    public Optional<Company> findById(int id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void save(Company entity) {
        repository.save(entity);
    }

    @Override
    @Transactional
    public void update(Company entity, int id) {
        Optional<Company> company = repository.findById(id);
        if (company.isEmpty()) {
            return;
        }
        Company companyToUpdate = company.get();
        companyToUpdate.setName(entity.getName());
        companyToUpdate.setResumes(entity.getResumes());
        repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(int id) {
        repository.deleteById(id);
    }
}
