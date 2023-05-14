package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.dto.HrResponseDto;
import by.beltamozh.beltamozhHire.mappers.HrResponseMapper;
import by.beltamozh.beltamozhHire.models.HrResponse;
import by.beltamozh.beltamozhHire.repositories.HrResponseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class HrResponseService implements CrudService<HrResponse>, DtoProviderService<HrResponseDto> {

    private final HrResponseRepository repository;

    private final HrResponseMapper mapper;

    public HrResponseService(HrResponseRepository repository, HrResponseMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<List<HrResponse>> findAll() {
        return Optional.of(repository.findAll());
    }

    @Override
    public Optional<HrResponse> findById(int id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public void save(HrResponse entity) {
        repository.save(entity);
    }

    @Transactional
    @Override
    public void update(HrResponse entity, int id) {
        Optional<HrResponse> hrResponse = repository.findById(id);
        if (hrResponse.isEmpty()) {
            return;
        }
        HrResponse hrResponseToUpdate = hrResponse.get();
        hrResponseToUpdate.setName(entity.getName());
        repository.save(entity);
    }

    @Transactional
    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<List<HrResponseDto>> findAllDto() {
        var responses = findAll();
        if (responses.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of((List<HrResponseDto>) mapper.toIterableDto(responses.get()));
    }

    @Override
    public Optional<HrResponseDto> findDtoById(int id) {
        var response = findById(id);
        if (response.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(mapper.toDto(response.get()));
    }

    @Override
    public void saveDto(HrResponseDto dto) {
        save(mapper.toEntity(dto));
    }

    @Override
    public void updateDto(HrResponseDto dto, int id) {
        update(mapper.toEntity(dto), id);
    }
}
