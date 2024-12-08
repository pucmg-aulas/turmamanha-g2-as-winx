package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.ParkingHistory;

public class ParkingHistoryView extends JFrame {
    private JPanel contentPane;
    private JTextField clientIdField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JTable historyTable;
    private DefaultTableModel tableModel;
    private JButton viewHistoryButton;
    private JButton filterButton;

    public ParkingHistoryView() {
        setTitle("Parking History");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(10, 10));
        setContentPane(contentPane);

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        inputPanel.add(new JLabel("Client ID:"));
        clientIdField = new JTextField();
        inputPanel.add(clientIdField);

        inputPanel.add(new JLabel("Start Date (YYYY-MM-DD):"));
        startDateField = new JTextField();
        inputPanel.add(startDateField);

        inputPanel.add(new JLabel("End Date (YYYY-MM-DD):"));
        endDateField = new JTextField();
        inputPanel.add(endDateField);

        viewHistoryButton = new JButton("View All History");
        filterButton = new JButton("Filter History");
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(viewHistoryButton);
        buttonPanel.add(filterButton);
        inputPanel.add(buttonPanel);

        contentPane.add(inputPanel, BorderLayout.NORTH);

        // Table
        String[] columns = {"Spot ID", "Vehicle Plate", "Start Time", "End Time", "Price"};
        tableModel = new DefaultTableModel(columns, 0);
        historyTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(historyTable);
        contentPane.add(scrollPane, BorderLayout.CENTER);
    }

    public String getClientId() {
        return clientIdField.getText();
    }

    public String getStartDate() {
        return startDateField.getText();
    }

    public String getEndDate() {
        return endDateField.getText();
    }

    public void addViewHistoryListener(ActionListener listener) {
        viewHistoryButton.addActionListener(listener);
    }

    public void addFilterHistoryListener(ActionListener listener) {
        filterButton.addActionListener(listener);
    }

    public void updateHistoryTable(List<ParkingHistory> history) {
        tableModel.setRowCount(0);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        for (ParkingHistory record : history) {
            Object[] row = {
                record.getSpotId(),
                record.getVehiclePlate(),
                record.getStartTime().format(dateFormatter),
                record.getEndTime().format(dateFormatter),
                String.format("R$ %.2f", record.getPrice())
            };
            tableModel.addRow(row);
        }
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}