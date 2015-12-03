
import java.util.Scanner;

/**
 * The Menu class is the driver for this assignment. It instantiates an array of animals (simulating a shelter)
 * and presents a menu to the user which allows them to see how many animals there are,
 * the names and types of each animal, a list of animals that can fly, and
 * a more detailed description of each animal.
 * @author chud
 */
public class Menu {
	static Animal[] shelter = new Animal[25];
	public static void main(String[] args) {
		//set up, fills the shelter with animals.
		shelter[0] = new MiniPig("Oatmeal", 1);
		shelter[1] = new Pig("Omelette", 3);
		shelter[2] = new FlyMiniPig("Bread", 2);
		shelter[3] = new Dog("Yogurt", 0, "Samoyed");
		shelter[4] = new Cat("Jeff", 6);
		shelter[5] = new FlyCat("Joel", 5);
		shelter[6] = new FlyDog("Burger", 4, "Samoyed");
		shelter[7] = new Dog("Doyle", 2, "Golden Retriever");
		shelter[8] = new Pig("Chris P Bacon", 0);
		shelter[9] = new Cat("Max", 8);
		shelter[10] = new Cat("John", 12);
		shelter[11] = new FlyDog("Ketchup", 6, "Pitbull");
		shelter[12] = new FlyDog("Pete", 2, "Pitbull");
		shelter[13] = new FlyCat("Tyler", 2);
		shelter[14] = new FlyPig("Swift", 5);
		shelter[15] = new Dog("Lovey", 4, "Labrador");
		shelter[16] = new Dog("Joby", 7, "Yorkshire Terrier");
		shelter[17] = new Dog("Button", 8, "Border Collie");
		shelter[18] = new FlyMiniPig("Butter", 1);
		shelter[19] = new FlyMiniPig("Baker", 4);
		shelter[20] = new FlyMiniPig("Pastry", 3);
		shelter[21] = new FlyMiniPig("Lenny", 9);
		shelter[22] = new MiniPig("Cereal", 4);
		shelter[23] = new MiniPig("Moxie", 0);
		shelter[24] = new Pig("Coffee", 2);
		
		showMenu();
	}
	
	
	/**
	 * Shows the UI/menu and asks user for input. Takes in input and outputs text to console based on what is selected.
	 */
	public static void showMenu() {
		Scanner kb = new Scanner(System.in);
		String input = "";
		menuText();
		input = kb.next();
		
		switch (input.toLowerCase()) {
			case "1":
				System.out.println("The total number of animals in this shelter is " + Animal.getNumAnimals());
				break;
			case "2":
				for (Animal animal : shelter) {
					//System.out.println(animal.getName() + " is a " + animal.getType());
					System.out.print(animal.getName() + " ");
					for (int i = 0; i < 15 - animal.getName().length(); i++)
						System.out.print("-");
					System.out.print("> ");
					System.out.println(animal.getType());
				}
				break;
			case "3": 
				for (Animal animal : shelter)
					if (animal.isCanFly())
						System.out.println(animal.getName() + " can fly");
				break;
			case "4":
				for (Animal animal : shelter) 
					System.out.println(animal);
				break;
			case "q":
				quitText();
				break;
			default:
				System.out.println("Please enter 1, 2, 3, 4, or q.\n");
		}
		showMenu();
	}
	
	//prints the menu
	private static void menuText() {
		System.out.println("-------------------------------------------------");
		System.out.println("Press 1 to see how many animals we have (total number of Animals)\n"
				+ "Press 2 to see the name and kind of each animal\n"
				+ "Press 3 to see which animals can fly\n"
				+ "Press 4 to see a description of each animal\n"
				+ "Press q to terminate the program.");
		System.out.println("-------------------------------------------------");
	}
	
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