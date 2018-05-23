package project1;

import java.text.DecimalFormat;

import java.io.*;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**********************************************************************
  Creates the methods for our StopWatch.

  @author Jarod Collier and Ben Burger
  @version 5/11/2018
 *********************************************************************/

public class StopWatch {

	/** Keeps track of minutes in StopWatch */
	private int minutes;

	/** Keeps track of seconds in StopWatch */
	private int seconds;

	/** Keeps track of milliseconds in StopWatch */
	private int milliseconds;

	/** Static variable that will be used to suspend all operations */
	private static boolean suspend = false;

	/******************************************************************
	  Constructor that initializes the instance variables with
	   provided minutes, seconds, and milliseconds
	  @param minutes
	  @param seconds
	  @param milliseconds
	 *****************************************************************/
	public StopWatch(int minutes, int seconds, int milliseconds) {
		super();
		if (minutes < 0 || seconds < 0 || milliseconds < 0)
			throw new IllegalArgumentException("Must " +
					"input positive numbers");

		if ( seconds > 59)
			throw new IllegalArgumentException("Seconds " +
					"must be between 0 and 59");

		if ( milliseconds > 999)
			throw new IllegalArgumentException("Milliseconds " +
					"must be between 0 and 999");

		this.minutes = minutes;
		this.seconds = seconds;
		this.milliseconds = milliseconds;
	}

	/******************************************************************
	  Constructor that initializes the instance variables with 
	  provided seconds and milliseconds
	  @param seconds
	  @param milliseconds
	 *****************************************************************/
	public StopWatch(int seconds, int milliseconds) {
		super();
		if (seconds < 0 || milliseconds < 0)
			throw new IllegalArgumentException("Must " +
					"input positive numbers");

		if ( seconds > 59)
			throw new IllegalArgumentException("Seconds " +
					"must be between 0 and 59");

		if ( milliseconds > 999)
			throw new IllegalArgumentException("Milliseconds " +
					"must be between 0 and 999");

		this.seconds = seconds;
		this.milliseconds = milliseconds;
	}

	/******************************************************************
	  Constructor that initializes the instance variable milliseconds  
	  @param milliseconds
	 *****************************************************************/
	public StopWatch(int milliseconds) {
		super();
		if (milliseconds < 0)
			throw new IllegalArgumentException("Must " +
					"input positive numbers");

		if ( milliseconds > 999)
			throw new IllegalArgumentException("Milliseconds " +
					"must be between 0 and 999");

		this.milliseconds = milliseconds;
	}

	/******************************************************************
	  Default constructor for objects in StopWatch class 
	  that sets the StopWatch to zero
	 *****************************************************************/
	public StopWatch() {
		super();
		minutes = 0;
		seconds = 0;
		milliseconds = 0;
	}

	/******************************************************************
	  Constructor that accepts a string as a parameter with
	  format "1:21:300", "15:200" or "300".	  
	  @param startTime String that the user chooses
	  @return none
	 *****************************************************************/
	public StopWatch(String startTime) {
		if (!startTime.matches("[0-9:]+"))
			throw new IllegalArgumentException("String may only " +
					"contain 0-9 and a colon");

		if (startTime.substring(startTime.length() -1).equals(":"))
			throw new IllegalArgumentException("Can't end string " +
					"with a colon");

		if (startTime.substring(0,1).equals(":"))
			throw new IllegalArgumentException("Can't begin " +
					"string with a colon");

		if (startTime.contains("::"))
			throw new IllegalArgumentException("Can't have " + 
					"string with sequential colons");

		String[] temp = startTime.split(":"); 

		//Checks Array of length 3 for valid inputs
		if (temp.length == 3) {
			if ( Integer.parseInt(temp[1]) > 59) 
				throw new IllegalArgumentException("Seconds " +
						"must be between 0 and 59");

			if ( Integer.parseInt(temp[2]) > 999)
				throw new IllegalArgumentException("Milliseconds" +
						" must be between 0 and 999");

			minutes = Integer.parseInt(temp[0]);
			seconds = Integer.parseInt(temp[1]);
			milliseconds = Integer.parseInt(temp[2]);
		} 

		//Checks Array of length 2 for valid inputs
		else if (temp.length == 2) {

			if ( Integer.parseInt(temp[0]) > 59)
				throw new IllegalArgumentException("Seconds " +
						"must be between 0 and 59");

			if ( Integer.parseInt(temp[1]) > 999)
				throw new IllegalArgumentException("Milliseconds " +
						"must be between 0 and 999");

			seconds = Integer.parseInt(temp[0]);
			milliseconds = Integer.parseInt(temp[1]);
		}

		//Checks Array of length 1 for valid input
		else if (temp.length == 1) {

			if ( Integer.parseInt(temp[0]) > 999)
				throw new IllegalArgumentException("Milliseconds " +
						"must be between 0 and 999");

			milliseconds = Integer.parseInt(temp[0]);
		}
		else
			throw new IllegalArgumentException("String must have " +
					"valid input");
	}

	/******************************************************************
	  toString for StopWatch class	  
	  @param none
	  @return String with format "0:00:000"
	 *****************************************************************/
	@Override
	public String toString() {

		DecimalFormat secondsFmt = new DecimalFormat("00");
		DecimalFormat millisecondsFmt = new DecimalFormat("000");

		return this.minutes + ":" + secondsFmt.format(seconds) +
				":" + millisecondsFmt.format(milliseconds);
	}

	/******************************************************************
	  Returns the minutes for the stopwatch	  
	  @param none
	  @return minutes integer from stopwatch
	 *****************************************************************/
	public int getMinutes() {
		return minutes;
	}

	/******************************************************************
	  Sets the amount of minutes on the stopwatch

	  @param minutes to be set for the StopWatch
	  @return none
	 *****************************************************************/
	public void setMinutes(int minutes) {
		if(!suspend) {
			if (minutes < 0)
				throw new IllegalArgumentException("Minutes " +
						"must be positive");

			this.minutes = minutes;
		}
	}

	/******************************************************************
	  Returns the seconds for the stopwatch  
	  @param none
	  @return seconds from stopwatch
	 *****************************************************************/
	public int getSeconds() {
		return seconds;
	}

	/******************************************************************
	  Sets the amount of seconds on the stopwatch   
	  @param seconds to be set for the StopWatch
	  @return none
	 *****************************************************************/
	public void setSeconds(int seconds) {
		if (!suspend) {
			if (seconds < 0)
				throw new IllegalArgumentException("Seconds " +
						"must be positive");
			if (seconds > 59)
				throw new IllegalArgumentException("Seconds " +
						"must be between 0 and 59");

			this.seconds = seconds;
		}
	}

	/******************************************************************
	  returns the milliseconds on the stopwatch  
	  @param none
	  @return milliseconds from stopwatch
	 *****************************************************************/
	public int getMilliseconds() {
		return milliseconds;
	}

	/******************************************************************
	  Sets the amount of milliseconds on the stopwatch 	  
	  @param milliseconds to be set for the StopWatch
	  @return none
	 *****************************************************************/
	public void setMilliseconds(int milliseconds) {
		if (!suspend) {
			if (milliseconds < 0)
				throw new IllegalArgumentException("Milliseconds " +
						"must be positive");
			if (milliseconds > 999)
				throw new IllegalArgumentException("Milliseconds " +
						"must be between 0 and 999");

			this.milliseconds = milliseconds;
		} 
	}

	//	/**************************************************************
	//	Returns true if "this" StopWatch object is the 
	//	same as the other StopWatch object
	//	@param other stopwatch 
	//	@return true or false depending if StopWatches equal
	//	**************************************************************/
	//	public boolean equals (StopWatch other) {
	//		if (this.minutes == other.minutes && 
	//				this.seconds == other.seconds &&
	//				this.milliseconds == other.milliseconds) 
	//			return true;
	//		return false;
	//	}

	/******************************************************************
	  Returns true if "this" StopWatch object is the same 
	  as the other object 
	  @param an Object called other
	  @return true or false if object is the same or different
	 *****************************************************************/
	public boolean equals (Object other) {
		if(other != null && other instanceof StopWatch) {
			if (this.minutes == ((StopWatch) other).minutes &&
					this.seconds == ((StopWatch) other).seconds &&
					this.milliseconds ==
					((StopWatch) other).milliseconds)
				return true;
		}
		return false;
	}

	/******************************************************************
	  Static method that returns true if StopWatch object s1 
	  is exactly the same as StopWatch object s2         
	  @param StopWatch object s1
	  @param StopWatch object s2
	  @return true or false if objects are the same or different
	 *****************************************************************/
	public static boolean equals (StopWatch s1, StopWatch s2) {
		if( s1.minutes == s2. minutes && s1.seconds == s2.seconds
				&& s1.milliseconds == s2.milliseconds)
			return true;
		return false;
	}

	/******************************************************************
	  Takes the time on the stopWatch and converts it to milliseconds	  
	  @param none
	  @return none
	 *****************************************************************/
	public void convertToMilliseconds() {
		milliseconds += (minutes * 60000) + (seconds * 1000);

		minutes = 0;
		seconds = 0;
	}

	/******************************************************************
	  Takes the amount of milliseconds and converts it to the 
	  equivalent amount of minutes and seconds	  
	  @param none
	  @return none
	 *****************************************************************/
	public void convertToStopwatch() {
		minutes += (milliseconds / 60000);
		milliseconds %= 60000;

		seconds += (milliseconds / 1000);
		milliseconds %= 1000;
	}

	/******************************************************************
	  Returns 1 if the time represented by "this" StopWatch object is 
	  greater than the time represented by the other StopWatch.
	  Returns -1 if other StopWatch is greater and 0 if they are equal.
	  @param other StopWatch
	  @return 1, -1, or 0 depending on which Stopwatch has more time
	 *****************************************************************/
	public int compareTo (StopWatch other) {
		convertToMilliseconds();
		other.convertToMilliseconds();

		int comparison;

		if( this.milliseconds > other.milliseconds)
			comparison = 1;
		else if (this.milliseconds < other.milliseconds)
			comparison = -1;
		else
			comparison = 0;

		convertToStopwatch();

		return comparison;
	}

	/******************************************************************
	  Adds the number of milliseconds to "this" StopWatch object
	  @param milliseconds to be added to the stopwatch
	  @return none
	 *****************************************************************/
	public void add (int milliseconds) {
		if (!suspend) {
			if(milliseconds < 0)
				throw new IllegalArgumentException("Milliseconds " +
						"to add must be positive");

			for (int i = 0; i < milliseconds; i++) 
				inc();
		}
	}

	/******************************************************************
	  Adds StopWatch other to the "this" StopWatch.
	  @param other StopWatch 
	  @return none
	 *****************************************************************/
	public void add (StopWatch other) {	
		if (!suspend) {
			other.convertToMilliseconds();

			add(other.milliseconds);

			convertToStopwatch();
		}
	}

	/******************************************************************
	  Increments the "this" StopWatch by 1 millisecond	  
	  @param none
	  @return none
	 *****************************************************************/
	public void inc () {
		if (!suspend) {
			convertToMilliseconds();

			this.milliseconds++;

			convertToStopwatch();
		}
	}

	/******************************************************************
	  Decrements the "This" StopWatch by 1 millisecond	  
	  @param none
	  @return none
	 *****************************************************************/
	public void dec () {
		if (!suspend) {
			convertToMilliseconds();

			if (milliseconds > 0) 
				this.milliseconds--;

			convertToStopwatch();
		}

	}

	/******************************************************************
	  Subtracts the number of milliseconds from "This" StopWatch object.
	  @param milliseconds to be subtracted out
	  @return none
	 *****************************************************************/
	public void sub (int milliseconds) {
		if (!suspend) {
			if(milliseconds < 0)
				throw new IllegalArgumentException("Milliseconds " +
						"to subtract must be positive");

			for (int i = 0; i < milliseconds; i++)
				dec();
		}
	}

	/******************************************************************
	  Subtracts StopWatch other from the "this" StopWatch
	  @param other StopWatch
	  @return none
	 *****************************************************************/
	public void sub (StopWatch other) {	
		if (!suspend) {
			other.convertToMilliseconds();

			sub(other.milliseconds);

			this.convertToStopwatch();
		}
	}


	/******************************************************************
	  Loads the "this" StopWatch from a file
	  @param fileName that the user wants to load
	  @return none
	 *****************************************************************/
	public void load(String fileName) {
		if (!suspend) {
			try{

				// open the data file
				Scanner fileReader = new Scanner(new File(fileName)); 

				//Reads the value for each variable
				minutes = fileReader.nextInt();
				seconds = fileReader.nextInt();
				milliseconds = fileReader.nextInt();

				fileReader.close();
			}

			// problem reading the file
			catch(Exception error){
				JOptionPane.showMessageDialog(null, 
						"File does not exist.");
			} 
		}
	}

	/******************************************************************
	  Saves the "this" StopWatch to a file
	  @param fileName chosen by the user to save
	  @return none
	 *****************************************************************/
	public void save(String fileName) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(
					new FileWriter(fileName)));
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		out.println(minutes);
		out.println(seconds);
		out.println(milliseconds);
		out.close(); 
	}

	/******************************************************************
	  Suspends all operations when the flag is true
	  @param true or false depending on user choice to suspend tasks
	  @return none
	 *****************************************************************/
	public static void suspend (boolean flag) {
		StopWatch.suspend = flag;
	}

	/******************************************************************
 	  Returns true if the StopWatch is not suspended
 	  @param none
	  @return True or false whether StopWatch is suspended
	 *****************************************************************/
	public static boolean isSuspended () { 
		return suspend; 
	}

}