package main;

import java.util.Scanner;

import main.application.Application;


/**
 * Entry point class
 * @author Gbenga Oshinaga
 *
 */
public class Main {

	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Application app = new Application();
		String input = "";
		System.out.println("Hello, how can I help you?");
		while (true) {
			input = in.nextLine();
			if (input.equalsIgnoreCase("bye")) {
			  System.out.println("Bye!");
			  in.close();
			  System.exit(0);
			}
			System.out.println(app.assistant(input));
		}
	}

}
