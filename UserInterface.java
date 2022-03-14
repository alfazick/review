package flashcards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;



public class UserInterface {
    Scanner scanner;
    CardSet activecards;
    FlashCardsFile cardfiles;
    HashMap<String,String> termdef;
    HashMap<String,String> defterm;

    enum Action {
        ADD, REMOVE, IMPORT, EXPORT, ASK, EXIT
    }

    public UserInterface(Scanner sc, CardSet ac, FlashCardsFile fcf)
    {
        this.activecards = ac;
        this.scanner = sc;
        this.cardfiles = fcf;
        this.termdef = new HashMap<>();
        this.defterm = new HashMap<>();

    }

    public void RunProgram(){
        boolean run = true;
        while (run){
            System.out.println("Input the action (add, remove, import, export, ask, exit):");
            String action = this.readInput();
            Action cur_action = Action.valueOf(action.toUpperCase());

            switch (cur_action) {
                case ADD:
                    this.addNewCard(this.createCard());
                    break;
                case REMOVE:
                    this.removeCard();
                    break;
                case IMPORT:
                    this.importFile();
                    break;
                case EXPORT:
                    this.exportFile();
                    break;
                case ASK:
                    this.startTest();
                    break;
                case EXIT:
                    run = false;
                    break;
            }
        }
        System.out.println("Bye bye!");
    }

    private void exportFile() {
        System.out.println("File name:");
        String filename = this.readInput();
        this.cardfiles.writeToFile(filename,activecards);
        System.out.println(activecards.sizeofCard() + " cards have been saved.");
    }

    private void importFile() {
        System.out.println("File name:");
        String filename = this.readInput();

        CardSet new_cards = this.cardfiles.readFromFile(filename);

        int n = 0;
        for (Card c : new_cards.getCards().values()) {
            activecards.addCard(c);
            n += 1;
        }
        if (n == 0){
            System.out.println("File not found.");
        } else {
            System.out.println(n + " cards have been loaded.");
        }
    }


    private void removeCard() {
        System.out.println("Which card?");
        String termtodel = this.readInput();

        if (activecards.hasCard(termtodel)){
            String deftodel = termdef.get(termtodel);
            termdef.remove(termtodel);
            defterm.remove(deftodel);
            Card c = new Card(termtodel,"Bad");
            activecards.remCard(c);
            System.out.println("The card has been removed.");

        } else {
            System.out.println("Can't remove \"" + termtodel + "\": there is no such card.");
        }

    }

    public Card createCard(){

        String term = "";
        boolean termaccepted = false;
        int n = activecards.sizeofCard() + 1;
        System.out.println("The card:");

        while (!termaccepted)
        {

            term = readInput();

            if (this.termdef.containsKey(term)){
                System.out.println("The card \"" + term + "\" already exists.");
                return new Card("#","#");
            } else {
                termaccepted = true;
            }
        }



        String ans = "";
        boolean accepted = false;

        System.out.println("The definition of the card:");
        while (!accepted)
        {
            ans = readInput();

            if (this.defterm.containsKey(ans)){
                System.out.println("The definition \"" + ans + "\" already exists. Try again:");
                return new Card("#","#");
            } else {
                accepted = true;
            }
        }

        return (new Card(term, ans));
    }

    public void addNewCard(Card c) {
        if (c.front.equals("#")){
            return;
        }

        this.termdef.put(c.front,c.back);
        this.defterm.put(c.back,c.front);

        this.activecards.addCard(c);
        System.out.println("The pair (\"" + c.front + "\":\"" + c.back + "\") has been added.");

    }

    public String readInput(){
        return this.scanner.nextLine();
    }

    public void CheckCard(Card c) {
        System.out.println("Print the definition of " + "\"" + c.front + "\":");
        String ans = readInput();

        //The right answer is "correct answer",but your definition is correct for
        // "term for user's answer". ,where "correct answer" is the actual definition
        // for the requested term, and "term for user's answer" is the appropriate term
        // for the user-entered definition.

        if (defterm.containsKey(ans) && !(c.back.equals(ans))){
            System.out.println(" Wrong. The right answer is \"" + c.back +
                    "\", but your definition is correct for \"" + defterm.get(ans) + "\"." );
            return;
        }

        c.checkCard(ans);
    }



    public void startTest(){

        System.out.println("How many times to ask?");
        int n = Integer.parseInt(this.readInput());

        ArrayList<Card> currencards = new ArrayList<>(this.activecards.getCards().values());
        Random rand = new Random();
        while (n > 0) {
            int randomidx = rand.nextInt(currencards.size());
            Card c = currencards.get(randomidx);
            CheckCard(c);
            n -= 1;
        }
    }

    }

