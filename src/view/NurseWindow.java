/*
 * ID:
 * Tali Ratner 315489856
 * Tom Sapir 206540007
 */

package view;



import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NurseWindow extends BasicWindow {
	
	//Fields
    private static final long serialVersionUID = 1L;
    private Font buttonFont;

    //Constructor
	public NurseWindow(int userID) {
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
        		cardLayout.show(CardView,"nurseDetailsFrame");
        		nurseDetailsFrame.add(saveDetails);
        	}
		});

        //My Details button
        myDetailsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {//Show My Details
                cardLayout.show(CardView, "nurseDetailsFrame");
                nurseDetailsFrame.add(saveDetails);
            }
        });
        
        //Departments button
        JButton btnDepartments = styleButton(new JButton("Departments"), new Color(255, 255, 255), Color.WHITE);
        btnDepartments.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnDepartments.setHorizontalAlignment(SwingConstants.CENTER);
        btnDepartments.setBounds(1, yPosition, 212, buttonHeight);
        sidebar.add(btnDepartments);
        btnDepartments.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {//Show the Departments table
            	updateTable(writeDepartment(),departmentSP);
	        	departmentsFrame.revalidate();
	        	departmentsFrame.repaint();
	        	lastTableOpened = departmentsFrame;
	            showFrame("departmentsFrame");
            }
        });

        yPosition += buttonHeight + gap;
        
        //Medical Problems button
        JButton btnMedicalProblems = styleButton(new JButton("Medical Problems"), new Color(255, 255, 255), Color.WHITE);
        btnMedicalProblems.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnMedicalProblems.setHorizontalAlignment(SwingConstants.CENTER);
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
                medicalProblemsFrame.add(viewTreatments);
                
                viewTreatments.setBounds(420, 25, 180, 23);;
            }
        });

        yPosition += buttonHeight + gap;
        
        //Patients button
        JButton btnPatients = styleButton(new JButton("Patients"), new Color(255, 255, 255), Color.WHITE);
        btnPatients.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnPatients.setHorizontalAlignment(SwingConstants.CENTER);
        btnPatients.setBounds(1, yPosition, 212, buttonHeight);
        sidebar.add(btnPatients);
        btnPatients.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {//Show the Patients table
            	updateTable(writePatient(),patientSP);
				patientsFrame.revalidate();
				patientsFrame.repaint();
				lastTableOpened = patientsFrame;
	            showFrame("patientsFrame");
				patientsFrame.add(addButton);
				patientsFrame.add(viewVisits);
				
		        addButton.setBounds(330, 25, 120, 23);
		        viewVisits.setBounds(480, 25, 120, 23);
		        
		        for (ActionListener al : addButton.getActionListeners()) {
	            	addButton.removeActionListener(al);
                }
				
		        addButton.addActionListener(new ActionListener() {//Add a new Patient button
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                	lastAddPressed = patientsAddFrame;
	    	            showFrame("patientsAddFrame");
	                    patientsAddFrame.add(backButton); 
	                    patientsAddFrame.add(saveButton);	                }
	            });

            }
        });

        yPosition += buttonHeight + gap;

        //Visits button
        JButton btnVisits = styleButton(new JButton("Visits"), new Color(255, 255, 255), Color.WHITE);
        btnVisits.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnVisits.setHorizontalAlignment(SwingConstants.CENTER);
        btnVisits.setBounds(1, yPosition, 212, buttonHeight);
        sidebar.add(btnVisits);
        btnVisits.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {//Show the Visits table
            	updateTable(writeVisit(),visitSP);
            	visitsFrame.revalidate();
            	visitsFrame.repaint();
            	lastTableOpened = visitsFrame;
	            showFrame("visitsFrame");
				visitsFrame.add(addButton);
				visitsFrame.add(viewMedicalProblems);
		        visitsFrame.add(viewTreatments);
			
				
				addButton.setBounds(190, 30, 120, 23);
				viewMedicalProblems.setBounds(340, 30, 210, 23);
				viewTreatments.setBounds(580, 30, 190, 23);
				
				for (ActionListener al : addButton.getActionListeners()) {
	            	addButton.removeActionListener(al);
                }
				
				 addButton.addActionListener(new ActionListener() {//Add a new Visit button
		                @Override
		                public void actionPerformed(ActionEvent e) {
	                	lastAddPressed = visitsAddFrame;
	    	            showFrame("visitsAddFrame");
	                    visitsAddFrame.add(backButton); 
	                    visitsAddFrame.add(saveButton); 
	                    }
	            });
            }
        });

        yPosition += buttonHeight + gap;

        //Treatments button
        JButton btnTreatments = styleButton(new JButton("Treatments"), new Color(255, 255, 255), Color.WHITE);
        btnTreatments.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnTreatments.setHorizontalAlignment(SwingConstants.CENTER);
        btnTreatments.setBounds(1, yPosition, 212, buttonHeight);
        sidebar.add(btnTreatments);
        btnTreatments.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {//Show the Treatments table
            	updateTable(writeTreatment(),treatmentSP);
	  			treatmentsFrame.revalidate();
	  			treatmentsFrame.repaint();
	  			lastTableOpened = treatmentsFrame;
	            showFrame("treatmentsFrame");
                treatmentsFrame.add(viewMedications);
			    treatmentsFrame.add(viewMedicalProblems);
			    
			    viewMedications.setBounds(300, 30, 180, 23);
			    viewMedicalProblems.setBounds(510, 30, 210, 23);
            }
        });

        yPosition += buttonHeight + gap;
        
        //Medications button
        JButton btnMedications = styleButton(new JButton("Medications"), new Color(255, 255, 255), Color.WHITE);
        btnMedications.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnMedications.setHorizontalAlignment(SwingConstants.CENTER);
        btnMedications.setBounds(1, yPosition, 212, buttonHeight);
        sidebar.add(btnMedications);
        btnMedications.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {//Show the Medications table
            	updateTable(writeMedication(),medicationSP);
	  			medicationsFrame.revalidate();
	  			medicationsFrame.repaint();
	  			lastTableOpened = medicationsFrame;
	            showFrame("medicationsFrame");
            }
        });
        
        yPosition += buttonHeight + gap;
        
        //More Optins button
        JButton btnMoreOptions = styleButton(new JButton("More Options..."), new Color(255, 255, 255), Color.WHITE);
		btnMoreOptions.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMoreOptions.setHorizontalAlignment(SwingConstants.CENTER);
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
        
        yPosition += buttonHeight + gap;

        //Main Menu button
        JButton btnMainMenu = styleButton(new JButton("Back to main menu"), new Color(255, 255, 255), Color.WHITE);
		btnMainMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
		btnMainMenu.setBounds(1, yPosition, 212, buttonHeight);
		sidebar.add(btnMainMenu);
		btnMainMenu.addMouseListener(new MouseAdapter() {//Show Main Menu
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(CardView, "MainFrame");
            }
        });
		
		addHoverEffect(btnDepartments);
		addHoverEffect(btnMedicalProblems);
		addHoverEffect(btnPatients);
		addHoverEffect(btnVisits);
		addHoverEffect(btnTreatments);
		addHoverEffect(btnMedications);
		addHoverEffect(btnMoreOptions);
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

