package view;

import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import java.awt.Color;

public class AddClientView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldName;
    private JButton btnRegisterNormal;
    private JButton btnRegisterAnonymous;
    private JRadioButton rdbtnNormal;
    private JRadioButton rdbtnElder;
    private JRadioButton rdbtnPcd;
    private JRadioButton rdbtnVip;
    private ButtonGroup clientTypeGroup;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddClientView frame = new AddClientView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public AddClientView() {
        setTitle("Register Client");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblName = new JLabel("Client name:");
        lblName.setHorizontalAlignment(SwingConstants.RIGHT);
        lblName.setBounds(10, 30, 100, 14);
        contentPane.add(lblName);

        textFieldName = new JTextField();
        textFieldName.setBounds(120, 27, 200, 20);
        contentPane.add(textFieldName);
        textFieldName.setColumns(10);

        // Client type radio buttons
        JLabel lblClientType = new JLabel("Client type:");
        lblClientType.setHorizontalAlignment(SwingConstants.RIGHT);
        lblClientType.setBounds(10, 60, 100, 14);
        contentPane.add(lblClientType);

        clientTypeGroup = new ButtonGroup();

        rdbtnNormal = new JRadioButton("Normal");
        rdbtnNormal.setBounds(120, 60, 80, 23);
        rdbtnNormal.setSelected(true);
        contentPane.add(rdbtnNormal);

        rdbtnElder = new JRadioButton("Elder");
        rdbtnElder.setBounds(200, 60, 80, 23);
        contentPane.add(rdbtnElder);

        rdbtnPcd = new JRadioButton("PCD");
        rdbtnPcd.setBounds(280, 60, 80, 23);
        contentPane.add(rdbtnPcd);

        rdbtnVip = new JRadioButton("VIP");
        rdbtnVip.setBounds(360, 60, 80, 23);
        contentPane.add(rdbtnVip);

        clientTypeGroup.add(rdbtnNormal);
        clientTypeGroup.add(rdbtnElder);
        clientTypeGroup.add(rdbtnPcd);
        clientTypeGroup.add(rdbtnVip);

        btnRegisterNormal = new JButton("Register Client");
        btnRegisterNormal.setForeground(new Color(240, 255, 255));
        btnRegisterNormal.setBackground(new Color(0, 206, 209));
        btnRegisterNormal.setBounds(120, 100, 200, 23);
        contentPane.add(btnRegisterNormal);

        btnRegisterAnonymous = new JButton("Register Anonymous");
        btnRegisterAnonymous.setForeground(new Color(240, 248, 255));
        btnRegisterAnonymous.setBackground(new Color(128, 128, 128));
        btnRegisterAnonymous.setBounds(120, 130, 200, 23);
        contentPane.add(btnRegisterAnonymous);
    }

    public String getClientName() {
        return textFieldName.getText();
    }

    public String getSelectedClientType() {
        if (rdbtnElder.isSelected()) return "ELDER";
        if (rdbtnPcd.isSelected()) return "PCD";
        if (rdbtnVip.isSelected()) return "VIP";
        return "NORMAL";
    }

    public void addRegisterNormalListener(ActionListener listener) {
        btnRegisterNormal.addActionListener(listener);
    }

    public void addRegisterAnonymousListener(ActionListener listener) {
        btnRegisterAnonymous.addActionListener(listener);
    }

    public void clearFields() {
        textFieldName.setText("");
        rdbtnNormal.setSelected(true);
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