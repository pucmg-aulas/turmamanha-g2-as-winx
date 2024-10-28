package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import model.RentalOfCarSpace;

class RentalOfCarSpaceTest {

	@Test
	public void CalculateTime() {
		RentalOfCarSpace r1 = new RentalOfCarSpace(null, null, null, null, 0);
        LocalDateTime startDateTime = LocalDateTime.of(2024, 9, 29, 8, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2024, 9, 29, 12, 46);

        String duration = r1.calculateTime(startDateTime, endDateTime);
        
        String timeExpected = "04:46:00";;
        		
        assertEquals(timeExpected, duration);
	}
	@Test
	public void CalculatePrice() {
		RentalOfCarSpace r1 = new RentalOfCarSpace(null, null, null, null, 0);
        LocalDateTime startDateTime = LocalDateTime.of(2024, 9, 29, 8, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2024, 9, 29, 12, 46);

        String duration = r1.calculateTime(startDateTime, endDateTime);
        Double totalPrice = r1.calculatePrice(startDateTime, endDateTime);
        
        Double priceExpected = 50.0;
        			
        assertEquals(priceExpected, totalPrice, 0.0001);
	}
}
