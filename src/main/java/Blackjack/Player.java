package Blackjack;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> cards = new ArrayList<>();
    private int blackJackValue;
    private int aces = 0;

    public Player(String name) {
        this.name = name;
        this.blackJackValue = 0;
    }

    public void getCard(Card drawnCard) {
        cards.add(drawnCard);
        String cardValue = drawnCard.getValue();
        try {
            int value = Integer.parseInt(cardValue);
            this.blackJackValue += value;
        } catch (Exception e) {
            switch (cardValue) {
                case "J":
                    blackJackValue += 10;
                    break;
                case "Q":
                    blackJackValue += 10;
                    break;
                case "K":
                    blackJackValue += 10;
                    break;
                case "A":
                    blackJackValue += 11;
                    aces++;
                    break;
            }
        }
        while(blackJackValue > 21 && aces > 0)
        {
            blackJackValue -= 10;
            aces--;
        }
    }

    public int getblackJackValue() {
        return this.blackJackValue;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {

        StringBuilder string = new StringBuilder();
        string.append(String.format("%s (%d): %n", this.name, this.blackJackValue));

        for (Card card : cards) {
            string.append(card.toString() + " ");
        }
        return string.toString();
    }
}
