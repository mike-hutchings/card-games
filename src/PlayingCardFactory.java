import java.util.LinkedList;

public class PlayingCardFactory {

    public static PlayingCard createCard(Suit suit, Rank rank) {
        return new PlayingCard(suit, rank);
    }

    public static LinkedList<PlayingCard> createDeck() {
        LinkedList<PlayingCard> deck = new LinkedList<>();

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(createCard(suit, rank));
            }
        }

        return deck;
    }
}
