import java.util.Collections;
import java.util.LinkedList;

public abstract class CardGame {

    private LinkedList<Player> players = new LinkedList<>();
    private LinkedList<PlayingCard> deck;
    private LinkedList<PlayingCard> discard = new LinkedList<>();

    private int initialHandSize;
    private boolean gameOver = false;

    public CardGame(int initialHandSize, int numberOfPlayers) {

        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player());
        }

        deck = PlayingCardFactory.createDeck();
        Collections.shuffle(deck);

        this.initialHandSize = initialHandSize;
    }

    public CardGame(int initialHandSize, int numberOfHumanPlayers, int numberOfComputerPlayers) {

        for (int i = 0; i < numberOfHumanPlayers; i++) {
            players.add(new Player());
        }

        for (int i = 0; i < numberOfComputerPlayers; i++) {
            players.add(new Player(true));
        }

        Collections.shuffle(players);

        deck = PlayingCardFactory.createDeck();
        Collections.shuffle(deck);

        this.initialHandSize = initialHandSize;
    }

    public LinkedList<Player> getPlayers() {
        return players;
    }

    public LinkedList<PlayingCard> getDeck() {
        return deck;
    }

    public LinkedList<PlayingCard> getDiscard() {
        return discard;
    }

    public boolean isDiscardEmpty() {
        return discard.isEmpty();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void dealCards() {

        int cardsToDeal = initialHandSize;

        while (cardsToDeal > 0) {

            cardsToDeal -= 1;

            for (Player player : getPlayers()) {
                player.addCard(deck.poll());
            }
        }
    }

    public void dealCards(Player player, int numberOfCardsToDeal) {

        int cardsToDeal = numberOfCardsToDeal;

        while (cardsToDeal > 0) {

            cardsToDeal -= 1;

            player.addCard(deck.poll());
        }
    }

    public void dealCard(Player player) {
        player.addCard(deck.poll());
    }

    public void playGame() {

        while (!isGameOver()) {

            for (Player player : players) {

                takeTurn(player);

                if (isGameOver()) {
                    break;
                }
            }
        }

        endGame();
    }

    public void discardCard(PlayingCard card) {
        discard.addFirst(card);
    }

    public PlayingCard getTopOfDiscard() {
        return discard.getFirst();
    }

    public abstract void takeTurn(Player player);

    public abstract void endGame();
}
