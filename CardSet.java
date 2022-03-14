package flashcards;

import java.util.ArrayList;
import java.util.HashMap;

public class CardSet {
    private HashMap< String, Card> myflashcards;


    CardSet() {
        this.myflashcards = new HashMap<>();
    }

    public void addCard(Card c){
        this.myflashcards.put(c.front,c);
    }

    public boolean hasCard(String term){
        return myflashcards.containsKey(term);
    }

    public void remCard(Card c){
        myflashcards.remove(c.front);
    }

    public int sizeofCard(){
        return this.myflashcards.size();
    }

    public HashMap<String,Card> getCards(){
        return (HashMap<String, Card>) this.myflashcards.clone();
    }




}
