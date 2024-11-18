package controller;

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
		this.park.setRows(this.width);
	    this.park.setColumns(this.height);
		ParkDao parkDao = new ParkDao();
		parkDao.loadPark(park);
	}
}
