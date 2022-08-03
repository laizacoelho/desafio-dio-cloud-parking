package one.digitalinnovation.parking.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ParkingNotFoundException extends RuntimeException{

    public ParkingNotFoundException() {
        super("Vaga não encontrada com o id informado");
    }
}
