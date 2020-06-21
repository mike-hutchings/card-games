import java.util.LinkedList;

public class Player {

    private String name;
    private LinkedList<PlayingCard> hand;
    private boolean cpu = false;

    public Player() {
        hand  = new LinkedList<>();
    }

    public Player(boolean cpu) {
        this.cpu = cpu;
        hand  = new LinkedList<>();
    }

    public LinkedList<PlayingCard> getHand() {
        return hand;
    }

    public boolean isCpu() {
        return cpu;
    }

    public void addCard(PlayingCard card) {
        hand.add(card);
    }

    public PlayingCard removeCard(int index) {
        return hand.remove(index);
    }

    public void printAllCards() {

        PlayingCardAsciiArt.printPlayingCardsAndIndices(hand);
        System.out.println();
    }
}
