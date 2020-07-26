package classes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import java.awt.Cursor;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.UIManager;

public class MainMenu extends JFrame {
	// Essential Components
	public JFrame frmMain;
	public Container mainCont;
	public CardLayout cl;
	private CaseNotes cn;

	// Panels
	// --MainMenu
	public JPanel cards;
	public JPanel mainCard;
	public JPanel helpSubCard;
	public JPanel studentSubCard;
	public JPanel menuBar;
	// --Students
	private JScrollPane scrollPane;
	private JScrollPane scrollPaneMedium;
	private JScrollPane scrollPanePriority;
	private JScrollPane scrollPaneHigh;
	// --Help
	private JScrollPane scrollPaneHelp;
	// --CaseNotes
	public JPanel caseNotesCard;
	// --Settings
	public JPanel settingsSubCard;
	private JLabel studentBg;
	private JLabel settingsBg;

	// JLists
	// --MainMenu
	private JList listPriority;
	private JList listHigh;
	private JList listMedium;
	private JList listLow;
	private JTabbedPane tabbedPanePriority;
	private static DefaultListModel modelPriority;
	private static DefaultListModel modelHigh;
	private static DefaultListModel modelMedium;
	private static DefaultListModel modelLow;

	// IO
	private File selected;
	private File account;
	private File priorityFile;
	private FileReader stream;
	private BufferedReader read;
	private FileOutputStream out;
	private PrintWriter w;
	private JFileChooser fc;

	// Buttons
	// --MenuBar
	private JButton btnHome;
	private JButton btnStudent;
	private JButton btnHelp;
	private JButton btnSettings;
	// --MainMenu
	private JButton btnOpenSelected;
	private JButton btnDeleteSelected;
	private JButton btnQuit;
	private JButton btnImportList;
	private JButton btnViewCaseNotes;
	private JButton btnAddAttachments;
	private JButton btnRemoveAttachment;
	// --Settings
	private JButton btnDeleteAccount;

	// Labels
	// --Generic Labels
	private JLabel lblTitle;
	// --MenuBar
	private JLabel lblSpace1;
	private JLabel lblSpace2;
	// --MainMenu
	private JLabel lblWelcomeBackUser;
	private JLabel lblPriorityList;
	// --Students
	private JTextField txtSearch;
	private JLabel lblStudentInstruction;
	private JLabel lblPleaseSelect;
	private JTable tblStudent;
	private JLabel lblStudentName;
	private JLabel lblGradeLevel;
	private JLabel lblSearch;
	private JLabel lblAttachmentList;
	private JLabel mainBG;
	private JLabel lblSelectAStudent;
	// --Help
	private JLabel lblHelpInfo;
	private JLabel helpBG;
	private JLabel lblHelpInstructions;
	// --Settings
	private JLabel lblDeleteIcon;

	// Student Table Properties
	private DefaultTableModel model;
	private Object[] row;

	// Attachment List Properties
	private static DefaultListModel modelAttachments = new DefaultListModel();
	private JList listAttachments;

	// Variables
	private String studentName;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String stDirectory;
	private String selectedFirst;
	private String selectedLast;
	private String selectedGrade;
	private String selectedPriorityList;
	private String caseNotesDirectory;

	// Launch the application
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

	// Create the application
	public MainMenu(String username) {
		this.username = username;
		initialize();
	}

	// Define the frame
	private void initialize() {
		// Main Frame
		frmMain = new JFrame();
		frmMain.setTitle("Counselling Application");
		frmMain.setResizable(false);
		frmMain.setSize(950, 540);
		frmMain.setLocationRelativeTo(null);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Main Content Pane
		mainCont = frmMain.getContentPane();
		mainCont.setLayout(null);
		mainCont.setBackground(Color.WHITE);

		// Main panel to hold other panels
		cards = new JPanel(new CardLayout());
		cards.setBounds(81, 0, 869, 521);
		mainCont.add(cards);

		// Define mainCard
		mainCard = new JPanel();
		mainCard.setLayout(null);
		mainCard.setBounds(0, 0, 950, 550);
		cards.add(mainCard, "mainCard");

		// Define studentSubCard
		studentSubCard = new JPanel();
		studentSubCard.setLayout(null);
		studentSubCard.setBounds(0, 0, 950, 550);
		cards.add(studentSubCard, "studentSubCard");

		// Define helpSubCard
		helpSubCard = new JPanel();
		helpSubCard.setBounds(0, 0, 950, 550);
		cards.add(helpSubCard, "helpSubCard");
		helpSubCard.setLayout(null);

		// Define settingsSubCard
		settingsSubCard = new JPanel();
		settingsSubCard.setBounds(0, 0, 950, 550);
		cards.add(settingsSubCard, "settingsSubCard");
		settingsSubCard.setLayout(null);

		// Initialize the components of the cards
		// --MenuBar
		initMenuBar();
		// --MainMenu
		initMainCard();
		initStudentSubCard();
		initHelpSubCard();
		initSettingsSubCard();
		initLoadStudents();
		readPriorityList();

		// Show mainCard on application startup
		cl = (CardLayout) (cards.getLayout());
		cl.show(cards, "mainCard");

	}

	// Define components of MenuBar
	private void initMenuBar() {
		// Side Menubar Panel
		menuBar = new JPanel();
		menuBar.setBounds(0, 0, 81, 521);
		frmMain.getContentPane().add(menuBar);
		menuBar.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		menuBar.setLayout(new GridLayout(0, 1, 0, 0));

		btnHome = new JButton("");
		btnHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHome.setContentAreaFilled(false);
		btnHome.setIcon(new ImageIcon(MainMenu.class.getResource("/images/Home.png")));
		btnHome.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(cards, "mainCard");
			}
		});
		menuBar.add(btnHome);

		btnStudent = new JButton("");
		btnStudent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnStudent.setContentAreaFilled(false);
		btnStudent.setIcon(new ImageIcon(MainMenu.class.getResource("/images/Class.png")));
		btnStudent.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(cards, "studentSubCard");
			}
		});
		menuBar.add(btnStudent);

		// Filling the space in the menu bar
		lblSpace1 = new JLabel("");
		menuBar.add(lblSpace1);
		lblSpace2 = new JLabel("");
		menuBar.add(lblSpace2);

		btnHelp = new JButton("");
		btnHelp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHelp.setContentAreaFilled(false);
		btnHelp.setIcon(new ImageIcon(MainMenu.class.getResource("/images/Help.png")));
		btnHelp.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(cards, "helpSubCard");
			}
		});
		menuBar.add(btnHelp);

		btnSettings = new JButton("");
		btnSettings.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSettings.setContentAreaFilled(false);
		btnSettings.setIcon(new ImageIcon(MainMenu.class.getResource("/images/Settings.png")));
		btnSettings.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(cards, "settingsSubCard");
			}
		});
		menuBar.add(btnSettings);
	}

	// Define components of mainCard
	private void initMainCard() {
		// Variables
		caseNotesDirectory = "/Users/" + System.getProperty("user.name") + "/Desktop/AppData/caseNotes/";
		// Buttons
		btnOpenSelected = new JButton("Open Selected");
		btnOpenSelected.setOpaque(true);
		btnOpenSelected.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnOpenSelected.setForeground(Color.WHITE);
		btnOpenSelected.setBackground(new Color(0, 102, 204));
		btnOpenSelected.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOpenSelected.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		btnOpenSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (tabbedPanePriority.getSelectedIndex() == 0) {
						selectedPriorityList = (String) modelPriority.getElementAt(listPriority.getSelectedIndex());
					}
					if (tabbedPanePriority.getSelectedIndex() == 1) {
						selectedPriorityList = (String) modelHigh.getElementAt(listHigh.getSelectedIndex());
					}
					if (tabbedPanePriority.getSelectedIndex() == 2) {
						selectedPriorityList = (String) modelMedium.getElementAt(listMedium.getSelectedIndex());
					}
					if (tabbedPanePriority.getSelectedIndex() == 3) {
						selectedPriorityList = (String) modelLow.getElementAt(listLow.getSelectedIndex());
					}

					// Loading File
					selected = new File(caseNotesDirectory + selectedPriorityList + "/" + selectedPriorityList + "Notes.txt");
					// Reading from the existing file
					stream = new FileReader(selected);
					read = new BufferedReader(stream);

					// Variables read from Notes file
					System.out.println("File found: " + read.readLine());
					selectedFirst = read.readLine();
					selectedLast = read.readLine();
					selectedGrade = read.readLine();

					// Finished
					read.close();

					// Initializing the CaseNotes Class
					cn = new CaseNotes(selectedFirst, selectedLast, selectedGrade);
					cn.frmMain.setVisible(true);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null,
							"The student's file does not exist. \nPlease select the student from the Student list first.");
					System.out.println(">>ERROR OCCURED - Reading Student File from TabbedPane<<");
				}
			}
		});
		btnOpenSelected.setBounds(501, 212, 262, 96);
		mainCard.add(btnOpenSelected);

		btnQuit = new JButton("Quit Program");
		btnQuit.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnQuit.setOpaque(true);
		btnQuit.setForeground(Color.WHITE);
		btnQuit.setBackground(new Color(204, 51, 51));
		btnQuit.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		btnQuit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int option = JOptionPane.showConfirmDialog(null,
						"Are you sure you want to quit? Any unsaved data will be lost...", null,
						JOptionPane.YES_NO_OPTION);
				if (option == 0) {
					System.exit(0);
				} else {
				}
			}
		});
		
		btnDeleteSelected = new JButton("Remove Selected");
		btnDeleteSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (tabbedPanePriority.getSelectedIndex() == 0) {
						selectedPriorityList = (String) modelPriority.getElementAt(listPriority.getSelectedIndex());
					}
					if (tabbedPanePriority.getSelectedIndex() == 1) {
						selectedPriorityList = (String) modelHigh.getElementAt(listHigh.getSelectedIndex());
					}
					if (tabbedPanePriority.getSelectedIndex() == 2) {
						selectedPriorityList = (String) modelMedium.getElementAt(listMedium.getSelectedIndex());
					}
					if (tabbedPanePriority.getSelectedIndex() == 3) {
						selectedPriorityList = (String) modelLow.getElementAt(listLow.getSelectedIndex());
					}

					// Loading File
					selected = new File("/Users/" + System.getProperty("user.name") + "/Desktop/AppData/" + "Priority.txt");
					// Reading from the existing file
					stream = new FileReader(selected);
					read = new BufferedReader(stream);
					
					// Variables
					String line;
					line = read.readLine();
					// Adding names to 'Priority' tab
					if (!line.equals("")) {
						while ((line = read.readLine()) != null) {
							modelLow.addElement(line);
							if(line.equals(selectedPriorityList)) {
								JOptionPane.showMessageDialog(null, "Student found.");
							}
						}
					}

					// Finished
					read.close();

					// Initializing the CaseNotes Class
					cn = new CaseNotes(selectedFirst, selectedLast, selectedGrade);
					cn.frmMain.setVisible(true);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null,
							"The student's file does not exist. \nPlease select the student from the Student list first.");
					System.out.println(">>ERROR OCCURED - Reading Student File from TabbedPane<<");
				}
			}
		});
		btnDeleteSelected.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeleteSelected.setOpaque(true);
		btnDeleteSelected.setForeground(Color.WHITE);
		btnDeleteSelected.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		btnDeleteSelected.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnDeleteSelected.setBackground(new Color(0, 102, 204));
		btnDeleteSelected.setBounds(501, 333, 200, 46);
		mainCard.add(btnDeleteSelected);
		btnQuit.setBounds(640, 432, 208, 78);
		mainCard.add(btnQuit);

		modelPriority = new DefaultListModel();
		modelHigh = new DefaultListModel();
		modelMedium = new DefaultListModel();
		modelLow = new DefaultListModel();

		// Labels

		lblPriorityList = new JLabel("Priority List");
		lblPriorityList.setHorizontalAlignment(SwingConstants.CENTER);
		lblPriorityList.setFont(new Font("Segoe UI", Font.PLAIN, 28));
		lblPriorityList.setBounds(56, 100, 403, 53);
		mainCard.add(lblPriorityList);

		lblWelcomeBackUser = new JLabel("Welcome back, " + firstName + "!");
		lblWelcomeBackUser.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 40));
		lblWelcomeBackUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeBackUser.setBounds(38, 11, 751, 78);
		mainCard.add(lblWelcomeBackUser);

		lblSelectAStudent = new JLabel("Select a student from the list below.");
		lblSelectAStudent.setFont(new Font("Segoe UI Semilight", Font.ITALIC, 14));
		lblSelectAStudent.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectAStudent.setBounds(66, 145, 393, 24);
		mainCard.add(lblSelectAStudent);

		// Tabbed Pane
		tabbedPanePriority = new JTabbedPane(JTabbedPane.TOP);
		tabbedPanePriority.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
		tabbedPanePriority.setBounds(66, 173, 393, 262);
		mainCard.add(tabbedPanePriority);

		// Tabbed Pane Panels
		// --Priority Tab
		scrollPanePriority = new JScrollPane();
		tabbedPanePriority.addTab("Priority", null, scrollPanePriority, null);
		listPriority = new JList(modelPriority);
		listPriority.setVisibleRowCount(16);
		listPriority.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		listPriority.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		scrollPanePriority.setColumnHeaderView(listPriority);

		// --High Tab
		scrollPaneHigh = new JScrollPane();
		tabbedPanePriority.addTab("High", null, scrollPaneHigh, null);
		listHigh = new JList(modelHigh);
		listHigh.setVisibleRowCount(16);
		listHigh.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		listHigh.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		scrollPaneHigh.setViewportView(listHigh);

		// --Medium Tab
		scrollPaneMedium = new JScrollPane();
		tabbedPanePriority.addTab("Medium", null, scrollPaneMedium, null);
		listMedium = new JList(modelMedium);
		listMedium.setVisibleRowCount(16);
		listMedium.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		listMedium.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		scrollPaneMedium.setViewportView(listMedium);

		// --Low Tab
		scrollPane = new JScrollPane();
		tabbedPanePriority.addTab("Low", null, scrollPane, null);
		listLow = new JList(modelLow);
		listLow.setVisibleRowCount(16);
		listLow.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		listLow.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		scrollPane.setViewportView(listLow);

		mainBG = new JLabel("");
		mainBG.setOpaque(true);
		mainBG.setBackground(new Color(153, 204, 255));
		mainBG.setBounds(0, 0, 869, 550);
		mainCard.add(mainBG);
	}

	// Define components of StudentSubCard
	public void initStudentSubCard() {

		// Compartments
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 130, 440, 328);
		studentSubCard.add(scrollPane);

		// Student Table
		tblStudent = new JTable();
		tblStudent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblStudent.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		scrollPane.setViewportView(tblStudent);
		// --Table Properties
		Object[] columns = { "Last Name", "First Name", "Grade" };
		model = new DefaultTableModel();
		model.setColumnIdentifiers(columns);
		// Setting the model to the table
		tblStudent.setModel(model);
		// Row and Height Properties
		tblStudent.setRowHeight(20);
		row = new Object[3];
		tblStudent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				selectedLast = (String) model.getValueAt(tblStudent.convertRowIndexToModel(tblStudent.getSelectedRow()), 0);
				selectedFirst = (String) model.getValueAt(tblStudent.convertRowIndexToModel(tblStudent.getSelectedRow()), 1);
				selectedGrade = (String) model.getValueAt(tblStudent.convertRowIndexToModel(tblStudent.getSelectedRow()), 2);

				lblStudentName.setText(selectedFirst + " " + selectedLast);
				lblGradeLevel.setText("Grade: " + selectedGrade);

				// Setting StudentName for CaseNotes file name
				studentName = selectedLast + selectedFirst;

				// Making Student Components Visible After selection
				// btnAddAttachments.setVisible(true);
				// lblAttachmentList.setVisible(true);
				btnViewCaseNotes.setVisible(true);
				lblStudentName.setVisible(true);
				lblGradeLevel.setVisible(true);
				lblPleaseSelect.setVisible(false);
			}
		});

		// Textfield
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Segoe UI Light", Font.PLAIN, 12));
		txtSearch.setBounds(65, 75, 236, 33);
		studentSubCard.add(txtSearch);
		txtSearch.setColumns(10);

		// Search
		// Essential components
		TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tblStudent.getModel());
		tblStudent.setRowSorter(rowSorter);

		// Search functions
		txtSearch.getDocument().addDocumentListener(new DocumentListener() {
			// Adding characters to search bar
			@Override
			public void insertUpdate(DocumentEvent e) {
				String text = txtSearch.getText();

				if (text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}

			}

			// Removing characters from search bar
			@Override
			public void removeUpdate(DocumentEvent e) {
				String text = txtSearch.getText();

				if (text.trim().length() == 0) {
					rowSorter.setRowFilter(null);
				} else {
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
				}
			}

			// Changed characters
			@Override
			public void changedUpdate(DocumentEvent e) {
				throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods,
																				// choose Tools | Templates.
			}

		});

		btnImportList = new JButton("Import New Student List (.csv file)");
		btnImportList.setOpaque(true);
		btnImportList.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnImportList.setForeground(new Color(255, 255, 255));
		btnImportList.setBackground(new Color(0, 102, 204));
		btnImportList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnImportList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// File Chooser
				fc = new JFileChooser("/Users/" + System.getProperty("user.name") + "/Desktop");
				fc.setDialogTitle("File Browser");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("csv", "Comma Separated Value", "CSV",
						".csv");
				fc.setFileFilter(filter);
				fc.setAcceptAllFileFilterUsed(false);
				fc.setFileHidingEnabled(true);
				int confirm = fc.showOpenDialog(null);

				if (confirm == JFileChooser.APPROVE_OPTION) {
					try {
						// Reading File
						selected = fc.getSelectedFile();
						FileReader stream = new FileReader(selected);
						BufferedReader read = new BufferedReader(stream);

						// CSV File Reading
						List<String[]> elements = new ArrayList<String[]>();
						String line = null;
						while ((line = read.readLine()) != null) {
							String[] splitted = line.split(",");

							// Add items to the row array
							row[0] = splitted[0];
							row[1] = splitted[1];
							row[2] = splitted[2];

							// Adds row array to a new row on the table
							model.addRow(row);
						}
						String csvFilePath = fc.getSelectedFile().getAbsolutePath();
						
						// Finished
						read.close();
						saveDirectory(csvFilePath);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Error occured while trying to load file."
								+ "\nPlease make sure there are only 3 columns with First Name, Last Name, and Grade.");
						System.out.println(">>ERROR OCCURED - CSV File<<");
					}
				}

			}
		});
		btnImportList.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnImportList.setBounds(194, 469, 256, 41);
		studentSubCard.add(btnImportList);

		btnViewCaseNotes = new JButton("View Case Notes");
		btnViewCaseNotes.setOpaque(true);
		btnViewCaseNotes.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnViewCaseNotes.setBackground(new Color(0, 102, 204));
		btnViewCaseNotes.setForeground(new Color(255, 255, 255));
		btnViewCaseNotes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnViewCaseNotes.setVisible(false);
		btnViewCaseNotes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				readCase();
			}
		});
		btnViewCaseNotes.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnViewCaseNotes.setBounds(554, 254, 236, 50);
		studentSubCard.add(btnViewCaseNotes);

		/*
		 * //Beta attachment system, not functional btnAddAttachments = new
		 * JButton("Add Attachment"); btnAddAttachments.setVisible(false);
		 * btnAddAttachments.setForeground(new Color(255, 255, 255));
		 * btnAddAttachments.setBackground(new Color(0, 102, 204));
		 * btnAddAttachments.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		 * btnAddAttachments.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent arg0) { addAttachments(); } });
		 * btnAddAttachments.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		 * btnAddAttachments.setBounds(495, 467, 163, 40);
		 * studentSubCard.add(btnAddAttachments);
		 * 
		 * btnRemoveAttachment = new JButton("Remove Selected");
		 * btnRemoveAttachment.setVisible(false);
		 * btnRemoveAttachment.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent arg0) { int index =
		 * listAttachments.getSelectedIndex(); modelAttachments.removeElementAt(index);
		 * }
		 * 
		 * }); btnRemoveAttachment.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		 * btnRemoveAttachment.setBounds(631, 472, 172, 31);
		 * studentSubCard.add(btnRemoveAttachment);
		 * 
		 * lblAttachmentList = new JLabel("Attachment List");
		 * lblAttachmentList.setVisible(false); lblAttachmentList.setFont(new
		 * Font("Segoe UI Light", Font.PLAIN, 18)); lblAttachmentList.setBounds(508,
		 * 305, 271, 33); studentSubCard.add(lblAttachmentList);
		 */

		lblStudentInstruction = new JLabel("Note: edits made by double clicking students will not be saved.");
		lblStudentInstruction.setHorizontalAlignment(SwingConstants.LEFT);
		lblStudentInstruction.setFont(new Font("Segoe UI Semilight", Font.ITALIC, 12));
		lblStudentInstruction.setBounds(10, 106, 393, 24);
		studentSubCard.add(lblStudentInstruction);

		lblPleaseSelect = new JLabel("Please select a student from the Student List.");
		lblPleaseSelect.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseSelect.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 17));
		lblPleaseSelect.setBounds(471, 193, 369, 126);
		studentSubCard.add(lblPleaseSelect);

		// Labels
		lblTitle = new JLabel("My Students");
		lblTitle.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 34));
		lblTitle.setBounds(20, 0, 256, 75);
		studentSubCard.add(lblTitle);

		lblGradeLevel = new JLabel("Grade Level");
		lblGradeLevel.setVisible(false);
		lblGradeLevel.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		lblGradeLevel.setBounds(508, 193, 220, 33);
		studentSubCard.add(lblGradeLevel);

		lblSearch = new JLabel("Search:");
		lblSearch.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 16));
		lblSearch.setBounds(10, 79, 77, 23);
		studentSubCard.add(lblSearch);

		lblStudentName = new JLabel("Student Name");
		lblStudentName.setVisible(false);
		lblStudentName.setFont(new Font("Segoe UI", Font.PLAIN, 28));
		lblStudentName.setBounds(508, 130, 351, 66);
		studentSubCard.add(lblStudentName);

		JScrollPane scrollPaneAttachments = new JScrollPane();
		scrollPaneAttachments.setVisible(false);
		scrollPaneAttachments.setBounds(508, 349, 295, 112);
		studentSubCard.add(scrollPaneAttachments);

		listAttachments = new JList();
		listAttachments.setVisible(false);
		scrollPaneAttachments.setViewportView(listAttachments);

		studentBg = new JLabel("");
		studentBg.setOpaque(true);
		studentBg.setBackground(new Color(153, 204, 255));
		studentBg.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		studentBg.setBounds(0, 0, 869, 532);
		studentSubCard.add(studentBg);
	}

	// Define components of HelpSubCard
	private void initHelpSubCard() {
		// Text Boxes
		lblTitle = new JLabel("User Help");
		lblTitle.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 30));
		lblTitle.setBounds(10, 11, 237, 56);
		helpSubCard.add(lblTitle);

		scrollPaneHelp = new JScrollPane();
		scrollPaneHelp.setBounds(10, 84, 800, 426);
		helpSubCard.add(scrollPaneHelp);

		lblHelpInfo = new JLabel("");
		lblHelpInfo.setIcon(new ImageIcon(MainMenu.class.getResource("/images/infograph.png")));
		lblHelpInfo.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		scrollPaneHelp.setViewportView(lblHelpInfo);

		lblHelpInstructions = new JLabel("Browse the instructions by using the scroll bar on the right side.");
		lblHelpInstructions.setHorizontalAlignment(SwingConstants.LEFT);
		lblHelpInstructions.setFont(new Font("Segoe UI Semilight", Font.ITALIC, 12));
		lblHelpInstructions.setBounds(10, 59, 393, 24);
		helpSubCard.add(lblHelpInstructions);

		helpBG = new JLabel("");
		helpBG.setBackground(new Color(153, 204, 255));
		helpBG.setOpaque(true);
		helpBG.setBounds(0, 0, 869, 521);
		helpSubCard.add(helpBG);
	}

	// Define components of SettingsSubCard
	private void initSettingsSubCard() {
		// Text Boxes
		lblTitle = new JLabel("Settings");
		lblTitle.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 26));
		lblTitle.setBounds(30, 11, 199, 51);
		settingsSubCard.add(lblTitle);

		btnDeleteAccount = new JButton("Delete Account");
		btnDeleteAccount.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnDeleteAccount.setOpaque(true);
		btnDeleteAccount.setForeground(new Color(255, 255, 255));
		btnDeleteAccount.setBackground(new Color(255, 51, 51));
		btnDeleteAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "Are you sure you would like to delete your account?",
						null, JOptionPane.YES_NO_OPTION);
				if (option == 0) {
					deleteAccount();
				} else {
				}

			}
		});

		lblDeleteIcon = new JLabel("");
		lblDeleteIcon.setBackground(new Color(255, 51, 51));
		lblDeleteIcon.setOpaque(true);
		lblDeleteIcon.setIcon(new ImageIcon(MainMenu.class.getResource("/images/Delete.png")));
		lblDeleteIcon.setBounds(573, 435, 66, 75);
		settingsSubCard.add(lblDeleteIcon);
		btnDeleteAccount.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
		btnDeleteAccount.setBounds(623, 435, 219, 75);
		settingsSubCard.add(btnDeleteAccount);

		settingsBg = new JLabel("");
		settingsBg.setBackground(new Color(153, 204, 255));
		settingsBg.setOpaque(true);
		settingsBg.setBounds(0, 0, 869, 521);
		settingsSubCard.add(settingsBg);
	}

	// Reads the Acc.txt file to verify is user has a Student List Directory, then
	// reads it
	private void initLoadStudents() {
		try {
			// Loading File
			selected = new File("/Users/" + System.getProperty("user.name") + "/Desktop/AppData/" + "Acc.txt");
			stream = new FileReader(selected);
			read = new BufferedReader(stream);

			// Variables
			firstName = read.readLine();
			lastName = read.readLine();
			username = read.readLine();
			password = read.readLine();
			stDirectory = read.readLine();

			// Welcome message on MainMenu card
			lblWelcomeBackUser.setText("Welcome back, " + firstName + "!");

			// Finished
			read.close();

			if (stDirectory == null) {
				JOptionPane.showMessageDialog(null,
						"No student list found. Please import a list under the My Students page.");
			} else {
				System.out.println("Directory found: " + stDirectory);
				// Initialize List on start-up
				try {
					// Reading File
					selected = new File(stDirectory);
					FileReader stream = new FileReader(selected);
					BufferedReader read = new BufferedReader(stream);

					// CSV File Reading
					List<String[]> elements = new ArrayList<String[]>();
					String line = null;
					while ((line = read.readLine()) != null) {
						String[] splitted = line.split(",");

						// Add items to the row array
						row[0] = splitted[0];
						row[1] = splitted[1];
						row[2] = splitted[2];

						// Adds row array to a new row on the table
						model.addRow(row);
					}

					// Finished
					read.close();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null,
							"Error occured while trying to load CSV file on startup to table model. File does not exist.");
					System.out.println(">>ERROR OCCURED - CSV File Import to Table Model<<");
				}
			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Error reading Acc.txt. Make sure the file exists, and is in the right place.");
			System.out.println(">>ERROR OCCURED - Reading Acc.txt<<");
		}
	}

	// Deleting the username and account from Acc.txt
	private void deleteAccount() {
		try {
			// Loading the file
			selected = new File("/Users/" + System.getProperty("user.name") + "/Desktop/AppData/" + "Acc.txt");
			out = new FileOutputStream(selected);
			w = new PrintWriter(out);

			// Writing variables to file
			w.println("");

			// Finished
			w.close();
			// Loading the PRIORITY file
			selected = new File("/Users/" + System.getProperty("user.name") + "/Desktop/AppData/" + "Priority.txt");
			out = new FileOutputStream(selected);
			w = new PrintWriter(out);

			// Writing variables to file
			w.println("--PRIORITY");
			w.println("--HIGH");
			w.println("--MEDIUM");
			w.println("--LOW");

			// Finished
			w.close();
			JOptionPane.showMessageDialog(null,
					"Account has been deleted. \nPlease restart the program to create a new account.");
			frmMain.dispose();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error occured while trying to delete. Somehow it failed :(");
			System.out.println(">>ERROR OCCURED - Deleting<<");
		}
	}

	// Reading and writing import file directory to Acc.txt
	private void saveDirectory(String csvFilePath) {
		try {
			// Writing to file
			account = new File("/Users/" + System.getProperty("user.name") + "/Desktop/AppData/" + "Acc.txt");
			out = new FileOutputStream(account);
			w = new PrintWriter(out);

			// Writing variables to file
			w.println(firstName);
			w.println(lastName);
			w.println(username);
			w.println(password);
			// Writes the directory of the CSV file to Acc.txt
			w.println(csvFilePath);

			// Finished
			w.close();
			JOptionPane.showMessageDialog(null, csvFilePath + "\nFile directory has been saved.");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error occured while trying to save. Somehow it failed :(");
			System.out.println(">>ERROR OCCURED - Writing<<");
		}
	}

	// Reading from the student Case Notes files
	private void readCase() {
		try {
			// Creating folder with studentName
			File folder = new File("/Users/" + System.getProperty("user.name") + "/Desktop/AppData/caseNotes/" + studentName + "/");
			folder.mkdir();
			
			// Loading File
			selected = new File(caseNotesDirectory + studentName + "/" + studentName + "Notes.txt");

			// Checking if the file exists before reading
			if (selected.exists() == false) {
				out = new FileOutputStream(selected);
				w = new PrintWriter(out);

				// Coding feedback
				System.out.println("No file found for: " + studentName + ". Creating one now.");
				
				// Finished
				w.close();

			} else {
				// Reading from the existing file
				stream = new FileReader(selected);
				read = new BufferedReader(stream);

				// Coding feedback
				System.out.println("File found: " + read.readLine());

				// Finished
				read.close();
			}

			// Initializing the CaseNotes Class
			cn = new CaseNotes(selectedFirst, selectedLast, selectedGrade);
			cn.frmMain.setVisible(true);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error occured while trying to read CaseNotes. No file exists.");
			System.out.println(">>ERROR OCCURED - Reading CaseNotes<<");
		}

	}

	// Reading from the Priority File
	private void readPriorityList() {
		try {
			// Loading File
			priorityFile = new File("/Users/" + System.getProperty("user.name") + "/Desktop/AppData/" + "Priority.txt");
			stream = new FileReader(priorityFile);
			read = new BufferedReader(stream);

			// Variables
			String line;
			line = read.readLine();
			// Adding names to 'Priority' tab
			if (!line.equals("")) {
				while (!(line = read.readLine()).equals("--HIGH")) {
					modelPriority.addElement(line);
				}
				// Adding names to 'High' tab
				while (!(line = read.readLine()).equals("--MEDIUM")) {
					modelHigh.addElement(line);
				}
				// Adding names to 'Medium' tab
				while (!(line = read.readLine()).equals("--LOW")) {
					modelMedium.addElement(line);
				}
				// Adding names to 'Low' tab
				while ((line = read.readLine()) != null) {
					modelLow.addElement(line);
				}
			}

			// Finished
			read.close();

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error occured while trying to read Priority. No file exists.");
			System.out.println(">>ERROR OCCURED - Reading Priority List<<");
		}
	}
}
