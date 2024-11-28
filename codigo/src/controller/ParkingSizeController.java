package controller;

import java.time.LocalDateTime;
import model.CarSpace;
import model.Vip;
import model.Elder;
import model.Pcd;
import dao.ParkDao;
import model.Park;
import view.ParkingSizeView;

public class ParkingSizeController {
	private ParkingSizeView view;
	private Park park;
	private int width;
	private int height;
	
	public ParkingSizeController(ParkingSizeView view, Park park) {
		this.view = view;
		this.park = park;
		
		setCurrently();
		this.view.doneListener(e -> setNewParkingSize());

	}
	
	public void setNewParkingSize() {
		this.width = Integer.valueOf(view.getTxtWidth());
		this.height = Integer.valueOf(view.getTxtHeight());
		
		if(park.verifySize(height) && park.verifySize(width)) {
			setPark();
			setCurrently();
			view.showSuccessMessage("Parking size updated!");
			view.clearFields();
		} else {
			view.showErrorMessage("Verify if the sizes are between 4 and 20");
		}
	}
	
	public void setCurrently() {
		this.view.setLblCurrentlySizePark
		(park.getRows() 
				+ " x " 
				+ park.getColumns());
	}
	
	public void setPark() {
		// Create new arrays with new dimensions
		CarSpace[][] newParkingSpaces = new CarSpace[this.width][this.height];
		LocalDateTime[][] newParkingStartTimes = new LocalDateTime[this.width][this.height];
		String[][] newParkingVehiclePlates = new String[this.width][this.height];
		
		// Initialize the new parking spaces with the correct types
		for (int i = 0; i < this.width; i++) {
			String rowLetter = String.valueOf((char)('A' + i));
			for (int j = 0; j < this.height; j++) {
				double baseValue = 4.0; // Base price per 15-minute fraction
				
				if (i == 0) {
					// First row - VIP spots
					newParkingSpaces[i][j] = new Vip(true, true, baseValue);
				}
				else if (i == 1) {
					// Second row - Elder and PCD spots
					if (j < this.height/2 || (this.height % 2 != 0 && j == this.height/2)) {
						// First half (and middle spot if odd) - Elder spots
						newParkingSpaces[i][j] = new Elder(baseValue);
					} else {
						// Second half - PCD spots
						newParkingSpaces[i][j] = new Pcd(true, baseValue);
					}
				}
				else {
					// Regular spots for all other rows
					newParkingSpaces[i][j] = new CarSpace(rowLetter, j + 1);
				}
				
				// Set the spot ID for all spaces
				newParkingSpaces[i][j].setRow(rowLetter);
				newParkingSpaces[i][j].setNumber(j + 1);
			}
		}
		
		// Update all parking arrays in the park
		this.park.setRows(this.width);
		this.park.setColumns(this.height);
		this.park.setParkingSpaces(newParkingSpaces);
		this.park.setParkingStartTimes(newParkingStartTimes);
		this.park.setParkingVehiclePlates(newParkingVehiclePlates);
		
		// Save the updated park configuration
		ParkDao parkDao = new ParkDao();
		parkDao.savePark(park);
	}
}
