package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import model.RevenueTracker.ClientRevenue;

public class RevenueView extends JFrame {
    private JLabel totalRevenueLabel;
    private JLabel averageRevenueLabel;
    private JLabel monthlyRevenueLabel;
    private JLabel monthlyAverageLabel;
    private JTextField yearField;
    private JTextField monthField;
    private JButton calculateButton;
    private JButton monthlyAverageButton;
    private DefaultTableModel tableModel;

    public RevenueView() {
        setTitle("Revenue Statistics");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Stats Panel - Usando BoxLayout para melhor organização vertical
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setBorder(BorderFactory.createTitledBorder("Revenue Statistics"));
        
        totalRevenueLabel = new JLabel("Total Revenue: R$ 0.00");
        averageRevenueLabel = new JLabel("Average Revenue per Use: R$ 0.00");
        monthlyRevenueLabel = new JLabel("Monthly Revenue: R$ 0.00");
        monthlyAverageLabel = new JLabel("Monthly Average Revenue: R$ 0.00");
        
        statsPanel.add(totalRevenueLabel);
        statsPanel.add(Box.createVerticalStrut(2));
        statsPanel.add(averageRevenueLabel);
        statsPanel.add(Box.createVerticalStrut(2));
        statsPanel.add(monthlyRevenueLabel);
        statsPanel.add(Box.createVerticalStrut(2));
        statsPanel.add(monthlyAverageLabel);

        // Input Panel - Usando FlowLayout para organização horizontal compacta
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Search Period"));

        inputPanel.add(new JLabel("Year:"));
        yearField = new JTextField(4);
        inputPanel.add(yearField);

        inputPanel.add(new JLabel("Month:"));
        monthField = new JTextField(2);
        inputPanel.add(monthField);

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 0));
        calculateButton = new JButton("Calculate Revenue");
        monthlyAverageButton = new JButton("Calculate Average");
        
        buttonPanel.add(calculateButton);
        buttonPanel.add(monthlyAverageButton);
        inputPanel.add(buttonPanel);

        // Table Panel com altura fixa
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Top Clients"));
        
        String[] columnNames = {"Rank", "ID", "Name", "Revenue"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable clientTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(clientTable);
        scrollPane.setPreferredSize(new Dimension(450, 150));
        tablePanel.add(scrollPane);

        // Layout setup
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(statsPanel, BorderLayout.NORTH);
        topPanel.add(inputPanel, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
        pack(); // Ajusta o tamanho da janela ao conteúdo
        setLocationRelativeTo(null); // Centraliza a janela
    }

    public void updateClientRanking(List<ClientRevenue> clients) {
        tableModel.setRowCount(0);
        int rank = 1;
        for (ClientRevenue client : clients) {
            Object[] row = {
                rank++,
                client.getClientId(),
                client.getClientName(),
                String.format("R$ %.2f", client.getRevenue())
            };
            tableModel.addRow(row);
        }
    }

    public String getYear() { return yearField.getText(); }
    public String getMonth() { return monthField.getText(); }

    public void setTotalRevenue(double amount) {
        SwingUtilities.invokeLater(() -> 
            totalRevenueLabel.setText(String.format("Total Revenue: R$ %.2f", amount)));
    }

    public void setAverageRevenue(double amount) {
        SwingUtilities.invokeLater(() -> 
            averageRevenueLabel.setText(String.format("Average Revenue per Use: R$ %.2f", amount)));
    }

    public void setMonthlyRevenue(double amount) {
        SwingUtilities.invokeLater(() -> 
            monthlyRevenueLabel.setText(String.format("Monthly Revenue: R$ %.2f", amount)));
    }

    public void setMonthlyAverage(double amount) {
        SwingUtilities.invokeLater(() -> 
            monthlyAverageLabel.setText(String.format("Monthly Average Revenue: R$ %.2f", amount)));
    }

    public void addCalculateListener(ActionListener listener) {
        calculateButton.addActionListener(listener);
    }

    public void addMonthlyAverageListener(ActionListener listener) {
        monthlyAverageButton.addActionListener(listener);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}