import java.util.LinkedList;

public class PlayingCards {

    public static void sortHand(LinkedList<PlayingCard> hand) {

        for (int i = 0; i < hand.size() - 1; i++) {

            boolean swapped = false;

            for (int j = 0; j < hand.size() - 1; j++) {

                if (hand.get(j).getSuit().compareTo(hand.get(j + 1).getSuit()) >  0 || (hand.get(j).getSuit().compareTo(hand.get(j + 1).getSuit()) ==  0 && hand.get(j).getRank().compareTo(hand.get(j + 1).getRank()) >  0)) {

                    hand.add(j, hand.remove(j + 1));

                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }
}
