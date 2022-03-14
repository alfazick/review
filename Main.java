package flashcards;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        UserInterface ui = new UserInterface(new Scanner(System.in), new CardSet(), new FlashCardsFile());
        ui.RunProgram();

    }
}
