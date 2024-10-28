package view;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Client;
import java.awt.Color;

public class AddVehicleView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldPlate;
    private JTextField textFieldModel;
    private JTextField textFieldClientId;
    private JButton btnRegisterVehicle;
    private JTable clientTable;
    private DefaultTableModel tableModel;

    public AddVehicleView() {
        setTitle("Register Vehicle");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 700, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        String[] columnNames = {"Client ID", "Client Name"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        clientTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(clientTable);
        scrollPane.setBounds(350, 27, 300, 400);
        contentPane.add(scrollPane);

        JLabel lblClientList = new JLabel("Client list:");
        lblClientList.setHorizontalAlignment(SwingConstants.LEFT);
        lblClientList.setBounds(350, 10, 100, 14);
        contentPane.add(lblClientList);

        JLabel lblClientId = new JLabel("Client ID:");
        lblClientId.setHorizontalAlignment(SwingConstants.RIGHT);
        lblClientId.setBounds(10, 27, 112, 14);
        contentPane.add(lblClientId);

        textFieldClientId = new JTextField();
        textFieldClientId.setBounds(77, 42, 200, 20);
        contentPane.add(textFieldClientId);
        textFieldClientId.setColumns(10);

        JLabel lblPlate = new JLabel("Plate:");
        lblPlate.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPlate.setBounds(10, 73, 100, 14);
        contentPane.add(lblPlate);

        textFieldPlate = new JTextField();
        textFieldPlate.setBounds(77, 87, 200, 20);
        contentPane.add(textFieldPlate);
        textFieldPlate.setColumns(10);

        JLabel lblModel = new JLabel("Model:");
        lblModel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblModel.setBounds(10, 118, 100, 14);
        contentPane.add(lblModel);

        textFieldModel = new JTextField();
        textFieldModel.setBounds(77, 134, 200, 20);
        contentPane.add(textFieldModel);
        textFieldModel.setColumns(10);

        btnRegisterVehicle = new JButton("Register Vehicle");
        btnRegisterVehicle.setForeground(new Color(240, 255, 255));
        btnRegisterVehicle.setBackground(new Color(0, 206, 209));
        btnRegisterVehicle.setBounds(77, 188, 200, 23);
        contentPane.add(btnRegisterVehicle);
    }

    public void updateClientTable(List<Client> clients) {
        tableModel.setRowCount(0);
        for (Client client : clients) {
            Object[] row = {client.getId(), client.getName()};
            tableModel.addRow(row);
        }
    }

    public String getPlate() {
        return textFieldPlate.getText();
    }

    public String getModel() {
        return textFieldModel.getText();
    }

    public String getClientId() {
        return textFieldClientId.getText();
    }

    public void addRegisterVehicleListener(ActionListener listener) {
        btnRegisterVehicle.addActionListener(listener);
    }

    public void clearFields() {
        textFieldPlate.setText("");
        textFieldModel.setText("");
        textFieldClientId.setText("");
    }

    public void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this,
            message,
            "Success",
            JOptionPane.INFORMATION_MESSAGE);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this,
            message,
            "Error",
            JOptionPane.ERROR_MESSAGE);
    }
}