/**
 * Displays the current class period at Carleton College. It's hardly the most
 * sophisticated program, but it'll look nice on my desktop.
 * 
 * Last modified 2015-04-17
 * @author Michael Stoneman
 * @version 0.1.1
 */

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.LocalTime;
import static java.time.temporal.ChronoUnit.MINUTES;

public class krlxtime {
	/**
	 * @return the current time, as a LocalTime object.
	 */
	public static LocalTime getTime() {
		return LocalTime.now();
	}

	/**
	 * Creates a list of krlxCourse objects corresponding to the day's schedule.
	 * 
	 * @return an ArrayList of krlxCourse objects.
	 */
	public static ArrayList<krlxCourse> getSchedule() {
		int day = LocalDate.now().getDayOfWeek().getValue();
		ArrayList<krlxCourse> today = new ArrayList<krlxCourse>();
		// Monday and Wednesday
		if (day == 1 || day == 3) {
			today.add(new krlxCourse(" 1a ", "08:30", 70));
			today.add(new krlxCourse(" 2a ", "09:50", 70));
			today.add(new krlxCourse(" 3a ", "11:10", 70));
			today.add(new krlxCourse(" 4a ", "12:30", 70));
			today.add(new krlxCourse(" 5a ", "13:50", 70));
			today.add(new krlxCourse(" 6a ", "15:10", 70));
		} else if (day == 2 || day == 4) {
			today.add(new krlxCourse("1/2c", "08:15", 105));
			today.add(new krlxCourse("2/3c", "10:10", 105));
			today.add(new krlxCourse(" CT ", "12:05", 60));
			today.add(new krlxCourse("4/5c", "13:15", 105));
			today.add(new krlxCourse("5/6c", "15:10", 105));
		} else if (day == 5) {
			today.add(new krlxCourse(" 1a ", "08:30", 60));
			today.add(new krlxCourse(" 2a ", "09:40", 60));
			today.add(new krlxCourse(" CV ", "10:50", 60));
			today.add(new krlxCourse(" 3a ", "12:00", 60));
			today.add(new krlxCourse(" 4a ", "13:10", 60));
			today.add(new krlxCourse(" 5a ", "14:20", 60));
			today.add(new krlxCourse(" 6a ", "15:30", 60));
		}
		return today;
	}

	/**
	 * Displays the name of the current class time, or a countdown to the next
	 * class time.
	 * 
	 * @return some weird things. This isn't the nicest program.
	 */
	public static String[] outputArray(ArrayList<krlxCourse> schedule) {
		String[] output = new String[3];
		LocalTime now = getTime();
		int i = 0;
		while (i < schedule.size() && schedule.get(i).getStart().compareTo(now) != 1) {
			i++;
		} // i is the index of the upcoming class period.
		if (i == 0) {
			long until_class = now.until(schedule.get(i).getStart(), MINUTES);
			if (until_class <= 60) {
				output[0] =  " " + String.format("%02d", until_class) + " ";
				output[1] = "minutes until " + schedule.get(i).getName() + ".";
				output[2] = "Classes have yet to begin.";
			} else {
				output[0] = " -- ";
				output[1] = "minutes until class starts.";
				output[2] = "Classes have yet to begin.";
			}
		} else if (i < schedule.size()) {
			long until_class = now.until(schedule.get(i).getStart(), MINUTES);
			if (schedule.get(i-1).getEnd().compareTo(now) != 1) {
				output[0] = " " + String.format("%02d", until_class) + " ";
				output[1] = "minutes until " + schedule.get(i).getName() + ".";
				output[2] = "Classes currently taking place.";
			} else {
				output[0] = schedule.get(i-1).getName();
				output[1] = "ends in " + now.until(schedule.get(i-1).getEnd(), MINUTES) + "minutes.";
				output[2] = "(" + until_class + " minutes until " + schedule.get(i).getName() + ".";
			}
		} else {
			output[0] = " -- ";
			output[1] = "";
			output[2] = "Classes are over for the day.";
		}
		return output;
	}

	/**
	 * Let's see. What do I actually want for the output?
	 * In the circle, I want the name of the current period. Something along
	 * the lines of "5a" will fit well, but "2/3c" could be iffier. We'll need
	 * to write it as " 5a " or something.
	 * 
	 * ( 5a ) ends in n minutes.
	 *        (k minutes until 6a.)
	 *   
	 * If there's not a current period (passing time, or within 90 minutes of
	 * the next class), it could display time until next thing.
	 * 
	 * ( 10 ) minutes until 6a.
	 * 
	 * When the day is over, go to -- mode.
	 */
	public static void main(String[] args) {
		ArrayList<krlxCourse> schedule = getSchedule();
		String[] output = outputArray(schedule);
		if (args[0].compareTo("-0") == 0) {
			System.out.println(output[0]);
		} else if (args[0].compareTo("-1") == 0) {
			System.out.println(output[1]);
		} else if (args[0].compareTo("-2") == 0) {
			System.out.println(output[2]);
		} else {
			System.out.println("Not a valid use case.");
		}
	}
}