import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class CrazyEights extends CardGame {

    private Suit currentSuit;
    private Rank currentRank;
    private boolean jackInPlay = false;
    private boolean deuceInPlay = false;

    public CrazyEights(int numberOfPlayers) {
        super(8, numberOfPlayers);
        dealCards();
        playGame();
    }

    public CrazyEights(int numberOfHumanPlayers, int numberOfComputerPlayers) {
        super(8, numberOfHumanPlayers, numberOfComputerPlayers);
        dealCards();
        playGame();
    }

    @Override
    public void takeTurn(Player player) {

        if (jackInPlay) {
            jackInPlay = false;
            System.out.printf("Player %d loses their turn!\n\n", getPlayers().indexOf(player));
            return;
        }

        if (deuceInPlay) {
            deuceInPlay = false;
            System.out.printf("Player %d draws 2 cards!\n\n", getPlayers().indexOf(player));
            dealCards(player, 2);
        }

        int command = -1;
        Suit newSuitChoice = null;

        if (player.isCpu()) {

            LinkedList<PlayingCard> playableCards = new LinkedList<>();

            for (PlayingCard card : player.getHand()) {

                if (currentSuit == null || card.getSuit() == currentSuit || card.getRank() == currentRank || card.getRank() == Rank.EIGHT) {

                    playableCards.add(card);
                }
            }

            if (!playableCards.isEmpty()) {

                Random random = new Random();
                int tempIndex = random.nextInt(playableCards.size());

                command = player.getHand().indexOf(playableCards.get(tempIndex));

                if (player.getHand().get(command).getRank() == Rank.EIGHT) {

                    int numClubs = 0;
                    int numDiamonds = 0;
                    int numHearts = 0;
                    int numSpades = 0;

                    for (PlayingCard card : player.getHand()) {

                        switch (card.getSuit()) {

                            case CLUBS:
                                numClubs++;
                                break;

                            case DIAMONDS:
                                numDiamonds++;
                                break;

                            case HEARTS:
                                numHearts++;
                                break;

                            case SPADES:
                                numSpades++;
                                break;
                        }
                    }

                    int newSuitChoiceCount = Math.max(numSpades, Math.max(numHearts, Math.max(numClubs, numDiamonds)));

                    if (newSuitChoiceCount == numClubs) {

                        newSuitChoice = Suit.CLUBS;
                    }
                    else if (newSuitChoiceCount == numDiamonds) {

                        newSuitChoice = Suit.DIAMONDS;
                    }
                    else if (newSuitChoiceCount == numHearts) {

                        newSuitChoice = Suit.HEARTS;
                    }
                    else {

                        newSuitChoice = Suit.SPADES;
                    }
                }
            }
        }

        else {

            player.printAllCards();

            Scanner scanner = new Scanner(System.in);

            while (scanner.hasNext()) {

                String input = scanner.next();

                if (input.matches("^\\d+$")) {

                    int index = Integer.parseInt(input);

                    if (index < player.getHand().size() && index > -1) {

                        PlayingCard card = player.getHand().get(index);

                        if (currentSuit == null || card.getSuit() == currentSuit || card.getRank() == currentRank || card.getRank() == Rank.EIGHT) {

                            command = index;

                            if (card.getRank() == Rank.EIGHT) {

                                while (scanner.hasNext()) {

                                    String input2 = scanner.next();

                                    if (input2.matches("^[cC]$")) {
                                        newSuitChoice = Suit.CLUBS;
                                        break;
                                    }
                                    else if (input2.matches("^[dD]$")) {
                                        newSuitChoice = Suit.DIAMONDS;
                                        break;
                                    }
                                    else if (input2.matches("^[hH]$")) {
                                        newSuitChoice = Suit.HEARTS;
                                        break;
                                    }
                                    else if (input2.matches("^[sS]$")) {
                                        newSuitChoice = Suit.SPADES;
                                        break;
                                    }
                                }
                            }

                            break;
                        }
                    }
                }

                else if (input.matches("^[dD]$")) {
                    break;
                }

                else if (input.matches("^[cC]$")) {
                    System.out.printf("Current rank: %s\nCurrent suit: %s\n\n", currentRank, currentSuit);
                }

                else if (input.matches("^[sS]$")) {

                    PlayingCards.sortHand(player.getHand());

                    player.printAllCards();
                }
            }
        }

        if (command == -1) {
            dealCard(player);
            System.out.printf("Player %d drew a card (%d cards in hand)\n\n", getPlayers().indexOf(player), player.getHand().size());
        }

        else {

            PlayingCard playedCard = player.removeCard(command);
            discardCard(playedCard);

            String suitUpdatedMessage = "";

            if (playedCard.getRank() == Rank.EIGHT) {
                currentSuit = newSuitChoice;
                suitUpdatedMessage = String.format("and changed the suit to %s ", currentSuit.name());
            }
            else {
                currentSuit = playedCard.getSuit();
            }

            currentRank = playedCard.getRank();

            if (playedCard.getRank() == Rank.JACK) {
                jackInPlay = true;
            }

            else if (playedCard.getRank() == Rank.TWO) {
                deuceInPlay = true;
            }

            System.out.printf("Player %d played:\n", getPlayers().indexOf(player));
            PlayingCardAsciiArt.printPlayingCard(playedCard);
            System.out.println();
            System.out.printf("%s(%d cards in hand)\n\n", suitUpdatedMessage, player.getHand().size());
        }

        if (player.getHand().isEmpty()) {
            setGameOver(true);
        }
    }

    @Override
    public void endGame() {

        for (Player player : getPlayers()) {

            if (player.getHand().isEmpty()) {

                System.out.printf("Player %d wins the game!\n\n", getPlayers().indexOf(player));
            }
        }
    }
}
