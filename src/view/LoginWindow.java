/*
 * ID:
 * Tali Ratner 315489856
 * Tom Sapir 206540007
 */

package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import control.Hospital;
import exceptions.WrongLoginException;
import model.*;

public class LoginWindow extends JFrame {

	//Fields
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;
    protected int userID;
    private Font buttonFont;

    private Hospital hosp;
    private final Map<String, String> USERS = new HashMap<>();
    private final Map<String, String> ROLES = new HashMap<>();

    //MAIN Method
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginWindow frame = new LoginWindow();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //Constructor
    public LoginWindow() {
    	
        File f = new File("Hospital.ser");//Check for existing file
        if (f.exists()) {
            hosp = deserialize();
            
        } else {
            JOptionPane.showMessageDialog(null, "File does not exist! Creating an empty file instead", "Error!", JOptionPane.ERROR_MESSAGE);
            hosp = Hospital.getInstance();
            serialize(hosp);
        }
        
        USERS.put("admin", "admin");//Usernames and Passwords creation
        ROLES.put("admin", "ADMIN");
        for (StaffMember i : hosp.getStaffMembers().values()) {
            if (i instanceof Doctor) {
                USERS.put("doctor" + i.getId(), "doctor" + i.getId());
                ROLES.put("doctor" + i.getId(), "Doctor");
            }

            if (i instanceof Nurse) {
                USERS.put("nurse" + i.getId(), "nurse" + i.getId());
                ROLES.put("nurse" + i.getId(), "Nurse");
            }
        }

        setTitle("Hanamal Hospital Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 400);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        Font titleFont = new Font("Segoe UI", Font.BOLD, 24);
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 16);
        buttonFont = new Font("Segoe UI", Font.BOLD, 14);

        JLabel lblNewLabel = new JLabel("Sign in to Hanamal");
        lblNewLabel.setFont(titleFont);

        JLabel lblNewLabel_1 = new JLabel("Username:");
        lblNewLabel_1.setFont(labelFont);

        JLabel lblNewLabel_1_1 = new JLabel("Password:");
        lblNewLabel_1_1.setFont(labelFont);

        textField = new JTextField();
        styleTextField(textField);

        passwordField = new JPasswordField();
        styleTextField(passwordField);

        //Check for correct Login after pressing ENTER
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        authenticateUser();
                    } catch (WrongLoginException ex) {
                        JOptionPane.showMessageDialog(LoginWindow.this, ex.getMessage(), "Login Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        authenticateUser();
                    } catch (WrongLoginException ex) {
                        JOptionPane.showMessageDialog(LoginWindow.this, ex.getMessage(), "Login Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        //Check for correct login after pressing "Sing In" button
        JButton btnSignIn = styleButton(new JButton("Sign in"), new Color(255, 255, 255), Color.WHITE);
        btnSignIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSignIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    authenticateUser();
                } catch (WrongLoginException ex) {
                    JOptionPane.showMessageDialog(LoginWindow.this, ex.getMessage(), "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        //Close the page after pressing "Cancel" button
        JButton btnCancel = styleButton(new JButton("Cancel"), new Color(255, 255, 255), Color.WHITE);
        btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });

        addHoverEffect(btnSignIn);
        addHoverEffect(btnCancel);

        //Video that runs in the background of the Login Window
        Image img2 = new ImageIcon(this.getClass().getResource("/resources/hospital.gif")).getImage();
        JLabel background = new JLabel(new ImageIcon(img2));
        setContentPane(background);
        background.setLayout(null);

        background.add(lblNewLabel);
        background.add(lblNewLabel_1);
        background.add(lblNewLabel_1_1);
        background.add(textField);
        background.add(passwordField);
        background.add(btnSignIn);
        background.add(btnCancel);

        lblNewLabel.setBounds(40, 10, 300, 47);
        lblNewLabel_1.setBounds(15, 60, 100, 20); 
        lblNewLabel_1_1.setBounds(15, 120, 100, 20);

        textField.setBounds(15, 80, 270, 35);
        passwordField.setBounds(15, 140, 270, 35);

        btnSignIn.setBounds(25, 200, 120, 40); 
        btnCancel.setBounds(160, 200, 120, 40);
    }

    //check if the given username and password are correct, and opens the window accordingly
    private void authenticateUser() {
        String username = textField.getText();
        String password = new String(passwordField.getPassword());

        if (USERS.containsKey(username) && USERS.get(username).equals(password)) {
            dispose();
            openMainWindow(username);
        } else {
            textField.setText("");
            passwordField.setText("");
            throw new WrongLoginException(); 
        }
    }

    //Select the window to be opened after entering the right username/password
    private void openMainWindow(String username) {
        String role = ROLES.getOrDefault(username, "User");

        BasicWindow mainWindow;
        switch (role) {
            case "ADMIN":
                mainWindow = new AdminWindow(0);
                break;
            case "Doctor":
                userID = Integer.parseInt(username.replaceAll("[^0-9]", ""));
                mainWindow = new DoctorWindow(userID);
                break;
            case "Nurse":
                userID = Integer.parseInt(username.replaceAll("[^0-9]", ""));
                mainWindow = new NurseWindow(userID);
                break;
            default:
                throw new IllegalArgumentException("Invalid user role");
        }

        mainWindow.setHelloMessage(role);
        mainWindow.setVisible(true);
    }

    //Customize text fields
    private void styleTextField(JTextField textField) {
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(204, 204, 204)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
    }
    
    //Customize buttons
    private JButton styleButton(JButton button, Color bgColor, Color fgColor) {
        button.setFont(buttonFont);
        button.setForeground(Color.BLACK);
        button.setBackground(bgColor);
        button.setBorder(BorderFactory.createLineBorder(new Color(76, 182, 183), 2));
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    //Customize buttons after hovering them
    private void addHoverEffect(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(76, 182, 183));
                button.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.WHITE); 
                button.setForeground(Color.BLACK);
            }
        });
    }

    //Helper methods: Serialize and Deserialize Files
    public static void serialize(Hospital hospital1)
	{
		 try {
	            FileOutputStream fileOut = new FileOutputStream("Hospital.ser");
	            ObjectOutputStream out = new ObjectOutputStream(fileOut);
	            out.writeObject(hospital1);
	            out.close();
	            fileOut.close();
	        } catch (IOException i) {
	            i.printStackTrace();
	        }
	}
	
	public static Hospital deserialize()
	{
		Hospital hospital1 = null;
		try {
            FileInputStream fileIn = new FileInputStream("Hospital.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            hospital1 = (Hospital) in.readObject();
            in.close();
            fileIn.close();
            return hospital1;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("hospital class not found");
            c.printStackTrace();
            return null;
        }
	}
}
