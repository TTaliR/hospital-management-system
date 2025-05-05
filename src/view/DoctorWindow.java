package view;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DoctorWindow extends BasicWindow {
	//Fields
    private static final long serialVersionUID = 1L;
    private Font buttonFont;

    //Constructor
    public DoctorWindow(int userID) {
        super(userID);
    }

    @Override
    protected void setupSpecificUI() {
        int yPosition = 240;
        int buttonHeight = 29;
        int gap = 5;
        
        buttonFont = new Font("Segoe UI", Font.BOLD, 14);
        sidebar.add(myDetailsButton);
        myDetailsButton.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e)
        	{
        		cardLayout.show(CardView,"doctorDetailsFrame");
        		doctorDetailsFrame.add(saveDetails);
        	}
		});


        //Departments button
        JButton btnDepartments = styleButton(new JButton("Departments"), new Color(255, 255, 255), Color.WHITE);
        btnDepartments.setBounds(1, yPosition, 212, buttonHeight);
        sidebar.add(btnDepartments);
        btnDepartments.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {//Show the Departments table
                updateTable(writeDepartment(), departmentSP);
                departmentsFrame.revalidate();
                departmentsFrame.repaint();
                lastTableOpened = departmentsFrame;
                showFrame("departmentsFrame");
            }
        });
        addHoverEffect(btnDepartments);

        yPosition += buttonHeight + gap;
        
        //Medical Problems button
        JButton btnMedicalProblems = styleButton(new JButton("Medical Problems"), new Color(255, 255, 255), Color.WHITE);
        btnMedicalProblems.setBounds(1, yPosition, 212, buttonHeight);
        sidebar.add(btnMedicalProblems);
        btnMedicalProblems.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {//Show the Medical Problems table
                chooseTable.setSelectedItem("Injuries");
                updateTable(writeInjury(), injuriesSP);
                medicalProblemsFrame.revalidate();
                medicalProblemsFrame.repaint();
                lastTableOpened = medicalProblemsFrame;
                showFrame("medicalProblemsFrame");
                medicalProblemsFrame.add(btnAddTreatmentToMedProblem);
                medicalProblemsFrame.add(addButton);
                medicalProblemsFrame.add(viewTreatments);
                for (ActionListener al : addButton.getActionListeners()) {
                    addButton.removeActionListener(al);
                }
                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        lastAddPressed = medicalProblemsAddFrame;
                        showFrame("medicalProblemsAddFrame");
                        medicalProblemsAddFrame.add(backButton); 
                        medicalProblemsAddFrame.add(saveButton);
                        }
                });
                addButton.setBounds(260, 25, 120, 23);
                btnAddTreatmentToMedProblem.setBounds(411, 25, 150, 23);
                viewTreatments.setBounds(590, 25, 170, 23);
            }
        });
        addHoverEffect(btnMedicalProblems);

        yPosition += buttonHeight + gap;

        //Patients button
        JButton btnPatients = styleButton(new JButton("Patients"), new Color(255, 255, 255), Color.WHITE);
        btnPatients.setBounds(1, yPosition, 212, buttonHeight);
        sidebar.add(btnPatients);
        btnPatients.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {//Show the Patients table
                updateTable(writePatient(), patientSP);
                patientsFrame.revalidate();
                patientsFrame.repaint();
                lastTableOpened = patientsFrame;
                showFrame("patientsFrame");
                patientsFrame.add(viewVisits);
                viewVisits.setBounds(420, 25, 120, 23);
            }
        });
        addHoverEffect(btnPatients);

        yPosition += buttonHeight + gap;

        //Visits button
        JButton btnVisits = styleButton(new JButton("Visits"), new Color(255, 255, 255), Color.WHITE);
        btnVisits.setBounds(1, yPosition, 212, buttonHeight);
        sidebar.add(btnVisits);
        btnVisits.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {//Show the Visits table
                updateTable(writeVisit(), visitSP);
                visitsFrame.revalidate();
                visitsFrame.repaint();
                lastTableOpened = visitsFrame;
                showFrame("visitsFrame");
                visitsFrame.add(btnAddMedProblemToVisit);
                visitsFrame.add(btnAddTreatmentToVisit);
                visitsFrame.add(viewMedicalProblems);
                visitsFrame.add(viewTreatments);
                
                btnAddMedProblemToVisit.setBounds(60, 25, 200, 23);
                btnAddTreatmentToVisit.setBounds(295, 25, 150, 23);
                viewMedicalProblems.setBounds(482, 25, 210, 23);
                viewTreatments.setBounds(730, 25, 160, 23);

            }
        });
        addHoverEffect(btnVisits);

        yPosition += buttonHeight + gap;

        //Treatments button
        JButton btnTreatments = styleButton(new JButton("Treatments"), new Color(255, 255, 255), Color.WHITE);
        btnTreatments.setBounds(1, yPosition, 212, buttonHeight);
        sidebar.add(btnTreatments);
        btnTreatments.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {//Show the Treatments table
                updateTable(writeTreatment(), treatmentSP);
                treatmentsFrame.revalidate();
                treatmentsFrame.repaint();
                lastTableOpened = treatmentsFrame;
                showFrame("treatmentsFrame");
                treatmentsFrame.add(btnAddMedicationToTreatment);
                treatmentsFrame.add(addButton);
                treatmentsFrame.add(viewMedications);
                treatmentsFrame.add(viewMedicalProblems);
                for (ActionListener al : addButton.getActionListeners()) {
                    addButton.removeActionListener(al);
                }
                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        lastAddPressed = treatmentsAddFrame;
                        showFrame("treatmentsAddFrame");
                        treatmentsAddFrame.add(backButton); 
                        treatmentsAddFrame.add(saveButton);
                        }
                });
                addButton.setBounds(80, 25, 110, 23);
                btnAddMedicationToTreatment.setBounds(230, 25, 180, 23);
                viewMedications.setBounds(452, 25, 210, 23);
                viewMedicalProblems.setBounds(700, 25, 210, 23);
            }
        });
        addHoverEffect(btnTreatments);

        yPosition += buttonHeight + gap;

        //Medications button
        JButton btnMedications = styleButton(new JButton("Medications"), new Color(255, 255, 255), Color.WHITE);
        btnMedications.setBounds(1, yPosition, 212, buttonHeight);
        sidebar.add(btnMedications);
        btnMedications.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {//Show the Medications table
                updateTable(writeMedication(), medicationSP);
                medicationsFrame.revalidate();
                medicationsFrame.repaint();
                lastTableOpened = medicationsFrame;
                showFrame("medicationsFrame");
                medicationsFrame.add(addButton);
                for (ActionListener al : addButton.getActionListeners()) {
                    addButton.removeActionListener(al);
                }

                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        lastAddPressed = medicationsAddFrame;
                        showFrame("medicationsAddFrame");
                        medicationsAddFrame.add(backButton); 
                        medicationsAddFrame.add(saveButton);
                        }
                });
                addButton.setBounds(310, 25, 120, 23);

                
                
            }
        });
        addHoverEffect(btnMedications);

        yPosition += buttonHeight + gap;

        //More Options button
        JButton btnMoreOptions = styleButton(new JButton("More Options..."), new Color(255, 255, 255), Color.WHITE);
        btnMoreOptions.setBounds(1, yPosition, 212, buttonHeight);
        sidebar.add(btnMoreOptions);
        btnMoreOptions.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {//Show More Options
                cardLayout.show(CardView, "queriesFrame");
                countDocsSpecButton.setVisible(false);
	            newDepManagerButton.setVisible(false);
	            
	            howManyVisitBeforeButton.setBounds(155, 200, 287, 100);
	            countMedicationsButton.setBounds(555, 200, 287, 100);
	            staffAndComplianceButton.setBounds(350,380,287,100);
            }
        });
        addHoverEffect(btnMoreOptions);

        yPosition += buttonHeight + gap;

        //Main Menu button
        JButton btnMainMenu = styleButton(new JButton("Back to main menu"), new Color(255, 255, 255), Color.WHITE);
        btnMainMenu.setBounds(1, yPosition, 212, buttonHeight);
        sidebar.add(btnMainMenu);
        btnMainMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {//Show Main Menu
                cardLayout.show(CardView, "MainFrame");
            }
        });
        addHoverEffect(btnMainMenu);
    }

    //Customize buttons
    private JButton styleButton(JButton button, Color bgColor, Color fgColor) {
        button.setFont(buttonFont);
        button.setForeground(Color.BLACK);
        button.setBackground(bgColor);

        
        Color borderColor = new Color(255, 114, 94, 255);
        Border line = BorderFactory.createLineBorder(borderColor);
        Border margin = BorderFactory.createEmptyBorder(5, 15, 5, 15);
        Border compound = BorderFactory.createCompoundBorder(line, margin);
        Border shadow = BorderFactory.createMatteBorder(0, 4, 0, 0, new Color(200, 200, 200, 150));
        Border finalBorder = BorderFactory.createCompoundBorder(shadow, compound);

        button.setBorder(finalBorder);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    //Customize buttons after hovering them
    private void addHoverEffect(JButton button) {
        Color originalBgColor = button.getBackground();
        Color borderColor = new Color(255, 114, 94, 255);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(borderColor);
                button.setForeground(Color.WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(originalBgColor);
                button.setForeground(Color.BLACK);
            }
        });
    }
}
