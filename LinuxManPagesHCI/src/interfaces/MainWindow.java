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

import Controllers.ArgsPanelCtrl;
import Controllers.CommandsPanelCtrl;
import Controllers.TopicPanelManager;
import reader.CommandSearch;
import reader.UArg;
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
		contentFrame.setBounds(100, 100, 800, 483);
		contentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		contentFrame.getContentPane().setLayout(springLayout);

		JLabel lblUnixCommands = new JLabel("Unix Commands");
		springLayout.putConstraint(SpringLayout.NORTH, lblUnixCommands, 6, SpringLayout.NORTH, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblUnixCommands, 6, SpringLayout.WEST, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblUnixCommands, -569, SpringLayout.EAST, contentFrame.getContentPane());
		lblUnixCommands.setFont(new Font("Impact", Font.BOLD | Font.ITALIC, 22));
		lblUnixCommands.setHorizontalAlignment(SwingConstants.LEFT);
		contentFrame.getContentPane().add(lblUnixCommands);

		JPanel locationTracking = new JPanel();
		springLayout.putConstraint(SpringLayout.SOUTH, lblUnixCommands, -6, SpringLayout.NORTH, locationTracking);
		springLayout.putConstraint(SpringLayout.WEST, locationTracking, 6, SpringLayout.WEST, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, locationTracking, -410, SpringLayout.EAST, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, locationTracking, 53, SpringLayout.NORTH, contentFrame.getContentPane());
		contentFrame.getContentPane().add(locationTracking);

		JLabel locationTitle = new JLabel("General Topics");
		locationTitle.setFont(new Font("Impact", Font.BOLD | Font.ITALIC, 18));
		springLayout.putConstraint(SpringLayout.NORTH, locationTitle, 10, SpringLayout.SOUTH, locationTracking);
		springLayout.putConstraint(SpringLayout.WEST, locationTitle, 10, SpringLayout.WEST, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, locationTitle, -569, SpringLayout.EAST, contentFrame.getContentPane());
		contentFrame.getContentPane().add(locationTitle);

		JScrollPane optionSelectPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, optionSelectPane, 67, SpringLayout.SOUTH, locationTracking);
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
		optionalArgsPanel.setLayout(new GridLayout(3,5,0,0));;

		JPanel informationContainer = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, informationContainer, 10, SpringLayout.EAST, optionSelectPane);
		springLayout.putConstraint(SpringLayout.SOUTH, informationContainer, -10, SpringLayout.SOUTH, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, informationContainer, -10, SpringLayout.EAST, contentFrame.getContentPane());
		contentFrame.getContentPane().add(informationContainer);
		informationContainer.setLayout(new CardLayout(0, 0));

		JTextPane textDisplayPane = new JTextPane();
		informationContainer.add(textDisplayPane, "textPane");
		springLayout.putConstraint(SpringLayout.EAST, optionSelectPane, -10, SpringLayout.WEST, textDisplayPane);
		springLayout.putConstraint(SpringLayout.NORTH, textDisplayPane, 0, SpringLayout.NORTH, optionSelectPane);
		springLayout.putConstraint(SpringLayout.SOUTH, textDisplayPane, -10, SpringLayout.SOUTH, contentFrame.getContentPane());
		textDisplayPane.setEditable(false);
		springLayout.putConstraint(SpringLayout.WEST, textDisplayPane, contentFrame.getWidth()/2, SpringLayout.WEST, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, textDisplayPane, -10, SpringLayout.EAST, contentFrame.getContentPane());
		textDisplayPane.setText("TBD");

		JPanel resultsPanel = new JPanel();

		JScrollPane searchResultsPane = new JScrollPane();
		searchResultsPane.setViewportView(resultsPanel);
		resultsPanel.setLayout(new GridLayout(0, 1, 0, 0));

		informationContainer.add(textDisplayPane, "InfoPanel");
		informationContainer.add(searchResultsPane, "SearchResultsPane");



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
				button.addMouseListener(new TopicPanelManager(textDisplayPane, command.getCategory(), commands, selectionPanel, commandsPanel, optionalArgsPanel, locationTracking, locationTitle));
				button.addMouseListener(new MouseAdapter(){
					@Override
					public void mousePressed(MouseEvent e){
						resultsPanel.removeAll();
						((CardLayout)informationContainer.getLayout()).show(informationContainer,"InfoPanel");
					}
				});
			}
		}

		CommandSearch finder = new CommandSearch(commands);

		JTextField search = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, search, 53, SpringLayout.NORTH, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, search, 6, SpringLayout.EAST, locationTracking);
		springLayout.putConstraint(SpringLayout.SOUTH, search, -373, SpringLayout.SOUTH, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, search, -190, SpringLayout.EAST, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, locationTracking, 0, SpringLayout.SOUTH, search);
		springLayout.putConstraint(SpringLayout.NORTH, informationContainer, 11, SpringLayout.SOUTH, search);
		search.setToolTipText("What do you want to do?");

		GridBagLayout gbl_locationTracking = new GridBagLayout();
		gbl_locationTracking.columnWidths = new int[]{100, 100,100};
		gbl_locationTracking.rowHeights = new int[] {34};
		gbl_locationTracking.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_locationTracking.rowWeights = new double[]{0.0};
		locationTracking.setLayout(gbl_locationTracking);

		JButton btnTopLevel = new JButton("Topics");
		btnTopLevel.setPreferredSize(new Dimension(60, 33));
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
				locationTitle.setText("General Topics");
			}
		});
		btnTopLevel.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				resultsPanel.removeAll();
				((CardLayout)informationContainer.getLayout()).show(informationContainer,"InfoPanel");
			}
		});

		contentFrame.getContentPane().add(search);
		search.setColumns(10);

		JButton btnSearch = new JButton("Search");
		springLayout.putConstraint(SpringLayout.NORTH, btnSearch, 53, SpringLayout.NORTH, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnSearch, 616, SpringLayout.WEST, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnSearch, -11, SpringLayout.NORTH, informationContainer);
		springLayout.putConstraint(SpringLayout.EAST, btnSearch, 91, SpringLayout.EAST, search);
		btnSearch.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				List<UCommand> results = finder.searchByString(search.getText());
				resultsPanel.removeAll();
				for(UCommand c: results){
					JButton b = new JButton(c.getName());
					b.setPreferredSize(new Dimension(384, 34));
					b.setMaximumSize(new Dimension(360, 34));
					b.addMouseListener(new MouseAdapter(){
						@Override
						public void mousePressed(MouseEvent e){
							optionalArgsPanel.removeAll();
							locationTracking.removeAll();
							locationTracking.add(btnTopLevel, gbc_btnTopLevel);
							commandsPanel.removeAll();
							
							//populate the commands panel
							for(UCommand com: commands){
								if(com.getCategory().equals(c.getCategory())){
									JButton butt = new JButton(com.getName());
									butt.addMouseListener(new CommandsPanelCtrl(com, com.getCategory(), textDisplayPane, selectionPanel, optionalArgsPanel, locationTracking, locationTitle));
									commandsPanel.add(butt);
								}
							}
							
							//set the panel to show the arguments
							for(UArg arg: c.getArgs()){
								JButton butt = new JButton(arg.getCall());
								butt.addMouseListener(new ArgsPanelCtrl(c, arg, textDisplayPane, locationTracking, locationTitle));
								optionalArgsPanel.add(butt);
							}
							((CardLayout)selectionPanel.getLayout()).show(selectionPanel, "argsPanel");
							
							//add tracking button w/functionality
							JButton categoryButton = new JButton(c.getCategory());
							categoryButton.setPreferredSize(new Dimension(100,33));
							categoryButton.addMouseListener(new MouseAdapter(){
								@Override
								public void mousePressed(MouseEvent e){
									if(locationTracking.getComponents().length > 2){
										locationTracking.remove(2);
										locationTracking.repaint();
									}
									((CardLayout)selectionPanel.getLayout()).show(selectionPanel, "commandsPanel");
									locationTitle.setText(c.getCategory() + " Commands");
									locationTitle.repaint();
								}
							});
							
							//add tracking button w/functionality
							JButton commandButton = new JButton(c.getName());
							commandButton.setPreferredSize(new Dimension(100,33));
							commandButton.addMouseListener(new MouseAdapter(){
								@Override
								public void mousePressed(MouseEvent e){
									((CardLayout)selectionPanel.getLayout()).show(selectionPanel, "argsPanel");
									locationTitle.setText(c.getName() + " Arguments");
								}
							});
							
							GridBagConstraints gbc_cmd = new GridBagConstraints();
							gbc_cmd.fill = GridBagConstraints.HORIZONTAL;
							gbc_cmd.gridx = 2;
							gbc_cmd.gridy = 0;
							
							GridBagConstraints gbc_cat = new GridBagConstraints();
							gbc_cat.fill = GridBagConstraints.HORIZONTAL;
							gbc_cat.gridx = 1;
							gbc_cat.gridy = 0;
							
							locationTracking.add(categoryButton, gbc_cat);
							locationTracking.add(commandButton, gbc_cmd);
							locationTitle.setText(c.getName() + " Arguments");
							optionalArgsPanel.repaint();
							((CardLayout)informationContainer.getLayout()).show(informationContainer,"InfoPanel");
						}
					});
					GridBagConstraints gbc_result = new GridBagConstraints();
					gbc_result.fill = GridBagConstraints.HORIZONTAL;
					if(resultsPanel.getComponents().length < 1){
						gbc_result.gridy = 0;
					} else {
						gbc_result.gridy = GridBagConstraints.RELATIVE;
					}
					gbc_result.gridx = 0;
//					resultsPanel.add(b, gbc_result);
					resultsPanel.add(b);
					((CardLayout)informationContainer.getLayout()).show(informationContainer,"SearchResultsPane");
				}
				resultsPanel.repaint();
			}
		});
		contentFrame.getContentPane().add(btnSearch);

		JButton btnClear = new JButton("Clear");
		springLayout.putConstraint(SpringLayout.NORTH, btnClear, 53, SpringLayout.NORTH, contentFrame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnClear, 6, SpringLayout.EAST, btnSearch);
		springLayout.putConstraint(SpringLayout.SOUTH, btnClear, -11, SpringLayout.NORTH, informationContainer);
		springLayout.putConstraint(SpringLayout.EAST, btnClear, -17, SpringLayout.EAST, contentFrame.getContentPane());
		contentFrame.getContentPane().add(btnClear);
		btnClear.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				resultsPanel.removeAll();
				textDisplayPane.setText("");
				((CardLayout)informationContainer.getLayout()).show(informationContainer,"InfoPanel");
			}
		});



	}
}
