package Controllers;


import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import reader.UArg;
import reader.UCommand;

public class TopicPanelManager extends MouseAdapter{

	List<UCommand> commands;
	JTextPane textPane;
	JPanel selectionPanel, commandsPanel, argsPanel;
	String category;
	
	public TopicPanelManager(JTextPane tpane,String category, List<UCommand> commands, JPanel parent, JPanel commandsPanel, JPanel argsPanel){
		super();
		this.commands = commands;
		this.selectionPanel = parent;
		this.commandsPanel = commandsPanel;
		this.argsPanel = argsPanel;
		this.category = category;
		this.textPane = tpane;
	}
	
	@Override
	public void mousePressed(MouseEvent e){
		commandsPanel.removeAll();
		for(UCommand c: commands){
			if(c.getCategory().equals(category)){
				JButton b = new JButton(c.getName());
				commandsPanel.add(b);
				((CardLayout)selectionPanel.getLayout()).show(selectionPanel, "commandsPanel");
				//TODO add listeners to these buttons to get to their arguments
				b.addMouseListener(new CommandsPanelCtrl(c, c.getCategory(), textPane, selectionPanel, argsPanel));
			}
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e){
		String commNames = "";
		for(UCommand comm: commands){
			if(comm.getCategory().equals(category)){
				commNames += comm.getSynopsis() +"\n";
			}
		}
		textPane.setText(commNames);
	}
	
	@Override
	public void mouseExited(MouseEvent e){
		textPane.setText("");
	}
}
