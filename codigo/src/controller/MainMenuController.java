package controller;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import dao.ClientDao;
import dao.ParkDao;
import dao.VehicleDao;
import model.Client;
import model.Park;
import model.Vehicle;
import view.AddClientView;
import view.AddVehicleView;
import view.MainMenuView;
import view.ParkingSizeView;
import view.ParkingSpotsView;

public class MainMenuController {
	
	private MainMenuView view;
	private Park park;
	
	public MainMenuController(MainMenuView view, Park park) {
		this.view = view;
		this.park = park;
		
		this.view.addClientListener(e -> showAddClientView());
		this.view.addVehicleListener(e -> showAddVehicleView());
		this.view.addParkingSpotsListener(e -> showParkingSpotsView());
		this.view.addParkingSizeListener(e -> showParkingSizeView());
	}

	public void showAddClientView() {
		AddClientView view = new AddClientView();
		AddClientController controller = new AddClientController(view, park);
		view.setVisible(true);
	}
	
	private void showAddVehicleView() {
		AddVehicleView view = new AddVehicleView();
		AddVehicleController controller = new AddVehicleController(view, park);
		view.setVisible(true);
	}
	
	private void showParkingSpotsView() {
		ParkingSpotsView view = new ParkingSpotsView(park.getRows(), park.getColumns());
		ParkingSpotsController controller = new ParkingSpotsController(view, park);
		view.setVisible(true);
	}
	
	private void showParkingSizeView() {
		ParkingSizeView view = new ParkingSizeView();
		ParkingSizeController controller = new ParkingSizeController(view, park);
		view.setVisible(true);
	}

	@SuppressWarnings("unused")
	private void methodWithUnusedVariable() {
		// Method implementation
	}
}


