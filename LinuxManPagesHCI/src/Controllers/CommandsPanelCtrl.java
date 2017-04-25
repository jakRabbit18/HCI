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

public class CommandsPanelCtrl extends MouseAdapter {

	UCommand command;
	JPanel argsPanel, selectionPanel, historyPanel;
	JTextPane textPane;
	String category;
	
	public CommandsPanelCtrl(UCommand command, String category, JTextPane textPane, JPanel selectionPanel, JPanel argsPanel, JPanel locationHistory) {
		this.command = command;
		this.textPane = textPane;
		this.category = category;
		this.argsPanel = argsPanel;
		this.selectionPanel = selectionPanel;
		this.historyPanel = locationHistory;
	}
	
	@Override
	public void mousePressed(MouseEvent e){
		argsPanel.removeAll();
		
		for(UArg arg: command.getArgs()){
			JButton butt = new JButton(arg.getCall());
			butt.addMouseListener(new ArgsPanelCtrl(command, arg, textPane, historyPanel));
			argsPanel.add(butt);
		}
		((CardLayout)selectionPanel.getLayout()).show(selectionPanel, "argsPanel");
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e){
		textPane.setText(command.getDescription());
	}
	
	@Override
	public void mouseExited(MouseEvent e){
		textPane.setText("");
	}
	
}
