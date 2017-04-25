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
import reader.CommandSearch;
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
import java.awt.Dimension;
import java.awt.CardLayout;
import javax.swing.JTextField;
import java.awt.Font;

public class MainWindow {

	private JFrame contentFrame;
	private JTextField search;

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
		lblUnixCommands.setFont(new Font("Impact", Font.BOLD | Font.ITALIC, 22));
		lblUnixCommands.setHorizontalAlignment(SwingConstants.LEFT);
		contentFrame.getContentPane().add(lblUnixCommands);

		JPanel locationTracking = new JPanel();
		springLayout.putConstraint(SpringLayout.SOUTH, lblUnixCommands, -6, SpringLayout.NORTH, locationTracking);
		springLayout.putConstraint(SpringLayout.WEST, locationTracking, 6, SpringLayout.WEST, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, locationTracking, 164, SpringLayout.NORTH, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, locationTracking, -410, SpringLayout.EAST, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, locationTracking, 53, SpringLayout.NORTH, contentFrame.getContentPane());
		contentFrame.getContentPane().add(locationTracking);

		JScrollPane optionSelectPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, optionSelectPane, 14, SpringLayout.SOUTH, locationTracking);
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
		springLayout.putConstraint(SpringLayout.SOUTH, txtpnTbd, -10, SpringLayout.SOUTH, contentFrame.getContentPane());
		txtpnTbd.setEditable(false);
		springLayout.putConstraint(SpringLayout.WEST, txtpnTbd, contentFrame.getWidth()/2, SpringLayout.WEST, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtpnTbd, -10, SpringLayout.EAST, contentFrame.getContentPane());
		txtpnTbd.setText("TBD");
		contentFrame.getContentPane().add(txtpnTbd);;

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
				button.addMouseListener(new TopicPanelManager(txtpnTbd, command.getCategory(), commands, selectionPanel, commandsPanel, optionalArgsPanel, locationTracking));
			}
		}

		CommandSearch finder = new CommandSearch(commands);

		JTextField search = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, search, -10, SpringLayout.EAST, contentFrame.getContentPane());
		search.setToolTipText("What do you want to do?");
		springLayout.putConstraint(SpringLayout.NORTH, search, 11, SpringLayout.NORTH, lblUnixCommands);
		
		GridBagLayout gbl_locationTracking = new GridBagLayout();
		gbl_locationTracking.columnWidths = new int[]{384, 0};
		gbl_locationTracking.rowHeights = new int[] {34, 34, 34};
		gbl_locationTracking.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_locationTracking.rowWeights = new double[]{0.0, 0.0};
		locationTracking.setLayout(gbl_locationTracking);

		JButton btnTopLevel = new JButton("General Topics");
		btnTopLevel.setPreferredSize(new Dimension(198, 33));
		btnTopLevel.setIcon(null);
		btnTopLevel.setMaximumSize(new Dimension(198, 33));
		
		GridBagConstraints gbc_btnTopLevel = new GridBagConstraints();
		gbc_btnTopLevel.fill = GridBagConstraints.HORIZONTAL;
//		gbc_btnTopLevel.anchor = GridBagConstraints.PAGE_START;
		gbc_btnTopLevel.gridx = 0;
		gbc_btnTopLevel.gridy = 0;
		locationTracking.add(btnTopLevel, gbc_btnTopLevel);
		
		btnTopLevel.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				locationTracking.removeAll();
				locationTracking.add(btnTopLevel, gbc_btnTopLevel);
				locationTracking.repaint();
				((CardLayout)selectionPanel.getLayout()).show(selectionPanel, "topicsPanel");
			}
		});
		
		
		
		
		contentFrame.getContentPane().add(search);
		search.setColumns(10);

		JButton btnSearch = new JButton("Search");
		springLayout.putConstraint(SpringLayout.EAST, btnSearch, -202, SpringLayout.EAST, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, search, 6, SpringLayout.EAST, btnSearch);
		springLayout.putConstraint(SpringLayout.EAST, lblUnixCommands, -282, SpringLayout.WEST, btnSearch);
		springLayout.putConstraint(SpringLayout.NORTH, btnSearch, 11, SpringLayout.NORTH, lblUnixCommands);
		btnSearch.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				List<UCommand> results = finder.searchByString(search.getText());
				String result = "";
				for(UCommand c: results){
					result += c.toString() + "\n\n";
				}
				txtpnTbd.setText(result);
			}
		});
		contentFrame.getContentPane().add(btnSearch);

	}
}
