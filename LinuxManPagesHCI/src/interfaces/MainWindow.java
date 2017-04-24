package interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextPane;
import javax.swing.JTree;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SpringLayout;
import java.awt.Component;
import java.awt.CardLayout;

public class MainWindow {

	private JFrame contentFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.contentFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		contentFrame = new JFrame();
		contentFrame.setResizable(false);
		contentFrame.setTitle("Linux Manual");
		contentFrame.setBounds(100, 100, 800, 600);
		contentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		contentFrame.getContentPane().setLayout(springLayout);
		
		JLabel lblUnixCommands = new JLabel("Unix Commands");
		springLayout.putConstraint(SpringLayout.NORTH, lblUnixCommands, 6, SpringLayout.NORTH, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblUnixCommands, 6, SpringLayout.WEST, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblUnixCommands, 28, SpringLayout.NORTH, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblUnixCommands, 150, SpringLayout.WEST, contentFrame.getContentPane());
		lblUnixCommands.setHorizontalAlignment(SwingConstants.LEFT);
		contentFrame.getContentPane().add(lblUnixCommands);
		
		JPanel locationTracking = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, locationTracking, 53, SpringLayout.NORTH, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, locationTracking, 6, SpringLayout.WEST, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, locationTracking, 121, SpringLayout.NORTH, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, locationTracking, 231, SpringLayout.WEST, contentFrame.getContentPane());
		contentFrame.getContentPane().add(locationTracking);
		locationTracking.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblTrackingLocationsLike = new JLabel("Tracking Locations Like...");
		locationTracking.add(lblTrackingLocationsLike);
		
		JScrollPane optionSelectPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, optionSelectPane, 133, SpringLayout.NORTH, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, optionSelectPane, 6, SpringLayout.WEST, contentFrame.getContentPane());
		contentFrame.getContentPane().add(optionSelectPane);
		
		JPanel selctionPanel = new JPanel();
		optionSelectPane.setViewportView(selctionPanel);
		selctionPanel.setLayout(new CardLayout(0, 0));
		
		JPanel topicsPanel = new JPanel();
		selctionPanel.add(topicsPanel, "name_42501900295119");
		topicsPanel.setLayout(new GridLayout(3, 5, 0, 0));
		
		/**
		 * for all of these buttons, they need to be replaced with a function
		 * that reads all the xmls available and makes a button for each topic described
		 * in those files. These need to be loaded into some sort of list or hashmap to make it
		 * easy to pick and choose from them as need be.
		 * 
		 * Buttons need to give an overview of the category that they represent when hovered over,
		 * and switch to the commandsPanel when pressed, loading the appropriate commands into the 
		 * commands panel
		 * 
		 * Buttons in the commands panel need to give a basic overview of the command in the 
		 * infoPane when hovered over. This locks into the upper text pane when the button is pressed,
		 * and the selection pane is next populated with the optinal arguments. When hovered over, the 
		 * arg buttons should cause the description of the arguments to appear in the the textPane
		 * clicking the argument button will lock the text into the text pane
		 */
		JButton btnBasics = new JButton("Basics");
		topicsPanel.add(btnBasics);
		btnBasics.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JButton btnNetwork = new JButton("Network");
		topicsPanel.add(btnNetwork);
		btnNetwork.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNetwork.setAlignmentY(Component.TOP_ALIGNMENT);
		
		JButton btnFileManagement = new JButton("File Management");
		topicsPanel.add(btnFileManagement);
		btnFileManagement.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JButton btnIO = new JButton("I/O");
		topicsPanel.add(btnIO);
		
		JButton btnGitFucntions = new JButton("Git Fucntions");
		topicsPanel.add(btnGitFucntions);
		
		JPanel commandsPanel = new JPanel();
		selctionPanel.add(commandsPanel, "name_42531984596752");
		
		JPanel optionaArgsPanel = new JPanel();
		selctionPanel.add(optionaArgsPanel, "name_42544981621797");
		
		
		btnIO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btnBasics.addMouseListener(new MouseAdapter() {
			//TODO add "display overview of category" text actions
			@Override
			public void mouseEntered(MouseEvent e) {
			}
		});
		
		JTextPane txtpnTbd = new JTextPane();
		springLayout.putConstraint(SpringLayout.WEST, txtpnTbd, contentFrame.getWidth()/2, SpringLayout.WEST, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, optionSelectPane, 0, SpringLayout.SOUTH, txtpnTbd);
		springLayout.putConstraint(SpringLayout.EAST, optionSelectPane, -10, SpringLayout.WEST, txtpnTbd);
		springLayout.putConstraint(SpringLayout.NORTH, txtpnTbd, 10, SpringLayout.NORTH, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, txtpnTbd, -10, SpringLayout.SOUTH, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtpnTbd, -10, SpringLayout.EAST, contentFrame.getContentPane());
		txtpnTbd.setText("TBD");
		contentFrame.getContentPane().add(txtpnTbd);
	}
}
