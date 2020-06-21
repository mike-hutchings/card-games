import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class PlayingCardAsciiArt {

    private static Path cardArtFile = Paths.get(".\\src\\card_ascii.txt");
    private static LinkedList<PlayingCard> deck = PlayingCardFactory.createDeck();
    private static HashMap<String, String> art = new HashMap<>();

    static {

        ArrayList<String> rawCardArt = (ArrayList<String>) SimpleFileReader.readFile(cardArtFile);

        deck.forEach(card -> {

            rawCardArt.forEach(line -> {

                String[] splitLine = line.split("=");

                String cardName = splitLine[0];
                String cardArt = splitLine[1];

                if (card.toString().equals(cardName)) {
                    art.put(card.toString(), cardArt);
                }

            });
        });
    }

    public static void printPlayingCard(PlayingCard inputCard) {

        String[] artLines = art.get(inputCard.toString()).split("\\\\n");

        for (String line : artLines) {

            System.out.println(line);
        }
    }

    public static void printHandIndices(LinkedList<PlayingCard> inputCards) {

        for (int i = 0; i < inputCards.size(); i++) {

            System.out.printf("   %d     ", i);
        }

        System.out.print("\n");
    }

    public static void printPlayingCards(LinkedList<PlayingCard> inputCards) {

        LinkedList<String[]> allArtLines = new LinkedList<>();

        inputCards.forEach(card -> {

            String[] artLines = art.get(card.toString()).split("\\\\n");

            allArtLines.add(artLines);
        });

        for (int i = 0; i < 6; i++) {

            for (String[] artLines : allArtLines) {

                System.out.printf("%s  ", artLines[i]);
            };

            System.out.println();
        }
    }

    public static void printPlayingCardsAndIndices(LinkedList<PlayingCard> inputCards) {

        printHandIndices(inputCards);
        printPlayingCards(inputCards);
    }
}
