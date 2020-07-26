package classes;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.awt.Rectangle;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class Index extends JFrame {
	// Essential Components
	public JFrame frmMain;
	public Container mainCont;
	public CardLayout cl;
	private MainMenu main;
	boolean activatedLogin = false;

	// Graphics
	// --LoginMain

	// Buttons
	// --LoginMain
	private JPanel cards;
	private JPanel mainLogin;
	private JPanel createUser;
	private JButton btnLogin;
	// --CreateUser
	private JButton btnCreate;

	// IO
	private File selected;
	private FileReader stream;
	private BufferedReader read;
	private FileOutputStream out;
	private PrintWriter w;

	// Variables
	private String firstName;
	private String lastName;
	private String username;
	private String password;

	// Text Boxes
	// --CreateUser
	public JLabel lblLockIcon;
	public JLabel lblUsername;
	public JLabel lblUserName;
	private JLabel lblSubText;
	private JLabel lblInstructions;
	private JLabel lblTitle;
	private JLabel lblFirstName;
	private JLabel lblLastName;
	private JLabel lblPassword;
	// --CreateUser
	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField tfUsername;
	private JTextField tfCreatePassword;
	private JPasswordField pwdPassword;
	private JLabel lblBg;
	private JLabel label;

	// Launch the application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index window = new Index();
					window.frmMain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Create the application
	public Index() {
		initialize();
	}

	// Define the frame
	private void initialize() {
		// Main Frame
		frmMain = new JFrame();
		frmMain.setTitle("Counsellor Login Page");
		frmMain.setResizable(false);
		frmMain.setSize(950, 550);
		frmMain.setLocationRelativeTo(null);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Main Content Pane
		mainCont = frmMain.getContentPane();
		mainCont.setLayout(null);
		mainCont.setBackground(Color.WHITE);

		// Main panel to hold other panels
		cards = new JPanel(new CardLayout());
		cards.setBounds(0, 0, 950, 550);
		mainCont.add(cards);

		// Define mainLogin
		mainLogin = new JPanel();
		mainLogin.setLayout(null);
		mainLogin.setBounds(0, 0, 950, 550);
		cards.add(mainLogin, "mainLogin");

		// Define createUser
		createUser = new JPanel();
		createUser.setLayout(null);
		createUser.setBounds(0, 0, 1018, 728);
		cards.add(createUser, "createUser");

		// Checks the status of the Acc.txt file
		accountRead();

		// Initialize the components of the cards
		// --LoginMain
		initLoginMain();
		// --CreateUser
		initCreateUser();

		// Show mainCard on application startup
		cl = (CardLayout) (cards.getLayout());

		// If acc.txt has an account
		if (activatedLogin == true) {
			cl.show(cards, "mainLogin");
		}
		// If acc.txt is empty
		if (activatedLogin == false) {
			cl.show(cards, "createUser");
		}
	}

	// Define components of LoginMain
	private void initLoginMain() {
		// Labels
		lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		lblPassword.setBounds(211, 224, 114, 29);
		mainLogin.add(lblPassword);

		pwdPassword = new JPasswordField();
		pwdPassword.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		pwdPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (arg0.getKeyChar() == KeyEvent.VK_ENTER) {
					btnLogin.doClick();
				}
			}
		});
		pwdPassword.setBounds(321, 223, 330, 34);
		mainLogin.add(pwdPassword);

		lblUserName = new JLabel("User: " + username);
		lblUserName.setForeground(Color.BLACK);
		lblUserName.setFont(new Font("Segoe UI Semilight", Font.BOLD, 40));
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setBounds(0, 133, 950, 59);
		mainLogin.add(lblUserName);

		btnLogin = new JButton("Login");
		btnLogin.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnLogin.setBackground(new Color(51, 153, 255));
		btnLogin.setOpaque(true);
		btnLogin.setForeground(Color.WHITE);
		// btnLogin.setBackground(new Color(51, 102, 255));
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 20));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkPassword();
			}
		});
		btnLogin.setBounds(341, 287, 272, 76);
		mainLogin.add(btnLogin);

		lblBg = new JLabel("");
		lblBg.setIcon(new ImageIcon(Index.class.getResource("/images/Login.jpg")));
		lblBg.setBackground(new Color(128, 0, 0));
		lblBg.setOpaque(true);
		lblBg.setBounds(0, 0, 950, 528);
		mainLogin.add(lblBg);

	}

	private void initCreateUser() {
		// Components
		lblSubText = new JLabel("I see that you've never run this program before, so let's set up an account.");
		lblSubText.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubText.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 23));
		lblSubText.setBounds(0, 65, 940, 48);
		createUser.add(lblSubText);

		lblInstructions = new JLabel("Please enter the following information, then click create account:");
		lblInstructions.setFont(new Font("Segoe UI Light", Font.PLAIN, 18));
		lblInstructions.setBounds(108, 108, 632, 48);
		createUser.add(lblInstructions);

		lblTitle = new JLabel("Hi there!");
		lblTitle.setForeground(new Color(0, 0, 255));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 50));
		lblTitle.setBounds(0, 11, 940, 48);
		createUser.add(lblTitle);

		lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 23));
		lblFirstName.setBounds(102, 165, 143, 33);
		createUser.add(lblFirstName);

		tfFirstName = new JTextField();
		tfFirstName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		tfFirstName.setBounds(239, 167, 372, 33);
		createUser.add(tfFirstName);
		tfFirstName.setColumns(10);

		lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 23));
		lblLastName.setBounds(102, 209, 143, 33);
		createUser.add(lblLastName);

		tfLastName = new JTextField();
		tfLastName.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		tfLastName.setColumns(10);
		tfLastName.setBounds(239, 209, 372, 33);
		createUser.add(tfLastName);

		lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 23));
		lblUsername.setBounds(102, 253, 126, 33);
		createUser.add(lblUsername);

		tfUsername = new JTextField();
		tfUsername.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		tfUsername.setColumns(10);
		tfUsername.setBounds(239, 253, 372, 33);
		createUser.add(tfUsername);

		lblPassword = new JLabel("Password: ");
		lblPassword.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 23));
		lblPassword.setBounds(102, 297, 143, 33);
		createUser.add(lblPassword);

		tfCreatePassword = new JTextField();
		tfCreatePassword.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		tfCreatePassword.setColumns(10);
		tfCreatePassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (arg0.getKeyChar() == KeyEvent.VK_ENTER) {
					btnCreate.doClick();
				}
			}
		});
		tfCreatePassword.setBounds(239, 297, 372, 33);
		
		createUser.add(tfCreatePassword);

		// Buttons
		btnCreate = new JButton("Create Account");
		btnCreate.setOpaque(true);
		btnCreate.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCreate.setForeground(new Color(255, 255, 255));
		btnCreate.setBackground(new Color(0, 102, 204));
		btnCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Create account confirmation
				username = tfUsername.getText();
				if (tfUsername.getText().equals("") || tfFirstName.getText().equals("")
						|| tfLastName.getText().equals("") || tfCreatePassword.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter the missing information.", "Error Message",
							JOptionPane.ERROR_MESSAGE);
				} else {
					try {
						createAppDataFolder();
						createCaseNotesFolder();
						createAttachmentsFolder();
						accountWrite();
						priorityWrite();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Error when creating the app data folders.");
						System.out.println("Error occured. <<createAppDataFolder>>");
					}
					main = new MainMenu(username);
					main.frmMain.setVisible(true);
					// Pass the username to MainMenu here
					frmMain.dispose();
					JOptionPane.showMessageDialog(null, "If this is your first time using the program, "
							+ "\nplease look at the instructions in the '?' panel.");
				}
			}
		});
		btnCreate.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		btnCreate.setBounds(386, 352, 225, 69);
		createUser.add(btnCreate);

		label = new JLabel("");
		label.setIcon(new ImageIcon(Index.class.getResource("/images/background-blurred-1587.jpg")));
		label.setBounds(0, 0, 950, 544);
		createUser.add(label);
	}

	// Reading from Acc.txt to check if there is anything in the file
	private void accountRead() {
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

			// Finished
			read.close();

			if (username == null) {
				JOptionPane.showMessageDialog(null, "Acc.txt exists. No account was found.");
			} else {
				activatedLogin = true;
			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Starting program for first time. Creating Acc.txt");
			System.out.println(">>ERROR OCCURED - Loading<<");
		}
	}

	// Writing to the Acc.txt File after account creation
	private void accountWrite() {
		try {
			// Loading the file
			selected = new File("/Users/" + System.getProperty("user.name") + "/Desktop/AppData/" + "Acc.txt");
			out = new FileOutputStream(selected);
			w = new PrintWriter(out);

			// Writing variables to file
			w.println(tfFirstName.getText());
			w.println(tfLastName.getText());
			w.println(tfUsername.getText());
			w.println(tfCreatePassword.getText());

			// Finished
			w.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,
					"Error occured while trying to write to file. Nothing was written in TextField.");
			System.out.println(">>ERROR OCCURED - Writing<<");
		}
	}

	// Writing to the Priority.txt File after account creation
	private void priorityWrite() {
		try {
			// Loading the file
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
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error occured while trying to create Priority.txt.");
			System.out.println(">>ERROR OCCURED - Priority.txt Creation<<");
		}
	}

	// Checks the password when logging in
	private void checkPassword() {
		// Process the password.
		String temp = new String(pwdPassword.getPassword());
		if (temp.equals(password)) {
			System.out.println("Success! You typed the right password.");

			// Opening Main Menu
			main = new MainMenu(username);
			main.frmMain.setVisible(true);
			frmMain.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Invalid password. Try again.", "Error Message",
					JOptionPane.ERROR_MESSAGE);

			// For debugging
			System.out.println(temp + " " + password);
		}
	}

	// Creates a folder 'caseNotes' on the Desktop for storing all notes
	private void createAppDataFolder() throws IOException {
		File folder = new File("/Users/" + System.getProperty("user.name") + "/Desktop/AppData/");
		folder.mkdir();
	}

	// Creates a folder 'caseNotes' on the Desktop for storing all notes
	private void createCaseNotesFolder() throws IOException {
		File folder = new File("/Users/" + System.getProperty("user.name") + "/Desktop/AppData/caseNotes/");
		folder.mkdir();
	}

	// Creates a folder 'caseNotes' on the Desktop for storing all notes
	private void createAttachmentsFolder() throws IOException {
		File folder = new File("/Users/" + System.getProperty("user.name") + "/Desktop/AppData/Attachments/");
		folder.mkdir();
	}
}
