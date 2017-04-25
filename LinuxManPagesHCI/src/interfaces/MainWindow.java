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

import Controllers.TopicPanelManager;
import reader.UCommand;
import reader.XMLReader;

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
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SpringLayout;
import java.awt.Component;
import java.awt.CardLayout;
import javax.swing.JTextField;
import java.awt.Font;

public class MainWindow {

	private JFrame contentFrame;
	private JTextField serachTextField;

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
		lblUnixCommands.setFont(new Font("Impact", Font.BOLD | Font.ITALIC, 22));
		springLayout.putConstraint(SpringLayout.NORTH, lblUnixCommands, 6, SpringLayout.NORTH, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblUnixCommands, 6, SpringLayout.WEST, contentFrame.getContentPane());
		lblUnixCommands.setHorizontalAlignment(SwingConstants.LEFT);
		contentFrame.getContentPane().add(lblUnixCommands);

		JPanel locationTracking = new JPanel();
		springLayout.putConstraint(SpringLayout.SOUTH, lblUnixCommands, -6, SpringLayout.NORTH, locationTracking);
		springLayout.putConstraint(SpringLayout.EAST, lblUnixCommands, 0, SpringLayout.EAST, locationTracking);
		springLayout.putConstraint(SpringLayout.NORTH, locationTracking, 53, SpringLayout.NORTH, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, locationTracking, 6, SpringLayout.WEST, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, locationTracking, 121, SpringLayout.NORTH, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, locationTracking, 231, SpringLayout.WEST, contentFrame.getContentPane());
		contentFrame.getContentPane().add(locationTracking);
		locationTracking.setLayout(new GridLayout(0, 1, 0, 0));

		JScrollPane optionSelectPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, optionSelectPane, 12, SpringLayout.SOUTH, locationTracking);
		springLayout.putConstraint(SpringLayout.WEST, optionSelectPane, 6, SpringLayout.WEST, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, optionSelectPane, -10, SpringLayout.SOUTH, contentFrame.getContentPane());
		contentFrame.getContentPane().add(optionSelectPane);

		JPanel selectionPanel = new JPanel();
		optionSelectPane.setViewportView(selectionPanel);
		selectionPanel.setLayout(new CardLayout(0, 0));

		JPanel topicsPanel = new JPanel();
		selectionPanel.add(topicsPanel, "topicsPanel");
		topicsPanel.setLayout(new GridLayout(3, 5, 0, 0));

		JPanel commandsPanel = new JPanel();
		selectionPanel.add(commandsPanel, "commandsPanel");
		commandsPanel.setLayout(new GridLayout(3,5, 0,0));

		JPanel optionalArgsPanel = new JPanel();
		selectionPanel.add(optionalArgsPanel, "argsPanel");
		optionalArgsPanel.setLayout(new GridLayout(3,5,0,0));
		
		JTextPane txtpnTbd = new JTextPane();
		springLayout.putConstraint(SpringLayout.EAST, optionSelectPane, -10, SpringLayout.WEST, txtpnTbd);
		springLayout.putConstraint(SpringLayout.NORTH, txtpnTbd, 0, SpringLayout.NORTH, optionSelectPane);
		springLayout.putConstraint(SpringLayout.WEST, txtpnTbd, contentFrame.getWidth()/2, SpringLayout.WEST, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, txtpnTbd, -10, SpringLayout.SOUTH, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtpnTbd, -10, SpringLayout.EAST, contentFrame.getContentPane());
		txtpnTbd.setText("TBD");
		contentFrame.getContentPane().add(txtpnTbd);
		
		serachTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, serachTextField, 11, SpringLayout.NORTH, lblUnixCommands);
		springLayout.putConstraint(SpringLayout.WEST, serachTextField, 373, SpringLayout.EAST, locationTracking);
		
		JButton btnTopLevel = new JButton("Topics");
		btnTopLevel.setIcon(null);
		locationTracking.add(btnTopLevel);
		springLayout.putConstraint(SpringLayout.EAST, serachTextField, 0, SpringLayout.EAST, txtpnTbd);
		contentFrame.getContentPane().add(serachTextField);
		serachTextField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		springLayout.putConstraint(SpringLayout.NORTH, btnSearch, 11, SpringLayout.NORTH, lblUnixCommands);
		springLayout.putConstraint(SpringLayout.EAST, btnSearch, -6, SpringLayout.WEST, serachTextField);
		contentFrame.getContentPane().add(btnSearch);
		
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
		 * clicking the argument button will lock the text into the text panel
		 */

		File folder = new File("./XML_Files");
		File[] listOfFiles = folder.listFiles();
		List<UCommand> commands = new ArrayList<UCommand>();
		for (File file : listOfFiles) {
			if (file.isFile()) {
				System.out.println(file.getName());
				System.out.println(XMLReader.readXMLToCommand(file));
				commands.add(XMLReader.readXMLToCommand(file));
			}
		}
		
		List<String> categories = new ArrayList<String>();
		for(UCommand command: commands){
			if(!categories.contains(command.getCategory())){
				categories.add(command.getCategory());
				JButton button = new JButton(command.getCategory());
				topicsPanel.add(button);
				button.setAlignmentX(Component.CENTER_ALIGNMENT);
				button.addMouseListener(new TopicPanelManager(txtpnTbd, command.getCategory(), commands, selectionPanel, commandsPanel, optionalArgsPanel));
			}
		}
		
	}
}
