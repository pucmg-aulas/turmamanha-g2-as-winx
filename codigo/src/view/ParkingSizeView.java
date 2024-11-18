package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Color;

public class ParkingSizeView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtWidth;
	private JTextField txtHeight;
	private JButton btnDone;
	private JLabel lblCurrentlyParkingSize;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParkingSizeView frame = new ParkingSizeView();
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
	public ParkingSizeView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Currently parking size: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(lblNewLabel);
		
		lblCurrentlyParkingSize = new JLabel("");
		lblCurrentlyParkingSize.setHorizontalAlignment(SwingConstants.LEFT);
		lblCurrentlyParkingSize.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(lblCurrentlyParkingSize);
		
		JPanel panel_6 = new JPanel();
		contentPane.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("New size");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		panel_6.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Width");
		panel_2.add(lblNewLabel_2);
		lblNewLabel_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_3 = new JLabel("Height");
		panel_2.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setVerticalAlignment(SwingConstants.BOTTOM);
		
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel = new JPanel();
		panel_3.add(panel);
		
		txtWidth = new JTextField();
		panel.add(txtWidth);
		txtWidth.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_3.add(panel_4);
		
		txtHeight = new JTextField();
		panel_4.add(txtHeight);
		txtHeight.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		contentPane.add(panel_5);
		
		btnDone = new JButton("Done");
		btnDone.setForeground(new Color(240, 255, 240));
		btnDone.setBackground(new Color(0, 128, 128));
		panel_5.add(btnDone);
	}
	
	public void setLblCurrentlySizePark(String text) {
		lblCurrentlyParkingSize.setText(text);
	}
	
	public String getTxtWidth() {
		return txtWidth.getText();
	}

	public String getTxtHeight() {
		return txtHeight.getText();
	}

	public void doneListener(ActionListener listener) {
		btnDone.addActionListener(listener);
	}
	
    public void clearFields() {
    	txtWidth.setText("");
    	txtHeight.setText("");
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
