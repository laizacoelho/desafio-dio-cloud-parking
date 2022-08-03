package one.digitalinnovation.parking.repositories;

import one.digitalinnovation.parking.dtos.ParkingResponseDto;
import one.digitalinnovation.parking.models.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, String> {
    Parking findByLicense(String license);
}
