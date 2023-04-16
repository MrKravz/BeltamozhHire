package by.beltamozh.beltamozhHire.mappers;

import by.beltamozh.beltamozhHire.dto.HrResponseDto;
import by.beltamozh.beltamozhHire.models.HrResponse;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class HrResponseDtoMapper implements Function<HrResponse, HrResponseDto> {

    @Override
    public HrResponseDto apply(HrResponse hrResponse) {
        return new HrResponseDto(hrResponse.getId(),
                hrResponse.getName());
    }
}
