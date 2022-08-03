package one.digitalinnovation.parking.controllers;

import one.digitalinnovation.parking.dtos.ParkingRequestDto;
import one.digitalinnovation.parking.dtos.ParkingResponseDto;
import one.digitalinnovation.parking.models.Parking;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import one.digitalinnovation.parking.services.ParkingService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/parkings")
public class ParkingController {

    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping
    public ResponseEntity<List<ParkingResponseDto>> findAll() {
        return ResponseEntity.ok(parkingService.findAll());
    }

    @PostMapping
    public ResponseEntity<ParkingResponseDto> save(@RequestBody @Valid ParkingRequestDto parkingRequestDto) {
        Parking parking = new Parking();
        BeanUtils.copyProperties(parkingRequestDto, parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingService.save(parking));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingResponseDto> findById(@PathVariable String id){
        return ResponseEntity.ok(parkingService.findById(id));
    }

    @GetMapping("/placa/{license}")
    public ResponseEntity<ParkingResponseDto> findByLicense(@PathVariable String license){
        return ResponseEntity.ok(parkingService.findByLicense(license));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        parkingService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingResponseDto> update(@PathVariable String id,
                                                     @RequestBody @Valid ParkingRequestDto parkingRequestDto) {
        Parking parking = new Parking();
        BeanUtils.copyProperties(parkingRequestDto, parking);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(parkingService.update(id, parking));
    }



}
