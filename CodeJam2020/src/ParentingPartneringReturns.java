
import java.util.Scanner;
import java.util.Arrays;
import java.util.*;
import java.io.*;

/* 
 * Solution to Parenting Partnering Returns from the 2020 Google Code Jam Qualifying Round
 * Creates an object which stores a start and end time for the task, the original position number in order to unsort a sorted array
 * and a person which determines who the task is assigned to.
 * First checks if any tasks overlap more than once, then assigns tasks. If the first person is busy at that time, the second person 
 * must be free because impossible cases were already accounted for.
 */
public class ParentingPartneringReturns implements Comparable<ParentingPartneringReturns> {
	// Create the object
	private int start;
	private int end;
	private int position;
	private String person;

	// Getter and setter methods
	public void setStart(int start) {
		this.start = start;
	}

	public int getStart() {
		return start;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getEnd() {
		return end;
	}

	public int getPosition() {
		return position;
	}

	public String getPerson() {
		return person;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	// Sorting method
	@Override
	public int compareTo(ParentingPartneringReturns o) {
		return (this.getStart() < ((ParentingPartneringReturns) o).getStart() ? -1
				: (this.getStart() == ((ParentingPartneringReturns) o).getStart() ? 0 : 1));
	}

	// To String method
	@Override
	public String toString() {
		return String.format(start + " + " + end);
	}

	public static void main(String[] args) {
		// input setup
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		int count = 1;
		for (int a = 1; a <= t; a++) {
			int x = in.nextInt();
			ParentingPartneringReturns[] obj = new ParentingPartneringReturns[x];
			// read the input
			for (int i = 0; i < x; i++) {
				obj[i] = new ParentingPartneringReturns();
				obj[i].setStart(in.nextInt());
				obj[i].setEnd(in.nextInt());
				obj[i].setPosition(i);
			}
			// sort array by start time
			Arrays.sort(obj);

			// Check for the impossible cases
			int counter = 0;
			boolean end = false;
			for (int i = 0; i < x; i++) {
				for (int j = 0; j < x; j++) {
					if (obj[i].getStart() >= obj[j].getStart() && obj[i].getStart() < obj[j].getEnd()) {
						counter++;
						if (counter > 2) {
							end = true;
							break;
						}

					}

				}
				counter = 0;

			}
			// Check whether a certain time period is already taken, if so then the other
			// person has to be able to take the task because impossibles have been ruled out
			if (end == false) {
				obj[0].setPerson("C");
				for (int i = 1; i < x; i++) {
					for (int j = 0; j < i; j++) {
						if (obj[j].getPerson() == "C") {

							if (obj[i].getStart() < obj[j].getEnd()) {

								obj[i].setPerson("J");
								break;
							} else {

								obj[i].setPerson("C");
							}
						} else {

							obj[i].setPerson("C");
						}

					}
				}
				// put final results into a string to print out
				String[] arr = new String[x];
				for (int i = 0; i < x; i++) {

					arr[obj[i].getPosition()] = obj[i].getPerson();
				}
				System.out.print("Case #" + a + ": ");
				for (int i = 0; i < x; i++) {
					System.out.print(arr[i]);
				}
				System.out.println();

			} else {
				System.out.println("Case #" + a + ": IMPOSSIBLE");
			}

		}
	}
}
