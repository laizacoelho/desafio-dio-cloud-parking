package one.digitalinnovation.parking.dtos;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ParkingRequestDto {
    @NotNull
    private String license;
    private String state;
    private String model;
    private String color;
    private LocalDateTime entryDate;

    public ParkingRequestDto(String license, String state, String model, String color, LocalDateTime entryDate) {
        this.license = license;
        this.state = state;
        this.model = model;
        this.color = color;
        this.entryDate = entryDate;
    }

    public ParkingRequestDto() {
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LocalDateTime getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDateTime entryDate) {
        this.entryDate = entryDate;
    }
}
