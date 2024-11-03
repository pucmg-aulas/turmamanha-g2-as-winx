package controller;

import model.Park;
import view.ParkingSpotsView;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ParkingSpotsController {
	private ParkingSpotsView view;
	private Park park;
	
	private void spotClicked(int row, int col) {
        boolean[][] spots = park.getParkingSpaces();
        if (!spots[row][col]) { 
            boolean confirmed = view.showConfirmDialog(
                "Do you want to select spot at Row " + row + ", Column " + col + "?"
            );
            
            if (confirmed) {
                view.setSelectedSpot(row, col);
            }
        } else {
            view.showErrorMessage("This spot is already occupied!");
        }
    }

	public ParkingSpotsController(ParkingSpotsView view, Park park) {
		this.view = view;
		this.park = park;

		for (int i = 0; i < park.getRows(); i++) {
			for (int j = 0; j < park.getColumns(); j++) {
				final int row = i;
				final int col = j;
				view.addSpotClickListener(i, j, new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						spotClicked(row, col);
					}
				});
			}
		}

		view.addOccupyButtonListener(e -> occupySelectedSpot());

		updateParkingView();
		view.updateClientTable(park.getClients());
	}

	private void occupySelectedSpot() {
	    String clientIdStr = view.getClientId();
	    String licensePlate = view.getLicensePlate();
	    String yearStr = view.getYear(); 
	    String monthStr = view.getMonth(); 
	    String dayStr = view.getDay(); 
	    String hours = view.getHour();
	    String minutes = view.getMinute();
	    
	    int row = view.getSelectedRow();
	    int col = view.getSelectedColumn();

	    if (clientIdStr.isEmpty() || licensePlate.isEmpty() || yearStr.isEmpty() || monthStr.isEmpty() || dayStr.isEmpty()) {
	        view.showErrorMessage("Please fill all fields!");
	        return;
	    }

	    if (!isNumeric(clientIdStr)) {
	        view.showErrorMessage("Client ID must be a number!");
	        return;
	    }

	    int clientId = Integer.parseInt(clientIdStr);
	    
	    if (!isNumeric(yearStr) || !isNumeric(monthStr) || !isNumeric(dayStr) || !isNumeric(hours) || !isNumeric(minutes)) {
	        view.showErrorMessage("Year, Month, Day, Hour, and Minute must be numbers!");
	        return;
	    }

	    int clientYear = Integer.parseInt(yearStr);
	    int clientMonth = Integer.parseInt(monthStr);
	    int clientDay = Integer.parseInt(dayStr);
	    int clientHour = Integer.parseInt(hours);
	    int clientMinute = Integer.parseInt(minutes);

	    boolean success = park.occupySpot(row, col, clientId, licensePlate, clientYear, clientMonth, clientDay, clientHour, clientMinute);
	    if (success) {
	        updateParkingView();
	        view.updateClientTable(park.getClients());
	        view.clearFields();
	        view.showSuccessMessage("Spot successfully occupied!");
	    }
	}

	private void updateParkingView() {
		boolean[][] parkingSpaces = park.getParkingSpaces();
		view.updateParkingSpots(parkingSpaces);
	}

	private boolean isNumeric(String str) {
		if (str == null || str.isEmpty()) {
			return false;
		}
		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}
}