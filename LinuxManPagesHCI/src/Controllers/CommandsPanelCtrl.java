package Controllers;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
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
		
		JButton previousLevel = new JButton(command.getName());
		previousLevel.setPreferredSize(new Dimension(198,33));
		previousLevel.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				((CardLayout)selectionPanel.getLayout()).show(selectionPanel, "argsPanel");
			}
		});
		GridBagConstraints gbc = new GridBagConstraints();
//		gbc.anchor = GridBagConstraints.PAGE_END;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 2;
		historyPanel.add(previousLevel, gbc);
		historyPanel.repaint();
		
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
