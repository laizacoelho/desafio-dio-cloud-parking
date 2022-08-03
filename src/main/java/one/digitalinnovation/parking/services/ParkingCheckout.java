package one.digitalinnovation.parking.services;

import one.digitalinnovation.parking.models.Parking;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ParkingCheckout {

    public static final int TWENTY_FOUR_HOURS = 24;
    public static final double ONE_HOUR_PRICE = 5.00;
    public static final double ADDITIONAL_PER_HOUR_PRICE = 2.00;
    public static final double DAY_PRICE = 20.00;

    public static Double getBill(Parking parking) {
        return getBill(parking.getEntryDate(), parking.getExitDate());
    }

    private static Double getBill(LocalDateTime entryDate, LocalDateTime exitDate) {
        long hours = entryDate.until(exitDate, ChronoUnit.HOURS);
        long days = entryDate.until(entryDate, ChronoUnit.DAYS);
        double bill;

        if (days <= 1) {
            if (hours <= 1) {
                return ONE_HOUR_PRICE;
            } else {
                bill = ONE_HOUR_PRICE;
                hours--;
                bill = bill + ((int) hours) * ADDITIONAL_PER_HOUR_PRICE;
                return bill;
            }
        } else {
            double fullDays = Math.floor(days);
            double extraHours = days - fullDays;
            bill = (fullDays * DAY_PRICE) + ((int)(extraHours * TWENTY_FOUR_HOURS) * ADDITIONAL_PER_HOUR_PRICE);
            return  bill;
        }
    }
}


