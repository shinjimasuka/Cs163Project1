package project1;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**********************************************************************
Creates the main panel for the StopWatch GUI.

@author Ben Burger and Jarod Collier
@version 5/22/2018
*********************************************************************/

public class MainPanel extends JPanel {

	private TimerPanel p1;
	private TimerPanel p2;
	private TimerPanel p3;
	
	private JButton suspendButton;

	public MainPanel () {
		
		ButtonListener buttonListen = new ButtonListener();
		
		p1 = new TimerPanel();
		p2 = new TimerPanel(); 
		p3 = new TimerPanel();
		suspendButton = new JButton("suspend");
		 
		suspendButton.addActionListener(buttonListen);
		
		setLayout(new GridBagLayout());
        GridBagConstraints loc = new GridBagConstraints();
        
        loc.gridx = 0;
        loc.gridy = 0;
        loc.insets.bottom = 40;
        add (p1, loc);
        
        loc.gridy = 1;
        add (p2, loc);
        
        loc.gridy = 2;
        add (p3, loc);
		
        loc.gridy = 5;
		add (suspendButton, loc);
	}
	
	private class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {	
			
			if (event.getSource() == suspendButton ) {
				if (StopWatch.isSuspended()) {
					StopWatch.suspend(false);
				} 
				else {
					StopWatch.suspend(true);
				}
			}
		}
	}
}

