package one.digitalinnovation.parking.dtos;

import one.digitalinnovation.parking.models.Parking;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParkingMapper {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ParkingResponseDto toParkingResponseDto(Parking parking) {
        return MODEL_MAPPER.map(parking,ParkingResponseDto.class);
    }

    public List<ParkingResponseDto> toParkingDtoResponseList(List<Parking> parkingList) {
        return parkingList.stream()
                .map(this::toParkingResponseDto)
                .collect(Collectors.toList());
    }
}
