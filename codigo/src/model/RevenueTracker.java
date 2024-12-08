package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RevenueTracker {
    private List<ParkingHistory> parkingRecords;
    private Park park;

    public static class ClientRevenue {
        private int clientId;
        private String clientName;
        private double revenue;

        public ClientRevenue(int clientId, String clientName, double revenue) {
            this.clientId = clientId;
            this.clientName = clientName;
            this.revenue = revenue;
        }

        public int getClientId() { return clientId; }
        public String getClientName() { return clientName; }
        public double getRevenue() { return revenue; }
    }

    public RevenueTracker(Park park) {
        this.parkingRecords = new ArrayList<>();
        this.park = park;
    }

    public void addParkingRecord(ParkingHistory record) {
        parkingRecords.add(record);
    }

    public double getTotalRevenue() {
        return parkingRecords.stream()
                .mapToDouble(ParkingHistory::getPrice)
                .sum();
    }

    public double getAverageRevenue() {
        if (parkingRecords.isEmpty()) return 0.0;
        return getTotalRevenue() / parkingRecords.size();
    }

    public double getMonthlyRevenue(int year, int month) {
        return parkingRecords.stream()
                .filter(record -> {
                    LocalDateTime endTime = record.getEndTime();
                    return endTime.getYear() == year && endTime.getMonthValue() == month;
                })
                .mapToDouble(ParkingHistory::getPrice)
                .sum();
    }

    public List<ClientRevenue> getTopClientsForMonth(int year, int month, int limit) {
        Map<Integer, Double> clientRevenues = new HashMap<>();
        
        parkingRecords.stream()
            .filter(record -> {
                LocalDateTime endTime = record.getEndTime();
                return endTime.getYear() == year && endTime.getMonthValue() == month;
            })
            .forEach(record -> {
                clientRevenues.merge(record.getClientId(), record.getPrice(), Double::sum);
            });

        return clientRevenues.entrySet().stream()
            .map(entry -> {
                Client client = park.findClientById(entry.getKey());
                String clientName = client != null ? client.getName() : "Unknown Client";
                return new ClientRevenue(
                    entry.getKey(),
                    clientName,
                    entry.getValue()
                );
            })
            .sorted(Comparator.comparingDouble(ClientRevenue::getRevenue).reversed())
            .collect(Collectors.toList());
    }

    public int getMonthlyParkingCount(int year, int month) {
        return (int) parkingRecords.stream()
                .filter(record -> {
                    LocalDateTime endTime = record.getEndTime();
                    return endTime.getYear() == year && endTime.getMonthValue() == month;
                })
                .count();
    }
}