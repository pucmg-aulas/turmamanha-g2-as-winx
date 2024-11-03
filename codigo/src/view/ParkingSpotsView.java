package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.List;
import model.Client;
import model.Vehicle;

public class ParkingSpotsView extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel parkingGrid;
	private JLabel[][] spotLabels;
	private JTextField textFieldClientId;
	private JTextField textFieldLicensePlate;
	private JTextField textFieldYear;
	private JTextField textFieldMonth;
	private JTextField textFieldDay;
	private JTextField textFieldHour;
	private JTextField textFieldMinute;
	private JButton btnOccupy;
	private JTable clientTable;
	private DefaultTableModel tableModel;
	private int selectedRow = -1;
	private int selectedColumn = -1;

	public ParkingSpotsView(int rows, int columns) {
		rows = Math.max(1, rows);
		columns = Math.max(1, columns);

		setTitle("Parking Spots View");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 600);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JLabel lblEntrance = new JLabel("Parking Entrance");
		lblEntrance.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblEntrance, BorderLayout.NORTH);

		JPanel mainPanel = new JPanel(new BorderLayout());

		parkingGrid = new JPanel(new GridLayout(rows, columns, 5, 5));
		parkingGrid.setBorder(new EmptyBorder(10, 10, 10, 10));

		spotLabels = new JLabel[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				JLabel spot = new JLabel("[ ]");
				spot.setHorizontalAlignment(SwingConstants.CENTER);
				spot.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				spot.setOpaque(true);
				spot.setBackground(Color.WHITE);
				final int row = i;
				final int col = j;
				spot.setToolTipText("Row: " + row + ", Column: " + col);
				spotLabels[i][j] = spot;
				parkingGrid.add(spot);
			}
		}

		mainPanel.add(parkingGrid, BorderLayout.CENTER);

		JPanel rightPanel = new JPanel(new BorderLayout());

		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(8, 5, 5, 5));
		controlPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		controlPanel.add(new JLabel("Client ID:"));
		textFieldClientId = new JTextField();
		controlPanel.add(textFieldClientId);

		controlPanel.add(new JLabel("Vehicle Plate:"));
		textFieldLicensePlate = new JTextField();
		controlPanel.add(textFieldLicensePlate);
		
		controlPanel.add(new JLabel("YEAR:"));
		textFieldYear = new JTextField();
		controlPanel.add(textFieldYear);
		
		controlPanel.add(new JLabel("MONTH:"));
		textFieldMonth = new JTextField();
		controlPanel.add(textFieldMonth);
		
		controlPanel.add(new JLabel("DAY:"));
		textFieldDay = new JTextField();
		controlPanel.add(textFieldDay);
		
		controlPanel.add(new JLabel("HOUR:"));
		textFieldHour = new JTextField();
		controlPanel.add(textFieldHour);
		
		controlPanel.add(new JLabel("MINUTE:"));
		textFieldMinute = new JTextField();
		controlPanel.add(textFieldMinute);

		btnOccupy = new JButton("Occupy selected spot");
		btnOccupy.setForeground(new Color(240, 255, 255));
		btnOccupy.setBackground(new Color(0, 206, 209));
		btnOccupy.setEnabled(false);
		controlPanel.add(btnOccupy);

		rightPanel.add(controlPanel, BorderLayout.NORTH);

		String[] columnNames = { "Client ID", "Client Name", "Vehicle Plate", "Vehicle Model" };
		tableModel = new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		clientTable = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(clientTable);
		rightPanel.add(scrollPane, BorderLayout.CENTER);

		mainPanel.add(rightPanel, BorderLayout.EAST);
		contentPane.add(mainPanel, BorderLayout.CENTER);

		JLabel lblExit = new JLabel("Parking Exit");
		lblExit.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblExit, BorderLayout.SOUTH);
	}

	public void updateClientTable(List<Client> clients) {
		tableModel.setRowCount(0);
		for (Client client : clients) {
			if (client.getVehicles().isEmpty()) {
				Object[] row = { client.getId(), client.getName(), "-", "-" };
				tableModel.addRow(row);
			} else {
				for (Vehicle vehicle : client.getVehicles()) {
					Object[] row = { client.getId(), client.getName(), vehicle.getPlate(), vehicle.getModel() };
					tableModel.addRow(row);
				}
			}
		}
	}

	public void addSpotClickListener(int row, int col, MouseListener listener) {
		spotLabels[row][col].addMouseListener(listener);
	}

	public void addOccupyButtonListener(ActionListener listener) {
		btnOccupy.addActionListener(listener);
	}

	public void setSelectedSpot(int row, int col) {
		if (selectedRow >= 0 && selectedColumn >= 0) {
			if (!spotLabels[selectedRow][selectedColumn].getText().equals("[X]")) {
				spotLabels[selectedRow][selectedColumn].setBackground(Color.WHITE);
			}
		}

		selectedRow = row;
		selectedColumn = col;
		if (!spotLabels[row][col].getText().equals("[X]")) {
			spotLabels[row][col].setBackground(Color.YELLOW);
			btnOccupy.setEnabled(true);
		}
	}

	public String getClientId() {
		return textFieldClientId.getText();
	}

	public String getLicensePlate() {
		return textFieldLicensePlate.getText();
	}
	
	public String getYear() {
		return textFieldYear.getText();
	}
	public String getMonth() {
		return textFieldMonth.getText();
	}
	
	public String getDay() {
		return textFieldDay.getText();
	}
	public String getHour() {
		return textFieldHour.getText();
	}
	
	public String getMinute() {
		return textFieldMinute.getText();
	}
	public int getSelectedRow() {
		return selectedRow;
	}

	public int getSelectedColumn() {
		return selectedColumn;
	}

	public void updateParkingSpots(boolean[][] parkingSpaces) {
		for (int i = 0; i < parkingSpaces.length; i++) {
			for (int j = 0; j < parkingSpaces[i].length; j++) {
				if (parkingSpaces[i][j]) {
					spotLabels[i][j].setText("[X]");
					spotLabels[i][j].setBackground(Color.RED);
				} else {
					spotLabels[i][j].setText("[ ]");
					spotLabels[i][j].setBackground(Color.GREEN);
				}
			}
		}
	}

	public boolean showConfirmDialog(String message) {
		int response = JOptionPane.showConfirmDialog(this, message, "Confirm Spot Selection", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		return response == JOptionPane.YES_OPTION;
	}

	public void clearFields() {
		textFieldClientId.setText("");
		textFieldLicensePlate.setText("");
		textFieldYear.setText("");
		textFieldMonth.setText("");
		textFieldDay.setText("");
		textFieldHour.setText("");
		textFieldMinute.setText("");
		
		btnOccupy.setEnabled(false);
		if (selectedRow >= 0 && selectedColumn >= 0) {
			if (!spotLabels[selectedRow][selectedColumn].getText().equals("[X]")) {
				spotLabels[selectedRow][selectedColumn].setBackground(Color.WHITE);
			}
		}
		selectedRow = -1;
		selectedColumn = -1;
	}

	public void showSuccessMessage(String message) {
		JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
	}

	public void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
}