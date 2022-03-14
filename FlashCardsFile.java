package flashcards;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;

public class FlashCardsFile {



    public void writeToFile(String filename,CardSet cards) {
        try {
            PrintWriter writer =  new PrintWriter(filename, "UTF-8");

            for (Card c : cards.getCards().values()){
                writer.println(c.front + "," + c.back);
            }
            writer.close();
            System.out.println(cards.sizeofCard() + " cards have been saved");

        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();

        }

    }



    CardSet readFromFile(String filename) {
        CardSet cardfromfile = new CardSet();

        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null){
                String[] c = line.toString().split(",");
                cardfromfile.addCard(new Card(c[0],c[1]));
                line = br.readLine();
            }

            return cardfromfile;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return cardfromfile;
    }
}
