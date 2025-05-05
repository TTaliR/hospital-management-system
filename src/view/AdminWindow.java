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

public class AdminWindow extends BasicWindow {
	//Fields
	private static final long serialVersionUID = 1L;
    private Font buttonFont;

    //Constructor
	public AdminWindow(int userID) {
		super(userID);
	}
	

	@Override
	protected void setupSpecificUI() {

		int yPosition = 240;
		int buttonHeight = 29;
		int gap = 5;

		
		
		frameHistory.push("MainFrame");
		
        buttonFont = new Font("Segoe UI", Font.BOLD, 14);
        
        //Navigation Bar Buttons
		
        //Departments Button
		JButton btnDepartments = styleButton(new JButton("Departments"), new Color(255, 255, 255), Color.WHITE);
	    btnDepartments.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    btnDepartments.setHorizontalAlignment(SwingConstants.CENTER);
	    btnDepartments.setBounds(1, yPosition, 212, buttonHeight);
	    sidebar.add(btnDepartments);
	    btnDepartments.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {//Show Departments table
	        	updateTable(writeDepartment(),departmentSP);
	        	departmentsFrame.revalidate();
	        	departmentsFrame.repaint();
	        	lastTableOpened = departmentsFrame;
	            showFrame("departmentsFrame");
	            departmentsFrame.add(addButton);
	            departmentsFrame.add(removeButton);
	            departmentsFrame.add(editButton);
	            departmentsFrame.add(viewStaff);
	            departmentsFrame.add(btnAddStaffMemberToDep);
	            
  
	            addButton.setBounds(100, 25, 110, 23);
	            removeButton.setBounds(245, 25, 110, 23);
	            editButton.setBounds(390, 25, 110, 23);
	            btnAddStaffMemberToDep.setBounds(530, 25, 180, 23);
	            viewStaff.setBounds(740, 25, 150, 23);
	            
	            for (ActionListener al : addButton.getActionListeners()) {
	            	addButton.removeActionListener(al);
                }
	            
	            addButton.addActionListener(new ActionListener() {//Add a new Department Button
	                @Override
	                public void actionPerformed(ActionEvent e)
	                {
	                	lastAddPressed = departmentsAddFrame;
	    	            showFrame("departmentsAddFrame");
	                    departmentsAddFrame.add(backButton); 
	                    departmentsAddFrame.add(saveButton);
	                    
	                }
	            });
	        }
	    });
		
			
		yPosition += buttonHeight + gap;

		//Medical Problems Button
		JButton btnMedicalProblems = styleButton(new JButton("Medical Problems"), new Color(255, 255, 255), Color.WHITE);
		btnMedicalProblems.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMedicalProblems.setHorizontalAlignment(SwingConstants.CENTER);
		btnMedicalProblems.setBounds(1, yPosition, 212, buttonHeight);
		sidebar.add(btnMedicalProblems);
		btnMedicalProblems.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {//Show Medical Problems Table
		        chooseTable.setSelectedItem("Injuries");
				updateTable(writeInjury(), injuriesSP);
				lastTableOpened = medicalProblemsFrame;
				medicalProblemsFrame.revalidate();
				medicalProblemsFrame.repaint();
	            showFrame("medicalProblemsFrame");
				medicalProblemsFrame.add(addButton);	
				medicalProblemsFrame.add(removeButton);
				medicalProblemsFrame.add(editButton);
				medicalProblemsFrame.add(chooseTable);
				medicalProblemsFrame.add(btnAddTreatmentToMedProblem);
				medicalProblemsFrame.add(viewTreatments);
				
				addButton.setBounds(190, 25, 110, 23);
				removeButton.setBounds(322, 25, 110, 23);
				editButton.setBounds(454, 25, 110, 23);
				btnAddTreatmentToMedProblem.setBounds(585, 25, 180, 23);
				viewTreatments.setBounds(785, 25, 180, 23);
				
				for (ActionListener al : addButton.getActionListeners()) {
	            	addButton.removeActionListener(al);
                }
				
				 addButton.addActionListener(new ActionListener() {//Add a new Medical Problem button
		                @Override
		                public void actionPerformed(ActionEvent e) {
	                	lastAddPressed = medicalProblemsAddFrame;
	    	            showFrame("medicalProblemsAddFrame");
	                    medicalProblemsAddFrame.add(backButton); 
	                    medicalProblemsAddFrame.add(saveButton);
	                    }
	            });
				
			}
		});

		yPosition += buttonHeight + gap;

		//Patients Button
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
				patientsFrame.add(removeButton);
				patientsFrame.add(editButton);
				patientsFrame.add(viewVisits);
				
				addButton.setBounds(200, 25, 110, 23);
				removeButton.setBounds(345, 25, 110, 23);
				editButton.setBounds(490, 25, 110, 23);
				viewVisits.setBounds(633, 25, 150, 23);
				
				for (ActionListener al : addButton.getActionListeners()) {
	            	addButton.removeActionListener(al);
                }
				
				 addButton.addActionListener(new ActionListener() {//Add a new Patient button
		                @Override
		                public void actionPerformed(ActionEvent e) {
	                	lastAddPressed = patientsAddFrame;
	    	            showFrame("patientsAddFrame");
	                    patientsAddFrame.add(backButton); 
	                    patientsAddFrame.add(saveButton);
	                    }
	            });

			}
		});

		yPosition += buttonHeight + gap;
		
		//Vists Button
		JButton btnVisits = styleButton(new JButton("Visits"), new Color(255, 255, 255), Color.WHITE);
		btnVisits.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVisits.setHorizontalAlignment(SwingConstants.CENTER);
		btnVisits.setBounds(1, yPosition, 212, buttonHeight);
		sidebar.add(btnVisits);
		btnVisits.addMouseListener(new MouseAdapter() {//Show the Visits table
	            @Override
	            public void mouseClicked(MouseEvent e) {
	            	updateTable(writeVisit(),visitSP);
	            	visitsFrame.revalidate();
	            	visitsFrame.repaint();
	            	lastTableOpened = visitsFrame;
		            showFrame("visitsFrame");
	                visitsFrame.add(addButton);
	                visitsFrame.add(removeButton);
	                visitsFrame.add(editButton);
			        visitsFrame.add(btnAddMedProblemToVisit);
			        visitsFrame.add(btnAddTreatmentToVisit);
			        visitsFrame.add(viewMedicalProblems);
			        visitsFrame.add(viewTreatments);
					
			        addButton.setBounds(90, 10, 120, 23);
			        removeButton.setBounds(230, 10, 120, 23);
			        editButton.setBounds(370, 10, 120, 23);
			        btnAddMedProblemToVisit.setBounds(510, 10, 200, 23);
			        btnAddTreatmentToVisit.setBounds(730, 10, 150, 23);

			        viewMedicalProblems.setBounds(255, 45, 210, 23);
			        viewTreatments.setBounds(485, 45, 180, 23);


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
		
		//Treatments Button
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
                treatmentsFrame.add(addButton);
                treatmentsFrame.add(removeButton);
                treatmentsFrame.add(editButton);
		        treatmentsFrame.add(btnAddMedicationToTreatment);
		        treatmentsFrame.add(viewMedications);
		        treatmentsFrame.add(viewDoctors);
		        treatmentsFrame.add(viewMedicalProblems);
		        treatmentsFrame.add(btnAddMedProblemToTreatment);
				
		        addButton.setBounds(70, 10, 120, 23);
		        removeButton.setBounds(210, 10, 120, 23);
		        editButton.setBounds(350, 10, 120, 23);
		        btnAddMedicationToTreatment.setBounds(490, 10, 180, 23);
		        viewMedications.setBounds(690, 10, 180, 23);

		        btnAddMedProblemToTreatment.setBounds(140, 45, 200, 23);
		        viewMedicalProblems.setBounds(360, 45, 210, 23);
		        viewDoctors.setBounds(590, 45, 180, 23);
		        
		        for (ActionListener al : addButton.getActionListeners()) {
	            	addButton.removeActionListener(al);
                }
		        
		        addButton.addActionListener(new ActionListener() {//Add a new Treatment button
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                	lastAddPressed = treatmentsAddFrame;
	    	            showFrame("treatmentsAddFrame");
	                    treatmentsAddFrame.add(backButton); 
	                    treatmentsAddFrame.add(saveButton);	                }
	            });
				
            }
        });
		

		yPosition += buttonHeight + gap;
		
		//Medication button
		JButton btnMedications = styleButton(new JButton("Medications"), new Color(255, 255, 255), Color.WHITE);
		btnMedications.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMedications.setHorizontalAlignment(SwingConstants.CENTER);
		btnMedications.setBounds(1, yPosition, 212, buttonHeight);
		sidebar.add(btnMedications);
		btnMedications.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {//Show the Medication table
            	updateTable(writeMedication(),medicationSP);
	  			medicationsFrame.revalidate();
	  			medicationsFrame.repaint();
	  			lastTableOpened = medicationsFrame;
	            showFrame("medicationsFrame");
                medicationsFrame.add(addButton);
                medicationsFrame.add(removeButton);
                medicationsFrame.add(editButton);
                
                addButton.setBounds(160, 25, 110, 23);
                removeButton.setBounds(300, 25, 110, 23);
	            editButton.setBounds(440, 25, 110, 23);
				
				for (ActionListener al : addButton.getActionListeners()) {
	            	addButton.removeActionListener(al);
                }
				
				 addButton.addActionListener(new ActionListener() {//Add a new Medication button
		                @Override
		                public void actionPerformed(ActionEvent e) {
	                	lastAddPressed = medicationsAddFrame;
	    	            showFrame("medicationsAddFrame");
	                    medicationsAddFrame.add(backButton); 
	                    medicationsAddFrame.add(saveButton);	                }
	            });
            }
        });

		yPosition += buttonHeight + gap;

		//Doctors button
		JButton btnDoctors = styleButton(new JButton("Doctors"), new Color(255, 255, 255), Color.WHITE);
		btnDoctors.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDoctors.setHorizontalAlignment(SwingConstants.CENTER);
		btnDoctors.setBounds(1, yPosition, 212, buttonHeight);
		sidebar.add(btnDoctors);
		btnDoctors.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {//Show the Doctors table
            	updateTable(writeDoctor(),doctorSP);
            	doctorsFrame.revalidate();
            	doctorsFrame.repaint();
            	lastTableOpened = doctorsFrame;
	            showFrame("doctorsFrame");
                doctorsFrame.add(addButton);
                doctorsFrame.add(removeButton);
                doctorsFrame.add(editButton);
                doctorsFrame.add(viewDepartments);

                addButton.setBounds(160, 25, 110, 23);
	            removeButton.setBounds(300, 25, 110, 23);
	            editButton.setBounds(440, 25, 110, 23);
	            viewDepartments.setBounds(578, 25, 180, 23);
				
	            for (ActionListener al : addButton.getActionListeners()) {
	            	addButton.removeActionListener(al);
                }
	            
	            addButton.addActionListener(new ActionListener() {//Add a new Doctor button
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                	lastAddPressed = doctorsAddFrame;
	    	            showFrame("doctorsAddFrame");
	                    doctorsAddFrame.add(backButton); 
	                    doctorsAddFrame.add(saveButton);	                }
	            });
            }
        });

		yPosition += buttonHeight + gap;
		
		//Nurses button
		JButton btnNurses = styleButton(new JButton("Nurses"), new Color(255, 255, 255), Color.WHITE);
		btnNurses.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNurses.setHorizontalAlignment(SwingConstants.CENTER);
		btnNurses.setBounds(1, yPosition, 212, buttonHeight);
		sidebar.add(btnNurses);
		btnNurses.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {//Show the Nurses table
            	updateTable(writeNurse(),nurseSP);
            	nursesFrame.revalidate();
            	nursesFrame.repaint();
            	lastTableOpened = nursesFrame;
	            showFrame("nursesFrame");
                nursesFrame.add(addButton);
                nursesFrame.add(removeButton);
                nursesFrame.add(editButton);
                nursesFrame.add(viewDepartments);

                addButton.setBounds(160, 25, 110, 23);
	            removeButton.setBounds(300, 25, 110, 23);
	            editButton.setBounds(440, 25, 110, 23);
	            viewDepartments.setBounds(578, 25, 180, 23);
				
	            for (ActionListener al : addButton.getActionListeners()) {
	            	addButton.removeActionListener(al);
                }
	            
	            addButton.addActionListener(new ActionListener() {//Add a new Nurse button
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                	lastAddPressed = nursesAddFrame;
	    	            showFrame("nursesAddFrame");
	                    nursesAddFrame.add(backButton); 
	                    nursesAddFrame.add(saveButton);	                }
	            });
            }
        });

		yPosition += buttonHeight + gap;
		
		//More Options button
		JButton btnMoreOptions = styleButton(new JButton("More Options..."), new Color(255, 255, 255), Color.WHITE);
		btnMoreOptions.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMoreOptions.setHorizontalAlignment(SwingConstants.CENTER);
		btnMoreOptions.setBounds(1, yPosition, 212, buttonHeight);
		sidebar.add(btnMoreOptions);
		btnMoreOptions.addMouseListener(new MouseAdapter() {
			@Override
	        public void mouseClicked(MouseEvent e) {//Show More Options
	            cardLayout.show(CardView, "queriesFrame");  
	        }
		});
		
		yPosition += buttonHeight + gap;

		//Main Menu button
		JButton btnMainMenu = styleButton(new JButton("Back to main menu"), new Color(255, 255, 255), Color.WHITE);
		btnMainMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
		btnMainMenu.setBounds(1, yPosition, 212, buttonHeight);
		sidebar.add(btnMainMenu);
		btnMainMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {//Show the Main Menu
                cardLayout.show(CardView, "MainFrame");
            }
        });

		addHoverEffect(btnDepartments);
		addHoverEffect(btnMedicalProblems);
		addHoverEffect(btnDoctors);
		addHoverEffect(btnNurses);
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

