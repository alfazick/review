package flashcards;

public class Card {
    String front;
    String back;

    Card(String f, String b) {
        this.front = f;
        this.back = b;
    }

    public void showCard(){
        System.out.println("Card: ");
        System.out.println(this.front);
        System.out.println("Definition: ");
        System.out.println(this.back);
    }

    public void checkCard(String ans) {

        if (this.back.equals(ans)) {
            System.out.println("Correct!");
        } else {
            System.out.println("Wrong. The right answer is " + "\"" + this.back + "\"" + ".");
        }
    }



}
