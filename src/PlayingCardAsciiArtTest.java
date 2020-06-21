import java.util.LinkedList;

public class PlayingCardAsciiArtTest {

    public static void main(String[] args) {

        LinkedList<PlayingCard> deck = PlayingCardFactory.createDeck();

        deck.forEach(PlayingCardAsciiArt::printPlayingCard);
    }
}
