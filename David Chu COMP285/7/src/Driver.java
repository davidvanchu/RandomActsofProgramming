import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The driver class to display the menu for the NSA database. Initiates an arraylist of type Person which can be
 * an Adult or a Child. Seven menu options to display the record types and add or delete entries, and another option to
 * exit the program.
 * @author chud
 *
 */
public class Driver {
	private static ArrayList<Person> NSADatabase = new ArrayList<Person>();
	public static void main(String[] args) throws IOException {
		File file = new File("resources/input.txt");
		
		String inputData = "";
		
		FileReader reader = new FileReader(file);
		
		Scanner scanner = new Scanner(reader);
		
		while (scanner.hasNextLine()) {
			inputData = scanner.nextLine();
			String[] strArr = inputData.split(":");
			if (strArr.length == 3) {
				insertRecord(strArr[0], strArr[1], Integer.parseInt(strArr[2]));
			}
			else if (strArr.length == 5) {
				insertRecord(strArr[0], strArr[1], Integer.parseInt(strArr[2]), strArr[3], strArr[4]);
			}
		}
		scanner.close();
		titleText();
		showMenu();
	}
	
	/**
	 * Print all records.
	 */
	private static void printAllRecords() {
		for (int i = 0; i < NSADatabase.size(); i++)
			System.out.println(NSADatabase.get(i));
	}
	
	/**
	 * Print only children records.
	 */
	private static void printChildRecords() {
		for (int i = 0; i < NSADatabase.size(); i++)
			if (NSADatabase.get(i) instanceof Child)
				System.out.println(NSADatabase.get(i));
	}
	
	/**
	 * Print a specific Person based on his/her First Name
	 * @param fName first name
	 */
	private static void printSpecific(String fName) {
		for (int i = 0; i < NSADatabase.size(); i++)
			if (NSADatabase.get(i).getFName().equalsIgnoreCase(fName))
				System.out.println(NSADatabase.get(i));
	}
	
	/**
	 * 	Print Persons based on a given range of Age
	 * @param low lower bound inclusive
	 * @param high higher bound inclusive
	 */
	private static void printAgeRange(int low, int high) {
		for (int i = 0; i < NSADatabase.size(); i++)
			if (NSADatabase.get(i).getAge() >= low && NSADatabase.get(i).getAge() <= high)
				System.out.println(NSADatabase.get(i));
	}
	
	/**
	 * Allow the user to Insert new records.
	 * @param fName
	 * @param lName
	 * @param age
	 * @param fatherName
	 * @param motherName
	 */
	
	private static void insertRecord(String fName, String lName, int age, String fatherName, String motherName) {
		NSADatabase.add(new Child(fName, lName, age, fatherName, motherName));
	}
	
	/**
	 * Allow the user to Insert new records.
	 * @param fName
	 * @param lName
	 * @param age
	 */
	private static void insertRecord(String fName, String lName, int age) {
		NSADatabase.add(new Adult(fName, lName, age));
	}
	
	/**
	 * Saves db to resources/databaseSave.txt, a separate file from the one that gets read in at the start of the program.
	 * @throws IOException
	 */
	private static void saveDB() throws IOException {
		String NSADatabaseString = new String("");
		for (Person p : NSADatabase) {
			NSADatabaseString = NSADatabaseString + (p.toStringAsDBFormat());
		}
		File newTextFile = new File("resources/databaseSave.txt");
		FileWriter fw = new FileWriter(newTextFile);
		fw.write(NSADatabaseString.toString());
		fw.close();
		System.out.println(NSADatabaseString);
	}
	
	/**
	 * Displays the menu for the user, also reads in the input from the user for which menu option they chose.
	 * @throws IOException
	 */
	public static void showMenu() throws IOException {
		Scanner scanner = new Scanner(System.in);
		String input = "";
		menuText();
		input = scanner.next();
		
		switch (input.toLowerCase()) {
			case "1":
				printAllRecords();
				break;
			case "2":
				printChildRecords();
				break;
			case "3": 
				getNameForPrint();
				break;
			case "4":
				try {
					getAgeRange();
				} catch (Exception e1) {
					System.err.println("Invalid input.");
				}
				break;
			case "5":
				try {
					getNewRecordArgs();
				} catch (Exception e) {
					System.err.println("Invalid input.");
				}
				break;
			case "6": 
				getNameForDelete();
				break;
			case "7":
				saveDB();
				break;
			case "q":
				quitText();
				break;
			default:
				System.out.println("Please enter 1, 2, 3, 4, 5, 6, 7 or q.\n");
		}
		showMenu();
	}
	
	
	/**
	 * prompts the user for the last name for which they want to delete.
	 */
	private static void getNameForDelete() {
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter the last name.");
		deleteRecord(kb.next());
	}

	/**
	 * Deletes the first person in the database with the last name
	 * @param next the last name of the person to delete.
	 */
	private static void deleteRecord(String next) {
		for (Person p : NSADatabase) {
			if (p.lName.equalsIgnoreCase(next)) {
				NSADatabase.remove(p);
				return;
			}
		}
		System.out.println("No match found.");
	}

	/**
	 * Allows the user to input data which is then used to add a new record into the DB.
	 */
	private static void getNewRecordArgs() throws Exception{
		System.out.println("Enter a new record in the format: firstName lastName age father mother.\nFather and mother are optional.");
		Scanner kb = new Scanner(System.in);
		String inputData = kb.nextLine();
		String[] strArr = inputData.split(" ");

		if (strArr.length == 3) {
			insertRecord(strArr[0], strArr[1], Integer.parseInt(strArr[2]));
		}
		else if (strArr.length == 5) {
			insertRecord(strArr[0], strArr[1], Integer.parseInt(strArr[2]), strArr[3], strArr[4]);
		}		
	}

	/**
	 * reads in user input for the name to print records of.
	 */
	private static void getNameForPrint() {
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter the first name.");
		printSpecific(kb.next());
		}
	
	/**
	 * reads in user input for the minimum and maximum age to print records of.
	 * @throws Exception
	 */
	private static void getAgeRange() throws Exception {
		Scanner kb = new Scanner(System.in);
		System.out.println("Enter minimum age, then maximum age.");
		printAgeRange(kb.nextInt(), kb.nextInt());
	}

	/**
	 * prints title text
	 */
	private static void titleText() {
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println(" __        __     _                                _           _    _           \n"
				+ " \\ \\      / /___ | |  ___  ___   _ __ ___    ___  | |_  ___   | |_ | |__    ___\n"
				+ "  \\ \\ /\\ / // _ \\| | / __|/ _ \\ | '_ ` _ \\  / _ \\ | __|/ _ \\  | __|| '_ \\  / _ \\\n"
				+ "   \\ V  V /|  __/| || (__| (_) || | | | | ||  __/ | |_| (_) | | |_ | | | ||  __/\n"
				+ "    \\_/\\_/  \\___||_| \\___|\\___/ |_| |_| |_| \\___|  \\__|\\___/   \\__||_| |_| \\___|\n"
				+ "    _   _  ____     _      ____          _          _                        _\n"
				+ "   | \\ | |/ ___|   / \\    |  _ \\   __ _ | |_  __ _ | |__    __ _  ___   ___ | |\n"
				+ "   |  \\| |\\___ \\  / _ \\   | | | | / _` || __|/ _` || '_ \\  / _` |/ __| / _ \\| |\n"
				+ "   | |\\  | ___) |/ ___ \\  | |_| || (_| || |_| (_| || |_) || (_| |\\__ \\|  __/|_|\n"
				+ "   |_| \\_||____//_/   \\_\\ |____/  \\__,_| \\__|\\__,_||_.__/  \\__,_||___/ \\___|(_)\n"
				+ "\n----------------------------------------------------------------------------------");
	}
	
	/**
	 * prints the menu
	 */
	private static void menuText() {
		System.out.println("-------------------------------------------------");
		System.out.println("|1: Print all records                           |\n"
				+ "|2: Print children records                      |\n"
				+ "|3: Print specific person                       |\n"
				+ "|4: Print based on age range (inclusive)        |\n"
				+ "|5: Insert new record                           |\n"
				+ "|6: Delete specific record                      |\n"
				+ "|7: Save database                               |\n"
				+ "|Press q to terminate the program.              |");
		System.out.println("-------------------------------------------------");
	}
	
	/**
	 * prints quitting text then exits the program.
	 */
	private static void quitText() {
		System.out.println("Quitting...");
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("3...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("2...");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("1...\nProgram exited");
		System.exit(1);
	}
}
