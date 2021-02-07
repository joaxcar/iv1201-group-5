package se.kth.iv1201.recruitmentapplicationgroup5.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Class representing the birthdate of a person with precision to the day, in numbers.
 */

@Entity
public class BirthDate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private int day;
	private int month;
	private int year;
	
	public BirthDate() {
		
	}
	
	/**
	 * Creates an instance of BirthDate with the given year, month and day.
	 * @param year Year of birth
	 * @param month Month of birth, given as number.
	 * @param day Day of birth 
	 */
	public BirthDate(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
}
