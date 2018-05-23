package project1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.lang.Integer;
import java.lang.String;
import project1.TimerPanel;

/**********************************************************************
Creates a stop watch panel.

@author Ben Burger and Jarod Collier
@version 5/22/2018
*********************************************************************/

public class TimerPanel extends JPanel {
	
	private static final int TIME_DELAY = 10;
	
	private StopWatch stopwatch;
	private Timer javaTimer;
	private TimerListener timerListener; 
	
	private JButton startButton;
	private JButton stopButton;
	private JButton addButton;
	private JButton subButton;
	private JButton loadButton;
	private JButton saveButton;
	private JButton resetButton;
	
	private JTextField minutesField;
	private JTextField secondsField;
	private JTextField millisecondsField;
	
	private JLabel timerLabel;
	private JLabel minutesLabel;
	private JLabel secondsLabel;
	private JLabel millisecondsLabel;
	
	public TimerPanel () {
		
		stopwatch = new StopWatch(0,0,0);
		timerListener = new TimerListener();
		javaTimer = new Timer(TIME_DELAY, timerListener);
	
		startButton = new JButton("start");
		stopButton = new JButton("stop");
		addButton = new JButton("add");
		subButton = new JButton("sub");
		loadButton = new JButton("load");
		saveButton = new JButton("save");
		resetButton = new JButton("reset");

		minutesField = new JTextField (4);
		secondsField = new JTextField (4);
		millisecondsField = new JTextField (4);
		
		timerLabel = new JLabel();
		minutesLabel = new JLabel("minutes");
		secondsLabel = new JLabel("seconds");
		millisecondsLabel = new JLabel("milliseconds");
		
		ButtonListener buttonListen = new ButtonListener();
		
		startButton.addActionListener(buttonListen);
		stopButton.addActionListener(buttonListen);
		addButton.addActionListener(buttonListen);
		subButton.addActionListener(buttonListen);
		loadButton.addActionListener(buttonListen);
		saveButton.addActionListener(buttonListen);
		resetButton.addActionListener(buttonListen);

		setLayout(new GridBagLayout());
        GridBagConstraints loc = new GridBagConstraints();
        
        loc.insets.bottom = 2;
        
        loc.gridx = 1;
        loc.gridy = 0;
        add(timerLabel, loc);
         
        loc.gridx = 0;
        loc.gridy = 1;
        loc.anchor = GridBagConstraints.LINE_START; 
        add(startButton, loc);
        
        loc.gridx = 2;
        loc.gridy = 1;
        loc.anchor = GridBagConstraints.LINE_END; 
        add(stopButton, loc);
        
        loc.gridx = 0;
        loc.gridy = 2;
        loc.anchor = GridBagConstraints.LINE_START; 
        add(addButton, loc);
        
        loc.gridx = 2;
        loc.gridy = 2;
        loc.anchor = GridBagConstraints.LINE_END; 
        add(subButton, loc);
        
        loc.gridx = 1;
        loc.gridy = 4;
        loc.insets.bottom = 5;
        loc.anchor = GridBagConstraints.CENTER; 
        add(resetButton, loc);
        
        loc.insets.bottom = 2;
        
        loc.gridx = 0;
        loc.gridy = 3;
        loc.anchor = GridBagConstraints.LINE_START; 
        add(saveButton, loc);
        
        loc.gridx = 2;
        loc.gridy = 3;
        loc.anchor = GridBagConstraints.LINE_END; 
        add(loadButton, loc);
        
        loc.gridx = 0;
        loc.gridy = 5;
        loc.anchor = GridBagConstraints.CENTER; 
        add(minutesField, loc);
        
        loc.gridx = 1;
        loc.gridy = 5;
        add(secondsField, loc);
        
        loc.gridx = 2;
        loc.gridy = 5;
        add(millisecondsField, loc);
        
        loc.gridx = 0;
        loc.gridy = 6;
        add(minutesLabel, loc);
         
        loc.gridx = 1;
        loc.gridy = 6;
        add(secondsLabel, loc);
        
        loc.gridx = 2;
        loc.gridy = 6;
        add(millisecondsLabel, loc);
         
        setBorder(BorderFactory.createLineBorder(Color.black));
        
        minutesField.setText("0");
        secondsField.setText("0");
        millisecondsField.setText("0");
        
        timerLabel.setText(stopwatch.toString());
	} 
	
	private class TimerListener implements ActionListener {
		
		public void actionPerformed (ActionEvent event) {
			
			stopwatch.add(TIME_DELAY);
			timerLabel.setText(stopwatch.toString());
		}
	}

	public class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {			
			
			int minutes, seconds, milliseconds;
			
			if (event.getSource() == stopButton) {
				javaTimer.stop();
			}
			
			if (event.getSource() == startButton) {
				javaTimer.start();
			}
			
			if (event.getSource() == resetButton) {
				stopwatch = new StopWatch();
				
				minutesField.setText("0");
				secondsField.setText("0");
				millisecondsField.setText("0");
				
				javaTimer.stop();
			}
			
			if (event.getSource() == addButton) {
				try {
						minutes = Integer.parseInt(minutesField.getText());
						seconds = Integer.parseInt(secondsField.getText());
						milliseconds = Integer.parseInt(millisecondsField.getText());
						StopWatch stopwatch2 = new StopWatch(minutes, seconds, milliseconds);
						stopwatch.add(stopwatch2);
						minutesField.setText("0");
						secondsField.setText("0");
						millisecondsField.setText("0");
						
				}
				catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Enter integers.");
				}
				catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(null, "Invalid input.");
				}
			}
			
			if (event.getSource() == subButton) {
				try {
						minutes = Integer.parseInt(minutesField.getText());
						seconds = Integer.parseInt(secondsField.getText());
						milliseconds = Integer.parseInt(millisecondsField.getText());
						StopWatch stopwatch2 = new StopWatch(minutes, seconds, milliseconds);
						stopwatch.sub(stopwatch2);
						minutesField.setText("0");
						secondsField.setText("0");
						millisecondsField.setText("0");
				}
				catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Enter integers.");
				}
				catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(null, "Invalid input.");
				}
			}
			
			if (event.getSource() == saveButton) {
				String filename = JOptionPane.showInputDialog("Enter a filename:");
				stopwatch.save(filename);
			}
			
			if (event.getSource() == loadButton) {
				String filename = JOptionPane.showInputDialog("Enter a filename:");
				stopwatch.load(filename);
			}
	
			timerLabel.setText(stopwatch.toString());
		}
	}
}
