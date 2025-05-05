/*
 * ID:
 * Tali Ratner 315489856
 * Tom Sapir 206540007
 */
package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import control.Hospital;
import enums.BiologicalSex;
import enums.HealthFund;
import enums.Specialization;
import exceptions.*;
import model.*;

import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import java.awt.Component;

public abstract class BasicWindow extends JFrame {

	// *********FIELDS************//
	
	private static final long serialVersionUID = 1L;
	protected Hospital hosp = Hospital.getInstance();
	protected JPanel contentPane;
	protected JPanel sidebar;
	protected CardLayout cardLayout;
	protected JPanel CardView;
	protected JLabel helloLabel;
	protected JTable table;
	protected int userID;
	protected Stack<String> frameHistory = new Stack<>();
	protected Font buttonFont;
	protected DefaultTableModel injuriesModel, diseasesModel, fracturesModel, addInjuryModel, addDiseaseModel,
	addFractureModel, patientsModel, visitsModel, medicationsModel, treatmentsModel, addDoctorModel,
	addNurseModel, departmentsModel;
	protected JComboBox<String> chooseTable;
	protected JComboBox<String> chooseTable2;
	protected JComboBox<String> chooseTable3;
	protected JComboBox<String> chooseTable4;
	protected JComboBox<Integer> depManagerSelect, medProblemDepSelect, visitPatientSelect;
	
	// buttons
	protected JButton addButton;
	protected JButton removeButton;
	protected JButton editButton;
	protected JButton viewTreatments;
	protected JButton viewMedicalProblems;
	protected JButton viewMedications;
	protected JButton viewVisits;
	protected JButton viewStaff;
	protected JButton viewDoctors;
	protected JButton viewDepartments;
	protected JButton backButton;
	protected JButton saveButton;
	protected JButton saveDetails;
	protected JButton saveAddToList;
	protected JButton myDetailsButton;
	protected JButton btnAddTreatmentToMedProblem;
	protected JButton btnAddMedProblemToTreatment;
	protected JButton btnAddMedProblemToVisit;
	protected JButton btnAddTreatmentToVisit;
	protected JButton btnAddMedicationToTreatment;
	protected JButton btnAddStaffMemberToDep;
	protected JButton newDepManagerButton;
	protected JButton howManyVisitBeforeButton;
	protected JButton countMedicationsButton;
	protected JButton countDocsSpecButton;
	protected JButton staffAndComplianceButton;
	private final ButtonGroup medicalProblemsRadio = new ButtonGroup();
	private final ButtonGroup biologicalSexRadio = new ButtonGroup();
	
	// frames
	protected JPanel departmentsFrame;
	protected JPanel medicalProblemsFrame;
	protected JPanel patientsFrame;
	protected JPanel visitsFrame;
	protected JPanel treatmentsFrame;
	protected JPanel medicationsFrame;
	protected JPanel doctorsFrame;
	protected JPanel nursesFrame;
	protected JPanel departmentsAddFrame;
	protected JPanel medicalProblemsAddFrame;
	protected JPanel patientsAddFrame;
	protected JPanel visitsAddFrame;
	protected JPanel treatmentsAddFrame;
	protected JPanel doctorsAddFrame;
	protected JPanel nursesAddFrame;
	protected JPanel medicationsAddFrame;
	protected JPanel addTreatmentToFrame;
	protected JPanel addMedicationToTreatmentFrame;
	protected JPanel addMedProblemToTreatmentFrame;
	protected JPanel addVisitToPatientFrame;
	protected JPanel addMedProblemToVisitFrame;
	protected JPanel addStaffMemberToDepartmentFrame;
	protected JPanel doctorDetailsFrame;
	protected JPanel nurseDetailsFrame;
	protected JPanel queriesFrame;
	protected JPanel lastAddPressed;
	protected JPanel lastTableOpened = null;
	protected JScrollPane departmentSP, injuriesSP, patientSP, visitSP, treatmentSP, doctorSP, nurseSP, medicationSP,
			diseasesSP, fracturesSP, addFractureSP, addInjurySP, addDiseaseSP, addMedicationToTreatmentSP,
			addTreatmentSP, addDoctorSP, addNurseSP, addInjurySPTreatments, addDiseaseSPTreatments, addFractureSPTreatments;
	
	//HashMaps
	protected HashMap<Integer, Doctor> doctorsHashMap;
	protected HashMap<Integer, Nurse> nursesHashMap;
	protected HashMap<String, Injury> injuriesHashMap;
	protected HashMap<String, Fracture> fracturesHashMap;
	protected HashMap<String, Disease> diseasesHashMap;
	
	protected MedicalProblem selectedProblem;
	

	

	public BasicWindow(int userID) {     
		setBackground(Color.WHITE);
	
		this.userID = userID;

		// Font initialization
		buttonFont = new Font("Segoe UI", Font.BOLD, 14);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 24);
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 16);	

        // Window initialization
		setTitle("Hanamal Hospital Data System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 680);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Right side card layout screen initialization
		CardView = new JPanel();
		CardView.setBackground(Color.WHITE);
		CardView.setBounds(222, 0, 1055, 680);
		contentPane.add(CardView);
		cardLayout = new CardLayout(0, 0);
		CardView.setLayout(cardLayout);
			
		
		// *********MAPS************//
		Hospital deserialized = deserialize();
		hosp.setDepartments(deserialized.getDepartments());
		hosp.setMedicalProblems(deserialized.getMedicalProblems());
		hosp.setStaffMembers(deserialized.getStaffMembers());
		hosp.setMedications(deserialized.getMedications());
		hosp.setPatients(deserialized.getPatients());
		hosp.setTreatments(deserialized.getTreatments());
		hosp.setVisits(deserialized.getVisits());
		doctorsHashMap = hosp.getStaffMembers().entrySet().stream().filter(entry -> entry.getValue() instanceof Doctor)
				.collect(Collectors.toMap(Map.Entry::getKey, entry -> (Doctor) entry.getValue(),
						(oldValue, newValue) -> oldValue, HashMap::new));
		nursesHashMap = hosp.getStaffMembers().entrySet().stream().filter(entry -> entry.getValue() instanceof Nurse)
				.collect(Collectors.toMap(Map.Entry::getKey, entry -> (Nurse) entry.getValue(),
						(oldValue, newValue) -> oldValue, HashMap::new));
		injuriesHashMap = hosp.getMedicalProblems().entrySet().stream()
				.filter(entry -> entry.getValue() instanceof Injury).collect(Collectors.toMap(Map.Entry::getKey,
						entry -> (Injury) entry.getValue(), (oldValue, newValue) -> oldValue, HashMap::new));
		fracturesHashMap = hosp.getMedicalProblems().entrySet().stream()
				.filter(entry -> entry.getValue() instanceof Fracture).collect(Collectors.toMap(Map.Entry::getKey,
						entry -> (Fracture) entry.getValue(), (oldValue, newValue) -> oldValue, HashMap::new));
		diseasesHashMap = hosp.getMedicalProblems().entrySet().stream()
				.filter(entry -> entry.getValue() instanceof Disease).collect(Collectors.toMap(Map.Entry::getKey,
						entry -> (Disease) entry.getValue(), (oldValue, newValue) -> oldValue, HashMap::new));
		int i = 0;// NOTE
		while (i < hosp.getMedicalProblems().size()) {
			new Disease(null, null, null);
			i++;
		}		
		

		// ****** BUTTONS *********//
		addButton = styleButton(new JButton("Add"), new Color(255, 255, 255), Color.WHITE);
		addButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		

		removeButton = styleButton(new JButton("Remove"), new Color(255, 255, 255), Color.WHITE);
		removeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		editButton = styleButton(new JButton("Edit"), new Color(255, 255, 255), Color.WHITE);
		editButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		backButton = styleButton(new JButton("Back"), new Color(255, 255, 255), Color.WHITE);
		backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				goBack();

			}
		});
		backButton.setBounds(20, 605, 89, 23);
		addHoverEffect(backButton);

		myDetailsButton = styleButton(new JButton("My Details"), new Color(255, 255, 255), Color.WHITE);
		myDetailsButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		myDetailsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		myDetailsButton.setBounds(53, 170, 116, 25);
		addHoverEffect(myDetailsButton);

		// Save button for Add Object frames
		saveButton = styleButton(new JButton("Save"), new Color(255, 255, 255), Color.WHITE);
		saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		saveButton.setBounds(881, 605, 89, 23);

		// Save button for Add Object To List frames
		saveAddToList = styleButton(new JButton("Save"), new Color(255, 255, 255), Color.WHITE);
		saveAddToList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		saveAddToList.setBounds(881, 605, 89, 23);

		// View Buttons
		viewTreatments = styleButton(new JButton("View Treatments"), new Color(255, 255, 255), Color.WHITE);
		viewTreatments.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		viewTreatments.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Get the selected row index from the table
		            int selectedRow = table.getSelectedRow();
		            if (selectedRow < 0) {
		                throw new NoCellSelectedException(); // Exception if no row is selected
		            }

		            Set<Treatment> treatments = new HashSet<>();
		            String objectType = "";

		            // Check the type of object based on the table's first column
		            if (table.getColumnName(0).equals("Number")) {
		                int visitId = (int) table.getValueAt(selectedRow, 0);
		                Visit selectedVisit = hosp.getRealVisit(visitId);
		                if (selectedVisit == null) {
		                    // Display an error message if the visit is not found
		                    JOptionPane.showMessageDialog(null, "Selected visit not found!", "Error!", JOptionPane.ERROR_MESSAGE);
		                    return;
		                }
		                treatments = selectedVisit.getTreatmentsList(); // Get the list of treatments for the visit
		                objectType = "visit";
		            } else if (table.getColumnName(0).equals("Code")) {
		                String code = (String) table.getValueAt(selectedRow, 0);
		                MedicalProblem selectedMedicalProblem = hosp.getMedicalProblem(code);
		                if (selectedMedicalProblem == null) {
		                    // Display an error message if the medical problem is not found
		                    JOptionPane.showMessageDialog(null, "Selected medical problem not found!", "Error!", JOptionPane.ERROR_MESSAGE);
		                    return;
		                }
		                treatments = selectedMedicalProblem.getTreatmentsList(); // Get the list of treatments for the medical problem
		                objectType = "medical problem";
		            }

		            // Build the string to display treatments
		            StringBuilder treatmentList = new StringBuilder("Treatments:\n");
		            if (!treatments.isEmpty()) {
		                for (Treatment treatment : treatments) {
		                    treatmentList.append(treatment.getSerialNumber()).append(". ")
		                                 .append(treatment.getDescription()).append("\n");
		                }
		            } else {
		                treatmentList.append("No treatments found for the selected ").append(objectType).append(".");
		            }

		            // Show the treatments in a JOptionPane
		            JOptionPane.showMessageDialog(null, treatmentList.toString(), "Treatments", JOptionPane.INFORMATION_MESSAGE);
		        } catch (NoCellSelectedException x) {
		            // Handle the case where no row is selected
		            JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		viewMedicalProblems = styleButton(new JButton("View Medical Problems"), new Color(255, 255, 255), Color.WHITE);
		viewMedicalProblems.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		viewMedicalProblems.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try {
		        
		            int selectedRow = table.getSelectedRow();
		            if (selectedRow < 0) {
		                throw new NoCellSelectedException();
		            }

		            HashMap<String, String> medicalProblems = new HashMap<>();

		            // Determine the content based on the last table opened
		            if (lastTableOpened == treatmentsFrame) {
		                int treatmentCode = (int) table.getValueAt(selectedRow, 0);
		                Treatment selectedTreatment = hosp.getRealTreatment(treatmentCode);
		                if (selectedTreatment != null) {
		                    for (MedicalProblem problem : selectedTreatment.getMedicalProblemsList()) {
		                        medicalProblems.put(problem.getCode(), problem.getName());
		                    }
		                }
		            } else if (lastTableOpened == visitsFrame) {
		                int visitId = (int) table.getValueAt(selectedRow, 0);
		                Visit selectedVisit = hosp.getRealVisit(visitId);
		                if (selectedVisit != null) {
		                    for (MedicalProblem problem : selectedVisit.getMedicalProblemsList()) {
		                        medicalProblems.put(problem.getCode(), problem.getName());
		                    }
		                }
		            }

		            // Build the string to display medical problems
		            StringBuilder medicalProblemList = new StringBuilder("Medical Problems:\n");
		            if (medicalProblems.isEmpty()) {
		                medicalProblemList.append("No medical problems found for the selected object.");
		            } else {
		                for (Map.Entry<String, String> entry : medicalProblems.entrySet()) {
		                    medicalProblemList.append(entry.getKey()).append(". ").append(entry.getValue()).append("\n");
		                }
		            }

		            // Show the medical problems in a JOptionPane
		            JOptionPane.showMessageDialog(null, medicalProblemList.toString(), "Medical Problems", JOptionPane.INFORMATION_MESSAGE);
		        } catch (NoCellSelectedException x) {
		            // Handle the case where no row is selected
		            JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		viewMedications = styleButton(new JButton("View Medications"), new Color(255, 255, 255), Color.WHITE);
		viewMedications.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		viewMedications.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try {
		            int selectedRow = table.getSelectedRow();
		            if (selectedRow < 0) {
		                throw new NoCellSelectedException();
		            }

		            int treatmentCode = (int) table.getValueAt(selectedRow, 0);
		            Treatment selectedTreatment = hosp.getRealTreatment(treatmentCode);

		            if (selectedTreatment == null) {
		                // Display an error message if the treatment is not found
		                JOptionPane.showMessageDialog(null, "Selected treatment not found!", "Error!", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            // Build the string to display medications
		            StringBuilder medicationList = new StringBuilder("Medications:\n");
		            if (!selectedTreatment.getMedicationsList().isEmpty()) {
		                boolean medicationFound = false;
		                for (Medication medication : selectedTreatment.getMedicationsList()) {
		                    if (medication != null) {
		                        medicationFound = true;
		                        medicationList.append(medication.getCode()).append(". ").append(medication.getName()).append("\n");
		                    }
		                }
		                if (!medicationFound) {
		                    medicationList.append("No medications found for the selected treatment.");
		                }
		            } else {
		                medicationList.append("No medications found for the selected treatment.");
		            }

		            // Show the medications in a dialog
		            JOptionPane.showMessageDialog(null, medicationList.toString(), "Medications", JOptionPane.INFORMATION_MESSAGE);
		        } catch (NoCellSelectedException x) {
		            // Handle the case where no row is selected
		            JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		viewVisits = styleButton(new JButton("View Visits"), new Color(255, 255, 255), Color.WHITE);
		viewVisits.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		viewVisits.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Get the selected row index from the table
		            int selectedRow = table.getSelectedRow();
		            if (selectedRow < 0) {
		                throw new NoCellSelectedException(); // Custom exception if no row is selected
		            }

		            int patientId = (int) table.getValueAt(selectedRow, 0);
		            Patient selectedPatient = hosp.getRealPatient(patientId);

		            if (selectedPatient == null) {
		                // Display an error message if the patient is not found
		                JOptionPane.showMessageDialog(null, "Selected patient not found!", "Error!", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            // Build the string to display visits
		            StringBuilder visitList = new StringBuilder("Visits:\n");
		            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		            if (!selectedPatient.getVisitsList().isEmpty()) {
		                for (Visit visit : selectedPatient.getVisitsList()) {
		                    if (visit != null) {
		                        String formattedStartDate = dateFormat.format(visit.getStartDate());
		                        String formattedEndDate = dateFormat.format(visit.getEndDate());
		                        visitList.append(visit.getNumber()).append(". ").append(formattedStartDate)
		                                 .append(" - ").append(formattedEndDate).append("\n");
		                    }
		                }
		            } else {
		                visitList.append("No visits found for the selected patient.");
		            }

		            // Show the visits in a dialog
		            JOptionPane.showMessageDialog(null, visitList.toString(), "Visits", JOptionPane.INFORMATION_MESSAGE);
		        } catch (NoCellSelectedException x) {
		            // Handle the case where no row is selected
		            JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		viewStaff = styleButton(new JButton("View Staff"), new Color(255, 255, 255), Color.WHITE);
		viewStaff.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		viewStaff.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Get the selected row index from the table
		            int selectedRow = table.getSelectedRow();
		            if (selectedRow < 0) {
		                throw new NoCellSelectedException(); // Custom exception if no row is selected
		            }

		            int departmentId = (int) table.getValueAt(selectedRow, 0);
		            Department selectedDepartment = hosp.getRealDepartment(departmentId);

		            if (selectedDepartment == null) {
		                // Display an error message if the department is not found
		                JOptionPane.showMessageDialog(null, "Selected department not found!", "Error!", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            // Build the string to display staff members
		            StringBuilder staffList = new StringBuilder("Staff:\n");
		            if (!selectedDepartment.getStaffMembersList().isEmpty()) {
		                boolean staffFound = false;
		                for (StaffMember staffMember : selectedDepartment.getStaffMembersList()) {
		                    if (staffMember != null) {
		                        staffFound = true;
		                        staffList.append(staffMember.getId()).append(". ").append(staffMember.getFirstName())
		                                 .append(" ").append(staffMember.getLastName()).append("\n");
		                    }
		                }
		                if (!staffFound) {
		                    staffList.append("No staff found for the selected department.");
		                }
		            } else {
		                staffList.append("No staff found for the selected department.");
		            }

		            // Show the staff members in a dialog
		            JOptionPane.showMessageDialog(null, staffList.toString(), "Staff", JOptionPane.INFORMATION_MESSAGE);
		        } catch (NoCellSelectedException x) {
		            // Handle the case where no row is selected
		            JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		viewDepartments = styleButton(new JButton("View Departments"), new Color(255, 255, 255), Color.WHITE);
		viewDepartments.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		viewDepartments.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Get the selected row index from the table
		            int selectedRow = table.getSelectedRow();
		            if (selectedRow < 0) {
		                throw new NoCellSelectedException(); // Custom exception if no row is selected
		            }

		            int id = (int) table.getValueAt(selectedRow, 0);

		            HashMap<Integer, String> departments = new HashMap<>();
		            // Determine the context based on the last table opened
		            if (lastTableOpened == nursesFrame) {
		                Nurse selectedNurse = hosp.getRealNurse(id);
		                if (selectedNurse != null) {
		                    for (Department department : selectedNurse.getDepartments()) {
		                        departments.put(department.getNumber(), department.getName());
		                    }
		                }
		            } else if (lastTableOpened == doctorsFrame) {
		                Doctor selectedDoctor = hosp.getRealDoctor(id);
		                if (selectedDoctor != null) {
		                    for (Department department : selectedDoctor.getDepartments()) {
		                        departments.put(department.getNumber(), department.getName());
		                    }
		                }
		            }

		            // Build the string to display departments
		            StringBuilder departmentList = new StringBuilder("Departments:\n");
		            if (departments.isEmpty()) {
		                departmentList.append("No departments are associated with the selected object.");
		            } else {
		                for (Map.Entry<Integer, String> entry : departments.entrySet()) {
		                    departmentList.append(entry.getKey()).append(". ").append(entry.getValue()).append("\n");
		                }
		            }

		            // Show the departments in a dialog
		            JOptionPane.showMessageDialog(null, departmentList.toString(), "Departments", JOptionPane.INFORMATION_MESSAGE);
		        } catch (NoCellSelectedException x) {
		            // Handle the case where no row is selected
		            JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		viewDoctors = styleButton(new JButton("View Qualified Doctors"), new Color(255, 255, 255), Color.WHITE);
		viewDoctors.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		viewDoctors.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Get the selected row index from the table
		            int selectedRow = table.getSelectedRow();
		            if (selectedRow < 0) {
		                throw new NoCellSelectedException(); // Custom exception if no row is selected
		            }

		            int id = (int) table.getValueAt(selectedRow, 0);

		            Treatment selectedTreatment = hosp.getRealTreatment(id);
		            if (selectedTreatment == null) {
		                // Display an error message if the treatment is not found
		                JOptionPane.showMessageDialog(null, "Selected treatment does not exist!", "Error!", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            HashSet<Doctor> doctorsList = selectedTreatment.getDoctorsList();

		            // Build the string to display doctors
		            StringBuilder doctorList = new StringBuilder();
		            if (doctorsList.isEmpty()) {
		                doctorList.append("No doctors are qualified in the selected treatment.");
		            } else {
		                doctorList.append("Doctors qualified for this treatment:\n");
		                for (Doctor doctor : doctorsList) {
		                    doctorList.append(doctor.getId()).append(". ").append(doctor.getFirstName()).append(" ")
		                              .append(doctor.getLastName()).append("\n");
		                }
		            }

		            // Show the doctors in a dialog
		            JOptionPane.showMessageDialog(null, doctorList.toString(), "Doctors", JOptionPane.INFORMATION_MESSAGE);
		        } catch (NoCellSelectedException x) {
		            // Handle the case where no row is selected
		            JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});


		/******** ADD TO LIST ******/

		// Button to add a staff member to a department
		btnAddStaffMemberToDep = styleButton(new JButton("Add Staff Member"), new Color(255, 255, 255), Color.WHITE);
		btnAddStaffMemberToDep.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAddStaffMemberToDep.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Get the selected row index from the table
		            int selectedRow = table.getSelectedRow();
		            if (selectedRow < 0) {
		                throw new NoCellSelectedException(); // Custom exception if no row is selected
		            }

		            // Retrieve the selected department by its ID
		            int departmentId = (int) table.getValueAt(selectedRow, 0);
		            Department selectedDepartment = hosp.getRealDepartment(departmentId);

		            if (selectedDepartment == null) {
		                // Display an error message if the department is not found
		                JOptionPane.showMessageDialog(null, "Selected department not found!", "Error!", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            // Set the default selection in the dropdown for choosing the table
		            chooseTable3.setSelectedItem("Doctors");
		            // Update the table to show the list of doctors
		            updateTable(writeDoctor(), addDoctorSP);
		            // Refresh the frame displaying the list to add staff members
		            addStaffMemberToDepartmentFrame.revalidate();
		            addStaffMemberToDepartmentFrame.repaint();
		            lastTableOpened = addStaffMemberToDepartmentFrame;
		            // Show the frame for adding staff members to a department
		            showFrame("addStaffMemberToDepartmentFrame");

		            // Add necessary buttons to the frame
		            addStaffMemberToDepartmentFrame.add(backButton);
		            addStaffMemberToDepartmentFrame.add(saveAddToList);
		            addStaffMemberToDepartmentFrame.add(chooseTable3);

		            // Remove previous action listeners from the save button to avoid multiple listeners being added
		            for (ActionListener al : saveAddToList.getActionListeners()) {
		                saveAddToList.removeActionListener(al);
		            }

		            // Add a new action listener to the save button
		            saveAddToList.addActionListener(new ActionListener() {
		                @Override
		                public void actionPerformed(ActionEvent e) {
		                    try {
		                        // Get the selected row index from the table
		                        int selectedStaffRow = table.getSelectedRow();
		                        if (selectedStaffRow < 0) {
		                            throw new NoCellSelectedException();
		                        }

		                        int id = (int) table.getValueAt(selectedStaffRow, 0);
		                        StaffMember selectedStaffMember = null;

		                        // Determine whether the staff member is a doctor or a nurse
		                        switch (chooseTable3.getSelectedItem().toString()) {
		                            case "Doctors":
		                                selectedStaffMember = hosp.getRealDoctor(id);
		                                break;
		                            case "Nurses":
		                                selectedStaffMember = hosp.getRealNurse(id);
		                                break;
		                        }

		                        if (selectedStaffMember == null) {
		                            // Display an error message if the staff member is not found
		                            JOptionPane.showMessageDialog(null, "Selected staff member not found!", "Error!", JOptionPane.ERROR_MESSAGE);
		                            return;
		                        }

		                        // Attempt to add the staff member to the department
		                        boolean success = false;
		                        switch (chooseTable3.getSelectedItem().toString()) {
		                            case "Doctors":
		                                success = hosp.addDoctorToDepartment(selectedDepartment, (Doctor) selectedStaffMember);
		                                break;
		                            case "Nurses":
		                                success = hosp.addNurseToDepartment(selectedDepartment, (Nurse) selectedStaffMember);
		                                break;
		                        }

		                        if (success) {
		                            // Build a list of staff members in the department after adding the new one
		                            StringBuilder staffList = new StringBuilder(
		                                "Staff member successfully added to the department!\n\nUpdated Staff List for Department " + selectedDepartment.getNumber() + ":\n");
		                            for (StaffMember staff : selectedDepartment.getStaffMembersList()) {
		                                if (staff != null) {
		                                    staffList.append(staff.getId()).append(". ").append(staff.getFirstName()).append(" ").append(staff.getLastName()).append("\n");
		                                }
		                            }
		                            
		                            
		                            serialize(hosp);
		                            // Show the updated staff list in a success dialog
		                            JOptionPane.showMessageDialog(null, staffList.toString(), "Success!", JOptionPane.INFORMATION_MESSAGE);
		                            
		                        }
		                        
		                    } catch (NoCellSelectedException x) {
		                        // Handle the case where no row is selected
		                        JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		                    } catch (ObjectAlreadyExistsException x) {
		                        // Handle the case where the staff member already exists in the department
		                        JOptionPane.showMessageDialog(null, "Failed to add! Object already exists in the list!", "Error!", JOptionPane.ERROR_MESSAGE);
		                    }
		                }
		            });
		        } catch (NoCellSelectedException x) {
		            // Handle the case where no row is selected
		            JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		// Button to add a treatment to a medical problem
		btnAddTreatmentToMedProblem = styleButton(new JButton("Add Treatment"), new Color(255, 255, 255), Color.WHITE);
		btnAddTreatmentToMedProblem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAddTreatmentToMedProblem.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Get the selected row index from the table
		            int selectedRow = table.getSelectedRow();
		            if (selectedRow < 0) {
		                throw new NoCellSelectedException(); // Custom exception if no row is selected
		            }

		            String code = (String) table.getValueAt(selectedRow, 0);
		            selectedProblem = null;

		            // Determine the type of medical problem based on the selection in the dropdown
		            switch (chooseTable.getSelectedItem().toString()) {
		                case "Injuries":
		                    selectedProblem = hosp.getRealInjury(code);
		                    break;
		                case "Diseases":
		                    selectedProblem = hosp.getRealDisease(code);
		                    break;
		                case "Fractures":
		                    selectedProblem = hosp.getRealFracture(code);
		                    break;
		            }

		            if (selectedProblem == null) {
		                // Display an error message if the medical problem is not found
		                JOptionPane.showMessageDialog(null, "Selected medical problem not found!", "Error!", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            // Update the table to show the list of treatments
		            updateTable(writeTreatment(), addTreatmentSP);
		            // Refresh the frame displaying the list to add treatments
		            addTreatmentToFrame.revalidate();
		            addTreatmentToFrame.repaint();
		            lastTableOpened = addTreatmentToFrame;
		            // Show the frame for adding treatments to a medical problem
		            showFrame("addTreatmentToFrame");

		            // Add necessary buttons to the frame
		            addTreatmentToFrame.add(backButton);
		            addTreatmentToFrame.add(saveAddToList);

		            // Remove previous action listeners from the save button to avoid multiple listeners being added
		            for (ActionListener al : saveAddToList.getActionListeners()) {
		                saveAddToList.removeActionListener(al);
		            }

		            // Add a new action listener to the save button
		            saveAddToList.addActionListener(new ActionListener() {
		                @Override
		                public void actionPerformed(ActionEvent e) {
		                    try {
		                        // Get the selected row index from the table
		                        int selectedTreatmentRow = table.getSelectedRow();
		                        if (selectedTreatmentRow < 0) {
		                            throw new NoCellSelectedException();
		                        }

		                        int treatmentId = (int) table.getValueAt(selectedTreatmentRow, 0);
		                        Treatment selectedTreatment = hosp.getRealTreatment(treatmentId);

		                        if (selectedTreatment == null) {
		                            // Display an error message if the treatment is not found
		                            JOptionPane.showMessageDialog(null, "Selected treatment not found!", "Error!", JOptionPane.ERROR_MESSAGE);
		                            return;
		                        }

		                        try {
		                            // Attempt to add the treatment to the medical problem
		                            selectedProblem.addTreatment(selectedTreatment);
		                        } catch (ObjectAlreadyExistsException ex) {
		                            // Handle the case where the treatment already exists for the medical problem
		                            JOptionPane.showMessageDialog(null, "Failed to add! Object already exists in the list!", "Error!", JOptionPane.ERROR_MESSAGE);
		                            return;
		                        }

		                        // Build a list of treatments for the medical problem after adding the new one
		                        StringBuilder treatmentList = new StringBuilder(
		                            "Treatment successfully added to the medical problem!\n\nUpdated Treatment list for Medical Problem " + selectedProblem.getCode() + "\n");
		                        for (Treatment treatment : selectedProblem.getTreatmentsList()) {
		                            treatmentList.append(treatment.getSerialNumber()).append(". ").append(treatment.getDescription()).append("\n");
		                        }
		                        
	                            serialize(hosp);
		                        // Show the updated treatment list in a success dialog
		                        JOptionPane.showMessageDialog(null, treatmentList.toString(), "Success!", JOptionPane.INFORMATION_MESSAGE);

		                    } catch (NoCellSelectedException ex) {
		                        // Handle the case where no row is selected
		                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		                    }
		                }
		            });
		        } catch (NoCellSelectedException ex) {
		            // Handle the case where no row is selected
		            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		// Button to add a medical problem to a visit
		btnAddMedProblemToVisit = styleButton(new JButton("Add Medical Problem"), new Color(255, 255, 255), Color.WHITE);
		btnAddMedProblemToVisit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAddMedProblemToVisit.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Get the selected row index from the table
		            int selectedRow = table.getSelectedRow();
		            if (selectedRow < 0) {
		                throw new NoCellSelectedException(); // Custom exception if no row is selected
		            }

		            // Retrieve the selected visit by its ID
		            int visitId = (int) table.getValueAt(selectedRow, 0);
		            Visit selectedVisit = hosp.getRealVisit(visitId);

		            if (selectedVisit == null) {
		                // Display an error message if the visit is not found
		                JOptionPane.showMessageDialog(null, "Selected visit not found!", "Error!", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            // Set the default selection in the dropdown for choosing the table
		            chooseTable2.setSelectedItem("Injuries");
		            // Update the table to show the list of injuries
		            updateTable(writeInjury(), addInjurySP);
		            // Refresh the frame displaying the list to add medical problems to the visit
		            addMedProblemToVisitFrame.revalidate();
		            addMedProblemToVisitFrame.repaint();
		            lastTableOpened = addMedProblemToVisitFrame;
		            // Show the frame for adding medical problems to a visit
		            showFrame("addMedProblemToVisitFrame");

		            // Add necessary buttons to the frame
		            addMedProblemToVisitFrame.add(backButton);
		            addMedProblemToVisitFrame.add(saveAddToList);
		            addMedProblemToVisitFrame.add(chooseTable2);

		            // Remove previous action listeners from the save button to avoid multiple listeners being added
		            for (ActionListener al : saveAddToList.getActionListeners()) {
		                saveAddToList.removeActionListener(al);
		            }

		            // Add a new action listener to the save button
		            saveAddToList.addActionListener(new ActionListener() {
		                @Override
		                public void actionPerformed(ActionEvent e) {
		                    try {
		                        // Get the selected row index from the table
		                        int selectedMedProblemRow = table.getSelectedRow();
		                        if (selectedMedProblemRow < 0) {
		                            throw new NoCellSelectedException();
		                        }

		                        String code = (String) table.getValueAt(selectedMedProblemRow, 0);
		                        MedicalProblem selectedProblem = null;

		                        // Determine the type of medical problem based on the selection in the dropdown
		                        switch (chooseTable2.getSelectedItem().toString()) {
		                            case "Injuries":
		                                selectedProblem = hosp.getRealInjury(code);
		                                break;
		                            case "Diseases":
		                                selectedProblem = hosp.getRealDisease(code);
		                                break;
		                            case "Fractures":
		                                selectedProblem = hosp.getRealFracture(code);
		                                break;
		                        }

		                        if (selectedProblem != null) {
		                            // Add the medical problem to the visit
		                            selectedVisit.addMedicalProblem(selectedProblem);

		                            // Build a list of medical problems for the visit after adding the new one
		                            StringBuilder medicalProblemList = new StringBuilder(
		                                "Medical problem successfully added to the visit!\n\nUpdated Medical Problem list for Visit "
		                                + selectedVisit.getNumber() + ":\n");
		                            for (MedicalProblem problem : selectedVisit.getMedicalProblemsList()) {
		                                medicalProblemList.append(problem.getCode()).append(". ").append(problem.getName()).append("\n");
		                            }
		                            serialize(hosp);
		                            // Show the updated medical problem list in a success dialog
		                            JOptionPane.showMessageDialog(null, medicalProblemList.toString(), "Success!", JOptionPane.INFORMATION_MESSAGE);

		                        } else {
		                            // Display an error message if the medical problem is not found
		                            JOptionPane.showMessageDialog(null, "Selected medical problem not found!", "Error!", JOptionPane.ERROR_MESSAGE);
		                        }
		                    } catch (NoCellSelectedException x) {
		                        // Handle the case where no row is selected
		                        JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		                    } catch (ObjectAlreadyExistsException x) {
		                        // Handle the case where the medical problem already exists in the visit
		                        JOptionPane.showMessageDialog(null, "Failed to add! Object already exists in the list!", "Error!", JOptionPane.ERROR_MESSAGE);
		                    }
		                }
		            });
		        } catch (NoCellSelectedException x) {
		            // Handle the case where no row is selected
		            JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		
		// Button to add a medical problem to a treatment
		btnAddMedProblemToTreatment = styleButton(new JButton("Add Medical Problem"), new Color(255, 255, 255), Color.WHITE);
		btnAddMedProblemToTreatment.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAddMedProblemToTreatment.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Get the selected row index from the table
		            int selectedRow = table.getSelectedRow();
		            if (selectedRow < 0) {
		                throw new NoCellSelectedException(); // Custom exception if no row is selected
		            }

		            // Retrieve the selected treatment by its ID
		            int treatmentId = (int) table.getValueAt(selectedRow, 0);
		            Treatment selectedTreatment = hosp.getRealTreatment(treatmentId);

		            if (selectedTreatment == null) {
		                // Display an error message if the treatment is not found
		                JOptionPane.showMessageDialog(null, "Selected treatment not found!", "Error!", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            // Set the default selection in the dropdown for choosing the table
		            chooseTable4.setSelectedItem("Injuries");
		            // Update the table to show the list of injuries
		            updateTable(writeInjury(), addInjurySPTreatments);
		            // Refresh the frame displaying the list to add medical problems to the treatment
		            addMedProblemToTreatmentFrame.revalidate();
		            addMedProblemToTreatmentFrame.repaint();
		            lastTableOpened = addMedProblemToTreatmentFrame;
		            // Show the frame for adding medical problems to a treatment
		            showFrame("addMedProblemToTreatmentFrame");

		            // Add necessary buttons to the frame
		            addMedProblemToTreatmentFrame.add(backButton);
		            addMedProblemToTreatmentFrame.add(saveAddToList);
		            addMedProblemToTreatmentFrame.add(chooseTable4);

		            // Remove previous action listeners from the save button to avoid multiple listeners being added
		            for (ActionListener al : saveAddToList.getActionListeners()) {
		                saveAddToList.removeActionListener(al);
		            }

		            // Add a new action listener to the save button
		            saveAddToList.addActionListener(new ActionListener() {
		                @Override
		                public void actionPerformed(ActionEvent e) {
		                    try {
		                        // Get the selected row index from the table
		                        int selectedMedProblemRow = table.getSelectedRow();
		                        if (selectedMedProblemRow < 0) {
		                            throw new NoCellSelectedException();
		                        }

		                        String code = (String) table.getValueAt(selectedMedProblemRow, 0);
		                        MedicalProblem selectedProblem = null;

		                        // Determine the type of medical problem based on the selection in the dropdown
		                        switch (chooseTable4.getSelectedItem().toString()) {
		                            case "Injuries":
		                                selectedProblem = hosp.getRealInjury(code);
		                                break;
		                            case "Diseases":
		                                selectedProblem = hosp.getRealDisease(code);
		                                break;
		                            case "Fractures":
		                                selectedProblem = hosp.getRealFracture(code);
		                                break;
		                        }

		                        if (selectedProblem != null) {
		                            // Add the medical problem to the treatment
		                            selectedTreatment.addMedicalProblem(selectedProblem);

		                            // Build a list of medical problems for the treatment after adding the new one
		                            StringBuilder medicalProblemList = new StringBuilder(
		                                "Medical problem successfully added to the treatment!\n\nUpdated Medical Problem list for Treatment "
		                                + selectedTreatment.getSerialNumber() + ":\n");
		                            for (MedicalProblem problem : selectedTreatment.getMedicalProblemsList()) {
		                                medicalProblemList.append(problem.getCode()).append(". ").append(problem.getName()).append("\n");
		                            }
		                            serialize(hosp);
		                            // Show the updated medical problem list in a success dialog
		                            JOptionPane.showMessageDialog(null, medicalProblemList.toString(), "Success!", JOptionPane.INFORMATION_MESSAGE);

		                        } else {
		                            // Display an error message if the medical problem is not found
		                            JOptionPane.showMessageDialog(null, "Selected medical problem not found!", "Error!", JOptionPane.ERROR_MESSAGE);
		                        }
		                    } catch (NoCellSelectedException x) {
		                        // Handle the case where no row is selected
		                        JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		                    } catch (ObjectAlreadyExistsException x) {
		                        // Handle the case where the medical problem already exists in the treatment
		                        JOptionPane.showMessageDialog(null, "Failed to add! Object already exists in the list!", "Error!", JOptionPane.ERROR_MESSAGE);
		                    }
		                }
		            });
		        } catch (NoCellSelectedException x) {
		            // Handle the case where no row is selected
		            JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});


		// Button to add a treatment to a visit
		btnAddTreatmentToVisit = styleButton(new JButton("Add Treatment"), new Color(255, 255, 255), Color.WHITE);
		btnAddTreatmentToVisit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAddTreatmentToVisit.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Get the selected row index from the table
		            int selectedRow = table.getSelectedRow();
		            if (selectedRow < 0) {
		                throw new NoCellSelectedException(); // Custom exception if no row is selected
		            }

		            // Retrieve the selected visit by its ID
		            int visitId = (int) table.getValueAt(selectedRow, 0);
		            Visit selectedVisit = hosp.getRealVisit(visitId);

		            if (selectedVisit == null) {
		                // Display an error message if the visit is not found
		                JOptionPane.showMessageDialog(null, "Selected visit not found!", "Error!", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            // Update the table to show the list of treatments
		            updateTable(writeTreatment(), addTreatmentSP);
		            // Refresh the frame displaying the list to add treatments
		            addTreatmentToFrame.revalidate();
		            addTreatmentToFrame.repaint();
		            lastTableOpened = addTreatmentToFrame;
		            // Show the frame for adding treatments to a visit
		            showFrame("addTreatmentToFrame");

		            // Add necessary buttons to the frame
		            addTreatmentToFrame.add(backButton);
		            addTreatmentToFrame.add(saveAddToList);

		            // Remove previous action listeners from the save button to avoid multiple listeners being added
		            for (ActionListener al : saveAddToList.getActionListeners()) {
		                saveAddToList.removeActionListener(al);
		            }

		            // Add a new action listener to the save button
		            saveAddToList.addActionListener(new ActionListener() {
		                @Override
		                public void actionPerformed(ActionEvent e) {
		                    try {
		                        // Get the selected row index from the treatments table
		                        int selectedTreatmentRow = table.getSelectedRow();
		                        if (selectedTreatmentRow < 0) {
		                            throw new NoCellSelectedException();
		                        }

		                        int treatmentId = (int) treatmentsModel.getValueAt(selectedTreatmentRow, 0);
		                        Treatment selectedTreatment = hosp.getRealTreatment(treatmentId);

		                        if (selectedTreatment == null) {
		                            // Display an error message if the treatment is not found
		                            JOptionPane.showMessageDialog(null, "Selected treatment not found!", "Error!", JOptionPane.ERROR_MESSAGE);
		                            return;
		                        }

		                        // Attempt to add the treatment to the visit
		                        selectedVisit.addTreatment(selectedTreatment);

		                        // Build a list of treatments for the visit after adding the new one
		                        StringBuilder treatmentList = new StringBuilder(
		                            "Treatment successfully added to the visit!\n\nUpdated Treatment list for Visit " + selectedVisit.getNumber() + ":\n");
		                        for (Treatment treatment : selectedVisit.getTreatmentsList()) {
		                            treatmentList.append(treatment.getSerialNumber()).append(". ").append(treatment.getDescription()).append("\n");
		                        }
	                            serialize(hosp);
		                        // Show the updated treatment list in a success dialog
		                        JOptionPane.showMessageDialog(null, treatmentList.toString(), "Success!", JOptionPane.INFORMATION_MESSAGE);

		                    } catch (ObjectAlreadyExistsException x) {
		                        // Handle the case where the treatment already exists in the visit
		                        JOptionPane.showMessageDialog(null, "Failed to add! Object already exists in the list!", "Error!", JOptionPane.ERROR_MESSAGE);

		                    } catch (NoCellSelectedException x) {
		                        // Handle the case where no row is selected
		                        JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		                    }
		                }
		            });
		        } catch (NoCellSelectedException x) {
		            // Handle the case where no row is selected
		            JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		// Button to add a medication to a treatment
		btnAddMedicationToTreatment = styleButton(new JButton("Add Medication"), new Color(255, 255, 255), Color.WHITE);
		btnAddMedicationToTreatment.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAddMedicationToTreatment.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        try {
		            // Get the selected row index from the table
		            int selectedRow = table.getSelectedRow();
		            if (selectedRow < 0) {
		                throw new NoCellSelectedException(); // Custom exception if no row is selected
		            }

		            // Retrieve the selected treatment by its ID
		            int treatmentId = (int) table.getValueAt(selectedRow, 0);
		            Treatment selectedTreatment = hosp.getRealTreatment(treatmentId);

		            if (selectedTreatment == null) {
		                // Display an error message if the treatment is not found
		                JOptionPane.showMessageDialog(null, "Selected treatment not found!", "Error!", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            // Update the table to show the list of medications
		            updateTable(writeMedication(), addMedicationToTreatmentSP);
		            // Refresh the frame displaying the list to add medications to the treatment
		            addMedicationToTreatmentFrame.revalidate();
		            addMedicationToTreatmentFrame.repaint();
		            lastTableOpened = addMedicationToTreatmentFrame;
		            // Show the frame for adding medications to a treatment
		            showFrame("addMedicationToTreatmentFrame");

		            // Add necessary buttons to the frame
		            addMedicationToTreatmentFrame.add(backButton);
		            addMedicationToTreatmentFrame.add(saveAddToList);

		            // Remove previous action listeners from the save button to avoid multiple listeners being added
		            for (ActionListener al : saveAddToList.getActionListeners()) {
		                saveAddToList.removeActionListener(al);
		            }

		            // Add a new action listener to the save button
		            saveAddToList.addActionListener(new ActionListener() {
		                @Override
		                public void actionPerformed(ActionEvent e) {
		                    try {
		                        // Get the selected row index from the medications table
		                        int selectedMedicationRow = table.getSelectedRow();
		                        if (selectedMedicationRow < 0) {
		                            throw new NoCellSelectedException();
		                        }

		                        int medicationId = (int) medicationsModel.getValueAt(selectedMedicationRow, 0);
		                        Medication selectedMedication = hosp.getRealMedication(medicationId);

		                        if (selectedMedication == null) {
		                            // Display an error message if the medication is not found
		                            JOptionPane.showMessageDialog(null, "Selected medication not found!", "Error!", JOptionPane.ERROR_MESSAGE);
		                            return;
		                        }

		                        // Attempt to add the medication to the treatment
		                        if (selectedTreatment.addMedication(selectedMedication)) {
		                            // Build a list of medications for the treatment after adding the new one
		                            StringBuilder medicationList = new StringBuilder(
		                                "Medication successfully added to the treatment!\n\nUpdated Medication list for Treatment " + selectedTreatment.getSerialNumber() + ":\n");
		                            for (Medication medication : selectedTreatment.getMedicationsList()) {
		                                medicationList.append(medication.getCode()).append(". ").append(medication.getName()).append("\n");
		                            }
		                            serialize(hosp);
		                            // Show the updated medication list in a success dialog
		                            JOptionPane.showMessageDialog(null, medicationList.toString(), "Success!", JOptionPane.INFORMATION_MESSAGE);

		                        }
		                    } catch (ObjectAlreadyExistsException x) {
		                        // Handle the case where the medication already exists in the treatment
		                        JOptionPane.showMessageDialog(null, "Failed to add! Object already exists in the list!", "Error!", JOptionPane.ERROR_MESSAGE);

		                    } catch (NoCellSelectedException x) {
		                        // Handle the case where no row is selected
		                        JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		                    }
		                }
		            });
		        } catch (NoCellSelectedException x) {
		            // Handle the case where no row is selected
		            JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});

		// Add hover effects to all buttons
		addHoverEffect(addButton);
		addHoverEffect(removeButton);
		addHoverEffect(editButton);
		addHoverEffect(saveButton);
		addHoverEffect(btnAddMedicationToTreatment);
		addHoverEffect(btnAddMedProblemToVisit);
		addHoverEffect(btnAddStaffMemberToDep);
		addHoverEffect(btnAddTreatmentToMedProblem);
		addHoverEffect(saveAddToList);
		addHoverEffect(btnAddTreatmentToVisit);
		addHoverEffect(viewDepartments);
		addHoverEffect(viewDoctors);
		addHoverEffect(viewStaff);
		addHoverEffect(viewTreatments);
		addHoverEffect(viewMedications);
		addHoverEffect(viewMedicalProblems);
		addHoverEffect(viewVisits);
		addHoverEffect(btnAddMedProblemToTreatment);
		

		// SIDEBAR
		sidebar = new JPanel();
		sidebar.setBackground(Color.WHITE);
		sidebar.setBounds(0, 0, 223, 680);
		contentPane.add(sidebar);
		sidebar.setLayout(null);

		setupCommonSidebar(sidebar);

		setupSpecificUI();
				
		// ************ CATEGORY FRAMES ***************//
		
		// MAIN FRAME
		
		JPanel MainFrame = new JPanel();
		MainFrame.setBackground(Color.WHITE);
		CardView.add(MainFrame, "MainFrame");
		MainFrame.setLayout(null);

		JLabel lblNewLabel = new JLabel("Welcome back!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel.setBounds(374, 43, 219, 112);
		MainFrame.add(lblNewLabel);

		JLabel mainFrameGif = new JLabel("");
		mainFrameGif.setIcon(new ImageIcon(BasicWindow.class.getResource("/resources/medicalFrontliners.gif")));
		mainFrameGif.setBounds(177, 20, 640, 640);
		MainFrame.add(mainFrameGif);


		// Departments
		
		departmentsFrame = new JPanel();
		departmentsFrame.setBackground(Color.WHITE);
		CardView.add(departmentsFrame, "departmentsFrame");
		departmentsFrame.setLayout(null);

		departmentsModel = createTableModel(new String[] { "Number", "Name", "Manager", "Location", "Specialization" },
				new Class[] { Integer.class, String.class, Doctor.class, String.class, Specialization.class });

		//Initialize the Departments table
		table = new JTable(departmentsModel);
		table.setBackground(Color.WHITE);
		populateTable(departmentsModel, hosp.getDepartments(),
				new Object[][] { { Integer.class, String.class, Doctor.class, String.class, Specialization.class } });

		//Initialize the Departments ScrollPane
		departmentSP = new JScrollPane(table);
		departmentSP.setBounds(10, 74, 975, 384);
		departmentsFrame.add(departmentSP);

		JLabel departmentsImg1 = new JLabel("");
		departmentsImg1.setIcon(new ImageIcon(BasicWindow.class.getResource("/resources/Medicine-pana (1).png")));
		departmentsImg1.setBounds(695, 421, 300, 300);
		departmentsFrame.add(departmentsImg1);

		JLabel departmentsImg1_1 = new JLabel("");
		departmentsImg1_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		departmentsImg1_1
				.setIcon(new ImageIcon(BasicWindow.class.getResource("/resources/Cardiologist-pana (1) (1).png")));
		departmentsImg1_1.setBounds(20, 430, 250, 250);
		departmentsFrame.add(departmentsImg1_1);

		// Medical Problems
		
		medicalProblemsFrame = new JPanel();
		medicalProblemsFrame.setBackground(Color.WHITE);
		CardView.add(medicalProblemsFrame, "medicalProblemsFrame");
		medicalProblemsFrame.setLayout(null);

		//Initialize the Medical Problems tables
		injuriesModel = createTableModel(
				new String[] { "Code", "Name", "Department", "Common recovery time (months)", "Location" },
				new Class[] { String.class, String.class, Department.class, Double.class, String.class });
		table = new JTable(injuriesModel);
		populateTableString(injuriesModel, injuriesHashMap,
				new Object[][] { { String.class, String.class, Department.class, Double.class, String.class } });

		diseasesModel = createTableModel(new String[] { "Code", "Name", "Department", "Description" },
				new Class[] { String.class, String.class, Department.class, String.class });
		table = new JTable(diseasesModel);
		populateTableString(diseasesModel, diseasesHashMap,
				new Object[][] { { String.class, String.class, Department.class, String.class } });

		fracturesModel = createTableModel(new String[] { "Code", "Name", "Department", "Location", "Requires cast" },
				new Class[] { String.class, String.class, Department.class, String.class, String.class });
		table = new JTable(fracturesModel);
		populateTableString(fracturesModel, fracturesHashMap,
				new Object[][] { { String.class, String.class, Department.class, String.class, String.class } });

		//Initialize the Medical Problems scroll panes
		injuriesSP = new JScrollPane(table);
		injuriesSP.setBounds(10, 67, 975, 404);
		medicalProblemsFrame.add(injuriesSP);

		diseasesSP = new JScrollPane(table);
		diseasesSP.setBounds(10, 67, 975, 404);
		medicalProblemsFrame.add(diseasesSP);

		fracturesSP = new JScrollPane(table);
		fracturesSP.setBounds(10, 67, 975, 404);
		medicalProblemsFrame.add(fracturesSP);
		
		//Create Combo Box to choose between Medical Problems tables
		chooseTable = new JComboBox<String>();
		chooseTable.setBackground(new Color(255, 255, 255));
		chooseTable.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chooseTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chooseTable.setModel(new DefaultComboBoxModel<String>(new String[] { "Injuries", "Diseases", "Fractures" }));
		chooseTable.setBounds(10, 29, 155, 21);
		medicalProblemsFrame.add(chooseTable);

		JLabel chooseTableLabel = new JLabel("Choose data to view:");
		chooseTableLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chooseTableLabel.setBounds(10, 10, 155, 13);
		medicalProblemsFrame.add(chooseTableLabel);

		JLabel medProblemImg = new JLabel("");
		medProblemImg.setIcon(new ImageIcon(BasicWindow.class.getResource("/resources/Ambulance-pana (1).png")));
		medProblemImg.setBounds(10, 463, 346, 229);
		medicalProblemsFrame.add(medProblemImg);

		JLabel medProblemsImg2 = new JLabel("");
		medProblemsImg2.setIcon(new ImageIcon(BasicWindow.class.getResource("/resources/Hospital building-pana (1).png")));
		medProblemsImg2.setBounds(631, 418, 359, 275);
		medicalProblemsFrame.add(medProblemsImg2);

		//Show the Medical Problem table after pressing the Combo Box
		chooseTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (chooseTable.getSelectedItem().toString()) {
				case "Injuries":
					updateTable(writeInjury(), injuriesSP);
					injuriesSP.setVisible(true);
					diseasesSP.setVisible(false);
					fracturesSP.setVisible(false);
					break;
				case "Diseases":
					updateTable(writeDisease(), diseasesSP);
					injuriesSP.setVisible(false);
					diseasesSP.setVisible(true);
					fracturesSP.setVisible(false);
					break;
				case "Fractures":
					updateTable(writeFracture(), fracturesSP);
					injuriesSP.setVisible(false);
					diseasesSP.setVisible(false);
					fracturesSP.setVisible(true);
					break;
				}
				medicalProblemsFrame.revalidate();
				medicalProblemsFrame.repaint();
			}

		});

		// Patients
		
		patientsFrame = new JPanel();
		patientsFrame.setBackground(Color.WHITE);
		CardView.add(patientsFrame, "patientsFrame");
		patientsFrame.setLayout(null);

		//Initialize the Patients table
		patientsModel = createTableModel(
				new String[] { "ID", "First name", "Last name", "Date of birth", "Address", "Phone number", "Email",
						"Health Fund", "Biological Sex", "Gender" },
				new Class[] { Integer.class, String.class, String.class, Date.class, String.class, String.class,
						String.class, HealthFund.class, BiologicalSex.class, String.class });
		table = new JTable(patientsModel);
		populateTable(patientsModel, hosp.getPatients(),
				new Object[][] { { Integer.class, String.class, String.class, Date.class, String.class, String.class,
						String.class, HealthFund.class, BiologicalSex.class, String.class } });

		//Initialize the Patient scroll pane
		patientSP = new JScrollPane(table);
		patientSP.setBounds(10, 67, 975, 419);
		patientsFrame.add(patientSP);

		JLabel patientsImg1 = new JLabel("");
		patientsImg1.setIcon(new ImageIcon(BasicWindow.class.getResource("/resources/Screenshot_3 (1).png")));
		patientsImg1.setBounds(721, 415, 431, 317);
		patientsFrame.add(patientsImg1);

		JLabel patientsImg2 = new JLabel("");
		patientsImg2.setIcon(new ImageIcon(BasicWindow.class.getResource("/resources/Screenshot_4.png")));
		patientsImg2.setBounds(0, 469, 321, 271);
		patientsFrame.add(patientsImg2);

		//Visits
		visitsFrame = new JPanel();
		visitsFrame.setBackground(Color.WHITE);
		CardView.add(visitsFrame, "visitsFrame");
		visitsFrame.setLayout(null);

		//Initialize the Visits table
		visitsModel = createTableModel(new String[] { "Number", "Patient", "Start", "End" },
				new Class[] { Integer.class, Patient.class, Date.class, Date.class });
		table = new JTable(visitsModel);
		populateTable(visitsModel, hosp.getVisits(),
				new Object[][] { { Integer.class, Patient.class, Date.class, Date.class } });

		//Initialize the Visits scroll pane
		visitSP = new JScrollPane(table);
		visitSP.setBounds(10, 80, 975, 408);
		visitsFrame.add(visitSP);

		JLabel visitsImg1 = new JLabel("");
		visitsImg1
				.setIcon(new ImageIcon(BasicWindow.class.getResource("/resources/Hospital family visit-pana (1).png")));
		visitsImg1.setBounds(10, 478, 321, 221);
		visitsFrame.add(visitsImg1);

		JLabel visitsImg2 = new JLabel("");
		visitsImg2.setIcon(
				new ImageIcon(BasicWindow.class.getResource("/resources/Hospital family visit-rafiki (2) (3).png")));
		visitsImg2.setBounds(703, 454, 292, 233);
		visitsFrame.add(visitsImg2);

		//Treatments
		
		treatmentsFrame = new JPanel();
		treatmentsFrame.setBackground(Color.WHITE);
		CardView.add(treatmentsFrame, "treatmentsFrame");
		treatmentsFrame.setLayout(null);

		//Initialize the Treatments table
		treatmentsModel = createTableModel(new String[] { "Serial Number", "Description" },
				new Class[] { Integer.class, String.class });
		table = new JTable(treatmentsModel);
		populateTable(treatmentsModel, hosp.getTreatments(), new Object[][] { { Integer.class, String.class } });

		//Initialize the Treatments scroll pane
		treatmentSP = new JScrollPane(table);
		treatmentSP.setBounds(10, 75, 975, 414);
		treatmentsFrame.add(treatmentSP);

		JLabel treatmentsImg1 = new JLabel("");
		treatmentsImg1
				.setIcon(new ImageIcon(BasicWindow.class.getResource("/resources/Blood donation-rafiki (1) (2).png")));
		treatmentsImg1.setBounds(0, 449, 321, 271);
		treatmentsFrame.add(treatmentsImg1);

		JLabel treatmentsImg2 = new JLabel("");
		treatmentsImg2.setIcon(new ImageIcon(BasicWindow.class.getResource("/resources/CT scan-pana (1).png")));
		treatmentsImg2.setBounds(689, 434, 321, 271);
		treatmentsFrame.add(treatmentsImg2);

		//Medications
		
		medicationsFrame = new JPanel();
		medicationsFrame.setBackground(Color.WHITE);
		CardView.add(medicationsFrame, "medicationsFrame");
		medicationsFrame.setLayout(null);

		////Initialize the Medications table
		medicationsModel = createTableModel(new String[] { "Code", "Name", "Dosage", "Number of doses" },
				new Class[] { Integer.class, String.class, Double.class, Integer.class });
		table = new JTable(medicationsModel);
		populateTable(medicationsModel, hosp.getMedications(),
				new Object[][] { { Integer.class, String.class, Double.class, Integer.class } });

		//Initialize the Medications scroll pane
		medicationSP = new JScrollPane(table);
		medicationSP.setBounds(10, 67, 759, 531);
		medicationsFrame.add(medicationSP);

		JLabel medicationsImg = new JLabel("");
		medicationsImg.setIcon(new ImageIcon(BasicWindow.class.getResource("/resources/Medicine-bro (1) (2).png")));
		medicationsImg.setBounds(761, 392, 294, 271);
		medicationsFrame.add(medicationsImg);

		//Doctors
		
		doctorsFrame = new JPanel();
		doctorsFrame.setBackground(Color.WHITE);
		CardView.add(doctorsFrame, "doctorsFrame");
		doctorsFrame.setLayout(null);

		//Initialize the Doctors table
		DefaultTableModel doctorsModel = createTableModel(
				new String[] { "ID", "First name", "Last name", "Date of birth", "Address", "Phone number", "Email",
						"Gender", "Started Working", "Salary", "License Number", "Finished internship",
						"Intensive Care", "Specialization" },
				new Class[] { Integer.class, String.class, String.class, Date.class, String.class, String.class,
						String.class, String.class, Date.class, Double.class, Integer.class, Boolean.class,
						Boolean.class, Specialization.class });
		table = new JTable(doctorsModel);
		populateTable(doctorsModel, doctorsHashMap,
				new Object[][] { { Integer.class, String.class, String.class, Date.class, String.class, String.class,
						String.class, String.class, Date.class, Double.class, Integer.class, Boolean.class,
						Boolean.class, Specialization.class } });

		////Initialize the Doctors scroll pane
		doctorSP = new JScrollPane(table);
		doctorSP.setBounds(10, 67, 855, 567);
		doctorsFrame.add(doctorSP);

		JLabel doctorImg = new JLabel("");
		doctorImg.setIcon(
				new ImageIcon(BasicWindow.class.getResource("/resources/doctorPointing-removebg-preview (2).png")));
		doctorImg.setBounds(691, 260, 398, 461);
		doctorsFrame.add(doctorImg);

		//Nurses
		
		nursesFrame = new JPanel();
		nursesFrame.setBackground(Color.WHITE);
		CardView.add(nursesFrame, "nursesFrame");
		nursesFrame.setLayout(null);

		//Initialize the Nurses table
		DefaultTableModel nursesModel = createTableModel(
				new String[] { "ID", "First name", "Last name", "Date of birth", "Address", "Phone number", "Email",
						"Gender", "Started Working", "Salary", "License Number", "Intensive Care" },
				new Class[] { Integer.class, String.class, String.class, Date.class, String.class, String.class,
						String.class, String.class, Date.class, Double.class, Integer.class, Boolean.class });
		table = new JTable(nursesModel);
		populateTable(nursesModel, nursesHashMap,
				new Object[][] { { Integer.class, String.class, String.class, Date.class, String.class, String.class,
						String.class, String.class, Date.class, Double.class, Integer.class, Boolean.class } });

		//Initialize the Nurses scroll pane
		nurseSP = new JScrollPane(table);
		nurseSP.setBounds(10, 67, 848, 567);
		nursesFrame.add(nurseSP);

		JLabel nurseImg = new JLabel("");
		nurseImg.setIcon(new ImageIcon(BasicWindow.class.getResource("/resources/nurseTable-removebg-preview.png")));
		nurseImg.setBounds(870, 240, 185, 482);
		nursesFrame.add(nurseImg);

		// QUERIES FRAME
		queriesFrame = new JPanel();
		queriesFrame.setBackground(Color.WHITE);
		CardView.add(queriesFrame, "queriesFrame");
		queriesFrame.setLayout(null);

		JLabel queriesMainLabel = new JLabel("What would you like to do?");
		queriesMainLabel.setFont(titleFont);
		queriesMainLabel.setBounds(330, 42, 320, 64);
		queriesFrame.add(queriesMainLabel);
		
		/******** BUTTON TO COUNT PATIENTS WHO VISITED BEFORE A SPECIFIC DATE ******/
		
		howManyVisitBeforeButton = styleButton(new JButton("Count Patients who Visited Before Date"), new Color(255, 255, 255), Color.WHITE);
		howManyVisitBeforeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		howManyVisitBeforeButton.setBounds(45, 180, 287, 100); // Set the position and size of the button
		queriesFrame.add(howManyVisitBeforeButton); // Add the button to the queries frame
		howManyVisitBeforeButton.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        boolean validDateSelected = false;

		        // Loop until a valid date is selected or the user cancels the operation
		        while (!validDateSelected) {
		            JLabel label = new JLabel("Please select a date:");
		            JCalendar calendar = new JCalendar(); // Calendar widget for date selection
		            calendar.setDate(Hospital.TODAY); // Set the initial date to today
		            JPanel panel = new JPanel(new BorderLayout());
		            panel.add(label, BorderLayout.NORTH);
		            panel.add(calendar, BorderLayout.CENTER);

		            // Show a dialog for the user to select a date
		            int option = JOptionPane.showConfirmDialog(null, panel, "Select Date", JOptionPane.OK_CANCEL_OPTION,
		                    JOptionPane.QUESTION_MESSAGE);

		            if (option == JOptionPane.OK_OPTION) {
		                try {
		                    Date selectedDate = calendar.getDate();

		                    if (selectedDate.after(Hospital.TODAY)) {
		                        throw new FutureDateException(selectedDate); // Custom exception if a future date is selected
		                    }

		                    validDateSelected = true;

		                    // Count the number of patients who visited before the selected date
		                    int count = hosp.howManyVisitBefore(selectedDate);

		                    JPanel resultPanel = new JPanel(new BorderLayout());
		                    JLabel resultLabel = new JLabel(
		                            "Number of patients who visited before selected date: " + count);

		                    resultPanel.add(resultLabel);

		                    // Display the result in a dialog
		                    JOptionPane.showMessageDialog(null, resultPanel, "Patient Count",
		                            JOptionPane.INFORMATION_MESSAGE);

		                } catch (FutureDateException x) {
		                    // Handle the case where a future date is selected
		                    JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		                }
		            } else {
		                break; // Exit the loop if the user cancels the operation
		            }
		        }
		    }
		});

		/******** BUTTON TO COUNT MEDICATIONS BY DOSAGE RANGE ******/

		countMedicationsButton = styleButton(new JButton("Count Medication by Dosage Range"), new Color(255, 255, 255), Color.WHITE);
		countMedicationsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		countMedicationsButton.setBounds(379, 180, 260, 100); // Set the position and size of the button
		queriesFrame.add(countMedicationsButton); // Add the button to the queries frame
		countMedicationsButton.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        boolean validInput = false;

		        // Loop until valid dosage input is provided or the user cancels the operation
		        while (!validInput) {
		            JLabel minDosageLabel = new JLabel("Minimum Dosage:");
		            JTextField minDosageField = new JTextField(10); // Text field for minimum dosage input

		            JLabel maxDosageLabel = new JLabel("Maximum Dosage:");
		            JTextField maxDosageField = new JTextField(10); // Text field for maximum dosage input

		            JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10)); // Layout for input fields
		            inputPanel.add(minDosageLabel);
		            inputPanel.add(minDosageField);
		            inputPanel.add(maxDosageLabel);
		            inputPanel.add(maxDosageField);

		            // Show a dialog for the user to enter dosage range
		            int option = JOptionPane.showConfirmDialog(null, inputPanel, "Enter Dosage Range",
		                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

		            if (option == JOptionPane.OK_OPTION) {
		                try {
		                    // Parse the dosage inputs as doubles
		                    double minDosage = Double.parseDouble(minDosageField.getText());
		                    double maxDosage = Double.parseDouble(maxDosageField.getText());

		                    if (minDosage < 0) {
		                        throw new NegativeDosageException(minDosage); // Custom exception if a negative dosage is entered
		                    }
		                    if (maxDosage < 0) {
		                        throw new NegativeDosageException(maxDosage); // Custom exception if a negative dosage is entered
		                    }

		                    validInput = true;

		                    // Count the number of medications within the specified dosage range
		                    int count = hosp.countMedications(minDosage, maxDosage);

		                    JPanel resultPanel = new JPanel(new BorderLayout());
		                    JLabel countLabel = new JLabel("Count of Medications: " + count);
		                    resultPanel.add(countLabel);

		                    // Display the result in a dialog
		                    JOptionPane.showMessageDialog(null, resultPanel, "Dosage Range Results",
		                            JOptionPane.INFORMATION_MESSAGE);

		                } catch (NegativeDosageException x) {
		                    // Handle the case where a negative dosage is entered
		                    JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
		                } catch (NumberFormatException x) {
		                    // Handle invalid input that cannot be parsed as a double
		                    JOptionPane.showMessageDialog(null, "Wrong Input!", "Error!", JOptionPane.ERROR_MESSAGE);
		                }
		            } else {
		                break; // Exit the loop if the user cancels the operation
		            }
		        }
		    }
		});

		/******** BUTTON TO COUNT DOCTORS BY SPECIALIZATION ******/

		countDocsSpecButton = styleButton(new JButton("Count Doctors by Specialization"), new Color(255, 255, 255), Color.WHITE);
		countDocsSpecButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		countDocsSpecButton.setBounds(698, 180, 250, 100); // Set the position and size of the button
		queriesFrame.add(countDocsSpecButton); // Add the button to the queries frame
		countDocsSpecButton.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // Create a combo box for selecting a specialization
		        JComboBox<Specialization> specializations = new JComboBox<>(Specialization.values());
		        JPanel inputPanel = new JPanel();
		        inputPanel.add(new JLabel("Select Specialization:"));
		        inputPanel.add(specializations);

		        // Show a dialog for the user to select a specialization
		        int option = JOptionPane.showConfirmDialog(null, inputPanel, "Select Specialization",
		                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

		        if (option == JOptionPane.OK_OPTION) {
		            // Get the selected specialization from the combo box
		            Specialization selectedSpecialization = (Specialization) specializations.getSelectedItem();

		            // Retrieve the number of doctors for the selected specialization
		            HashMap<Specialization, Integer> doctorCountMap = hosp.getNumberOfDoctorsBySpecialization();
		            int doctorCount = doctorCountMap.getOrDefault(selectedSpecialization, 0);

		            JPanel resultPanel = new JPanel(new BorderLayout());
		            JLabel resultLabel = new JLabel(
		                    "Number of Doctors in " + selectedSpecialization + ": " + doctorCount);
		            resultPanel.add(resultLabel);

		            // Display the result in a dialog
		            JOptionPane.showMessageDialog(null, resultPanel, "Doctors by Specialization",
		                    JOptionPane.INFORMATION_MESSAGE);
		        }
		    }
		});

		/******** BUTTON TO EVALUATE STAFF AND COMPLIANCE ******/

		staffAndComplianceButton = styleButton(new JButton("Evaluate Staff and Compliance"), new Color(255, 255, 255), Color.WHITE);
		staffAndComplianceButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		staffAndComplianceButton.setBounds(218, 375, 250, 100); // Set the position and size of the button
		queriesFrame.add(staffAndComplianceButton); // Add the button to the queries frame
		staffAndComplianceButton.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        JPanel resultPanel = new JPanel(new BorderLayout());

		        // Display the number of intensive care staff members
		        JLabel intensiveCount = new JLabel(
		                "The number of Intensive Care staff members is: " + hosp.howManyIntensiveCareStaffMembers());
		        resultPanel.add(intensiveCount, BorderLayout.NORTH);

		        // Display the average salary of staff members
		        JLabel avgSalary = new JLabel("The average salary of staff members is: " + hosp.avgSalary());
		        resultPanel.add(avgSalary, BorderLayout.CENTER);

		        // Display whether the hospital complies with the ministry of health standard
		        JLabel isComplies;
		        if (hosp.isCompliesWithTheMinistryOfHealthStandard()) {
		            isComplies = new JLabel("The hospital complies with the ministry of health standard");
		        } else {
		            isComplies = new JLabel("The hospital doesn't comply with the ministry of health standard");
		        }
		        resultPanel.add(isComplies, BorderLayout.SOUTH);

		        // Show the result in a dialog
		        JOptionPane.showMessageDialog(null, resultPanel, "Evaluate Staff and Compliance",
		                JOptionPane.INFORMATION_MESSAGE);
		    }
		});

		/******** BUTTON TO APPOINT A NEW DEPARTMENT MANAGER ******/

		newDepManagerButton = styleButton(new JButton("Appoint New Department Manager"), new Color(255, 255, 255), Color.WHITE);
		newDepManagerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		newDepManagerButton.setBounds(531, 375, 270, 100); // Set the position and size of the button
		queriesFrame.add(newDepManagerButton); // Add the button to the queries frame
		newDepManagerButton.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        // Create a panel and JComboBox for selecting a department
		        JPanel inputPanel = new JPanel(new BorderLayout());
		        JLabel label = new JLabel("Choose a department:");

		        // Populate JComboBox with department IDs from the hospital's department list
		        JComboBox<Integer> departments = new JComboBox<>(
		                hosp.getDepartments().keySet().stream().toArray(Integer[]::new));

		        inputPanel.add(label, BorderLayout.NORTH);
		        inputPanel.add(departments, BorderLayout.CENTER);

		        // Show a dialog for the user to select a department
		        int option = JOptionPane.showConfirmDialog(null, inputPanel, "Appoint a New Manager",
		                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

		        if (option == JOptionPane.OK_OPTION) {
		            // Display a warning message before proceeding with the appointment
		            String warningMessage = "You are about to appoint a new department manager.\n\n"
		                    + "The following actions will occur if you proceed:\n"
		                    + "1. The new manager will be the most senior doctor in the department who has completed an internship and has a\n"
		                    + "specialization matching the department's specialization.\n"
		                    + "2. The current manager, if one exists, will be removed from the system.\n"
		                    + "3. The new manager will receive a salary increase of 5,000.\n\n"
		                    + "Are you sure you want to proceed?";

		            int confirm = JOptionPane.showConfirmDialog(null, warningMessage, "Warning",
		                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

		            if (confirm == JOptionPane.OK_OPTION) {
		                // Retrieve the selected department by its ID
		                int selectedDepartmentId = (int) departments.getSelectedItem();
		                Department selectedDepartment = hosp.getDepartments().get(selectedDepartmentId);

		                // Call the method to appoint a new manager for the selected department
		                Doctor newManager = hosp.AppointANewManager(selectedDepartment);

		                // Display a confirmation message if a new manager is successfully appointed
		                JPanel confirmationPanel = new JPanel(new BorderLayout());

		                if (newManager != null) {
		                    JLabel confirmationLabel = new JLabel(
		                            "Successfully appointed new manager for department " + selectedDepartmentId);
		                    JLabel newDoctorLabel = new JLabel(
		                            "The new manager is " + newManager.getFirstName() + " " + newManager.getLastName());
		                    confirmationPanel.add(confirmationLabel, BorderLayout.NORTH);
		                    confirmationPanel.add(newDoctorLabel, BorderLayout.CENTER);

		                    // Serialize the updated hospital data and update the department table
		                    serialize(hosp);
		                    updateTable(writeDepartment(), departmentSP);

		                } else {
		                    // Display an error message if no suitable doctor is found for appointment
		                    JLabel errorLabel = new JLabel("No suitable doctor found for appointment.");
		                    confirmationPanel.add(errorLabel, BorderLayout.CENTER);
		                }

		                // Show the confirmation or error message in a dialog
		                JOptionPane.showMessageDialog(null, confirmationPanel, "Appoint a New Manager",
		                        JOptionPane.INFORMATION_MESSAGE);
		            }
		        }
		    }
		});

		// Add hover effects to various buttons in the queries frame
		addHoverEffect(howManyVisitBeforeButton);
		addHoverEffect(newDepManagerButton);
		addHoverEffect(staffAndComplianceButton);
		addHoverEffect(countDocsSpecButton);
		addHoverEffect(countMedicationsButton);


		/******** REMOVE ELEMENTS ******/
		
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int selectedRow = table.getSelectedRow();
					if (selectedRow < 0) {//If no cell is selected, show an error message
						throw new NoCellSelectedException();
					}

					DefaultTableModel model = (DefaultTableModel) table.getModel();
					int result;

					// Remove Department
					if (lastTableOpened == departmentsFrame) { 
						result = JOptionPane.showConfirmDialog(null,
								"Are you sure you want to remove department " + table.getValueAt(selectedRow, 0) + "?",
								"Confirm Removal", JOptionPane.YES_NO_OPTION);
						if (result == JOptionPane.YES_OPTION) {//Confirm removal
							Department toRemove = hosp.getDepartments().get(table.getValueAt(selectedRow, 0));//Find the Department to remove
							hosp.removeDepartment(toRemove);//Remove the Department from the System
							model.removeRow(selectedRow);//Remove the Department from the table
							serialize(hosp);//Update the table after changes
							updateTable(writeDepartment(), departmentSP);
							JOptionPane.showMessageDialog(null, "Successfully removed Department");
						}
					}

					// Remove Medical Problem
					if (lastTableOpened == medicalProblemsFrame) { 
						switch (chooseTable.getSelectedItem().toString()) {
						
						// Remove Injury
						case "Injuries": 
							result = JOptionPane.showConfirmDialog(null,
									"Are you sure you want to remove Injury " + table.getValueAt(selectedRow, 0) + "?",
									"Confirm Removal", JOptionPane.YES_NO_OPTION);
							if (result == JOptionPane.YES_OPTION) {//Confirm removal
								Injury toRemove = (Injury) hosp.getMedicalProblems()
										.get(table.getValueAt(selectedRow, 0));//Find the Injury to remove
								injuriesHashMap.remove(table.getValueAt(selectedRow, 0));//Remove the Injury from the System
								hosp.removeInjury(toRemove);
								model.removeRow(selectedRow);//Remove the Injury from the table
								serialize(hosp);//Update the table after changes
								updateTable(writeInjury(), injuriesSP);
								JOptionPane.showMessageDialog(null, "Successfully removed Injury");
							}
							break;

						//Remove Fracture
						case "Fractures": 
							result = JOptionPane
									.showConfirmDialog(null,
											"Are you sure you want to remove Fracture "
													+ table.getValueAt(selectedRow, 0) + "?",
											"Confirm Removal", JOptionPane.YES_NO_OPTION);
							if (result == JOptionPane.YES_OPTION) {//Confirm removal
								Fracture toRemove = (Fracture) hosp.getMedicalProblems()
										.get(table.getValueAt(selectedRow, 0));//Find the Fracture to remove
								fracturesHashMap.remove(table.getValueAt(selectedRow, 0));//Remove the Fracture from the System
								hosp.removeFracture(toRemove);
								model.removeRow(selectedRow);//Remove the Fracture from the table
								serialize(hosp);//Update the table after changes
								updateTable(writeFracture(), fracturesSP);
								JOptionPane.showMessageDialog(null, "Successfully removed Fracture");
							}
							break;

						// Remove Disease
						case "Diseases": 
							result = JOptionPane.showConfirmDialog(null,
									"Are you sure you want to remove Disease " + table.getValueAt(selectedRow, 0) + "?",
									"Confirm Removal", JOptionPane.YES_NO_OPTION);
							if (result == JOptionPane.YES_OPTION) {//Confirm removal
								Disease toRemove = (Disease) hosp.getMedicalProblems()
										.get(table.getValueAt(selectedRow, 0));//Find the Disease to remove
								diseasesHashMap.remove(table.getValueAt(selectedRow, 0));//Remove the Disease from the System
								hosp.removeDisease(toRemove);
								model.removeRow(selectedRow);//Remove the Disease from the table
								serialize(hosp);//Update the table after changes
								updateTable(writeDisease(), diseasesSP);
								JOptionPane.showMessageDialog(null, "Successfully removed Disease");
							}
							break;
						}
					}
					
					//Remove Patient
					if (lastTableOpened == patientsFrame) { 
						result = JOptionPane.showConfirmDialog(null,
								"Are you sure you want to remove Patient " + table.getValueAt(selectedRow, 0) + "?",
								"Confirm Removal", JOptionPane.YES_NO_OPTION);
						if (result == JOptionPane.YES_OPTION) {//Confirm removal
							Patient toRemove = hosp.getPatients().get(table.getValueAt(selectedRow, 0));//Find the Patient to remove
							hosp.removePatient(toRemove);//Remove the Patient from the System
							model.removeRow(selectedRow);//Remove the Patient from the table
							serialize(hosp);//Update the table after changes
							updateTable(writePatient(), patientSP);
							JOptionPane.showMessageDialog(null, "Successfully removed Patient");
						}
					}

					if (lastTableOpened == visitsFrame) { // Remove Visit
						result = JOptionPane.showConfirmDialog(null,
								"Are you sure you want to remove Visit " + table.getValueAt(selectedRow, 0) + "?",
								"Confirm Removal", JOptionPane.YES_NO_OPTION);
						if (result == JOptionPane.YES_OPTION) {//Confirm removal
							Visit toRemove = hosp.getVisits().get(table.getValueAt(selectedRow, 0));//Find the Visit to remove
							hosp.removeVisit(toRemove);//Remove the Visit from the System
							model.removeRow(selectedRow);//Remove the Visit from the table
							serialize(hosp);//Update the table after changes
							updateTable(writeVisit(), visitSP);
							JOptionPane.showMessageDialog(null, "Successfully removed Visit");
						}
					}
					
					//Remove Treatment
					if (lastTableOpened == treatmentsFrame) { 
						result = JOptionPane.showConfirmDialog(null,
								"Are you sure you want to remove treatment " + table.getValueAt(selectedRow, 0) + "?",
								"Confirm Removal", JOptionPane.YES_NO_OPTION);
						if (result == JOptionPane.YES_OPTION) {//Confirm removal
							Treatment toRemove = hosp.getTreatments().get(table.getValueAt(selectedRow, 0));//Find the Treatment to remove
							hosp.removeTreatment(toRemove);//Remove the Treatment from the System
							model.removeRow(selectedRow);//Remove the Treatment from the table
							serialize(hosp);//Update the table after changes
							updateTable(writeTreatment(), treatmentSP);
							JOptionPane.showMessageDialog(null, "Successfully removed Treatment");
						}
					}

					//Remove Doctor
					if (lastTableOpened == doctorsFrame) { 
						result = JOptionPane.showConfirmDialog(null,
								"Are you sure you want to remove doctor " + table.getValueAt(selectedRow, 0) + "?",
								"Confirm Removal", JOptionPane.YES_NO_OPTION);
						if (result == JOptionPane.YES_OPTION) {//Confirm removal
							Doctor toRemove = (Doctor) hosp.getStaffMembers().get(table.getValueAt(selectedRow, 0));//Find the Doctor to remove
							doctorsHashMap.remove(table.getValueAt(selectedRow, 0));//Remove the Doctor from the System
							hosp.removeDoctor(toRemove);
							model.removeRow(selectedRow);//Remove the Doctor from the table
							serialize(hosp);//Update the table after changes
							updateTable(writeDoctor(), doctorSP);
							JOptionPane.showMessageDialog(null, "Successfully removed Doctor");
						}
					}

					//Remove Nurse
					if (lastTableOpened == nursesFrame) { 
						result = JOptionPane.showConfirmDialog(null,
								"Are you sure you want to remove nurse " + table.getValueAt(selectedRow, 0) + "?",
								"Confirm Removal", JOptionPane.YES_NO_OPTION);
						if (result == JOptionPane.YES_OPTION) {//Confirm removal
							Nurse toRemove = (Nurse) hosp.getStaffMembers().get(table.getValueAt(selectedRow, 0));//Find the Nurse to remove
							nursesHashMap.remove(table.getValueAt(selectedRow, 0));//Remove the Nurse from the System
							hosp.removeNurse(toRemove);
							model.removeRow(selectedRow);//Remove the Nurse from the table
							serialize(hosp);//Update the table after changes
							updateTable(writeNurse(), nurseSP);
							JOptionPane.showMessageDialog(null, "Successfully removed Nurse");
						}
					}

					//Remove Medication
					if (lastTableOpened == medicationsFrame) { 
						result = JOptionPane.showConfirmDialog(null,
								"Are you sure you want to remove medication " + table.getValueAt(selectedRow, 0) + "?",
								"Confirm Removal", JOptionPane.YES_NO_OPTION);
						if (result == JOptionPane.YES_OPTION) {//Confirm removal
							Medication toRemove = hosp.getMedications().get(table.getValueAt(selectedRow, 0));//Find the Medication to remove
							hosp.removeMedication(toRemove);//Remove the Medication from the System
							model.removeRow(selectedRow);//Remove the Medication from the table
							serialize(hosp);//Update the table after changes
							updateTable(writeMedication(), medicationSP);
							JOptionPane.showMessageDialog(null, "Successfully removed Medication");
						}
					}

				} catch (NoCellSelectedException x) {
					JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// ************ EDIT ELEMENTS ***************//

		editButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int selectedRow = table.getSelectedRow();
					int selectedCollum = table.getSelectedColumn();
					if (selectedRow < 0 || selectedCollum < 0)//If no Cell is selected, show an error message
						throw new NoCellSelectedException();
					else {
						if (selectedCollum == 0)//If the cell selected is of the Primary Key, show an error message
							throw new ChangePrimaryKeyException();
						else {
							
							// Edit Department Elements
							if (lastTableOpened == departmentsFrame)
							{
								switch (selectedCollum) {
								//Edit Department Name
								case 1: {
									String newName = JOptionPane.showInputDialog(null, "Please enter a new Name");
									if (newName != null) {
										if (!hosp.getDepartments().get((selectedRow + 1)).getName().equals(newName)) {
											hosp.getDepartments().get(selectedRow + 1).setName(newName);//Set the name for the Department
											serialize(hosp);//Update the table after changes
											updateTable(writeDepartment(), departmentSP);
											JOptionPane.showMessageDialog(null,
													"Successfully changed Department's name!");
										} else {//If the same value is given, show an error message
											throw new ChangeToSameValueException();
										}
									}

									break;
								}

								//If the Department Manager is selected to be edited, show an error message
								case 2: {
									JOptionPane.showMessageDialog(null,
											"Can't change a Manager on this window.\nIf you wish to change a department manager, refer to Appoint a New Manager option in 'More Options'.",
											"Error!", JOptionPane.ERROR_MESSAGE);
									break;
								}
								
								//Edit Department Location
								case 3: {
									String newLocation = JOptionPane.showInputDialog(null,
											"Please enter a new Location");
									if (newLocation != null) {
										if (!hosp.getDepartments().get((selectedRow + 1)).getLocation()
												.equals(newLocation)) {
											hosp.getDepartments().get(selectedRow + 1).setLocation(newLocation);
											serialize(hosp);//Update the table after changes
											updateTable(writeDepartment(), departmentSP);
											JOptionPane.showMessageDialog(null,
													"Successfully changed Department's location!");
										} else {//If the same value is given, show an error message
											throw new ChangeToSameValueException();
										}
									}

									break;
								}
								
								//Edit Department Specializations
								case 4: {
									JComboBox<Specialization> specializations = new JComboBox<Specialization>(
											Specialization.values());//Create a Combo Box of all Specializations
									int result = JOptionPane.showConfirmDialog(null, specializations,
											"Please select a new Specialization", JOptionPane.OK_CANCEL_OPTION,
											JOptionPane.QUESTION_MESSAGE);
									if (result == JOptionPane.OK_OPTION) {//Confirm edit
										Specialization newSpecialization = (Specialization) specializations
												.getSelectedItem();//Find the Specialization to change the Department to
										if (!hosp.getDepartments().get(selectedRow + 1).getSpecialization()
												.equals(newSpecialization)) {
											hosp.getDepartments().get(selectedRow + 1)//Set the Department Specialization
													.setSpecialization(newSpecialization);
											serialize(hosp);//Update the table after changes
											updateTable(writeDepartment(), departmentSP);
											JOptionPane.showMessageDialog(null,
													"Successfully changed Department's specialization!");
										} else {//If the same value is given, show an error message
											throw new ChangeToSameValueException();
										}
									}
									break;
								}
								}
							}
							
							//Edit Treatment Elements
							if (lastTableOpened == treatmentsFrame)
							{
								switch (selectedCollum) {
								
								//Edit Treatment Description
								case 1: {
									String newDescription = JOptionPane.showInputDialog(null,
											"Please enter a new Description");
									if (newDescription != null) {
										if (!hosp.getTreatments().get(table.getValueAt(selectedRow, 0)).getDescription()
												.equals(newDescription)) {
											hosp.getTreatments().get(table.getValueAt(selectedRow, 0)).setDescription(newDescription);//Set the Treatment Description
											serialize(hosp);//Update the table after changes
											updateTable(writeTreatment(), treatmentSP);
											JOptionPane.showMessageDialog(null,
													"Successfully changed Treatment's Description!");
										} else {//If the same value is given, show an error message
											throw new ChangeToSameValueException();
										}
									}

									break;
								}
								}
							}
							
							//Edit Medical Problem Elements
							if (lastTableOpened == medicalProblemsFrame)
							{
								switch (chooseTable.getSelectedItem().toString()) {
								
								//Edit Injury
								case "Injuries":
								{
									switch (selectedCollum) {
									//Edit Injury Recovery Time
									case 3: {
										try {
											String newRecoveryTimeStr = JOptionPane.showInputDialog(null,
													"Please enter a new Recovery Time");
											if (newRecoveryTimeStr != null) {//Check for valid number
												double newRecoveryTime = Double.parseDouble(newRecoveryTimeStr);
												if (newRecoveryTime < 0)
													throw new InvalidCommonRecoveryTimeException(newRecoveryTime);
												else {
													if (injuriesHashMap.get(table.getValueAt(selectedRow, 0))
															.getCommonRecoveryTime() != newRecoveryTime) {
														injuriesHashMap.get(table.getValueAt(selectedRow, 0))
																.setCommonRecoveryTime(newRecoveryTime);//Set the Injury Recovery Time
														serialize(hosp);//Update the table after changes
														updateTable(writeInjury(), injuriesSP);
														JOptionPane.showMessageDialog(null,
																"Successfully changed Medical Problem's Recovery Time!");
													} else {//If the same value is given, show an error message
														throw new ChangeToSameValueException();
													}
												}
											}
										} catch (NumberFormatException x) {
											JOptionPane.showMessageDialog(null, "Wrong Input!", "Error!",
													JOptionPane.ERROR_MESSAGE);

										} catch (InvalidCommonRecoveryTimeException x) {
											JOptionPane.showMessageDialog(null, x.getMessage(), "Error!",
													JOptionPane.ERROR_MESSAGE);
										}
										break;
									}
									//Edit Injury Location
									case 4: {
										String newLocation = JOptionPane.showInputDialog(null,
												"Please enter a new Location");
										if (newLocation != null) {
											if (!injuriesHashMap.get(table.getValueAt(selectedRow, 0)).getLocation()
													.equals(newLocation)) {
												injuriesHashMap.get(table.getValueAt(selectedRow, 0))
														.setLocation(newLocation);//Set the Injury Location
												serialize(hosp);//Update the table after changes
												updateTable(writeInjury(), injuriesSP);
												JOptionPane.showMessageDialog(null,
														"Successfully changed Medical Problem's Location!");
											} else {//If the same value is given, show an error message
												throw new ChangeToSameValueException();
											}
										}
										break;
									}
									//For any other Value to change, Change it with the "editMedicalProblem" Method
									default: {
										editMedicalProblem(selectedRow, selectedCollum, injuriesHashMap);
										serialize(hosp);//Update the table after changes
										updateTable(writeInjury(), injuriesSP);
									}
									}
									break;
								}
								
								//Edit Fracture
								case "Fractures":
								{
									switch (selectedCollum) {
									//Edit Fracture Location
									case 3: {
										String newLocation = JOptionPane.showInputDialog(null,
												"Please enter a new Location");
										if (newLocation != null) {
											if (!fracturesHashMap.get(table.getValueAt(selectedRow, 0)).getLocation()
													.equals(newLocation)) {
												fracturesHashMap.get(table.getValueAt(selectedRow, 0))
														.setLocation(newLocation);//Set the Fracture Location
												serialize(hosp);//Update the table after changes
												updateTable(writeFracture(), fracturesSP);
												JOptionPane.showMessageDialog(null,
														"Successfully changed Medical Problem's Location!");
											} else {//If the same value is given, show an error message
												throw new ChangeToSameValueException();
											}
										}
										break;
									}
									//Edit Fracture Required Cast
									case 4: {
										int result = JOptionPane.showConfirmDialog(null,
												"Are you sure you want to change the Requires Cast?",
												"Confirm Requires Cast change", JOptionPane.YES_NO_OPTION);
										if (result == JOptionPane.OK_OPTION) {//Confirm edit
											boolean newRequiredCast = (fracturesHashMap
													.get(table.getValueAt(selectedRow, 0)).isRequiresCast()) ? false
															: true;
											fracturesHashMap.get(table.getValueAt(selectedRow, 0))
													.setRequiresCast(newRequiredCast);//Set the Fracture Required Cast
											serialize(hosp);//Update the table after changes
											updateTable(writeFracture(), fracturesSP);
											JOptionPane.showMessageDialog(null,
													"Successfully changed Medical Problem's Requires Cast!");
										}
										break;
									}
									//For any other Value to change, Change it with the "editMedicalProblem" Method
									default: {
										editMedicalProblem(selectedRow, selectedCollum, fracturesHashMap);
										serialize(hosp);
										updateTable(writeFracture(), fracturesSP);
									}
									}
									break;
								}
								
								//Edit Disease
								case "Diseases":
								{
									switch (selectedCollum) {
									//Set Disease Description
									case 3: {

										String newDescription = JOptionPane.showInputDialog(null,
												"Please enter a new Description");
										if (newDescription != null) {
											if (!diseasesHashMap.get(table.getValueAt(selectedRow, 0)).getDescription()
													.equals(newDescription)) {
												diseasesHashMap.get(table.getValueAt(selectedRow, 0))
														.setDescription(newDescription);//Set the Disease Description
												serialize(hosp);//Update the table after changes
												updateTable(writeDisease(), diseasesSP);
												JOptionPane.showMessageDialog(null,
														"Successfully changed Medical Problem's Description!");
											} else {//If the same value is given, show an error message
												throw new ChangeToSameValueException();
											}
										}
										break;

									}
									//For any other Value to change, Change it with the "editMedicalProblem" Method
									default: {
										editMedicalProblem(selectedRow, selectedCollum, diseasesHashMap);
										serialize(hosp);
										updateTable(writeDisease(), diseasesSP);
									}
									}
									break;
								}
								}
							}
							
							//Edit Medication Elements
							if (lastTableOpened == medicationsFrame)
							{
								switch (selectedCollum) {
								//Edit Medication Name
								case 1: {
									String newName = JOptionPane.showInputDialog(null, "Please enter a new Name");
									if (newName != null) {
										if (!hosp.getMedications().get(table.getValueAt(selectedRow, 0)).getName()
												.equals(newName)) {
											hosp.getMedications().get(table.getValueAt(selectedRow, 0))
													.setName(newName);//Set Medication Name
											serialize(hosp);//Update the table after changes
											updateTable(writeMedication(), medicationSP);
											JOptionPane.showMessageDialog(null,
													"Successfully changed Medication's Name!");
										} else {//If the same value is given, show an error message
											throw new ChangeToSameValueException();
										}
									}

									break;
								}
								//Edit Medication Dosage
								case 2: {
									try {
										String newDosageStr = JOptionPane.showInputDialog(null,
												"Please enter a new Dosage");
										if (newDosageStr != null) {
											double newDosage = Double.parseDouble(newDosageStr);
											if (hosp.getMedications().get(table.getValueAt(selectedRow, 0))
													.getDosage() != newDosage) {
												hosp.getMedications().get(table.getValueAt(selectedRow, 0))
														.setDosage(newDosage);//Set Medication Dosage
												serialize(hosp);//Update the table after changes
												updateTable(writeMedication(), medicationSP);
												JOptionPane.showMessageDialog(null,
														"Successfully changed Medication's Dosage!");
											} else {//If the same value is given, show an error message
												throw new ChangeToSameValueException();
											}
										}
									} catch (NegativeDosageException x) {//Check for valid Dosage number
										JOptionPane.showMessageDialog(null, x.getMessage(), "Error!",
												JOptionPane.ERROR_MESSAGE);
									} catch (NumberFormatException x) {
										JOptionPane.showMessageDialog(null, "Wrong Input!", "Error!",
												JOptionPane.ERROR_MESSAGE);
									}
									break;
								}
								//Edit Medication Doses Number
								case 3: {
									try {
										String newDosesNumStr = JOptionPane.showInputDialog(null,
												"Please enter a new Doses Number");
										if (newDosesNumStr != null) {
											int newDosesNum = Integer.parseInt(newDosesNumStr);
											if (hosp.getMedications().get(table.getValueAt(selectedRow, 0))
													.getNumberOfDose() != newDosesNum) {
												hosp.getMedications().get(table.getValueAt(selectedRow, 0))
														.setNumberOfDose(newDosesNum);//Set Medication Doses Number
												serialize(hosp);//Update the table after changes
												updateTable(writeMedication(), medicationSP);
												JOptionPane.showMessageDialog(null,
														"Successfully changed Medication's Doses NUmber!");
											} else {//If the same value is given, show an error message
												throw new ChangeToSameValueException();
											}
										}
									} catch (NegativeNumberOfDosesException x) {//Check for valid Doses number
										JOptionPane.showMessageDialog(null, x.getMessage(), "Error!",
												JOptionPane.ERROR_MESSAGE);
									} catch (NumberFormatException x) {
										JOptionPane.showMessageDialog(null, "Wrong Input!", "Error!",
												JOptionPane.ERROR_MESSAGE);
									}
									break;
								}

								}
							}

							//Edit Visit Elements
							if (lastTableOpened == visitsFrame) {
								Visit v = hosp.getVisits().get(table.getValueAt(selectedRow, 0));//Find the Visit to change
								switch (selectedCollum) {
								//Edit Visit Patient
								case 1: {
									JComboBox<Integer> patients = new JComboBox<Integer>(
											hosp.getPatients().keySet().toArray(Integer[]::new));//Create a Combo Box of all Patients
									int result = JOptionPane.showConfirmDialog(null, patients,
											"Please select a new Patient", JOptionPane.OK_CANCEL_OPTION,
											JOptionPane.QUESTION_MESSAGE);
									if (result == JOptionPane.OK_OPTION) {//Confirm Change
										Patient newPatient = hosp.getRealPatient((Integer) patients.getSelectedItem());//Find the Patient to Change the Visit to
										Patient oldPatient = v.getPatient();
										if (!v.getPatient().equals(newPatient)) {
											v.setPatient(newPatient);//Set the Visit Patient
											oldPatient.removeVisit(//Remove the Visit from the former Patient
													hosp.getVisits().get(table.getValueAt(selectedRow, 0)));
											newPatient.addVisit(hosp.getVisits().get(table.getValueAt(selectedRow, 0)));//Add the Visit to the new Patient
											serialize(hosp);//Update the table after changes
											updateTable(writeVisit(), visitSP);
											JOptionPane.showMessageDialog(null,
													"Successfully changed Visit's Patient!");
										} else {//If the same value is given, show an error message
											throw new ChangeToSameValueException();
										}
									}
									break;
								}
								//Edit Visit Start Date
								case 2: {
									try {
										JCalendar calendar = new JCalendar();
										calendar.setDate(v.getStartDate());//Create a Calendar to choose a new Start Date
										String message = "Please select a Start Date";
										SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
										Object[] params = { message, calendar };
										int result = JOptionPane.showConfirmDialog(null, params, "Start date",
												JOptionPane.PLAIN_MESSAGE);

										if (result == JOptionPane.OK_OPTION) {//Confirm change
											Date newStartDate = calendar.getDate();

											if (newStartDate.after(v.getEndDate())) {//Check for valid Start Date
												throw new InvalidDateException(v.getEndDate(), newStartDate);
											}

											String newDateStr = sdf.format(newStartDate);
											String currentDateStr = sdf.format(v.getStartDate());

											if (!newDateStr.equals(currentDateStr)) {
												v.setStartDate(newStartDate);//Set the Visit Start Date
												serialize(hosp);//Update the table after changes
												updateTable(writeVisit(), visitSP);
												JOptionPane.showMessageDialog(null,
														"Successfully changed Visit's start date!");
											} else {//If the same value is given, show an error message
												throw new ChangeToSameValueException();
											}
										}
										
									} catch (FutureDateException x) {
										JOptionPane.showMessageDialog(null, x.getMessage(), "Error!",
												JOptionPane.ERROR_MESSAGE);
									} catch (InvalidDateException x) {
										JOptionPane.showMessageDialog(null, x.getMessage(), "Error!",
												JOptionPane.ERROR_MESSAGE);
									} 
									
									
									

									break;

								}
								//Edit Visit End Date
								case 3: {
									try {
										JCalendar calendar = new JCalendar();
										calendar.setDate(v.getEndDate());//Create a Calendar to choose a new End Date
										String message = "Please select an End Date";
										SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
										Object[] params = { message, calendar };
										int result = JOptionPane.showConfirmDialog(null, params, "End date",
												JOptionPane.PLAIN_MESSAGE);

										if (result == JOptionPane.OK_OPTION) {//Confirm change
											Date newEndDate = calendar.getDate();

											if (newEndDate.before(v.getStartDate())) {//Check for valid End Date
												throw new InvalidDateException(v.getStartDate(), newEndDate);
											}

											if (v.getStartDate().after(newEndDate)) {
												throw new InvalidDateException(v.getStartDate(), newEndDate);
											}

											String newDateStr = sdf.format(newEndDate);
											String currentDateStr = sdf.format(v.getEndDate());

											if (!newDateStr.equals(currentDateStr)) {
												v.setEndDate(newEndDate);//Set the Visit End Date
												serialize(hosp);//Update the table after changes
												updateTable(writeVisit(), visitSP);
												JOptionPane.showMessageDialog(null,
														"Successfully changed Visit's end date!");
											} else {//If the same value is given, show an error message
												throw new ChangeToSameValueException();
											}
										}
									}catch (FutureDateException x) {
										JOptionPane.showMessageDialog(null, x.getMessage(), "Error!",
												JOptionPane.ERROR_MESSAGE);
									
									} catch (InvalidDateException x) {
										JOptionPane.showMessageDialog(null, x.getMessage(), "Error!",
												JOptionPane.ERROR_MESSAGE);
									} 

									break;

								}
								}
							}
							
							//Edit Patient Elements
							if (lastTableOpened == patientsFrame)
							{
								switch (selectedCollum) {
								//Edit Patient Birth Date
								case 3: {
									try {
										JCalendar calendar = new JCalendar();
										Patient p = hosp.getPatients().get(table.getValueAt(selectedRow, 0));
										calendar.setDate(p.getBirthDate());//Create a Calendar to choose a new Birth Date
										String message = "Please select a DOB";
										SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
										Object[] params = { message, calendar };
										int result = JOptionPane.showConfirmDialog(null, params, "DOB",
												JOptionPane.PLAIN_MESSAGE);
										if (result == JOptionPane.OK_OPTION) {//Confirm change
											String newDateStr = sdf.format(calendar.getDate());
											String currentDateStr = sdf.format(p.getBirthDate());
											if (!newDateStr.equals(currentDateStr)) {
												p.setBirthDate(calendar.getDate());//Set the Patient Birth Date
												serialize(hosp);//Update the table after changes
												updateTable(writePatient(), patientSP);
												JOptionPane.showMessageDialog(null,
														"Successfully changed Patient's DOB!");
											} else {//If the same value is given, show an error message
												throw new ChangeToSameValueException();
											}
										}
									} catch (FutureDateException x) {//Check for valid Birth Date
										JOptionPane.showMessageDialog(null, x.getMessage(), "Error!",
												JOptionPane.ERROR_MESSAGE);
									}

									break;
								}
								//Edit Patient Health Fund
								case 7: {
									JComboBox<HealthFund> healthFunds = new JComboBox<HealthFund>(HealthFund.values());//Create a Combo Box of all Health Funds
									int result = JOptionPane.showConfirmDialog(null, healthFunds,
											"Please select a new Health Fund", JOptionPane.OK_CANCEL_OPTION,
											JOptionPane.QUESTION_MESSAGE);
									if (result == JOptionPane.OK_OPTION) {//Confirm changes
										HealthFund newHealthFund = (HealthFund) healthFunds.getSelectedItem();//Find the Health Fund to change the Patient to
										if (!hosp.getPatients().get(table.getValueAt(selectedRow, 0)).getHealthFund()
												.equals(newHealthFund)) {
											hosp.getPatients().get(table.getValueAt(selectedRow, 0))
													.setHealthFund(newHealthFund);//Set the Patient Health Fund
											serialize(hosp);//Update the table after changes
											updateTable(writePatient(), patientSP);
											JOptionPane.showMessageDialog(null,
													"Successfully changed Patient's Health Fund!");
										} else {//If the same value is given, show an error message
											throw new ChangeToSameValueException();
										}
									}
									break;
								}
								//Edit Patient Biological Sex
								case 8: {
									int result = JOptionPane.showConfirmDialog(null,
											"Are you sure you want to change the Biological Sex?",
											"Confirm Biological Sex change", JOptionPane.YES_NO_OPTION);
									if (result == JOptionPane.OK_OPTION) {//Confirm change
										BiologicalSex newBiologicalSex = (hosp.getPatients()
												.get(table.getValueAt(selectedRow, 0)).getBiologicalSex()
												.equals(BiologicalSex.M)) ? BiologicalSex.F : BiologicalSex.M;//Find the Biological Sex to change Patient to
										hosp.getPatients().get(table.getValueAt(selectedRow, 0))
												.setBiologicalSex(newBiologicalSex);//Set the Patient Biological Sex
										serialize(hosp);//Update the table after changes
										updateTable(writePatient(), patientSP);
										JOptionPane.showMessageDialog(null,
												"Successfully changed Patient's Biological Sex!");
									}
									break;
								}
								//Edit Patient Gender
								case 9: {
									String[] genderOptions = { "Male", "Female", "Other", "Prefer not to say" };
									JComboBox<String> genderComboBox = new JComboBox<>(genderOptions);//Create a Combo Box of Genders

									int result = JOptionPane.showConfirmDialog(null, genderComboBox, "Please select a new Gender",
											JOptionPane.OK_CANCEL_OPTION);
									if (result == JOptionPane.OK_OPTION) {//Confirm change
										String newGender = (String) genderComboBox.getSelectedItem();

										if (!hosp.getPatients().get(table.getValueAt(selectedRow, 0)).getGender().equals(newGender)) {
											hosp.getPatients().get(table.getValueAt(selectedRow, 0)).setGender(newGender);//Set the Patient Gender
											JOptionPane.showMessageDialog(null, "Successfully changed Patient's Gender!");
											serialize(hosp);//Update the table after changes
											updateTable(writePatient(), patientSP);
										} else {//If the same value is given, show an error message
											throw new ChangeToSameValueException();
										}
									}
									break;
								}
								//For any other Value to change, Change it with the "editPerson" Method
								default: {
									editPerson(Patient.class, selectedRow, selectedCollum, hosp.getPatients());
									serialize(hosp);
									updateTable(writePatient(), patientSP);
								}

								}
							}
							
							//Edit Nurse Elements
							if (lastTableOpened == nursesFrame)
							{
								switch (selectedCollum) {
								//Edit Nurse License Number
								case 10:
                                {
                                    try {
                                        String newLicenseNumberStr = JOptionPane.showInputDialog(null,"Please enter a new License Number");
                                        if (newLicenseNumberStr != null) {
                                            int newLicenseNumber = Integer.parseInt(newLicenseNumberStr);
                                            if (((Nurse) hosp.getStaffMembers().get(table.getValueAt(selectedRow, 0))).getLicenseNumber() != newLicenseNumber) {
                                                ((Nurse) hosp.getStaffMembers().get(table.getValueAt(selectedRow, 0))).setLicenseNumber(newLicenseNumber);//Set the Nurse License Number
                                                serialize(hosp);//Update the table after changes
                                                updateTable(writeNurse(), nurseSP);
                                                JOptionPane.showMessageDialog(null,
                                                        "Successfully changed Nurse's License Number!");
                                            } else {//If the same value is given, show an error message
                                                throw new ChangeToSameValueException();
                                            }
                                        }
                                    } catch (NumberFormatException x) {
                                        JOptionPane.showMessageDialog(null, "Wrong Input!", "Error!",
                                                JOptionPane.ERROR_MESSAGE);
                                    }
                                    break;
                                }
								//Edit Nurse Intensive Care
								case 11: {
									int result = JOptionPane.showConfirmDialog(null,
											"Are you sure you want to change the Intensive Care",
											"Confirm Intensive Care change", JOptionPane.YES_NO_OPTION);
									if (result == JOptionPane.OK_OPTION) {//Confirm Change
										Nurse toChange = nursesHashMap.get(table.getValueAt(selectedRow, 0));//Find the Nurse to change
										Nurse changedNurse;
										if (table.getValueAt(selectedRow, 11).equals("Yes")) {//If the change is from IntensiveCareNurse to Nurse, create a new Nurse
											changedNurse = new Nurse(toChange.getId(), toChange.getFirstName(),
													toChange.getLastName(), toChange.getBirthDate(),
													toChange.getAddress(), toChange.getPhoneNumber(),
													toChange.getEmail(), toChange.getGender(),
													toChange.getWorkStartDate(), toChange.getDepartments(),
													toChange.getSalary(), toChange.getLicenseNumber());
										} else {//If the change is from Nurse to IntensiveCareNurse, create a new IntensiveCareNurse
											changedNurse = new IntensiveCareNurse(toChange.getId(),
													toChange.getFirstName(), toChange.getLastName(),
													toChange.getBirthDate(), toChange.getAddress(),
													toChange.getPhoneNumber(), toChange.getEmail(),
													toChange.getGender(), toChange.getWorkStartDate(),
													toChange.getDepartments(), toChange.getSalary(),
													toChange.getLicenseNumber());

										}
										hosp.getStaffMembers().put(toChange.getId(), changedNurse);//Set the Nurse to be/not be IntensiveCareNurse
										nursesHashMap.put(toChange.getId(), changedNurse);
										serialize(hosp);//Update the table after changes
										updateTable(writeNurse(), nurseSP);
										JOptionPane.showMessageDialog(null,
												"Successfully changed Nurse's Intensive Care!");
									}
									break;
								}
								//For any other Value to change, Change it with the "editStaffMember" Method
								default: {
									editStaffMember(Nurse.class, selectedRow, selectedCollum, nursesHashMap);
									serialize(hosp);//Update the table after changes
									updateTable(writeNurse(), nurseSP);
								}

								}
							}
							
							//Edit Doctor Elements
							if (lastTableOpened == doctorsFrame)
							{
								switch (selectedCollum) {
								//Edit Doctor License Number
                                case 10:
                                {
                                    try {
                                        String newLicenseNumberStr = JOptionPane.showInputDialog(null,"Please enter a new License Number");
                                        if (newLicenseNumberStr != null) {
                                            int newLicenseNumber = Integer.parseInt(newLicenseNumberStr);
                                            if (((Doctor) hosp.getStaffMembers().get(table.getValueAt(selectedRow, 0))).getLicenseNumber() != newLicenseNumber) {
                                                ((Doctor) hosp.getStaffMembers().get(table.getValueAt(selectedRow, 0))).setLicenseNumber(newLicenseNumber);//Set the Doctor License Number
                                                serialize(hosp);//Update the table after changes
                                                updateTable(writeDoctor(), doctorSP);
                                                JOptionPane.showMessageDialog(null,
                                                        "Successfully changed Doctor's License Number!");
                                            } else {//If the same value is given, show an error message
                                                throw new ChangeToSameValueException();
                                            }
                                        }
                                    } catch (NumberFormatException x) {
                                        JOptionPane.showMessageDialog(null, "Wrong Input!", "Error!",
                                                JOptionPane.ERROR_MESSAGE);
                                    }
                                    break;
                                }
								//Edit Doctor Finished Internship
								case 11: {
									int result = JOptionPane.showConfirmDialog(null,
											"Are you sure you want to change the Finished Internship",
											"Confirm Finished Internship change", JOptionPane.YES_NO_OPTION);
									if (result == JOptionPane.OK_OPTION) {//Confirm change
										boolean newFinishedInternship = (doctorsHashMap
												.get(table.getValueAt(selectedRow, 0)).isFinishInternship() == true)
														? false
														: true;//Find the Finished Internship to change the Doctor to
										doctorsHashMap.get(table.getValueAt(selectedRow, 0))
												.setFinishInternship(newFinishedInternship);//Set the Doctor Finished Internship
										serialize(hosp);//Update the table after changes
										updateTable(writeDoctor(), doctorSP);
										JOptionPane.showMessageDialog(null,
												"Successfully changed Doctor's Finished Internship!");
									}
									break;
								}
								//Edit Doctor Intensive Care
								case 12: {
									int result = JOptionPane.showConfirmDialog(null,
											"Are you sure you want to change the Intensive Care",
											"Confirm Intensive Care change", JOptionPane.YES_NO_OPTION);
									if (result == JOptionPane.OK_OPTION) {//Confirm change
										Doctor toChange = doctorsHashMap.get(table.getValueAt(selectedRow, 0));//Find the Doctor to change
										Doctor changedDoctor;
										if (table.getValueAt(selectedRow, 12).equals("Yes")) {//If the change is from IntensiveCareDoctor to Doctor, create a new Doctor
											changedDoctor = new Doctor(toChange.getId(), toChange.getFirstName(),
													toChange.getLastName(), toChange.getBirthDate(),
													toChange.getAddress(), toChange.getPhoneNumber(),
													toChange.getEmail(), toChange.getGender(),
													toChange.getWorkStartDate(), toChange.getDepartments(),
													toChange.getSalary(), toChange.getLicenseNumber(),
													toChange.isFinishInternship(), toChange.getSpecialization());
										} else {//If the change is from Doctor to IntensiveCareDoctor, create a new IntensiveCareDoctor
											changedDoctor = new IntensiveCareDoctor(toChange.getId(),
													toChange.getFirstName(), toChange.getLastName(),
													toChange.getBirthDate(), toChange.getAddress(),
													toChange.getPhoneNumber(), toChange.getEmail(),
													toChange.getGender(), toChange.getWorkStartDate(),
													toChange.getDepartments(), toChange.getSalary(),
													toChange.getLicenseNumber(), toChange.isFinishInternship());
										}
										hosp.getStaffMembers().put(toChange.getId(), changedDoctor);//Set the Doctor to be/ not be IntensiveCareDoctors
										doctorsHashMap.put(toChange.getId(), changedDoctor);
										serialize(hosp);//Update the table after changes
										updateTable(writeDoctor(), doctorSP);
										JOptionPane.showMessageDialog(null,
												"Successfully changed Doctor's Intensive Care!");
									}

									break;
								}
								//Edit Doctor Specialization
								case 13: {
									JComboBox<Specialization> specializations = new JComboBox<Specialization>(
											Specialization.values());//Create a Combo Box of all Specializations
									int result = JOptionPane.showConfirmDialog(null, specializations,
											"Please select a new Specialization", JOptionPane.OK_CANCEL_OPTION,
											JOptionPane.QUESTION_MESSAGE);
									if (result == JOptionPane.OK_OPTION) {//Confirm change
										Specialization newSpecialization = (Specialization) specializations
												.getSelectedItem();
										if (!doctorsHashMap.get(table.getValueAt(selectedRow, 0)).getSpecialization()
												.equals(newSpecialization)) {
											doctorsHashMap.get(table.getValueAt(selectedRow, 0))
													.setSpecialization(newSpecialization);//Set the Doctor Specialization
											serialize(hosp);//Update the table after changes
											updateTable(writeDoctor(), doctorSP);
											JOptionPane.showMessageDialog(null,
													"Successfully changed Doctor's Specialization!");
										} else {//If the same value is given, show an error message
											throw new ChangeToSameValueException();
										}
									}
									break;
								}
								//For any other Value to change, Change it with the "editStaffMember" Method
								default: {
									editStaffMember(Doctor.class, selectedRow, selectedCollum, doctorsHashMap);
									serialize(hosp);
									updateTable(writeDoctor(), doctorSP);
								}
								}
							}
						}

					}
				} catch (NoCellSelectedException x) {
					JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
				} catch (ChangePrimaryKeyException x) {
					JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
				} catch (ChangeToSameValueException x) {
					JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);

				}

			}
		});

		// ********** ADD OBJECT FRAMES ************//

		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				depManagerSelect.setModel(
						new DefaultComboBoxModel<Integer>(doctorsHashMap.keySet().stream().toArray(Integer[]::new)));

				medProblemDepSelect.setModel(new DefaultComboBoxModel<Integer>(
						hosp.getDepartments().keySet().stream().toArray(Integer[]::new)));

				visitPatientSelect.setModel(new DefaultComboBoxModel<Integer>(
						hosp.getPatients().keySet().stream().toArray(Integer[]::new)));

			}
		});
		
		// ********** CREATIONS OF ALL LABELS AND FIELDS FOR DATA INPUT ************//

		// DEPARTMENT
		departmentsAddFrame = new JPanel();
		departmentsAddFrame.setBackground(Color.WHITE);
		CardView.add(departmentsAddFrame, "departmentsAddFrame");
		departmentsAddFrame.setLayout(null);

		JLabel depNumLabel = new JLabel("Department number:");
		depNumLabel.setFont(labelFont);
		depNumLabel.setBounds(60, 136, 153, 20);
		departmentsAddFrame.add(depNumLabel);

		JTextField departmentNum = new JTextField();
		departmentNum.setFont(new Font("Tahoma", Font.PLAIN, 12));
		departmentNum.setBounds(60, 161, 172, 20);
		departmentsAddFrame.add(departmentNum);
		departmentNum.setColumns(10);

		JTextField departmentName = new JTextField();
		departmentName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		departmentName.setColumns(10);
		departmentName.setBounds(60, 237, 172, 20);
		departmentsAddFrame.add(departmentName);

		JTextField departmentLocation = new JTextField();
		departmentLocation.setFont(new Font("Tahoma", Font.PLAIN, 12));
		departmentLocation.setColumns(10);
		departmentLocation.setBounds(60, 309, 172, 20);
		departmentsAddFrame.add(departmentLocation);

		JLabel depNameLabel = new JLabel("Department name:");
		depNameLabel.setFont(labelFont);
		depNameLabel.setBounds(60, 206, 153, 20);
		departmentsAddFrame.add(depNameLabel);

		JLabel depLocationLabel = new JLabel("Department location:");
		depLocationLabel.setFont(labelFont);
		depLocationLabel.setBounds(60, 278, 153, 20);
		departmentsAddFrame.add(depLocationLabel);

		JLabel depManagerLabel = new JLabel("Who is the department manager?");
		depManagerLabel.setFont(labelFont);
		depManagerLabel.setBounds(653, 136, 259, 20);
		departmentsAddFrame.add(depManagerLabel);

		DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<>();
		depManagerSelect = new JComboBox<>(model);
		depManagerSelect.setFont(new Font("Tahoma", Font.PLAIN, 12));
		depManagerSelect.setBackground(Color.WHITE);
		depManagerSelect.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		depManagerSelect.setBounds(653, 160, 233, 22);
		depManagerSelect.setEnabled(false);
		departmentsAddFrame.add(depManagerSelect);

		JLabel depSpecializationLabel = new JLabel("Department specialization:");
		depSpecializationLabel.setFont(labelFont);
		depSpecializationLabel.setBounds(304, 136, 194, 20);
		departmentsAddFrame.add(depSpecializationLabel);

		JComboBox<Specialization> depSpecializationSelect = new JComboBox<>(Specialization.values());
		depSpecializationSelect.setFont(new Font("Tahoma", Font.PLAIN, 12));
		depSpecializationSelect.setBackground(Color.WHITE);
		depSpecializationSelect.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		depSpecializationSelect.setBounds(304, 160, 233, 22);
		departmentsAddFrame.add(depSpecializationSelect);

		//If an IntensiveCare is chosen, set the Specialization to be "IntensiveCare" automatically
		depSpecializationSelect.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        Specialization selectedSpecialization = (Specialization) depSpecializationSelect.getSelectedItem();
		        DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<>();

		        for (Map.Entry<Integer, Doctor> entry : doctorsHashMap.entrySet()) {
		            if (entry.getValue().getSpecialization() == selectedSpecialization) {
		                model.addElement(entry.getKey());
		            }
		        }

		        depManagerSelect.setModel(model);
		        depManagerSelect.setEnabled(model.getSize() > 0);
		    }
		});

		JLabel depMainLabel = new JLabel("Adding a department");
		depMainLabel.setFont(titleFont);
		depMainLabel.setBounds(58, 42, 255, 51);
		departmentsAddFrame.add(depMainLabel);
		
		// Apply styling to JTextField components
		styleTextField(departmentNum);
		styleTextField(departmentName);
		styleTextField(departmentLocation);

		// Apply styling to JComboBox components
		styleComboBox(depManagerSelect);
		styleComboBox(depSpecializationSelect);
		
		JLabel depAddImg = new JLabel("");
		depAddImg.setIcon(new ImageIcon(BasicWindow.class.getResource("/resources/House restyling-pana (1).png")));
		depAddImg.setBounds(281, 300, 584, 415);
		departmentsAddFrame.add(depAddImg);

		// MEDICAL PROBLEM
		medicalProblemsAddFrame = new JPanel();
		medicalProblemsAddFrame.setBackground(Color.WHITE);
		CardView.add(medicalProblemsAddFrame, "medicalProblemsAddFrame");
		medicalProblemsAddFrame.setLayout(null);

		JLabel medProblemMainLabel = new JLabel("Adding a medical problem");
		medProblemMainLabel.setFont(titleFont);
		medProblemMainLabel.setBounds(58, 42, 400, 49);
		medicalProblemsAddFrame.add(medProblemMainLabel);

		JLabel medProblemTypeLabel = new JLabel("Problem type:");
		medProblemTypeLabel.setFont(labelFont);
		medProblemTypeLabel.setBounds(58, 142, 153, 20);
		medicalProblemsAddFrame.add(medProblemTypeLabel);

		JRadioButton typeInjury = new JRadioButton("Injury");
		typeInjury.setBackground(Color.WHITE);
		typeInjury.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		medicalProblemsRadio.add(typeInjury);
		typeInjury.setFont(new Font("Tahoma", Font.PLAIN, 14));
		typeInjury.setBounds(58, 171, 109, 23);
		typeInjury.setActionCommand("i");
		medicalProblemsAddFrame.add(typeInjury);

		JRadioButton typeDisease = new JRadioButton("Disease");
		typeDisease.setBackground(Color.WHITE);
		typeDisease.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		medicalProblemsRadio.add(typeDisease);
		typeDisease.setFont(new Font("Tahoma", Font.PLAIN, 14));
		typeDisease.setBounds(58, 207, 109, 23);
		typeDisease.setActionCommand("d");
		medicalProblemsAddFrame.add(typeDisease);

		JRadioButton typeFracture = new JRadioButton("Fracture");
		typeFracture.setBackground(Color.WHITE);
		typeFracture.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		medicalProblemsRadio.add(typeFracture);
		typeFracture.setFont(new Font("Tahoma", Font.PLAIN, 14));
		typeFracture.setBounds(58, 242, 109, 23);
		typeFracture.setActionCommand("f");
		medicalProblemsAddFrame.add(typeFracture);

		JLabel medProblemNameLabel = new JLabel("Problem name:");
		medProblemNameLabel.setFont(labelFont);
		medProblemNameLabel.setBounds(258, 142, 153, 20);
		medicalProblemsAddFrame.add(medProblemNameLabel);

		JTextField medProblemName = new JTextField();
		medProblemName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		medProblemName.setBounds(258, 173, 204, 20);
		medicalProblemsAddFrame.add(medProblemName);
		medProblemName.setColumns(10);

		JLabel medProblemDepLabel = new JLabel("Which department treats the problem?");
		medProblemDepLabel.setFont(labelFont);
		medProblemDepLabel.setBounds(528, 142, 333, 20);
		medicalProblemsAddFrame.add(medProblemDepLabel);

		medProblemDepSelect = new JComboBox<Integer>();
		medProblemDepSelect.setFont(new Font("Tahoma", Font.PLAIN, 14));
		medProblemDepSelect.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		medProblemDepSelect.setModel(
		        new DefaultComboBoxModel<Integer>(hosp.getDepartments().keySet().stream().toArray(Integer[]::new)));
		medProblemDepSelect.setBackground(Color.WHITE);
		medProblemDepSelect.setBounds(528, 171, 319, 22);
		medicalProblemsAddFrame.add(medProblemDepSelect);

		JLabel diseaseDescLabel = new JLabel("Disease description:");
		diseaseDescLabel.setFont(labelFont);
		diseaseDescLabel.setBounds(58, 364, 153, 20);
		medicalProblemsAddFrame.add(diseaseDescLabel);

		JLabel fractureLocationLabel = new JLabel("Fracture location:");
		fractureLocationLabel.setFont(labelFont);
		fractureLocationLabel.setBounds(326, 364, 153, 20);
		medicalProblemsAddFrame.add(fractureLocationLabel);

		JTextField fractureLocation = new JTextField();
		fractureLocation.setFont(new Font("Tahoma", Font.PLAIN, 12));
		fractureLocation.setColumns(10);
		fractureLocation.setBounds(326, 394, 204, 20);
		medicalProblemsAddFrame.add(fractureLocation);

		JLabel fractureCastLabel = new JLabel("Fracture requires a cast:");
		fractureCastLabel.setFont(labelFont);
		fractureCastLabel.setBounds(326, 440, 180, 20);
		medicalProblemsAddFrame.add(fractureCastLabel);

		JCheckBox fractureCastCheck = new JCheckBox("");
		fractureCastCheck.setCursor(new Cursor(Cursor.HAND_CURSOR));
		fractureCastCheck.setBackground(Color.WHITE);
		fractureCastCheck.setBounds(504, 440, 33, 21);
		medicalProblemsAddFrame.add(fractureCastCheck);

		JLabel injuryRecoveryLabel = new JLabel("Injury common recovery time:");
		injuryRecoveryLabel.setFont(labelFont);
		injuryRecoveryLabel.setBounds(580, 370, 231, 20);
		medicalProblemsAddFrame.add(injuryRecoveryLabel);

		JTextField injuryRecoveryTime = new JTextField();
		injuryRecoveryTime.setFont(new Font("Tahoma", Font.PLAIN, 12));
		injuryRecoveryTime.setColumns(10);
		injuryRecoveryTime.setBounds(580, 396, 53, 20);
		medicalProblemsAddFrame.add(injuryRecoveryTime);

		JLabel recoveryMonths = new JLabel("months");
		recoveryMonths.setFont(new Font("Tahoma", Font.PLAIN, 12));
		recoveryMonths.setBounds(643, 399, 45, 13);
		medicalProblemsAddFrame.add(recoveryMonths);

		JLabel injuryLocationLabel = new JLabel("Injury location:");
		injuryLocationLabel.setFont(labelFont);
		injuryLocationLabel.setBounds(580, 440, 231, 20);
		medicalProblemsAddFrame.add(injuryLocationLabel);

		JTextField injuryLocation = new JTextField();
		injuryLocation.setFont(new Font("Tahoma", Font.PLAIN, 12));
		injuryLocation.setColumns(10);
		injuryLocation.setBounds(580, 470, 204, 20);
		medicalProblemsAddFrame.add(injuryLocation);
		fractureLocation.setEditable(false);
		fractureCastCheck.setEnabled(false);
		injuryRecoveryTime.setEditable(false);
		injuryLocation.setEditable(false);

		JTextField diseaseDesc = new JTextField();
		diseaseDesc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		diseaseDesc.setEditable(false);
		diseaseDesc.setColumns(10);
		diseaseDesc.setBounds(58, 394, 204, 20);
		medicalProblemsAddFrame.add(diseaseDesc);

		//Customize Medical Problem Fields availability after selecting Medical Problem Type
		typeInjury.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        diseaseDesc.setText("");
		        fractureLocation.setText("");
		        injuryRecoveryTime.setText("");
		        injuryLocation.setText("");
		        fractureCastCheck.setSelected(false);

		        diseaseDesc.setEditable(false);
		        fractureLocation.setEditable(false);
		        fractureCastCheck.setEnabled(false);
		        injuryRecoveryTime.setEditable(true);
		        injuryLocation.setEditable(true);
		    }
		});

		typeFracture.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        diseaseDesc.setText("");
		        fractureLocation.setText("");
		        injuryRecoveryTime.setText("");
		        injuryLocation.setText("");
		        fractureCastCheck.setSelected(false);
		        diseaseDesc.setEditable(false);
		        fractureLocation.setEditable(true);
		        fractureCastCheck.setEnabled(true);
		        injuryRecoveryTime.setEditable(false);
		        injuryLocation.setEditable(false);
		    }
		});

		typeDisease.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        diseaseDesc.setText("");
		        fractureLocation.setText("");
		        injuryRecoveryTime.setText("");
		        injuryLocation.setText("");
		        fractureCastCheck.setSelected(false);
		        diseaseDesc.setEditable(true);
		        fractureLocation.setEditable(false);
		        fractureCastCheck.setEnabled(false);
		        injuryRecoveryTime.setEditable(false);
		        injuryLocation.setEditable(false);
		    }
		});
		
		// Apply styling to JTextField components
		styleTextField(medProblemName);
		styleTextField(fractureLocation);
		styleTextField(injuryRecoveryTime);
		styleTextField(injuryLocation);
		styleTextField(diseaseDesc);

		// Apply styling to JComboBox components
		styleComboBox(medProblemDepSelect);

		// Apply styling to JRadioButton components
		styleRadioButton(typeInjury);
		styleRadioButton(typeDisease);
		styleRadioButton(typeFracture);

		// Apply styling to JCheckBox components
		styleCheckBox(fractureCastCheck);
		
		JLabel medProblemAddImg = new JLabel("");
		medProblemAddImg.setIcon(new ImageIcon(BasicWindow.class.getResource("/resources/injury-cuate (2).png")));
		medProblemAddImg.setBounds(97, 395, 231, 302);
		medicalProblemsAddFrame.add(medProblemAddImg);


		// PATIENT
		patientsAddFrame = new JPanel();
		patientsAddFrame.setBackground(Color.WHITE);
		CardView.add(patientsAddFrame, "patientsAddFrame");
		patientsAddFrame.setLayout(null);

		JLabel patientMainLabel = new JLabel("Adding a patient");
		patientMainLabel.setFont(titleFont);
		patientMainLabel.setBounds(58, 42, 200, 49);
		patientsAddFrame.add(patientMainLabel);

		JLabel patiendIDLabel = new JLabel("Patient ID:");
		patiendIDLabel.setFont(labelFont);
		patiendIDLabel.setBounds(58, 138, 153, 20);
		patientsAddFrame.add(patiendIDLabel);

		JLabel patientFirstNameLabel = new JLabel("First name:");
		patientFirstNameLabel.setFont(labelFont);
		patientFirstNameLabel.setBounds(58, 199, 153, 20);
		patientsAddFrame.add(patientFirstNameLabel);

		JLabel patientLNameLabel = new JLabel("Last name:");
		patientLNameLabel.setFont(labelFont);
		patientLNameLabel.setBounds(58, 264, 153, 20);
		patientsAddFrame.add(patientLNameLabel);

		JLabel patientDOBLabel = new JLabel("Date of birth:");
		patientDOBLabel.setFont(labelFont);
		patientDOBLabel.setBounds(58, 321, 153, 20);
		patientsAddFrame.add(patientDOBLabel);

		JLabel patientAddressLabel = new JLabel("Address:");
		patientAddressLabel.setFont(labelFont);
		patientAddressLabel.setBounds(302, 138, 153, 20);
		patientsAddFrame.add(patientAddressLabel);

		JLabel patientPhoneLabel = new JLabel("Phone number:");
		patientPhoneLabel.setFont(labelFont);
		patientPhoneLabel.setBounds(302, 199, 153, 20);
		patientsAddFrame.add(patientPhoneLabel);

		JLabel patientEmailLabel = new JLabel("E-mail address:");
		patientEmailLabel.setFont(labelFont);
		patientEmailLabel.setBounds(302, 264, 153, 20);
		patientsAddFrame.add(patientEmailLabel);

		JLabel patientBioLabel = new JLabel("Biological sex:");
		patientBioLabel.setFont(labelFont);
		patientBioLabel.setBounds(587, 218, 153, 20);
		patientsAddFrame.add(patientBioLabel);

		JLabel patientFundLabel = new JLabel("Health fund:");
		patientFundLabel.setFont(labelFont);
		patientFundLabel.setBounds(587, 310, 153, 20);
		patientsAddFrame.add(patientFundLabel);

		JLabel patientGenderLabel = new JLabel("Gender:");
		patientGenderLabel.setFont(labelFont);
		patientGenderLabel.setBounds(587, 138, 153, 20);
		patientsAddFrame.add(patientGenderLabel);

		JTextField patientID = new JTextField();
		patientID.setBounds(58, 168, 153, 20);
		patientsAddFrame.add(patientID);
		patientID.setColumns(10);

		JTextField patientFName = new JTextField();
		patientFName.setColumns(10);
		patientFName.setBounds(58, 230, 153, 20);
		patientsAddFrame.add(patientFName);

		JTextField patientLName = new JTextField();
		patientLName.setColumns(10);
		patientLName.setBounds(58, 295, 153, 20);
		patientsAddFrame.add(patientLName);

		JCalendar patientDOB = new JCalendar();
		patientDOB.setFont(new Font("Segoe UI", Font.PLAIN, 11));
		patientDOB.setBackground(Color.WHITE);
		patientDOB.getMonthChooser().getComboBox().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		patientDOB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		patientDOB.setBounds(58, 352, 226, 172);
		patientDOB.setDate(Hospital.TODAY);
		patientsAddFrame.add(patientDOB);
		
		JTextField patientAddress = new JTextField();
		patientAddress.setColumns(10);
		patientAddress.setBounds(302, 168, 153, 20);
		patientsAddFrame.add(patientAddress);

		JTextField patientPhone = new JTextField();
		patientPhone.setColumns(10);
		patientPhone.setBounds(302, 230, 153, 20);
		patientsAddFrame.add(patientPhone);

		JTextField patientEmail = new JTextField();
		patientEmail.setColumns(10);
		patientEmail.setBounds(302, 295, 153, 20);
		patientsAddFrame.add(patientEmail);

		JComboBox<String> patientGenderSelect = new JComboBox<String>();
		patientGenderSelect.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		patientGenderSelect.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Male", "Female", "Other", "Prefer not to say" }));
		patientGenderSelect.setFont(new Font("Tahoma", Font.PLAIN, 14));
		patientGenderSelect.setBackground(Color.WHITE);
		patientGenderSelect.setBounds(587, 169, 200, 22);
		patientsAddFrame.add(patientGenderSelect);

		JRadioButton male = new JRadioButton("Male");
		male.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		biologicalSexRadio.add(male);
		male.setFont(new Font("Tahoma", Font.PLAIN, 14));
		male.setBounds(587, 245, 109, 23);
		male.setActionCommand("M");
		male.setBackground(Color.WHITE);
		patientsAddFrame.add(male);

		JRadioButton female = new JRadioButton("Female");
		female.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		biologicalSexRadio.add(female);
		female.setFont(new Font("Tahoma", Font.PLAIN, 14));
		female.setBounds(587, 271, 109, 23);
		female.setActionCommand("F");
		female.setBackground(Color.WHITE);
		patientsAddFrame.add(female);

		JComboBox<HealthFund> patientFundSelect = new JComboBox<HealthFund>();
		patientFundSelect.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		patientFundSelect.setModel(new DefaultComboBoxModel<HealthFund>(HealthFund.values()));
		patientFundSelect.setFont(new Font("Tahoma", Font.PLAIN, 14));
		patientFundSelect.setBackground(Color.WHITE);
		patientFundSelect.setBounds(587, 341, 200, 22);
		patientsAddFrame.add(patientFundSelect);

		// Apply styling to JTextField components
		styleTextField(patientID);
		styleTextField(patientFName);
		styleTextField(patientLName);
		styleTextField(patientAddress);
		styleTextField(patientPhone);
		styleTextField(patientEmail);

		// Apply styling to JComboBox components
		styleComboBox(patientGenderSelect);
		styleComboBox(patientFundSelect);

		// Apply styling to JRadioButton components
		styleRadioButton(male);
		styleRadioButton(female);
		
		JLabel medProblemAddImg_1 = new JLabel("");
		medProblemAddImg_1.setIcon(new ImageIcon(BasicWindow.class.getResource("/resources/Hospital patient-amico (1).png")));
		medProblemAddImg_1.setBounds(250, 276, 506, 420);
		patientsAddFrame.add(medProblemAddImg_1);

		// VISIT
		visitsAddFrame = new JPanel();
		visitsAddFrame.setBackground(Color.WHITE);
		CardView.add(visitsAddFrame, "visitsAddFrame");
		visitsAddFrame.setLayout(null);

		JLabel visitsMainLabel = new JLabel("Creating a new visit");
		visitsMainLabel.setFont(titleFont);
		visitsMainLabel.setBounds(58, 42, 250, 49);
		visitsAddFrame.add(visitsMainLabel);

		JLabel visitPatientLabel = new JLabel("Patient for this visit:");
		visitPatientLabel.setFont(labelFont);
		visitPatientLabel.setBounds(58, 156, 153, 20);
		visitsAddFrame.add(visitPatientLabel);

		JLabel visitNumLabel = new JLabel("Visit number:");
		visitNumLabel.setFont(labelFont);
		visitNumLabel.setBounds(58, 221, 153, 20);
		visitsAddFrame.add(visitNumLabel);

		JLabel visitStartLabel = new JLabel("Visit start date:");
		visitStartLabel.setFont(labelFont);
		visitStartLabel.setBounds(311, 156, 153, 20);
		visitsAddFrame.add(visitStartLabel);

		JCalendar visitStartDate = new JCalendar();
		visitStartDate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		visitStartDate.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		visitStartDate.getMonthChooser().getComboBox().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		visitStartDate.setBounds(311, 196, 300, 212);
		visitStartDate.setDate(Hospital.TODAY);
		visitsAddFrame.add(visitStartDate);

		JTextField visitNum = new JTextField();
		visitNum.setColumns(10);
		visitNum.setBounds(58, 252, 200, 20);
		visitsAddFrame.add(visitNum);

		visitPatientSelect = new JComboBox<Integer>();
		visitPatientSelect.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		visitPatientSelect.setModel(
				new DefaultComboBoxModel<Integer>(hosp.getPatients().keySet().stream().toArray(Integer[]::new)));
		visitPatientSelect.setFont(new Font("Tahoma", Font.PLAIN, 14));
		visitPatientSelect.setBackground(Color.WHITE);
		visitPatientSelect.setBounds(58, 187, 200, 22);
		visitsAddFrame.add(visitPatientSelect);

		JLabel visitEndLabel = new JLabel("Visit end date:");
		visitEndLabel.setFont(labelFont);
		visitEndLabel.setBounds(639, 156, 153, 20);
		visitsAddFrame.add(visitEndLabel);

		JCalendar visitEnd = new JCalendar();
		visitEnd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		visitEnd.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		visitEnd.setBounds(639, 196, 300, 212);
		visitEnd.setDate(Hospital.TODAY);
		visitsAddFrame.add(visitEnd);
		
		// Apply styling to JTextField components
		styleTextField(visitNum);

		// Apply styling to JComboBox components
		styleComboBox(visitPatientSelect);
		
		JLabel visitsAddImg = new JLabel("");
		visitsAddImg.setIcon(new ImageIcon(BasicWindow.class.getResource("/resources/Blood donation-pana (1).png")));
		visitsAddImg.setBounds(145, 385, 406, 302);
		visitsAddFrame.add(visitsAddImg);

		// TREATMENT
		treatmentsAddFrame = new JPanel();
		treatmentsAddFrame.setBackground(Color.WHITE);
		CardView.add(treatmentsAddFrame, "treatmentsAddFrame");
		treatmentsAddFrame.setLayout(null);

		JLabel treatmentsMainLabel = new JLabel("Creating a new treatment");
		treatmentsMainLabel.setFont(titleFont);
		treatmentsMainLabel.setBounds(51, 42, 300, 49);
		treatmentsAddFrame.add(treatmentsMainLabel);

		JLabel treatmentsSerialLabel = new JLabel("Serial number:");
		treatmentsSerialLabel.setFont(labelFont);
		treatmentsSerialLabel.setBounds(121, 230, 153, 20);
		treatmentsAddFrame.add(treatmentsSerialLabel);

		JLabel treatmentDescLabel = new JLabel("Description:");
		treatmentDescLabel.setFont(labelFont);
		treatmentDescLabel.setBounds(498, 235, 153, 20);
		treatmentsAddFrame.add(treatmentDescLabel);

		JTextField treatmentsSerialNum = new JTextField();
		treatmentsSerialNum.setColumns(10);
		treatmentsSerialNum.setBounds(121, 261, 191, 20);
		treatmentsAddFrame.add(treatmentsSerialNum);

		JTextArea treatmentsDescription = new JTextArea();
		treatmentsDescription.setBounds(498, 261, 254, 116);
		Border blackBorder = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(204, 204, 204)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10));
		treatmentsDescription.setBorder(blackBorder);
		treatmentsAddFrame.add(treatmentsDescription);
		
		// Apply styling to JTextField components
		styleTextField(treatmentsSerialNum);

		// Apply styling to JTextArea components
		styleTextArea(treatmentsDescription);
		
		JLabel treatmentsAddImg = new JLabel("");
		treatmentsAddImg.setIcon(new ImageIcon(BasicWindow.class.getResource("/resources/Cardiologist-amico (2).png")));
		treatmentsAddImg.setBounds(102, 230, 463, 511);
		treatmentsAddFrame.add(treatmentsAddImg);

		// DOCTOR
		doctorsAddFrame = new JPanel();
		doctorsAddFrame.setBackground(Color.WHITE);
		CardView.add(doctorsAddFrame, "doctorsAddFrame");
		doctorsAddFrame.setLayout(null);

		JLabel doctorIDLabel = new JLabel("Doctor ID:");
		doctorIDLabel.setFont(labelFont);
		doctorIDLabel.setBounds(72, 137, 153, 20);
		doctorsAddFrame.add(doctorIDLabel);

		JLabel doctorFNameLabel = new JLabel("First name:");
		doctorFNameLabel.setFont(labelFont);
		doctorFNameLabel.setBounds(72, 198, 153, 20);
		doctorsAddFrame.add(doctorFNameLabel);

		JLabel doctroLNameLabel = new JLabel("Last name:");
		doctroLNameLabel.setFont(labelFont);
		doctroLNameLabel.setBounds(72, 263, 153, 20);
		doctorsAddFrame.add(doctroLNameLabel);

		JLabel doctorDOBLabel = new JLabel("Date of birth:");
		doctorDOBLabel.setFont(labelFont);
		doctorDOBLabel.setBounds(72, 335, 153, 20);
		doctorsAddFrame.add(doctorDOBLabel);

		JLabel doctorAddressLabel = new JLabel("Address:");
		doctorAddressLabel.setFont(labelFont);
		doctorAddressLabel.setBounds(316, 137, 153, 20);
		doctorsAddFrame.add(doctorAddressLabel);

		JLabel doctorPhoneLabel = new JLabel("Phone number:");
		doctorPhoneLabel.setFont(labelFont);
		doctorPhoneLabel.setBounds(316, 198, 153, 20);
		doctorsAddFrame.add(doctorPhoneLabel);

		JLabel doctorEmailLabel = new JLabel("E-mail address:");
		doctorEmailLabel.setFont(labelFont);
		doctorEmailLabel.setBounds(316, 263, 153, 20);
		doctorsAddFrame.add(doctorEmailLabel);

		JLabel doctorGenderLabel = new JLabel("Gender:");
		doctorGenderLabel.setFont(labelFont);
		doctorGenderLabel.setBounds(601, 137, 153, 20);
		doctorsAddFrame.add(doctorGenderLabel);

		JTextField doctorID = new JTextField();
		doctorID.setColumns(10);
		doctorID.setBounds(72, 167, 153, 20);
		doctorsAddFrame.add(doctorID);

		JTextField doctorFN = new JTextField();
		doctorFN.setColumns(10);
		doctorFN.setBounds(72, 229, 153, 20);
		doctorsAddFrame.add(doctorFN);

		JTextField doctorLN = new JTextField();
		doctorLN.setColumns(10);
		doctorLN.setBounds(72, 294, 153, 20);
		doctorsAddFrame.add(doctorLN);

		JCalendar doctorDOB = new JCalendar();
		doctorDOB.setCursor(new Cursor(Cursor.HAND_CURSOR));
		doctorDOB.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		doctorDOB.setBounds(72, 366, 226, 172);
		doctorDOB.setDate(Hospital.TODAY);
		doctorsAddFrame.add(doctorDOB);

		JTextField doctorAddress = new JTextField();
		doctorAddress.setColumns(10);
		doctorAddress.setBounds(316, 167, 153, 20);
		doctorsAddFrame.add(doctorAddress);

		JTextField doctorPhone = new JTextField();
		doctorPhone.setColumns(10);
		doctorPhone.setBounds(316, 229, 153, 20);
		doctorsAddFrame.add(doctorPhone);

		JTextField doctorEmail = new JTextField();
		doctorEmail.setColumns(10);
		doctorEmail.setBounds(316, 294, 153, 20);
		doctorsAddFrame.add(doctorEmail);

		JComboBox<String> doctorGender = new JComboBox<String>();
		doctorGender.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		doctorGender.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Male", "Female", "Other", "Prefer not to say" }));
		doctorGender.setFont(new Font("Tahoma", Font.PLAIN, 14));
		doctorGender.setBackground(Color.WHITE);
		doctorGender.setBounds(601, 168, 200, 22);
		doctorsAddFrame.add(doctorGender);

		JLabel doctorsMainLabel = new JLabel("Adding a doctor");
		doctorsMainLabel.setFont(titleFont);
		doctorsMainLabel.setBounds(48, 52, 200, 49);
		doctorsAddFrame.add(doctorsMainLabel);

		JLabel doctorLicenseLabel = new JLabel("License number:");
		doctorLicenseLabel.setFont(labelFont);
		doctorLicenseLabel.setBounds(601, 203, 153, 20);
		doctorsAddFrame.add(doctorLicenseLabel);

		JTextField doctorsLicense = new JTextField();
		doctorsLicense.setColumns(10);
		doctorsLicense.setBounds(601, 229, 153, 20);
		doctorsAddFrame.add(doctorsLicense);

		JLabel doctorWorkStartLabel = new JLabel("Work start date:");
		doctorWorkStartLabel.setFont(labelFont);
		doctorWorkStartLabel.setBounds(316, 335, 153, 20);
		doctorsAddFrame.add(doctorWorkStartLabel);

		JCalendar doctorStart = new JCalendar();
		doctorStart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		doctorStart.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		doctorStart.setBounds(316, 366, 226, 172);
		doctorStart.setDate(doctorDOB.getDate());
		doctorsAddFrame.add(doctorStart);

		JLabel doctorSalaryLabel = new JLabel("Salary:");
		doctorSalaryLabel.setFont(labelFont);
		doctorSalaryLabel.setBounds(601, 268, 153, 20);
		doctorsAddFrame.add(doctorSalaryLabel);

		JTextField doctorSalary = new JTextField();
		doctorSalary.setColumns(10);
		doctorSalary.setBounds(601, 294, 153, 20);
		doctorsAddFrame.add(doctorSalary);

		JLabel doctorSpecLabel = new JLabel("Specialization:");
		doctorSpecLabel.setFont(labelFont);
		doctorSpecLabel.setBounds(601, 412, 194, 20);
		doctorsAddFrame.add(doctorSpecLabel);

		JComboBox<Specialization> doctorSpec = new JComboBox<Specialization>();
		doctorSpec.setModel(new DefaultComboBoxModel<Specialization>(Specialization.values()));
		doctorSpec.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		doctorSpec.setFont(new Font("Tahoma", Font.PLAIN, 12));
		doctorSpec.setBackground(Color.WHITE);
		doctorSpec.setBounds(601, 442, 233, 22);
		doctorsAddFrame.add(doctorSpec);

		JLabel doctorInternshipLabel = new JLabel("Finish internship?");
		doctorInternshipLabel.setFont(labelFont);
		doctorInternshipLabel.setBounds(601, 375, 129, 20);
		doctorsAddFrame.add(doctorInternshipLabel);

		JCheckBox internCheck = new JCheckBox("");
		internCheck.setBackground(Color.WHITE);
		internCheck.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		internCheck.setBounds(728, 375, 29, 23);
		doctorsAddFrame.add(internCheck);

		JLabel doctorIntensiveAddLabel = new JLabel("Is the doctor an Intensive Care doctor?");
		doctorIntensiveAddLabel.setFont(labelFont);
		doctorIntensiveAddLabel.setBounds(601, 335, 276, 20);
		doctorsAddFrame.add(doctorIntensiveAddLabel);

		JCheckBox doctorIntensiveAddCheck = new JCheckBox("");
		doctorIntensiveAddCheck.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		doctorIntensiveAddCheck.setBackground(Color.WHITE);
		doctorIntensiveAddCheck.setBounds(881, 335, 29, 23);
		doctorsAddFrame.add(doctorIntensiveAddCheck);
		doctorIntensiveAddCheck.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					doctorSpec.setSelectedItem(Specialization.IntensiveCare);
					doctorSpec.setEnabled(false);
				} else if (e.getStateChange() == ItemEvent.DESELECTED) {
					doctorSpec.setEnabled(true);
				}
			}
		});
		
		// Apply styling to JTextField components
		styleTextField(doctorID);
		styleTextField(doctorFN);
		styleTextField(doctorLN);
		styleTextField(doctorAddress);
		styleTextField(doctorPhone);
		styleTextField(doctorEmail);
		styleTextField(doctorsLicense);
		styleTextField(doctorSalary);

		// Apply styling to JComboBox components
		styleComboBox(doctorGender);
		styleComboBox(doctorSpec);

		// Apply styling to JCheckBox components
		styleCheckBox(internCheck);
		styleCheckBox(doctorIntensiveAddCheck);

		// NURSE
		nursesAddFrame = new JPanel();
		nursesAddFrame.setBackground(Color.WHITE);
		CardView.add(nursesAddFrame, "nursesAddFrame");
		nursesAddFrame.setLayout(null);

		JPanel doctorsAddFrame_1 = new JPanel();
		doctorsAddFrame_1.setBackground(Color.WHITE);
		doctorsAddFrame_1.setLayout(null);
		doctorsAddFrame_1.setBounds(10, 11, 995, 565);
		nursesAddFrame.add(doctorsAddFrame_1);

		JLabel nurseIDLabel = new JLabel("Nurse ID:");
		nurseIDLabel.setFont(labelFont);
		nurseIDLabel.setBounds(72, 137, 153, 20);
		doctorsAddFrame_1.add(nurseIDLabel);

		JLabel nurseFNLabel = new JLabel("First name:");
		nurseFNLabel.setFont(labelFont);
		nurseFNLabel.setBounds(72, 198, 153, 20);
		doctorsAddFrame_1.add(nurseFNLabel);

		JLabel nurseLNLabel = new JLabel("Last name:");
		nurseLNLabel.setFont(labelFont);
		nurseLNLabel.setBounds(72, 263, 153, 20);
		doctorsAddFrame_1.add(nurseLNLabel);

		JLabel nurseDOBLabel = new JLabel("Date of birth:");
		nurseDOBLabel.setFont(labelFont);
		nurseDOBLabel.setBounds(72, 335, 153, 20);
		doctorsAddFrame_1.add(nurseDOBLabel);

		JLabel nurseAddressLabel = new JLabel("Address:");
		nurseAddressLabel.setFont(labelFont);
		nurseAddressLabel.setBounds(316, 137, 153, 20);
		doctorsAddFrame_1.add(nurseAddressLabel);

		JLabel nursePhoneLabel = new JLabel("Phone number:");
		nursePhoneLabel.setFont(labelFont);
		nursePhoneLabel.setBounds(316, 198, 153, 20);
		doctorsAddFrame_1.add(nursePhoneLabel);

		JLabel nurseEmailLabel = new JLabel("E-mail address:");
		nurseEmailLabel.setFont(labelFont);
		nurseEmailLabel.setBounds(316, 263, 153, 20);
		doctorsAddFrame_1.add(nurseEmailLabel);

		JLabel nurseGenderLabel = new JLabel("Gender:");
		nurseGenderLabel.setFont(labelFont);
		nurseGenderLabel.setBounds(601, 137, 153, 20);
		doctorsAddFrame_1.add(nurseGenderLabel);

		JTextField nurseID = new JTextField();
		nurseID.setColumns(10);
		nurseID.setBounds(72, 167, 153, 20);
		doctorsAddFrame_1.add(nurseID);

		JTextField nurseFN = new JTextField();
		nurseFN.setColumns(10);
		nurseFN.setBounds(72, 229, 153, 20);
		doctorsAddFrame_1.add(nurseFN);

		JTextField nurseLN = new JTextField();
		nurseLN.setColumns(10);
		nurseLN.setBounds(72, 294, 153, 20);
		doctorsAddFrame_1.add(nurseLN);

		JCalendar nurseDOB = new JCalendar();
		nurseDOB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		nurseDOB.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		nurseDOB.setBounds(72, 366, 226, 172);
		nurseDOB.setDate(Hospital.TODAY);
		doctorsAddFrame_1.add(nurseDOB);

		JTextField nurseAddress = new JTextField();
		nurseAddress.setColumns(10);
		nurseAddress.setBounds(316, 167, 153, 20);
		doctorsAddFrame_1.add(nurseAddress);

		JTextField nursePhone = new JTextField();
		nursePhone.setColumns(10);
		nursePhone.setBounds(316, 229, 153, 20);
		doctorsAddFrame_1.add(nursePhone);

		JTextField nurseEmail = new JTextField();
		nurseEmail.setColumns(10);
		nurseEmail.setBounds(316, 294, 153, 20);
		doctorsAddFrame_1.add(nurseEmail);

		JComboBox<String> nurseGender = new JComboBox<String>();
		nurseGender.setModel(new DefaultComboBoxModel<String>(new String[] { "Male", "Female", "Other", "Prefer not to say" }));
		nurseGender.setCursor(new Cursor(Cursor.HAND_CURSOR));
		nurseGender.setFont(labelFont);
		nurseGender.setBackground(Color.WHITE);
		nurseGender.setBounds(601, 168, 200, 22);
		doctorsAddFrame_1.add(nurseGender);

		JLabel nurseMainLabel = new JLabel("Adding a nurse");
		nurseMainLabel.setFont(titleFont);
		nurseMainLabel.setBounds(48, 52, 200, 49);
		doctorsAddFrame_1.add(nurseMainLabel);

		JLabel nurseLicenseLabel = new JLabel("License number:");
		nurseLicenseLabel.setFont(labelFont);
		nurseLicenseLabel.setBounds(601, 203, 153, 20);
		doctorsAddFrame_1.add(nurseLicenseLabel);

		JTextField nurseLicense = new JTextField();
		nurseLicense.setColumns(10);
		nurseLicense.setBounds(601, 229, 153, 20);
		doctorsAddFrame_1.add(nurseLicense);

		JLabel nurseWorkStartLabel = new JLabel("Work start date:");
		nurseWorkStartLabel.setFont(labelFont);
		nurseWorkStartLabel.setBounds(316, 335, 153, 20);
		doctorsAddFrame_1.add(nurseWorkStartLabel);

		JCalendar nurseWorkStart = new JCalendar();
		nurseWorkStart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		nurseWorkStart.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		nurseWorkStart.setBounds(316, 366, 226, 172);
		nurseWorkStart.setDate(nurseDOB.getDate());
		doctorsAddFrame_1.add(nurseWorkStart);

		JLabel nurseSalaryLabel = new JLabel("Salary:");
		nurseSalaryLabel.setFont(labelFont);
		nurseSalaryLabel.setBounds(601, 268, 153, 20);
		doctorsAddFrame_1.add(nurseSalaryLabel);

		JTextField nurseSalary = new JTextField();
		nurseSalary.setColumns(10);
		nurseSalary.setBounds(601, 294, 153, 20);
		doctorsAddFrame_1.add(nurseSalary);

		JLabel nurseIntensiveAddLabel = new JLabel("Intensive care:");
		nurseIntensiveAddLabel.setFont(labelFont);
		nurseIntensiveAddLabel.setBounds(601, 333, 106, 20);
		doctorsAddFrame_1.add(nurseIntensiveAddLabel);

		JCheckBox nurseIntensiveAddCheck = new JCheckBox("");
		nurseIntensiveAddCheck.setCursor(new Cursor(Cursor.HAND_CURSOR));
		nurseIntensiveAddCheck.setBackground(Color.WHITE);
		nurseIntensiveAddCheck.setBounds(713, 332, 29, 23);
		doctorsAddFrame_1.add(nurseIntensiveAddCheck);
		
		// Apply styling to JTextField components
		styleTextField(nurseID);
		styleTextField(nurseFN);
		styleTextField(nurseLN);
		styleTextField(nurseAddress);
		styleTextField(nursePhone);
		styleTextField(nurseEmail);
		styleTextField(nurseLicense);
		styleTextField(nurseSalary);

		// Apply styling to JComboBox components
		styleComboBox(nurseGender);

		// Apply styling to JCheckBox components
		styleCheckBox(nurseIntensiveAddCheck);

		// MEDICATION
		medicationsAddFrame = new JPanel();
		medicationsAddFrame.setBackground(Color.WHITE);
		CardView.add(medicationsAddFrame, "medicationsAddFrame");
		medicationsAddFrame.setLayout(null);

		JLabel medCodeLabel = new JLabel("Medication code:");
		medCodeLabel.setFont(labelFont);
		medCodeLabel.setBounds(60, 224, 153, 20);
		medicationsAddFrame.add(medCodeLabel);

		JLabel medNameLabel = new JLabel("Medication name:");
		medNameLabel.setFont(labelFont);
		medNameLabel.setBounds(60, 290, 153, 20);
		medicationsAddFrame.add(medNameLabel);

		JLabel dosageLabel = new JLabel("Dosage:");
		dosageLabel.setFont(labelFont);
		dosageLabel.setBounds(341, 224, 97, 20);
		medicationsAddFrame.add(dosageLabel);

		JLabel dosesLabel = new JLabel("Number of daily doses:");
		dosesLabel.setFont(labelFont);
		dosesLabel.setBounds(572, 224, 195, 20);
		medicationsAddFrame.add(dosesLabel);

		JTextField medCode = new JTextField();
		medCode.setColumns(10);
		medCode.setBounds(60, 255, 153, 20);
		medicationsAddFrame.add(medCode);

		JTextField medName = new JTextField();
		medName.setColumns(10);
		medName.setBounds(60, 321, 153, 20);
		medicationsAddFrame.add(medName);

		JTextField dosage = new JTextField();
		dosage.setColumns(10);
		dosage.setBounds(341, 255, 153, 20);
		medicationsAddFrame.add(dosage);

		JTextField doses = new JTextField();
		doses.setColumns(10);
		doses.setBounds(572, 255, 165, 20);
		medicationsAddFrame.add(doses);

		JLabel medMainLabel = new JLabel("Adding a medication");
		medMainLabel.setFont(titleFont);
		medMainLabel.setBounds(48, 52, 277, 49);
		medicationsAddFrame.add(medMainLabel);
		
		// Apply styling to JTextField components
		styleTextField(medCode);
		styleTextField(medName);
		styleTextField(dosage);
		styleTextField(doses);
		
		JLabel medProblemAddImg_2 = new JLabel("");
		medProblemAddImg_2.setIcon(new ImageIcon(BasicWindow.class.getResource("/resources/Medicine-amico (1).png")));
		medProblemAddImg_2.setBounds(351, 255, 547, 417);
		medicationsAddFrame.add(medProblemAddImg_2);

		// ************ ADD ELEMENTS ***************//

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//Add Medication
				if (lastAddPressed == medicationsAddFrame) { 
					try {//Get all values for the new Medication
						String medCodeText = medCode.getText().trim();
						String medNameText = medName.getText().trim();
						String dosageText = dosage.getText().trim();
						String dosesText = doses.getText().trim();

						//If one of the fields is not given, show an error message
						if (medCodeText.isEmpty() || medNameText.isEmpty() || dosageText.isEmpty()
								|| dosesText.isEmpty()) {
							throw new EmptyFieldsException();
						}
						//If the Primary Key is not valid, show an error message
						if (!medCodeText.matches("^[1-9]\\d*$")) {
							throw new InvalidPrimaryKeyException(medCodeText);
						}

						int newcode = Integer.parseInt(medCodeText);
						String newMedName = medNameText;
						double newDosage = Double.parseDouble(dosageText);
						int newDoses = Integer.parseInt(dosesText);

						//Create a new Medication from the given values
						Medication toAdd = new Medication(newcode, newMedName, newDosage, newDoses);
						if (hosp.addMedication(toAdd)) {//Add the new Medication to the System
							serialize(hosp);//Update the table after changes
							updateTable(writeMedication(), medicationSP);
							JOptionPane.showMessageDialog(null, "Successfully added Medication: " + newMedName);
							medCode.setText("");//Reset all Text Fields
							medName.setText("");
							dosage.setText("");
							doses.setText("");
						}

					//Check for valid values
					} catch (EmptyFieldsException x) {
						JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
					} catch (ObjectAlreadyExistsException x) {
						JOptionPane.showMessageDialog(null, "Object already exists in Hospital!", "Error!",
								JOptionPane.ERROR_MESSAGE);
						medCode.setText("");
					} catch (NumberFormatException x) {
						JOptionPane.showMessageDialog(null, "Wrong Input!", "Error!", JOptionPane.ERROR_MESSAGE);
					} catch (NegativeDosageException x) {
						JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
						dosage.setText("");
					} catch (NegativeNumberOfDosesException x) {
						JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
						doses.setText("");
					} catch (InvalidPrimaryKeyException x) {
						JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
						medCode.setText("");
					}
				}

				//Add Treatment
				if (lastAddPressed == treatmentsAddFrame) { 
					try {//Get all values for the new Treatment
						String serialNumText = treatmentsSerialNum.getText().trim();
						String descriptionText = treatmentsDescription.getText().trim();
						
						//If one of the fields is not given, show an error message
						if (serialNumText.isEmpty() || descriptionText.isEmpty()) {
							throw new EmptyFieldsException();
						}
						//If the Primary Key is not valid, show an error message
						if (!serialNumText.matches("^[1-9]\\d*$")) {
							throw new InvalidPrimaryKeyException(serialNumText);
						}

						int newCode = Integer.parseInt(serialNumText);
						String newDescription = descriptionText;

						//Create a new Treatment from the given values
						Treatment toAdd = new Treatment(newCode, newDescription);
						if (hosp.addTreatment(toAdd)) {//Add the new Treatment to the System
							serialize(hosp);//Update the table after changes
							updateTable(writeTreatment(), treatmentSP);
							JOptionPane.showMessageDialog(null, "Successfully added Treatment: " + newDescription);
							treatmentsSerialNum.setText("");//Reset all Text Fields
							treatmentsDescription.setText("");
						}
					//Check for valid values
					} catch (EmptyFieldsException x) {
						JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
					} catch (ObjectAlreadyExistsException x) {
						JOptionPane.showMessageDialog(null, "Object already exists in Hospital!", "Error!",
								JOptionPane.ERROR_MESSAGE);
						treatmentsSerialNum.setText("");
					} catch (NumberFormatException x) {
						JOptionPane.showMessageDialog(null, "Wrong Input!", "Error!", JOptionPane.ERROR_MESSAGE);
						treatmentsSerialNum.setText("");
					} catch (InvalidPrimaryKeyException x) {
						JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
						treatmentsSerialNum.setText("");
					}
				}

				//Add Department
				if (lastAddPressed == departmentsAddFrame) {
					try {//Get all values for a new Department
						String departmentNumText = departmentNum.getText().trim();
						String departmentNameText = departmentName.getText().trim();
						String departmentLocationText = departmentLocation.getText().trim();

						//If one of the values is not given, show an error message
						if (departmentNumText.isEmpty() || departmentNameText.isEmpty()
								|| departmentLocationText.isEmpty()) {
							throw new EmptyFieldsException();
						}

						//If the Primary Key is not valid, show an error message
						if (!departmentNumText.matches("^[1-9]\\d*$")) {
							throw new InvalidPrimaryKeyException(departmentNumText);
						}

						int newNum = Integer.parseInt(departmentNumText);
						String newName = departmentNameText;
						String newLocation = departmentLocationText;
						Doctor newManager = doctorsHashMap.get(depManagerSelect.getSelectedItem());
						Specialization newSpecialization = (Specialization) depSpecializationSelect.getSelectedItem();

						//Create a new Department from the given values
						Department toAdd = new Department(newNum, newName, newManager, newLocation, newSpecialization);
						if (hosp.addDepartment(toAdd)) {//Add the new Department to the System
							serialize(hosp);//Update the table after changes
							updateTable(writeDepartment(), departmentSP);
							JOptionPane.showMessageDialog(null, "Successfully added Department: " + newName);
							departmentNum.setText("");//Reset all Text Fields
							departmentName.setText("");
							departmentLocation.setText("");
						}
					//Check for valid values
					} catch (EmptyFieldsException x) {
						JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
					} catch (ObjectAlreadyExistsException x) {
						JOptionPane.showMessageDialog(null, "Object already exists in Hospital!", "Error!",
								JOptionPane.ERROR_MESSAGE);
						departmentNum.setText("");
					} catch (NumberFormatException x) {
						JOptionPane.showMessageDialog(null, "Wrong Input!", "Error!", JOptionPane.ERROR_MESSAGE);
						departmentNum.setText("");
					} catch (InvalidPrimaryKeyException x) {
						JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
						departmentNum.setText("");
					}
				}

				try {
					
					//Add Medical Problem
					if (lastAddPressed == medicalProblemsAddFrame) 
					{//Get values shared values of new Medical Problem
						String newMedicalProblemName = medProblemName.getText().trim();
						
						//If one of the values is not given, show an error message
						if (newMedicalProblemName.isEmpty()) {
							throw new EmptyFieldsException();
						}

						Department newMedicalProblemDepartment = hosp.getDepartments()
								.get(medProblemDepSelect.getSelectedItem());

						String medicalProblemsStr = medicalProblemsRadio.getSelection().getActionCommand();

						switch (medicalProblemsStr) {
						//Add Injury
						case "i": 
						{//Get all values for new Injury
							String newInjuryLocation = injuryLocation.getText().trim();
							String recoveryTimeText = injuryRecoveryTime.getText().trim();
							if (newInjuryLocation.isEmpty() || recoveryTimeText.isEmpty()) {//If one of the values is not given, show an error message
								throw new EmptyFieldsException();
							}

							double newInjuryCommonRecoveryTime = Double.parseDouble(recoveryTimeText);
							if (newInjuryCommonRecoveryTime <= 0) {//Check for valid Common Recovery Time
								throw new InvalidCommonRecoveryTimeException(newInjuryCommonRecoveryTime);
							}
							//Create a new Injury from the given values
							Injury toAdd = new Injury(newMedicalProblemName, newMedicalProblemDepartment,
									newInjuryCommonRecoveryTime, newInjuryLocation);
							if (hosp.addInjury(toAdd)) {//Add the new Injury to the System
								injuriesHashMap.put(toAdd.getCode(), toAdd);
								serialize(hosp);//Update the table after changes
								updateTable(writeInjury(), injuriesSP);
								JOptionPane.showMessageDialog(null,
										"Successfully added Medical Problem: " + newMedicalProblemName);
								medProblemName.setText("");//Reset all Text Fields
								injuryRecoveryTime.setText("");
								injuryLocation.setText("");
								fractureLocation.setText("");
								fractureCastCheck.setSelected(false);
								diseaseDesc.setText("");
							}
							break;
						}
						//Add Fracture
						case "f": 
						{//Get all values for new Fracture
							String newFractureLocation = fractureLocation.getText().trim();
							//If one of the values is not given, show an error message
							if (newFractureLocation.isEmpty()) {
								throw new EmptyFieldsException();
							}

							boolean newFractureRequiresCast = fractureCastCheck.isSelected();
							//Create a new Fracture from given values
							Fracture toAdd = new Fracture(newMedicalProblemName, newMedicalProblemDepartment,
									newFractureLocation, newFractureRequiresCast);
							if (hosp.addFracture(toAdd)) {//Add the new Fracture to the System
								fracturesHashMap.put(toAdd.getCode(), toAdd);
								serialize(hosp);//Update the table after changes
								updateTable(writeFracture(), fracturesSP);
								JOptionPane.showMessageDialog(null,
										"Successfully added Medical Problem: " + newMedicalProblemName);
								medProblemName.setText("");//Reset all Text Fields
								injuryRecoveryTime.setText("");
								injuryLocation.setText("");
								fractureLocation.setText("");
								fractureCastCheck.setSelected(false);
								diseaseDesc.setText("");
							}
							break;
						}
						//Add Disease
						case "d": 
						{//Get all values for new Disease
							String newDiseaseDescription = diseaseDesc.getText().trim();
							//If one of the values is not given, show an error message
							if (newDiseaseDescription.isEmpty()) {
								throw new EmptyFieldsException();
							}
							//Create a new Disease from given values
							Disease toAdd = new Disease(newMedicalProblemName, newMedicalProblemDepartment,
									newDiseaseDescription);
							if (hosp.addDisease(toAdd)) {//Add the new Disease to the System
								diseasesHashMap.put(toAdd.getCode(), toAdd);
								serialize(hosp);//Update the table after changes
								updateTable(writeDisease(), diseasesSP);
								JOptionPane.showMessageDialog(null,
										"Successfully added Medical Problem: " + newMedicalProblemName);
								medProblemName.setText("");//Reset all Text Fields
								injuryRecoveryTime.setText("");
								injuryLocation.setText("");
								fractureLocation.setText("");
								fractureCastCheck.setSelected(false);
								diseaseDesc.setText("");
							}
							break;
						}
						}
					}
				//Check for valid values
				} catch (EmptyFieldsException x) {
					JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException x) {
					JOptionPane.showMessageDialog(null, "Wrong Input!", "Error!", JOptionPane.ERROR_MESSAGE);
					injuryRecoveryTime.setText("");
				} catch (InvalidCommonRecoveryTimeException x) {
					JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
					injuryRecoveryTime.setText("");
				} catch (NullPointerException x) {
					JOptionPane.showMessageDialog(null, "No Medical Type Selected!", "Error!",
							JOptionPane.ERROR_MESSAGE);
				}

				//Add Visit
				if (lastAddPressed == visitsAddFrame) { 
					try {//Get all values for a new Visit
						String inputKey = visitNum.getText().trim();
						//If one of the values is not given, show an error message
						if (inputKey.isEmpty()) {
							throw new EmptyFieldsException();
						}
						//If the Primary Key is not valid, show an error message
						if (!inputKey.matches("^[1-9]\\d*$")) {
							throw new InvalidPrimaryKeyException(inputKey);
						}

						int newNum = Integer.parseInt(inputKey);
						Patient newPatient = hosp.getPatients().get(visitPatientSelect.getSelectedItem());

						Date newStartDate = visitStartDate.getDate();
						Date newEndDate = visitEnd.getDate();
						//Check for valid Start Date and End Date
						if (newStartDate == null || newEndDate == null) {
							throw new EmptyFieldsException();
						}

						if (newStartDate.after(Hospital.TODAY)) {
							throw new FutureDateException(newStartDate);
						}
						if (newEndDate.after(Hospital.TODAY)) {
							throw new FutureDateException(newEndDate);
						}
						
						if (newEndDate.before(newStartDate)) {
							throw new InvalidDateException(newEndDate, newStartDate);
						}						
						
						//Creates a new Visit from given values
						Visit toAdd = new Visit(newNum, newPatient, newStartDate, newEndDate);
						if (hosp.addVisit(toAdd)) {//Add the new Visit to the System
							serialize(hosp);//Update the table after changes
							updateTable(writeVisit(), visitSP);
							JOptionPane.showMessageDialog(null, "Successfully added Visit: " + newNum);
							visitNum.setText("");//Reset all Text Fields
							visitEnd.setDate(Hospital.TODAY);
							visitStartDate.setDate(Hospital.TODAY);
						}
					//Check for valid values
					} catch (EmptyFieldsException x) {
						JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
					} catch (ObjectAlreadyExistsException x) {
						JOptionPane.showMessageDialog(null, "Object already exists in Hospital!", "Error!",
								JOptionPane.ERROR_MESSAGE);
						visitNum.setText("");
					} catch (NumberFormatException x) {
						JOptionPane.showMessageDialog(null, "Wrong Input!", "Error!", JOptionPane.ERROR_MESSAGE);
						visitNum.setText("");
					} catch (InvalidPrimaryKeyException x) {
						JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
						visitNum.setText("");
					} catch (InvalidDateException x) {
						JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
					} catch (FutureDateException x) {
						JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}

				//Add Patient
				if (lastAddPressed == patientsAddFrame) { 
					try {//Get all values for a new Patient
						String inputKey = patientID.getText().trim();
						//If one of the values is not given, show an error message
						if (inputKey.isEmpty() || patientFName.getText().trim().isEmpty()
								|| patientLName.getText().trim().isEmpty() || patientDOB.getDate() == null
								|| patientAddress.getText().trim().isEmpty() || patientPhone.getText().trim().isEmpty()
								|| patientEmail.getText().trim().isEmpty()
								|| patientGenderSelect.getSelectedItem() == null
								|| patientFundSelect.getSelectedItem() == null
								|| biologicalSexRadio.getSelection() == null) {
							throw new EmptyFieldsException();
						}
						//If the Primary Key is not valid, show an error message
						if (!inputKey.matches("^[1-9]\\d*$")) {
							throw new InvalidPrimaryKeyException(inputKey);
						}

						int newID = Integer.parseInt(inputKey);
						String newFName = patientFName.getText().trim();
						String newLName = patientLName.getText().trim();
						Date newDOB = patientDOB.getDate();
						String newAddress = patientAddress.getText().trim();
						String newPhone = patientPhone.getText().trim();
						String newEmail = patientEmail.getText().trim();
						String newGender = (String) patientGenderSelect.getSelectedItem();
						BiologicalSex newBiologicalSex = (biologicalSexRadio.getSelection().getActionCommand()
								.equals("M")) ? BiologicalSex.M : BiologicalSex.F;
						HealthFund newHealthFund = (HealthFund) patientFundSelect.getSelectedItem();

						//Create a new Patient from given values
						Patient toAdd = new Patient(newID, newFName, newLName, newDOB, newAddress, newPhone, newEmail,
								newGender, newHealthFund, newBiologicalSex);
						if (hosp.addPatient(toAdd)) {//Add the new Patient to the System
							serialize(hosp);//Update the table after changes
							updateTable(writePatient(), patientSP);
							JOptionPane.showMessageDialog(null,
									"Successfully added Patient: " + newFName + " " + newLName);
							patientID.setText("");//Reset all Text Fields
							patientFName.setText("");
							patientLName.setText("");
							patientAddress.setText("");
							patientPhone.setText("");
							patientEmail.setText("");
							patientGenderSelect.setSelectedIndex(-1);
							patientFundSelect.setSelectedIndex(-1);
							biologicalSexRadio.clearSelection();
							patientDOB.setDate(Hospital.TODAY);
						}
					//Check for valid values
					} catch (EmptyFieldsException x) {
						JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
					} catch (ObjectAlreadyExistsException x) {
						JOptionPane.showMessageDialog(null, "Object already exists in Hospital!", "Error!",
								JOptionPane.ERROR_MESSAGE);
						patientID.setText("");
					} catch (NumberFormatException x) {
						JOptionPane.showMessageDialog(null, "Wrong Input!", "Error!", JOptionPane.ERROR_MESSAGE);
						patientID.setText("");
					} catch (InvalidPrimaryKeyException x) {
						JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
						patientID.setText("");
					} catch (FutureDateException x) {
						JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
						patientDOB.setDate(Hospital.TODAY);
					} 

				}

				//Add Nurse
				if (lastAddPressed == nursesAddFrame) { 
					try {//Get all values for a new Nurse
						String inputID = nurseID.getText().trim();
						//If one of the fields is not given, show an error message
						if (inputID.isEmpty() || nurseFN.getText().trim().isEmpty()
								|| nurseLN.getText().trim().isEmpty() || nurseDOB.getDate() == null
								|| nurseAddress.getText().trim().isEmpty() || nursePhone.getText().trim().isEmpty()
								|| nurseEmail.getText().trim().isEmpty() || nurseGender.getSelectedItem() == null
								|| nurseWorkStart.getDate() == null || nurseSalary.getText().trim().isEmpty()
								|| nurseLicense.getText().trim().isEmpty()) {
							throw new EmptyFieldsException();
						}

						int newNurseID = Integer.parseInt(inputID);
						String newNurseFName = nurseFN.getText().trim();
						String newNurseLName = nurseLN.getText().trim();
						Date newDOB = nurseDOB.getDate();
						String newNurseAddress = nurseAddress.getText().trim();
						String newNursePhone = nursePhone.getText().trim();
						String newNurseEmail = nurseEmail.getText().trim();
						String newNurseGender = (String) nurseGender.getSelectedItem();
						Date newNurseWorkStart = nurseWorkStart.getDate();
						double newNurseSalary = Double.parseDouble(nurseSalary.getText().trim());
						//Check for valid Salary
						if (newNurseSalary <= 0) {
							throw new InvalidSalaryException();
						}
						int newNurseLicenseNumber = Integer.parseInt(nurseLicense.getText().trim());

						//Check for valid Birth and Work Start Dates
						if (newNurseWorkStart.before(newDOB)) {
							throw new InvalidDateException(newNurseWorkStart, newDOB);
						}

						//Create a new Nurse from given values
						Nurse toAdd;
						if (!nurseIntensiveAddCheck.isSelected()) {//Create a new Nurse
							toAdd = new Nurse(newNurseID, newNurseFName, newNurseLName, newDOB, newNurseAddress,
									newNursePhone, newNurseEmail, newNurseGender, newNurseWorkStart, newNurseSalary,
									newNurseLicenseNumber);
						} else {//Create a new IntensiveCareNurse
							toAdd = new IntensiveCareNurse(newNurseID, newNurseFName, newNurseLName, newDOB,
									newNurseAddress, newNursePhone, newNurseEmail, newNurseGender, newNurseWorkStart,
									newNurseSalary, newNurseLicenseNumber);
						}

						if (hosp.addNurse(toAdd)) {//Add the new Nurse to the System
							nursesHashMap.put(newNurseID, toAdd);
							serialize(hosp);////Update the table after changes
							updateTable(writeNurse(), nurseSP);
							JOptionPane.showMessageDialog(null,
									"Successfully added Nurse: " + newNurseFName + " " + newNurseLName);
							nurseID.setText("");//Reset all Text Fields
							nurseFN.setText("");
							nurseLN.setText("");
							nurseAddress.setText("");
							nursePhone.setText("");
							nurseEmail.setText("");
							nurseLicense.setText("");
							nurseSalary.setText("");
							nurseIntensiveAddCheck.setSelected(false);
							nurseWorkStart.setDate(Hospital.TODAY);
							nurseDOB.setDate(Hospital.TODAY);
						}
					//Check for valid values
					} catch (EmptyFieldsException x) {
						JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
					} catch (ObjectAlreadyExistsException x) {
						JOptionPane.showMessageDialog(null, "Object already exists in Hospital!", "Error!",
								JOptionPane.ERROR_MESSAGE);
						nurseID.setText("");
					} catch (NumberFormatException x) {
						JOptionPane.showMessageDialog(null, "Wrong Input!", "Error!", JOptionPane.ERROR_MESSAGE);
					} catch (InvalidDateException x) {
						JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
					} catch (InvalidSalaryException x) {
						JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
						nurseSalary.setText("");
					} catch (FutureDateException x) {
						JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
					}

				}

				//Add Doctor
				if (lastAddPressed == doctorsAddFrame) { 
					try {//Get all values for a new Doctor
						String inputID = doctorID.getText().trim();
						//If one of the values is not given, show an error message
						if (inputID.isEmpty() || doctorFN.getText().trim().isEmpty()
								|| doctorLN.getText().trim().isEmpty() || doctorDOB.getDate() == null
								|| doctorAddress.getText().trim().isEmpty() || doctorPhone.getText().trim().isEmpty()
								|| doctorEmail.getText().trim().isEmpty() || doctorGender.getSelectedItem() == null
								|| doctorStart.getDate() == null || doctorSalary.getText().trim().isEmpty()
								|| doctorsLicense.getText().trim().isEmpty()) {
							throw new EmptyFieldsException();
						}

						int newDoctorID = Integer.parseInt(inputID);
						String newDoctorFName = doctorFN.getText().trim();
						String newDoctorLName = doctorLN.getText().trim();
						Date newDOB = doctorDOB.getDate();
						String newDoctorAddress = doctorAddress.getText().trim();
						String newDoctorPhone = doctorPhone.getText().trim();
						String newDoctorEmail = doctorEmail.getText().trim();
						String newDoctorGender = (String) doctorGender.getSelectedItem();
						Date newDoctorWorkStart = doctorStart.getDate();
						double newDoctorSalary = Double.parseDouble(doctorSalary.getText().trim());
						//Check for a valid Salary
						if (newDoctorSalary <= 0) {
							throw new InvalidSalaryException();
						}
						int newDoctorLicenseNumber = Integer.parseInt(doctorsLicense.getText().trim());
						boolean newDoctorFinishedInternship = internCheck.isSelected();
						//Check for a valid Birth and Work Start Dates
						if (newDoctorWorkStart.before(newDOB)) {
							throw new InvalidDateException(newDoctorWorkStart, newDOB);
						}
						//Create a new Doctor from given values
						Doctor toAdd;
						if (!doctorIntensiveAddCheck.isSelected()) {//Create a new Doctor
							Specialization newDoctorSpecialization = (Specialization) doctorSpec.getSelectedItem();
							toAdd = new Doctor(newDoctorID, newDoctorFName, newDoctorLName, newDOB, newDoctorAddress,
									newDoctorPhone, newDoctorEmail, newDoctorGender, newDoctorWorkStart,
									newDoctorSalary, newDoctorLicenseNumber, newDoctorFinishedInternship,
									newDoctorSpecialization);
						} else {//Create a new IntensiveCareDoctor
							toAdd = new IntensiveCareDoctor(newDoctorID, newDoctorFName, newDoctorLName, newDOB,
									newDoctorAddress, newDoctorPhone, newDoctorEmail, newDoctorGender,
									newDoctorWorkStart, newDoctorSalary, newDoctorLicenseNumber,
									newDoctorFinishedInternship);
						}

						if (hosp.addDoctor(toAdd)) {//Add the new Doctor to the System
							doctorsHashMap.put(newDoctorID, toAdd);
							serialize(hosp);//Update the table after changes
							updateTable(writeDoctor(), doctorSP);
							JOptionPane.showMessageDialog(null,
									"Successfully added Doctor: " + newDoctorFName + " " + newDoctorLName);
							doctorID.setText("");//Reset all Text Fields
							doctorFN.setText("");
							doctorLN.setText("");
							doctorAddress.setText("");
							doctorPhone.setText("");
							doctorEmail.setText("");
							doctorsLicense.setText("");
							doctorSalary.setText("");
							internCheck.setSelected(false);
							doctorIntensiveAddCheck.setSelected(false);
							doctorStart.setDate(Hospital.TODAY);
							doctorDOB.setDate(Hospital.TODAY);
						}
					//Check for valid values
					} catch (EmptyFieldsException x) {
						JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
					} catch (ObjectAlreadyExistsException x) {
						JOptionPane.showMessageDialog(null, "Object already exists in Hospital!", "Error!",
								JOptionPane.ERROR_MESSAGE);
						doctorID.setText("");
					} catch (NumberFormatException x) {
						JOptionPane.showMessageDialog(null, "Wrong Input!", "Error!", JOptionPane.ERROR_MESSAGE);
					} catch (InvalidDateException x) {
						JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
					} catch (InvalidSalaryException x) {
						JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
						doctorSalary.setText("");
					} catch (FutureDateException x) {
						JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
					}

				}

			}
		});

		// *********** ADDING OBJECTS TO OBJECTS FRAMES ***********//

		// MEDICAL PROBLEM ---> VISIT
		addMedProblemToVisitFrame = new JPanel();
		addMedProblemToVisitFrame.setBackground(Color.WHITE);
		CardView.add(addMedProblemToVisitFrame, "addMedProblemToVisitFrame");
		addMedProblemToVisitFrame.setLayout(null);

		JLabel addMedProblemToVisitMainLabel = new JLabel("Choose a medical problem to add:");
		addMedProblemToVisitMainLabel.setFont(titleFont);
		addMedProblemToVisitMainLabel.setBounds(10, 31, 450, 25);
		addMedProblemToVisitFrame.add(addMedProblemToVisitMainLabel);

		//Initialize the Medical Problem tables
		addInjuryModel = createTableModel(
				new String[] { "Code", "Name", "Department", "Common recovery time (months)", "Location" },
				new Class[] { Integer.class, String.class, String.class, String.class, String.class });
		table = new JTable(addInjuryModel);
		populateTableString(addInjuryModel, injuriesHashMap,
				new Object[][] { { Integer.class, String.class, String.class, String.class, String.class } });

		addDiseaseModel = createTableModel(new String[] { "Code", "Name", "Department", "Description" },
				new Class[] { Integer.class, String.class, String.class, String.class });
		table = new JTable(addDiseaseModel);
		populateTableString(addDiseaseModel, diseasesHashMap,
				new Object[][] { { Integer.class, String.class, String.class, String.class } });

		addFractureModel = createTableModel(new String[] { "Code", "Name", "Department", "Location", "Requires cast" },
				new Class[] { Integer.class, String.class, String.class, String.class, String.class });
		table = new JTable(addFractureModel);
		populateTableString(addFractureModel, fracturesHashMap,
				new Object[][] { { Integer.class, String.class, String.class, String.class, String.class } });

		//Initialize the Medical Problems scroll panes
		addInjurySP = new JScrollPane(table);
		addInjurySP.setBounds(10, 67, 975, 523);
		addMedProblemToVisitFrame.add(addInjurySP);

		addDiseaseSP = new JScrollPane(table);
		addDiseaseSP.setBounds(10, 67, 975, 523);
		addMedProblemToVisitFrame.add(addDiseaseSP);

		addFractureSP = new JScrollPane(table);
		addFractureSP.setBounds(10, 67, 975, 523);
		addMedProblemToVisitFrame.add(addFractureSP);

		chooseTable2 = new JComboBox<String>();
		chooseTable2.setBackground(new Color(255, 255, 255));
		chooseTable2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chooseTable2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chooseTable2.setModel(new DefaultComboBoxModel<String>(new String[] { "Injuries", "Diseases", "Fractures" }));
		chooseTable2.setBounds(134, 6, 155, 21);
		addMedProblemToVisitFrame.add(chooseTable2);

		JLabel chooseTableLabel2 = new JLabel("Choose data to view:");
		chooseTableLabel2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chooseTableLabel2.setBounds(10, 10, 155, 13);
		addMedProblemToVisitFrame.add(chooseTableLabel2);

		//Show the Medical Problem table after selecting the Type
		chooseTable2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (chooseTable2.getSelectedItem().toString()) {
				case "Injuries":
					updateTable(writeInjury(), addInjurySP);
					addInjurySP.setVisible(true);
					addDiseaseSP.setVisible(false);
					addFractureSP.setVisible(false);
					break;
				case "Diseases":
					updateTable(writeDisease(), addDiseaseSP);
					addInjurySP.setVisible(false);
					addDiseaseSP.setVisible(true);
					addFractureSP.setVisible(false);
					break;
				case "Fractures":
					updateTable(writeFracture(), addFractureSP);
					addInjurySP.setVisible(false);
					addDiseaseSP.setVisible(false);
					addFractureSP.setVisible(true);
					break;
				}
				addMedProblemToVisitFrame.revalidate();
				addMedProblemToVisitFrame.repaint();
			}

		});
		
		// MEDICAL PROBLEM ---> TREATMENT
		addMedProblemToTreatmentFrame = new JPanel();
		addMedProblemToTreatmentFrame.setBackground(Color.WHITE);
		CardView.add(addMedProblemToTreatmentFrame, "addMedProblemToTreatmentFrame");
		addMedProblemToTreatmentFrame.setLayout(null);

		JLabel addMedProblemToTreatmentMainLabel = new JLabel("Choose a medical problem to add:");
		addMedProblemToTreatmentMainLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		addMedProblemToTreatmentMainLabel.setBounds(10, 31, 350, 25);
		addMedProblemToTreatmentFrame.add(addMedProblemToTreatmentMainLabel);

		//Initialize the Medical Problem tables
		addInjuryModel = createTableModel(
				new String[] { "Code", "Name", "Department", "Common recovery time (months)", "Location" },
				new Class[] { Integer.class, String.class, String.class, String.class, String.class });
		table = new JTable(addInjuryModel);
		populateTableString(addInjuryModel, injuriesHashMap,
				new Object[][] { { Integer.class, String.class, String.class, String.class, String.class } });

		addDiseaseModel = createTableModel(new String[] { "Code", "Name", "Department", "Description" },
				new Class[] { Integer.class, String.class, String.class, String.class });
		table = new JTable(addDiseaseModel);
		populateTableString(addDiseaseModel, diseasesHashMap,
				new Object[][] { { Integer.class, String.class, String.class, String.class } });

		addFractureModel = createTableModel(new String[] { "Code", "Name", "Department", "Location", "Requires cast" },
				new Class[] { Integer.class, String.class, String.class, String.class, String.class });
		table = new JTable(addFractureModel);
		populateTableString(addFractureModel, fracturesHashMap,
				new Object[][] { { Integer.class, String.class, String.class, String.class, String.class } });

		//Initialize the Medical Problems scroll panes
		addInjurySPTreatments = new JScrollPane(table);
		addInjurySPTreatments.setBounds(10, 67, 975, 523);
		addMedProblemToTreatmentFrame.add(addInjurySPTreatments);

		addDiseaseSPTreatments = new JScrollPane(table);
		addDiseaseSPTreatments.setBounds(10, 67, 975, 523);
		addMedProblemToTreatmentFrame.add(addDiseaseSPTreatments);

		addFractureSPTreatments = new JScrollPane(table);
		addFractureSPTreatments.setBounds(10, 67, 975, 523);
		addMedProblemToTreatmentFrame.add(addFractureSPTreatments);

		chooseTable4 = new JComboBox<String>();
		chooseTable4.setBackground(new Color(255, 255, 255));
		chooseTable4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chooseTable4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chooseTable4.setModel(new DefaultComboBoxModel<String>(new String[] { "Injuries", "Diseases", "Fractures" }));
		chooseTable4.setBounds(134, 6, 155, 21);
		addMedProblemToTreatmentFrame.add(chooseTable4);

		JLabel chooseTableLabel4 = new JLabel("Choose data to view:");
		chooseTableLabel4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chooseTableLabel4.setBounds(10, 10, 155, 13);
		addMedProblemToTreatmentFrame.add(chooseTableLabel4);

		//Show the Medical Problem table after selecting the Type
		chooseTable4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (chooseTable4.getSelectedItem().toString()) {
				case "Injuries":
					updateTable(writeInjury(), addInjurySPTreatments);
					addInjurySPTreatments.setVisible(true);
					addDiseaseSPTreatments.setVisible(false);
					addFractureSPTreatments.setVisible(false);
					break;
				case "Diseases":
					updateTable(writeDisease(), addDiseaseSPTreatments);
					addInjurySPTreatments.setVisible(false);
					addDiseaseSPTreatments.setVisible(true);
					addFractureSPTreatments.setVisible(false);
					break;
				case "Fractures":
					updateTable(writeFracture(), addFractureSPTreatments);
					addInjurySPTreatments.setVisible(false);
					addDiseaseSPTreatments.setVisible(false);
					addFractureSPTreatments.setVisible(true);
					break;
				}
				addMedProblemToTreatmentFrame.revalidate();
				addMedProblemToTreatmentFrame.repaint();
			}

		});


		// STAFF MEMBER --> DEPARTMENT
		addStaffMemberToDepartmentFrame = new JPanel();
		addStaffMemberToDepartmentFrame.setBackground(Color.WHITE);
		CardView.add(addStaffMemberToDepartmentFrame, "addStaffMemberToDepartmentFrame");
		addStaffMemberToDepartmentFrame.setLayout(null);

		JLabel addStaffMemberToDepartmentFrameMainLabel = new JLabel("Choose a staff member to add:");
		addStaffMemberToDepartmentFrameMainLabel.setFont(titleFont);
		addStaffMemberToDepartmentFrameMainLabel.setBounds(10, 31, 360, 25);
		addStaffMemberToDepartmentFrame.add(addStaffMemberToDepartmentFrameMainLabel);

		//Initialize the Staff Members tables
		addDoctorModel = createTableModel(
				new String[] { "ID", "First name", "Last name", "Date of birth", "Address", "Phone number", "Email",
						"Gender", "Started Working", "Salary", "License Number", "Finished internship",
						"Intensive Care", "Specialization" },
				new Class[] { Integer.class, String.class, String.class, Date.class, String.class, String.class,
						String.class, String.class, Date.class, Double.class, Integer.class, Boolean.class,
						Boolean.class, Specialization.class });
		table = new JTable(addDoctorModel);
		populateTable(addDoctorModel, doctorsHashMap,
				new Object[][] { { Integer.class, String.class, String.class, Date.class, String.class, String.class,
						String.class, String.class, Date.class, Double.class, Integer.class, Boolean.class,
						Boolean.class, Specialization.class } });

		addNurseModel = createTableModel(
				new String[] { "ID", "First name", "Last name", "Date of birth", "Address", "Phone number", "Email",
						"Gender", "Started Working", "Salary", "License Number", "Intensive Care" },
				new Class[] { Integer.class, String.class, String.class, Date.class, String.class, String.class,
						String.class, String.class, Date.class, Double.class, Integer.class, Boolean.class });
		table = new JTable(addNurseModel);
		populateTable(addNurseModel, nursesHashMap,
				new Object[][] { { Integer.class, String.class, String.class, Date.class, String.class, String.class,
						String.class, String.class, Date.class, Double.class, Integer.class, Boolean.class } });

		//Initialize the Staff Members scroll panes
		addDoctorSP = new JScrollPane(table);
		addDoctorSP.setBounds(10, 67, 975, 523);
		addStaffMemberToDepartmentFrame.add(addDoctorSP);

		addNurseSP = new JScrollPane(table);
		addNurseSP.setBounds(10, 67, 975, 523);
		addStaffMemberToDepartmentFrame.add(addNurseSP);

		chooseTable3 = new JComboBox<String>();
		chooseTable3.setBackground(new Color(255, 255, 255));
		chooseTable3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chooseTable3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chooseTable3.setModel(new DefaultComboBoxModel<String>(new String[] { "Doctors", "Nurses" }));
		chooseTable3.setBounds(134, 6, 155, 21);
		addStaffMemberToDepartmentFrame.add(chooseTable3);

		JLabel chooseTableLabel3 = new JLabel("Choose data to view:");
		chooseTableLabel3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chooseTableLabel3.setBounds(10, 10, 155, 13);
		addStaffMemberToDepartmentFrame.add(chooseTableLabel3);

		//Show the Staff Member table after selecting the Type
		chooseTable3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (chooseTable3.getSelectedItem().toString()) {
				case "Doctors":
					updateTable(writeDoctor(), addDoctorSP);
					addDoctorSP.setVisible(true);
					addNurseSP.setVisible(false);
					break;
				case "Nurses":
					updateTable(writeNurse(), addNurseSP);
					addDoctorSP.setVisible(false);
					addNurseSP.setVisible(true);
					break;
				}
				addStaffMemberToDepartmentFrame.revalidate();
				addStaffMemberToDepartmentFrame.repaint();
			}

		});

		// MEDICATION ---> TREATMENT
		addMedicationToTreatmentFrame = new JPanel();
		addMedicationToTreatmentFrame.setBackground(Color.WHITE);
		CardView.add(addMedicationToTreatmentFrame, "addMedicationToTreatmentFrame");
		addMedicationToTreatmentFrame.setLayout(null);

		JLabel addMedicationToTreatmentMainLabel = new JLabel("Choose a medication to add:");
		addMedicationToTreatmentMainLabel.setFont(titleFont);
		addMedicationToTreatmentMainLabel.setBounds(10, 31, 329, 25);
		addMedicationToTreatmentFrame.add(addMedicationToTreatmentMainLabel);

		//Initialize the Medication table
		DefaultTableModel addMedicationToTreatmentModel = createTableModel(
				new String[] { "Code", "Name", "Dosage", "Number of doses" },
				new Class[] { Integer.class, String.class, Double.class, Integer.class });
		table = new JTable(addMedicationToTreatmentModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoCreateRowSorter(true);
		populateTable(addMedicationToTreatmentModel, hosp.getMedications(),
				new Object[][] { { Integer.class, String.class, Double.class, Integer.class } });

		//Initialize the Medication scroll pane
		addMedicationToTreatmentSP = new JScrollPane(table);
		addMedicationToTreatmentSP.setBounds(10, 67, 975, 523);
		addMedicationToTreatmentFrame.add(addMedicationToTreatmentSP);

		// TREATMENT ----> MEDICAL PROBLEM, VISIT
		addTreatmentToFrame = new JPanel();
		addTreatmentToFrame.setBackground(Color.WHITE);
		CardView.add(addTreatmentToFrame, "addTreatmentToFrame");
		addTreatmentToFrame.setLayout(null);

		JLabel addTreatmentMainLabel = new JLabel("Choose a treatment to add:");
		addTreatmentMainLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		addTreatmentMainLabel.setBounds(10, 31, 329, 25);
		addTreatmentToFrame.add(addTreatmentMainLabel);

		//Initialize the Treatment table
		DefaultTableModel addTreatmentModel = createTableModel(new String[] { "Serial Number", "Description" },
				new Class[] { Integer.class, String.class });
		table = new JTable(addTreatmentModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoCreateRowSorter(true);
		populateTable(addTreatmentModel, hosp.getTreatments(), new Object[][] { { Integer.class, String.class } });

		//Initialize the Treatment scroll pane
		addTreatmentSP = new JScrollPane(table);
		addTreatmentSP.setBounds(10, 67, 975, 523);
		addTreatmentToFrame.add(addTreatmentSP);

		// ************* DOCTOR/NURSE DETAILS FRAMES ***********

		saveDetails = styleButton(new JButton("Save"), new Color(255, 255, 255), Color.WHITE);
		saveDetails.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		saveDetails.setBounds(881, 615, 89, 23);
		addHoverEffect(saveDetails);
		
		JLabel queriesFrameImg = new JLabel("");
		queriesFrameImg.setIcon(new ImageIcon(BasicWindow.class.getResource("/resources/Thinking face-rafiki (3).png")));
		queriesFrameImg.setBounds(665, 355, 439, 302);
		queriesFrame.add(queriesFrameImg);

		if (userID != 0) {//If the user entered is not the Admin
			StaffMember user = hosp.getStaffMember(userID);//Find the Staff Member that entered the System

			// labels
			JLabel staffDetailsLabel = new JLabel("My details");
			staffDetailsLabel.setFont(titleFont);
			staffDetailsLabel.setBounds(70, 32, 228, 38);

			JLabel staffIDLabel = new JLabel("ID:");
			staffIDLabel.setFont(labelFont);
			staffIDLabel.setBounds(101, 137, 153, 20);

			JLabel staffFNLabel = new JLabel("First name:");
			staffFNLabel.setFont(labelFont);
			staffFNLabel.setBounds(101, 198, 153, 20);

			JLabel staffLNLabel = new JLabel("Last name:");
			staffLNLabel.setFont(labelFont);
			staffLNLabel.setBounds(101, 263, 153, 20);

			JLabel staffAddressLabel = new JLabel("Address:");
			staffAddressLabel.setFont(labelFont);
			staffAddressLabel.setBounds(345, 137, 153, 20);

			JLabel staffPhoneLabel = new JLabel("Phone number:");
			staffPhoneLabel.setFont(labelFont);
			staffPhoneLabel.setBounds(345, 198, 153, 20);

			JLabel staffEmailLabel = new JLabel("E-mail address:");
			staffEmailLabel.setFont(labelFont);
			staffEmailLabel.setBounds(345, 263, 153, 20);

			JLabel staffDOBLabel = new JLabel("Date of birth:");
			staffDOBLabel.setFont(labelFont);
			staffDOBLabel.setBounds(101, 339, 153, 20);

			JLabel staffWorkStartLabel = new JLabel("Work start date:");
			staffWorkStartLabel.setFont(labelFont);
			staffWorkStartLabel.setBounds(345, 339, 153, 20);

			JLabel staffGenderLabel = new JLabel("Gender:");
			staffGenderLabel.setFont(labelFont);
			staffGenderLabel.setBounds(630, 137, 153, 20);

			JLabel staffLicenseLabel = new JLabel("License number:");
			staffLicenseLabel.setFont(labelFont);
			staffLicenseLabel.setBounds(630, 203, 153, 20);

			JLabel staffSalaryLabel = new JLabel("Salary:");
			staffSalaryLabel.setFont(labelFont);
			staffSalaryLabel.setBounds(630, 268, 153, 20);

			JLabel staffIntensiveCareLabel = new JLabel("Intensive care:");
			staffIntensiveCareLabel.setFont(labelFont);
			staffIntensiveCareLabel.setBounds(630, 431, 106, 20);

			// components
			JTextField staffIDDetail = new JTextField();
			staffIDDetail.setText(String.valueOf(userID));
			staffIDDetail.setColumns(10);
			staffIDDetail.setBounds(101, 167, 153, 20);
			staffIDDetail.setEditable(false);

			JTextField staffFNDetail = new JTextField();
			staffFNDetail.setText(user.getFirstName());
			staffFNDetail.setColumns(10);
			staffFNDetail.setBounds(101, 229, 153, 20);

			JTextField staffLNDetail = new JTextField();
			staffLNDetail.setText(user.getLastName());
			staffLNDetail.setColumns(10);
			staffLNDetail.setBounds(101, 294, 153, 20);

			JTextField staffAddressDetail = new JTextField();
			staffAddressDetail.setText(user.getAddress());
			staffAddressDetail.setColumns(10);
			staffAddressDetail.setBounds(345, 167, 153, 20);

			JTextField staffPhoneDetail = new JTextField();
			staffPhoneDetail.setText(user.getPhoneNumber());
			staffPhoneDetail.setColumns(10);
			staffPhoneDetail.setBounds(345, 229, 153, 20);

			JTextField staffEmailDetail = new JTextField();
			staffEmailDetail.setText(user.getEmail());
			staffEmailDetail.setColumns(10);
			staffEmailDetail.setBounds(345, 294, 153, 20);

			JTextField staffSalaryDetail = new JTextField();
			staffSalaryDetail.setText(String.valueOf(user.getSalary()));
			staffSalaryDetail.setColumns(10);
			staffSalaryDetail.setBounds(630, 294, 153, 20);

			JComboBox<String> staffGenderDetail = new JComboBox<String>();
			staffGenderDetail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			staffGenderDetail.setModel(
					new DefaultComboBoxModel<String>(new String[] { "Male", "Female", "Other", "Prefer not to say" }));
			staffGenderDetail.setFont(new Font("Tahoma", Font.PLAIN, 14));
			staffGenderDetail.setBackground(Color.WHITE);
			staffGenderDetail.setBounds(630, 168, 153, 22);
			staffGenderDetail.setSelectedItem((user).getGender());

			JDateChooser staffDOBDetail = new JDateChooser();
			staffDOBDetail.setDateFormatString("dd/MM/yyyy");
			staffDOBDetail.setBounds(101, 365, 153, 20); //
			staffDOBDetail.setDate(user.getBirthDate());

			JDateChooser staffWorkStartDetail = new JDateChooser();
			staffWorkStartDetail.setDateFormatString("dd/MM/yyyy");
			staffWorkStartDetail.setBounds(345, 365, 153, 20);
			staffWorkStartDetail.setDate(user.getWorkStartDate());

			/*** DEPARTMENTS LIST ****/

			JLabel allDepartmentsLabel = new JLabel("All departments");
			allDepartmentsLabel.setFont(labelFont);
			allDepartmentsLabel.setBounds(101, 410, 180, 20);

			JLabel myDepartmentsLabel = new JLabel("My departments");
			myDepartmentsLabel.setFont(labelFont);
			myDepartmentsLabel.setBounds(410, 410, 180, 20);

			DefaultListModel<Integer> depListModel = new DefaultListModel<Integer>();

			JList<Integer> depList = new JList<Integer>(depListModel);
			depList.setFont(labelFont);
			depList.setVisibleRowCount(99);
			depList.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)),BorderFactory.createEmptyBorder(5, 10, 5, 10)));
			depList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			depList.setBounds(101, 440, 180, 200);

			DefaultListModel<Integer> userDepListModel = new DefaultListModel<Integer>();

			JList<Integer> userDepList = new JList<Integer>(userDepListModel);
			userDepList.setFont(labelFont);
			userDepList.setVisibleRowCount(99);
			userDepList.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(204, 204, 204)),BorderFactory.createEmptyBorder(5, 10, 5, 10)));
			userDepList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			userDepList.setBounds(410, 440, 180, 200);

			JButton addDepButton = styleButton(new JButton("Add>>>"), new Color(255, 255, 255), Color.WHITE);
			addDepButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			addDepButton.setBounds(295, 470, 100, 30);
			addHoverEffect(addDepButton);

			JButton removeDepButton = styleButton(new JButton("Remove<<<"), new Color(255, 255, 255), Color.WHITE);
			removeDepButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			removeDepButton.setBounds(295, 510, 100, 30);
			addHoverEffect(removeDepButton);

			//Get the list of the Departments that the User contains, and the list of the Departments that don't
			Set<Integer> departmentIDList = hosp.getDepartments().keySet();
			HashSet<Integer> userDepartmentIDList = user.getDepartments().stream().map(Department::getNumber)
					.collect(Collectors.toCollection(HashSet::new));
			for (Integer j : departmentIDList) {
				if (userDepartmentIDList.contains(j))
					userDepListModel.addElement(j);
				else
					depListModel.addElement(j);
			}

			//Add a Department to the list of Departments that contains the User Contains to the other list
			addDepButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Integer selectedDepartmentIndex = depList.getSelectedValue();
					depListModel.removeElement(selectedDepartmentIndex);
					userDepListModel.addElement(selectedDepartmentIndex);

				}
			});
			//Remove a Department from the list of Departments that contains the User Contains to the other list
			removeDepButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Integer selectedDepartmentIndex = userDepList.getSelectedValue();
					userDepListModel.removeElement(selectedDepartmentIndex);
					depListModel.addElement(selectedDepartmentIndex);

				}
			});

			// doctor specific components

			JTextField doctorLicenseDetail = new JTextField();
			doctorLicenseDetail.setColumns(10);
			doctorLicenseDetail.setBounds(630, 229, 153, 20);

			JLabel doctorSpecLabel_1 = new JLabel("Specialization:");
			doctorSpecLabel_1.setFont(labelFont);
			doctorSpecLabel_1.setBounds(631, 334, 194, 20);

			JLabel doctorInternshipLabel_1 = new JLabel("Finish internship?");
			doctorInternshipLabel_1.setFont(labelFont);
			doctorInternshipLabel_1.setBounds(630, 401, 125, 20);

			JCheckBox doctorInternDetail = new JCheckBox("");
			doctorInternDetail.setCursor(new Cursor(Cursor.HAND_CURSOR));
			doctorInternDetail.setBackground(Color.WHITE);
			doctorInternDetail.setBounds(757, 401, 29, 23);

			JComboBox<Specialization> doctorSpecDetail = new JComboBox<Specialization>();
			doctorSpecDetail.setModel(new DefaultComboBoxModel<Specialization>(Specialization.values()));
			doctorSpecDetail.setFont(new Font("Tahoma", Font.PLAIN, 12));
			doctorSpecDetail.setBackground(Color.WHITE);
			doctorSpecDetail.setBounds(630, 364, 233, 22);

			JCheckBox doctorIntensiveCheck = new JCheckBox("");
			doctorIntensiveCheck.setBackground(Color.WHITE);
			doctorIntensiveCheck.setBounds(742, 432, 29, 23);
			doctorIntensiveCheck.setCursor(new Cursor(Cursor.HAND_CURSOR));
			doctorIntensiveCheck.setSelected(user instanceof IntensiveCareDoctor);
			//If the IntenisveCare is selected, set the Specialization to "IntensiveCare" automatically
			doctorIntensiveCheck.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED) {
						doctorSpecDetail.setSelectedItem(Specialization.IntensiveCare);
						doctorSpecDetail.setEnabled(false);
					} else if (e.getStateChange() == ItemEvent.DESELECTED) {
						doctorSpecDetail.setEnabled(true);
					}
				}
			});

			// nurse specific components

			JTextField nurseLicenseDetail = new JTextField();
			nurseLicenseDetail.setColumns(10);
			nurseLicenseDetail.setBounds(630, 229, 153, 20);

			JCheckBox nurseIntensiveCheck = new JCheckBox("");
			nurseIntensiveCheck.setBackground(Color.WHITE);
			nurseIntensiveCheck.setBounds(742, 341, 29, 23);
			nurseIntensiveCheck.setCursor(new Cursor(Cursor.HAND_CURSOR));
			nurseIntensiveCheck.setSelected(user instanceof IntensiveCareNurse);
			
			// Apply styling to JTextField components
			styleTextField(staffIDDetail);
			styleTextField(staffFNDetail);
			styleTextField(staffLNDetail);
			styleTextField(staffAddressDetail);
			styleTextField(staffPhoneDetail);
			styleTextField(staffEmailDetail);
			styleTextField(staffSalaryDetail);
			styleTextField(doctorLicenseDetail);
			styleTextField(nurseLicenseDetail);

			// Apply styling to JComboBox components
			styleComboBox(staffGenderDetail);
			styleComboBox(doctorSpecDetail);
			
			// Apply styling to JCheckBox components
			styleCheckBox(doctorInternDetail);
			styleCheckBox(doctorIntensiveCheck);
			styleCheckBox(nurseIntensiveCheck);


			// Apply styling to JList components
			styleList(depList);
			styleList(userDepList);

			if (user instanceof Doctor) {//If the User is a Doctor

				// DOCTOR DETAILS
				doctorDetailsFrame = new JPanel();
				doctorDetailsFrame.setBackground(Color.WHITE);
				CardView.add(doctorDetailsFrame, "doctorDetailsFrame");
				doctorDetailsFrame.setLayout(null);

				// Adding Labels
				doctorDetailsFrame.add(staffDetailsLabel);
				doctorDetailsFrame.add(staffIDLabel);
				doctorDetailsFrame.add(staffFNLabel);
				doctorDetailsFrame.add(staffLNLabel);
				doctorDetailsFrame.add(staffAddressLabel);
				doctorDetailsFrame.add(staffPhoneLabel);
				doctorDetailsFrame.add(staffEmailLabel);
				doctorDetailsFrame.add(staffDOBLabel);
				doctorDetailsFrame.add(staffWorkStartLabel);
				doctorDetailsFrame.add(staffGenderLabel);
				doctorDetailsFrame.add(staffLicenseLabel);
				doctorDetailsFrame.add(staffSalaryLabel);
				doctorDetailsFrame.add(staffIntensiveCareLabel);
				doctorDetailsFrame.add(allDepartmentsLabel);
				doctorDetailsFrame.add(myDepartmentsLabel);
				doctorDetailsFrame.add(doctorSpecLabel_1);
				doctorDetailsFrame.add(doctorInternshipLabel_1);

				// Adding Text Fields
				doctorDetailsFrame.add(staffIDDetail);
				doctorDetailsFrame.add(staffFNDetail);
				doctorDetailsFrame.add(staffLNDetail);
				doctorDetailsFrame.add(staffAddressDetail);
				doctorDetailsFrame.add(staffPhoneDetail);
				doctorDetailsFrame.add(staffEmailDetail);
				doctorDetailsFrame.add(doctorLicenseDetail);
				doctorLicenseDetail.setText(String.valueOf(((Doctor) user).getLicenseNumber()));
				doctorDetailsFrame.add(staffSalaryDetail);

				// Adding ComboBox and CheckBox
				doctorDetailsFrame.add(staffGenderDetail);
				doctorDetailsFrame.add(doctorSpecDetail);
				doctorSpecDetail.setSelectedItem(((Doctor) user).getSpecialization());
				doctorDetailsFrame.add(doctorInternDetail);
				doctorInternDetail.setSelected(((Doctor) user).isFinishInternship());
				doctorDetailsFrame.add(doctorIntensiveCheck);

				// Adding JDateChoosers
				doctorDetailsFrame.add(staffDOBDetail);
				doctorDetailsFrame.add(staffWorkStartDetail);

				// Adding JLists
				doctorDetailsFrame.add(depList);
				doctorDetailsFrame.add(userDepList);

				// Adding Buttons
				doctorDetailsFrame.add(addDepButton);
				doctorDetailsFrame.add(removeDepButton);

			}

			if (user instanceof Nurse) {//If the User is a Nurse

				// NURSE DETAILS
				nurseDetailsFrame = new JPanel();
				nurseDetailsFrame.setBackground(Color.WHITE);
				CardView.add(nurseDetailsFrame, "nurseDetailsFrame");
				nurseDetailsFrame.setLayout(null);

				staffIntensiveCareLabel.setBounds(630, 340, 106, 20);

				// Adding Labels
				nurseDetailsFrame.add(staffDetailsLabel);
				nurseDetailsFrame.add(staffIDLabel);
				nurseDetailsFrame.add(staffFNLabel);
				nurseDetailsFrame.add(staffLNLabel);
				nurseDetailsFrame.add(staffAddressLabel);
				nurseDetailsFrame.add(staffPhoneLabel);
				nurseDetailsFrame.add(staffEmailLabel);
				nurseDetailsFrame.add(staffDOBLabel);
				nurseDetailsFrame.add(staffWorkStartLabel);
				nurseDetailsFrame.add(staffGenderLabel);
				nurseDetailsFrame.add(staffLicenseLabel);
				nurseDetailsFrame.add(staffSalaryLabel);
				nurseDetailsFrame.add(staffIntensiveCareLabel);
				nurseDetailsFrame.add(allDepartmentsLabel);
				nurseDetailsFrame.add(myDepartmentsLabel);

				// Adding Text Fields
				nurseDetailsFrame.add(staffIDDetail);
				nurseDetailsFrame.add(staffFNDetail);
				nurseDetailsFrame.add(staffLNDetail);
				nurseDetailsFrame.add(staffAddressDetail);
				nurseDetailsFrame.add(staffPhoneDetail);
				nurseDetailsFrame.add(staffEmailDetail);
				nurseDetailsFrame.add(staffSalaryDetail);
				nurseLicenseDetail.setText(String.valueOf(((Nurse) user).getLicenseNumber()));
				nurseDetailsFrame.add(nurseLicenseDetail);

				// Adding ComboBox and CheckBox
				nurseDetailsFrame.add(staffGenderDetail);
				nurseDetailsFrame.add(nurseIntensiveCheck);

				// Adding JDateChoosers
				nurseDetailsFrame.add(staffDOBDetail);
				nurseDetailsFrame.add(staffWorkStartDetail);

				// Adding JLists
				nurseDetailsFrame.add(depList);
				nurseDetailsFrame.add(userDepList);

				// Adding Buttons
				nurseDetailsFrame.add(addDepButton);
				nurseDetailsFrame.add(removeDepButton);

			}

			//Change User Personal Details
			saveDetails.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					StaffMember updatedUser = null;
					try {//Get all values for a new Staff Member
						int changedID = Integer.parseInt(staffIDDetail.getText().trim());
						String changedFName = staffFNDetail.getText().trim();
						String changedLName = staffLNDetail.getText().trim();
						Date changedDOB = staffDOBDetail.getDate();
						String changedAddress = staffAddressDetail.getText().trim();
						String changedPhone = staffPhoneDetail.getText().trim();
						String changedEmail = staffEmailDetail.getText().trim();
						String changedGender = (String) staffGenderDetail.getSelectedItem();
						Date changedWorkStart = staffWorkStartDetail.getDate();
						String salaryText = staffSalaryDetail.getText().trim();
						String licenseText = user instanceof Doctor ? doctorLicenseDetail.getText().trim()
								: nurseLicenseDetail.getText().trim();
						boolean isIntensiveCare = user instanceof Doctor ? doctorIntensiveCheck.isSelected()
								: nurseIntensiveCheck.isSelected();

						HashSet<Department> changedDepartmentsList = new HashSet<>(userDepListModel.size());
						for (int i = 0; i < userDepListModel.size(); i++) {
							Integer depNum = userDepListModel.getElementAt(i);
							changedDepartmentsList.add(hosp.getRealDepartment(depNum));
						}

						//If one of the values is not given, show an error message
						if (changedFName.isEmpty() || changedLName.isEmpty() || changedDOB == null
								|| changedAddress.isEmpty() || changedPhone.isEmpty() || changedEmail.isEmpty()
								|| changedGender.isEmpty() || changedWorkStart == null || salaryText.isEmpty()
								|| licenseText.isEmpty()) {
							throw new EmptyFieldsException();
						}

						//Check for a valid Salary
						double changedSalary = Double.parseDouble(salaryText);
						if (changedSalary <= 0) {
							throw new InvalidSalaryException();
						}

						int changedLicenseNumber = Integer.parseInt(licenseText);

						//Check for a valid Birth and Work Start Dates
						Date currentDate = new Date();
						if (changedWorkStart.after(currentDate)) {
							throw new FutureDateException(changedWorkStart);
						}
						if (changedWorkStart.before(changedDOB)) {
							throw new InvalidDateException(changedWorkStart, changedDOB);
						}

						if (user instanceof IntensiveCareDoctor || user instanceof IntensiveCareNurse) {
							boolean containsIntensiveDepartment = false;
							for (Department d : changedDepartmentsList) {
								if (d.getSpecialization().equals(Specialization.IntensiveCare)) {
									containsIntensiveDepartment = true;
									break;
								}
							}
							if (containsIntensiveDepartment == false && isIntensiveCare)//If no IntensiveCare Department is chosen for an IntensiveCare StaffMember, show an error message
								throw new NoIntensiveCareDepartmentInIntensiveCareStaffMemberException();
						}

						if (isIntensiveCare == (user instanceof IntensiveCareDoctor//Set the new values for a StaffMember
								|| user instanceof IntensiveCareNurse)) {
							user.setFirstName(changedFName);
							user.setLastName(changedLName);
							user.setBirthDate(changedDOB);
							user.setAddress(changedAddress);
							user.setPhoneNumber(changedPhone);
							user.setEmail(changedEmail);
							user.setGender(changedGender);
							user.setWorkStartDate(changedWorkStart);
							user.setDepartments(changedDepartmentsList);
							user.setSalary(changedSalary);
							if (user instanceof Doctor) {
								((Doctor) user).setLicenseNumber(changedLicenseNumber);
								if (user instanceof Doctor && !(user instanceof IntensiveCareDoctor)) {
									((Doctor) user).setFinishInternship(doctorInternDetail.isSelected());
									((Doctor) user)
											.setSpecialization((Specialization) doctorSpecDetail.getSelectedItem());
								}
							} else if (user instanceof Nurse) {
								((Nurse) user).setLicenseNumber(changedLicenseNumber);
							}
							updatedUser = user;
						} else {
							if (user instanceof Doctor) {
								updatedUser = isIntensiveCare
										? new IntensiveCareDoctor(changedID, changedFName, changedLName, changedDOB, //Set the Doctor to be IntensiveCareDoctor
												changedAddress, changedPhone, changedEmail, changedGender,
												changedWorkStart, changedDepartmentsList, changedSalary,
												changedLicenseNumber, doctorInternDetail.isSelected())
										: new Doctor(changedID, changedFName, changedLName, changedDOB, changedAddress, //Set the IntensiveCareDoctor to be Doctor
												changedPhone, changedEmail, changedGender, changedWorkStart,
												changedDepartmentsList, changedSalary, changedLicenseNumber,
												doctorInternDetail.isSelected(),
												(Specialization) doctorSpecDetail.getSelectedItem());
							} else {
								updatedUser = isIntensiveCare
										? new IntensiveCareNurse(changedID, changedFName, changedLName, changedDOB, //Set the Nurse to be IntensiveCareNurse
												changedAddress, changedPhone, changedEmail, changedGender,
												changedWorkStart, changedDepartmentsList, changedSalary,
												changedLicenseNumber)
										: new Nurse(changedID, changedFName, changedLName, changedDOB, changedAddress,//Set the IntensiveCareNurse to be Nurse
												changedPhone, changedEmail, changedGender, changedWorkStart,
												changedDepartmentsList, changedSalary, changedLicenseNumber);
							}
							hosp.getStaffMembers().put(changedID, updatedUser);
							if (user instanceof Doctor) {
								doctorsHashMap.put(changedID, (Doctor) updatedUser);
							} else {
								nursesHashMap.put(changedID, (Nurse) updatedUser);
							}
						}

						serialize(hosp);////Update the table after changes
						updateTable(user instanceof Doctor ? writeDoctor() : writeNurse(),
								user instanceof Doctor ? doctorSP : nurseSP);
						JOptionPane.showMessageDialog(null,
								"Successfully changed the Personal details of "
										+ (user instanceof Doctor ? "Doctor: " : "Nurse: ") + changedFName + " "
										+ changedLName);

					//Check for valid values
					} catch (EmptyFieldsException | InvalidDateException | FutureDateException | InvalidSalaryException
							| NoIntensiveCareDepartmentInIntensiveCareStaffMemberException x) {
						JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
					} catch (NumberFormatException x) {
						JOptionPane.showMessageDialog(null, "Wrong Input!", "Error!", JOptionPane.ERROR_MESSAGE);
					} finally {//Reset all Text Fields
						staffFNDetail.setText(user.getFirstName());
						staffLNDetail.setText(user.getLastName());
						staffAddressDetail.setText(user.getAddress());
						staffPhoneDetail.setText(user.getPhoneNumber());
						staffEmailDetail.setText(user.getEmail());
						if (updatedUser != null) {
							if (updatedUser instanceof Doctor) {
								doctorLicenseDetail.setText(String.valueOf(((Doctor) user).getLicenseNumber()));
							} else if (updatedUser instanceof Nurse) {
								nurseLicenseDetail.setText(String.valueOf(((Nurse) user).getLicenseNumber()));
							}
							userDepListModel.clear();
							depListModel.clear();
							Set<Integer> departmentIDList = hosp.getDepartments().keySet();
							HashSet<Integer> userDepartmentIDList = updatedUser.getDepartments().stream()
									.map(Department::getNumber).collect(Collectors.toCollection(HashSet::new));
							for (Integer j : departmentIDList) {
								if (userDepartmentIDList.contains(j))
									userDepListModel.addElement(j);
								else
									depListModel.addElement(j);
							}
						}
						staffSalaryDetail.setText(String.valueOf(user.getSalary()));
						staffDOBDetail.setDate(user.getBirthDate());
						staffWorkStartDetail.setDate(user.getWorkStartDate());

					}
				}
			});

		}

	}

	// ************* HELPER METHODS ***********
	
	//Edit Person Elements
	public <P> void editPerson(Class c, int row, int collum, HashMap<Integer, P> hashMap) {
		String newValue = JOptionPane.showInputDialog(null, "Please enter a new value");
		if (newValue != null) {
			switch (collum) {
			//Edit Person First Name
			case 1: {
				if (!((Person) hashMap.get(table.getValueAt(row, 0))).getFirstName().equals(newValue)) {
					((Person) hashMap.get(table.getValueAt(row, 0))).setFirstName(newValue);//Set the Person First Name
					JOptionPane.showMessageDialog(null, "Successfully changed " + c.getSimpleName() + " First Name");
				} else {//If the same value is given, show an error message
					throw new ChangeToSameValueException();
				}
				break;
			}
			//Edit Person Last Name
			case 2:
				if (!((Person) hashMap.get(table.getValueAt(row, 0))).getLastName().equals(newValue)) {
					((Person) hashMap.get(table.getValueAt(row, 0))).setLastName(newValue);//Set the Person Last Name
					JOptionPane.showMessageDialog(null, "Successfully changed " + c.getSimpleName() + " Last Name");
				} else {//If the same value is given, show an error message
					throw new ChangeToSameValueException();
				}
				break;
			//Edit Person Address
			case 4:
				if (!((Person) hashMap.get(table.getValueAt(row, 0))).getAddress().equals(newValue)) {
					((Person) hashMap.get(table.getValueAt(row, 0))).setAddress(newValue);//Set the Person Address
					JOptionPane.showMessageDialog(null, "Successfully changed " + c.getSimpleName() + " Address");
				} else {//If the same value is given, show an error message
					throw new ChangeToSameValueException();
				}
				break;
			//Edit Person Phone Number
			case 5: {
				if (!((Person) hashMap.get(table.getValueAt(row, 0))).getPhoneNumber().equals(newValue)) {
					((Person) hashMap.get(table.getValueAt(row, 0))).setPhoneNumber(newValue);//Set the Person Phone Number
					JOptionPane.showMessageDialog(null, "Successfully changed " + c.getSimpleName() + " Phone Number");
				} else {//If the same value is given, show an error message
					throw new ChangeToSameValueException();
				}
				break;
			}
			//Edit Person Email
			case 6: {
				if (!((Person) hashMap.get(table.getValueAt(row, 0))).getEmail().equals(newValue)) {
					((Person) hashMap.get(table.getValueAt(row, 0))).setEmail(newValue);//Set the Person Email
					JOptionPane.showMessageDialog(null, "Successfully changed " + c.getSimpleName() + " Email");
				} else {//If the same value is given, show an error message
					throw new ChangeToSameValueException();
				}
				break;
			}
			}
		}
	}

	//Edit Staff Member Elements
	public <P> void editStaffMember(Class c, int row, int collum, HashMap<Integer, P> hashMap) {
		switch (collum) {
		//Edit StaffMember Birth Date
		case 3: {
			try {
				JCalendar calendar = new JCalendar();
				StaffMember s = (StaffMember) hashMap.get(table.getValueAt(row, 0));
				calendar.setDate(s.getBirthDate());//Create a Calendar to get the Birth Date to change the StaffMember to
				String message = "Please select a DOB";
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Object[] params = { message, calendar };
				int result = JOptionPane.showConfirmDialog(null, params, "DOB", JOptionPane.PLAIN_MESSAGE);

				if (result == JOptionPane.OK_OPTION) {//Confirm change
					Date newBirthDate = calendar.getDate();

					//Check for valid Birth Date
					if (newBirthDate.after(s.getWorkStartDate())) {
						throw new InvalidDateException(newBirthDate, s.getWorkStartDate());
					}

					String newDateStr = sdf.format(newBirthDate);
					String currentDateStr = sdf.format(s.getBirthDate());

					if (!newDateStr.equals(currentDateStr)) {
						s.setBirthDate(newBirthDate);//Set the StaffMember Birth Date
						JOptionPane.showMessageDialog(null, "Successfully changed " + c.getSimpleName() + "'s DOB!");
					} else {//If the same value is given, show an error message
						throw new ChangeToSameValueException();
					}
				}
			} catch (FutureDateException x) {
				JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
			} catch (InvalidDateException x) {
				JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
			}
			 
			

			break;
		}
		//Edit StaffMember Gender
		case 7: {
			String[] genderOptions = { "Male", "Female", "Other", "Prefer not to say" };
			JComboBox<String> genderComboBox = new JComboBox<>(genderOptions);//Create a Combo Box of Genders

			int result = JOptionPane.showConfirmDialog(null, genderComboBox, "Please select a new Gender",
					JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {//Confirm change
				String newGender = (String) genderComboBox.getSelectedItem();

				if (!((StaffMember) hashMap.get(table.getValueAt(row, 0))).getGender().equals(newGender)) {
					((StaffMember) hashMap.get(table.getValueAt(row, 0))).setGender(newGender);//Set the StaffMember Gender
					JOptionPane.showMessageDialog(null, "Successfully changed " + c.getSimpleName() + "'s Gender!");
				} else {//If the same value is given, show an error message
					throw new ChangeToSameValueException();
				}
			}
			break;

		}
		//Edit StaffMember Work Start Date
		case 8: {
			try {
				JCalendar calendar = new JCalendar();
				StaffMember s = (StaffMember) hashMap.get(table.getValueAt(row, 0));
				calendar.setDate(s.getWorkStartDate());//Create a Calendar to get the Work Start Date to change the StaffMember to
				String message = "Please select a Work Start Date";
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Object[] params = { message, calendar };
				int result = JOptionPane.showConfirmDialog(null, params, "Work Start Date", JOptionPane.PLAIN_MESSAGE);

				if (result == JOptionPane.OK_OPTION) {//Confirm change
					Date newWorkStartDate = calendar.getDate();
					Date currentDate = new Date();

					//Check for valid Work Start Date
					if (newWorkStartDate.after(currentDate)) {
						throw new FutureDateException(newWorkStartDate);
					}

					if (newWorkStartDate.before(s.getBirthDate())) {
						throw new InvalidDateException(s.getBirthDate(), newWorkStartDate);
					}

					String newDateStr = sdf.format(newWorkStartDate);
					String currentDateStr = sdf.format(s.getWorkStartDate());

					if (!newDateStr.equals(currentDateStr)) {
						s.setWorkStartDate(newWorkStartDate);//Set the StaffMember WorkStart Date
						JOptionPane.showMessageDialog(null,
								"Successfully changed " + c.getSimpleName() + "'s Work Start Date!");
					} else {//If the same value is given, show an error message
						throw new ChangeToSameValueException();
					}
				}
			} catch (InvalidDateException x) {
				JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
			} catch (FutureDateException x) {
				JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
			} catch (ChangeToSameValueException x) {
				JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
			}

			break;
		}
		//Edit StaffMember Salary
		case 9: {
			try {
				String newSalaryStr = JOptionPane.showInputDialog(null, "Please enter a Salary");
				if (newSalaryStr != null) {
					double newSalary = Double.parseDouble(newSalaryStr);
					//Check for valid Salary
					if (newSalary <= 0) {
						throw new InvalidSalaryException();
					}

					if (((StaffMember) hashMap.get(table.getValueAt(row, 0))).getSalary() != newSalary) {
						((StaffMember) hashMap.get(table.getValueAt(row, 0))).setSalary(newSalary);//Set the StaffMember Salary
						JOptionPane.showMessageDialog(null, "Successfully changed " + c.getSimpleName() + "'s Salary!");
					} else {//If the same value is given, show an error message
						throw new ChangeToSameValueException();
					}
				}
			} catch (InvalidSalaryException x) {
				JOptionPane.showMessageDialog(null, x.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
			} catch (NumberFormatException x) {
				JOptionPane.showMessageDialog(null, "Wrong Input!", "Error!", JOptionPane.ERROR_MESSAGE);
			}
			break;
		}
		//For any other Value to change, Change it with the "editPerson" Method
		default:
			editPerson(c, row, collum, hashMap);
		}
	}

	//Edit Medical Problem Elements
	public <P> void editMedicalProblem(int row, int collum, HashMap<String, P> hashMap) {
		switch (collum) {
		//Edit MedicalProblem Name
		case 1: {
			String newName = JOptionPane.showInputDialog(null, "Please enter a new Name");
			if (newName != null) {
				if (!((MedicalProblem) hashMap.get(table.getValueAt(row, 0))).getName().equals(newName)) {
					((MedicalProblem) hashMap.get(table.getValueAt(row, 0))).setName(newName);//Set the MedicalProblem Name
					JOptionPane.showMessageDialog(null, "Successfully changed Medical Problem's Name!");
				} else {//If the same value is given, show an error message
					throw new ChangeToSameValueException();
				}
			}
			break;
		}
		//Edit MedicalProblem Department
		case 2: {
			JComboBox<Integer> departments = new JComboBox<Integer>(
					hosp.getDepartments().keySet().stream().toArray(Integer[]::new));//Create a ComboBox of all Departments to change MedicalProblem to
			int result = JOptionPane.showConfirmDialog(null, departments, "Please select a new Department",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (result == JOptionPane.OK_OPTION) {//Confirm change
				Department newDepartment = hosp.getDepartments().get(departments.getSelectedItem());//Find the Department to change MedicalProblem to
				if (!((MedicalProblem) hashMap.get(table.getValueAt(row, 0))).getDepartment().equals(newDepartment)) {
					((MedicalProblem) hashMap.get(table.getValueAt(row, 0))).setDepartment(newDepartment);//Set MedicalProblem Department
					JOptionPane.showMessageDialog(null, "Successfully changed Medical Problem's Department!");
				} else {//If the same value is given, show an error message
					throw new ChangeToSameValueException();
				}
			}
			break;
		}
		}
	}

	//Set the Table to show the System Department
	public JTable writeDepartment() {
		String[] title = { "Number", "Name", "Manager", "Location", "Specialization" };
		Object[][] departmentMatrix = new Object[hosp.getDepartments().size()][5];
		int i = 0;
		for (Map.Entry<Integer, Department> entry : hosp.getDepartments().entrySet()) {
			departmentMatrix[i][0] = entry.getKey();
			departmentMatrix[i][1] = entry.getValue().getName();
			departmentMatrix[i][2] = (entry.getValue().getmanager() != null)
					? entry.getValue().getmanager().getFirstName() + " " + entry.getValue().getmanager().getLastName()
					: "null";
			departmentMatrix[i][3] = entry.getValue().getLocation();
			departmentMatrix[i][4] = entry.getValue().getSpecialization().name();
			i++;
		}
		table = new JTable(departmentMatrix, title);
		table.setAutoCreateRowSorter(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(departmentMatrix, title) {
			@Override
			public boolean isCellEditable(int row, int column) {
				//Cells cannot be edited by clicking on them
				return false;
			}

			public Class<?> getColumnClass(int columnIndex) {
				//Set the Types of the table cells
				switch (columnIndex) {
				case 0:
					return Integer.class;
				default:
					return super.getColumnClass(columnIndex);
				}

			}
		});

		return table;

	}

	//Set the Table to show the System Departments
	public JTable writeMedication() {
		String[] title = { "Code", "Name", "Dosage", "Number of doses" };
		Object[][] medMatrix = new Object[hosp.getMedications().size()][4];
		int i = 0;
		for (Map.Entry<Integer, Medication> entry : hosp.getMedications().entrySet()) {
			medMatrix[i][0] = entry.getKey();
			medMatrix[i][1] = entry.getValue().getName();
			medMatrix[i][2] = entry.getValue().getDosage();
			medMatrix[i][3] = entry.getValue().getNumberOfDose();
			i++;
		}
		table = new JTable(medMatrix, title);
		table.setAutoCreateRowSorter(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(medMatrix, title) {
			@Override
			public boolean isCellEditable(int row, int column) {
				//Cells cannot be edited by clicking on them
				return false;
			}

			public Class<?> getColumnClass(int columnIndex) {
				//Set the Types of the table cells
				switch (columnIndex) {
				case 0:
					return Integer.class;
				case 2:
					return Double.class;
				case 3:
					return Integer.class;
				default:
					return super.getColumnClass(columnIndex);
				}
			}
		});
		DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
		table.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(leftRenderer);
		return table;
	}

	//Set the Table to show the System Treatments
	public JTable writeTreatment() {
		String[] title = { "Serial Number", "Description" };
		Object[][] treatMatrix = new Object[hosp.getTreatments().size()][2];
		int i = 0;
		for (Map.Entry<Integer, Treatment> entry : hosp.getTreatments().entrySet()) {
			treatMatrix[i][0] = entry.getKey();
			treatMatrix[i][1] = entry.getValue().getDescription();
			i++;
		}
		table = new JTable(treatMatrix, title);
		table.setAutoCreateRowSorter(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(treatMatrix, title) {
			@Override
			public boolean isCellEditable(int row, int column) {
				//Cells cannot be edited by clicking on them
				return false;
			}

			public Class<?> getColumnClass(int columnIndex) {
				//Set the Types of the table cells
				switch (columnIndex) {
				case 0:
					return Integer.class;
				default:
					return super.getColumnClass(columnIndex);
				}
			}
		});

		return table;
	}

	//Set the Table to show the System Injuries
	public JTable writeInjury() {
		String[] title = { "Code", "Name", "Department", "Common recovery time (months)", "Location" };
		Object[][] injuryMatrix = new Object[injuriesHashMap.size()][5];
		int i = 0;
		for (Map.Entry<String, Injury> entry : injuriesHashMap.entrySet()) {
			injuryMatrix[i][0] = entry.getKey();
			injuryMatrix[i][1] = entry.getValue().getName();
			injuryMatrix[i][2] = (entry.getValue().getDepartment() != null) ? entry.getValue().getDepartment().getName()
					: "null";
			injuryMatrix[i][3] = entry.getValue().getCommonRecoveryTime();
			injuryMatrix[i][4] = entry.getValue().getLocation();
			i++;
		}
		table = new JTable(injuryMatrix, title);
		table.setAutoCreateRowSorter(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(true);
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
		sorter.setComparator(0, new Comparator<String>() {
			//Order the Primary Keys by their numbers
			public int compare(String s1, String s2) {
				// Extract numeric part from the string
				int num1 = Integer.parseInt(s1.replaceAll("[^0-9]", ""));
				int num2 = Integer.parseInt(s2.replaceAll("[^0-9]", ""));
				return Integer.compare(num1, num2);
			}
		});

		
		table.setRowSorter(sorter);
		table.setModel(new DefaultTableModel(injuryMatrix, title) {
			@Override
			public boolean isCellEditable(int row, int column) {
				//Cells cannot be edited by clicking on them
				return false;
			}

			public Class<?> getColumnClass(int columnIndex) {
				//Set the Types of the table cells
				switch (columnIndex) {
				case 3:
					return Double.class;
				default:
					return super.getColumnClass(columnIndex);
				}
			}

		});
		DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
		table.getColumnModel().getColumn(3).setCellRenderer(leftRenderer);
		return table;
	}

	//Set the Table to show the System Fractures
	public JTable writeFracture() {
		String[] title = { "Code", "Name", "Department", "Location", "Requires cast" };
		Object[][] fractureMatrix = new Object[fracturesHashMap.size()][5];
		int i = 0;
		for (Map.Entry<String, Fracture> entry : fracturesHashMap.entrySet()) {
			fractureMatrix[i][0] = entry.getKey();
			fractureMatrix[i][1] = entry.getValue().getName();
			fractureMatrix[i][2] = (entry.getValue().getDepartment() != null)
					? entry.getValue().getDepartment().getName()
					: "null";
			fractureMatrix[i][3] = entry.getValue().getLocation();
			fractureMatrix[i][4] = (entry.getValue().isRequiresCast()) ? "Yes" : "No";
			i++;
		}
		table = new JTable(fractureMatrix, title);
		table.setAutoCreateRowSorter(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(fractureMatrix, title) {
			@Override
			public boolean isCellEditable(int row, int column) {
				//Cells cannot be edited by clicking on them
				return false;
			}
		});
		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
		sorter.setComparator(0, new Comparator<String>() {
			//Order the Primary Keys by their numbers
			public int compare(String s1, String s2) {
				// Extract numeric part from the string
				int num1 = Integer.parseInt(s1.replaceAll("[^0-9]", ""));
				int num2 = Integer.parseInt(s2.replaceAll("[^0-9]", ""));
				return Integer.compare(num1, num2);
			}
		});

		table.setRowSorter(sorter);

		return table;
	}

	//Set the Table to show the System Diseases
	public JTable writeDisease() {
		String[] title = { "Code", "Name", "Department", "Description" };
		Object[][] diseaseMatrix = new Object[diseasesHashMap.size()][4];
		int i = 0;

		for (Map.Entry<String, Disease> entry : diseasesHashMap.entrySet()) {
			diseaseMatrix[i][0] = entry.getKey();
			diseaseMatrix[i][1] = entry.getValue().getName();
			diseaseMatrix[i][2] = (entry.getValue().getDepartment() != null)
					? entry.getValue().getDepartment().getName()
					: "null";
			diseaseMatrix[i][3] = entry.getValue().getDescription();
			i++;
		}

		DefaultTableModel model = new DefaultTableModel(diseaseMatrix, title) {
			@Override
			public boolean isCellEditable(int row, int column) {
				//Cells cannot be edited by clicking on them
				return false;
			}
		};

		table = new JTable(model);
		table.setAutoCreateRowSorter(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(true);

		TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
		sorter.setComparator(0, new Comparator<String>() {
			//Order the Primary Keys by their numbers
			public int compare(String s1, String s2) {
				// Extract numeric part from the string
				int num1 = Integer.parseInt(s1.replaceAll("[^0-9]", ""));
				int num2 = Integer.parseInt(s2.replaceAll("[^0-9]", ""));
				return Integer.compare(num1, num2);
			}
		});

		table.setRowSorter(sorter);

		return table;
	}

	//Set the Table to show the System Visits
	public JTable writeVisit() {
		String[] title = { "Number", "Patient", "Start", "End" };
		Object[][] visitMatrix = new Object[hosp.getVisits().size()][4];
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		int i = 0;

		for (Map.Entry<Integer, Visit> entry : hosp.getVisits().entrySet()) {
			visitMatrix[i][0] = entry.getKey();
			visitMatrix[i][1] = (entry.getValue().getPatient() != null)
					? entry.getValue().getPatient().getFirstName() + " " + entry.getValue().getPatient().getLastName()
					: "null";
			visitMatrix[i][2] = entry.getValue().getStartDate();
			visitMatrix[i][3] = entry.getValue().getEndDate();
			i++;
		}

		DefaultTableModel model = new DefaultTableModel(visitMatrix, title) {
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				//Set the Types of the table cells
				switch (columnIndex) {
				case 0:
					return Integer.class;
				case 2:
				case 3:
					return Date.class;
				default:
					return super.getColumnClass(columnIndex);
				}
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				//Cells cannot be edited by clicking on them
				return false;
			}
		};

		table = new JTable(model);
		table.setAutoCreateRowSorter(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(true);

		return table;
	}

	//Set the Table to show the System Patients
	public JTable writePatient() {
		String[] title = { "ID", "First Name", "Last Name", "Date of birth", "Address", "Phone number", "Email",
				"Health Fund", "Biological Sex", "Gender" };
		Object[][] patientMatrix = new Object[hosp.getPatients().size()][10];

		int i = 0;
		for (Map.Entry<Integer, Patient> entry : hosp.getPatients().entrySet()) {
			patientMatrix[i][0] = entry.getKey();
			patientMatrix[i][1] = entry.getValue().getFirstName();
			patientMatrix[i][2] = entry.getValue().getLastName();
			patientMatrix[i][3] = entry.getValue().getBirthDate();
			patientMatrix[i][4] = entry.getValue().getAddress();
			patientMatrix[i][5] = entry.getValue().getPhoneNumber();
			patientMatrix[i][6] = entry.getValue().getEmail();
			patientMatrix[i][7] = entry.getValue().getHealthFund().name();
			patientMatrix[i][8] = entry.getValue().getBiologicalSex().name();
			patientMatrix[i][9] = entry.getValue().getGender();
			i++;
		}
		table = new JTable(patientMatrix, title);
		table.setAutoCreateRowSorter(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(patientMatrix, title) {
			@Override
			public boolean isCellEditable(int row, int column) {
				//Cells cannot be edited by clicking on them
				return false;
			}

			public Class<?> getColumnClass(int columnIndex) {
				//Set the Types of the table cells
				switch (columnIndex) {
				case 0:
					return Integer.class;
				case 3:
					return Date.class;
				default:
					return super.getColumnClass(columnIndex);
				}
			}
		});

		return table;
	}

	//Set the Table to show the System Nurses
	public JTable writeNurse() {
		String[] title = { "ID", "First name", "Last name", "Date of birth", "Address", "Phone number", "Email",
				"Gender", "Started Working", "Salary", "License Number", "Intensive Care" };
		Object[][] nurseMatrix = new Object[nursesHashMap.size()][12];
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		int i = 0;
		for (Map.Entry<Integer, Nurse> entry : nursesHashMap.entrySet()) {
			nurseMatrix[i][0] = entry.getKey();
			nurseMatrix[i][1] = entry.getValue().getFirstName();
			nurseMatrix[i][2] = entry.getValue().getLastName();
			nurseMatrix[i][3] = entry.getValue().getBirthDate();
			nurseMatrix[i][4] = entry.getValue().getAddress();
			nurseMatrix[i][5] = entry.getValue().getPhoneNumber();
			nurseMatrix[i][6] = entry.getValue().getEmail();
			nurseMatrix[i][7] = entry.getValue().getGender();
			nurseMatrix[i][8] = entry.getValue().getWorkStartDate();
			nurseMatrix[i][9] = entry.getValue().getSalary();
			nurseMatrix[i][10] = entry.getValue().getLicenseNumber();
			nurseMatrix[i][11] = (entry.getValue() instanceof IntensiveCareNurse) ? "Yes" : "No";
			i++;
		}
		table = new JTable(nurseMatrix, title);
		table.setAutoCreateRowSorter(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(nurseMatrix, title) {
			@Override
			public boolean isCellEditable(int row, int column) {
				//Cells cannot be edited by clicking on them
				return false;
			}

			public Class<?> getColumnClass(int columnIndex) {
				//Set the Types of the table cells
				switch (columnIndex) {
				case 0:
				case 10:
					return Integer.class;
				case 3:
				case 8:
					return Date.class;
				case 9:
					return Double.class;
				default:
					return super.getColumnClass(columnIndex);
				}
			}
		});

		return table;
	}

	//Set the Table to show the System Doctors
	public JTable writeDoctor() {
		String[] title = { "ID", "First name", "Last name", "Date of birth", "Address", "Phone number", "Email",
				"Gender", "Started Working", "Salary", "License Number", "Finished internship", "Intensive Care",
				"Specialization" };
		Object[][] docMatrix = new Object[doctorsHashMap.size()][14];
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		int i = 0;
		for (Map.Entry<Integer, Doctor> entry : doctorsHashMap.entrySet()) {
			docMatrix[i][0] = entry.getKey();
			docMatrix[i][1] = entry.getValue().getFirstName();
			docMatrix[i][2] = entry.getValue().getLastName();
			docMatrix[i][3] = entry.getValue().getBirthDate();
			docMatrix[i][4] = entry.getValue().getAddress();
			docMatrix[i][5] = entry.getValue().getPhoneNumber();
			docMatrix[i][6] = entry.getValue().getEmail();
			docMatrix[i][7] = entry.getValue().getGender();
			docMatrix[i][8] = entry.getValue().getWorkStartDate();
			docMatrix[i][9] = entry.getValue().getSalary();
			docMatrix[i][10] = entry.getValue().getLicenseNumber();
			docMatrix[i][11] = (entry.getValue().isFinishInternship()) ? "Yes" : "No";
			docMatrix[i][12] = (entry.getValue() instanceof IntensiveCareDoctor) ? "Yes" : "No";
			docMatrix[i][13] = entry.getValue().getSpecialization().name();
			i++;
		}
		table = new JTable(docMatrix, title);
		table.setAutoCreateRowSorter(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(docMatrix, title) {
			@Override
			public boolean isCellEditable(int row, int column) {
				//Cells cannot be edited by clicking on them
				return false;
			}

			public Class<?> getColumnClass(int columnIndex) {
				//Set the Types of the table cells
				switch (columnIndex) {
				case 0:
				case 10:
					return Integer.class;
				case 3:
				case 8:
					return Date.class;
				case 9:
					return Double.class;
				default:
					return super.getColumnClass(columnIndex);
				}
			}
		});

		return table;
	}

	//Update the table to show the selected HashMap
	public void updateTable(JTable newTable, JScrollPane pane) {
		table = newTable;
		DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
		leftRenderer.setHorizontalAlignment(JLabel.LEFT);
		table.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
		pane.setViewportView(table);
		table.getRowSorter().toggleSortOrder(0);
		pane.revalidate();
		pane.repaint();
		table.getTableHeader().setReorderingAllowed(false);
	}

	protected void showFrame(String frameName) {
	    // Check if the frame history is not empty and the top of the stack is not the same as the frameName
	    if (!frameHistory.isEmpty() && !frameHistory.peek().equals(frameName)) {
	        frameHistory.push(frameName); // Add the new frame to the top of the stack
	    } else if (frameHistory.isEmpty()) {
	        frameHistory.push(frameName); // If the stack is empty, add the first frame to the stack
	    }

	    // Display the specified frame using CardLayout
	    cardLayout.show(CardView, frameName);
	}


	protected void goBack() {
	    // Check if there are more than one frames in the history stack
	    if (frameHistory.size() > 1) {
	        frameHistory.pop(); // Remove the current frame from the history
	        String previousFrame = frameHistory.peek(); // Get the name of the previous frame
	        cardLayout.show(CardView, previousFrame); // Show the previous frame using CardLayout

	        // Restore the lastTableOpened to the appropriate frame based on the previousFrame
	        switch (previousFrame) {
	            case "departmentsFrame":
	                lastTableOpened = departmentsFrame;
	                updateTable(writeDepartment(), departmentSP); // Update the table with department data
	                break;
	            case "medicalProblemsFrame":
	                lastTableOpened = medicalProblemsFrame;

	                // Check the selected item in chooseTable and update the table accordingly
	                if (chooseTable.getSelectedItem() != null
	                        && chooseTable.getSelectedItem().toString().equals("Injuries")) {
	                    updateTable(writeInjury(), injuriesSP); // Update with injury data
	                } else {
	                    chooseTable.setSelectedItem("Injuries"); // Default to "Injuries" if none selected
	                    updateTable(writeInjury(), injuriesSP); // Update with injury data
	                }

	                // Revalidate and repaint the frame to ensure proper display
	                medicalProblemsFrame.revalidate();
	                medicalProblemsFrame.repaint();
	                break;
	            case "treatmentsFrame":
	                lastTableOpened = treatmentsFrame;
	                updateTable(writeTreatment(), treatmentSP); // Update the table with treatment data
	                break;
	            case "medicationsFrame":
	                lastTableOpened = medicationsFrame;
	                updateTable(writeMedication(), medicationSP); // Update the table with medication data
	                break;
	            case "visitsFrame":
	                lastTableOpened = visitsFrame;
	                updateTable(writeVisit(), visitSP); // Update the table with visit data
	                break;
	            case "patientsFrame":
	                lastTableOpened = patientsFrame;
	                updateTable(writePatient(), patientSP); // Update the table with patient data
	                break;
	            case "doctorsFrame":
	                lastTableOpened = doctorsFrame;
	                updateTable(writeDoctor(), doctorSP); // Update the table with doctor data
	                break;
	            case "nursesFrame":
	                lastTableOpened = nursesFrame;
	                updateTable(writeNurse(), nurseSP); // Update the table with nurse data
	                break;
	            case "departmentsAddFrame":
	                lastTableOpened = departmentsAddFrame;
	                updateTable(writeDepartment(), departmentSP); // Update the table with department data
	                break;
	            case "medicalProblemsAddFrame":
	                lastTableOpened = medicalProblemsAddFrame;
	                updateTable(writeInjury(), injuriesSP); // Update the table with injury data
	                break;
	            case "patientsAddFrame":
	                lastTableOpened = patientsAddFrame;
	                updateTable(writePatient(), patientSP); // Update the table with patient data
	                break;
	            case "visitsAddFrame":
	                lastTableOpened = visitsAddFrame;
	                updateTable(writeVisit(), visitSP); // Update the table with visit data
	                break;
	            case "treatmentsAddFrame":
	                lastTableOpened = treatmentsAddFrame;
	                updateTable(writeTreatment(), treatmentSP); // Update the table with treatment data
	                break;
	            case "doctorsAddFrame":
	                lastTableOpened = doctorsAddFrame;
	                updateTable(writeDoctor(), doctorSP); // Update the table with doctor data
	                break;
	            case "nursesAddFrame":
	                lastTableOpened = nursesAddFrame;
	                updateTable(writeNurse(), nurseSP); // Update the table with nurse data
	                break;
	            case "medicationsAddFrame":
	                lastTableOpened = medicationsAddFrame;
	                updateTable(writeMedication(), medicationSP); // Update the table with medication data
	                break;
	            case "addTreatmentToFrame":
	                lastTableOpened = addTreatmentToFrame;
	                updateTable(writeTreatment(), addTreatmentSP); // Update the table with treatment data
	                break;
	            case "addMedicationToTreatmentFrame":
	                lastTableOpened = addMedicationToTreatmentFrame;
	                updateTable(writeTreatment(), treatmentSP); // Update the table with treatment data
	                break;
	            case "addVisitToPatientFrame":
	                lastTableOpened = addVisitToPatientFrame;
	                updateTable(writePatient(), patientSP); // Update the table with patient data
	                break;
	            case "addMedProblemToVisitFrame":
	                lastTableOpened = addMedProblemToVisitFrame;
	                updateTable(writeVisit(), visitSP); // Update the table with visit data
	                break;
	            default:
	                lastTableOpened = null; // If no matching frame is found, set lastTableOpened to null
	                break;
	        }
	    }
	}


	//Write a new Hospital to a file
	public static void serialize(Hospital hospital1) {
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

	//Read from a file to a Hospital
	public static Hospital deserialize() {
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

	//Generate a TableModel to show HashMap values
	private DefaultTableModel createTableModel(String[] columnNames, Class<?>[] columnClasses) {
		return new DefaultTableModel(new Object[][] {}, columnNames) {
			Class[] columnTypes = columnClasses;

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Make cells uneditable
			}
		};
	}

	//Generate a Table to show HashMap values (Integer Key)
	private <V> void populateTable(DefaultTableModel model, Map<Integer, V> dataMap, Object[][] columnData) {
		model.setRowCount(0); // Clear existing rows

		for (Map.Entry<Integer, V> entry : dataMap.entrySet()) {
			Integer key = entry.getKey();
			V data = entry.getValue();

			Object[] rowData = new Object[columnData[0].length];

			rowData[0] = key;

			if (data instanceof Object[]) {
				System.arraycopy((Object[]) data, 0, rowData, 1, ((Object[]) data).length);
			} else {
				rowData[1] = data;
			}

			model.addRow(rowData);
		}
	}

	//Generate a Table to show HashMap values (String Key)
	private <V> void populateTableString(DefaultTableModel model, Map<String, V> dataMap, Object[][] columnData) {
		model.setRowCount(0); // Clear existing rows

		for (Map.Entry<String, V> entry : dataMap.entrySet()) {
			String key = entry.getKey();
			V data = entry.getValue();

			Object[] rowData = new Object[columnData[0].length];

			rowData[0] = key;

			if (data instanceof Object[]) {
				System.arraycopy((Object[]) data, 0, rowData, 1, ((Object[]) data).length);
			} else {
				rowData[1] = data;
			}

			model.addRow(rowData);
		}
	}

	// Method to style the sidebar 
	private void setupCommonSidebar(JPanel Sidebar) {

		Font titleFont = new Font("Segoe UI", Font.BOLD, 20);

		JButton btnLogout = styleButtonSidebar(new JButton("Logout"), new Color(255, 255, 255), Color.WHITE);
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Logout",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (response == JOptionPane.YES_OPTION) {
					dispose();
					JFrame loginWindow;
					loginWindow = new LoginWindow();
					loginWindow.setVisible(true);
				}

			}
		});
		btnLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogout.setFont(buttonFont);
		btnLogout.setBounds(1, 630, 103, 23);
		Sidebar.add(btnLogout);

		JButton btnExit = styleButtonSidebar(new JButton("Exit"), new Color(255, 255, 255), Color.WHITE);
		btnExit.setFont(buttonFont);
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

				if (response == JOptionPane.YES_OPTION) {
					dispose();
				}

			}
		});
		btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExit.setBounds(103, 630, 110, 23);
		Sidebar.add(btnExit);

		addHoverEffectSidebar(btnExit);
		addHoverEffectSidebar(btnLogout);

		helloLabel = new JLabel("Hello, ");
		helloLabel.setFont(titleFont);
		helloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		helloLabel.setBounds(43, 138, 139, 29);
		Sidebar.add(helloLabel);


		JLabel Logo = new JLabel("");
		Logo.setHorizontalAlignment(SwingConstants.CENTER);
		Logo.setIcon(new ImageIcon(BasicWindow.class.getResource("/resources/hanamalLogo-removebg-preview (2).png")));
		Logo.setBounds(18, 0, 184, 146);
		Sidebar.add(Logo);

	}

	// Method to display the Hello message in the sidebar
	public void setHelloMessage(String role) {
		if (userID==0)
		{
			helloLabel.setText("Hello, " + role);
		}
		else
		{
			helloLabel.setText("Hello, " + role + " " + userID);
			helloLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		}
		
	}

	// Styling methods
	
	private void styleTextField(JTextField textField) {
	    textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	    textField.setForeground(Color.DARK_GRAY);
	    textField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
	}
	
	private void styleComboBox(JComboBox<?> comboBox) {
	    comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	    comboBox.setForeground(Color.DARK_GRAY);
	    comboBox.setBackground(Color.WHITE);
	    comboBox.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
	}

	private void styleCheckBox(JCheckBox checkBox) {
	    checkBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	    checkBox.setForeground(Color.DARK_GRAY);
	    checkBox.setBackground(Color.WHITE);
	}
	
	private void styleRadioButton(JRadioButton radioButton) {
	    radioButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	    radioButton.setForeground(Color.DARK_GRAY);
	    radioButton.setBackground(Color.WHITE);
	}
	
	private void styleTextArea(JTextArea textArea) {
	    textArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	    textArea.setForeground(Color.DARK_GRAY);
	    textArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
	    textArea.setLineWrap(true);
	    textArea.setWrapStyleWord(true);
	    textArea.setMargin(new Insets(5, 10, 5, 10));
	}
	
	private void styleList(JList<?> list) {
	    list.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	    list.setForeground(Color.DARK_GRAY);
	    list.setBackground(Color.WHITE);
	    list.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
	    list.setSelectionBackground(new Color(255, 180, 160, 255));
	}
	
	 private JButton styleButtonSidebar(JButton button, Color bgColor, Color fgColor) {
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

	    private void addHoverEffectSidebar(JButton button) {
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
	    
	    
	    private JButton styleButton(JButton button, Color bgColor, Color fgColor) {
	        button.setFont(buttonFont);
	        button.setForeground(Color.BLACK);
	        Color baseColor = new Color(255, 180, 160, 255);
	        button.setBackground(baseColor);

	        Border line = BorderFactory.createLineBorder(Color.WHITE, 1);
	        Border shadow = BorderFactory.createMatteBorder(0, 4, 4, 0, new Color(200, 200, 200, 100));
	        Border finalBorder = BorderFactory.createCompoundBorder(shadow, line);

	        
	        button.setBorder(finalBorder);
	        button.setFocusPainted(false);
	        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        return button;
	    }


	    private void addHoverEffect(JButton button) {
	        Color originalBgColor = button.getBackground();
	        Color hoverColor = new Color(255, 200, 180, 255);

	        button.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {
	                button.setBackground(hoverColor);
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	                button.setBackground(originalBgColor);
	                button.setForeground(Color.BLACK);
	            }
	        });
	    }
	    
	    // Method to change the program UI depending on the user that enters
		protected abstract void setupSpecificUI();
}
