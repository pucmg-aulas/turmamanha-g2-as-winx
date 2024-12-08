package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Park;
import model.ParkingHistory;
import view.ParkingHistoryView;

public class ParkingHistoryController {
    private ParkingHistoryView view;
    private Park park;
    private List<ParkingHistory> parkingHistory;

    public ParkingHistoryController(ParkingHistoryView view, Park park) {
        this.view = view;
        this.park = park;
        this.parkingHistory = new ArrayList<>();

        this.view.addViewHistoryListener(e -> showFullHistory());
        this.view.addFilterHistoryListener(e -> showFilteredHistory());
    }

    private void showFullHistory() {
        String clientIdStr = view.getClientId();
        if (!isValidClientId(clientIdStr)) {
            view.showErrorMessage("Please enter a valid client ID");
            return;
        }

        int clientId = Integer.parseInt(clientIdStr);
        List<ParkingHistory> clientHistory = park.getParkingHistoryByClient(clientId, null, null);
        
        if (clientHistory.isEmpty()) {
            view.showErrorMessage("No parking history found for this client");
            return;
        }
        
        view.updateHistoryTable(clientHistory);
    }

    private void showFilteredHistory() {
        String clientIdStr = view.getClientId();
        String startDateStr = view.getStartDate();
        String endDateStr = view.getEndDate();

        if (!isValidClientId(clientIdStr)) {
            view.showErrorMessage("Please enter a valid client ID");
            return;
        }

        try {
            int clientId = Integer.parseInt(clientIdStr);
            LocalDateTime startDate = LocalDate.parse(startDateStr).atStartOfDay();
            LocalDateTime endDate = LocalDate.parse(endDateStr).atTime(23, 59, 59);

            List<ParkingHistory> filteredHistory = park.getParkingHistoryByClient(clientId, startDate, endDate);
            
            if (filteredHistory.isEmpty()) {
                view.showErrorMessage("No parking history found for the specified period");
                return;
            }
            
            view.updateHistoryTable(filteredHistory);
        } catch (DateTimeParseException e) {
            view.showErrorMessage("Please enter valid dates in YYYY-MM-DD format");
        }
    }

    private boolean isValidClientId(String clientIdStr) {
        try {
            int clientId = Integer.parseInt(clientIdStr);
            return park.findClientById(clientId) != null;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void addParkingRecord(ParkingHistory record) {
        parkingHistory.add(record);
    }
}