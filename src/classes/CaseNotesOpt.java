package classes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

public class CaseNotesOpt extends JFrame {

	private JPanel contentPane;
	// Essential Components
	public JFrame caseNotesOptFrame;
	public String finalPath;
	public CaseNotes cnmain;

	// Text Boxes
	public JTextField filePath;
	public JLabel lblPath;
	public JLabel lblInstructions;

	// Radio Buttons
	public JRadioButton windows;
	public JRadioButton mac;

	// Buttons
	public JButton btnCancel;
	public JButton btnExpSave;
	public JButton btnExport;

	// IO
	private File saved;
	private FileOutputStream stream;
	private PrintWriter write;
	
	//Printing / Exporting Variables
	private String notes;
	private String studentName;
	private File printFile;
	private String fileName;

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
	// Create the application.
	public CaseNotesOpt(String studentName, String notes, JButton x, JButton y, JButton z, JButton a, JButton b, JButton c,  JLabel bg) {
		initialize(studentName, notes, x, y, z, a, b, c, bg);
	}

	// Initialize the contents of the frame.
	private void initialize(String studentName, String notes, JButton x, JButton y, JButton z, JButton a, JButton b, JButton c, JLabel bg) {
		this.studentName = studentName;
		this.notes = notes;
		// Main Frame
		caseNotesOptFrame = new JFrame();
		caseNotesOptFrame.setTitle("Case Notes Generator - Options");
		caseNotesOptFrame.setSize(550, 400);
		caseNotesOptFrame.setLocationRelativeTo(bg);
		caseNotesOptFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		caseNotesOptFrame.getContentPane().setLayout(null);
		caseNotesOptFrame.getContentPane().setBackground(Color.WHITE);

		// Buttons
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				caseNotesOptFrame.dispose();
				x.setEnabled(true);
				y.setEnabled(true);
				z.setEnabled(true);
				a.setEnabled(true);
				b.setEnabled(true);
				c.setEnabled(true);
			}
		});
		btnCancel.setBounds(21, 274, 205, 54);
		caseNotesOptFrame.getContentPane().add(btnCancel);

		
		

		//Text Boxes
		filePath = new JTextField();
		filePath.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		filePath.setBounds(21, 178, 502, 39);
		caseNotesOptFrame.getContentPane().add(filePath);
		filePath.setColumns(10);

		lblPath = new JLabel("---");
		lblPath.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblPath.setText("Save to:");
		lblPath.setBounds(21, 151, 205, 26);
		caseNotesOptFrame.getContentPane().add(lblPath);

		lblInstructions = new JLabel("Select your current Operating System:");
		lblInstructions.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblInstructions.setBounds(21, 21, 502, 26);
		caseNotesOptFrame.getContentPane().add(lblInstructions);

		//Radio Buttons
		windows = new JRadioButton("Windows");
		windows.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		windows.setBackground(Color.WHITE);
		windows.setBounds(25, 64, 201, 35);
		windows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					filePath.setText("C:/Users/" + System.getProperty("user.name") + "/Desktop/");
					btnExport.setEnabled(true);
			}
		});
		caseNotesOptFrame.getContentPane().add(windows);

		mac = new JRadioButton("Mac");
		mac.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		mac.setBackground(Color.WHITE);
		mac.setBounds(318, 64, 201, 35);
		mac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					filePath.setText("/Users/" + System.getProperty("user.name") + "/Desktop/");
					btnExport.setEnabled(true);
			}
		});
		caseNotesOptFrame.getContentPane().add(mac);

		ButtonGroup group = new ButtonGroup();
		group.add(windows);
		group.add(mac);
		
		btnExport = new JButton("Export");
		btnExport.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				finalPath = filePath.getText();
				finalSave(finalPath);
				a.setEnabled(true);
				b.setEnabled(true);
				c.setEnabled(true);
				x.setEnabled(true);
				y.setEnabled(true);
				z.setEnabled(true);
				caseNotesOptFrame.dispose();
			}
		});
		btnExport.setBounds(314, 274, 205, 54);
		btnExport.setEnabled(false);
		caseNotesOptFrame.getContentPane().add(btnExport);
	}

	public void finalSave(String path){
		fileName = studentName + " ExportNotes";
		try {        
			//Creating File
			printFile = new File(path + fileName + ".txt");
			//Checker
			if(printFile.createNewFile()){
				//Proceed as normal
				stream = new FileOutputStream(printFile);
				write = new PrintWriter(stream);
			} else {
				stream = new FileOutputStream(printFile);
				write = new PrintWriter(stream);
				int option = JOptionPane.showConfirmDialog(null, "A file with this name already exists. Do you want to overwrite it?", null, JOptionPane.YES_NO_OPTION);
				if(option == 0){
					//Proceed as normal
					stream = new FileOutputStream(printFile);
					write = new PrintWriter(stream);
				}else{
					String oldName = fileName;

					while(true){
						fileName = JOptionPane.showInputDialog("What would you like to name this file?");

						if(fileName.equals("")){
							JOptionPane.showMessageDialog(null, "Please enter a valid name.");
						}else if(fileName.equals(oldName)){
							JOptionPane.showMessageDialog(null, "File will be overwritten.");
							stream = new FileOutputStream(printFile);
							write = new PrintWriter(stream);
							break;
						}else{
							//Renaming File
							printFile = new File(filePath + fileName + ".txt");
							stream = new FileOutputStream(printFile);
							write = new PrintWriter(stream);
							break;
						}
					}
				}
			}


			//Variables
			write.println(notes);

			//Finished
			write.close();
			
			JOptionPane.showMessageDialog(null, "File has been created on your desktop.");
		}catch(IOException e){
			JOptionPane.showMessageDialog(null, "Error occured during file creation. Please enter valid file path.");
			System.out.println(">>ERROR OCCURED - Save<<");
		}	
		
		}
	}

