package controller;

import model.RevenueTracker;
import model.Park;
import model.ParkingHistory;
import view.RevenueView;
import java.time.LocalDateTime;
import java.util.List;

public class RevenueController {
    private RevenueView view;
    private Park park;

    public RevenueController(RevenueView view, Park park) {
        this.view = view;
        this.park = park;
        
        // Add listener for the calculate button
        view.addCalculateListener(e -> calculateRevenue());
        
        // Add listener for the monthly average button
        view.addMonthlyAverageListener(e -> calculateMonthlyAverage());
        
        // Initialize with overall stats
        updateOverallStats();
    }

    private void calculateRevenue() {
        try {
            int year = Integer.parseInt(view.getYear());
            int month = Integer.parseInt(view.getMonth());
            
            if (month < 1 || month > 12) {
                view.showErrorMessage("Month must be between 1 and 12");
                return;
            }

            double monthlyRevenue = park.getRevenueTracker().getMonthlyRevenue(year, month);
            view.setMonthlyRevenue(monthlyRevenue);

            List<RevenueTracker.ClientRevenue> topClients = 
                park.getRevenueTracker().getTopClientsForMonth(year, month, Integer.MAX_VALUE);
            view.updateClientRanking(topClients);

        } catch (NumberFormatException e) {
            view.showErrorMessage("Please enter valid numbers for year and month");
        }
    }

    private void calculateMonthlyAverage() {
        try {
            int year = Integer.parseInt(view.getYear());
            int month = Integer.parseInt(view.getMonth());
            
            if (month < 1 || month > 12) {
                view.showErrorMessage("Month must be between 1 and 12");
                return;
            }

            double monthlyRevenue = park.getRevenueTracker().getMonthlyRevenue(year, month);
            int parkingCount = park.getRevenueTracker().getMonthlyParkingCount(year, month);
            
            double monthlyAverage = parkingCount > 0 ? monthlyRevenue / parkingCount : 0.0;
            view.setMonthlyAverage(monthlyAverage);

        } catch (NumberFormatException e) {
            view.showErrorMessage("Please enter valid numbers for year and month");
        }
    }

    private void updateOverallStats() {
        double totalRevenue = park.getRevenueTracker().getTotalRevenue();
        double averageRevenue = park.getRevenueTracker().getAverageRevenue();
        
        view.setTotalRevenue(totalRevenue);
        view.setAverageRevenue(averageRevenue);
    }

    public void addParkingRecord(int clientId, String spotId, String vehiclePlate,
                               LocalDateTime startTime, LocalDateTime endTime, double price) {
        park.getRevenueTracker().addParkingRecord(new ParkingHistory(clientId, spotId, vehiclePlate, 
                                       startTime, endTime, price));
        updateOverallStats();
    }
}