package one.digitalinnovation.parking.services;

import one.digitalinnovation.parking.dtos.ParkingMapper;
import one.digitalinnovation.parking.dtos.ParkingRequestDto;
import one.digitalinnovation.parking.dtos.ParkingResponseDto;
import one.digitalinnovation.parking.exeptions.ParkingNotFoundException;
import one.digitalinnovation.parking.models.Parking;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import one.digitalinnovation.parking.repositories.ParkingRepository;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingService {
    private final ParkingRepository parkingRepository;
    private final ParkingMapper parkingMapper;


    public ParkingService(ParkingRepository parkingRepository, ParkingMapper parkingMapper) {
        this.parkingRepository = parkingRepository;
        this.parkingMapper = parkingMapper;
    }

     private static String getUUID() {
        return UUID.randomUUID().toString().replace("-","");
     }

    public List<ParkingResponseDto> findAll() {
        List<Parking> parkingList = parkingRepository.findAll();
        return parkingMapper.toParkingDtoResponseList(parkingList);
    }

    public ParkingResponseDto findById(String id) {
        Parking parking = parkingRepository.findById(id).orElseThrow(ParkingNotFoundException::new);
        return parkingMapper.toParkingResponseDto(parking);
    }

    @Transactional
    public ParkingResponseDto save(Parking parking) {
        parking.setId(getUUID());
        parking.setEntryDate(LocalDateTime.now());
        Parking savedParking = parkingRepository.save(parking);
        return parkingMapper.toParkingResponseDto(savedParking);
    }

    @Transactional
    public void delete(String id) {
        Parking parking = parkingRepository.findById(id).orElseThrow(ParkingNotFoundException::new);
        parkingRepository.delete(parking);
    }

    public ParkingResponseDto update(String id, Parking parking) {
        Parking parkingDB = parkingRepository.findById(id).orElseThrow(ParkingNotFoundException::new);
        parking.setId(parkingDB.getId());
        return parkingMapper.toParkingResponseDto(
                        parkingRepository.save(parking));
    }
}
