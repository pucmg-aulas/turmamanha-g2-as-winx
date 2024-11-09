package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.Client;
import model.ClientVip;
import model.Vehicle;

class ClientTest {

	@Test
	void ClientAddTest() {
		Client c1 = new Client(1, "Cleber");
		Client c1Expected = new ClientVip(1, "Cleber");
		
		assertEquals(c1, c1Expected);
	}
	@Test 
	void ClientAddVehicleTest() {
		Client c1 = new Client(1, "Cleber");
		Vehicle bmw = new Vehicle("000", "bmw");
		Vehicle fusca = new Vehicle("123", "fusca");
		
		c1.addVehicle(bmw);
		c1.addVehicle(fusca);
				
		List<Vehicle> vehiclesExpected = new ArrayList<>();
        vehiclesExpected.add(bmw);
        vehiclesExpected.add(fusca);

        assertEquals(vehiclesExpected, c1.getVehicles());
		
	}

}
