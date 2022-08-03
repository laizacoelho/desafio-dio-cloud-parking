package one.digitalinnovation.parking.repositories;

import one.digitalinnovation.parking.models.ParkingArchive;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingArchiveRepository extends JpaRepository<ParkingArchive, String> {
}
