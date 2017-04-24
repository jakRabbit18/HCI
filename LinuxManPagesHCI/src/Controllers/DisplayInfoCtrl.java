package Controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTextPane;

import reader.UCommand;

public class DisplayInfoCtrl extends MouseAdapter {

	List<UCommand> commands;
	JTextPane textPane;
	String category;
	
	public DisplayInfoCtrl(List<UCommand> commands, String category, JTextPane textPane) {
		this.commands = commands;
		this.textPane = textPane;
		this.category = category;
	}
	
	
}
