package main;

import java.util.Scanner;

import application.Application;



public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Application app = new Application();
		String input = "";
		System.out.println("Hello, I'm your assistant. How can I help you?");
		while (!input.equals("bye")) {
			input = in.nextLine();
			app.assistant(input);
		}
		System.exit(0);
	}

}
