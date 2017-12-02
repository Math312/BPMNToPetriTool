package ynu.edu.frame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import ynu.edu.Main;
import ynu.edu.module.rule.BPMNtoPetri.TransferByRule1;
import ynu.edu.module.rule.BPMNtoPetri.TransferByRule2;

public class MainFrame {

	private JFrame frmBpmntopetriTool;
	private JTextField textField;
	private JTextField textField_1;
	private String inputFilePath;
	private String outputFilePath;
	private JFileChooser FileChooser;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frmBpmntopetriTool.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		FileChooser = new JFileChooser();
		frmBpmntopetriTool = new JFrame();
		frmBpmntopetriTool.setTitle("BPMNToPetri Tool");
		frmBpmntopetriTool.setBounds(100, 100, 596, 424);
		frmBpmntopetriTool.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBpmntopetriTool.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "input", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(14, 13, 554, 136);
		frmBpmntopetriTool.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("File Path:\r\n");
		lblNewLabel.setBounds(30, 58, 80, 18);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(124, 55, 277, 24);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Choose");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = FileChooser.showOpenDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) 
				{
					File file = FileChooser.getSelectedFile();
					String filePath = file.getAbsolutePath();
					String suffix = getSuffix(filePath);
					if(!suffix.equals(".bpmn")) 
					{
						 JOptionPane.showMessageDialog(null, "Please enter a file whose suffix name is \".bpmn\".","ERROR",JOptionPane.ERROR_MESSAGE);
					}
					else 
					{
						inputFilePath = filePath;
						textField.setText(inputFilePath);
					}
					
				}
			}
		});
		btnNewButton.setBounds(415, 54, 113, 27);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "output", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(14, 162, 554, 205);
		frmBpmntopetriTool.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("File Path:");
		lblNewLabel_1.setBounds(28, 65, 82, 18);
		panel_1.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(124, 62, 272, 24);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Choose");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = FileChooser.showSaveDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) 
				{
					String filePath = FileChooser.getSelectedFile().getAbsolutePath();
					String suffix = getSuffix(filePath);
					if(!suffix.equals(".xml")) 
					{
						JOptionPane.showMessageDialog(null, "Please enter a file whose suffix name is \".xml\".","ERROR",JOptionPane.ERROR_MESSAGE);
					}
					else 
					{
						outputFilePath = filePath;
						textField_1.setText(outputFilePath);
						System.out.println(getSuffix(outputFilePath));
					}
					
				}
			}
		});
		btnNewButton_1.setBounds(410, 61, 113, 27);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Rule1");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(inputFilePath == null) 
				{
					JOptionPane.showMessageDialog(null, "Please choose a file to be the input!","ERROR",JOptionPane.ERROR_MESSAGE);
				}
				else if(outputFilePath == null) 
				{
					JOptionPane.showMessageDialog(null, "Please choose a file-name to save the output!","ERROR",JOptionPane.ERROR_MESSAGE);
				}
				else 
				{
					Main main = new Main(new TransferByRule1());
					main.transfer(inputFilePath, outputFilePath);
					JOptionPane.showMessageDialog(null, "Successful transformation!","Tip",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton_2.setBounds(80, 149, 113, 27);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Rule2");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(inputFilePath == null) 
				{
					JOptionPane.showMessageDialog(null, "Please choose a file to be the input!","ERROR",JOptionPane.ERROR_MESSAGE);
				}
				else if(outputFilePath == null) 
				{
					JOptionPane.showMessageDialog(null, "Please choose a file-name to save the output!","ERROR",JOptionPane.ERROR_MESSAGE);
				}
				else 
				{
					Main main = new Main(new TransferByRule2());
					main.transfer(inputFilePath, outputFilePath);
					JOptionPane.showMessageDialog(null, "Successful transformation!","Tip",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton_3.setBounds(316, 149, 113, 27);
		panel_1.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("?");
		btnNewButton_4.setPreferredSize(new Dimension(0, 0));
		btnNewButton_4.setMargin(new Insets(0, 0, 0, 0));
		btnNewButton_4.setMinimumSize(new Dimension(0, 0));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnNewButton_4.setBounds(514, 165, 26, 27);
		panel_1.add(btnNewButton_4);
	}
	
	private String getSuffix(String fileName) 
	{
		return fileName.substring(fileName.lastIndexOf("."));
	}
}
