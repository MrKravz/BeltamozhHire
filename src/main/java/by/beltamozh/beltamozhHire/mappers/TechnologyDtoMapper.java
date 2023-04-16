package by.beltamozh.beltamozhHire.mappers;

import by.beltamozh.beltamozhHire.dto.TechnologyDto;
import by.beltamozh.beltamozhHire.models.Technology;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TechnologyDtoMapper implements Function<Technology, TechnologyDto> {
    @Override
    public TechnologyDto apply(Technology technology) {
        return new TechnologyDto(technology.getId(), technology.getName());
    }
}
