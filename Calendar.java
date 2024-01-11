/**
 * Prints the calendars of all the years in the 20th century.
 */
public class Calendar {
    // Starting the calendar on 1/1/1900
	static int dayOfMonth = 1;
	static int month = 1;
	static int year;
	static int nDaysInMonth = 31; // Number of days in January

	/**
	 * Prints the calendars of all the years in the 20th century. Also prints the
	 * number of Sundays that occured on the first day of the month during this period.
	 */
	public static void main(String args[]) {
		// Advances the date and the day-of-the-week from 1/1/1900 till 31/12/1999, inclusive.
	    // Prints each date dd/mm/yyyy in a separate line. If the day is a Sunday, prints "Sunday".
	    // The following variable, used for debugging purposes, counts how many days were advanced so far.
		year = Integer.parseInt(args[0]);
		int nextYear = year + 1;
	    //// Write the necessary initialization code, and replace the condition
	    //// of the while loop with the necessary condition
	 	while (year != nextYear) {
			System.out.println(dayOfMonth + "/" + month + "/" + year + " ");
			advance();
		}
	 }
	 //Added my own function to get day of the week simpler
	 // Advances the date (day, month, year) and the day-of-the-week.
	 // If the month changes, sets the number of days in this month.
	 // Side effects: changes the static variables dayOfMonth, month, year, dayOfWeek, nDaysInMonth.
	 private static void advance() {
		dayOfMonth++;
		if (dayOfMonth > nDaysInMonth) {
			dayOfMonth = 1;
			if (month == 12) {
				month = 1;
				year++;
			}
			else {
				month++;
			}
			nDaysInMonth = nDaysInMonth(month, year);
		}
	 }

    // Returns true if the given year is a leap year, false otherwise.
	private static boolean isLeapYear(int year) {
	    // Replace the following statement with your code
		if (year % 4 == 0 && year % 100 != 0) {
			return true;
		}
		return false;
	}

	// Returns the number of days in the given month and year.
	// April, June, September, and November have 30 days each.
	// February has 28 days in a common year, and 29 days in a leap year.
	// All the other months have 31 days.
	private static int nDaysInMonth(int month, int year) {
		// Replace the following statement with your code
		switch (month) {
			case 4:
			case 6:
			case 9:
			case 11:
				return 30;
			case 2:
				if (isLeapYear(year)) {
					return 29;
				}
				return 28;
			default:
				return 31;
		}
	}
}