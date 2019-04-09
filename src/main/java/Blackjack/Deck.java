package Blackjack;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.shuffle;

public class Deck {
    private ArrayList<Card> deckContent = new ArrayList<>();
    private int cardAt;

    public Deck(int size) {
        this.cardAt = -1;
        generateDeck(size);
    }

    private void generateDeck(int nDecks) {
        for (int i = 0; i < nDecks; i++) {

            for (int j = 1; j < 53; j++) {

                Card card = new Card(j);
                this.deckContent.add(card);

            }
        }
        this.deckContent.add(new Card(0));
        shuffle(deckContent);
    }

    public Card drawCard(){
        cardAt++;
        return deckContent.get(cardAt);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (Card card: this.deckContent) {
            string.append(card.toString());
        }
        return string.toString();
    }
}
