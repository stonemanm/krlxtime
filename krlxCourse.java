/**
 * Stores the name of a course slot and LocalTime objects corresponding to its
 * start time and end time. A helper class for krlxtime.
 * 
 * Last modified 2015-04-20
 * @author Michael Stoneman
 */

import java.time.LocalTime;

public class krlxCourse {
	private String name = "";
	private LocalTime start = null;
	private LocalTime end = null;

	public krlxCourse(String name, String startTime, long length) {
		this.name = name;
		this.start = LocalTime.parse(startTime);
		this.end = this.start.plusMinutes(length);
	}

	public void setName(String name) { this.name = name; }

	public void setStart(String start) { this.start = LocalTime.parse(start); }

	public void setEnd(String end) { this.end = LocalTime.parse(end); }

	public String getName() { return this.name; }

	public String getShortName() { return this.name.replaceAll("\\s",""); }

	public LocalTime getStart() { return this.start; }

	public LocalTime getEnd() { return this.end; }

}