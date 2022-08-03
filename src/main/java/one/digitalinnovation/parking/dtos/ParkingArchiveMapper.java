package one.digitalinnovation.parking.dtos;

import one.digitalinnovation.parking.models.Parking;
import one.digitalinnovation.parking.models.ParkingArchive;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParkingArchiveMapper {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ParkingArchive toParkingArchive(Parking parking) {
        return MODEL_MAPPER.map(parking,ParkingArchive.class);
    }
}
