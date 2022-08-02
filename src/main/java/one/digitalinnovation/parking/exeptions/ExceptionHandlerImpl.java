package one.digitalinnovation.parking.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerImpl {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ParkingNotFoundException.class)
    public ResponseEntity<String> handleParkingNotFound(ParkingNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }
}
