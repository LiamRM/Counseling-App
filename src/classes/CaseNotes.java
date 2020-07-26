package classes;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JInternalFrame;
import java.awt.GridLayout;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.awt.Cursor;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CaseNotes extends JFrame {
	// Essential Components
	private JPanel contentPane;
	public JFrame frmMain;
	public Container mainCont;
	public CardLayout cl;
	private MainMenu mm;

	// Panels
	// --MainMenu
	public JPanel cards;
	public JPanel mainCard;
	public JPanel initialMeetingCard;

	// IO
	private File cnFile;
	private File priorityFile;
	private FileReader stream;
	private BufferedReader read;
	private FileOutputStream out;
	private PrintWriter w;

	// Buttons
	private JButton btnSave;
	private JButton btnAddMeeting;
	private JButton btnAddFollowup;
	private JButton btnPrint;
	private JButton btnAddInitialMeeting;
	private JButton btnExit;
	private JButton btnDiscardMeeting;
	private JToggleButton tglbtnLow_1;
	private JToggleButton tglbtnMedium_1;
	private JToggleButton tglbtnHigh_1;
	private JToggleButton tglbtnPriority_1;

	// --Button Group
	private final ButtonGroup buttonGroup = new ButtonGroup();

	// Text Areas
	private JScrollPane scrollPaneNotes;
	private JTextArea textAreaNotes;
	// Labels
	private JLabel lblCaseNotes;
	private JLabel lblStudentName;
	private JLabel lblContactInfo;
	private JLabel lblPleaseIndicateThe;
	private JLabel lblInstructions;
	private JLabel lblPrintIcon;

	// Variables
	private String firstName;
	private String lastName;
	private String studentName;
	private String gradeLevel;
	private String priority = "0";
	private String caseNotesDirectory = "/Users/" + System.getProperty("user.name") + "/Desktop/AppData/caseNotes/";
	private int previousIndex;
	private int newIndex;

	// Printing / Exporting Variables
	private String filePath;
	private File printFile;
	private String fileName;
	// Maximum number of 'priority' students

	// Getting the Current Date
	Date date;
	DateFormat dateFormat;
	String dateToday;
	private JTextField textFieldContactInfo;

	// Textfields
	private JTextArea textAreaIncident;
	private JTextField textFieldMeetingTime;
	private JTextField textFieldInterest;
	private JTextField textFieldIncidentTime;
	private JTextField textFieldReferral;
	private JTextField textFieldMotivation;
	private JTextField textFieldHelpfulness;
	private JTextField textFieldInitiative;
	private JTextField textFieldOrganization;
	private JTextField textFieldFocus;
	private JScrollPane scrollPaneIncident;

	// CheckBoxes
	private JCheckBox checkBoxInterest;
	private JCheckBox checkBoxMotivation;
	private JCheckBox checkBoxHelpfulness;
	private JCheckBox checkBoxInitiative;
	private JCheckBox checkBoxOrganization;
	private JCheckBox checkBoxFocus;

	// Labels
	private JLabel lblTitle;
	private JLabel lblStudent;
	private JLabel lblDate;
	private JLabel lblAgenda;
	private JLabel lblComments;
	private JLabel lblTimeOfMeeting;
	private JLabel lblTimeOfIncident;
	private JLabel lblReferral;
	private JLabel initialMeetingBG;
	// --Comments
	private JLabel lblOtherComments;
	private JLabel lblOfConcern;
	private JLabel lblInterest;
	private JLabel lblMotivation;
	private JLabel lblHelpfulness;
	private JLabel lblInitiative;
	private JLabel lblOrganization;
	private JLabel lblFocus;

	// Buttons

	private LinkedList<String> priorityLL;
	private LinkedList<String> highLL;
	private LinkedList<String> mediumLL;
	private LinkedList<String> lowLL;
	private JLabel caseNotesBG;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	// Create the application
	public CaseNotes(String firstName, String lastName, String gradeLevel) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gradeLevel = gradeLevel;
		studentName = lastName + firstName;
		initialize();
	}

	// Define the frame
	public void initialize() {
		// Main Frame
		frmMain = new JFrame();
		frmMain.setTitle("Case Notes");
		frmMain.setResizable(false);
		frmMain.setSize(834, 704);
		frmMain.setLocationRelativeTo(null);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Main Content Pane
		mainCont = frmMain.getContentPane();
		mainCont.setLayout(null);
		mainCont.setBackground(Color.WHITE);

		// Main panel to hold other panels
		cards = new JPanel(new CardLayout());
		cards.setBounds(0, 0, 834, 704);
		mainCont.add(cards);

		// Define mainCard
		mainCard = new JPanel();
		mainCard.setLayout(null);
		mainCard.setBounds(0, 0, 834, 704);
		cards.add(mainCard, "mainCard");

		// Define initialMeetingCard
		initialMeetingCard = new JPanel();
		initialMeetingCard.setLayout(null);
		initialMeetingCard.setBounds(0, 0, 834, 704);
		cards.add(initialMeetingCard, "initialMeetingCard");

		// Initialize the components
		priorityLL = new LinkedList<String>();
		highLL = new LinkedList<String>();
		mediumLL = new LinkedList<String>();
		lowLL = new LinkedList<String>();
		initCaseNotes();
		initInitialMeeting();
		readCase();
		readPriority();

		// Show mainCard on application startup
		cl = (CardLayout) (cards.getLayout());
		cl.show(cards, "mainCard");
	}

	// Define components of CaseNotes
	private void initCaseNotes() {
		// Labels
		lblCaseNotes = new JLabel("Case Notes");
		lblCaseNotes.setBounds(20, 9, 194, 56);
		lblCaseNotes.setFont(new Font("Segoe UI Semibold", Font.BOLD, 30));
		mainCard.add(lblCaseNotes);

		lblStudentName = new JLabel(firstName + " " + lastName);
		lblStudentName.setBounds(212, 11, 318, 56);
		lblStudentName.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		mainCard.add(lblStudentName);

		lblPleaseIndicateThe = new JLabel("Please indicate the level of priority of the student.");
		lblPleaseIndicateThe.setFont(new Font("Dialog", Font.ITALIC, 14));
		lblPleaseIndicateThe.setBounds(20, 103, 390, 37);
		mainCard.add(lblPleaseIndicateThe);

		lblContactInfo = new JLabel("Contact Information:");
		lblContactInfo.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 20));
		lblContactInfo.setBounds(20, 61, 229, 56);
		mainCard.add(lblContactInfo);

		// Buttons

		btnAddMeeting = new JButton("Add Meeting");
		btnAddMeeting.setOpaque(true);
		btnAddMeeting.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnAddMeeting.setForeground(Color.WHITE);
		btnAddMeeting.setBackground(new Color(0, 102, 255));
		btnAddMeeting.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAddMeeting.setBounds(247, 479, 217, 76);
		btnAddMeeting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				date = new Date();
				dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				dateToday = dateFormat.format(date);
				textAreaNotes
						.setText(textAreaNotes.getText() + "\n----------------------------------" + "\n Meeting -- "
								+ dateToday + "\n --Time of Meeting: " + "\n --Meeting with: " + "\n Notes: ");
			}
		});

		btnSave = new JButton("Save & Close Notes");
		btnSave.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnSave.setOpaque(true);
		btnSave.setBackground(new Color(255, 51, 51));
		btnSave.setForeground(Color.WHITE);
		btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSave.setBounds(516, 566, 292, 77);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				savePriority();
				saveCase();
				frmMain.dispose();

			}
		});

		lblPrintIcon = new JLabel("");
		lblPrintIcon.setOpaque(true);
		lblPrintIcon.setBackground(new Color(0, 153, 255));
		lblPrintIcon.setIcon(new ImageIcon(CaseNotes.class.getResource("/images/Print.png")));
		lblPrintIcon.setBounds(542, 26, 48, 77);
		mainCard.add(lblPrintIcon);
		btnSave.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		mainCard.add(btnSave);
		btnAddMeeting.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		mainCard.add(btnAddMeeting);

		btnAddFollowup = new JButton("Add Followup");
		btnAddFollowup.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnAddFollowup.setOpaque(true);
		btnAddFollowup.setForeground(Color.WHITE);
		btnAddFollowup.setBackground(new Color(0, 102, 255));
		btnAddFollowup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				date = new Date();
				dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				dateToday = dateFormat.format(date);
				textAreaNotes.setText(textAreaNotes.getText() + "\n----------------------------------"
						+ "\n Follow-up -- " + dateToday + "\n --Following up with: " + "\n --Notes: ");
			}
		});
		btnAddFollowup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAddFollowup.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		btnAddFollowup.setBounds(474, 479, 217, 76);
		mainCard.add(btnAddFollowup);

		btnPrint = new JButton("Export All Notes");
		btnPrint.setOpaque(true);
		btnPrint.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnPrint.setForeground(Color.WHITE);
		btnPrint.setBackground(new Color(0, 153, 255));
		btnPrint.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				print();
			}
		});
		btnPrint.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		btnPrint.setBounds(589, 26, 194, 77);
		mainCard.add(btnPrint);

		tglbtnLow_1 = new JToggleButton("Low");
		tglbtnLow_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tglbtnLow_1.setBounds(20, 140, 101, 23);
		buttonGroup.add(tglbtnLow_1);
		mainCard.add(tglbtnLow_1);

		tglbtnMedium_1 = new JToggleButton("Medium");
		tglbtnMedium_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tglbtnMedium_1.setBounds(131, 140, 101, 23);
		buttonGroup.add(tglbtnMedium_1);
		mainCard.add(tglbtnMedium_1);

		tglbtnHigh_1 = new JToggleButton("High");
		tglbtnHigh_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tglbtnHigh_1.setBounds(242, 140, 99, 23);
		buttonGroup.add(tglbtnHigh_1);
		mainCard.add(tglbtnHigh_1);

		tglbtnPriority_1 = new JToggleButton("Priority");
		tglbtnPriority_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tglbtnPriority_1.setBounds(353, 140, 136, 23);
		buttonGroup.add(tglbtnPriority_1);
		mainCard.add(tglbtnPriority_1);

		btnAddInitialMeeting = new JButton("Add Inital Meeting");
		btnAddInitialMeeting.setOpaque(true);
		btnAddInitialMeeting.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnAddInitialMeeting.setForeground(Color.WHITE);
		btnAddInitialMeeting.setBackground(new Color(0, 102, 255));
		btnAddInitialMeeting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				date = new Date();
				dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				dateToday = dateFormat.format(date);
				lblDate.setText(dateToday);
				cl.show(cards, "initialMeetingCard");
			}
		});
		btnAddInitialMeeting.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAddInitialMeeting.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		btnAddInitialMeeting.setBounds(20, 479, 217, 76);
		mainCard.add(btnAddInitialMeeting);

		// Checking Toggle Buttons for Priority, then saving priority
		// ButtonGroup checking class
		class VoteItemListener implements ItemListener {
			public void itemStateChanged(ItemEvent ex) {
				String item = ((AbstractButton) ex.getItemSelectable()).getActionCommand();
				boolean selected = (ex.getStateChange() == ItemEvent.SELECTED);
				System.out.println("ITEM Priority Selected: " + selected + " Selection: " + item);
				// Prevents printing the previously selected item
				if (selected == true) {
					// Setting the priority based on toggle buttons
					if (tglbtnLow_1.isSelected()) {
						priority = "0";
						priorityLL.remove(studentName);
						highLL.remove(studentName);
						mediumLL.remove(studentName);
						lowLL.add(studentName);

						// Removing from other Linked Lists
						if (priorityLL.contains(studentName)) {
							previousIndex = priorityLL.indexOf(studentName);
							priorityLL.remove(previousIndex);
						}
						if (highLL.contains(studentName)) {
							previousIndex = highLL.indexOf(studentName);
							highLL.remove(previousIndex);
						}
						if (mediumLL.contains(studentName)) {
							previousIndex = mediumLL.indexOf(studentName);
							mediumLL.remove(previousIndex);
						}
					}
					if (tglbtnMedium_1.isSelected()) {
						priority = "1";
						priorityLL.remove(studentName);
						highLL.remove(studentName);
						lowLL.remove(studentName);
						mediumLL.add(studentName);

						// Removing from other Linked Lists
						if (priorityLL.contains(studentName)) {
							previousIndex = priorityLL.indexOf(studentName);
							priorityLL.remove(previousIndex);
						}
						if (highLL.contains(studentName)) {
							previousIndex = highLL.indexOf(studentName);
							highLL.remove(previousIndex);
						}
						if (lowLL.contains(studentName)) {
							previousIndex = lowLL.indexOf(studentName);
							lowLL.remove(previousIndex);
						}
					}
					if (tglbtnHigh_1.isSelected()) {
						priority = "2";
						mediumLL.remove(studentName);
						lowLL.remove(studentName);
						priorityLL.remove(studentName);
						highLL.add(studentName);

						// Removing from other Linked Lists
						if (priorityLL.contains(studentName)) {
							previousIndex = priorityLL.indexOf(studentName);
							priorityLL.remove(previousIndex);
						}
						if (lowLL.contains(studentName)) {
							previousIndex = lowLL.indexOf(studentName);
							lowLL.remove(previousIndex);
						}
						if (mediumLL.contains(studentName)) {
							previousIndex = mediumLL.indexOf(studentName);
							mediumLL.remove(previousIndex);
						}
					}
					if (tglbtnPriority_1.isSelected()) {
						priority = "3";
						highLL.remove(studentName);
						mediumLL.remove(studentName);
						lowLL.remove(studentName);
						priorityLL.add(studentName);

						// Brute force removing duplicates from other Linked Lists
						if (lowLL.contains(studentName)) {
							previousIndex = lowLL.indexOf(studentName);
							lowLL.remove(previousIndex);
						}
						if (highLL.contains(studentName)) {
							previousIndex = highLL.indexOf(studentName);
							highLL.remove(previousIndex);
						}
						if (mediumLL.contains(studentName)) {
							previousIndex = mediumLL.indexOf(studentName);
							mediumLL.remove(previousIndex);
						}
					}
				}

			}
		}
		ItemListener il = new VoteItemListener();
		tglbtnLow_1.addItemListener(il);
		tglbtnMedium_1.addItemListener(il);
		tglbtnHigh_1.addItemListener(il);
		tglbtnPriority_1.addItemListener(il);

		// Text fields + Panels

		textFieldContactInfo = new JTextField();
		textFieldContactInfo.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 14));
		textFieldContactInfo.setBounds(222, 70, 308, 34);
		mainCard.add(textFieldContactInfo);
		textFieldContactInfo.setColumns(10);

		scrollPaneNotes = new JScrollPane();
		scrollPaneNotes.setBounds(21, 185, 703, 283);
		mainCard.add(scrollPaneNotes);

		textAreaNotes = new JTextArea();
		textAreaNotes.setLineWrap(true);
		textAreaNotes.setWrapStyleWord(true);
		scrollPaneNotes.setViewportView(textAreaNotes);

		caseNotesBG = new JLabel("");
		caseNotesBG.setBackground(new Color(153, 204, 255));
		caseNotesBG.setOpaque(true);
		caseNotesBG.setBounds(0, 0, 834, 704);
		mainCard.add(caseNotesBG);

	}

	private void initInitialMeeting() {
		// TextFields

		textFieldMeetingTime = new JTextField();
		textFieldMeetingTime.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 14));
		textFieldMeetingTime.setColumns(10);
		textFieldMeetingTime.setBounds(190, 119, 127, 28);
		initialMeetingCard.add(textFieldMeetingTime);

		textFieldIncidentTime = new JTextField();
		textFieldIncidentTime.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 14));
		textFieldIncidentTime.setColumns(10);
		textFieldIncidentTime.setBounds(190, 155, 127, 28);
		initialMeetingCard.add(textFieldIncidentTime);

		textFieldInterest = new JTextField();
		textFieldInterest.setEditable(false);
		textFieldInterest.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textFieldInterest.setBounds(354, 360, 431, 28);
		initialMeetingCard.add(textFieldInterest);
		textFieldInterest.setColumns(10);

		textFieldReferral = new JTextField();
		textFieldReferral.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 14));
		textFieldReferral.setColumns(10);
		textFieldReferral.setBounds(190, 80, 308, 28);
		initialMeetingCard.add(textFieldReferral);

		textFieldMotivation = new JTextField();
		textFieldMotivation.setEditable(false);
		textFieldMotivation.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textFieldMotivation.setColumns(10);
		textFieldMotivation.setBounds(354, 399, 431, 28);
		initialMeetingCard.add(textFieldMotivation);

		textFieldHelpfulness = new JTextField();
		textFieldHelpfulness.setEditable(false);
		textFieldHelpfulness.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textFieldHelpfulness.setColumns(10);
		textFieldHelpfulness.setBounds(354, 438, 431, 28);
		initialMeetingCard.add(textFieldHelpfulness);

		textFieldInitiative = new JTextField();
		textFieldInitiative.setEditable(false);
		textFieldInitiative.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textFieldInitiative.setColumns(10);
		textFieldInitiative.setBounds(354, 477, 431, 28);
		initialMeetingCard.add(textFieldInitiative);

		textFieldOrganization = new JTextField();
		textFieldOrganization.setEditable(false);
		textFieldOrganization.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textFieldOrganization.setColumns(10);
		textFieldOrganization.setBounds(354, 516, 431, 28);
		initialMeetingCard.add(textFieldOrganization);

		textFieldFocus = new JTextField();
		textFieldFocus.setEditable(false);
		textFieldFocus.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		textFieldFocus.setColumns(10);
		textFieldFocus.setBounds(354, 555, 431, 28);
		initialMeetingCard.add(textFieldFocus);

		// CheckBoxes

		checkBoxInterest = new JCheckBox("");
		checkBoxInterest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkSelectedBox();
			}
		});
		checkBoxInterest.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		checkBoxInterest.setBounds(259, 360, 28, 28);
		initialMeetingCard.add(checkBoxInterest);

		checkBoxMotivation = new JCheckBox("");
		checkBoxMotivation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkSelectedBox();
			}
		});
		checkBoxMotivation.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		checkBoxMotivation.setBounds(259, 399, 28, 28);
		initialMeetingCard.add(checkBoxMotivation);

		checkBoxHelpfulness = new JCheckBox("");
		checkBoxHelpfulness.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkSelectedBox();
			}
		});
		checkBoxHelpfulness.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		checkBoxHelpfulness.setBounds(259, 438, 28, 28);
		initialMeetingCard.add(checkBoxHelpfulness);

		checkBoxInitiative = new JCheckBox("");
		checkBoxInitiative.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkSelectedBox();
			}
		});
		checkBoxInitiative.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		checkBoxInitiative.setBounds(259, 477, 28, 28);
		initialMeetingCard.add(checkBoxInitiative);

		checkBoxOrganization = new JCheckBox("");
		checkBoxOrganization.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkSelectedBox();
			}
		});
		checkBoxOrganization.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		checkBoxOrganization.setBounds(259, 516, 28, 28);
		initialMeetingCard.add(checkBoxOrganization);

		checkBoxFocus = new JCheckBox("");
		checkBoxFocus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkSelectedBox();
			}
		});
		checkBoxFocus.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 12));
		checkBoxFocus.setBounds(259, 555, 28, 28);
		initialMeetingCard.add(checkBoxFocus);

		scrollPaneIncident = new JScrollPane();
		scrollPaneIncident.setBounds(43, 208, 742, 86);
		initialMeetingCard.add(scrollPaneIncident);

		textAreaIncident = new JTextArea();
		textAreaIncident.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 14));
		scrollPaneIncident.setViewportView(textAreaIncident);

		// Labels

		lblTitle = new JLabel("Initial Meeting Form");
		lblTitle.setFont(new Font("Segoe UI Semibold", Font.BOLD, 28));
		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitle.setBounds(43, 4, 308, 38);
		initialMeetingCard.add(lblTitle);

		lblInstructions = new JLabel("Please fill out each of the blank sections below.");
		lblInstructions.setHorizontalAlignment(SwingConstants.LEFT);
		lblInstructions.setFont(new Font("Segoe UI Semilight", Font.ITALIC, 16));
		lblInstructions.setBounds(43, 41, 455, 33);
		initialMeetingCard.add(lblInstructions);

		lblStudent = new JLabel("Student: " + studentName);
		lblStudent.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblStudent.setBounds(493, 4, 279, 38);
		initialMeetingCard.add(lblStudent);

		lblDate = new JLabel("Date: " + dateToday);
		lblDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblDate.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblDate.setBounds(493, 41, 173, 33);
		initialMeetingCard.add(lblDate);

		lblAgenda = new JLabel("Incident Notes");
		lblAgenda.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgenda.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		lblAgenda.setBounds(290, 179, 224, 28);
		initialMeetingCard.add(lblAgenda);

		lblComments = new JLabel("Comments:");
		lblComments.setFont(new Font("Lucida Console", Font.PLAIN, 16));
		lblComments.setBounds(354, 335, 132, 27);
		initialMeetingCard.add(lblComments);

		lblTimeOfMeeting = new JLabel("Time of meeting:");
		lblTimeOfMeeting.setHorizontalAlignment(SwingConstants.LEFT);
		lblTimeOfMeeting.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 16));
		lblTimeOfMeeting.setBounds(43, 116, 140, 33);
		initialMeetingCard.add(lblTimeOfMeeting);

		lblTimeOfIncident = new JLabel("Time of incident:");
		lblTimeOfIncident.setHorizontalAlignment(SwingConstants.LEFT);
		lblTimeOfIncident.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 16));
		lblTimeOfIncident.setBounds(43, 152, 140, 33);
		initialMeetingCard.add(lblTimeOfIncident);

		lblReferral = new JLabel("Referral From:");
		lblReferral.setHorizontalAlignment(SwingConstants.LEFT);
		lblReferral.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 16));
		lblReferral.setBounds(43, 77, 127, 33);
		initialMeetingCard.add(lblReferral);

		lblOtherComments = new JLabel("Other Comments and Concerns");
		lblOtherComments.setHorizontalAlignment(SwingConstants.CENTER);
		lblOtherComments.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		lblOtherComments.setBounds(248, 303, 313, 35);
		initialMeetingCard.add(lblOtherComments);

		lblInterest = new JLabel("Interest in Class:");
		lblInterest.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInterest.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 16));
		lblInterest.setBounds(78, 360, 141, 28);
		initialMeetingCard.add(lblInterest);

		lblMotivation = new JLabel("Motivation:");
		lblMotivation.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMotivation.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 16));
		lblMotivation.setBounds(79, 399, 140, 28);
		initialMeetingCard.add(lblMotivation);

		lblHelpfulness = new JLabel("Helpfulness:");
		lblHelpfulness.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHelpfulness.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 16));
		lblHelpfulness.setBounds(79, 436, 140, 30);
		initialMeetingCard.add(lblHelpfulness);

		lblInitiative = new JLabel("Initiative:");
		lblInitiative.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInitiative.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 16));
		lblInitiative.setBounds(78, 477, 140, 28);
		initialMeetingCard.add(lblInitiative);

		lblOrganization = new JLabel("Organization:");
		lblOrganization.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOrganization.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 16));
		lblOrganization.setBounds(79, 516, 140, 28);
		initialMeetingCard.add(lblOrganization);

		lblFocus = new JLabel("Other:");
		lblFocus.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFocus.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblFocus.setBounds(78, 555, 140, 28);
		initialMeetingCard.add(lblFocus);

		lblOfConcern = new JLabel("Of concern?");
		lblOfConcern.setFont(new Font("Lucida Console", Font.PLAIN, 14));
		lblOfConcern.setBounds(229, 336, 122, 27);
		initialMeetingCard.add(lblOfConcern);

		// Buttons

		btnExit = new JButton("Save and Close");
		btnExit.setOpaque(true);
		btnExit.setForeground(Color.WHITE);
		btnExit.setBackground(new Color(0, 102, 255));
		btnExit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(cards, "mainCard");
				saveInitial();
			}
		});
		btnExit.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnExit.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnExit.setBounds(526, 594, 271, 71);
		initialMeetingCard.add(btnExit);

		btnDiscardMeeting = new JButton("Discard Meeting");
		btnDiscardMeeting.setOpaque(true);
		btnDiscardMeeting.setForeground(Color.WHITE);
		btnDiscardMeeting.setBackground(new Color(204, 51, 51));
		btnDiscardMeeting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cl.show(cards, "mainCard");
			}
		});
		btnDiscardMeeting.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDiscardMeeting.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnDiscardMeeting.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnDiscardMeeting.setBounds(43, 594, 194, 71);
		initialMeetingCard.add(btnDiscardMeeting);

		initialMeetingBG = new JLabel("");
		initialMeetingBG.setBackground(new Color(153, 204, 255));
		initialMeetingBG.setOpaque(true);
		initialMeetingBG.setBounds(0, 0, 834, 682);
		initialMeetingCard.add(initialMeetingBG);
	}

	// Checks to see if checkBox is selected, and allows for typing of comment
	private void checkSelectedBox() {
		if (checkBoxInterest.isSelected()) {
			textFieldInterest.setEditable(true);
		} else {
			textFieldInterest.setEditable(false);
		}

		if (checkBoxMotivation.isSelected()) {
			textFieldMotivation.setEditable(true);
		} else {
			textFieldMotivation.setEditable(false);
		}

		if (checkBoxHelpfulness.isSelected()) {
			textFieldHelpfulness.setEditable(true);
		} else {
			textFieldHelpfulness.setEditable(false);
		}

		if (checkBoxInitiative.isSelected()) {
			textFieldInitiative.setEditable(true);
		} else {
			textFieldInitiative.setEditable(false);
		}

		if (checkBoxOrganization.isSelected()) {
			textFieldOrganization.setEditable(true);
		} else {
			textFieldOrganization.setEditable(false);
		}

		if (checkBoxFocus.isSelected()) {
			textFieldFocus.setEditable(true);
		} else {
			textFieldFocus.setEditable(false);
		}
	}

	// Reading from the Priority.txt file when starting CaseNotes
	private void readPriority() {
		try {
			// Loading File
			cnFile = new File("/Users/" + System.getProperty("user.name") + "/Desktop/AppData/" + "Priority.txt");
			stream = new FileReader(cnFile);
			read = new BufferedReader(stream);

			String line;
			line = read.readLine();

			// Reads from Priority.txt if file is not blank
			if (!line.equals("")) {
				// Reads 'Priority' students in while loops until '--HIGH' text is found
				while (!(line = read.readLine()).equals("--HIGH")) {
					priorityLL.add(line); // Adds students to temporary priority Linked List
				}
				// Reading 'High' students
				while (!(line = read.readLine()).equals("--MEDIUM")) {
					highLL.add(line);
				}
				// Reading 'Medium' students
				while (!(line = read.readLine()).equals("--LOW")) {
					mediumLL.add(line);
				}
				// Reading 'Low' students
				while ((line = read.readLine()) != null) {
					lowLL.add(line);
				}
			}

			// Finished
			read.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Error occured while trying to read the Priority file. Somehow it failed :(");
			System.out.println(">>ERROR OCCURED - readPriority<<");
		}
	}

	// Reading from student Notes file when starting the program
	private void readCase() {
		try {
			// Loading File
			cnFile = new File(caseNotesDirectory + studentName + "/" + studentName + "Notes.txt");
			stream = new FileReader(cnFile);
			read = new BufferedReader(stream);

			// Variables
			read.readLine();
			read.readLine();
			read.readLine();
			read.readLine();
			priority = read.readLine();
			if (priority == null) {
				priority = "0";
			}
			checkPriority();
			// --Contact Info
			textFieldContactInfo.setText(read.readLine());

			String line;
			read.readLine(); // Removes extra line from appearing
			while ((line = read.readLine()) != null) {
				// textAreaNotes Starts off as a blank line?
				textAreaNotes.setText(textAreaNotes.getText() + "\n" + line);
			}

			// Finished
			read.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Error occured while trying to save to priority file. Somehow it failed :(");
			System.out.println(">>ERROR OCCURED - Reading Notes File CaseNotes<<");
		}
	}

	// Writing to student CaseNotes File when exiting
	private void saveCase() {
		try {
			// Creating the File
			cnFile = new File(caseNotesDirectory + studentName + "/" + studentName + "Notes.txt");
			out = new FileOutputStream(cnFile);
			w = new PrintWriter(out);

			// Writing variables to file
			w.println(studentName);
			w.println(firstName);
			w.println(lastName);
			w.println(gradeLevel);
			w.println(priority);
			w.println(textFieldContactInfo.getText());
			w.println(textAreaNotes.getText());

			JOptionPane.showMessageDialog(null, "Your changes have been saved.");

			// Finished
			w.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Error occured while trying to write to file. Nothing was written in TextField.");
			System.out.println(">>ERROR OCCURED - Saving CaseNotes File<<");
		}
	}

	// This function will write the names of priority students to a file
	private void savePriority() {
		try {
			// Writing to file
			priorityFile = new File("/Users/" + System.getProperty("user.name") + "/Desktop/AppData/" + "Priority.txt");
			out = new FileOutputStream(priorityFile);
			w = new PrintWriter(out);

			// Sorting duplicate names in LinkedList e.g. LiamRichards, LiamRichards
			for (int y = 0; y < priorityLL.size(); y++) {
				for (int i = y + 1; i < priorityLL.size(); i++) {
					if (priorityLL.get(y).equals(priorityLL.get(i))) {
						priorityLL.remove(i);
					}
				}
			}
			for (int y = 0; y < highLL.size(); y++) {
				for (int i = y + 1; i < highLL.size(); i++) {
					if (highLL.get(y).equals(highLL.get(i))) {
						highLL.remove(i);
					}
				}
			}
			for (int y = 0; y < mediumLL.size(); y++) {
				for (int i = y + 1; i < mediumLL.size(); i++) {
					if (mediumLL.get(y).equals(mediumLL.get(i))) {
						mediumLL.remove(i);
					}
				}
			}
			for (int y = 0; y < lowLL.size(); y++) {
				for (int i = y + 1; i < lowLL.size(); i++) {
					if (lowLL.get(y).equals(lowLL.get(i))) {
						lowLL.remove(i);
					}
				}
			}

			// Printing the LinkedList to Priority.txt
			w.println("--PRIORITY");
			for (String x : priorityLL) {
				w.println(x);
			}
			// Printing the 'high priority' to Priority.txt
			w.println("--HIGH");
			for (String x : highLL) {
				w.println(x);
			}
			// Printing the 'medium priority' to Priority.txt
			w.println("--MEDIUM");
			for (String x : mediumLL) {
				w.println(x);
			}
			// Printing the 'low priority' to Priority.txt
			w.println("--LOW");
			for (String x : lowLL) {
				w.println(x);
			}

			// Finished
			w.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Error occured while trying to save to priority file. Somehow it failed :(");
			System.out.println(">>ERROR OCCURED - Writing Priority CaseNotes<<");
		}
	}

	private void saveInitial() {
		textAreaNotes
				.setText(textAreaNotes.getText() + "\n----------------------------------" + "\n Initial Meeting -- "
						+ dateToday + "\n --Referral from: " + textFieldReferral.getText() + "\n --Time of Meeting: "
						+ textFieldMeetingTime.getText() + "\n --Time of incident: " + textFieldIncidentTime.getText()
						+ "\n Notes: " + textAreaIncident.getText() + "\n\n --Other concerns: ");
		if (checkBoxInterest.isSelected() || checkBoxMotivation.isSelected() || checkBoxHelpfulness.isSelected()
				|| checkBoxInitiative.isSelected() || checkBoxOrganization.isSelected() || checkBoxFocus.isSelected()) {
			if (checkBoxInterest.isSelected()) {
				textAreaNotes.setText(textAreaNotes.getText() + "\n Interest in class: " + textFieldInterest.getText());
			}
			if (checkBoxMotivation.isSelected()) {
				textAreaNotes.setText(textAreaNotes.getText() + "\n Motivation: " + textFieldMotivation.getText());
			}
			if (checkBoxHelpfulness.isSelected()) {
				textAreaNotes.setText(textAreaNotes.getText() + "\n Helpfulness: " + textFieldHelpfulness.getText());
			}
			if (checkBoxInitiative.isSelected()) {
				textAreaNotes.setText(textAreaNotes.getText() + "\n Initiative: " + textFieldInitiative.getText());
			}
			if (checkBoxOrganization.isSelected()) {
				textAreaNotes.setText(textAreaNotes.getText() + "\n Organization: " + textFieldOrganization.getText());
			}
			if (checkBoxFocus.isSelected()) {
				textAreaNotes.setText(textAreaNotes.getText() + "\n Other: " + textFieldFocus.getText());
			}
		} else {
			textAreaNotes.setText(textAreaNotes.getText() + " None");
		}
	}

	private void checkPriority() throws IOException {
		// Setting the priority value from the txt File
		if (!priority.equals("0")) {
			if (priority.equals("3")) {
				tglbtnPriority_1.setSelected(true);
			}
			if (priority.equals("2")) {
				tglbtnHigh_1.setSelected(true);
			}
			if (priority.equals("1")) {
				tglbtnMedium_1.setSelected(true);
			}
		} else {
			tglbtnLow_1.setSelected(true);
		}
	}

	// The function for printing the file here
	private void print() {
		CaseNotesOpt cnopt = new CaseNotesOpt(studentName, textAreaNotes.getText(), btnSave, btnAddMeeting,
				btnAddFollowup, btnPrint, btnAddInitialMeeting, btnExit, caseNotesBG);
		cnopt.caseNotesOptFrame.setVisible(true);
		btnSave.setEnabled(false);
		btnAddMeeting.setEnabled(false);
		btnAddFollowup.setEnabled(false);
		btnPrint.setEnabled(false);
		btnAddInitialMeeting.setEnabled(false);
		btnExit.setEnabled(false);
	}
}
