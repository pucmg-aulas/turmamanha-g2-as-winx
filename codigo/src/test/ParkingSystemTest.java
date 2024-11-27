package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.Park;
import model.CarSpace;
import model.Client;
import model.Vehicle;
import java.time.LocalDateTime;

public class ParkingSystemTest {
    private Park park;
    private Client testClient;
    private Vehicle testVehicle;

    @Before
    public void setUp() {
        // Criar um estacionamento 3x3 para teste
        park = new Park(3, 3);
        
        // Criar um cliente de teste
        testClient = new Client(1, "Test Client");
        testVehicle = new Vehicle("ABC123", "Test Car");
        testClient.addVehicle(testVehicle);
        park.addClient(testClient);
    }

    @Test
    public void testSpotIdentification() {
        CarSpace[][] spots = park.getParkingSpaces();
        
        // Verificar se as identificações das vagas estão corretas
        assertEquals("A01", spots[0][0].getSpotId());
        assertEquals("A02", spots[0][1].getSpotId());
        assertEquals("B01", spots[1][0].getSpotId());
        assertEquals("C03", spots[2][2].getSpotId());
    }

    @Test
    public void testOccupySpot() {
        // Tentar ocupar uma vaga
        boolean success = park.occupySpot(0, 0, 1, "ABC123", 2024, 3, 15, 14, 30);
        assertTrue("Should successfully occupy the spot", success);
        
        // Verificar se a vaga está ocupada
        assertTrue("Spot should be marked as occupied", 
            park.getParkingSpaces()[0][0].isOccupied());
        
        // Tentar ocupar a mesma vaga novamente
        success = park.occupySpot(0, 0, 1, "ABC123", 2024, 3, 15, 14, 35);
        assertFalse("Should not be able to occupy an already occupied spot", success);
    }

    @Test
    public void testFreeSpot() {
        // Primeiro ocupar uma vaga
        park.occupySpot(0, 0, 1, "ABC123", 2024, 3, 15, 14, 30);
        
        // Tentar liberar a vaga
        boolean success = park.freeSpot(0, 0, 2024, 3, 15, 15, 30);
        assertTrue("Should successfully free the spot", success);
        
        // Verificar se a vaga está livre
        assertFalse("Spot should be marked as not occupied", 
            park.getParkingSpaces()[0][0].isOccupied());
    }

    @Test
    public void testParkBoundaries() {
        CarSpace[][] spots = park.getParkingSpaces();
        
        // Verificar se o tamanho do estacionamento está correto
        assertEquals("Should have 3 rows", 3, spots.length);
        assertEquals("Should have 3 columns", 3, spots[0].length);
        
        // Verificar se todas as vagas foram inicializadas
        for (int i = 0; i < spots.length; i++) {
            for (int j = 0; j < spots[i].length; j++) {
                assertNotNull("Spot should not be null", spots[i][j]);
                assertFalse("Spot should start unoccupied", spots[i][j].isOccupied());
                
                String expectedRow = String.valueOf((char)('A' + i));
                String expectedId = String.format("%s%02d", expectedRow, j + 1);
                assertEquals("Spot ID should match pattern", expectedId, spots[i][j].getSpotId());
            }
        }
    }
}