public class PlayingCard {

    private Suit suit;
    private Rank rank;

    public PlayingCard(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public String toString() {
        return this.rank.name() + " of " + this.suit.name();
    }

    public boolean isLargerThan(PlayingCard other) {
        return getRank().compareTo(other.getRank()) > 0;
    }
}
