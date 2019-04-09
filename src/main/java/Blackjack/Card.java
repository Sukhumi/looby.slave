package Blackjack;

public class Card {
    private String suit;
    private String value;
    private int ID;

    public Card(int ID) {
        this.ID = ID;
        generateCard(ID);
    }

    private void generateCard(int n) {

        if (n == 0)
        {
            this.suit = " ";
            this.value = "Joker";
            return;
        }

        switch ((n - 1) / 13) {

            case 0:
                this.suit = "\u2663";
                break;

            case 1:
                this.suit = "\u2666";
                break;
            case 2:
                this.suit = "\u2665";
                break;
            case 3:
                this.suit = "\u2660";
                break;
        }
        switch (n % 13) {

            case 0:
                this.value = "A";
                break;
            case 1:
                this.value = "2";
                break;
            case 2:
                this.value = "3";
                break;
            case 3:
                this.value = "4";
                break;
            case 4:
                this.value = "5";
                break;
            case 5:
                this.value = "6";
                break;
            case 6:
                this.value = "7";
                break;
            case 7:
                this.value = "8";
                break;
            case 8:
                this.value = "9";
                break;
            case 9:
                this.value = "10";
                break;
            case 10:
                this.value = "J";
                break;
            case 11:
                this.value = "Q";
                break;
            case 12:
                this.value = "K";
                break;

        }
    }

    public String getValue() {
        return value;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return String.format("%s%s", this.value, this.suit);
    }
}
