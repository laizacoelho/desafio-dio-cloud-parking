package one.digitalinnovation.parking.services;

import one.digitalinnovation.parking.dtos.ParkingArchiveMapper;
import one.digitalinnovation.parking.dtos.ParkingMapper;
import one.digitalinnovation.parking.dtos.ParkingResponseDto;
import one.digitalinnovation.parking.exeptions.ParkingNotFoundException;
import one.digitalinnovation.parking.models.Parking;
import one.digitalinnovation.parking.models.ParkingArchive;
import one.digitalinnovation.parking.models.ParkingArchiveRepository;
import org.springframework.stereotype.Service;
import one.digitalinnovation.parking.repositories.ParkingRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ParkingService {
    private final ParkingRepository parkingRepository;
    private final ParkingMapper parkingMapper;
    private final ParkingArchiveMapper parkingArchiveMapper;
    private final ParkingArchiveRepository parkingArchiveRepository;


    public ParkingService(ParkingRepository parkingRepository,
                          ParkingMapper parkingMapper,
                          ParkingArchiveMapper parkingArchiveMapper,
                          ParkingArchiveRepository parkingArchiveRepository) {
        this.parkingRepository = parkingRepository;
        this.parkingMapper = parkingMapper;
        this.parkingArchiveMapper = parkingArchiveMapper;
        this.parkingArchiveRepository = parkingArchiveRepository;
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

    @Transactional
    public ParkingResponseDto update(String id, Parking parking) {
        Parking parkingDB = parkingRepository.findById(id).orElseThrow(ParkingNotFoundException::new);
        parking.setId(parkingDB.getId());
        parking.setEntryDate(parkingDB.getEntryDate());
        parking.setExitDate(parkingDB.getExitDate());
        return parkingMapper.toParkingResponseDto(
                        parkingRepository.save(parking));
    }
    
    public ParkingResponseDto findByLicense(String license) {
        Parking byLicense = parkingRepository.findByLicense(license);
        return parkingMapper.toParkingResponseDto(byLicense);
    }

    public ParkingResponseDto exit(String license) {
        Parking parking = parkingRepository.findByLicense(license);
        parking.setExitDate(LocalDateTime.now());
        ParkingArchive parkingArchive = parkingArchiveMapper.toParkingArchive(parking);
        parkingArchiveRepository.save(parkingArchive);
        parkingRepository.delete(parking);
        return parkingMapper.toParkingResponseDto(parking);
    }
}
