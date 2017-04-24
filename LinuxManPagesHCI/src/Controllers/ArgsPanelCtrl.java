package Controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextPane;

import reader.UArg;
import reader.UCommand;

public class ArgsPanelCtrl extends MouseAdapter{

	UCommand command;
	UArg arg;
	JTextPane textPane;
	int idNum;

	static Boolean lockText = false;
	static int buttonHold = 0;
	static int numButtons = 0;

	public ArgsPanelCtrl(UCommand command, UArg arg, JTextPane textPane) {
		this.command = command;
		this.arg = arg;
		this.textPane = textPane;
		idNum = ++numButtons;
	}

	@Override
	public void mousePressed(MouseEvent e){
		if(buttonHold == idNum){
			lockText = !lockText;
		} else {
			lockText = true;
			buttonHold = idNum;
		}
		String s = arg.getCall() + " " + arg.getLongCall() + "\n" + arg.getDescription();
		textPane.setText(s);
	}

	@Override
	public void mouseEntered(MouseEvent e){
		if(!lockText){
			String s = "ButtonID:" + idNum + " " + arg.getCall() + " " + arg.getLongCall() + "\n" + arg.getDescription();
			textPane.setText(s);
		}
	}
	@Override
	public void mouseExited(MouseEvent e){
		if(!lockText){
			textPane.setText("");
		}
	}
}
