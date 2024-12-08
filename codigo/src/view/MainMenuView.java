	package view;
	
	import java.awt.EventQueue;
	import java.awt.Color;
	
	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.border.EmptyBorder;
	import javax.swing.JLabel;
	import java.awt.SystemColor;
	import java.awt.Font;
	import javax.swing.JButton;
	import javax.swing.JDesktopPane;
	import java.awt.event.ActionListener;
	import java.awt.event.ActionEvent;
	import javax.swing.BoxLayout;
	import javax.swing.JInternalFrame;
	import java.awt.GridLayout;
	import java.awt.GridBagLayout;
	import java.awt.GridBagConstraints;
	import java.awt.Insets;
	import java.awt.CardLayout;
import javax.swing.SwingConstants;
	
	public class MainMenuView extends JFrame {
	
		private static final long serialVersionUID = 1L;
		private JPanel contentPane;
		private JButton btnNewClient;
		private JButton btnNewVehicle;
		private JButton btnParkingSpots;
		private JButton btnSetParkingSize;
		private JButton btnViewHistory;
	
		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						MainMenuView frame = new MainMenuView();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	
		/**
		 * Create the frame.
		 */
		public MainMenuView() {
			setBackground(SystemColor.menu);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	
			setContentPane(contentPane);
			contentPane.setLayout(new GridLayout(0, 1, 0, 0));
			
			JLabel lblNewLabel = new JLabel("Welcome to Xulambs Inc. Java Parking!");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			contentPane.add(lblNewLabel);
			lblNewLabel.setFont(new Font("Myanmar Text", Font.PLAIN, 22));
			
			JPanel panel = new JPanel();
			contentPane.add(panel);
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			
			btnNewClient = new JButton("New client");
			panel.add(btnNewClient);
			
			btnNewVehicle = new JButton("New vehicle");
			panel.add(btnNewVehicle);
			
			btnParkingSpots = new JButton("Parking spots");
			panel.add(btnParkingSpots);
			
			btnSetParkingSize = new JButton("Set parking size");
			panel.add(btnSetParkingSize);
			
			btnViewHistory = new JButton("View Parking History");
			panel.add(btnViewHistory);
		}
	
		public void addClientListener(ActionListener listener) {
		    btnNewClient.addActionListener(listener);
		}

		public void addVehicleListener(ActionListener listener) {
		    btnNewVehicle.addActionListener(listener);
		}

		public void addParkingSpotsListener(ActionListener listener) {
		    btnParkingSpots.addActionListener(listener);
		}

		public void addParkingSizeListener(ActionListener listener) {
		    btnSetParkingSize.addActionListener(listener);
		}

		public void addViewHistoryListener(ActionListener listener) {
		    btnViewHistory.addActionListener(listener);
		}
	}
